package cn.yhs.learn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.utils.PropertiesUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 20:12
 * @Description: 读取properties文件中
 **/
public class PropertiesUtils {

    private static final Properties properties = new Properties();

    static {
        // 通过类加载器来获取配置文件并创建流对象
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过key得到value
     *
     * @param key 配置文件中的key
     * @return
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(get("redis.hostName"));

    }
}
