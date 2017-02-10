package org.seckill.exception;

/**
 * 重复秒杀异常(运行期异常)
 * Java 异常分两种：
 *      1.编译期异常
 *      2.运行期异常：不用手动去 try/catch. 并且 Spring 声明式事务只接收运行期异常回滚策略.
 * Created by Kexin_Li on 2017/2/10.
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

}
