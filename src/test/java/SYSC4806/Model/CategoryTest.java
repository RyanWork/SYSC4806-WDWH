package SYSC4806.Model;

import SYSC4806.TestHelpers.InstanceTestClassListener;
import SYSC4806.Repository.CategoryRepository;
import SYSC4806.TestHelpers.SpringInstanceTestClassRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

@RunWith(SpringInstanceTestClassRunner.class)
@ContextConfiguration
@EnableJpaRepositories(basePackages = "SYSC4806.Repository")
@ComponentScan(basePackages = "SYSC4806")
public class CategoryTest implements InstanceTestClassListener {
    /**
     * Repository used to save data about categories
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Test Category to be used for tests
     */
    private Category c = new Category("TEST_CATEGORY");

    /**
     * Learning outcome to be used for tests
     */
    private LearningOutcome lo = new LearningOutcome("TEST_CATEGORY_LEARNING_OUTCOME", c);

    /**
     * Add the test category to the repository
     */
    @Override
    public void beforeClassSetup() {
        c.addLO(lo);
        categoryRepository.save(c);
    }

    /**
     * Delete the test category from the repository
     */
    @Override
    public void afterClassSetup() {
        categoryRepository.delete(c);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Category testCategory = categoryRepository.findByName("TEST_CATEGORY");
        assertNotNull(testCategory);
        assertEquals(testCategory.getName(), c.getName());
    }

    @Test
    @Transactional
    public void testLearningOutcomes(){
        Category testCategory = categoryRepository.findByName("TEST_CATEGORY");

        assertNotNull(testCategory);
        assertNotNull(testCategory.getLearningOutcomes());
        assertFalse(testCategory.getLearningOutcomes().isEmpty());
    }
}