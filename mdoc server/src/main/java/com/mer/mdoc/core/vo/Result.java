package com.mer.mdoc.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mercurli
 * @date 2019/10/4
 * @version: V1.0
 * @title
 */
@Data
public class Result implements Serializable {

    private boolean success;

    private String message;

    private Object data;

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

    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static Result ok() {
        return new Result(true);
    }

    public static Result error(String message) {
        return new Result(false, message);
    }
}
