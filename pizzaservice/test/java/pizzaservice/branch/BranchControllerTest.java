package pizzaservice.branch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.salespointframework.accountancy.Accountancy;
import org.salespointframework.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pizzaservice.AbstractIntegrationTests;
import pizzaservice.employee.EmployeeManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class BranchControllerTest extends AbstractIntegrationTests{

	@Autowired
	MockMvc mvc;

	@Autowired
	Accountancy accountancy;

	@Autowired
	EmployeeManagement employeeManagement;

	BranchController branchController;

	@BeforeEach
	public void setUp(){
		branchController = new BranchController(accountancy, employeeManagement);
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void accountancyTest() throws Exception {
		mvc.perform(get("/branch"))
				.andExpect(status().isOk())
				.andExpect(view().name("branch"))
				.andExpect(model().attributeExists("invoice"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void setAccountancyTest() throws Exception {
		LocalDate endDate = LocalDate.now().plusWeeks(1);
		LocalDate startDate = endDate.minusWeeks(1);

		mvc.perform(post("/branch")
				.param("start", startDate.toString())
				.param("end", endDate.toString())
				.characterEncoding("utf-8"))
				.andExpect(status().isOk())
				.andExpect(view().name("branch"))
				.andExpect(model().attributeExists("invoice"));
	}

	@Test
	public void payrollTest(){
		branchController.payroll();
		assertThat(!accountancy.find(Interval.from(LocalDateTime.now()).to(LocalDateTime.now().plusMinutes(1))).isEmpty());
	}
}
