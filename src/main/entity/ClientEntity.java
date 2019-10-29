package entity;

import dao.ClientDAO;
import dao.CourseDAO;
import util.SessionProvider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientEntity {
    public static ClientDAO getById(String id) {
        return SessionProvider.getSession().load(ClientDAO.class, id);
    }

    public static List<ClientDAO> getAll() {
        CriteriaBuilder cb = SessionProvider.getSession().getCriteriaBuilder();
        CriteriaQuery<ClientDAO> cq = cb.createQuery(ClientDAO.class);
        Root<ClientDAO> rootEntry = cq.from(ClientDAO.class);
        CriteriaQuery<ClientDAO> all = cq.select(rootEntry);

        TypedQuery<ClientDAO> allQuery = SessionProvider.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
