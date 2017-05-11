package com.consoft.university.repository;

import com.consoft.university.domain.StudyGroup;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StudyGroup entity.
 */
@SuppressWarnings("unused")
public interface StudyGroupRepository extends JpaRepository<StudyGroup,Long> {

}
