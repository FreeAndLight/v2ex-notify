# V2EX-Notify
V2EX-Notify 用于实现当指定节点出现指定关键词时，通过邮件给订阅者发送通知。
# 开发环境
MySql : 建议使用 8.0 以上\
JDK : 建议使用 8 以上
# 使用说明
## 修改信息
1. 修改 application.yml 中的数据库信息
2. 修改 com.shanhai.v2exnotify.util.MailUtil 50 行与 56行
## 数据库建表语句
```
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : v2ex

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 24/06/2022 17:18:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_components
-- ----------------------------
DROP TABLE IF EXISTS `t_components`;
CREATE TABLE `t_components` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `key_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'key',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'value',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组件表';

-- ----------------------------
-- Table structure for t_keywords
-- ----------------------------
DROP TABLE IF EXISTS `t_keywords`;
CREATE TABLE `t_keywords` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键词',
  `subscriber` varchar(64) DEFAULT NULL COMMENT '订阅人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='关键词表';

-- ----------------------------
-- Table structure for t_node
-- ----------------------------
DROP TABLE IF EXISTS `t_node`;
CREATE TABLE `t_node` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='api表';

-- ----------------------------
-- Table structure for t_node_subscription
-- ----------------------------
DROP TABLE IF EXISTS `t_node_subscription`;
CREATE TABLE `t_node_subscription` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `node_id` varchar(255) DEFAULT NULL COMMENT '节点表id',
  `subscriber` varchar(64) DEFAULT NULL COMMENT '订阅人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='节点订阅表';

-- ----------------------------
-- Table structure for t_send_record
-- ----------------------------
DROP TABLE IF EXISTS `t_send_record`;
CREATE TABLE `t_send_record` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `topic_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主题id',
  `subscriber` varchar(64) DEFAULT NULL COMMENT '订阅人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发送记录表';

-- ----------------------------
-- Table structure for t_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `t_subscriber`;
CREATE TABLE `t_subscriber` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
  `subscriber` varchar(64) DEFAULT NULL COMMENT '订阅人',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订阅人表';

SET FOREIGN_KEY_CHECKS = 1;
```
## 插入数据
1. 在 t_node 表中插入节点信息
2. 在 t_subscriber 插入订阅者信息
3. 在 t_node_subscription 插入节点与订阅者信心
4. 在 t_keywords 插入关键词与订阅者信息
5. 在 t_components 插入 key_name 为 token 的 V站令牌
## 打包命令
```
mvn clean install -Dmaven.test.skip=true
```
## 部署命令
```
nohup java -jar v2ex-notify-0.0.1-SNAPSHOT.jar >v2ex_notify.log &
```
# 注意事项
如果部署在 Liunx 服务器上，需要检查是否可以 ping 通 v2ex
```
ping v2ex.com
```