package repository;

import entity.ClientEntity;
import entity.CourseSessionEntity;
import redis.clients.jedis.Client;

import java.util.List;

public class ClientRepository extends BaseRepository {
    public static ClientEntity getById(int id) {
        return BaseRepository.getById(id, ClientEntity.class);
    }

    public static ClientEntity getByIdFromDb(int id) { return BaseRepository.getByIdFromDb(id, ClientEntity.class); }

    public static List<ClientEntity> getAll() {
        return BaseRepository.getAll(ClientEntity.class);
    }

    public static ClientEntity create(ClientEntity obj) {
        BaseRepository.delRedis(obj.getCourseSession());
        BaseRepository.delRedis(obj.getCourseSession().getCourse());
        return BaseRepository.create(obj);
    }

    public static ClientEntity save(ClientEntity obj) {
        BaseRepository.delRedis(obj.getCourseSession());
        BaseRepository.delRedis(obj.getCourseSession().getCourse());
        return BaseRepository.save(obj);
    }

    public static void delete(ClientEntity obj) {
        BaseRepository.delRedis(obj.getCourseSession());
        BaseRepository.delRedis(obj.getCourseSession().getCourse());
        BaseRepository.delete(obj);
    }
}
