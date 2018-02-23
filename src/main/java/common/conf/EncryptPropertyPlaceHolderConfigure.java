package common.conf;

import common.util.StringEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 加密属性配置器
 */
public class EncryptPropertyPlaceHolderConfigure extends PropertyPlaceholderConfigurer {
    private List<String> encryptPropertyNameList = new ArrayList<String>();

    /**
     * 对特定属性的属性值进行转换
     *
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProperty(propertyName)) {
            String decryptValue = StringEncrypter.getDecryptValue(propertyValue);
            //System.out.println("\nProperty:" + propertyName + "\nValue:" + decryptValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /**
     * 判断是否为加密属性
     *
     * @param propertyName 属性名称
     * @return True 表示为加密属性，False 表示为非加密属性
     */
    private boolean isEncryptProperty(String propertyName) {
        for (String encryptPropertyName : encryptPropertyNameList) {
            if (encryptPropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getEncryptPropertyNameList() {
        return encryptPropertyNameList;
    }

    public void setEncryptPropertyNameList(List<String> list) {
        this.encryptPropertyNameList = list;
    }
}
