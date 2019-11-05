package servlet.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseElement extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(request, response);
        } else if (request.getMethod().equalsIgnoreCase("DELETE")) {
            doDelete(request, response);
        }
        else {
            super.service(request, response);
        }
    }

    public abstract void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public abstract void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}