package indi.zhuyst.retrofitlearn.client.pojo;

/**
 * Created by zhuyst on 2017/7/11.
 */

public class Msg<T> {
    private Boolean isSuccess;

    private String message;

    private T entity;

    public Msg() {}

    public Msg(T entity){
        this.entity = entity;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
