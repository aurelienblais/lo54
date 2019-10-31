package repository;

import entity.CourseEntity;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseRepository {
    public static CourseEntity getById(String id) {
        return SessionProvider.getSession().load(CourseEntity.class, id);
    }

    public static List<CourseEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<CourseEntity> cq = cb.createQuery(CourseEntity.class);
        Root<CourseEntity> rootEntry = cq.from(CourseEntity.class);
        CriteriaQuery<CourseEntity> all = cq.select(rootEntry);

        TypedQuery<CourseEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
