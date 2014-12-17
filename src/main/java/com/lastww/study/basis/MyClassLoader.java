package com.lastww.study.basis;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * Created by liuweiwei on 14-12-17.
 */
public class MyClassLoader extends ClassLoader {

    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    public Hashtable<String, Class> loadedClasses = new Hashtable<>();

    public Class findClass(String className) throws ClassNotFoundException {

        if (loadedClasses.get(className) != null) {
            return loadedClasses.get(className);
        }

        className = className.replace('.', File.separatorChar);

        File f = new File(this.path + File.separator + className + ".class");
        if (!f.exists()) {
            throw new ClassNotFoundException();
        }
        int size = (int)f.length();
        byte buff[] = new byte[size];
        try {
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            dis.readFully(buff);
            dis.close();
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException();
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
        return defineClass(className, buff, 0, size);
    }

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("/Users/liuweiwei");
        try {
            Class test = classLoader.loadClass("Test");
            System.out.println("classloader:" + test.getClassLoader());
            System.out.println("class:" + test);
            Method saiHi = test.getMethod("sayHi");
            /** Test类不再此包中，必须声明为public此处才能调用 */
            saiHi.invoke(test);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

