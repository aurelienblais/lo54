package dao;

import com.google.gson.annotations.JsonAdapter;
import serializer.CourseDAOSerializer;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "course", schema = "public", catalog = "lo54")
@JsonAdapter(CourseDAOSerializer.class)
public class CourseDAO {
    private String code;
    private String title;
    private Collection<CourseSessionDAO> courseSessions;

    @Id
    @Column(name = "code", nullable = false, length = -1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

        CourseDAO that = (CourseDAO) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "course")
    public Collection<CourseSessionDAO> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(Collection<CourseSessionDAO> courseSessions) {
        this.courseSessions = courseSessions;
    }
}
