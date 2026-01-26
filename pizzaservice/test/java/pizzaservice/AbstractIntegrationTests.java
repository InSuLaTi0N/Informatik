package pizzaservice;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractIntegrationTests {}