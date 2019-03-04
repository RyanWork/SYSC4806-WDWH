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
    }

    /**
     * Deleting the saved information for categories
     */
    @After
    public void tearDown(){

    }

    @Test
    @Transactional
    public void contextLoads(){
        assertNotNull(categoryRepository);
    }

    @Test
    @Transactional
    public void testFindByName(){
        Category cat1 = categoryRepository.findByName("Programming");
        assertEquals(cat1.getName(), "Programming");
    }

    @Test
    @Transactional
    public void testLearningOutcomes(){
        Category cat = categoryRepository.findByName("Programming");
        assertEquals(cat.getLearningOutcomes().size(), 1);
    }
}