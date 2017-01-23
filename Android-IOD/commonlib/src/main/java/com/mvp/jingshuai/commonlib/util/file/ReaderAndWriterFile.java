package com.mvp.jingshuai.commonlib.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by eqruvvz on 8/29/2016.
 */
public class ReaderAndWriterFile {


    /**
     * @param args
     */
    public static void main(String[] args) {
        File oldfile = new File("D:\\c\\www.txt");
        File newfile = new File("D:\\c\\1.txt");

        writer(readerFile(oldfile), newfile);
    }

    /**
     * 利用字节流读文件
     *
     * @param file 源文件
     * @return 源文件的字符串
     */
    public static String readerFile(File file) {
        StringBuffer sb = new StringBuffer();
        if (file.isFile()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                // fileInputStream.available()获取文件的字节数
                byte[] b = new byte[fileInputStream.available()];
                int read = fileInputStream.read(b);
                while (read != -1) {
                    sb.append(new String(b));
                    read = fileInputStream.read(b);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static void writer(String str, File newfile) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(newfile);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}