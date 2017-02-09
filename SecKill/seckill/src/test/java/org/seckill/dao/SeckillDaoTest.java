package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * SeckillDao 方法的测试类
 * Created by Kexin_Li on 2017/2/9.
 */
// 1:配置 Spring 和 junit 的整合,目的是让 junit 在启动时就加载 SpringIOC 容器.
@RunWith(SpringJUnit4ClassRunner.class)
// 2:告诉 junit,Spring 的配置文件在何处
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    // 3:注入 DAO 实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    /**
     * 1000元秒杀iPhone7
     * Seckill{seckillId = 1000,
     *         name = ' 1000元秒杀iPhone7',
     *         number = 100,
     *         startTime = Thu Feb 09 00:00:00 CST 2017,
     *         endTime = Fri Feb 10 00:00:00 CST 2017, c
     *         reateTime = Thu Feb 09 14:58:05 CST 2017}

     */
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        // 4:报如下错误：
        // Caused by: org.apache.ibatis.binding.BindingException: Parameter 'offset' not found.Available parameters are [0, 1, param1, param2]

        /*
        * 由于 Java 没有保存形参的记录,所以 queryAll(int offset, int limit) 在运行时将被解析为 query(arg0, arg1).
        * 解决的办法是在参数前面加上 Param 的注解.
        */
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        // updateCount 只能是 0.
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000, killTime);
        System.out.print(updateCount);
    }

}