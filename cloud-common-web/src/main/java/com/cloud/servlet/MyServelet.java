package com.cloud.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sunhaidi on 2019-05-28.
 * 自定义 Servelet，也可使用第三方 Servelet 代理实现
 * <dependency>
 *     <groupId>org.mitre.dsmiley.httpproxy</groupId>
 *     <artifactId>smiley-http-proxy-servlet</artifactId>
 *  </dependency>
 */
public class MyServelet extends HttpServlet {
    String url = "http://127.0.0.1:8081/customer";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        super.doGet(req, resp);
        req.startAsync().start(() -> {

        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//          response.sendRedirect(url);
//        request.getRequestDispatcher(url).forward(request,response);

//        request.
//        buildResponse(response,0,"success");
    }

    private void buildResponse(HttpServletResponse response, int code, String msg) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(String.format("{\"code\":%d,\"msg\":\"%s\"}", code, msg));
            writer.close();
        } catch (IOException e) {
            //ignore
        }
    }
}
