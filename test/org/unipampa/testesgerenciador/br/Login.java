/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;

/**
 *
 * @author YURY
 */
public class Login {

    public static WebDriver driver;
    public static String url = "http://192.168.56.101:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;

    /**
     * Método para realizar o Login que é uma pré-condição para os testes desta
     * classe.
     */
    public static void autenticar(String email, String senha) {
        try {
            WebElement eElement;
            driver.manage().deleteAllCookies();
            driver.get(url);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeLogin:emailInput")));
            eElement = driver.findElement(By.id("formularioDeLogin:emailInput"));
            eElement.click();
            eElement.sendKeys(email);

            eElement = driver.findElement(By.id("formularioDeLogin:senhaInput"));
            eElement.click();
            eElement.sendKeys(senha);

            driver.findElement(By.id("formularioDeLogin:botaoLogin")).click();

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
