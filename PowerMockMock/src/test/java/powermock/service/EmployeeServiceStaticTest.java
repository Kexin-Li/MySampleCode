package powermock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.entity.Employee;
import powermock.helloworld.service.EmployeeServiceStatic;
import powermock.helloworld.util.EmployeeUtil;

import static org.junit.Assert.assertEquals;

/**
 * Mock Static方法
 * Created by likexin5 on 2017/9/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeUtil.class)
public class EmployeeServiceStaticTest {

    @Test
    public void testGetEmployeeCount() {
        PowerMockito.mockStatic(EmployeeUtil.class);
        PowerMockito.when(EmployeeUtil.getEmployeeCount()).thenReturn(10);
        EmployeeServiceStatic serviceStatic = new EmployeeServiceStatic();
        assertEquals(10, serviceStatic.getEmployeeCount());
    }

    @Test
    public void testCreateEmployee() {
        PowerMockito.mockStatic(EmployeeUtil.class);
        PowerMockito.doNothing().when(EmployeeUtil.class);
        Employee employee = new Employee();
        EmployeeServiceStatic serviceStatic = new EmployeeServiceStatic();
        serviceStatic.createEmployee(employee);
        PowerMockito.verifyStatic();
    }

}
