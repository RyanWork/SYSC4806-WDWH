package SYSC4806.Repository;

import SYSC4806.Model.Course;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * Find all course names that are offered in a given program and year
     * @param p_id The program id that the course should be chosen from
     * @param year  The year to search through
     * @return      A list of all course names offered in the requested year and program
     */
    @Query(value = "SELECT c.name FROM COURSE as c INNER JOIN PROGRAM_COURSES as p_c ON c.id = p_c.courses_id WHERE p_c.programs_id = ?1 AND c.year = ?2", nativeQuery = true)
    List<String> findCourseByProgramAndYear(Long p_id, int year);

}