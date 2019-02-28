package SYSC4806.Model;

import SYSC4806.Repository.CategoryRepository;
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
 * Tests for the Category Model class to ensure that the learning outcomes persist in the database.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

    /**
     * Repository used to save data about categories
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Setting up dummy information for categories
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        List<LearningOutcome> lo1 = new ArrayList<>();
        List<LearningOutcome> lo2 = new ArrayList<>();

        lo1.add(new LearningOutcome());
        lo1.add(new LearningOutcome());
        lo1.add(new LearningOutcome());
        lo1.add(new LearningOutcome());
        lo2.add(new LearningOutcome());
        lo2.add(new LearningOutcome());

        Category c1 = new Category("Category1", lo1);
        Category c2 = new Category("Category2", lo2);

        categoryRepository.save(c1);
        categoryRepository.save(c2);
    }

    /**
     * Deleting the saved information for categories
     */
    @After
    public void tearDown(){
        categoryRepository.deleteAll();
    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(categoryRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Category cat1 = categoryRepository.findByName("Category2");
        assertEquals(cat1.getName(), "Category2");
    }

    @Test
    @Transactional
    public void testLearningOutcomes(){
        Category cat = categoryRepository.findByName("Category1");
        assertEquals(cat.getLearningOutcomes().size(), 4);
    }
}