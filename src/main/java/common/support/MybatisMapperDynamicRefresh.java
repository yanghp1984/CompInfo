package common.support;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

/**
 * 动态刷新MyBatis的Mapper配置文件，该功能只能用于开发环境，不得用于生产环境。
 *
 * @author bianj
 * @version 1.0.0 2017-02-06
 */
public class MybatisMapperDynamicRefresh implements InitializingBean, ApplicationContextAware {
    private Logger logger = LogManager.getLogger(this.getClass());

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * 用于存放Mapper配置文件的集合
     */
    private HashMap<String, String> mappers = new HashMap<String, String>();

    /**
     * Spring应用上下文
     */
    private volatile ApplicationContext context;

    /**
     * Mapper配置文件路径，不要加"classpath*:"
     */
    private String mapperLocations;

    /**
     * 定时器延迟启动时间（秒）
     */
    private Integer delay = 10;

    /**
     * 定时器每隔多久执行一次
     */
    private Integer period = 5;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private void init() throws Exception {
        if (StringUtils.isBlank(this.mapperLocations)) {
            throw new Exception("Mapper配置文件路径不能为空！");
        }
        this.mapperLocations = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperLocations;

        Resource[] resources = findResources();
        if (resources != null) {
            for (Resource resource : resources) {
                String key = resource.getURI().toString();
                String value = getMd(resource);
                mappers.put(key, value);
            }
        }
    }

    private void refreshMapper() throws Exception {
        SqlSessionFactory factory = context.getBean(SqlSessionFactory.class);
        Configuration configuration = factory.getConfiguration();
        removeConfig(configuration);

        Resource[] resources = findResources();
        for (Resource resource : resources) {
            try {
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(), configuration,
                        resource.toString(), configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            } finally {
                ErrorContext.instance().reset();
            }
        }
    }

    private boolean isChanged() throws IOException {
        boolean flag = false;
        Resource[] resources = findResources();
        if (resources != null && resources.length > 0) {
            for (Resource resource : resources) {
                String key = resource.getURI().toString();
                String value = getMd(resource);
                if (!value.equals(mappers.get(key))) {
                    flag = true;
                    mappers.put(key, value);
                }
            }
        }
        return flag;
    }

    private Resource[] findResources() throws IOException {
        return resourcePatternResolver.getResources(mapperLocations);
    }

    private String getMd(Resource resource) throws IOException {
        return new StringBuilder().append(resource.contentLength()).append("-").append(resource.lastModified()).toString();
    }

    private void removeConfig(Configuration configuration) throws Exception {
        Class<?> classConfig = configuration.getClass();
        clearMap(classConfig, configuration, "mappedStatements");
        clearMap(classConfig, configuration, "caches");
        clearMap(classConfig, configuration, "resultMaps");
        clearMap(classConfig, configuration, "parameterMaps");
        clearMap(classConfig, configuration, "keyGenerators");
        clearMap(classConfig, configuration, "sqlFragments");
        clearSet(classConfig, configuration, "loadedResources");
    }

    private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
        Field field = classConfig.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((Set) field.get(configuration)).clear();
    }

    private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {
        Field field = classConfig.getDeclaredField(fieldName);
        field.setAccessible(true);
        ((Set) field.get(configuration)).clear();
    }

    /**
     * 获取Mapper配置文件路径，不要加"classpath*:"
     *
     * @return Mapper配置文件路径
     */
    public String getMapperLocations() {
        return mapperLocations;
    }

    /**
     * 设置Mapper配置文件路径，不要加"classpath*:"
     *
     * @param mapperLocations Mapper配置文件路径，不要加"classpath*:"
     */
    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    /**
     * 获取定时器延迟启动时间（秒）
     *
     * @return 定时器延迟启动时间（秒）
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * 设置定时器延迟启动时间（秒）
     *
     * @param delay 定时器延迟启动时间（秒）
     */
    public void setDelay(Integer delay) {
        if (delay != null && delay > 0) {
            this.delay = delay;
        }
    }

    /**
     * 获取定时器每隔多久执行一次
     *
     * @return 定时器每隔多久执行一次
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置定时器每隔多久执行一次
     *
     * @param period 定时器每隔多久执行一次
     */
    public void setPeriod(Integer period) {
        if (period != null && period > 0) {
            this.period = period;
        }
    }
}
