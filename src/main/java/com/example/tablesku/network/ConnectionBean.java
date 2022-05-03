package com.example.tablesku.network;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionBean {
    private final ObjectMapper objectMapper;
    private String serverAddress;
    private String authHeader;

    public ConnectionBean(String serverAddress){
        this.objectMapper = new ObjectMapper();
        this.serverAddress = serverAddress;
    }

    /**
     *
     * @param object - single object that will be parsed to json to be send to backend
     * @param postfix - address postfix
     * @return returns json that has to mapped to an object or object array
     * @throws IOException - can throw exceptions
     */
    public String postObject(Object object, String postfix) throws IOException {
        String input = objectMapper.writeValueAsString(object);
        return postData(input, postfix);
    }

    /**
     *
     * @param objectList - list of objects that will be parsed to json to be send to backend
     * @param postfix - address postfix
     * @return  returns json that has to mapped to an object or object array
     * @throws IOException - can throw exceptions
     */
    public <T> String postObjects(List<T> objectList, String postfix) throws IOException {
        String input = objectMapper.writeValueAsString(objectList);
        return postData(input, postfix);
    }

    /**
     *
     * @param postfix - this method only needs postfix because it doesnt send any data, only receives data from backend, android runtime only sends post request so all the GET requests have to be send as POST requests
     * @return returns json that has to mapped to an object or object array
     * @throws IOException - can throw exceptions
     */
    public String postGet(String postfix) throws IOException {
        return postData(null, postfix);
    }

    /**
     *
     * @param postfix - address postfix
     * @param hashMap - mapped arguemtns that will be added to url through encoding
     * @return string
     * @throws IOException
     */
    public String postXWwwFormUrlEncoded(String postfix, HashMap<String, Object> hashMap) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String, Object> entry : hashMap.entrySet()){
            stringBuilder
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        String urlParameters = removeLastChar(stringBuilder.toString());
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        HttpURLConnection connection = openConnection(postfix, postData);
        sendData(connection, urlParameters);
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));
        return readData(br);
    }

    private String postData(String input, String postfix) throws IOException {
        HttpURLConnection connection = openConnection(postfix);
        sendData(connection, input);    //if input is either null of empty no data will be send to the backend

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + connection.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));
        return readData(br);
    }

    private HttpURLConnection openConnection(String postfix) throws IOException {
        URL address = new URL(serverAddress + postfix);
        HttpURLConnection conn = (HttpURLConnection) address.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");          //somehow android runtime sends all requests as POSTs even when request method is set to GET
        conn.setRequestProperty("Content-Type", "application/json");
        if(authHeader != null && !authHeader.isEmpty()){
            conn.setRequestProperty("Authorization", authHeader);
        }
        return conn;
    }

    private HttpURLConnection openConnection(String postfix, byte[] postData) throws IOException {
        URL address = new URL(serverAddress + postfix);
        HttpURLConnection conn = (HttpURLConnection) address.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");          //somehow android runtime sends all requests as POSTs even when request method is set to GET
        int postDataLength = postData.length;

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        if(authHeader != null && !authHeader.isEmpty()){
            conn.setRequestProperty("Authorization", authHeader);
        }
        return conn;
    }

    private String readData(BufferedReader br) throws IOException {
        String output;
        StringBuilder stringBuilder = new StringBuilder();
        while ((output = br.readLine()) != null) {
            stringBuilder.append(output);
        }
        return stringBuilder.toString();
    }

    private void sendData(HttpURLConnection connection, String input) throws IOException {
        if (input == null || input.isEmpty()){
            return;
        }
        OutputStream os = connection.getOutputStream();
        os.write(input.getBytes());
        os.flush();
    }

    private String removeLastChar(String s){
        return (s == null || s.length() == 0)
                ? ""
                : (s.substring(0, s.length() - 1));
    }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = "Bearer " + authHeader;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
