package com.accenture.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;

import static java.lang.Thread.sleep;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        String path = getPath();
        String data = getDataFromFile(path);
        JSONObject job = convertToJSON(data);
        Map values = mapAmmountOfValueRedundancies(job);
        System.out.println("Wynik: " + values.toString());
    }

    private static Map mapAmmountOfValueRedundancies(JSONObject job) {
        Map<String,Integer> map = new HashMap();
        int x = 0;

        ArrayList<String> list = null;

        for (String key:job.keySet()) {
            System.out.println(job.get(key));

            list = new ArrayList<>();
            JSONArray jArray = (JSONArray) job.get(key);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    list.add(jArray.getString(i));
                }
            }
        }
        Integer tmpValue = 1;
        for (String key:list) {

            if(!map.containsKey(key)){
                map.put(key, tmpValue);
            } else {
                Integer val = map.get(key);
                map.put(key, val+1);
            }
            x++;
        }

        return map;
    }

    private static JSONObject convertToJSON(String data) {

        JSONObject jsonObject = new JSONObject(data);
        System.out.println("Przekonwertowano");
        return jsonObject;

    }

    private static String getDataFromFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder sb = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(ls);
            }
            System.out.println(sb.toString());
            return sb.toString();
        } finally {
            reader.close();
        }
    }

    private static String getPath(){
        String x;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path for JSON file: ");
        x = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        System.out.println(sb);
        return sb.toString();
    }
}
