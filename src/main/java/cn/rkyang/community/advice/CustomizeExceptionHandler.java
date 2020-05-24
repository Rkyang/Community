package cn.rkyang.community.advice;

import cn.rkyang.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/24 16:24
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {
        if (ex instanceof CustomizeException) {
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", "Something went wrong...");
        }
        return new ModelAndView("error");
    }
}
