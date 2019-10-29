package dao;

import com.google.gson.annotations.JsonAdapter;
import serializer.CourseDAOSerializer;
import serializer.CourseSessionDAOSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "course_session", schema = "public", catalog = "lo54")
@JsonAdapter(CourseSessionDAOSerializer.class)
public class CourseSessionDAO {
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer max;
    private CourseDAO course;
    private Collection<ClientDAO> clients;
    private LocationDAO location;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "max", nullable = true)
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseSessionDAO that = (CourseSessionDAO) o;

        if (id != that.id) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (max != null ? !max.equals(that.max) : that.max != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code", nullable = false)
    public CourseDAO getCourse() {
        return course;
    }

    public void setCourse(CourseDAO course) {
        this.course = course;
    }

    @OneToMany(mappedBy = "courseSession")
    public Collection<ClientDAO> getClients() {
        return clients;
    }

    public void setClients(Collection<ClientDAO> clients) {
        this.clients = clients;
    }

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    public LocationDAO getLocation() {
        return location;
    }

    public void setLocation(LocationDAO location) {
        this.location = location;
    }
}
