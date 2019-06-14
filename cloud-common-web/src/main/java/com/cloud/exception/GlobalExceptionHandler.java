package com.cloud.exception;//package com.sun.boot.exception;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 全局异常处理<br>
// * 包括自定义异常、系统异常
// */
//@ControllerAdvice
//public class GlobalExceptionHandler{
//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//	@ExceptionHandler(value = {BaseException.class})
//    @ResponseBody
//    public InnerErrorMessage handleBaseException(BaseException ex, HttpServletRequest request, HttpServletResponse response) {
//		response.setStatus(200);
//		logger.info("RequestURI[{}]",request.getRequestURI());
//        logger.info("QueryString[{}]",request.getQueryString());
//		logger.error(ex.msg());
//        return new InnerErrorMessage((BaseException) ex);
//    }
//
//	@ExceptionHandler(value = {Exception.class})
//    @ResponseBody
//    public InnerErrorMessage handleOtherException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
//		if (ex instanceof MissingServletRequestParameterException) {
//			response.setStatus(400);
//			MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) ex;
//			String msg=String.format("[%s]字段不能为空", msrpe.getParameterName());
//			logger.info("{}:{}",request.getMethod(),request.getServletPath());
//			logger.info("RequestURI[{}]",request.getRequestURI());
//	        logger.info("QueryString[{}]",request.getQueryString());
//			logger.error(msg);
//	        return new InnerErrorMessage(msg);
//		}else if(ex instanceof HttpMessageNotReadableException){
//			response.setStatus(400);
//			logger.info("{}:{}",request.getMethod(),request.getServletPath());
//			logger.info("RequestURI[{}]",request.getRequestURI());
//	        logger.info("QueryString[{}]",request.getQueryString());
//			logger.error(ex.getMessage());
//	        return new InnerErrorMessage("Required request body is missing.");
//		}else{
//			response.setStatus(500);
//			logger.info("{}:{}",request.getMethod(),request.getServletPath());
//			logger.info("RequestURI[{}]",request.getRequestURI());
//	        logger.info("QueryString[{}]",request.getQueryString());
//	        logger.error("",ex);
//	        return new InnerErrorMessage("内部错误");
//		}
//    }
//
//}
