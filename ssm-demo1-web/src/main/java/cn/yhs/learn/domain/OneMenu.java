package cn.yhs.learn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.OneMeau
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 18:54
 * @Description: 一级菜单
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneMenu {
    @JsonIgnore // todo 6,该属性可以被JackSon忽略
    private Integer id;
    private String name;
    private String icon;
    private String url;
    private Boolean hidden; // todo 3 mybatis自动会将 0 1 做转换 0->false 1->true

    private List<TwoMenu> list; // 1:N的提现

}
