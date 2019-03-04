package SYSC4806.Model;

import SYSC4806.Repository.CourseRepository;
import SYSC4806.Repository.LearningOutcomeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the LearningOutcomes Model class to ensure that the category and courses persist in the database.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningOutcomeTest {

    /**
     * Repository used to save data about learning outcomes
     */
    @Autowired
    private LearningOutcomeRepository learningOutcomeRepository;

    /**
     * Setting up dummy information for learningoutcomes
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Deleting the saved information for learningoutcomes
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(learningOutcomeRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        LearningOutcome lo = learningOutcomeRepository.findByName("Web Application Coding");
        assertEquals(lo.getName(), "Web Application Coding");
    }

    @Test
    @Transactional
    public void testNumberOfCourses(){
        LearningOutcome lo = learningOutcomeRepository.findByName("Web Application Coding");
        assertEquals(lo.getCourses().size(), 4);
    }
}