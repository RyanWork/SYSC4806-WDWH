package SYSC4806.Repository;

import SYSC4806.Model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Repository resource to query the course data
 */
@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    /**
     * Find a course by its name
     * @param name  The name of the course
     * @return      The course with the specified name
     */
    Course findByName(@Param("name") String name);

    /**
     * Find a course by its code
     * @param code  The code of the course
     * @return      The course with the specified code
     */
    Course findByCode(@Param("code") String code);

    /**
     * Find all the courses that are offered in a specified year
     * @param year  The year to search through
     * @return      A list of all courses offered in the requested year
     */
    List<Course> findByYear(@Param("year") int year);
}