package powermock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import powermock.helloworld.dao.EmployeeDao;
import powermock.helloworld.service.EmployeeServiceReturn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Mock带返回值的方法
 * Created by likexin5 on 2017/9/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeServiceReturn.class)
// 这个注解是告诉PowerMock为我提前准备一个EmployeeServiceReturn的class，根据我测试预期的行为去准备.
public class EmployeeServiceReturnTest {

    @Test
    public void testGetTotalEmployee() {
        EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
        try {
            // 这句话就是告诉PowerMock录制一个这样的构造行为，等我接下来用的时候你就把之前mock的东西给我
            PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
            PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
            EmployeeServiceReturn serviceReturn = new EmployeeServiceReturn();
            assertEquals(10, serviceReturn.getTotalEmployee());
        } catch (Exception e) {
            fail("测试失败");
        }
    }

}
