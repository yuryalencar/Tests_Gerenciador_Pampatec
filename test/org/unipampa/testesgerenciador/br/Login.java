/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author YURY
 */
public class Login {

    public static int timeToSleep = 500;
    
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
            Thread.sleep(timeToSleep);
            driver.findElement(By.id("formularioDeLogin:emailInput")).sendKeys(email);
            Thread.sleep(timeToSleep);
            driver.findElement(By.id("formularioDeLogin:senhaInput")).sendKeys(senha);
            Thread.sleep(timeToSleep);
            driver.findElement(By.id("formularioDeLogin:botaoLogin")).click();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
