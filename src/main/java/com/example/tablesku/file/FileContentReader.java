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

    public FileContentReader(String path) {
        this.path = path;
    }

    public FileContentReader(String path, String delimiter) {
        this.path = path;
        this.delimiter = delimiter;
    }



    public List<Computer> readFromFile() throws Exception{
        ClassReader classReader = new ClassReader(Computer.class);
        classReader.readClassMethods();
        List<Computer> computers = new ArrayList<>();


        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split(delimiter);
            Computer computer = new Computer();
            for(int i = 0; i < lineArray.length; i++){
                Method method = classReader.getSetMethods().get(i);
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

}
