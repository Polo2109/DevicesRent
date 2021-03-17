package pl.polo.devicerentspring.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polo.devicerentspring.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findFirstByPesel(String pesel);
}
