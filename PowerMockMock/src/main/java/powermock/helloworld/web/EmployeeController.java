package powermock.helloworld.web;

import powermock.helloworld.service.EmployeeServiceEmail;

/**
 * Mock Argument Matcher
 * Created by likexin5 on 2017/9/8.
 */
public class EmployeeController {

    public String getEmail(String userName) {
        EmployeeServiceEmail service = new EmployeeServiceEmail();
        return service.findEmailByUserName(userName);
    }

}
