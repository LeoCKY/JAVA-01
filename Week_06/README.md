# **第12节课作业**

##### 基于电商交易场景(用户、商品、订单)，设计一套简单的表结构，提交DDL 的 SQL 文件到 Github(后面2周的作业依然要是用到这个表结构)。




```sql
CREATE TABLE `tb_user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `account` varchar(255) NOT NULL unique COMMENT '登入號',
  `password` varchar(255) NOT NULL COMMENT '密碼',
  `salt` varchar(64) COMMENT '密碼鹽加密',
  `status` tinyint NOT NULL COMMENT '狀態',
  
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '客戶表'; 

CREATE TABLE `tb_user_info` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `f_name` varchar(255)NOT NULL,
  `l_name` varchar(255)NOT NULL,
  `email` varchar(255)NOT NULL  COMMENT '信箱',
  `phone` varchar(64)NOT NULL COMMENT '手機',
  `birthday` timestamp COMMENT '生日',
  `id_num` varchar(128) COMMENT '身分證號',
  `postcode` int(10) COMMENT '郵政編碼',
  `country` varchar(32) COMMENT '國家',
  `city` varchar(32)  COMMENT '城市',
  `address` varchar(128) COMMENT '地址',
  
  `version` int,
  `create_user` int,
  `create_date` timestamp,
  `create_ip` varchar(64),
  `update_user` int,
  `update_date` timestamp,
  `update_ip` varchar(64),
  `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '客戶資訊表'; 


CREATE TABLE `tb_order` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `user_id` int COMMENT '客戶ID',
    `merch_id` int COMMENT '商務ID' ,
    `order_no` varchar(16)NOT NULL COMMENT '訂單編號',
    `status` tinyint NOT NULL COMMENT '訂單狀態 0未支付,1已付款,2已發貨,3已簽收,10退貨申請',
    `product_amount_total` decimal(12,1)NOT NULL COMMENT '商品總價',
    `order_amount_total` decimal(12,1)NOT NULL DEFAULT '0.0' COMMENT '實際付款金額',
    `logistics_fee` decimal(12,1)NOT NULL COMMENT '運費金額',
    `pay_time` timestamp COMMENT '付款時間',
    `postcode` varchar(10) COMMENT '郵政編碼',
    `delivery_addr` varchar(128) COMMENT '收件地址',

    `version` int,
    `create_user` int,
    `create_date` timestamp,
    `create_ip` varchar(64),
    `update_user` int,
    `update_date` timestamp,
    `update_ip` varchar(64),
    `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '訂單表'; 


CREATE TABLE `tb_order_detail` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `oder_id` int NOT NULL COMMENT '訂單ID',
    `product_id` int NOT NULL COMMENT '產品ID',  
    `product_name`  varchar(127) NOT NULL COMMENT '商品標題',
    `product_desc`  varchar(127) NOT NULL COMMENT '商品規格',
    `product_price` decimal(12,1) NOT NULL COMMENT '商品金額',
    `count`  int NOT NULL COMMENT '購買數量',
    `sub_total` decimal(12,1)NOT NULL COMMENT '小計金額',
    `discount_rate` tinyint COMMENT '折扣比例',
    `discount_amount` decimal(12,1)NOT NULL COMMENT '折扣金額',
  
    `version` int,
    `create_user` int,
    `create_date` timestamp,
    `create_ip` varchar(64),
    `update_user` int,
    `update_date` timestamp,
    `update_ip` varchar(64),
    `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '訂單明細'; 




CREATE TABLE `tb_product` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `name`  varchar(128) NOT NULL COMMENT '商品標題',
    `category_id` int COMMENT '雜項ID',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '狀態',
    `price` decimal(8.1)NOT NULL COMMENT '單價',
    `commodity_code` char(10) COMMENT '商品編碼',
    `weight` decimal(5,2) COMMENT '商品重量',
  
    `version` int,
    `create_user` int,
    `create_date` timestamp,
    `create_ip` varchar(64),
    `update_user` int,
    `update_date` timestamp,
    `update_ip` varchar(64),
    `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '商品'; 


CREATE TABLE `tb_product_album` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `product_id` int COMMENT '產品 ID',
    `url` varchar(45)  DEFAULT NULL COMMENT '圖片地址',
    `intro` varchar(255) COMMENT '圖片介紹',
    `status` tinyint NOT NULL  default '0' COMMENT '狀態',
    `type`   tinyint NOT NULL  default '0' COMMENT '類型 0->圖 1->影片',
    `sort` int default '999' COMMENT '順序'  ,
    
    `version` int,
    `create_user` int,
    `create_date` timestamp,
    `create_ip` varchar(64),
    `update_user` int,
    `update_date` timestamp,
    `update_ip` varchar(64),
    `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '商品圖、影片'; 

CREATE TABLE `tb_category` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(254) NOT NULL COMMENT '商品雜項名',
    `pid` int  NOT NULL COMMENT '父分類編號',
    `sort` int default '999' COMMENT '順序'  ,
  
    `version` int,
    `create_user` int,
    `create_date` timestamp,
    `create_ip` varchar(64),
    `update_user` int,
    `update_date` timestamp,
    `update_ip` varchar(64),
    `is_del` tinyint(1)
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '商品雜項'; 


CREATE TABLE `tb_pd_category` (
    `product_id` int COMMENT '產品 ID',
    `category_id` int COMMENT '雜項 ID'
)ENGINE=InnoDB DEFAULT CHARSET 'utf8mb4' COMMENT '商品雜項關係表'; 


