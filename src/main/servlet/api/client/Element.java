package servlet.api.client;

import com.google.gson.Gson;
import entity.ClientEntity;
import repository.ClientRepository;
import repository.CourseSessionRepository;
import servlet.api.BaseElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

@WebServlet("/api/clients/*")
public class Element extends BaseElement {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(ClientRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)))));
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        ClientEntity obj = ClientRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)));
        obj.setFirstname(request.getParameter("firstname"));
        obj.setLastname(request.getParameter("lastname"));
        obj.setAddress(request.getParameter("address"));
        obj.setEmail(request.getParameter("email"));
        obj.setPhone(request.getParameter("phone"));
        obj.setCourseSession(CourseSessionRepository.getById(parseInt(request.getParameter("course_session"))));

        obj = ClientRepository.save(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientEntity obj = ClientRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)));
        ClientRepository.delete(obj);
    }
}