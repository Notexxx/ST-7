package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        WebDriver driver = new ChromeDriver(options);

        try {
            // --- Задание 1: генератор паролей ---
            System.out.println("=== Задание 1: Генератор паролей ===");
            driver.get("https://www.calculator.net/password-generator.html");
            try {
                WebElement passwordField = driver.findElement(
                        By.cssSelector("input.infofield"));
                System.out.println("Сгенерированный пароль: "
                        + passwordField.getAttribute("value"));
            } catch (Exception e) {
                System.out.println("Не удалось найти поле пароля: " + e.getMessage());
            }

            // --- Задание 2: IP-адрес ---
            System.out.println("\n=== Задание 2: IP-адрес ===");
            Task2.getIP(driver);

            // --- Задание 3: Прогноз погоды ---
            System.out.println("\n=== Задание 3: Прогноз погоды ===");
            Task3.getForecast(driver);

        } finally {
            driver.quit();
        }
    }
}
