package pizzaservice.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

public interface EmployeeRepository extends CrudRepository<pizzaservice.employee.Employee, Long> {
	/**
	 * Re-declared {@link CrudRepository#findAll()} to return a {@link Streamable} instead of {@link Iterable}.
	 */
	@Override
	Streamable<Employee> findAll();
}
