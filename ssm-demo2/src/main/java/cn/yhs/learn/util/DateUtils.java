package cn.yhs.learn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.util.DataUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 16:18
 * @Description: todo
 **/
public class DateUtils {
    /**
     * 将传入的日期格式化成指定的格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String parseString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = null;
            try {
                simpleDateFormat = new SimpleDateFormat(format);
            } catch (Exception e) {
                throw new RuntimeException("日期格式有误");
            }
            String s = simpleDateFormat.format(date);
            return s;
        } else {
            throw new RuntimeException("传入的日期为空");
        }

    }

    /**
     *  默认格式：  String format = "yyyy-MM-dd HH:mm:ss";
     * @param date
     * @return
     */
    public static String parseString(Date date) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return parseString(date, format);
    }
}
