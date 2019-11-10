package repository;

import entity.CourseSessionEntity;

import java.util.List;

public class CourseSessionRepository extends BaseRepository {
    public static CourseSessionEntity getById(int id) {
        return BaseRepository.getById(id, CourseSessionEntity.class);
    }

    public static List<CourseSessionEntity> getAll() {
        return BaseRepository.getAll(CourseSessionEntity.class);
    }

    public static CourseSessionEntity create(CourseSessionEntity obj) {
        return BaseRepository.create(obj);
    }

    public static CourseSessionEntity save(CourseSessionEntity obj) {
        return BaseRepository.save(obj);
    }

    public static void delete(CourseSessionEntity obj) {
        BaseRepository.delete(obj);
    }
}
