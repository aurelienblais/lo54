package repository;

import entity.CourseEntity;
import entity.CourseSessionEntity;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseSessionRepository {
    public static CourseSessionEntity getById(int id) {
        return SessionProvider.getSession().get(CourseSessionEntity.class, id);
    }

    public static List<CourseSessionEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<CourseSessionEntity> cq = cb.createQuery(CourseSessionEntity.class);
        Root<CourseSessionEntity> rootEntry = cq.from(CourseSessionEntity.class);
        CriteriaQuery<CourseSessionEntity> all = cq.select(rootEntry);

        TypedQuery<CourseSessionEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
