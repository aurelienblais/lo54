package servlet.api.course;

import com.google.gson.Gson;
import entity.CourseEntity;
import org.apache.maven.shared.utils.StringUtils;
import repository.CourseRepository;
import servlet.api.BaseElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/api/courses/*")
public class Element extends BaseElement {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(CourseRepository.getById(request.getPathInfo().substring(1))));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseEntity obj = CourseRepository.getById(request.getPathInfo().substring(1));
        CourseRepository.delete(obj);
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        CourseEntity obj = CourseRepository.getById(request.getPathInfo().substring(1));

        Map<String, String> data = gson.fromJson(request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual), Map.class);

        if(!StringUtils.isEmpty(data.get("title")))
            obj.setTitle(data.get("title"));

        obj = CourseRepository.save(obj);

        out.println(gson.toJson(obj));
    }
}