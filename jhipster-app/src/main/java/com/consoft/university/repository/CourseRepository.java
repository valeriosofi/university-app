package com.consoft.university.repository;

import com.consoft.university.domain.Course;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import com.consoft.university.domain.Student;
        

/**
 * Spring Data JPA repository for the Course entity.
 */
@SuppressWarnings("unused")
public interface CourseRepository extends JpaRepository<Course,Long> {
    
    @Query("select distinct student from Student student left join fetch student.attends"
            + " where student.user.login = ?#{principal.username}")
    List<Student> findAllCoursesOfTheCurrentUser();
}
