package powermock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.entity.Employee;
import powermock.helloworld.service.EmployeeServiceVerifying;

/**
 * Mock Verify
 * Created by likexin5 on 2017/9/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeServiceVerifying.class)
public class EmployeeServiceVerifyingTest {

    @Test
    public void testSaveOrUpdateLessZero() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            Employee employee = new Employee();
            PowerMockito.when(employeeDao.getCount(employee)).thenReturn(0L);
            EmployeeServiceVerifying serviceVerifying = new EmployeeServiceVerifying();
            serviceVerifying.saveOrUpdate(employee);
            Mockito.verify(employeeDao).saveEmployee(employee);
            Mockito.verify(employeeDao, Mockito.never()).updateEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveOrUpdateMoreZero() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            Employee employee = new Employee();
            PowerMockito.when(employeeDao.getCount(employee)).thenReturn(1L);
            EmployeeServiceVerifying serviceVerifying = new EmployeeServiceVerifying();
            serviceVerifying.saveOrUpdate(employee);
            Mockito.verify(employeeDao).updateEmployee(employee);
            Mockito.verify(employeeDao, Mockito.never()).saveEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
