package servlet.api.location;

import com.google.gson.Gson;
import entity.LocationEntity;
import repository.LocationRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/locations")
public class Collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.println(gson.toJson(LocationRepository.getAll()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        LocationEntity obj = new LocationEntity();
        obj.setCity(request.getParameter("city"));

        obj = LocationRepository.create(obj);
        Gson gson = new Gson();
        out.println(gson.toJson(obj));
    }
}