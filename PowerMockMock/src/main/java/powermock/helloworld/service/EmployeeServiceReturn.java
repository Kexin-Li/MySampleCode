package powermock.helloworld.service;

import powermock.helloworld.dao.EmployeeDao;

/**
 * Mock带返回值的方法
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeServiceReturn {

    public int getTotalEmployee() {
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.getTotal();
    }

}
