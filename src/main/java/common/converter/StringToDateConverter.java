package common.converter;

import common.constant.GlobalConstant;
import common.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 日期类型数据转换器，将String转换成日期对象
 *
 * @author bianJ
 * @version 1.0.0 2017-07-15
 */
public class StringToDateConverter implements Converter<String, Date> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Date convert(String source) {
        Date date = null;
        if (StringUtils.isNotBlank(source)) {
            source = source.trim();
            if (!source.equalsIgnoreCase("null")) {
                if (source.length() > 10) {
                    date = DateUtils.stringToDate(source, GlobalConstant.DATE_TIME_PATTERN);
                } else if (source.length() == 10) {
                    date = DateUtils.stringToDate(source, GlobalConstant.DATE_PATTERN);
                }
            }
        }
        return date;
    }
}
