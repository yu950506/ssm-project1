package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.MeanList
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 19:42
 * @Description: todo
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuList {
    private List<OneMenu> list;
}
