package powermock.service;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import powermock.helloworld.service.FileServiceSpy;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Mock Spy
 * Created by likexin5 on 2017/9/9.
 */
public class FileServiceSpyTest {

    @Test
    public void testWriteSpy() {
        FileServiceSpy serviceSpy = PowerMockito.spy(new FileServiceSpy());
        serviceSpy.write("wuyanzu");
        File file = new File(System.getProperty("user.dir") + "/likexin.txt");
        assertTrue(file.exists());
    }

}
