package cn.yhs.learn.domian;

import cn.yhs.learn.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domian.SysLog
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 16:43
 * @Description: 系统日志类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {
    private Integer id;
    private String username;
    private String ip;
    private String url;
    private String clazz;
    private String method;
    private Date visitTime;
    private String visitTimeStr;
    private Long executionTime;

    public String getVisitTimeStr() {
        return DateUtils.parseString(this.getVisitTime());
    }


}
