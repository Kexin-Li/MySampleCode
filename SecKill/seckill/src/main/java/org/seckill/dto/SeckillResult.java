package org.seckill.dto;

/**
 * 所有的 ajax 请求返回类型,封装 json 结果
 * Created by Kexin_Li on 2017/2/11.
 */
public class SeckillResult<T> {

    // 请求是否成功的标志位
    private boolean success;
    // 返回的数据
    private T data;
    // 错误信息
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
