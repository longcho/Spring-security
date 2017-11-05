package cn.longzzai.handler;

import cn.longzzai.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * MyException异常拦截
 *
 * @author longcho
 * 2017-10-16-14:46
 */
@ControllerAdvice
public class ControllerExceptionHandler{

    @ExceptionHandler(MyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String , Object> handleMyException(MyException e){
        Map<String , Object> map= new HashMap<>();
        map.put("message" , e.getMessage());
        map.put("id" , e.getId());
        return map;
    }
}
