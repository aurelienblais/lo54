package servlet.util;

import com.google.gson.Gson;
import entity.ClientEntity;
import entity.CourseEntity;
import entity.CourseSessionEntity;
import entity.LocationEntity;
import repository.*;
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

        BaseRepository.getAllFromDB(ClientEntity.class).forEach(BaseRepository::toRedis);
        BaseRepository.getAllFromDB(CourseEntity.class).forEach(BaseRepository::toRedis);
        BaseRepository.getAllFromDB(CourseSessionEntity.class).forEach(BaseRepository::toRedis);
        BaseRepository.getAllFromDB(LocationEntity.class).forEach(BaseRepository::toRedis);

        System.out.println("--------------------");
        System.out.println("    Data  Loaded    ");
        System.out.println("--------------------");

    }

}
