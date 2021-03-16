package pl.polo.devicerentspring.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polo.devicerentspring.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
