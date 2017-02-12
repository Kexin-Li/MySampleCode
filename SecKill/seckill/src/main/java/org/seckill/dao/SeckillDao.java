package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Seckill 实体类的 DAO 接口
 * Created by Kexin_Li on 2017/2/9.
 */
public interface SeckillDao {

    /**
     * 减库存, 需要减去库存的 id, 并且知道减去的时间。killTime 对应数据库中的 createTime。
     * @param seckillId
     * @param killTime
     * @return 插入的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /*下面是两个查询接口*/

    /**
     * 根据 id 查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * 由于 Java 没有保存形参的记录,所以 queryAll(int offset, int limit) 在运行时将被解析为 query(arg0, arg1).
     * 解决的办法是在参数前面加上 Param 的注解.
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
//      List<Seckill> queryAll(int offset, int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);

}
