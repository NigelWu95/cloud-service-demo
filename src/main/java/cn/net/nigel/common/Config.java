package cn.net.nigel.common;

import java.io.*;
import java.util.Properties;

public class Config {

    private Properties properties;

    public Config(String resourceName) throws IOException {
        properties = new Properties();
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        properties.load(inStream);
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * 获取属性值，判断是否存在相应的 key，不存在或 value 为空则抛出异常
     * @param key 属性名
     * @return 属性值字符
     * @throws IOException 无法获取参数值或者参数值为空时抛出异常
     */
    public String getValue(String key) throws IOException {
        if ("".equals(properties.getProperty(key, ""))) {
            throw new IOException("not set \"" + key + "\" parameter value.");
        } else {
            return properties.getProperty(key);
        }
    }

    /**
     * 获取属性值，不抛出异常，使用 default 值进行返回
     * @param key 属性名
     * @param Default 默认返回值
     * @return 属性值字符
     */
    public String getValue(String key, String Default) {
        if ("".equals(properties.getProperty(key, ""))) return Default;
        return properties.getProperty(key, Default);
    }
}
