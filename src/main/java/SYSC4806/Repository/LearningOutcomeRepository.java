package SYSC4806.Repository;

import SYSC4806.Model.LearningOutcome;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
}