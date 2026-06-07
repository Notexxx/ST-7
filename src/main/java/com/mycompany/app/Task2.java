package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {

    public static String getIP(WebDriver driver) {
        try {
            driver.get("https://api.ipify.org/?format=json");
            WebElement elem = driver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            String ip = (String) obj.get("ip");
            System.out.println("Мой IP-адрес: " + ip);
            return ip;
        } catch (Exception e) {
            System.out.println("Ошибка Task2: " + e.toString());
            return null;
        }
    }
}
