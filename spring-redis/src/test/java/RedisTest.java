import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: ssm-demo1
 * @Name: PACKAGE_NAME.RedisTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 15:10
 * @Description: todo
 **/
public class RedisTest {

    // 入门案例
    @Test
    public void test() {
        // 创建客户端对象，输入redis的地址和端口号
        Jedis jedis = new Jedis("192.168.1.101", 6379);
        Client jedisClient = jedis.getClient();
        System.out.println("jedisClient = " + jedisClient);
        // string 类型的存储一个数据
        jedis.set("jedis", "hello jedis");
        //获取string类型的数据
        String s = jedis.get("jedis");
        System.out.println("s = " + s);
        // 关闭连接
        jedis.close();
    }

    private Jedis jedis = null;

    @Before
    public void init() {
        jedis = new Jedis("192.168.1.101", 6379);
    }


    @After
    public void close(){
        if(jedis!=null){
            jedis.close();
        }
    }

    /**
     * string类型的CRUD
     */
    @Test
    public void testString() {
        // 添加数据
        jedis.set("name", "喻汉生");
        // 查询数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
        // 修改数据-在原始数据的基础上进行添加
        jedis.append("name", "yhs");
        name = jedis.get("name");
        System.out.println("name = " + name);
        // 修改数据-对原始数据进行覆盖
        jedis.set("name", "张秀云");
        name=jedis.get("name");
        System.out.println("name = " + name);
        // 删除数据
        jedis.del("name");
        name = jedis.get("name");
        System.out.println("name = " + name); // null
    }

    /**
     *  list类型的数据的CRUD
     */
    @Test
    public void testList() {
        // 清空list集合中的数据
        Long del = jedis.del("list");
        System.out.println("del = " + del);
        // 从左边开始插入数据
        jedis.lpush("list","1","2","3","4");
        // 查看list中的数据
        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println("list = " + list);
        // 从右边开始插入数据
        jedis.rpushx("list", "a","b","c","d");
        list = jedis.lrange("list", 0, -1);
        System.out.println("list = " + list);
        // 获取list的长度
        Long listLength= jedis.llen("list");
        System.out.println("listLength = " + listLength);
        // 从左边删除一条数据
        String lvalue = jedis.lpop("list");
        System.out.println("lvalue = " + lvalue);
        list = jedis.lrange("list", 0, -1);
        System.out.println("list = " + list);
        // 从右边删除一条数据
        String rpop = jedis.rpop("list");
        System.out.println("rpop = " + rpop);
        list= jedis.lrange("list",0,-1);
        System.out.println("list = "+list);
    }

    /**
     * hash类型的CRUD
     */
    @Test
    public void testHash() {
        // 插入单挑数据
        jedis.hset("hash", "name", "喻汉生");
        // 获取相对应的key的值
        String hget = jedis.hget("hash", "name");
        System.out.println("hget = " + hget);
        Map<String,String> hashMap  = new HashMap<>();
        hashMap.put("age", "18");
        hashMap.put("sex", "female");
        // 批量插入,通过插入一个map
        jedis.hmset("hash", hashMap);
        // 批量获取key对应的value
        List<String> stringList = jedis.hmget("hash", "name", "age", "sex");
        System.out.println("stringList = " + stringList);
        // 获取所有数据，键值对数据
        Map<String, String> hash = jedis.hgetAll("hash");
        Set<String> strings = hash.keySet();
        for (String string : strings) {
            System.out.println("key = " + string +" value = "+hash.get(string));
        }
        // 获取所有的key
        Set<String> set = jedis.hkeys("hash");
        System.out.println("set = " + set);
        // 获取所有的value
        List<String> list = jedis.hvals("hash");
        System.out.println("list = " + list);
        // 删除数据
        jedis.hdel("hash", "name");
        set = jedis.hkeys("hash");
        System.out.println("set = " + set);
        // 判断数据是否存在
        Boolean exists = jedis.exists("hash");
        System.out.println("exists = " + exists);
        // 获取hash的长度
        Long hlen = jedis.hlen("hash");
        System.out.println("hlen = " + hlen);
    }

    /**
     * set 类型的crud
     */
    @Test
    public void testSet() {
        // 添加数据，set 中是存放不能重复的数据
        jedis.sadd("set", "s1","s2","s3","s1");
        // 获取set中的成员，即数据
        Set<String> set = jedis.smembers("set");
        System.out.println("set = " + set);
        // 删除数据
        jedis.srem("set", "s1");
        set = jedis.smembers("set");
        System.out.println("set = " + set);
        // set 的长度
        Long set1 = jedis.scard("set");
        System.out.println("set1 = " + set1);
    }

    /**
     *  常用的方法
     */
    @Test
    public void testCommon() {
        // * 返回当前库中所有的key
        Set<String> keys = jedis.keys("*");
        System.out.println("keys = " + keys);
        // rename 给指定的key重命名
        // jedis.rename("hash", "hashMap");
        keys = jedis.keys("*");
        System.out.println("keys = " + keys);
        // 删除key
        jedis.del("set");
        keys = jedis.keys("*");
        System.out.println("keys = " + keys);
        // 对指定的key设置指定的值，并设置过期时间
        jedis.setex("list", 10, "3");
    }

















}
