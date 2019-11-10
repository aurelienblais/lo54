package repository;

import entity.CourseEntity;

import java.util.List;

public class CourseRepository extends BaseRepository {
    public static CourseEntity getById(String id) {
        return BaseRepository.getById(id, CourseEntity.class);
    }

    public static List<CourseEntity> getAll() {
        return BaseRepository.getAll(CourseEntity.class);
    }

    public static CourseEntity create(CourseEntity obj) {
        return BaseRepository.create(obj);
    }

    public static CourseEntity save(CourseEntity obj) {
        return BaseRepository.save(obj);
    }

    public static void delete(CourseEntity obj) {
        BaseRepository.delete(obj);
    }
}
