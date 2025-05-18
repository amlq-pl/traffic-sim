package pl.jakubjanor;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        String inputJsonString = args[0];
        String outputJsonString = args[1];

        JSONObject inputJSONObject = new JSONObject(inputJsonString);
        JSONObject outputJSONObject = new JSONObject(outputJsonString);
    }
}