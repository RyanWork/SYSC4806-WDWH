package SYSC4806.Model;

import SYSC4806.TestHelpers.InstanceTestClassListener;
import SYSC4806.Repository.CourseRepository;
import SYSC4806.TestHelpers.SpringInstanceTestClassRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the Course Model class to ensure that the learning outcomes and progams persist in the database.
 */
@RunWith(SpringInstanceTestClassRunner.class)
@ContextConfiguration
@EnableJpaRepositories(basePackages = "SYSC4806.Repository")
@ComponentScan(basePackages = "SYSC4806")
public class CourseTest implements InstanceTestClassListener {

    /**
     * Repository used to save data about categories
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * Instance of course to add to repository
     */
    private Course c = new Course(1, "TEST_COURSE_NAME", "TEST_COURSE_CODE");

    /**
     * Learning outcome to be used for tests
     */
    private LearningOutcome lo = new LearningOutcome();

    /**
     * Program to be used for tests
     */
    private Program p = new Program();


    /**
     * Add the test category to the repository
     */
    @Override
    public void beforeClassSetup() {
        c.addLO(lo);
        c.addProgram(p);
        courseRepository.save(c);
    }

    /**
     * Delete the test category from the repository
     */
    @Override
    public void afterClassSetup() {
        courseRepository.delete(c);
    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(courseRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Course course = courseRepository.findByName("TEST_COURSE_NAME");

        assertNotNull(course);
        assertEquals(course.getName(), c.getName());
        assertEquals(course.getCode(), c.getCode());
    }

    @Test
    @Transactional
    public void testFindByCode(){
        Course course = courseRepository.findByCode("TEST_COURSE_CODE");

        assertNotNull(course);
        assertEquals(course.getName(), c.getName());
        assertEquals(course.getCode(), c.getCode());
    }

    @Test
    @Transactional
    public void testFindByYear(){
        List<Course> course = courseRepository.findByYear(1);

        assertNotNull(course);
        assertFalse(course.isEmpty());
    }

    @Test
    @Transactional
    public void testNumberOfLearningOutcomes(){
        Course course = courseRepository.findByName("TEST_COURSE_NAME");

        assertNotNull(course);
        assertNotNull(course.getLearningOutcomes());
        assertFalse(course.getLearningOutcomes().isEmpty());
    }

    @Test
    @Transactional
    public void testNumberOfPrograms(){
        Course course = courseRepository.findByName("TEST_COURSE_NAME");

        assertNotNull(course);
        assertNotNull(course.getPrograms());
        assertTrue(course.getPrograms().isEmpty());
    }
}