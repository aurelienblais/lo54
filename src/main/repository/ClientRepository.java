package repository;

import com.google.gson.Gson;
import entity.ClientEntity;
import org.hibernate.Session;
import util.RedisProvider;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientRepository {
    public static ClientEntity getById(int id) {
        if (RedisProvider.getSession().exists("client/" + id)) {
            return new Gson().fromJson(RedisProvider.getSession().get("client/" + id), ClientEntity.class);
        } else {
            ClientEntity entity = SessionProvider.getSession().get(ClientEntity.class, id);
            RedisProvider.getSession().set("client/" + id, new Gson().toJson(entity));
            return entity;
        }
    }

    public static List<ClientEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
        Root<ClientEntity> rootEntry = cq.from(ClientEntity.class);
        CriteriaQuery<ClientEntity> all = cq.select(rootEntry);

        TypedQuery<ClientEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    public static ClientEntity create(ClientEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("client/" + obj.getId(), new Gson().toJson(obj));
        return obj;
    }

    public static ClientEntity save(ClientEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().set("client/" + obj.getId(), new Gson().toJson(obj));
        return obj;
    }

    public static void delete(ClientEntity obj) {
        Session session = SessionProvider.getSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        RedisProvider.getSession().del("client/" + obj.getId());
    }
}
