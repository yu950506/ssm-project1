package cn.yhs.learn.util;

import cn.yhs.learn.domain.MenuList;
import cn.yhs.learn.domain.OneMenu;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.alibaba.druid.sql.ast.SQLPartitionValue.Operator.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.util.JSONUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 19:33
 * @Description: todo
 **/
public class JSONUtils {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test1() throws IOException {


        JsonNode jsonNode = objectMapper.readTree(new File("E:\\bilili学习java用到的jar包\\qadmin-1.0\\qadmin-1.0\\data\\menu.json"));
        //  System.out.println(jsonNode);
        for (JsonNode node : jsonNode) {
            System.out.println(node.toString());
        }


       /* MenuList menuList = objectMapper.readValue(new File("E:\\bilili学习java用到的jar包\\qadmin-1.0\\qadmin-1.0\\data\\menu.json"), MenuList.class);
        for (OneMenu oneMenu : menuList.getList()) {
            System.out.println(oneMenu);
        }*/

    }

}
