package SYSC4806.Model;

import SYSC4806.TestHelpers.InstanceTestClassListener;
import SYSC4806.Repository.LearningOutcomeRepository;
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
 * Tests for the LearningOutcomes Model class to ensure that the category and courses persist in the database.
 */
@RunWith(SpringInstanceTestClassRunner.class)
@ContextConfiguration
@EnableJpaRepositories(basePackages = "SYSC4806.Repository")
@ComponentScan(basePackages = "SYSC4806")
public class LearningOutcomeTest implements InstanceTestClassListener {

    /**
     * Repository used to save data about learning outcomes
     */
    @Autowired
    private LearningOutcomeRepository learningOutcomeRepository;

    /**
     * Test course to be used for tests
     */
    private Course c = new Course(1, "TEST_LEARNING_OUTCOME_COURSE_NAME", "TEST_LEARNING_OUTCOME_COURSE_CODE");

    /**
     * Test Category to be used for tests
     */
    private Category category = new Category("TEST_LEARNING_OUTCOME_CATEGORY");

    /**
     * Instance of learning outcome to add to repo
     */
    private LearningOutcome testLO = new LearningOutcome("TEST_LEARNING_OUTCOME", category);

    /**
     * Validator use to validate constraints on Learning Outcome attributes
     */
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * Setting up dummy information for learningoutcomes
     */
    @Override
    public void beforeClassSetup() {
        testLO.addCourse(c);
        learningOutcomeRepository.save(testLO);
    }

    /**
     * Deleting the saved information for learningoutcomes
     */
    @Override
    public void afterClassSetup() {
        learningOutcomeRepository.delete(testLO);
    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(learningOutcomeRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        LearningOutcome lo = learningOutcomeRepository.findByName("TEST_LEARNING_OUTCOME");

        assertNotNull(lo);
        assertEquals(lo.getName(), lo.getName());
    }

    @Test
    @Transactional
    public void testNumberOfCourses(){
        LearningOutcome lo = learningOutcomeRepository.findByName("TEST_LEARNING_OUTCOME");

        assertNotNull(lo);
        assertFalse(lo.getCourses().isEmpty());
    }

    @Test
    public void testBlankLearningOutcomeName(){
        LearningOutcome blankNameTestLo = new LearningOutcome("", category);
        Set<ConstraintViolation<LearningOutcome>> violations = validator.validate(blankNameTestLo);

        //1 violation is expected since the Learning Outcome name is blank
        assertEquals(violations.size(),1);
    }

    @Test
    public void testNullLearningOutcomeName(){
        LearningOutcome nullNameTestLo = new LearningOutcome(null, category);
        Set<ConstraintViolation<LearningOutcome>> violations = validator.validate(nullNameTestLo);

        //1 violation is expected since the Learning Outcome name is null
        assertEquals(violations.size(),1);
    }
}