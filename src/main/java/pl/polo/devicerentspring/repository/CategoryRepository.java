package pl.polo.devicerentspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polo.devicerentspring.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findFirstByNameIgnoreCase(String name);
}
