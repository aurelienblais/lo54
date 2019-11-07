package repository;

import com.google.gson.Gson;
import entity.CourseEntity;
import org.hibernate.Session;
import util.RedisProvider;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseRepository {
    public static CourseEntity getById(String id) {
        if (RedisProvider.getSession().exists("course/" + id)) {
            return new Gson().fromJson(RedisProvider.getSession().get("course/" + id), CourseEntity.class);
        } else {
            CourseEntity entity = SessionProvider.getSession().get(CourseEntity.class, id);
            RedisProvider.getSession().set("course/" + id, new Gson().toJson(entity));
            return entity;
        }
    }

    public static List<CourseEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<CourseEntity> cq = cb.createQuery(CourseEntity.class);
        Root<CourseEntity> rootEntry = cq.from(CourseEntity.class);
        CriteriaQuery<CourseEntity> all = cq.select(rootEntry);

        TypedQuery<CourseEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    public static CourseEntity create(CourseEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("course/" + obj.getCode(), new Gson().toJson(obj));
        return obj;
    }

    public static CourseEntity save(CourseEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("course/" + obj.getCode(), new Gson().toJson(obj));
        return obj;
    }

    public static void delete(CourseEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().del("course/" + obj.getCode());
    }

}
