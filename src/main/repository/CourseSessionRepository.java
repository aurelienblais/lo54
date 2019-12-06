package repository;

import entity.CourseEntity;
import entity.CourseSessionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionProvider;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseSessionRepository extends BaseRepository {
    private Session session;
    private CriteriaBuilder cb;
    private CriteriaQuery<CourseSessionEntity> cr;
    private Root<CourseSessionEntity> root;
    private Join<CourseSessionEntity, CourseEntity> courseJoin;

    private List<Predicate> predicates;

    public CourseSessionRepository() {
        session = SessionProvider.getSession();
        cb = session.getCriteriaBuilder();
        cr = cb.createQuery(CourseSessionEntity.class);
        root = cr.from(CourseSessionEntity.class);
        courseJoin = root.join("course");
        predicates = new ArrayList<>();
    }

    public static CourseSessionEntity getById(int id) {
        return BaseRepository.getById(id, CourseSessionEntity.class);
    }

    public static CourseSessionEntity getByIdFromDb(int id) {
        return BaseRepository.getByIdFromDb(id, CourseSessionEntity.class);
    }

    public void filterOutdated() {
        Date today = new Date();
        System.out.println(today.getTime());
        predicates.add(
                cb.between(
                        root.get("startDate"),
                        today,
                        new Date(today.getTime() + 31449600000L * 10)
                )
        );
    }

    public void filterSessionDate(String date) throws ParseException {
        Date parsed_date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        predicates.add(
                cb.between(
                        root.get("startDate"),
                        parsed_date,
                        new Date(parsed_date.getTime() + 86400000)
                )
        );
    }

    public void filterCity(int id) {
        predicates.add(cb.equal(root.get("location"), id));
    }

    public List<CourseSessionEntity> getAll() {
        if (predicates.size() > 0) {
            cr.select(root).where(predicates.toArray(new Predicate[0]));

            Query<CourseSessionEntity> query = session.createQuery(cr);
            return query.getResultList();
        } else {
            return BaseRepository.getAll(CourseSessionEntity.class);
        }
    }

    public static CourseSessionEntity create(CourseSessionEntity obj) {
        BaseRepository.delRedis(obj.getCourse());
        return BaseRepository.create(obj);
    }

    public static CourseSessionEntity save(CourseSessionEntity obj) {
        BaseRepository.delRedis(obj.getCourse());
        obj.getClients().forEach(BaseRepository::delRedis);
        return BaseRepository.save(obj);
    }

    public static void delete(CourseSessionEntity obj) {
        BaseRepository.delRedis(obj.getCourse());
        obj.getClients().forEach(BaseRepository::delRedis);
        BaseRepository.delete(obj);
    }
}
