/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author YURY
 */
public class MGP_59 {

    public static WebDriver driver;
    public static String url = "http://192.168.56.101:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "e462370c35a05bab566ee54b202b6a23";

    /**
     * Método que será executado antes de cada caso de teste com a finalidade de
     * abrir o navegador.
     *
     * @throws MalformedURLException
     */
    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
    }

    @Test
    public void verificarTodosOsFiltros() throws TestLinkAPIException {
        System.out.println("Verificar todos os Filtros");
        boolean error = false;
        StringBuilder errorMessage = new StringBuilder();
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();
            if (!driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT_data\"]/tr[1]/td[4]")).
                    getText().contains("Em elaboração")
                    && driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT_data\"]/tr[2]/td[4]")).
                            getText().contains("Em Pré-Avaliação")) {
                errorMessage.append("Filtro \"Todos\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[2]")).click();
            if (!driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Em elaboração")) {
                errorMessage.append("Filtro \"Em elaboração\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[3]")).click();
            if (!driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Em pré-avaliação")) {
                errorMessage.append("Filtro \"Em pré-avaliação\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[4]")).click();
            if (driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Necessita melhoria")) {
                errorMessage.append("Filtro \"Necessita melhoria\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[5]")).click();
            if (driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Aceito para avaliação")) {
                errorMessage.append("Filtro \"Aceito para avaliação\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[6]")).click();
            if (driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Em formalização")) {
                errorMessage.append("Filtro \"Em formalização\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[7]")).click();
            if (driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Incubação")) {
                errorMessage.append("Filtro \"Incubação\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:j_idt47\"]/div/select/option[8]")).click();
            if (driver.findElement(By.id("lista_planos:singleDT_data")).getText().contains("Reprovado")) {
                errorMessage.append("Filtro \"Reprovado\" não funcionando.\n");
                error = true;
                saveScreenShot();
            }

            if (error) {
                throw new Exception(errorMessage.toString());
            }
            
            Connection.updateResults("Verificar todos os filtros", null,
                    TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Connection.updateResults("Verificar todos os filtros", e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());

        }
    }

    private void saveScreenShot() throws Exception {
        TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "evidenciaserro", "Verificar todos os filtros");
    }

    /**
     * Método que contém a finalidade de fechar o navegador depois de cada
     * execução.
     */
    @After
    public void closeBrowser() {
        driver.quit();
    }

}
