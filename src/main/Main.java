import com.google.gson.Gson;
import entity.ClientEntity;
import entity.CourseEntity;
import repository.ClientRepository;
import repository.CourseRepository;
import org.hibernate.Session;
import util.RedisProvider;
import util.SessionProvider;

public class Main {
    public static void main(final String[] args) throws Exception {
        final Session session = SessionProvider.getSession();
        try {
            ClientEntity ce = ClientRepository.getById(3);
            System.out.println(ce.getId());

            ClientEntity ce2 = ClientRepository.getById(3);
            System.out.println(ce2.getId());
        } finally {
            session.close();
        }
    }
}