package cn.donaldy.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DonaldY on 2017/8/2.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userpawd = request.getParameter("userpawd");

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);

        cfg.setServletContextForTemplateLoading(getServletContext(), "/template");;

        Map<String, String> rootMap = new HashMap<String, String>();
        rootMap.put("username", username);
        rootMap.put("userpawd", userpawd);

        try {

            Template template = cfg.getTemplate("index.ftl");

            response.setContentType("text/html; charset=utf-8 ");

            Writer out = response.getWriter();

            template.process(rootMap, out);

        }catch(IOException e) {
            e.printStackTrace();
        }catch(TemplateException t) {
            t.printStackTrace();
        }
    }
}
