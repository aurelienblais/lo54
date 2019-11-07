package servlet.api.course_session;

import com.google.gson.Gson;
import entity.CourseSessionEntity;
import repository.CourseRepository;
import repository.CourseSessionRepository;
import repository.LocationRepository;
import servlet.api.BaseElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet("/api/course_sessions/*")
public class Element extends BaseElement {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(CourseSessionRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)))));
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        CourseSessionEntity obj = CourseSessionRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)));
        obj.setStartDate(Timestamp.valueOf(request.getParameter("start_date")));
        obj.setEndDate(Timestamp.valueOf(request.getParameter("end_date")));
        obj.setMax(Integer.valueOf(request.getParameter("max")));
        obj.setCourse(CourseRepository.getById(request.getParameter("course_code")));
        obj.setLocation(LocationRepository.getById(Integer.valueOf(request.getParameter("location_id"))));

        obj = CourseSessionRepository.save(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseSessionEntity obj = CourseSessionRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)));
        CourseSessionRepository.delete(obj);
    }
}