package servlet.api.client;

import com.google.gson.Gson;
import entity.ClientEntity;
import repository.ClientRepository;
import repository.CourseSessionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

@WebServlet("/api/clients")
public class Collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(ClientRepository.getAll()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        ClientEntity obj = new ClientEntity();
        obj.setFirstname(request.getParameter("firstname"));
        obj.setLastname(request.getParameter("lastname"));
        obj.setAddress(request.getParameter("address"));
        obj.setEmail(request.getParameter("email"));
        obj.setPhone(request.getParameter("phone"));
        obj.setCourseSession(CourseSessionRepository.getByIdFromDb(parseInt(request.getParameter("course_session"))));

        obj = ClientRepository.create(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }
}