package powermock.helloworld.service;

import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.entity.Employee;

/**
 * Mock void方法
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeServiceVoid {

    public void createEmployee(Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.addEmployee(employee);
    }

}
