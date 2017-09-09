package powermock.helloworld.service;

import powermock.helloworld.entity.Employee;
import powermock.helloworld.util.EmployeeUtil;

/**
 * Mock Static方法
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeServiceStatic {

    public int getEmployeeCount() {
        return EmployeeUtil.getEmployeeCount();
    }

    public void createEmployee(Employee employee) {
        EmployeeUtil.persistenceEmployee(employee);
    }

}
