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
import static org.unipampa.testesgerenciador.br.Login.timeToSleep;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucas
 */
public class MGP_13 {

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
    public void visualizarWorkFlow() throws Exception {

        String nomeCasoTeste = "Visualizar Workflow";
        
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:visualizar\"]/span[2]")).click();

        try {
            if (!driver.findElement(By.xpath("//*[@id=\"progressbar\"]")).isDisplayed()) {
                throw new Exception("Erro ao vizualizar Workflow");
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

    @Test
    public void clickLinksWorkflow() throws Exception {

        String nomeCasoTeste = "Click Link Workflow";
        
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:visualizar\"]/span[2]")).click();

        try {
            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"etapa1\"]/a")).click();
            Thread.sleep(timeToSleep);
            if (!driver.getCurrentUrl().equalsIgnoreCase("http://192.168.130.102:8080/GerenciadorPampatec/view/empreendedor/enviarProjeto.jsf")) {
                throw new Exception("Erro ao clicar no link do Workflow Elaboração");
            }
            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"etapa2\"]/a")).click();
            Thread.sleep(timeToSleep);
            if (!driver.getCurrentUrl().equalsIgnoreCase("http://192.168.130.102:8080/GerenciadorPampatec/view/empreendedor/planoDeNegocio/revisarPlanoDeNegocio.jsf")) {
                throw new Exception("Erro ao clicar no link do Workflow Pré-Avaliação");
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
