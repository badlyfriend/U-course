package com.ujiuye.pojo;

/**
 * 创建统一的返回数据格式
 * @Author Bob
 * @Create 2021-07-21-11:45
 */
public class ResultVo {
    private int code;//状态码
    private String message;//消息
    private Object data;//数据

    public ResultVo() {
    }

    public ResultVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
