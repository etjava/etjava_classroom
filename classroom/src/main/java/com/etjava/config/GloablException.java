package com.etjava.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GloablException {


//    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex){
        StringBuffer buf = new StringBuffer();
        buf.append("ERROR: <br />")
                .append(ex.getMessage())
                .append("<br />")
                .append(ex.getStackTrace().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buf.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
                               HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        String msg = "<font color='red' style='font-weight: bold' >ERROR: "+e.getMessage()+"</font>";
        mav.addObject("exceptionMsg", msg);
        mav.addObject("url", reqest.getRequestURL());
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuffer buf = new StringBuffer();
        for (StackTraceElement stackTraceElement : stackTrace) {
            buf.append(stackTraceElement.toString()).append("<br />");
        }
        mav.addObject("stackTrace",buf.toString());
        mav.setViewName("/error/500");// 500.html
        return mav;
    }
}
