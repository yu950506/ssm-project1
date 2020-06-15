package cn.yhs.learn.util;

import java.util.UUID;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.util.UUIDUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 20:17
 * @Description: todo
 **/
public class UUIDUtils {
    /**
     * 生成随机的UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

}
