package SYSC4806.Model;

import SYSC4806.Repository.CourseRepository;
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
 * Tests for the Course Model class to ensure that the learning outcomes and progams persist in the database.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {

    /**
     * Repository used to save data about categories
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * Setting up dummy information for courses
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Deleting the saved information for courses
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(courseRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Course course = courseRepository.findByName("Software Engineering Lab");
        assertEquals(course.getName(), "Software Engineering Lab");
        assertEquals(course.getCode(), "4806");
    }

    @Test
    @Transactional
    public void testFindByCode(){
        Course course = courseRepository.findByCode("4806");
        assertEquals(course.getCode(), "4806");
        assertEquals(course.getName(), "Software Engineering Lab");
    }

    @Test
    @Transactional
    public void testFindByYear(){
        List<Course> course = courseRepository.findByYear(3);
        assertEquals(course.size(), 2);
    }

    @Test
    @Transactional
    public void testNumberOfLearningOutcomes(){
        Course course = courseRepository.findByName("Software Engineering Lab");
        assertEquals(course.getLearningOutcomes().size(), 1);
    }

    @Test
    @Transactional
    public void testNumberOfPrograms(){
        Course course = courseRepository.findByName("Software Engineering Lab");
        assertEquals(course.getPrograms().size(), 1);
    }
}