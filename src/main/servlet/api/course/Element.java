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

@WebServlet("/api/courses/*")
public class Element extends BaseElement {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(CourseRepository.getById(request.getPathInfo().substring(1))));
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        CourseEntity obj = CourseRepository.getById(request.getPathInfo().substring(1));

        if(!StringUtils.isEmpty(request.getParameter("title")))
            obj.setTitle(request.getParameter("title"));

        obj = CourseRepository.save(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseEntity obj = CourseRepository.getById(request.getPathInfo().substring(1));
        CourseRepository.delete(obj);
    }
}