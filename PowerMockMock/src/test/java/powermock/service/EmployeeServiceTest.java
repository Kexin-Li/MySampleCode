package powermock.service;

import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.entity.Employee;
import powermock.helloworld.service.EmployeeService;

import static org.junit.Assert.assertEquals;

/**
 * Created by likexin5 on 2017/9/8.
 *
 */
public class EmployeeServiceTest {

    @Test
    public void testGetTotalEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
        EmployeeService service = new EmployeeService(employeeDao);
        assertEquals(10, service.getTotalEmployee());
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        Employee employee = new Employee();
        PowerMockito.doNothing().when(employeeDao).addEmployee(employee); // 注释掉也跑得通...
        EmployeeService service = new EmployeeService(employeeDao);
        service.createEmployee(employee);
        Mockito.verify(employeeDao).addEmployee(employee);
    }

}
