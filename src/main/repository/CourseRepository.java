package repository;

import entity.CourseEntity;
import entity.CourseSessionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionProvider;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseRepository extends BaseRepository {
    private Session session;
    private CriteriaBuilder cb;
    private CriteriaQuery<CourseEntity> cr;
    private Root<CourseEntity> root;
    private Join<CourseEntity, CourseSessionEntity> courseSessionJoin;
    private List<Predicate> predicates;

    public CourseRepository() {
        session = SessionProvider.getSession();
        cb = session.getCriteriaBuilder();
        cr = cb.createQuery(CourseEntity.class);
        root = cr.from(CourseEntity.class);
        courseSessionJoin = root.join("courseSessions");
        predicates = new ArrayList<>();
    }

    public static CourseEntity getById(String id) {
        return BaseRepository.getById(id, CourseEntity.class);
    }

    public void filterTitle(String title) {
        predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
    }

    public void filterCode(String code) {
        predicates.add(cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%"));
    }

    public void filterSessionDate(String date) throws ParseException {
        Date parsed_date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        predicates.add(
                cb.between(
                        courseSessionJoin.get("startDate"),
                        parsed_date,
                        new Date(parsed_date.getTime() + 86400000)
                )
        );
    }

    public List<CourseEntity> getAll() {
        if (predicates.size() > 0) {
            cr.select(root).where(predicates.toArray(new Predicate[0]));

            Query<CourseEntity> query = session.createQuery(cr);
            return query.getResultList();
        } else {
            return BaseRepository.getAll(CourseEntity.class);
        }
    }

    public static CourseEntity create(CourseEntity obj) {
        return BaseRepository.create(obj);
    }

    public static CourseEntity save(CourseEntity obj) {
        return BaseRepository.save(obj);
    }

    public static void delete(CourseEntity obj) {
        BaseRepository.delete(obj);
    }
}
