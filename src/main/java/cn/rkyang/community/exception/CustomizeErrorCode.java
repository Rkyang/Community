package cn.rkyang.community.exception;

/**
 * 异常枚举
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/24 16:48
 */
public enum CustomizeErrorCode implements CustomizeErrorCodeI {

    /**
     * 问题不存在
     */
    QUESTION_NOT_EXIST("This question is not exist...");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
