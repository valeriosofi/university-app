package com.consoft.university.service.impl;

import com.consoft.university.service.StudyGroupService;
import com.consoft.university.domain.StudyGroup;
import com.consoft.university.repository.StudyGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing StudyGroup.
 */
@Service
@Transactional
public class StudyGroupServiceImpl implements StudyGroupService{

    private final Logger log = LoggerFactory.getLogger(StudyGroupServiceImpl.class);
    
    private final StudyGroupRepository studyGroupRepository;

    public StudyGroupServiceImpl(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }

    /**
     * Save a studyGroup.
     *
     * @param studyGroup the entity to save
     * @return the persisted entity
     */
    @Override
    public StudyGroup save(StudyGroup studyGroup) {
        log.debug("Request to save StudyGroup : {}", studyGroup);
        StudyGroup result = studyGroupRepository.save(studyGroup);
        return result;
    }

    /**
     *  Get all the studyGroups.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudyGroup> findAll() {
        log.debug("Request to get all StudyGroups");
        List<StudyGroup> result = studyGroupRepository.findAll();

        return result;
    }

    /**
     *  Get one studyGroup by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StudyGroup findOne(Long id) {
        log.debug("Request to get StudyGroup : {}", id);
        StudyGroup studyGroup = studyGroupRepository.findOne(id);
        return studyGroup;
    }

    /**
     *  Delete the  studyGroup by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudyGroup : {}", id);
        studyGroupRepository.delete(id);
    }
}
