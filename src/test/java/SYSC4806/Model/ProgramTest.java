package SYSC4806.Model;

import SYSC4806.TestHelpers.InstanceTestClassListener;
import SYSC4806.Repository.ProgramRepository;
import SYSC4806.TestHelpers.SpringInstanceTestClassRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.runner.RunWith;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests for the Program Model class to ensure that the courses persist in the database.
 */
@RunWith(SpringInstanceTestClassRunner.class)
@ContextConfiguration
@EnableJpaRepositories(basePackages = "SYSC4806.Repository")
@ComponentScan(basePackages = "SYSC4806")
public class ProgramTest implements InstanceTestClassListener {

    /**
     * Repository used to save data about programs
     */
    @Autowired
    private ProgramRepository programRepository;

    /**
     * Program to be added to repository
     */
    private Program p = new Program("TEST_PROGRAM");

    /**
     * Test course to be used for tests
     */
    private Course c = new Course(1, "TEST_PROGRAM_COURSE_NAME", "TEST_PROGRAM_COURSE_CODE");

    /**
     * Validator use to validate constraints on Program attributes
     */
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Setting up dummy information for programs
     * @throws Exception
     */
    @Override
    public void beforeClassSetup() {
        p.addCourse(c);
        programRepository.save(p);
    }

    /**
     * Deleting the saved information for programs
     */
    @Override
    public void afterClassSetup() {
        programRepository.delete(p);
    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(programRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Program prog1 = programRepository.findByName("TEST_PROGRAM");

        assertNotNull(prog1);
        assertEquals(prog1.getName(), p.getName());
    }

    @Test
    @Transactional
    public void testNumberOfCourses(){
        Program prog1 = programRepository.findByName("TEST_PROGRAM");

        assertNotNull(prog1);
        assertNotNull(prog1.getCourses());
        assertFalse(prog1.getCourses().isEmpty());
    }

    @Test
    public void testBlankProgramName(){
        Program blankNameTestProg = new Program("");
        Set<ConstraintViolation<Program>> violations = validator.validate(blankNameTestProg);

        //1 violation is expected since the Program name is blank
        assertEquals(violations.size(),1);
    }

    @Test
    public void testNullProgramName(){
        Program nullNameTestProg = new Program(null);
        Set<ConstraintViolation<Program>> violations = validator.validate(nullNameTestProg);

        //1 violation is expected since the Program name is null
        assertEquals(violations.size(),1);
    }
}