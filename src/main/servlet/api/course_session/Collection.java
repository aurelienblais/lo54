package servlet.api.course_session;

import com.google.gson.Gson;
import entity.CourseSessionEntity;
import org.apache.maven.shared.utils.StringUtils;
import repository.CourseRepository;
import repository.CourseSessionRepository;
import repository.LocationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/api/course_sessions")
public class Collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List<CourseSessionEntity> res;
        CourseSessionRepository cr = new CourseSessionRepository();

        if (!StringUtils.isEmpty(request.getParameter("start_Date"))) {
            try {
                cr.filterSessionDate(request.getParameter("start_date"));
            } catch (ParseException e) {
                System.out.println("Failed to parse date: " + request.getParameter("date"));
            }
        }

        if(!StringUtils.isEmpty(request.getParameter("city"))) {
            cr.filterCity(Integer.parseInt(request.getParameter("city")));
        }

        res = cr.getAll();
        res.sort(Comparator.comparing((CourseSessionEntity cs) -> (cs.getCourse().getCode())));

        out.println(gson.toJson(res));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        CourseSessionEntity obj = new CourseSessionEntity();
        obj.setStartDate(Timestamp.valueOf(request.getParameter("start_date")));
        obj.setEndDate(Timestamp.valueOf(request.getParameter("end_date")));
        obj.setMax(Integer.valueOf(request.getParameter("max")));
        obj.setCourse(CourseRepository.getById(request.getParameter("course_code")));
        obj.setLocation(LocationRepository.getById(Integer.valueOf(request.getParameter("location_id"))));


        obj = CourseSessionRepository.create(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }
}