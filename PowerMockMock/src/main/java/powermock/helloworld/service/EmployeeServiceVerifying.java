package powermock.helloworld.service;

import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.entity.Employee;

/**
 * Mock Verify
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeServiceVerifying {

    public void saveOrUpdate(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao();
        long count = employeeDao.getCount(employee);
        if (count > 0) {
            employeeDao.updateEmployee(employee);
        } else {
            employeeDao.saveEmployee(employee);
        }
    }

}
