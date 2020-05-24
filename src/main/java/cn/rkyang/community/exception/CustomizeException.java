package cn.rkyang.community.exception;

/**
 * 自定义异常
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/24 16:35
 */
public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(CustomizeErrorCodeI customizeErrorCodeI) {
        this.message = customizeErrorCodeI.getMessage();
    }

   @Override
    public String getMessage() {
        return message;
    }
}
