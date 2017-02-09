-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;

-- 使用数据库
use seckill;

-- 创建秒杀库存表
  -- 指定InnoDB引擎，因为只有这个引擎支持事务。
  -- 指定初始自增主键为1000.
  -- 设置编码为UTF-8
  -- 给出注释
-- bigint为最大的int类型，tinyint为最小的int类型。
-- 设计完成字段之后，就需要设计索引。
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `number` INT NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
INSERT INTO
  seckill(name, number, start_time, end_time)
VALUES
  ('1000元秒杀iPhone7', 100, '2017-02-09 00:00:00', '2017-02-10 00:00:00'),
  ('500元秒杀iPad2', 100, '2017-02-09 00:00:00', '2017-02-10 00:00:00'),
  ('300元秒杀小米4', 100, '2017-02-09 00:00:00', '2017-02-10 00:00:00'),
  ('200元秒杀红米note', 100, '2017-02-09 00:00:00', '2017-02-10 00:00:00');


-- 秒杀成功明细表：用来记录谁秒杀成功了以及秒杀成功的时间
-- 用户登录认证相关的信息
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品id',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识 -1:无效 0:成功 1:已付款 2:已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id, user_phone), /*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- 更新所有商品的秒杀时间
UPDATE seckill SET start_time = '2017-02-10 00:00:00', end_time = '2017-02-11 00:00:00' WHERE number=100;

-- SHOW CREATE TABLE seckill\G;#显示表的创建信息