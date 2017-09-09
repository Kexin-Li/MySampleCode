package powermock.helloworld.service;

import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.entity.Employee;

/**
 * Created by likexin5 on 2017/9/8.
 *
 */
public class EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * 获取员工数量
     * @return 员工数
     */
    public int getTotalEmployee() {
        return employeeDao.getTotal();
    }

    /**
     * 创建一个员工
     * @param employee 员工
     */
    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

}
