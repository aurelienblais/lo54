package servlet.api.location;

import com.google.gson.Gson;
import entity.LocationEntity;
import repository.LocationRepository;
import servlet.api.BaseElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/locations/*")
public class Element extends BaseElement {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(LocationRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)))));
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        LocationEntity obj = LocationRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)));
        obj.setCity(request.getParameter("city"));

        obj = LocationRepository.save(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocationEntity obj = LocationRepository.getById(Integer.parseInt(request.getPathInfo().substring(1)));
        LocationRepository.delete(obj);
    }
}