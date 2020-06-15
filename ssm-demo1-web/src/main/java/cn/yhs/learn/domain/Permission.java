package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.Permission
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 22:25
 * @Description: 权限信息表
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Permission {
    private Integer id;
    private String permissionName;
    private String url;
}
