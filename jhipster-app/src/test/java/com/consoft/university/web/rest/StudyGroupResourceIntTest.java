package com.consoft.university.web.rest;

import com.consoft.university.UniversityApp;

import com.consoft.university.domain.StudyGroup;
import com.consoft.university.repository.StudyGroupRepository;
import com.consoft.university.service.StudyGroupService;
import com.consoft.university.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StudyGroupResource REST controller.
 *
 * @see StudyGroupResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniversityApp.class)
public class StudyGroupResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_MEMBERS = 0;
    private static final Integer UPDATED_NUM_MEMBERS = 1;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    private StudyGroupService studyGroupService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStudyGroupMockMvc;

    private StudyGroup studyGroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StudyGroupResource studyGroupResource = new StudyGroupResource(studyGroupService);
        this.restStudyGroupMockMvc = MockMvcBuilders.standaloneSetup(studyGroupResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudyGroup createEntity(EntityManager em) {
        StudyGroup studyGroup = new StudyGroup()
            .name(DEFAULT_NAME)
            .numMembers(DEFAULT_NUM_MEMBERS);
        return studyGroup;
    }

    @Before
    public void initTest() {
        studyGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudyGroup() throws Exception {
        int databaseSizeBeforeCreate = studyGroupRepository.findAll().size();

        // Create the StudyGroup
        restStudyGroupMockMvc.perform(post("/api/study-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studyGroup)))
            .andExpect(status().isCreated());

        // Validate the StudyGroup in the database
        List<StudyGroup> studyGroupList = studyGroupRepository.findAll();
        assertThat(studyGroupList).hasSize(databaseSizeBeforeCreate + 1);
        StudyGroup testStudyGroup = studyGroupList.get(studyGroupList.size() - 1);
        assertThat(testStudyGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStudyGroup.getNumMembers()).isEqualTo(DEFAULT_NUM_MEMBERS);
    }

    @Test
    @Transactional
    public void createStudyGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studyGroupRepository.findAll().size();

        // Create the StudyGroup with an existing ID
        studyGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudyGroupMockMvc.perform(post("/api/study-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studyGroup)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<StudyGroup> studyGroupList = studyGroupRepository.findAll();
        assertThat(studyGroupList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumMembersIsRequired() throws Exception {
        int databaseSizeBeforeTest = studyGroupRepository.findAll().size();
        // set the field null
        studyGroup.setNumMembers(null);

        // Create the StudyGroup, which fails.

        restStudyGroupMockMvc.perform(post("/api/study-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studyGroup)))
            .andExpect(status().isBadRequest());

        List<StudyGroup> studyGroupList = studyGroupRepository.findAll();
        assertThat(studyGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudyGroups() throws Exception {
        // Initialize the database
        studyGroupRepository.saveAndFlush(studyGroup);

        // Get all the studyGroupList
        restStudyGroupMockMvc.perform(get("/api/study-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studyGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].numMembers").value(hasItem(DEFAULT_NUM_MEMBERS)));
    }

    @Test
    @Transactional
    public void getStudyGroup() throws Exception {
        // Initialize the database
        studyGroupRepository.saveAndFlush(studyGroup);

        // Get the studyGroup
        restStudyGroupMockMvc.perform(get("/api/study-groups/{id}", studyGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(studyGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.numMembers").value(DEFAULT_NUM_MEMBERS));
    }

    @Test
    @Transactional
    public void getNonExistingStudyGroup() throws Exception {
        // Get the studyGroup
        restStudyGroupMockMvc.perform(get("/api/study-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudyGroup() throws Exception {
        // Initialize the database
        studyGroupService.save(studyGroup);

        int databaseSizeBeforeUpdate = studyGroupRepository.findAll().size();

        // Update the studyGroup
        StudyGroup updatedStudyGroup = studyGroupRepository.findOne(studyGroup.getId());
        updatedStudyGroup
            .name(UPDATED_NAME)
            .numMembers(UPDATED_NUM_MEMBERS);

        restStudyGroupMockMvc.perform(put("/api/study-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStudyGroup)))
            .andExpect(status().isOk());

        // Validate the StudyGroup in the database
        List<StudyGroup> studyGroupList = studyGroupRepository.findAll();
        assertThat(studyGroupList).hasSize(databaseSizeBeforeUpdate);
        StudyGroup testStudyGroup = studyGroupList.get(studyGroupList.size() - 1);
        assertThat(testStudyGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStudyGroup.getNumMembers()).isEqualTo(UPDATED_NUM_MEMBERS);
    }

    @Test
    @Transactional
    public void updateNonExistingStudyGroup() throws Exception {
        int databaseSizeBeforeUpdate = studyGroupRepository.findAll().size();

        // Create the StudyGroup

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStudyGroupMockMvc.perform(put("/api/study-groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(studyGroup)))
            .andExpect(status().isCreated());

        // Validate the StudyGroup in the database
        List<StudyGroup> studyGroupList = studyGroupRepository.findAll();
        assertThat(studyGroupList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStudyGroup() throws Exception {
        // Initialize the database
        studyGroupService.save(studyGroup);

        int databaseSizeBeforeDelete = studyGroupRepository.findAll().size();

        // Get the studyGroup
        restStudyGroupMockMvc.perform(delete("/api/study-groups/{id}", studyGroup.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StudyGroup> studyGroupList = studyGroupRepository.findAll();
        assertThat(studyGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudyGroup.class);
    }
}
