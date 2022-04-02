package com.example.tablesku.file;


import com.example.tablesku.entity.Computer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FileContentReader {
    private String path;
    private String delimiter;

    private LinkedList<Method> getMethods = new LinkedList<>();
    private LinkedList<Method> setMethods = new LinkedList<>();
    private LinkedList<Field> fields = new LinkedList<>();

    public FileContentReader(String path) {
        this.path = path;
    }

    public FileContentReader(String path, String delimiter) {
        this.path = path;
        this.delimiter = delimiter;
    }

    private void readClassMethods(Class tClass) {
        List<Method> classMethods = Arrays.stream(tClass.getDeclaredMethods()).collect(Collectors.toList());
        fields = new LinkedList<>(Arrays.stream(tClass.getDeclaredFields()).collect(Collectors.toList()));

        int i = 0;
        for(Field field : fields) {
            String methodName = field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
            Method method = getProperMethod(classMethods, "get" + methodName);
            Method method1 = getProperMethod(classMethods, "set" + methodName);
            getMethods.add(method);
            setMethods.add(method1);
            System.out.println(field.getName() + " " + method.getName() + " " + method1.getName());
        }
    }

    public List<Computer> readFromFile() throws Exception{
        readClassMethods(Computer.class);
        List<Computer> computers = new ArrayList<>();


        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split(delimiter);
            Computer computer = new Computer();
            for(int i = 0; i < lineArray.length; i++){
                Method method = setMethods.get(i);
                method.invoke(computer, lineArray[i]);
            }
            computers.add(computer);
        }
        return computers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    private Method getProperMethod(List<Method> methodList, String criteria) {
        for(Method method : methodList) {
            if(method.getName().contains(criteria)){
                return method;
            }
        }
        return null;
    }

    public LinkedList<Method> getGetMethods() {
        return getMethods;
    }

    public void setGetMethods(LinkedList<Method> getMethods) {
        this.getMethods = getMethods;
    }

    public LinkedList<Method> getSetMethods() {
        return setMethods;
    }

    public void setSetMethods(LinkedList<Method> setMethods) {
        this.setMethods = setMethods;
    }

    public LinkedList<Field> getFields() {
        return fields;
    }

    public void setFields(LinkedList<Field> fields) {
        this.fields = fields;
    }
}
