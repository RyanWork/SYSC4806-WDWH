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

        List<LearningOutcome> lo = new ArrayList<>();
        List<LearningOutcome> lo2 = new ArrayList<>();
        List<LearningOutcome> lo3 = new ArrayList<>();

        lo.add(new LearningOutcome());
        lo.add(new LearningOutcome());
        lo.add(new LearningOutcome());
        lo.add(new LearningOutcome());
        lo2.add(new LearningOutcome());
        lo2.add(new LearningOutcome());
        lo3.add(new LearningOutcome());

        List<Program> p1 = new ArrayList<>();
        List<Program> p2 = new ArrayList<>();
        List<Program> p3 = new ArrayList<>();

        p1.add(new Program());
        p1.add(new Program());
        p2.add(new Program());
        p3.add(new Program());

        Course c1 = new Course(2010, "OS", "4806", lo, p1);
        Course c2 = new Course(2018, "MATH", "4507", lo2, p2);
        Course c3 = new Course(2018, "ELEC", "4502", lo3, p2);

        courseRepository.save(c1);
        courseRepository.save(c2);
        courseRepository.save(c3);
    }

    /**
     * Deleting the saved information for courses
     */
    @After
    public void tearDown() throws Exception {
        courseRepository.deleteAll();
    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(courseRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Course course = courseRepository.findByName("OS");
        assertEquals(course.getName(), "OS");
        assertEquals(course.getCode(), "4806");
    }

    @Test
    @Transactional
    public void testFindByCode(){
        Course course = courseRepository.findByCode("4507");
        assertEquals(course.getCode(), "4507");
        assertEquals(course.getName(), "MATH");
    }

    @Test
    @Transactional
    public void testFindByYear(){
        List<Course> course = courseRepository.findByYear(2018);
        assertEquals(course.size(), 2);
    }

    @Test
    @Transactional
    public void testNumberOfLearningOutcomes(){
        Course course = courseRepository.findByName("ELEC");
        assertEquals(course.getLearningOutcomes().size(), 1);
    }

    @Test
    @Transactional
    public void testNumberOfPrograms(){
        Course course = courseRepository.findByName("OS");
        assertEquals(course.getPrograms().size(), 2);
    }
}