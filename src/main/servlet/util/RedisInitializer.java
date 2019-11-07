package servlet.util;

import com.google.gson.Gson;
import entity.ClientEntity;
import entity.CourseEntity;
import entity.CourseSessionEntity;
import entity.LocationEntity;
import repository.ClientRepository;
import repository.CourseRepository;
import repository.CourseSessionRepository;
import repository.LocationRepository;
import util.RedisProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class RedisInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //Notification that the servlet context is about to be shut down.
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // We use Postgres for persistence, but we need to ensure Redis is sync with it
        System.out.println("--------------------");
        System.out.println("Load Data into Redis");
        System.out.println("--------------------");

        List<ClientEntity> clients = ClientRepository.getAll();
        clients.forEach((e) -> RedisProvider.getSession().set("client/" + e.getId(), new Gson().toJson(e)));

        List<CourseEntity> courses = CourseRepository.getAll();
        courses.forEach((e) -> RedisProvider.getSession().set("course/" + e.getCode(), new Gson().toJson(e)));

        List<CourseSessionEntity> courseSessions = CourseSessionRepository.getAll();
        courseSessions.forEach((e) -> RedisProvider.getSession().set("course_session/" + e.getId(), new Gson().toJson(e)));

        List<LocationEntity> locations = LocationRepository.getAll();
        locations.forEach((e) -> RedisProvider.getSession().set("location/" + e.getId(), new Gson().toJson(e)));

        System.out.println("--------------------");
        System.out.println("    Data  Loaded    ");
        System.out.println("--------------------");

    }

}
