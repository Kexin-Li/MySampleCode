package powermock.helloworld.dao;

import powermock.helloworld.entity.Employee;

/**
 * Mock Constructor
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeDaoConstructors {

    public enum Dialect {
        MYSQL,
        ORACLE
    }

    public EmployeeDaoConstructors(boolean lazy, Dialect dialect) {
        throw new UnsupportedOperationException();
    }

    public void insertEmployee(Employee employee) {
        throw new UnsupportedOperationException();
    }

}
