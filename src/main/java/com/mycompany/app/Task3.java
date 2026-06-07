package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {

    public static void getForecast(WebDriver driver) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain"
                    + "&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow"
                    + "&forecast_days=1"
                    + "&wind_speed_unit=ms";

            driver.get(url);
            WebElement elem = driver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);

            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            // Заголовок таблицы
            String header = String.format("%-4s %-20s %-15s %-15s",
                    "№", "Дата/время", "Температура", "Осадки (мм)");
            String line = "-".repeat(56);

            System.out.println("\nПрогноз погоды для Нижнего Новгорода:");
            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            StringBuilder sb = new StringBuilder();
            sb.append("Прогноз погоды для Нижнего Новгорода:\n");
            sb.append(line).append("\n");
            sb.append(header).append("\n");
            sb.append(line).append("\n");

            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                Object tempObj = temps.get(i);
                Object rainObj = rains.get(i);

                String temp = tempObj != null ? tempObj.toString() + " °C" : "N/A";
                String rain = rainObj != null ? rainObj.toString() + " мм" : "N/A";

                String row = String.format("%-4d %-20s %-15s %-15s",
                        i + 1, time, temp, rain);
                System.out.println(row);
                sb.append(row).append("\n");
            }

            System.out.println(line);
            sb.append(line).append("\n");

            // Сохраняем в файл
            try (PrintWriter pw = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                pw.print(sb.toString());
            }
            System.out.println("Прогноз сохранён в result/forecast.txt");

        } catch (Exception e) {
            System.out.println("Ошибка Task3: " + e.toString());
        }
    }
}
