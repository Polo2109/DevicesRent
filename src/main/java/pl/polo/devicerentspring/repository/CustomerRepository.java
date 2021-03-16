package pl.polo.devicerentspring.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polo.devicerentspring.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
