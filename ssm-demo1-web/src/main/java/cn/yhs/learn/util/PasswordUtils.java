package cn.yhs.learn.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.util.PasswordUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 20:56
 * @Description: 用于密码加密的
 **/
public class PasswordUtils {
    // SpringSecurity 提供的密码加密
    private static BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();

    public static String encoding(String password) {
        return bcp.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(encoding("user"));
    }
}
