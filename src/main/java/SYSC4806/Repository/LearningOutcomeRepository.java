package SYSC4806.Repository;

import SYSC4806.Model.LearningOutcome;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

/**
 * Repository resource to query the learning outcome data
 */
@RepositoryRestResource(collectionResourceRel = "learningoutcomes", path = "learningoutcomes")
public interface LearningOutcomeRepository extends PagingAndSortingRepository<LearningOutcome, Long> {
    /**
     * Find a learning outcome by its name
     * @param name  The name of the learning outcome
     * @return      The learning outcome with the specified name
     */
    LearningOutcome findByName(@Param("name") String name);

    /**
     * Finding course id's by learning outcome id
     * @param id  The id of the learning outcome
     * @return    The course id's with the specified learning outcome
     */
    @Query(value = "SELECT COURSES_ID FROM LEARNING_OUTCOME_COURSES WHERE LEARNING_OUTCOMES_ID=?1", nativeQuery = true)
    List<BigInteger> findByLearningOutcomeId(Long id);

    /**
     * Finding learning outcome id's by Category name
     * @param name  Name of Category
     * @return    The learning outcome id's with the specified Category
     */
    @Query(value = "SELECT lo.id  FROM LEARNING_OUTCOME  as lo LEFT JOIN CATEGORY  as c ON lo.category_id  = c.id WHERE c.name = ?1", nativeQuery = true)
    List<BigInteger> findByCategoryName(String name);

//    @Query(value = "DELETE FROM LEARNING_OUTCOME WHERE name = ?1", nativeQuery = true)
    void deleteByName(@Param("name") String name);
}