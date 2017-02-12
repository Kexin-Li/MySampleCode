-- 秒杀执行存储过程:将插入购买明细和减库存的操作放进存储过程

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- 注意事项：
-- 1:存储过程的优化点是在减少了事务行级锁的持有时间.
-- 2:不应该过度依赖存储过程.
-- 3:简单的逻辑可以用存储过程.
-- 4:本秒杀项目使用了存储过程后,一个商品的秒杀单可以达到 6000/QPS.

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

-- 将换行标识符由 ; 变为 $$
DELIMITER $$

-- 定义存储过程
-- 参数： in 输入参数; out 输出参数
-- ROW_COUNT():返回上一条修改类型 SQL (delete, update, insert)的影响行数
-- ROW_COUNT() 返回值: 0:未修改数据; <0:SQL错误/未执行修改SQL; >0:修改的行数.
CREATE PROCEDURE `seckill`.`execute_seckill`
  (IN v_seckill_id BIGINT, IN v_phone BIGINT,
    IN v_kill_time TIMESTAMP, OUT r_result INT)

  BEGIN
    -- 定义 insert_count 变量
    DECLARE insert_count INT DEFAULT 0;
    -- 开启事务
    START TRANSACTION ;
    -- 插入购买记录
    INSERT IGNORE INTO success_killed
      (seckill_id, user_phone, create_time)
    VALUES
      (v_seckill_id, v_phone, v_kill_time);
    -- 执行 ROW_COUNT() 函数
    SELECT ROW_COUNT() INTO insert_count;
    -- 如果返回值是0,则说明有重复秒杀出现
    IF (insert_count = 0) THEN
      ROLLBACK ;
      SET r_result = -1;
    -- 如果返回值小于0,则说明系统异常
    ELSEIF (insert_count < 0) THEN
      ROLLBACK ;
      SET r_result = -2;
    -- 如果返回值大于0,则执行减库存
    ELSE
      UPDATE seckill
      SET number = number - 1
      WHERE seckill_id = v_seckill_id
        AND end_time > v_kill_time
        AND start_time < v_kill_time
        AND number > 0;
      SELECT ROW_COUNT() INTO insert_count;
      IF (insert_count = 0) THEN
        ROLLBACK ;
        SET r_result = 0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result= 1;
      END IF ;
  END IF;
  END
$$
-- 存储过程定义结束

-- 下面这些执行存储过程的逻辑应该写在 Java 代码中
DELIMITER ;
-- 定义变量
SET @r_result = -3;
-- 执行存储过程
CALL execute_seckill(1003, 13099000099, now(), @r_result);
-- 获取结果
SELECT @r_result;