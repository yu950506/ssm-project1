/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : yhs

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 14/06/2020 11:11:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键id 自增',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员姓名',
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `phone_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '张三', '小三', '18888888888', 'zs@163.com');
INSERT INTO `member` VALUES (2, '李四', '小四', '18888888888', 'lisi@163.com');
INSERT INTO `member` VALUES (3, '王五', '小武', '18888888888', 'ww@163.com');
INSERT INTO `member` VALUES (4, '赵六', '小三', '18888888888', 'zs@163.com');
INSERT INTO `member` VALUES (5, '力气', '小四', '18888888888', 'lisi@163.com');
INSERT INTO `member` VALUES (6, '王八', '小武', '18888888888', 'ww@163.com');

-- ----------------------------
-- Table structure for order_traveller
-- ----------------------------
DROP TABLE IF EXISTS `order_traveller`;
CREATE TABLE `order_traveller`  (
  `order_id` int(32) NOT NULL COMMENT '订单主键id',
  `traveller_id` int(32) NOT NULL COMMENT '旅客主键id',
  PRIMARY KEY (`order_id`, `traveller_id`) USING BTREE,
  INDEX `traveller_id`(`traveller_id`) USING BTREE,
  CONSTRAINT `order_traveller_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_traveller_ibfk_2` FOREIGN KEY (`traveller_id`) REFERENCES `traveller` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单旅客中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_traveller
-- ----------------------------
INSERT INTO `order_traveller` VALUES (1, 1);
INSERT INTO `order_traveller` VALUES (3, 1);
INSERT INTO `order_traveller` VALUES (5, 1);
INSERT INTO `order_traveller` VALUES (1, 2);
INSERT INTO `order_traveller` VALUES (3, 2);
INSERT INTO `order_traveller` VALUES (1, 3);
INSERT INTO `order_traveller` VALUES (2, 3);
INSERT INTO `order_traveller` VALUES (3, 3);
INSERT INTO `order_traveller` VALUES (1, 4);
INSERT INTO `order_traveller` VALUES (2, 4);
INSERT INTO `order_traveller` VALUES (3, 4);
INSERT INTO `order_traveller` VALUES (4, 4);
INSERT INTO `order_traveller` VALUES (5, 4);
INSERT INTO `order_traveller` VALUES (2, 5);
INSERT INTO `order_traveller` VALUES (4, 5);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '订单表主键自增',
  `order_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号uuid',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `people_count` int(11) NULL DEFAULT NULL COMMENT '出行人数总计',
  `order_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单描述',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '支付方式(0 支付宝 1 微信 2其它)',
  `order_status` int(11) NULL DEFAULT NULL COMMENT '订单状态(0 未支付 1 已支付)',
  `product_id` int(32) NULL DEFAULT NULL COMMENT '产品id 外键',
  `member_id` int(32) NULL DEFAULT NULL COMMENT '会员（联系人）id 外键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_num`(`order_num`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `member_id`(`member_id`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '0E7231DC797C486290E8713CA3C6ECCC', '2020-06-10 20:22:11', 5, '没什么', 0, 1, 1, 1);
INSERT INTO `orders` VALUES (2, '5DC6A48DD4E94592AE904930EA866AFA', '2020-06-10 20:22:28', 4, '没什么', 0, 1, 1, 1);
INSERT INTO `orders` VALUES (3, '2FF351C4AC744E2092DCF08CFD314420', '2020-06-10 14:47:12', 2, '没什么', 1, 1, 2, 1);
INSERT INTO `orders` VALUES (4, 'A0657832D93E4B10AE88A2D4B70B1A28', '2020-06-10 14:47:08', 2, '没什么', 1, 1, 2, 1);
INSERT INTO `orders` VALUES (5, 'E4DD4C45EED84870ABA83574A801083E', '2020-06-09 23:29:53', 2, '没什么', 0, 1, 2, 1);
INSERT INTO `orders` VALUES (6, '96CC8BD43C734CC2ACBFF09501B4DD5D', '2020-06-10 14:47:09', 2, '没什么', 2, 1, 4, 3);
INSERT INTO `orders` VALUES (7, '55F9AF582D5A4DB28FB4EC3199385762', '2020-06-10 14:47:10', 2, '没什么', 1, 1, 3, 1);
INSERT INTO `orders` VALUES (8, 'CA005CF1BE3C4EF68F88ABC7DF30E976', '2020-06-09 23:30:39', 2, '没什么', 0, 1, 3, 2);
INSERT INTO `orders` VALUES (9, '3081770BC3984EF092D9E99760FDABDE', '2020-06-09 23:30:43', 2, '没什么', 0, 1, 3, 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路劲',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '查找所有产品', '/product/findAll');
INSERT INTO `permission` VALUES (2, '查询所有订单', '/order/findAll');
INSERT INTO `permission` VALUES (3, '查询所有用户', '/user/findAll');
INSERT INTO `permission` VALUES (4, '查看所有权限', '/permission/findAll.do');
INSERT INTO `permission` VALUES (5, '访问首页', '/index.jsp');
INSERT INTO `permission` VALUES (6, '进行登录', '/login.jsp');
INSERT INTO `permission` VALUES (8, '测试用的', '/test');
INSERT INTO `permission` VALUES (10, '权限1', '权限1');
INSERT INTO `permission` VALUES (11, '权限2', '权限2');
INSERT INTO `permission` VALUES (12, '测试用的', '/test');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `product_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称（路线名称）',
  `city_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发城市',
  `departure_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '出发时间',
  `product_price` double NULL DEFAULT NULL COMMENT '产品价格',
  `product_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `product_status` bigint(20) NULL DEFAULT 0 COMMENT '状态（0关闭 1开启）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '旅游产品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'yhs-001', '北京三日游', '北京', '2020-06-09 11:30:00', 1200, '北京三日游，很不错啊', 1);
INSERT INTO `product` VALUES (2, 'yhs-002', '上海五日有', '上海', '2020-06-10 11:31:08', 1800, '上还是魔都', 0);
INSERT INTO `product` VALUES (3, 'yhs-003', '成都1日游', '成都', '2020-06-19 09:32:22', 800, '吃火锅了', 1);
INSERT INTO `product` VALUES (4, '0720641556e24b389287e774db270eac', '杭州7日游', '杭州', '2020-06-09 22:42:16', 9999, '我喜欢杭州', 1);
INSERT INTO `product` VALUES (5, '8e3fc9694f1643f9818cb6647c5bab09', '武汉3日游', '武汉', '2020-06-06 04:03:04', 666, '武汉大学也不错', 1);
INSERT INTO `product` VALUES (6, '8e3fc9694f1643f9818cb6647c5bab09', '武汉3日游', '武汉', '2020-06-06 04:03:04', 666, '武汉大学也不错', 1);
INSERT INTO `product` VALUES (7, '8e3fc9694f1643f9818cb6647d5bab09', '武汉7日游', '武汉', '2020-06-06 04:03:04', 777, '武汉大学也不错', 1);
INSERT INTO `product` VALUES (8, 'add6982a8db940819f11fcf4c2c6ce65', '郑州', '上海', '2020-06-10 13:50:10', 6666, '我爱郑州', 1);
INSERT INTO `product` VALUES (9, 'e23119d9e750487a824e0f9a46396d83', '广州3日游', '广州', '2020-06-10 13:51:30', 9999, '广州还不需哦', 1);
INSERT INTO `product` VALUES (10, '9c1d5d6552bb4272b56eb95ded785a59', '上海青旅游戏', '上海', '2020-06-10 14:03:53', 123456, '这是个测试', 0);
INSERT INTO `product` VALUES (11, '6e1f054b45a0425fad87285afbafa772', '北京7天乐', '北京', '2020-06-10 14:07:22', 66666, '我爱北京天安门', 0);
INSERT INTO `product` VALUES (12, 'a6c3c79261584e7a81ddd1ab31b9c36d', '上海地理是', '上海', '2020-06-10 14:12:31', 132456, '我爱上海', 1);
INSERT INTO `product` VALUES (13, 'a56791ddd80440a18ce677b6952520f6', '深圳我来了', '深圳', '2020-06-10 14:13:03', 5555, '你好啊', 0);
INSERT INTO `product` VALUES (14, '3d9d1e5ea68a4d5e8c1dc64e42d6d0d7', '杭州西湖统一有', '杭州', '2020-06-10 14:14:00', 888, '我爱你终止', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'USER', '普通用户');
INSERT INTO `role` VALUES (2, 'ADMIN', '管理员');
INSERT INTO `role` VALUES (4, 'Bug', '我是bug生产者');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` int(32) NOT NULL COMMENT '角色表的主键id',
  `permission_id` int(32) NOT NULL COMMENT '权限表的主键id',
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (4, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (2, 2);
INSERT INTO `role_permission` VALUES (4, 2);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (2, 3);
INSERT INTO `role_permission` VALUES (4, 3);
INSERT INTO `role_permission` VALUES (4, 4);
INSERT INTO `role_permission` VALUES (1, 5);
INSERT INTO `role_permission` VALUES (4, 5);
INSERT INTO `role_permission` VALUES (4, 6);
INSERT INTO `role_permission` VALUES (4, 12);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问者的用户名',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问者的ip',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问的url',
  `clazz` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问的类',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问的方法',
  `visit_time` datetime NULL DEFAULT NULL COMMENT '访问的开始时间',
  `execution_time` int(32) NULL DEFAULT NULL COMMENT '访问的执行时长',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (7, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-13 17:59:15', 150);
INSERT INTO `sys_log` VALUES (8, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-13 17:59:17', 4);
INSERT INTO `sys_log` VALUES (9, 'admin', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-13 17:59:19', 11);
INSERT INTO `sys_log` VALUES (10, 'admin', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-13 17:59:21', 8);
INSERT INTO `sys_log` VALUES (11, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-13 17:59:32', 15);
INSERT INTO `sys_log` VALUES (12, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-13 17:59:34', 3);
INSERT INTO `sys_log` VALUES (13, 'admin', '0:0:0:0:0:0:0:1', '/user/findById.do', 'cn.yhs.learn.controller.UserController', 'findById', '2020-06-13 17:59:37', 4);
INSERT INTO `sys_log` VALUES (14, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-13 17:59:39', 8);
INSERT INTO `sys_log` VALUES (15, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-13 17:59:58', 3);
INSERT INTO `sys_log` VALUES (16, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-13 18:00:01', 2);
INSERT INTO `sys_log` VALUES (17, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-13 18:00:02', 3);
INSERT INTO `sys_log` VALUES (18, 'admin', '0:0:0:0:0:0:0:1', '/permission/save.do', 'cn.yhs.learn.controller.PermissionController', 'save', '2020-06-13 18:00:18', 13);
INSERT INTO `sys_log` VALUES (19, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-13 18:00:18', 2);
INSERT INTO `sys_log` VALUES (20, 'admin', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-13 18:00:22', 7);
INSERT INTO `sys_log` VALUES (21, 'admin', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-13 18:00:24', 3);
INSERT INTO `sys_log` VALUES (22, 'admin', '0:0:0:0:0:0:0:1', '/role/findNoPermissionById', 'cn.yhs.learn.controller.RoleController', 'findNoPermissionById', '2020-06-13 18:00:25', 3);
INSERT INTO `sys_log` VALUES (23, 'admin', '0:0:0:0:0:0:0:1', '/role/addPermission.do', 'cn.yhs.learn.controller.RoleController', 'addPermission', '2020-06-13 18:00:27', 12);
INSERT INTO `sys_log` VALUES (24, 'admin', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-13 18:00:27', 3);
INSERT INTO `sys_log` VALUES (25, 'admin', '0:0:0:0:0:0:0:1', '/role/findById', 'cn.yhs.learn.controller.RoleController', 'findById', '2020-06-13 18:00:28', 3);
INSERT INTO `sys_log` VALUES (26, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-13 18:00:32', 3);
INSERT INTO `sys_log` VALUES (27, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-13 18:00:34', 2);
INSERT INTO `sys_log` VALUES (28, 'admin', '0:0:0:0:0:0:0:1', '/user/findById.do', 'cn.yhs.learn.controller.UserController', 'findById', '2020-06-13 18:00:37', 3);
INSERT INTO `sys_log` VALUES (29, 'admin', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-13 18:00:44', 3);
INSERT INTO `sys_log` VALUES (30, 'user', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-13 18:01:26', 3);
INSERT INTO `sys_log` VALUES (31, 'yhs', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 08:15:05', 3);
INSERT INTO `sys_log` VALUES (32, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 08:15:11', 2);
INSERT INTO `sys_log` VALUES (33, 'yhs', '0:0:0:0:0:0:0:1', '/user/findById.do', 'cn.yhs.learn.controller.UserController', 'findById', '2020-06-14 08:15:13', 5);
INSERT INTO `sys_log` VALUES (34, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 08:51:29', 145);
INSERT INTO `sys_log` VALUES (35, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 08:51:35', 17);
INSERT INTO `sys_log` VALUES (36, 'admin', '0:0:0:0:0:0:0:1', '/user/findById.do', 'cn.yhs.learn.controller.UserController', 'findById', '2020-06-14 08:51:38', 5);
INSERT INTO `sys_log` VALUES (37, 'admin', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 08:51:49', 11);
INSERT INTO `sys_log` VALUES (38, 'admin', '0:0:0:0:0:0:0:1', '/role/findById', 'cn.yhs.learn.controller.RoleController', 'findById', '2020-06-14 08:51:51', 3);
INSERT INTO `sys_log` VALUES (39, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 08:52:02', 8);
INSERT INTO `sys_log` VALUES (40, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 08:52:04', 4);
INSERT INTO `sys_log` VALUES (41, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 08:52:06', 4);
INSERT INTO `sys_log` VALUES (42, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 08:52:08', 5);
INSERT INTO `sys_log` VALUES (43, 'user', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-14 08:52:25', 8);
INSERT INTO `sys_log` VALUES (44, 'user', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-14 09:20:12', 8);
INSERT INTO `sys_log` VALUES (45, 'user', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 09:21:35', 16);
INSERT INTO `sys_log` VALUES (46, 'user', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 09:21:35', 3);
INSERT INTO `sys_log` VALUES (47, 'admin', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 09:26:16', 158);
INSERT INTO `sys_log` VALUES (48, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:18', 10);
INSERT INTO `sys_log` VALUES (49, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:22', 4);
INSERT INTO `sys_log` VALUES (50, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:30', 4);
INSERT INTO `sys_log` VALUES (51, 'admin', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-14 09:26:33', 10);
INSERT INTO `sys_log` VALUES (52, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:34', 5);
INSERT INTO `sys_log` VALUES (53, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:37', 4);
INSERT INTO `sys_log` VALUES (54, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:37', 3);
INSERT INTO `sys_log` VALUES (55, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:38', 2);
INSERT INTO `sys_log` VALUES (56, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:39', 3);
INSERT INTO `sys_log` VALUES (57, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:40', 3);
INSERT INTO `sys_log` VALUES (58, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:26:42', 4);
INSERT INTO `sys_log` VALUES (59, 'user', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-14 09:27:28', 3);
INSERT INTO `sys_log` VALUES (60, 'user', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 09:27:29', 3);
INSERT INTO `sys_log` VALUES (61, 'user', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 09:27:29', 7);
INSERT INTO `sys_log` VALUES (62, 'admin', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 09:32:03', 164);
INSERT INTO `sys_log` VALUES (63, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:32:08', 19);
INSERT INTO `sys_log` VALUES (64, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:32:12', 2);
INSERT INTO `sys_log` VALUES (65, 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:32:14', 3);
INSERT INTO `sys_log` VALUES (66, 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:32:19', 10);
INSERT INTO `sys_log` VALUES (67, 'yhs', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 09:35:56', 8);
INSERT INTO `sys_log` VALUES (68, 'yhs', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-14 09:36:01', 9);
INSERT INTO `sys_log` VALUES (69, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:36:07', 2);
INSERT INTO `sys_log` VALUES (70, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:36:11', 3);
INSERT INTO `sys_log` VALUES (71, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:36:12', 2);
INSERT INTO `sys_log` VALUES (72, 'yhs', '0:0:0:0:0:0:0:1', '/user/findById.do', 'cn.yhs.learn.controller.UserController', 'findById', '2020-06-14 09:36:15', 4);
INSERT INTO `sys_log` VALUES (73, 'yhs', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:36:17', 5);
INSERT INTO `sys_log` VALUES (74, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:40:05', 10);
INSERT INTO `sys_log` VALUES (75, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:40:07', 3);
INSERT INTO `sys_log` VALUES (76, 'yhs', '0:0:0:0:0:0:0:1', '/product/findAll.do', 'cn.yhs.learn.controller.ProductController', 'findAllWithPage', '2020-06-14 09:40:09', 10);
INSERT INTO `sys_log` VALUES (77, 'yhs', '0:0:0:0:0:0:0:1', '/order/findAll.do', 'cn.yhs.learn.controller.OrderController', 'findAll', '2020-06-14 09:40:12', 7);
INSERT INTO `sys_log` VALUES (78, 'yhs', '0:0:0:0:0:0:0:1', '/user/findAll.do', 'cn.yhs.learn.controller.UserController', 'findAll', '2020-06-14 09:40:12', 2);
INSERT INTO `sys_log` VALUES (79, 'yhs', '0:0:0:0:0:0:0:1', '/rolefindAll.do', 'cn.yhs.learn.controller.RoleController', 'findAll', '2020-06-14 09:40:13', 9);
INSERT INTO `sys_log` VALUES (80, 'yhs', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 09:40:14', 8);
INSERT INTO `sys_log` VALUES (81, 'yhs', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 09:40:21', 3);
INSERT INTO `sys_log` VALUES (82, 'yhs', '0:0:0:0:0:0:0:1', '/permissionfindAll.do', 'cn.yhs.learn.controller.PermissionController', 'findAll', '2020-06-14 09:40:22', 7);

-- ----------------------------
-- Table structure for traveller
-- ----------------------------
DROP TABLE IF EXISTS `traveller`;
CREATE TABLE `traveller`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `credentials_type` int(11) NULL DEFAULT NULL COMMENT '证件类型 0身份证 1护照 2军官证',
  `credentials_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `traveller_type` int(11) NULL DEFAULT NULL COMMENT '旅客类型(人群) 0 成人 1 儿童',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '旅客信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of traveller
-- ----------------------------
INSERT INTO `traveller` VALUES (1, '喻汉生', '男', '12345679', 0, '411524199505062325', 0);
INSERT INTO `traveller` VALUES (2, '喻汉生2', '女', '12345679', 0, '411524199505464894', 1);
INSERT INTO `traveller` VALUES (3, '喻汉生3', '男', '12345679', 0, '411524199505066512', 0);
INSERT INTO `traveller` VALUES (4, '喻汉生4', '男', '11111', 0, '411524199505789456', 0);
INSERT INTO `traveller` VALUES (5, '张秀云', '女', '000000', 0, '456424199505066512', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表的主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态：0未开启，1开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', '$2a$10$8XVuGEBfiXlY5iYix5ZdpOsUiMza1yHYo3Co1dUG5XZws2RvaGi7u', 'user@163.com', '15617577080', 1);
INSERT INTO `user` VALUES (2, 'admin', '$2a$10$TJHGA7wm0KLDOQwy2xkGB.s.KIThh4A6SFz2XaJa3YwiOE4if7y5e', 'admin@163.com', '12345678911', 1);
INSERT INTO `user` VALUES (3, 'yhs', '$2a$10$lYdJOH7Qj6olFAWCkVbbi.E8G9u.ch/2y1Vi/8KHTdxFUXgDkHfE6', 'yhs@163.com', '12345678966', 0);
INSERT INTO `user` VALUES (4, 'zxy', '$2a$10$p3/PFlg4JjtnW6exbdfrqOUYDph0qNuiyYExX7wWxRu49xJHG7B7C', 'zxy@162.com', '12345678978', 0);
INSERT INTO `user` VALUES (5, 'aaa', '$2a$10$gmQtG03ra96SOv3Dm1Pdyu1uv6zhoP41BZbioFkAExvuHkfuTXAVe', 'aaa@aaa.com', '132456789', 1);
INSERT INTO `user` VALUES (6, 'bbb', '$2a$10$w0.sW0DR7antySbKE1WbvOfobpGedPMY9H3QIV2V.qheNC8zid2ie', 'bbb@163.com', '12345678910', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(32) NOT NULL COMMENT '用户id主键',
  `role_id` int(32) NOT NULL COMMENT '角色id主键',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (3, 1);
INSERT INTO `user_role` VALUES (4, 1);
INSERT INTO `user_role` VALUES (6, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (6, 2);

SET FOREIGN_KEY_CHECKS = 1;
