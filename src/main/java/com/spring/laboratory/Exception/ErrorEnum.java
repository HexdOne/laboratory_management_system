package com.spring.laboratory.Exception;

/**
 * 异常码
 */
public enum ErrorEnum {
    UNKOWN_ERROR(-1,"未知错误"),
    ERROR_101(101,"验证码错误"),
    ERROR_300(300,"无数据"),
    ERROR_400(400,"增加失败"),
    ERROR_401(401,"该实验室已存在"),
    ERROR_402(402,"该设备已存在"),
    ERROR_500(500,"修改失败"),
    ERROR_600(600,"删除失败"),
    ERROR_422(422,"请求正确，但语义错误"),
    ERROR_404(404,"未找到相同数据"),
    ERROR_403(403,"该数据已存在");

    private int code;

    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
