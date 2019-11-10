package repository;

import entity.LocationEntity;

import java.util.List;

public class LocationRepository extends BaseRepository {
    public static LocationEntity getById(int id) { return BaseRepository.getById(id, LocationEntity.class); }

    public static List<LocationEntity> getAll() { return BaseRepository.getAll(LocationEntity.class); }

    public static LocationEntity create(LocationEntity obj) { return BaseRepository.create(obj); }

    public static LocationEntity save(LocationEntity obj) { return BaseRepository.save(obj); }

    public static void delete(LocationEntity obj) { BaseRepository.delete(obj); }
}
