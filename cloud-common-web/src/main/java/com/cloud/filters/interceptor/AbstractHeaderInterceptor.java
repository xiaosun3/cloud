package com.cloud.filters.interceptor;

import com.cloud.annotations.PassCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;


public abstract class AbstractHeaderInterceptor implements HandlerInterceptor {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractHeaderInterceptor.class);

    public static class HeaderCode {
        public String headerKey;
        int errorCode;

        HeaderCode(String headerKey, int errorCode) {
            this.headerKey = headerKey;
            this.errorCode = errorCode;
        }
    }

    protected Boolean isSandbox = false;

    public static final HeaderCode[] headers = new HeaderCode[]{
//            new HeaderCode("app-version", 8),
//            new HeaderCode("channel", 3),
//            new HeaderCode("device", 2),
//            new HeaderCode("main-area", 18),
//            new HeaderCode("model", 5),
//            new HeaderCode("os-version", 6),
//            new HeaderCode("platform", 4),
//            new HeaderCode("screen-height", 7),
//            new HeaderCode("screen-width", 7),
//            new HeaderCode("spec-area", 18),
//            new HeaderCode("version", 1)
    };

    public static final HeaderCode[] otherHeaders = new HeaderCode[]{
            new HeaderCode("request-id", 14),
            new HeaderCode("client-request-time", 15),
            new HeaderCode("signature", 20)
    };

    protected Boolean skipHeaderCheck(HttpServletRequest request, Object o) {

        String uri = request.getServletPath();
        if(isPassCheck(o)){
            return true;
        }
        return  "true".equals(request.getHeader("header-check-skip"));
    }

    private  boolean isPassCheck(Object o) {
        boolean pass = false;
        if (o instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) o;
            return pass = hm.getMethodAnnotation(PassCheck.class) != null;
        }
        return pass;
    }

}
