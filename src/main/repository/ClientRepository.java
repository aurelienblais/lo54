package repository;

import entity.ClientEntity;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientRepository {
    public static ClientEntity getById(String id) {
        return SessionProvider.getSession().load(ClientEntity.class, id);
    }

    public static List<ClientEntity> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<ClientEntity> cq = cb.createQuery(ClientEntity.class);
        Root<ClientEntity> rootEntry = cq.from(ClientEntity.class);
        CriteriaQuery<ClientEntity> all = cq.select(rootEntry);

        TypedQuery<ClientEntity> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
