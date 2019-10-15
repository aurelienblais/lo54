import entity.ClientEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            ClientEntity c = session.load(ClientEntity.class,3);
            System.out.println(c.getEmail());
            System.out.println(c.getCourseSession().getStartDate());
            System.out.println(c.getCourseSession().getCourse().getTitle());
            System.out.println(c.getCourseSession().getLocation().getCity());
        } finally {
            session.close();
        }
    }
}