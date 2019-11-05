package servlet.util;

import com.google.gson.Gson;
import entity.ClientEntity;
import repository.ClientRepository;
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
        System.out.println("Load Data into Redis");
        List<ClientEntity> clients = ClientRepository.getAll();
        clients.forEach((e) -> RedisProvider.getSession().set("client/" + e.getId(), new Gson().toJson(e)));
    }

}
