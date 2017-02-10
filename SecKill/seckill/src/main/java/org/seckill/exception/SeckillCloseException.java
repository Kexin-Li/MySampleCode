package org.seckill.exception;

/**
 * 秒杀关闭异常：比如时间到了,库存没了.
 * Created by Kexin_Li on 2017/2/10.
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}
