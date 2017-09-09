package powermock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.service.EmployeeServicePrivate;

import static org.junit.Assert.assertTrue;

/**
 * Mock Private
 * Created by likexin5 on 2017/9/9.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeServicePrivate.class)
public class EmployeeServicePrivateTest {

    @Test
    public void testExist() {
        EmployeeServicePrivate servicePrivate = PowerMockito.spy(new EmployeeServicePrivate());
        try {
            PowerMockito.doNothing().when(servicePrivate, "checkExist", "likexin");
            boolean result = servicePrivate.exist("likexin");
            assertTrue(result);
            PowerMockito.verifyPrivate(servicePrivate).invoke("checkExist", "likexin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
