package powermock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.dao.EmployeeDaoConstructors;
import powermock.helloworld.entity.Employee;
import powermock.helloworld.service.EmployeeServiceConstructor;

/**
 * Mock Constructor
 * Created by likexin5 on 2017/9/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeServiceConstructor.class)
public class EmployeeServiceConstructorTest {

    @Test
    public void testCreateEmployee() {
        EmployeeDaoConstructors daoConstructors = PowerMockito.mock(EmployeeDaoConstructors.class);
        try {
            PowerMockito.whenNew(EmployeeDaoConstructors.class).
                    withArguments(false, EmployeeDaoConstructors.Dialect.MYSQL).thenReturn(daoConstructors);
            Employee employee = new Employee();
            EmployeeServiceConstructor serviceConstructor = new EmployeeServiceConstructor();
            serviceConstructor.createEmployee(employee);
            Mockito.verify(daoConstructors).insertEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
