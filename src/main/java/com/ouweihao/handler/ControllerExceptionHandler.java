package com.ouweihao.handler;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //拦截所有标注有Controller的控制器
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) //标注此方法可以用于错误处理,Exception
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //记录
        logger.error("Request URL:{}, Exception :{}", request.getRequestURL(), e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        //返回到哪个页面 error文件夹下的error.html
        mv.setViewName("error/error");
        return mv;
    }

}
