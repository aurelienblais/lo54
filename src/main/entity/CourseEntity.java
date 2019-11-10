package entity;

import com.google.gson.annotations.JsonAdapter;
import serializer.CourseEntitySerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "course", schema = "public", catalog = "lo54")
@JsonAdapter(CourseEntitySerializer.class)
public class CourseEntity extends BaseEntity implements Serializable {
    private String code;
    private String title;
    private Collection<CourseSessionEntity> courseSessions;

    @Id
    @Column(name = "code", nullable = false, length = -1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // For compatibility with other entities
    @Override
    @Transient
    public String getId() { return getCode(); }

    @Basic
    @Column(name = "title", nullable = false, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return title != null ? title.equals(that.title) : that.title == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "course")
    public Collection<CourseSessionEntity> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(Collection<CourseSessionEntity> courseSessions) {
        this.courseSessions = courseSessions;
    }
}
