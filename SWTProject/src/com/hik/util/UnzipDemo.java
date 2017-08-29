package com.hik.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipDemo {

	public static final String EXT = ".zip";  
    private static final int BUFFER = 1024;
    
    /**
     * unzip
     * @param srcPath 源文件路径
     * @throws Exception
     */
    public static void decompress(String srcPath) throws Exception {
    	String destPath = new File(srcPath).getParent();
    	decompress(srcPath, destPath);
    }
    
    public static void decompress(String srcPath, String destPath) throws Exception {
        File srcFile = new File(srcPath);
        decompress(srcFile, destPath);
    }
    
    public static void decompress(File srcFile, String destPath) throws Exception {
        decompress(srcFile, new File(destPath));
    }
    
    public static void decompress(File srcFile, File destFile) throws Exception {
        CheckedInputStream cis = new CheckedInputStream(new FileInputStream(srcFile), new CRC32());
        ZipInputStream zis = new ZipInputStream(cis);  
        decompress(destFile, zis);  
        zis.close();  
    } 
    
    private static void decompress(File destFile, ZipInputStream zis) throws Exception {  
        ZipEntry entry = null;
        while ((entry = zis.getNextEntry()) != null) {
            // 文件
            String dir = destFile.getPath() + File.separator + entry.getName();
            File dirFile = new File(dir);
            // 文件检查
            fileProber(dirFile);
            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                decompressFile(dirFile, zis);
            }
            zis.closeEntry();
        }
    }
    
    private static void decompressFile(File destFile, ZipInputStream zis) throws Exception {  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = zis.read(data, 0, BUFFER)) != -1) {  
            bos.write(data, 0, count);  
        }  
        bos.close();  
    }
    
    private static void fileProber(File dirFile) {  
        File parentFile = dirFile.getParentFile();  
        if (!parentFile.exists()) {  
            // 递归寻找上级目录  
            fileProber(parentFile);  
            parentFile.mkdir();  
        }  
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println("start...");
    	// 解压到指定目录
        UnzipDemo.decompress("d:\\tomcat.zip");  
        System.out.println("end...");
	}
    
}
