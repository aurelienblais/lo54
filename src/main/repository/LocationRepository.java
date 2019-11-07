package repository;

import com.google.gson.Gson;
import entity.LocationEntity;
import org.hibernate.Session;
import util.RedisProvider;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LocationRepository {
    public static LocationEntity getById(int id) {
        if (RedisProvider.getSession().exists("location/" + id)) {
            return new Gson().fromJson(RedisProvider.getSession().get("location/" + id), LocationEntity.class);
        } else {
            LocationEntity entity = SessionProvider.getSession().get(LocationEntity.class, id);
            RedisProvider.getSession().set("location/" + id, new Gson().toJson(entity));
            return entity;
        }
    }

    public static List<LocationEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<LocationEntity> cq = cb.createQuery(LocationEntity.class);
        Root<LocationEntity> rootEntry = cq.from(LocationEntity.class);
        CriteriaQuery<LocationEntity> all = cq.select(rootEntry);

        TypedQuery<LocationEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    public static LocationEntity create(LocationEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("location/" + obj.getId(), new Gson().toJson(obj));
        return obj;
    }

    public static LocationEntity save(LocationEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("location/" + obj.getId(), new Gson().toJson(obj));
        return obj;
    }

    public static void delete(LocationEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().del("location/" + obj.getId());
    }
}
