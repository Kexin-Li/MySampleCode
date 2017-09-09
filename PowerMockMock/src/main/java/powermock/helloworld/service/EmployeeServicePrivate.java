package powermock.helloworld.service;

/**
 * Mock Private
 * Created by likexin5 on 2017/9/9.
 */
public class EmployeeServicePrivate {

    public boolean exist(String userName) {
        checkExist(userName);
        return true;
    }

    private void checkExist(String userName) {
        throw new UnsupportedOperationException();
    }

}
