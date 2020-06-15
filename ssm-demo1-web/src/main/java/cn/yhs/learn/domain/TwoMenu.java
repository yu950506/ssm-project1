package cn.yhs.learn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.TwoMean
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 18:57
 * @Description: 二级菜单
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwoMenu {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer oneId; // 演示和数据库中的id字段名称不对应怎么处理
    private String name;
    private String url;
}
