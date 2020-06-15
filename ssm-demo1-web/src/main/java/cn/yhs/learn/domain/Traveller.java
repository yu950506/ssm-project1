package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.Traveller
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 23:41
 * @Description: 旅客信息表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Traveller {
    private Integer id;
    private String name;
    private String sex;
    private String phoneNum;

    private Integer credentialsType;
    private String credentialsTypeStr;

    private String credentialsNum;

    private Integer travellerType;
    private String travellerTypeStr;

    // 证件类型的字符串表现形式
    public String getCredentialsTypeStr() {
        if (this.getCredentialsType() == 0)
            return "身份证";
        else if (this.getCredentialsType() == 1)
            return "护照";
        else if (this.getCredentialsType() == 2)
            return "军官证";
        else
            return "其他";
    }


    public String getTravellerTypeStr() {
        if (this.getTravellerType() == 0) {
            return "成人";
        } else {
            return "儿童";
        }
    }

}
