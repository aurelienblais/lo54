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
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/api/course_sessions")
public class Collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List<CourseSessionEntity> res;
        CourseSessionRepository cr = new CourseSessionRepository();

        if (!StringUtils.isEmpty(request.getParameter("start_date"))) {
            try {
                cr.filterSessionDate(request.getParameter("start_date"));
            } catch (ParseException e) {
                System.out.println("Failed to parse date: " + request.getParameter("date"));
            }
        } else {
            cr.filterOutdated();
        }

        if (!StringUtils.isEmpty(request.getParameter("city"))) {
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
        Date start_date = null;
        Date end_date = null;
        try {
            start_date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.FRANCE).parse(request.getParameter("start_date"));
            end_date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.FRANCE).parse(request.getParameter("end_date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        obj.setStartDate(new Timestamp(start_date.getTime()));
        obj.setEndDate(new Timestamp(end_date.getTime()));
        obj.setMax(Integer.valueOf(request.getParameter("max")));
        obj.setCourse(CourseRepository.getById(request.getParameter("course_code")));
        obj.setLocation(LocationRepository.getById(Integer.valueOf(request.getParameter("location_id"))));


        obj = CourseSessionRepository.create(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }
}