package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * SeckillKilledDao 方法的测试类
 * Created by Kexin_Li on 2017/2/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilleDaoTest {

    @Resource
    private SuccessKilleDao successKilleDao;

    @Test
    // 这里执行一次后会输出 insertCount=1,执行第二次就会输出 insertCount=0.这样是没有重复插入的表现.
    // 这种表现产生的原因是当时写数据库语句的时候使用的联合主键(PRIMARY KEY (seckill_id, user_phone))保证了不会重复插入.
    // 使用两个字段作为主键而不报异常的原因是 SuccessKilledDao.xml 中使用了 ignore 关键字.INSERT IGNORE INTO success_killed(seckill_id, user_phone)
    public void insertSuccessKilled() throws Exception {
        long id = 1001L;
        long phone = 13011112222L;
        int insertCount = successKilleDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount= " + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1001L;
        long phone = 13011112222L;
        SuccessKilled successKilled = successKilleDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}