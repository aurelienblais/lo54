package entity;

import dao.CourseDAO;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseEntity {
    public static CourseDAO getById(String id) {
        return SessionProvider.getSession().load(CourseDAO.class, id);
    }

    public static List<CourseDAO> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<CourseDAO> cq = cb.createQuery(CourseDAO.class);
        Root<CourseDAO> rootEntry = cq.from(CourseDAO.class);
        CriteriaQuery<CourseDAO> all = cq.select(rootEntry);

        TypedQuery<CourseDAO> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
