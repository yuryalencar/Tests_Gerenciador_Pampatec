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

/**
 *
 * @author YURY
 */
public class Login {

    public static WebDriverWait wait;

    /**
     * Método para realizar o Login que é uma pré-condição para os testes desta
     * classe.
     *
     * @param driver
     * @param email
     * @param senha
     * @param url
     */
    public static void autenticar(WebDriver driver, String email, String senha, String url) {
        try {
            driver.manage().deleteAllCookies();
            driver.get(url);
            
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeLogin:emailInput")));
            driver.findElement(By.id("formularioDeLogin:emailInput")).sendKeys(email);
            driver.findElement(By.id("formularioDeLogin:senhaInput")).sendKeys(senha);

            driver.findElement(By.id("formularioDeLogin:botaoLogin")).click();

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
