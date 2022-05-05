package com.example.aplikacjaklienta.file;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassReader {

    private LinkedList<Method> getMethods = new LinkedList<>();
    private LinkedList<Method> setMethods = new LinkedList<>();
    private LinkedList<Field> fields = new LinkedList<>();
    private Class tClass;

    public ClassReader(Class tClass) {
        this.tClass = tClass;
    }

    public void readClassMethods() {
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

    private Method getProperMethod(List<Method> methodList, String criteria) {
        for(Method method : methodList) {
            if(method.getName().equals(criteria)){
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
