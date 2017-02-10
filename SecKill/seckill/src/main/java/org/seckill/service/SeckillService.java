package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务接口:应该站在 使用者 的角度设计接口.
 * 如何站在使用者的角度设计, 有三个方面要注意:
 *     1.方法定义粒度：应该非常明确,比如秒杀的接口应该有个方法叫做执行秒杀,传入一些具体参数.而不应该关注怎么减库存,怎么记录购买行为.
 *     2.参数：参数应该越简练越直接越好,而不应该传入一个 Map,或者封装一个很庞大的列表.
 *     3.返回类型：即 return 类型要十分友好,不应该 return 一个 Map 或者 Entity 类等等.另外,要用好异常,有的业务可以抛出异常.比如重复秒杀抛异常.
 * Created by Kexin_Li on 2017/2/10.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录：为之后要做的列表页展示所有产品做准备.
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址,否则输出系统时间和秒杀时间。
     * 这个方法的目的是在秒杀未开启时谁也猜不到我们的秒杀地址是什么,而不是让用户可以通过 URL 拼出秒杀地址,提前让浏览器插件去执行秒杀.
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作：
     *      1.seckillId 和 userPhone 用来验证用户的唯一性
     *      2.md5 是 Exposer 这个 DTO 类传递进来的,因为 Exposer 类比这个方法先调用,调用后会生成一个 md5 值,
     *        我们在秒杀的时候判断当前的 md5 值和之前生成的 md5 值是否相同,如果不同说明用户的 URL 被篡改过,
     *        这样就不让他执行秒杀.
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;

}
