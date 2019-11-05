import com.google.gson.Gson;
import entity.ClientEntity;
import entity.CourseEntity;
import entity.CourseSessionEntity;
import repository.ClientRepository;
import repository.CourseRepository;
import org.hibernate.Session;
import repository.CourseSessionRepository;
import util.RedisProvider;
import util.SessionProvider;

import java.util.List;

public class Main {
    public static void main(final String[] args) throws Exception {
        ClientEntity ce = new ClientEntity();
        ce.setEmail("TEST");
        ce.setAddress("TEST");
        ce.setFirstname("TEST");
        ce.setLastname("TEST");
        ce.setPhone("TEST");
        ce.setCourseSession(CourseSessionRepository.getById(2));
        System.out.println(ce.getId());
        ce = ClientRepository.create(ce);
        System.out.println(ce.getId());

        ce.setLastname("LOL");
        ce = ClientRepository.save(ce);

        ClientRepository.delete(ce);
    }
}