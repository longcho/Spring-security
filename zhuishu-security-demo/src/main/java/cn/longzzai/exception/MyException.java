package cn.longzzai.exception;

import lombok.Data;

/**
 * @author longcho
 * 2017-10-16-14:44
 */
@Data
public class MyException extends RuntimeException {
    private String id;

    public MyException(String message, String id) {
        super(message);
        this.id = id;
    }
}
