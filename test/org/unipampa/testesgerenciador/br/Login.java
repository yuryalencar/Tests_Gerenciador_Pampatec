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
            driver.findElement(By.id("formularioDeLogin:emailInput")).sendKeys(email);
            driver.findElement(By.id("formularioDeLogin:senhaInput")).sendKeys(senha);
            driver.findElement(By.id("formularioDeLogin:botaoLogin")).click();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
