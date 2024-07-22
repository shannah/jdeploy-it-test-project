package com.github.shannah.jdeployittestproject;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class JdeployItTestProject {

    public static void main(String[] args) {
        File outputFile = new File(System.getProperty("user.home"), "jdeploy-it-test-project.json");
        JSONObject systemProperties = new JSONObject();
        System.getProperties().forEach((k, v) -> {
            systemProperties.put(k.toString(), v.toString());
        });

        JSONObject environment = new JSONObject();
        System.getenv().forEach((k, v) -> {
            environment.put(k, v);
        });

        JSONObject json = new JSONObject();
        json.put("systemProperties", systemProperties);
        json.put("environment", environment);

        try (FileWriter writer = new FileWriter(outputFile)) {
            System.out.println("Writing to "+outputFile);
            json.write(writer);
            System.out.println("Successfully wrote to "+outputFile);
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to write to "+outputFile);
            System.exit(1);
        }
    }
}
