package com.mer.mdoc.core.vo;

import lombok.Setter;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: V1.0
 * @title
 */
@Setter
@Getter
public class Result<T> implements Serializable {

    private boolean success;

    private String message;

    private T data;

    // 时间戳
    private long timestamp = System.currentTimeMillis();

    public Result() {}

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return new Result<>(true);
    }

    public static <T> Result<T> data(T data) {
        return new Result<T>(true, "", data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(false, message);
    }

}
