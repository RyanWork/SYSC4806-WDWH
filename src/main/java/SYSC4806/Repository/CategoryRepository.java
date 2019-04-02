package SYSC4806.Repository;

import SYSC4806.Model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository resource to query the category data
 */
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    /**
     * Find a Category by its name
     * @param name  The name of the category
     * @return      The category with the specified name
     */
    Category findByName(@Param("name") String name);
    void deleteByName(@Param("name") String name);
}