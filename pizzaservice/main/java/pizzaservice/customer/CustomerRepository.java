package pizzaservice.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

interface CustomerRepository extends CrudRepository<pizzaservice.customer.Customer, Long> {
	/**
	 * Re-declared {@link CrudRepository#findAll()} to return a {@link Streamable} instead of {@link Iterable}.
	 */
	@Override
	Streamable<Customer> findAll();
}
