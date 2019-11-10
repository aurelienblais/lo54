package repository;

import entity.ClientEntity;

import java.util.List;

public class ClientRepository extends BaseRepository {
    public static ClientEntity getById(int id) {
        return BaseRepository.getById(id, ClientEntity.class);
    }

    public static List<ClientEntity> getAll() {
        return BaseRepository.getAll(ClientEntity.class);
    }

    public static ClientEntity create(ClientEntity obj) {
        return BaseRepository.create(obj);
    }

    public static ClientEntity save(ClientEntity obj) {
        return BaseRepository.save(obj);
    }

    public static void delete(ClientEntity obj) {
        BaseRepository.delete(obj);
    }
}
