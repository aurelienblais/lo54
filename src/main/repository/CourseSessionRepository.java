package repository;

import com.google.gson.Gson;
import entity.CourseSessionEntity;
import org.hibernate.Session;
import util.RedisProvider;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseSessionRepository {
    public static CourseSessionEntity getById(int id) {
        if (RedisProvider.getSession().exists("course_session/" + id)) {
            return new Gson().fromJson(RedisProvider.getSession().get("course_session/" + id), CourseSessionEntity.class);
        } else {
            CourseSessionEntity entity = SessionProvider.getSession().get(CourseSessionEntity.class, id);
            RedisProvider.getSession().set("course_session/" + id, new Gson().toJson(entity));
            return entity;
        }
    }

    public static List<CourseSessionEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<CourseSessionEntity> cq = cb.createQuery(CourseSessionEntity.class);
        Root<CourseSessionEntity> rootEntry = cq.from(CourseSessionEntity.class);
        CriteriaQuery<CourseSessionEntity> all = cq.select(rootEntry);

        TypedQuery<CourseSessionEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    public static CourseSessionEntity create(CourseSessionEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("course_session/" + obj.getId(), new Gson().toJson(obj));
        return obj;
    }

    public static CourseSessionEntity save(CourseSessionEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("course_session/" + obj.getId(), new Gson().toJson(obj));
        return obj;
    }

    public static void delete(CourseSessionEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().del("course_session/" + obj.getId());
    }
}
