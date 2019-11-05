package entity;

import com.google.gson.annotations.JsonAdapter;
import serializer.LocationEntitySerializer;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "location", schema = "public", catalog = "lo54")
@JsonAdapter(LocationEntitySerializer.class)
public class LocationEntity {
    private int id;
    private String city;
    private Collection<CourseSessionEntity> courseSessions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
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
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "location")
    public Collection<CourseSessionEntity> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(Collection<CourseSessionEntity> courseSessions) {
        this.courseSessions = courseSessions;
    }
}
