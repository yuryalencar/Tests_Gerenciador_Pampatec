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
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Juliana
 */
public class MGP_68 {

    public static WebDriver driver;
    public static String url = "http://192.168.150.102:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "8305a296244bd063e868bf5a357946d0";

    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Login.autenticar(driver, "novotestetestezin@gmail.com", "123", url);
    }

    @Test
    public void visualizarCampoModificado() throws Exception {
        try {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")));
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select")));
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select")).click();
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[4]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]/span[2]")));
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]/span[2]")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("tabNegocio")));

            driver.findElement(By.id("tabNegocio")).click();
            Thread.sleep(2000);

            if (!driver.findElement(By.xpath("//*[@id=\"formularioParte02\"]/div[1]/div[2]")).getText().equals("O empreendedor editou este campo desde a última avaliação")) {
                throw new Exception("Modificação no plano de negócio não encontrada");
            }

            Connection.updateResults("Visualizar campos modificados", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Visualizar campos modificados");

            Connection.updateResults("Visualizar campos modificados", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
