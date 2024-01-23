package ua.ivanyshen.customer.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.ivanyshen.customer.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
