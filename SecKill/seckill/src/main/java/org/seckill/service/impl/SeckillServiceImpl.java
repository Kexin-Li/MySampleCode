package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;

/**
 * SeckillService 的实现类
 * Created by Kexin_Li on 2017/2/10.
 */

/**
 * Spring 为我们提供以下几种类型注解：
 *      1.@Component:代表所有的组件,当我们不知道一个类是 service, dao, controller 时使用这个注解.
 *      2.@Service, @Dao, @Controller:分别代表一个 service, dao, controller.
 */
@Service
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 不初始化这两个 DAO,因为它们的实现都在 Spring 容器中.
    // 后面的工作让 Spring 依赖注入

    /* dao 层的东西都由 MyBatis 和 Spring 整合之后,MyBatis 帮我们以 mapper 的形式帮我们初始化好放到 Spring 容器中.
       我们在 Spring 容器中只需获取这个实例,把它注入到 service 下面来.*/

    // 注入 service 依赖
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    // md5 盐值字符串,用户混淆 md5.
    private final String slat = "gdasuifg3612786#$&$&JFSOIFOI@$#)(_)!@";

    private SeckillStateEnum seckillStateEnum;

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        // 如果 seckill 对象为空,返回的 Exposer 对象的标志位为 false.
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        // 拿到三个事件
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        // 判断时间是否介于秒杀开启和结束之间
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 如果秒杀可以执行,返回的 Exposer 对象就传入 md5.
        // md5 是可以对任意一个字符串,转换成一个特定编码.最大的特定是不可逆,即即使我把这个编码展示给用户了,用户也猜不到原来的字符串.
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 生成 md5
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        // 使用 Spring 的工具类 DigestUtils 生成 md5
        String md5  = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    /**
     * 使用注解控制事务方法的优点：
     *     1:开发团队达成一致约定,使用明确标注事务方法的编程风格.
     *     2:保证事务方法的执行时间尽可能短.
     *       Spring 通过 AOP 的一些技术,当我们第一次操作数据库时就开启事务,
     *       当退出方法时如果没有异常则提交事务.如果有运行期异常则事务回滚.这样保证了所有的操作都在事务的掌控范围之内.
     *     3:不要穿插其他网络操作 PRC/HTTP请求或者剥离到事务方法外部.
     *     4:不是所有的方法都需要事务.如只有一条修改操作,只读操作不需要事务控制.
     */
    // 对上面提到的优点的理解 TODO
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        // 判断 md5 是否匹配,如果不,就抛出一个系统异常
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        // 执行秒杀逻辑:减库存 + 记录购买明细
        Date nowTime = new Date();

        try {
            // 减库存
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                // 没有更新到记录,意味着秒杀技术
                throw new SeckillCloseException("seckill is closed");
            } else {
                // 记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    // 重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId,SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            // 为了避免最终抛出的异常都变成了 SeckillException 异常而导致我们不知道到底是重复操作异常还是秒杀关闭异常,
            // 所以将这两个异常在 SeckillException 之前 catch 一下以便知道发生的什么异常.
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译期异常转化为运行期异常
            // 这样的话,当 Spring 调用这个方法时它能感受到所有的异常都归为了 SeckillException 这个异常.这时 Spring 的声明式事务会帮助我们做事务会滚.
            throw new SeckillException("seckill inner error" + e.getMessage());
        }
    }

}
