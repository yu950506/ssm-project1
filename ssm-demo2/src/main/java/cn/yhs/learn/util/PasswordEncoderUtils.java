package cn.yhs.learn.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.util.PasswordEncoderUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 20:18
 * @Description: todo
 **/
public class PasswordEncoderUtils {
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encoder(String password) {
        if (password == null) {
            throw new RuntimeException("password is null");
        } else {
            return passwordEncoder.encode(password);
        }

    }

    public static void main(String[] args) {
        System.out.println(encoder("yhs"));
    }
}
