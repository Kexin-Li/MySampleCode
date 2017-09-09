package powermock.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.entity.Employee;
import powermock.helloworld.service.EmployeeServiceEmail;
import powermock.helloworld.web.EmployeeController;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Mock Argument Matcher
 * Created by likexin5 on 2017/9/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Test
    public void testGetEmail() {
        EmployeeServiceEmail serviceEmail = PowerMockito.mock(EmployeeServiceEmail.class);
        when(serviceEmail.findEmailByUserName(Mockito.argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                String arg = (String) o;
                if (arg.startsWith("likexin") || arg.startsWith("lkx")) {
                    return true;
                } else {
                    return false;
                }
            }
        }))).thenReturn("likexin@gmail.com");

        try {
            whenNew(EmployeeServiceEmail.class).withAnyArguments().thenReturn(serviceEmail);
            EmployeeController controller = new EmployeeController();
            String email = controller.getEmail("likexin");
            assertEquals(email, "likexin@gmail.com");
            controller.getEmail("likexin");
            fail("should not process here");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
