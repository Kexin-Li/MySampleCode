package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * SuccessKilled 实体类的 DAO 接口
 * Created by Kexin_Li on 2017/2/9.
 */
public interface SuccessKilleDao {

    /**
     * 插入购买明细，可过滤重复秒杀。
     * @param seckillId
     * @param userPhone
     * @return 如果影响行数 >1，表示更新的记录行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据 id 查询 SuccessKilled 并携带秒杀产品对象
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
