package repository;

import com.google.gson.Gson;
import entity.BaseEntity;
import org.hibernate.Session;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import util.RedisProvider;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static redis.clients.jedis.ScanParams.SCAN_POINTER_START;

public abstract class BaseRepository {
    static <T extends BaseEntity> T getById(Serializable id, Class target) {
        if (RedisProvider.getSession().exists(target.getSimpleName() + "/" + id)) {
            return fromRedis(target.getSimpleName() + "/" + id, target);
        } else {
            Session session = SessionProvider.getSession();
            T entity = (T) session.get(target, id);
            toRedis(entity);
            session.close();
            return entity;
        }
    }

    static <T extends BaseEntity> List<T> getAll(Class target) {
        List<T> list = new ArrayList<>();
        ScanParams scanParams = new ScanParams().count(100).match(target.getSimpleName() + "/*");
        String cur = SCAN_POINTER_START;
        do {
            ScanResult<String> scanResult = RedisProvider.getSession().scan(cur, scanParams);
            scanResult.getResult().forEach((k) -> list.add(fromRedis(k, target)));
            cur = scanResult.getCursor();
        } while (!cur.equals(SCAN_POINTER_START));
        return list;
    }

    public static <T extends BaseEntity> List<T> getAllFromDB(Class target) {
        Session session = SessionProvider.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(target);
        Root<T> rootEntry = cq.from(target);
        CriteriaQuery<T> all = cq.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    static <T extends BaseEntity> T create(BaseEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        toRedis(obj);
        return (T) obj;
    }

    static <T extends BaseEntity> T save(BaseEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        toRedis(obj);
        return (T) obj;
    }

    static void delete(BaseEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().del(obj.getClass().getSimpleName() + "/" + obj.getId());
    }

    static <T extends BaseEntity> T fromRedis(String k, Class target) {
        return new Gson().fromJson(RedisProvider.getSession().get(k), (Type) target);
    }

    public static void toRedis(BaseEntity obj) {
        RedisProvider.getSession().set(obj.getClass().getSimpleName() + "/" + obj.getId(), new Gson().toJson(obj));
    }
}
