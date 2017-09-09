package powermock.helloworld.service;

import powermock.helloworld.dao.EmployeeDaoConstructors;
import powermock.helloworld.entity.Employee;

/**
 * Mock Constructor
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeServiceConstructor {

    public void createEmployee(Employee employee) {
        EmployeeDaoConstructors employeeDaoConstructors = new EmployeeDaoConstructors(false, EmployeeDaoConstructors.Dialect.MYSQL);
        employeeDaoConstructors.insertEmployee(employee);
    }

}
