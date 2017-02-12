package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis 的数据访问对象
 * Created by Kexin_Li on 2017/2/11.
 */
public class RedisDao {

    // 类似于数据库连接池的 pool
    private final JedisPool jedisPool;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 序列化的本质:通过一个字节码和字节码所对应的对象有哪些属性,然后在创建这个对象的过程传递给那些属性.
    // 什么是序列化和反序列 TODO
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    /*我们希望 Redis 做以下两件事情:
    *   1:通过 Redis 拿到 Seckill 对象.
    *   2:当发现缓存没有时,我们去 put 一个缓存.*/

    /**
     * 从 Redis 拿到的字节数组,通过 protostuff 转化为我们要的 seckill 对象.
     * 这样的过程：get -> byte[] -> 反序列化 -> Object(Seckill)
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId) {
        // Redis 操作逻辑应该在这里面,而不应该在 service 方法里.
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                // Redis 和 Jedis 都没有实现内部序列号操作
                // 这里采用自定义序列化:通过开源社区的一些方案(protostuff)去写序列化,把我们的对象转换为二进制数组,然后传递给 Redis 缓存起来.
                // get -> byte[] -> 反序列化 -> Object(Seckill)

                /*我们只需告诉 protostuff 一个 pojo 对象(而不能是String,Long这样的对象)的 class, protostuff 内部有一个类似 schema 的东西会描述传入的 class 是什么结构*/
                byte[] bytes = jedis.get(key.getBytes());
                // 从缓存中获取到,接下用 protostuff 去转换
                if (bytes != null) {
                    // 空对象
                    Seckill seckill = schema.newMessage();
                    // 通过传入的三个参数,protostuff 会将属性传入对象内.
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    // seckill 被反序列
                    return seckill;
                }

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将一个 seckill 对象传递到 Redis 中
     * set Object(Seckill) -> 序列化 -> byte[]
     * @param seckill
     * @return
     */
    public String putSeckill(Seckill seckill) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存
                int timeout = 60 * 60;// 一小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
