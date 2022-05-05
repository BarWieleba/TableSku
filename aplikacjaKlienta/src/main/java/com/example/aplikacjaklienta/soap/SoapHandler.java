package com.example.aplikacjaklienta.soap;

import com.bartek.soap.ComputerResponse;
import jakarta.xml.bind.*;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPMessage;
import org.w3c.dom.NodeList;

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SoapHandler {

    private String handleRequest(String method, String url, String xmlPayload, String readTimeout, String connectTimeout) throws IOException {
        HttpURLConnection connection = null;
        OutputStreamWriter wr = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL connectionUrl = new URL(url);
            connection = (HttpURLConnection) connectionUrl.openConnection();
            connection.setRequestMethod(method);
            if (xmlPayload != null && !xmlPayload.equals("")) {
                connection.setReadTimeout(Integer.parseInt(readTimeout));
                connection.setConnectTimeout(Integer.parseInt(connectTimeout));
                connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
                /*connection.setRequestProperty("SOAPAction", soapAction);*/
                connection.setDoOutput(true);
                wr = new OutputStreamWriter(connection.getOutputStream());
                wr.write(xmlPayload);
                wr.flush();
            }
            try {
                // getInputStream will throw exception when response code is
                // greater than 400, so we need to getErrorStream
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (Exception e) {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            StringBuilder bodyBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                bodyBuilder.append(inputLine);
            }
            in.close();
            result = String.valueOf(bodyBuilder.toString());
            System.out.println(result);

        } catch (Exception e) {
        } finally {
            if (wr != null) {
                wr.close();
            }
            if (in != null) {
                in.close();
            }
            if (connection != null) {
                connection.disconnect();
            }

        }

        return result;
    }

    public ComputerResponse connectAndSend(boolean everything, String manufacturer, String resolution) {
        ComputerResponse computerList = null;
        String input = "" +
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" \n" +
                "         xmlns:std=\"http://bartek.com/soap\"  >\n" +
                "    <soap:Body>\n" +
                "        <std:request >\n" +
                "            <everything></everything>\n" +
                "            <manufacturer>Asus</manufacturer>\n" +
                "            <resolution>16x9</resolution>\n" +
                "        </std:request>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>";
        try {
            String resp = handleRequest("POST", "http://localhost:8080/ws", input, "1000", "1000");
            computerList = responseIntoComputerObjects(resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return computerList;
    }

    private ComputerResponse responseIntoComputerObjects(String response) throws Exception {
        ComputerResponse computerResponse = new ComputerResponse();
        Set<ComputerResponse.ComputerList> dataDtos = new HashSet<>();
        InputStream is = new ByteArrayInputStream(response.getBytes());
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(null, is);
        NodeList listResult = message.getSOAPBody().getChildNodes();
        for (int i = 0; i < listResult.getLength(); i++) {
            NodeList children = listResult.item(i).getChildNodes();
            for (int k = 0; k < children.getLength(); k++) {
                System.out.println(children.item(k).getNodeName());
                System.out.println(children.item(k).getTextContent());
                if(children.item(k).getNodeName().equals("count")){
                    computerResponse.setCount(Long.valueOf(children.item(k).getTextContent()));
                }
                if (children.item(k).getNodeName().equals("computerList")) {
                    NodeList children1 = children.item(k).getChildNodes();
                    ComputerResponse.ComputerList dataDto = new ComputerResponse.ComputerList();
                    for(int j=0; j< children1.getLength(); j++){
                        String content = children1.item(j).getTextContent();
                        if(j==0){
                            dataDto.setId(Long.valueOf(content));
                        }
                        if(j==1){
                            dataDto.setManufacturer(content);
                        }
                        if(j==2){
                            dataDto.setScreenSize(content);
                        }
                        if(j==3){
                            dataDto.setResolution(content);
                        }
                        if(j==4){
                            dataDto.setMatrixTexture(content);
                        }
                        if(j==5){
                            dataDto.setPackaging(content);
                        }
                        if(j==6){
                            dataDto.setProcessor(content);
                        }
                        if(j==7){
                            dataDto.setCoreCount(content);
                        }
                        if(j==7){
                            dataDto.setProcessorSpeed(content);
                        }
                        if(j==8){
                            dataDto.setRamSize(content);
                        }
                        if(j==9){
                            dataDto.setDriveSize(content);
                        }
                        if(j==10){
                            dataDto.setDriveType(content);
                        }
                        if(j==11){
                            dataDto.setGraphics(content);
                        }
                        if(j==12){
                            dataDto.setVideoMemory(content);
                        }
                        if(j==13){
                            dataDto.setOs(content);
                        }
                        if(j==14){
                            dataDto.setRecorder(content);
                        }
                    }
                    dataDtos.add(dataDto);
                }
            }
        }
        computerResponse.getComputerList().addAll(dataDtos);
        return computerResponse;
    }
}
