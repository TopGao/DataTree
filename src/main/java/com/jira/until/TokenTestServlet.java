package com.jira.until;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/v2/token")
public class TokenTestServlet extends HttpServlet
{
    private static final long serialVersionUID = 2573245614706073195L;

    /**
     * GET请求判断用户token是否过期
     * TODO 增加功能描述
     * @author lihong
     * @date 2017年7月27日
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Content-type", "text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        try
        {
            for (int i = 0; i < 10; i++)
            {
                Thread.sleep(1000);
                out.println("现在是第【" +i +"】你没的<br/>");
                out.flush();
            }
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (out != null)
            {
                out.close();
                out.flush();
            }
        }
    }
}
