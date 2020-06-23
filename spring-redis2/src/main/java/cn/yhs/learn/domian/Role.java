package cn.yhs.learn.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.Role
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 20:22
 * @Description: todo
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
}
