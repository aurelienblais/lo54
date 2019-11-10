package servlet.api.course;

import com.google.gson.Gson;
import entity.CourseEntity;
import org.apache.maven.shared.utils.StringUtils;
import repository.CourseRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/api/courses")
public class Collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List<CourseEntity> res;
        CourseRepository cr = new CourseRepository();

        if(!StringUtils.isEmpty(request.getParameter("title"))) {
            cr.filterTitle(request.getParameter("title"));
        }

        if(!StringUtils.isEmpty(request.getParameter("code"))) {
            cr.filterCode(request.getParameter("code"));
        }

        res = cr.getAll();
        res.sort(Comparator.comparing(CourseEntity::getCode));

        out.println(gson.toJson(res));
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