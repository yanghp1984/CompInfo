package common.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用于重写 toString() 方法的公共 Bean
 * @author yanghp1984
 * @version 1.0.0 2017-12-18
 */
public abstract class ToStringBean implements Serializable {
	private static final long serialVersionUID = -2279750420590662919L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
