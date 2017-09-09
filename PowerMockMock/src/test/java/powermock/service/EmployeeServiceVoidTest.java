package powermock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.entity.Employee;
import powermock.helloworld.service.EmployeeServiceVoid;

/**
 * Mock void方法
 * Created by likexin5 on 2017/9/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeServiceVoid.class)
public class EmployeeServiceVoidTest {

    @Test
    public void testCreateEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            Employee employee = new Employee();
            EmployeeServiceVoid serviceVoid = new EmployeeServiceVoid();
            serviceVoid.createEmployee(employee);
            Mockito.verify(employeeDao).addEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
