/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
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
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucas
 */
public class MGP_53 {

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

    private List<String> nomesAtributos() {
        List<String> attributesName = new ArrayList();

        attributesName.add("Usuario");
        attributesName.add("Senha");

        return attributesName;
    }

    @Test
    public void logarEmailSenhaValidos() throws Exception {

        System.out.println("Logar com Email e Senha Válidos");
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-53(SenhaEmailValidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());
        driver.manage().deleteAllCookies();
        driver.get(url);

        try {

            for (String[] data : inputData) {
                driver.findElement(By.xpath("//*[@id='formularioDeLogin:emailInput']")).sendKeys(data[0]);
                driver.findElement(By.xpath("//*[@id='formularioDeLogin:senhaInput']")).sendKeys(data[1]);
                driver.findElement(By.xpath("//*[@id='formularioDeLogin:botaoLogin']/span[2]")).click();

                Connection.updateResults("Logar com usuário e senhas válidos", null,
                        TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
            }

        } catch (Exception e) {

            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Logar com usuário e senhas válidos");

            Connection.updateResults("Logar com usuário e senhas válidos", null,
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);

            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void logarEmailSenhaInvalidos() throws Exception {

        System.out.println("Logar com Email e Senha Inválidos");
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-53(SenhaEmaiInvalidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        try {

            for (String[] data : inputData) {

                driver.manage().deleteAllCookies();
                driver.get(url);

                driver.findElement(By.xpath("//*[@id='formularioDeLogin:emailInput']")).sendKeys(data[0]);
                driver.findElement(By.xpath("//*[@id='formularioDeLogin:senhaInput']")).sendKeys(data[1]);
                driver.findElement(By.xpath("//*[@id='formularioDeLogin:botaoLogin']/span[2]")).click();

                if (driver.findElement(By.xpath("//*[@id='formularioDeLogin']/div[1]/span")) != null) {
                    Connection.updateResults("Logar com usuário e senhas inválidos", null,
                            TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
                } else {
                    throw new Exception("ERRO AO LOGAR COM EMAIL E SENHA INVÁLIDOS.");
                }
            }

        } catch (Exception e) {

            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Logar com usuário e senhas Inválidos");

            Connection.updateResults("Logar com usuário e senhas inválidos", null,
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);

            Assert.fail(e.getMessage());
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
