package indi.simuel.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Author simuel_tang
 * @Date 2021/3/16
 * @Time 19:44
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    // 需要加密的字段数组
    private final String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    /**
     * 对关键的属性进行转换
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            // 对已加密的字段进行解密工作
            return DESUtil.getDecryptString(propertyValue);
        } else {
            return propertyValue;
        }
    }

    /**
     * 该属性是否已加密
     *
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        // 若等于需要加密的field，则进行加密
        for (String epn : encryptPropNames) {
            if (epn.equals(propertyName))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String username = new EncryptPropertyPlaceholderConfigurer().convertProperty("jdbc.username", "WnplV/ietfQ=");
        String passwd = new EncryptPropertyPlaceholderConfigurer().convertProperty("jdbc.password", "JLIV9dwvBwE=");
        System.out.println(username);
        System.out.println(passwd);
    }
    
}
