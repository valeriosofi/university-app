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
 * A StudyGroup.
 */
@Entity
@Table(name = "study_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudyGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Min(value = 0)
    @Max(value = 30)
    @Column(name = "num_members", nullable = false)
    private Integer numMembers;

    @OneToMany(mappedBy = "studyGroup")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "studyGroup")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public StudyGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumMembers() {
        return numMembers;
    }

    public StudyGroup numMembers(Integer numMembers) {
        this.numMembers = numMembers;
        return this;
    }

    public void setNumMembers(Integer numMembers) {
        this.numMembers = numMembers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public StudyGroup students(Set<Student> students) {
        this.students = students;
        return this;
    }

    public StudyGroup addStudent(Student student) {
        this.students.add(student);
        student.setStudyGroup(this);
        return this;
    }

    public StudyGroup removeStudent(Student student) {
        this.students.remove(student);
        student.setStudyGroup(null);
        return this;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public StudyGroup bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public StudyGroup addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setStudyGroup(this);
        return this;
    }

    public StudyGroup removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setStudyGroup(null);
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
        StudyGroup studyGroup = (StudyGroup) o;
        if (studyGroup.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, studyGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", numMembers='" + numMembers + "'" +
            '}';
    }
}
