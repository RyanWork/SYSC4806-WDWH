package SYSC4806.Repository;

import SYSC4806.Model.Program;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository resource to query the program data
 */
@RepositoryRestResource(collectionResourceRel = "programs", path = "programs")
public interface ProgramRepository extends PagingAndSortingRepository<Program, Long> {
    /**
     * Find a program by its name
     * @param name  The name of the learning outcome
     * @return      The learning outcome with the specified name
     */
    Program findByName(@Param("name") String name);
}