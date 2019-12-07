package entity;

import com.google.gson.annotations.JsonAdapter;
import serializer.LocationEntitySerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "location", schema = "public", catalog = "lo54")
@JsonAdapter(LocationEntitySerializer.class)
public class LocationEntity extends BaseEntity implements Serializable {
    private int id;
    private String city;
    private Collection<CourseSessionEntity> courseSessions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city", nullable = false, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (id != that.id) return false;
        return city != null ? city.equals(that.city) : that.city == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "location", cascade=CascadeType.REMOVE)
    public Collection<CourseSessionEntity> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(Collection<CourseSessionEntity> courseSessions) {
        this.courseSessions = courseSessions;
    }
}
