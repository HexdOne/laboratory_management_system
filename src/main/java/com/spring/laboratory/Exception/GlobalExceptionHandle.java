package com.spring.laboratory.Exception;

import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

    @ExceptionHandler(value = MyException.class)
    public Result handle1(MyException e) {
        if (e instanceof MyException) {
            return ResultUtils.error(e.getCode(),e.getMsg());
        } else {
            return ResultUtils.error(ErrorEnum.UNKOWN_ERROR);
        }
    }

}