package com.consoft.university.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cfu")
    private Double cfu;

    @Column(name = "duration")
    private Double duration;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Course code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Course name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Course description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCfu() {
        return cfu;
    }

    public Course cfu(Double cfu) {
        this.cfu = cfu;
        return this;
    }

    public void setCfu(Double cfu) {
        this.cfu = cfu;
    }

    public Double getDuration() {
        return duration;
    }

    public Course duration(Double duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Course bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Course addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setCourse(this);
        return this;
    }

    public Course removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setCourse(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        if (course.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", code='" + code + "'" +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", cfu='" + cfu + "'" +
            ", duration='" + duration + "'" +
            '}';
    }
}
