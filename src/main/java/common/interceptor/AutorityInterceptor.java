package common.interceptor;

import common.constant.GlobalConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户权限拦截器
 *
 * @author bianj
 * @version 1.0.0 2017-07-10
 */
public class AutorityInterceptor extends HandlerInterceptorAdapter {
    /** 配置不需要登录验证的URI列表 */
    private List<String> excludeLoginUriList;

    /** 配置登录以后不需要进行权限验证的URI列表 */
    private List<String> excludeAuthorityUriList;

    private PathMatcher pathMatcher = new AntPathMatcher();

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preHandler 调用controller具体方法之前调用

        String errorMsg = (String) request.getAttribute(GlobalConstant.ERROR_MSG_KEY);
        if (StringUtils.isNotBlank(errorMsg)) {
            return true;
        }

        // 用户请求的URI
        String uri = urlPathHelper.getLookupPathForRequest(request);
        // 判断当前请求是否需要拦截
        if (!CollectionUtils.isEmpty(excludeLoginUriList)) {
            for (String excludeUri : excludeLoginUriList) {
                if (pathMatcher.match(excludeUri, uri)) {
                    return true;
                }
            }
        }

        // 判断当前请求是否需要进行权限验证
        if (!CollectionUtils.isEmpty(excludeAuthorityUriList)) {
            for (String excludeUri : excludeAuthorityUriList) {
                if (pathMatcher.match(excludeUri, uri)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取不需要登录验证的URI列表
     * @return 不需要登录验证的URI列表
     */
    public List<String> getExcludeLoginUriList() {
        return excludeLoginUriList;
    }

    /**
     * 设置不需要登录验证的URI列表
     * @param excludeLoginUriList 不需要登录验证的URI列表
     */
    public void setExcludeLoginUriList(List<String> excludeLoginUriList) {
        this.excludeLoginUriList = excludeLoginUriList;
    }

    /**
     * 获取登录以后不需要进行权限验证的URI列表
     * @return 登录以后不需要进行权限验证的URI列表
     */
    public List<String> getExcludeAuthorityUriList() {
        return excludeAuthorityUriList;
    }

    /**
     * 设置登录以后不需要进行权限验证的URI列表
     * @param excludeAuthorityUriList 登录以后不需要进行权限验证的URI列表
     */
    public void setExcludeAuthorityUriList(List<String> excludeAuthorityUriList) {
        this.excludeAuthorityUriList = excludeAuthorityUriList;
    }
}
