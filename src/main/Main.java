import repository.CourseRepository;
import org.hibernate.Session;
import util.SessionProvider;

public class Main {
    public static void main(final String[] args) throws Exception {
        final Session session = SessionProvider.getSession();
        try {
            System.out.println(CourseRepository.getById("1B").getTitle());
        } finally {
            session.close();
        }
    }
}