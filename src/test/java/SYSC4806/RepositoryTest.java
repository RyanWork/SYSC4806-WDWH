package SYSC4806;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import SYSC4806.Model.Category;
import SYSC4806.Model.Course;
import SYSC4806.Model.LearningOutcome;
import SYSC4806.Model.Program;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Basic repository tests to make simple web requests to ensure that the
 * REST Api works. Works using MockMvc,
 * see: https://spring.io/guides/gs/spring-boot/#_add_unit_tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryTest {

    /**
     * String to the categories api
     */
    private static final String API_CATEGORIES = "/api/categories";

    /**
     * String to the courses api
     */
    private static final String API_COURSES = "/api/courses";

    /**
     * String to the learning outcome api
     */
    private static final String API_LEARNING_OUTCOMES = "/api/learningoutcomes";

    /**
     * String to the programs api
     */
    private static final String API_PROGRAMS = "/api/programs";

    /**
     * mapper object used to serialize data
     */
    @Autowired
    private ObjectMapper mapper;

    /**
     * MVC instance to simulate invocation of web requests
     */
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void GetCategories() throws Exception {
        this.GetRequest(this.API_CATEGORIES);
    }

    @Test
    @WithMockUser
    public void CreateNewCategory() throws Exception {
        // Create the test object instance
        Category c = new Category();
        c.setName("testCategory1");

        this.PostRequest(this.API_CATEGORIES, c);
    }

    @Test
    @WithMockUser
    public void GetCourses() throws Exception {
        this.GetRequest(this.API_COURSES);
    }

    @Test
    @WithMockUser
    public void CreateNewCourses() throws Exception {
        Course course = new Course();
        course.setName("c1");
        course.setCode("1234");
        course.setYear(2);

        this.PostRequest(this.API_COURSES, course);
    }

    @Test
    @WithMockUser
    public void GetLearningOutcomes() throws Exception {
        this.GetRequest(this.API_LEARNING_OUTCOMES);
    }

    @Test
    @WithMockUser
    public void CreateNewLearningOutcome() throws Exception {
        LearningOutcome lo = new LearningOutcome();
        lo.setName("lo1");

        this.PostRequest(this.API_LEARNING_OUTCOMES, lo);
    }

    @Test
    @WithMockUser
    public void GetPrograms() throws Exception {
        this.GetRequest(this.API_PROGRAMS);
    }

    @Test
    @WithMockUser
    public void CreateNewProgram() throws Exception {
        Program p = new Program();
        p.setName("p1");

        this.PostRequest(this.API_PROGRAMS, p);
    }

    /**
     * Helper Method to perform a get request to the specified url.
     * Request should return status 200
     *
     * @param api The url of the API to make the request
     * @throws Exception
     */
    public void GetRequest(String api) throws Exception {
        // Perform a get request
        mvc.perform(MockMvcRequestBuilders.get(api)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Make sure status: 200 (Ok)
    }

    /**
     * Helper method to perform a post request to the specified url with the
     * provided object. It should create an object and return status 201
     *
     * @param api  The url of the API to make the request
     * @param data The object to serialize and post
     * @throws Exception
     */
    public void PostRequest(String api, Object data) throws Exception {
        // Serialize the object as JSON
        String categorySerialized = this.mapper.writeValueAsString(data);

        // Post the request
        mvc.perform(MockMvcRequestBuilders.post(api)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categorySerialized)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()); // Make sure status: 201 (Created)
    }
}