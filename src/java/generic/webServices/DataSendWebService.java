/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;

//import org.json.JSONObject;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.misc.BASE64Decoder;
import generic.webServiceModel.DataSendWebServiceModel;

/**
 *
 * @author Administrator
 */
@Path("/")
public class DataSendWebService {

    @POST
    @Path("/getAllTableRecords")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/BLE_Project/resources/getAllTableRecords
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject Records() {
        JSONObject obj = new JSONObject();
        DataSendWebServiceModel dataSendModel = new DataSendWebServiceModel();
        try {

            dataSendModel.setConnection();
            //JSONObject obj = new JSONObject();
            JSONArray json = null;

            json = dataSendModel.getTableRecords();
            obj.put("data", json);

        } catch (Exception e) {
            System.out.println("Error in DataWebServices 'requestData' url calling getWardData()..." + e);
        }
        return obj;
    }

    @POST
    @Path("/DetailList")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public String test2(String inputdata) throws org.codehaus.jettison.json.JSONException, IOException {
        DataSendWebServiceModel dataSendModel = new DataSendWebServiceModel();
        dataSendModel.setConnection();
        JSONObject json = new JSONObject(inputdata);
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        JSONObject json4 = new JSONObject();

        //image part start
        String currentDate = "";
        String currentTime = "";
        String Year = "";
        String Month = "";
        String day = "";
        String tableName = "";
        String audioPath = "";
        String imagePath = "";
        String location = "";
        double longitude = 0;
        double latitude = 0;
        json1 = json.getJSONObject("text");
        json2 = json.getJSONObject("image");
        json3 = json.getJSONObject("audio");
        json4 = json.getJSONObject("Location");
        tableName = json1.get("TableName").toString();
//           String img1 = json2.get("img1").toString();
        currentDate = json1.get("currentDate").toString();
        currentTime = json1.get("currentTime").toString();
        location = json4.toString();

        String location1 = location.substring(1, location.length() - 1);
        String location_arr[] = location1.split(",")[0].split(":");
        String location_arr1[] = location1.split(",")[1].split(":");

        latitude = Double.parseDouble(location_arr[1]);
        longitude = Double.parseDouble(location_arr1[1]);

        String date[] = currentDate.split(" ");
        day = date[0];
        Month = date[1];
        Year = date[2];
        OutputStream out = null;
        int no_of_rows = dataSendModel.getNoOfRows(tableName);
        no_of_rows = no_of_rows + 1;
        List<File> fileList = new ArrayList<File>();

        try {

            int size = Integer.parseInt(json2.get("totalImg").toString());
            String destination_path = "";
            String fileNameArray[] = new String[size];

            String fileName = "";
            for (int i = 1; i <= size; i++) {

                String getBackEncodedString = json2.get("byte_arr" + i).toString();
                byte[] imageAsBytes = new BASE64Decoder().decodeBuffer(getBackEncodedString);
                fileName = (json2.get("imgname" + i).toString());
                fileNameArray[i - 1] = fileName;
                if (fileName.isEmpty()) {
                    fileName = "out.jpg";
                }

                destination_path = dataSendModel.getDestinationPath("DetailList");
                fileNameArray[i - 1] = fileName;
//                if(imagePath == "") {
//                    imagePath = destination_path + "/images/" + Year + "/" + Month + "/" + day + "/" + tableName + "/" + fileName;
//                } else {
//                    imagePath = imagePath + ";" + destination_path + "/images/" + Year + "/" + Month + "/" + day + "/" + tableName + "/" + fileName;
//                }

                imagePath = destination_path + "/images/" + Year + "/" + Month + "/" + day + "/" + tableName + "/" + no_of_rows;

                dataSendModel.makeDirectory(imagePath);
                String file = imagePath + "/" + fileName;
                fileList.add(new File(file));
                out = new FileOutputStream(file);
                out.write(imageAsBytes);
                out.close();

//                dataSendModel.insertImageRecord(fileName);
                System.out.println("image insert ");

            }

        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        try {
            dataSendModel.setConnection();
        } catch (Exception ex) {
            System.out.println("ERROR: in trafficSignalData : " + ex);
        }
        //////////audio part

        OutputStream out1 = null;

        try {

            String destination_path = "";
            String fileName = "";
            if (!json3.get("audio").equals("")) {
                String getBackEncodedString = json3.get("audio").toString();
                byte[] audioAsBytes = new BASE64Decoder().decodeBuffer(getBackEncodedString);
                fileName = (json3.get("fileName").toString());

                if (fileName.isEmpty()) {
                    fileName = "out.mp3";
                }
                destination_path = dataSendModel.getDestinationPath("DetailList");
                audioPath = destination_path + "/audios/" + Year + "/" + Month + "/" + day + "/" + tableName + "/" + no_of_rows;
                dataSendModel.makeDirectory(audioPath);

                String file = audioPath + "/" + fileName;

                out1 = new FileOutputStream(file);
                out1.write(audioAsBytes);
                out1.close();
                System.out.println("audio insert ");
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        String peri = dataSendModel.getPeripheralList(tableName);

        try {
//        String peripherals[] = peri.substring(1, peri.length()-1).split(", ");
            String value = peri;
            value = value.substring(1, value.length() - 1);           //remove curly brackets
            String peripherals[] = value.split(",");              //split the string to creat key-value pairs
            Map<String, String> map = new HashMap<>();

            for (String pair : peripherals) //iterate over the pairs
            {
                String[] entry = pair.split("=");                   //split the pairs to get key and value 
                map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
            }
           
             if (map.containsKey("Images") || map.containsKey("Image")) {
                String imageField = peripherals[0].trim().split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";

                json1.put("latitude", latitude);
                json1.put("longitude", longitude);
                json1.put(imageField, imagePath);

                json1.put("created_at", json1.get("currentDate") + " " + json1.get("currentTime"));
                dataSendModel.insertRecord(json1);
            } else if (map.containsKey("Audio")) {
                String audioField = peripherals[0].trim().split("=")[0].equals("Audio") ? peripherals[0].split("=")[1] : "";
                json1.put("latitude", latitude);
                json1.put("longitude", longitude);

                json1.put(audioField, audioPath);
                json1.put("created_at", json1.get("currentDate") + " " + json1.get("currentTime"));
                dataSendModel.insertRecord(json1);
            }
         else{
              if (map.containsKey("Images") || map.containsKey("Image") && map.containsKey("Audio")) {
                String imageField = peripherals[0].trim().split("=")[0].equals("Image") || peripherals[0].split("=")[0].equals("Images") ? peripherals[0].split("=")[1] : "";
                String audioField = peripherals[1].trim().split("=")[0].equals("Audio") ? peripherals[1].split("=")[1] : "";
                json1.put("latitude", latitude);
                json1.put("longitude", longitude);
                json1.put(imageField, imagePath);
                json1.put(audioField, audioPath);
                json1.put("created_at", json1.get("currentDate") + " " + json1.get("currentTime"));
                dataSendModel.insertRecord(json1);
            }
            }

        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return "success";
    }

    @POST
    @Path("/DetailList1")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public String test3(String inputJsonObj) throws org.codehaus.jettison.json.JSONException, IOException {
        DataSendWebServiceModel dataSendModel = new DataSendWebServiceModel();
        JSONParser parser = new JSONParser();
        dataSendModel.setConnection();
        JSONObject json = new JSONObject(inputJsonObj);

        OutputStream out1 = null;
        File fileaudio = new File(inputJsonObj);

        String currentDate = "";

        String Year = "";
        String Month = "";
        String day = "";
        String tableName = "";

        tableName = json.get("TableName").toString();
        currentDate = json.get("currentDate").toString();
        String date[] = currentDate.split(" ");
        String check[] = currentDate.split(",");
        Month = date[0];
        day = date[1];
        Year = check[1];

        try {

            String destination_path = "";
            String fileName = "";
            String audioPath = "";

            String getBackEncodedString = json.get("audio").toString();
            byte[] audioAsBytes = new BASE64Decoder().decodeBuffer(getBackEncodedString);
            fileName = (json.get("fileName").toString());

            if (fileName.isEmpty()) {
                fileName = "out.mp3";
            }
            destination_path = dataSendModel.getDestinationPath("DetailList");

//         destination_path = "C:\\Users\\DELL\\Pictures\\audio";
            audioPath = destination_path + "/audios/" + Year + "/" + Month + "/" + day + "/" + tableName;
            dataSendModel.makeDirectory(audioPath);

            String file = audioPath + "/" + fileName;

            out1 = new FileOutputStream(file);
            out1.write(audioAsBytes);
            out1.close();

//                dataSendModel.insertImageRecord(fileName);
            System.out.println("audio insert ");
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return "success";
    }

}
