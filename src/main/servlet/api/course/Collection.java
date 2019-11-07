package servlet.api.course;

import com.google.gson.Gson;
import entity.CourseEntity;
import repository.CourseRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/courses")
public class Collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(CourseRepository.getAll()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        CourseEntity obj = new CourseEntity();
        obj.setCode(request.getParameter("code"));
        obj.setTitle(request.getParameter("title"));

        obj = CourseRepository.create(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }
}