package com.consoft.university.service;

import com.consoft.university.domain.StudyGroup;
import java.util.List;

/**
 * Service Interface for managing StudyGroup.
 */
public interface StudyGroupService {

    /**
     * Save a studyGroup.
     *
     * @param studyGroup the entity to save
     * @return the persisted entity
     */
    StudyGroup save(StudyGroup studyGroup);

    /**
     *  Get all the studyGroups.
     *  
     *  @return the list of entities
     */
    List<StudyGroup> findAll();

    /**
     *  Get the "id" studyGroup.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    StudyGroup findOne(Long id);

    /**
     *  Delete the "id" studyGroup.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
