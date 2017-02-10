package org.seckill.exception;

/**
 * 秒杀相关业务异常
 * 在一般的项目中,DAO 层有 DAO 层的异常
 * Created by Kexin_Li on 2017/2/10.
 */
public class SeckillException extends  RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}
