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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import static org.unipampa.testesgerenciador.br.Login.timeToSleep;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucascorrea
 */
public class MGP_14 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "cf444f32c54e4371a53aac0aacc56108";

    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void verificarRevisar() throws Exception {

        String nomeCasoTeste = "Verificar Revisar";
        System.out.println(nomeCasoTeste);

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        try {
            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/a")));
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")));
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"botao_revisar\"]")));          
            driver.findElement(By.xpath("//*[@id=\"botao_revisar\"]")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"div_revisar_plano\"]/div[1]")));
            if (!driver.findElement(By.xpath("//*[@id=\"div_revisar_plano\"]/div[1]")).isDisplayed()) {
                throw new Exception("Não existe o elemento de revisão");
            }

        } catch (Exception e) {

            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", nomeCasoTeste);

            Connection.updateResults(nomeCasoTeste, e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);

            Assert.fail(e.getMessage());

        }

        Connection.updateResults(nomeCasoTeste, null,
                TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
                
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
