# Redis

## 1. 介绍

Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。    	它支持多种类型的数据结构，如    	    [字符串（strings）](http://www.redis.cn/topics/data-types-intro.html#strings)，    [散列（hashes）](http://www.redis.cn/topics/data-types-intro.html#hashes)，    [列表（lists）](http://www.redis.cn/topics/data-types-intro.html#lists)，    [集合（sets）](http://www.redis.cn/topics/data-types-intro.html#sets)，    [有序集合（sorted sets）](http://www.redis.cn/topics/data-types-intro.html#sorted-sets) 与范围查询，    [bitmaps](http://www.redis.cn/topics/data-types-intro.html#bitmaps)，    [hyperloglogs](http://www.redis.cn/topics/data-types-intro.html#hyperloglogs) 和     [地理空间（geospatial）](http://www.redis.cn/commands/geoadd.html) 索引半径查询。     Redis 内置了    [复制（replication）](http://www.redis.cn/topics/replication.html)，[LUA脚本（Lua scripting）](http://www.redis.cn/commands/eval.html)，    [LRU驱动事件（LRU eviction）](http://www.redis.cn/topics/lru-cache.html)，[事务（transactions）](http://www.redis.cn/topics/transactions.html)    和不同级别的    [磁盘持久化（persistence）](http://www.redis.cn/topics/persistence.html)，    并通过    [Redis哨兵（Sentinel）](http://www.redis.cn/topics/sentinel.html)和自动    [分区（Cluster）](http://www.redis.cn/topics/cluster-tutorial.html)提供高可用性（high availability）。

## 2. 安装与使用

## 3. 数据类型介绍

Redis并不是简单的key-value存储，实际上他是一个数据结构服务器，支持不同类型的值。也就是说，你不必仅仅把字符串当作键所指向的值。下列这些数据类型都可作为值类型：

- 二进制安全的字符串
- Lists: 按插入顺序排序的字符串元素的集合。他们基本上就是*链表（linked lists）*。
- Sets: 不重复且无序的字符串元素的集合。
- Sorted sets,类似Sets,但是每个字符串元素都关联到一个叫*score*浮动数值（floating number value）。里面的元素总是通过score进行着排序，所以不同的是，它是可以检索的一系列元素。（例如你可能会问：给我前面10个或者后面10个元素）。
- Hashes,由field和关联的value组成的map。field和value都是字符串的。这和Ruby、Python的hashes很像。
- Bit arrays (或者说 simply bitmaps): 通过特殊的命令，你可以将 String 值当作一系列 bits 处理：可以设置和清除单独的 bits，数出所有设为 1 的 bits 的数量，找到最前的被设为 1 或 0 的 bit，等等。
- HyperLogLogs: 这是被用于估计一个 set 中元素数量的概率性的数据结构。别害怕，它比看起来的样子要简单…参见本教程的 HyperLogLog 部分

### 3.1 key

Redis key值是二进制安全的，这意味着可以用任何二进制序列作为key值，从形如”foo”的简单字符串到一个JPEG文件的内容都可以。空字符串也是有效key值。

关于key的几条规则：

- 太长的键值不是个好主意，例如1024字节的键值就不是个好主意，不仅因为消耗内存，而且在数据中查找这类键值的计算成本很高。
- 太短的键值通常也不是好主意，如果你要用”u:1000:pwd”来代替”user:1000:password”，这没有什么问题，但后者更易阅读，并且由此增加的空间消耗相对于key object和value object本身来说很小。当然，没人阻止您一定要用更短的键值节省一丁点儿空间。
- 最好坚持一种模式。例如：”object-type:id:field”就是个不错的注意，像这样”user:1000:password”。我喜欢对多单词的字段名中加上一个点，就像这样：”comment:1234:reply.to”。

### 3.2 Value——string（字符串）

#### 3.2.1 string介绍

这是最简单Redis类型。如果你只用这种类型，Redis就像一个可以持久化的memcached服务器（注：memcache的数据仅保存在内存中，服务器重启后，数据将丢失）。

值可以是任何种类的字符串（包括二进制数据），例如你可以在一个键下保存一副jpeg图片。值的长度不能超过512 MB。

`SET` 命令有些有趣的操作，例如，当key存在时`SET`会失败，或相反的，当key不存在时它只会成功。

虽然字符串是Redis的基本值类型，但你仍然能通过它完成一些有趣的操作。例如：原子递增：

[INCR](http://www.redis.cn/commands/incr.html) 命令将字符串值解析成整型，将其加一，最后将结果保存为新的字符串值，类似的命令有[INCRBY](http://www.redis.cn/commands/incrby.html), [DECR](http://www.redis.cn/commands/decr.html) 和 [DECRBY](http://www.redis.cn/commands/decrby.html)。实际上他们在内部就是同一个命令，只是看上去有点儿不同。

[INCR](http://www.redis.cn/commands/incr.html)是原子操作意味着什么呢？就是说即使多个客户端对同一个key发出[INCR](http://www.redis.cn/commands/incr.html)命令，也决不会导致竞争的情况。例如如下情况永远不可能发生：『客户端1和客户端2同时读出“10”，他们俩都对其加到11，然后将新值设置为11』。最终的值一定是12，read-increment-set操作完成时，其他客户端不会在同一时间执行任何命令。

对字符串，另一个的令人感兴趣的操作是[GETSET](http://www.redis.cn/commands/getset.html)命令，行如其名：他为key设置新值并且返回原值。这有什么用处呢？例如：你的系统每当有新用户访问时就用[INCR](http://www.redis.cn/commands/incr.html)命令操作一个Redis key。你希望每小时对这个信息收集一次。你就可以[GETSET](http://www.redis.cn/commands/getset.html)这个key并给其赋值0并读取原值。

#### 3.2.2 string常用命令:

```xml
SET key value                   设置key=value
GET key                         获得键key对应的值
GETRANGE key start end          得到字符串的子字符串存放在一个键
GETSET key value                设置键的字符串值，并返回旧值
GETBIT key offset               返回存储在键位值的字符串值的偏移
MGET key1 [key2..]              得到所有的给定键的值
SETBIT key offset value         设置或清除该位在存储在键的字符串值偏移
SETEX key seconds value         键到期时设置值
SETNX key value                 设置键的值，只有当该键不存在
SETRANGE key offset value       覆盖字符串的一部分从指定键的偏移
STRLEN key                      得到存储在键的值的长度
MSET key value [key value...]   设置多个键和多个值
MSETNX key value [key value...] 设置多个键多个值，只有在当没有按键的存在时
PSETEX key milliseconds value   设置键的毫秒值和到期时间
INCR key                        增加键的整数值一次
INCRBY key increment            由给定的数量递增键的整数值
INCRBYFLOAT key increment       由给定的数量递增键的浮点值
DECR key                        递减键一次的整数值
DECRBY key decrement            由给定数目递减键的整数值
APPEND key value                追加值到一个键
DEL key                         如果存在删除键
DUMP key                        返回存储在指定键的值的序列化版本
EXISTS key                      此命令检查该键是否存在
EXPIRE key seconds              指定键的过期时间
EXPIREAT key timestamp          指定的键过期时间。在这里，时间是在Unix时间戳格式
PEXPIRE key milliseconds        设置键以毫秒为单位到期
PEXPIREAT key milliseconds-timestamp        设置键在Unix时间戳指定为毫秒到期
KEYS pattern                    查找与指定模式匹配的所有键
MOVE key db                     移动键到另一个数据库
PERSIST key                     移除过期的键
PTTL key                        以毫秒为单位获取剩余时间的到期键。
TTL key                         获取键到期的剩余时间。
RANDOMKEY                       从Redis返回随机键
RENAME key newkey               更改键的名称
RENAMENX key newkey             重命名键，如果新的键不存在
TYPE key                        返回存储在键的数据类型的值。
```

#### 3.2.3 string命令演示：

```shell
linux-redis-5.0.8:0>set google http://www.google # 用set 设置一个string类型的键值对
"OK"
linux-redis-5.0.8:0>get google # 用get 获取名字为 google 的值
"http://www.google"
linux-redis-5.0.8:0>append google .com # 用 append进行追加数据
"21"
linux-redis-5.0.8:0>get google
"http://www.google.com"
linux-redis-5.0.8:0>set visitors 0 
"OK"
linux-redis-5.0.8:0>get visitors
"0"
linux-redis-5.0.8:0>incr visitors # 对数据进行自增+1
"1"
linux-redis-5.0.8:0>get visitors
"1"
linux-redis-5.0.8:0>incr visitors
"2"
linux-redis-5.0.8:0>incrby visitors 100 # 对数据进行制定值的递增
"102"
linux-redis-5.0.8:0>get visitors 
"102"
linux-redis-5.0.8:0>type google  # 获取数据的类型
"string"
linux-redis-5.0.8:0>type visitors
"string"
linux-redis-5.0.8:0>ttl google  # 获取数据的过期时间，-1 永不过期
"-1"
linux-redis-5.0.8:0>rename google google-site  # 对 key 进行重命名
"OK"
linux-redis-5.0.8:0>keys * # 查看所有的key
 1)  "visitors"
 2)  "google-site"
linux-redis-5.0.8:0>get google
null
linux-redis-5.0.8:0>get google-site
"http://www.google.com"
linux-redis-5.0.8:0>
```





### 3.3 Value——list(列表)

#### 3.3.1 list 介绍

要说清楚列表数据类型，最好先讲一点儿理论背景，在信息技术界List这个词常常被使用不当。例如”Python Lists”就名不副实（名为Linked Lists），但他们实际上是数组（同样的数据类型在Ruby中叫数组）

一般意义上讲，列表就是有序元素的序列：10,20,1,2,3就是一个列表。但用数组实现的List和用Linked List实现的List，在属性方面大不相同。

**Redis lists基于Linked  Lists实现。这意味着即使在一个list中有数百万个元素，在头部或尾部添加一个元素的操作，其时间复杂度也是常数级别的。用LPUSH  命令在十个元素的list头部添加新元素，和在千万元素list头部添加新元素的速度相同。**

那么，坏消息是什么？在数组实现的list中利用索引访问元素的速度极快，而同样的操作在linked list实现的list上没有那么快。

Redis Lists用linked list实现的原因是：对于数据库系统来说，至关重要的特性是：能非常快的在很大的列表上添加元素。另一个重要因素是，正如你将要看到的：Redis lists能在常数 时间取得常数长度。

如果快速访问集合元素很重要，建议使用可排序集合(sorted sets)。可排序集合我们会随后介绍。

#### 3.3.2 list 常用命令

```xml
BLPOP key1 [key2 ] timeout 取出并获取列表中的第一个元素，或阻塞，直到有可用
BRPOP key1 [key2 ] timeout 取出并获取列表中的最后一个元素，或阻塞，直到有可用
BRPOPLPUSH source destination timeout 从列表中弹出一个值，它推到另一个列表并返回它;或阻塞，直到有可用
LINDEX key index 从一个列表其索引获取对应的元素
LINSERT key BEFORE|AFTER pivot value 在列表中的其他元素之后或之前插入一个元素
LLEN key 获取列表的长度
LPOP key 获取并取出列表中的第一个元素
LPUSH key value1 [value2] 在前面加上一个或多个值的列表
LPUSHX key value 在前面加上一个值列表，仅当列表中存在
LRANGE key start stop 从一个列表获取各种元素
LREM key count value 从列表中删除元素
LSET key index value 在列表中的索引设置一个元素的值
LTRIM key start stop 修剪列表到指定的范围内
RPOP key 取出并获取列表中的最后一个元素
RPOPLPUSH source destination 删除最后一个元素的列表，将其附加到另一个列表并返回它
RPUSH key value1 [value2] 添加一个或多个值到列表
RPUSHX key value 添加一个值列表，仅当列表中存在
```

#### 3.3.3 list命令演示

```shell
linux-redis-5.0.8:0>lpush list1 redis # 用lpush 从左边往list里插入一条数据，返回list的当前长度
"1"
linux-redis-5.0.8:0>lpush list1 hello
"2"
linux-redis-5.0.8:0>lpush list1 world
"3"
linux-redis-5.0.8:0>llen list1 # llen 用来显示list的长度
"3"
linux-redis-5.0.8:0>type list1 # 获取数据的类型
"list"
linux-redis-5.0.8:0>lrange list1 0 4  # 用了lrange 查看指定范围的数据 
 1)  "world"
 2)  "hello"
 3)  "redis"
linux-redis-5.0.8:0>lrange list1 0 -1
 1)  "world"
 2)  "hello"
 3)  "redis"
linux-redis-5.0.8:0>rpush list1 A B B  # 用 rpush 插入一条数据
"6"
linux-redis-5.0.8:0>lrange list1 0 -1
 1)  "world"
 2)  "hello"
 3)  "redis"
 4)  "A"
 5)  "B"
 6)  "B"
linux-redis-5.0.8:0>rpush list1 C # 从右边插入一条数据
"7"
linux-redis-5.0.8:0>lpop list1  # 从左边弹出一条数据
"world"
linux-redis-5.0.8:0>rpop list1  # 从右边弹出一条数据
"C"
linux-redis-5.0.8:0>lrange list1 0 -1
 1)  "hello"
 2)  "redis"
 3)  "A"
 4)  "B"
 5)  "B"
```

### 3.4 Value——hash(字典,哈希表)

#### 3.4.1 hash 介绍

Redis hash 看起来就像一个 “hash” 的样子，由键值对组成,

Hash 便于表示 *objects*，实际上，你可以放入一个 hash 的域数量实际上没有限制（除了可用内存以外）。所以，你可以在你的应用中以不同的方式使用 hash。

`HMSET` 指令设置 hash 中的多个域，而 `HGET` 取回单个域。`HMGET` 和 `HGET` 类似，但返回一系列值：

#### 3.4.2 hash 常用命令

```xml
HDEL key field[field...] 删除对象的一个或几个属性域，不存在的属性将被忽略
HEXISTS key field 查看对象是否存在该属性域
HGET key field 获取对象中该field属性域的值
HGETALL key 获取对象的所有属性域和值
HINCRBY key field value 将该对象中指定域的值增加给定的value，原子自增操作，只能是integer的属性值可以使用
HINCRBYFLOAT key field increment 将该对象中指定域的值增加给定的浮点数
HKEYS key 获取对象的所有属性字段
HVALS key 获取对象的所有属性值
HLEN key 获取对象的所有属性字段的总数
HMGET key field[field...] 获取对象的一个或多个指定字段的值
HSET key field value 设置对象指定字段的值
HMSET key field value [field value ...] 同时设置对象中一个或多个字段的值
HSETNX key field value 只在对象不存在指定的字段时才设置字段的值
HSTRLEN key field 返回对象指定field的value的字符串长度，如果该对象或者field不存在，返回0.
HSCAN key cursor [MATCH pattern] [COUNT count] 类似SCAN命令
 
```

#### 3.4.3 hash命令演示

```shell
linux-redis-5.0.8:0>hset person name jack # hset 存一个值
"1"
linux-redis-5.0.8:0>hset person age 20
"1"
linux-redis-5.0.8:0>hset person sex famele
"1"
linux-redis-5.0.8:0>type person
"hash"
linux-redis-5.0.8:0>hget person nage
null
linux-redis-5.0.8:0>hget person name # hget 取出hash表中的键值
"jack"
linux-redis-5.0.8:0>hget person age 
"20"
linux-redis-5.0.8:0>hgetall person # hgetall 取出hash表中所有的键值对
 1)  "name"
 2)  "jack"
 3)  "age"
 4)  "20"
 5)  "sex"
 6)  "famele"
linux-redis-5.0.8:0>hkeys person # hkeys 取出所有的key
 1)  "name"
 2)  "age"
 3)  "sex"
linux-redis-5.0.8:0>hvals person # 取出所有的value
 1)  "jack"
 2)  "20"
 3)  "famele"
linux-redis-5.0.8:0>
```

### 3.5 Vlaue——Set(集合)

#### 3.5.1 set介绍

Redis Set 是 String 的无序排列。`SADD` 指令把新的元素添加到 set 中。对 set 也可做一些其他的操作，比如测试一个给定的元素是否存在，对不同 set 取交集，并集或差，等等。

#### 3.5.2 set常用命令

```xml
SADD key member [member ...] 添加一个或者多个元素到集合(set)里
SCARD key 获取集合里面的元素数量
SDIFF key [key ...] 获得队列不存在的元素
SDIFFSTORE destination key [key ...] 获得队列不存在的元素，并存储在一个关键的结果集
SINTER key [key ...] 获得两个集合的交集
SINTERSTORE destination key [key ...] 获得两个集合的交集，并存储在一个集合中
SISMEMBER key member 确定一个给定的值是一个集合的成员
SMEMBERS key 获取集合里面的所有key
SMOVE source destination member 移动集合里面的一个key到另一个集合
SPOP key [count] 获取并删除一个集合里面的元素
SRANDMEMBER key [count] 从集合里面随机获取一个元素
SREM key member [member ...] 从集合里删除一个或多个元素，不存在的元素会被忽略
SUNION key [key ...] 添加多个set元素
SUNIONSTORE destination key [key ...] 合并set元素，并将结果存入新的set里面
SSCAN key cursor [MATCH pattern] [COUNT count] 迭代set里面的元素
```

#### 3.5.3 set命令演示

```shell
linux-redis-5.0.8:0>sadd myset hello # 往 myset 中添加一个元素
"1"
linux-redis-5.0.8:0>sadd myset world myset # sadd 添加一个或者多个元素
"2"
linux-redis-5.0.8:0>smembers myset # 查看set集合中的全部成员
 1)  "myset"
 2)  "hello"
 3)  "world"
linux-redis-5.0.8:0>sadd myset test 
"1"
linux-redis-5.0.8:0>smembers myset
 1)  "test"
 2)  "myset"
 3)  "hello"
 4)  "world"
linux-redis-5.0.8:0>sismember myset one  # 判断元素是否在set集合中 不存在是0 ，存在是1
"0"
linux-redis-5.0.8:0>sismember myset hello
"1"
linux-redis-5.0.8:0>type myset
"set"
```

### 3.6 Value——Sorted Set(有序集合)

#### 3.6.1 sorted set 介绍



#### 3.6.2 sorted set 常用命令

```xml
ZADD key score1 member1 [score2 member2] 添加一个或多个成员到有序集合，或者如果它已经存在更新其分数
ZCARD key 得到的有序集合成员的数量
ZCOUNT key min max 计算一个有序集合成员与给定值范围内的分数
ZINCRBY key increment member 在有序集合增加成员的分数
ZINTERSTORE destination numkeys key [key ...] 多重交叉排序集合，并存储生成一个新的键有序集合。
ZLEXCOUNT key min max 计算一个给定的字典范围之间的有序集合成员的数量
ZRANGE key start stop [WITHSCORES] 由索引返回一个成员范围的有序集合（从低到高）
ZRANGEBYLEX key min max [LIMIT offset count]返回一个成员范围的有序集合（由字典范围）
ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT] 返回有序集key中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员，有序集成员按 score 值递增(从小到大)次序排列
ZRANK key member 确定成员的索引中有序集合
ZREM key member [member ...] 从有序集合中删除一个或多个成员，不存在的成员将被忽略
ZREMRANGEBYLEX key min max 删除所有成员在给定的字典范围之间的有序集合
ZREMRANGEBYRANK key start stop 在给定的索引之内删除所有成员的有序集合
ZREMRANGEBYSCORE key min max 在给定的分数之内删除所有成员的有序集合
ZREVRANGE key start stop [WITHSCORES] 返回一个成员范围的有序集合，通过索引，以分数排序，从高分到低分
ZREVRANGEBYSCORE key max min [WITHSCORES] 返回一个成员范围的有序集合，以socre排序从高到低
ZREVRANK key member 确定一个有序集合成员的索引，以分数排序，从高分到低分
ZSCORE key member 获取给定成员相关联的分数在一个有序集合
ZUNIONSTORE destination numkeys key [key ...] 添加多个集排序，所得排序集合存储在一个新的键
ZSCAN key cursor [MATCH pattern] [COUNT count] 增量迭代排序元素集和相关的分数
```

#### 3.6.3 sorted set 命令演示

```shell
linux-redis-5.0.8:0>zadd dbs 100 redis
"1"
linux-redis-5.0.8:0>zadd dbs 98 memcached
"1"
linux-redis-5.0.8:0>zadd dbs 97 mongodb
"1"
linux-redis-5.0.8:0>zcard dbs
"3"
linux-redis-5.0.8:0>zcount dbs 0 -1
"0"
linux-redis-5.0.8:0>zcount dbs 98 97
"0"
linux-redis-5.0.8:0>zcount dbs 91 100
"3"
linux-redis-5.0.8:0>zrange dbs 0 100
 1)  "mongodb"
 2)  "memcached"
 3)  "redis"
```

## 4. Jedis-java操作redis

### 4.1 导入坐标依赖

```xml
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
        <version>2.9.0</version>
    </dependency>
```

### 4.2Jedis CRUD

```java
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
```

## 5. Jedis 连接池

首先Redis也是一种数据库，它基于C/S模式，因此如果需要使用必须建立连接，稍微熟悉网络的人应该都清楚地知道为什么需要建立连接，C/S模式本身就是一种远程通信的交互模式，因此Redis服务器可以单独作为一个数据库服务器来独立存在。假设Redis服务器与客户端分处在异地，虽然基于内存的Redis数据库有着超高的性能，但是底层的网络通信却占用了一次数据请求的大量时间，因为每次数据交互都需要先建立连接，假设一次数据交互总共用时30ms，超高性能的Redis数据库处理数据所花的时间可能不到1ms，也即是说前期的连接占用了29ms，连接池则可以实现在客户端建立多个链接并且不释放，当需要使用连接的时候通过一定的算法获取已经建立的连接，使用完了以后则还给连接池，这就免去了数据库连接所占用的时间。

总结：Redis是单线程的，多个链接过去还是单线程处理，Redis的性能瓶颈其实就是网络通信，连接池多个连接发送过去的请求可以大大加快网络通信的效率，再加上Redis使用Linux底层多路I/O复用模型，才是Redis高吞吐量的精髓所在

### 5.1 redis.properties

```properties
#ip地址
redis.hostName=192.168.1.101
#端口号
redis.port=6379
#如果有密码
#redis.password=
#客户端超时时间单位是毫秒 默认是2000
redis.timeout=2000
#最大空闲数
redis.maxIdle=10
#连接池的最大数据库连接数。设为0表示无限制,如果是jedis 2.4以后用redis.maxTotal
redis.maxActive=10
#控制一个pool可分配多少个jedis实例,用来替换上面的redis.maxActive,如果是jedis 2.4以后用该属性
redis.maxTotal=10
#最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
redis.maxWaitMillis=1000
#连接的最小空闲时间 默认1800000毫秒(30分钟)
redis.minEvictableIdleTimeMillis=300000
#每次释放连接的最大数目,默认3
redis.numTestsPerEvictionRun=1024
#逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
redis.timeBetweenEvictionRunsMillis=30000
#是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
redis.testOnBorrow=false
#在空闲时检查有效性, 默认false
redis.testWhileIdle=false
```

### 5.2 PropertiesUtils

```java
package cn.yhs.learn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.utils.PropertiesUtils
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 20:12
 * @Description: 读取properties文件中
 **/
public class PropertiesUtils {

    private static final Properties properties = new Properties();

    static {
        // 通过类加载器来获取配置文件并创建流对象
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过key得到value
     *
     * @param key 配置文件中的key
     * @return
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(get("redis.hostName"));

    }
}

```

### 5.3 RedisPoolUtils

```java
package cn.yhs.learn.redis;

import cn.yhs.learn.utils.PropertiesUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.redis.RedisPool
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 20:26
 * @Description: todo
 **/
public class RedisPoolUtils {
    private static JedisPoolConfig config;
    private static JedisPool jedisPool;
    private static Jedis jedis;

    static {
        config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(PropertiesUtils.get("redis.maxTotal")));
        config.setMaxIdle(Integer.parseInt(PropertiesUtils.get("redis.maxIdle")));
        config.setMaxWaitMillis(Integer.parseInt(PropertiesUtils.get("redis.maxWaitMillis")));
        jedisPool = new JedisPool(config, PropertiesUtils.get("redis.hostName"), Integer.parseInt(PropertiesUtils.get("redis.port")));
    }

    /**
     * 创建一个Redis客户端对象
     *
     * @return
     */
    public static Jedis getClient() {
        jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 释放资源
     */
    public static void release() {
        if (jedis != null) {
            jedis.close();
        }
    }
}

```

## 6. Spring-整合Redis

### 6.1 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.yhs.learn</groupId>
    <artifactId>ssm-demo1</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>ssm-demo1-web</module>
        <module>ssm-demo2</module>
        <module>spring-redis</module>
        <module>spring-redis2</module>
    </modules>
    <!--将SSM整合的进行拆分。这是一个父工程-->
    <properties>
        <!--spring相关的版本的版本-->
        <spring-context.version>5.0.6.RELEASE</spring-context.version>
        <!--spring-security版本-->
        <spring-security.version>5.0.5.RELEASE</spring-security.version>
        <!--spring整合mybatis的版本-->
        <mybatis-spring.version>1.3.2</mybatis-spring.version>
        <!--spring整合junit的版本-->
        <spring-junit.version>5.0.6.RELEASE</spring-junit.version>
        <!--servlet-->
        <servlet.version>2.5</servlet.version>
        <servlet-jsp.version>2.0</servlet-jsp.version>
        <!--数据层版本-->
        <mybatis.version>3.4.5</mybatis.version>
        <pagehelper.version>5.1.10</pagehelper.version>
        <druid.version>1.1.12</druid.version>
        <mysql.version>5.1.47</mysql.version>
        <!--工具层版本-->
        <jackson.version>2.9.8</jackson.version>
        <lombok.version>1.18.8</lombok.version>
        <slf4j.version>1.7.25</slf4j.version>
        <log4j.version>1.2.17</log4j.version>
        <junit.version>4.12</junit.version>
    </properties>

    <dependencies>
        <!--=============================== Web层 ================================-->
        <!--SpringSecurity版本-->
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-web</artifactId>
            <version>${spring-security.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-config</artifactId>
            <version>${spring-security.version}</version>
        </dependency>
        <!--权限控制——页面级别的控制-->
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-taglibs</artifactId>
            <version>${spring-security.version}</version>
        </dependency>
        <!--JSR250-->
        <!--方法级别的权限控制1.导入依赖-->
        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>jsr250-api</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring-context.version}</version>
        </dependency>
        <!--导入servlet-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>${servlet.version}</version>
            <scope>provided</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet.jsp.jstl/jstl-api -->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
        </dependency>


        <!--=============================== SERVICE层 ================================-->
        <!--spring,自动会导入底层依赖的spring包-->
        <!--        <dependency>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-context</artifactId>
                    <version>${spring-context.version}</version>
                </dependency>-->
        <!--spring 面向切面编程必须加的jar-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring-context.version}</version>
        </dependency>
        <!--spring 事务-->
        <!--       <dependency>
                   <groupId>org.springframework</groupId>
                   <artifactId>spring-tx</artifactId>
                   <version>${spring-context.version}</version>
               </dependency>-->
        <!--spring 事务管理数据源-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring-context.version}</version>
        </dependency>

        <!--=============================== spring整合mybatis的jar ================================-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${mybatis-spring.version}</version>
        </dependency>
        <!--=============================== spring整合junit的jar ================================-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring-junit.version}</version>
            <!--  <scope>test</scope>-->
        </dependency>
        <!--=============================== spring整合redis的jar ================================-->
<!--        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-redis</artifactId>
            <version>2.0.7.RELEASE</version>
        </dependency>-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.6.0</version>
        </dependency>

        <!--=============================== DAO层 ================================-->
        <!--mybatis版本-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
        <!-- 分页插件版本 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>${pagehelper.version}</version>
        </dependency>

        <!--数据库连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>
        <!--mysql版本-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>

        <!--=============================== 工具层 ================================-->
        <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>


    </dependencies>
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.2</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <encoding>UTF-8</encoding>
                        <showWarnings>true</showWarnings>
                    </configuration>
                </plugin>

            </plugins>
        </pluginManagement>
    </build>

</project>
```

### 6.2 applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--Spring的配置文件-->
    <!--1. 配置基本包的扫描，排除@Controller注解的层-->
    <context:component-scan base-package="cn.yhs.learn">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--2. 配置Druid数据源-->
    <context:property-placeholder location="classpath:*.properties"/>
    <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource" init-method="init" destroy-method="close">
        <property name="driverClassName" value="${druid.jdbc.driverClassName}"/>
        <property name="url" value="${druid.jdbc.url}"/>
        <property name="username" value="${druid.jdbc.username}"/>
        <property name="password" value="${druid.jdbc.password}"/>
        <property name="initialSize" value="${druid.jdbc.initialSize}"/>
        <property name="maxActive" value="${druid.jdbc.maxActive}"/>
        <property name="maxWait" value="${druid.jdbc.maxWait}"/>
    </bean>
    <!--2.1 配置Redis-->
    <!-- redis连接池配置-->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <!--最大空闲数-->
        <property name="maxIdle" value="${redis.maxIdle}"/>
        <!--连接池的最大数据库连接数  -->
        <property name="maxTotal" value="${redis.maxTotal}"/>
        <!--最大建立连接等待时间-->
        <property name="maxWaitMillis" value="${redis.maxWaitMillis}"/>
        <!--逐出连接的最小空闲时间 默认1800000毫秒(30分钟)-->
        <property name="minEvictableIdleTimeMillis" value="${redis.minEvictableIdleTimeMillis}"/>
        <!--每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3-->
        <property name="numTestsPerEvictionRun" value="${redis.numTestsPerEvictionRun}"/>
        <!--逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1-->
        <property name="timeBetweenEvictionRunsMillis" value="${redis.timeBetweenEvictionRunsMillis}"/>
        <!--是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个-->
        <property name="testOnBorrow" value="${redis.testOnBorrow}"/>
        <!--在空闲时检查有效性, 默认false  -->
        <property name="testWhileIdle" value="${redis.testWhileIdle}"/>
    </bean>
    <bean class="redis.clients.jedis.JedisPool" id="jedisPool">
        <constructor-arg name="host" value="${redis.hostName}"/>
        <constructor-arg name="port" value="${redis.port}"/>
        <constructor-arg name="poolConfig" ref="jedisPoolConfig"/>
    </bean>

    <!--================service层 开始==================-->
    <!--3. 配置事务管理器-->
    <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--4. 配置声明式事务，service事务异常回滚-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="find*" read-only="true"/>
            <tx:method name="*" read-only="false"/>
        </tx:attributes>
    </tx:advice>
    <aop:config>
        <aop:pointcut id="pt1" expression="execution(* cn.yhs.learn.service.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pt1"/>
    </aop:config>
    <!--================service层 结束==================-->
    <!--================dao层 开始==================-->

    <!--5. 配置sqlSessionFactoryBean-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sessionFactoryBean">
        <!--5.1 配置数据源-->
        <property name="dataSource" ref="dataSource"/>
        <!--5.2 配置分页插件-->
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor" id="pageInterceptor">
                    <property name="properties">
                        <value>
                            helperDialect=mysql
                            reasonable=true
                        </value>
                    </property>
                </bean>
            </array>
        </property>
    </bean>

    <!--6. 配置mapper扫描-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="mapperScannerConfigurer">
        <property name="basePackage" value="cn.yhs.learn.dao"/>
    </bean>

    <!--================dao层 结束==================-->


</beans>
```

### 6.3 spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--Spring-MVC配置文件-->
    <!--1. 配置基本包扫描，只扫描带有@Controller -->
    <context:component-scan base-package="cn.yhs.learn">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--2.配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="viewResolver">
        <property name="prefix" value="/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--3. 开启注解支持-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter" id="stringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
    <!--4. 过滤掉静态资源-->
    <mvc:default-servlet-handler></mvc:default-servlet-handler>
</beans>
```

### 6.4 RedisService

```java
package cn.yhs.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.RedisService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/23 10:24
 * @Description: todo
 **/
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }



}
```

### 6.5 Controller

```java
package cn.yhs.learn.controller;

import cn.yhs.learn.domian.UserInfo;
import cn.yhs.learn.service.Userservice;
import cn.yhs.learn.service.impl.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.UserController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/23 10:31
 * @Description: todo
 **/
@RequestMapping("/user")
@Controller
@Slf4j
public class UserController {

    @Autowired
    private Userservice userservice;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll() {
        Jedis jedis = redisService.getJedis();
        // 1. 从redis中获取数据
        String userList = jedis.get("userList");
        // 2. 判断redis中是否有数据
        try {
            if (null != userList && userList.length() > 0) {
                // 3. redis中有数据就从redis中返回
                log.info("redis中有数据{}，从redis获取", userList);
                return userList;
            } else {
                // 4. redis中没有数据，从数据库种查询，将查询的结果存到redis中再进行返回
                log.info("redis中没有数据，从数据库中进行查询");
                List<UserInfo> userInfoList = userservice.findAll();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String userDbList = objectMapper.writeValueAsString(userInfoList);
                    jedis.set("userList", userDbList);
                    return userDbList;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } finally {
            jedis.close();
        }

    }

    @RequestMapping("/findAll2")
    @ResponseBody
    public Object findAll2() {
        return userservice.findAll();
    }
}

```

### 6.6 index.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-3.2.1.js"></script>
</head>
<body>
<h1>我是主页</h1>
<select id="s1" name="下拉框">
    <option>请选择姓名</option>
</select>
</body>
<script>
    $(function () {
        var res = $("#s1");
        $.ajax({
            type: "POST",
            url: "user/findAll",
            dataType: "json",
            success: function (data) {
                data.forEach(function (value) {
                    var name = value.username;
                    var id = value.id;
                    var option = "<option id='" + id + "'>" + name + "</option>";
                    res.append(option);
                });
            }
        });
    })
</script>
</html>
```

### 6.7 问题总结

1. 只是一个简单的配置整合
2. 凡是对数据进行的增加，修改，删除，都要对redis中的数据进行更新
3. 现在这种方式只是存储json字符串的形式，应该使用对象序列化进行存储，后续会改进（Spring-data提供的有）





