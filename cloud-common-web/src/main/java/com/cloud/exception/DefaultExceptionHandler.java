package com.cloud.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class DefaultExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger("exlog");

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            response.setStatus(400);
            MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) ex;
            return build(new ErrorMessage(String.format("[%s]字段不能为空", msrpe.getParameterName())));
        } else if (ex instanceof NoHandlerFoundException) {
            response.setStatus(404);
            return build(new ErrorMessage(404, "404NotFound"));
        } else {
            response.setStatus(500);
            logger.info("request-id=" + request.getHeader("request-id"));
            logger.info(request.getMethod() + request.getServletPath());
            logger.info(request.getQueryString());
//            try {
//                logger.info(Okio.buffer(Okio.source(request.getInputStream())).readString(Charsets.UTF_8));
//            } catch (IOException e) {
                //ignore
//            }
            logger.info(getStackTraceAsString(ex));
            return build(new ErrorMessage("内部错误"));
        }
    }

    public static ModelAndView build(Object object) {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setExtractValueFromSingleKeyModel(true);
        ModelAndView mav = new ModelAndView(view);
        mav.addObject(object);
        return mav;
    }

    public static String getStackTraceAsString(Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
