package powermock.helloworld.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Mock Spy
 * Created by likexin5 on 2017/9/9.
 */
public class FileServiceSpy {

    public void write(String text) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/likexin.txt"));
            bw.write(text);
            bw.flush();
            System.out.println("content write successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

}
