package com.consoft.university.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.consoft.university.domain.StudyGroup;
import com.consoft.university.service.StudyGroupService;
import com.consoft.university.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StudyGroup.
 */
@RestController
@RequestMapping("/api")
public class StudyGroupResource {

    private final Logger log = LoggerFactory.getLogger(StudyGroupResource.class);

    private static final String ENTITY_NAME = "studyGroup";
        
    private final StudyGroupService studyGroupService;

    public StudyGroupResource(StudyGroupService studyGroupService) {
        this.studyGroupService = studyGroupService;
    }

    /**
     * POST  /study-groups : Create a new studyGroup.
     *
     * @param studyGroup the studyGroup to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studyGroup, or with status 400 (Bad Request) if the studyGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/study-groups")
    @Timed
    public ResponseEntity<StudyGroup> createStudyGroup(@Valid @RequestBody StudyGroup studyGroup) throws URISyntaxException {
        log.debug("REST request to save StudyGroup : {}", studyGroup);
        if (studyGroup.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new studyGroup cannot already have an ID")).body(null);
        }
        StudyGroup result = studyGroupService.save(studyGroup);
        return ResponseEntity.created(new URI("/api/study-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /study-groups : Updates an existing studyGroup.
     *
     * @param studyGroup the studyGroup to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studyGroup,
     * or with status 400 (Bad Request) if the studyGroup is not valid,
     * or with status 500 (Internal Server Error) if the studyGroup couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/study-groups")
    @Timed
    public ResponseEntity<StudyGroup> updateStudyGroup(@Valid @RequestBody StudyGroup studyGroup) throws URISyntaxException {
        log.debug("REST request to update StudyGroup : {}", studyGroup);
        if (studyGroup.getId() == null) {
            return createStudyGroup(studyGroup);
        }
        StudyGroup result = studyGroupService.save(studyGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studyGroup.getId().toString()))
            .body(result);
    }

    /**
     * GET  /study-groups : get all the studyGroups.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of studyGroups in body
     */
    @GetMapping("/study-groups")
    @Timed
    public List<StudyGroup> getAllStudyGroups() {
        log.debug("REST request to get all StudyGroups");
        return studyGroupService.findAll();
    }

    /**
     * GET  /study-groups/:id : get the "id" studyGroup.
     *
     * @param id the id of the studyGroup to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studyGroup, or with status 404 (Not Found)
     */
    @GetMapping("/study-groups/{id}")
    @Timed
    public ResponseEntity<StudyGroup> getStudyGroup(@PathVariable Long id) {
        log.debug("REST request to get StudyGroup : {}", id);
        StudyGroup studyGroup = studyGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(studyGroup));
    }

    /**
     * DELETE  /study-groups/:id : delete the "id" studyGroup.
     *
     * @param id the id of the studyGroup to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/study-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudyGroup(@PathVariable Long id) {
        log.debug("REST request to delete StudyGroup : {}", id);
        studyGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
