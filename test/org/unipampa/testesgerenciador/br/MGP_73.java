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
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import org.unipampa.tests.evidenceerror.TestingSupport;

/**
 *
 * @author Juliana
 */
public class MGP_73 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec/";
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
    }

    @Test
    public void emailValido() throws Exception {
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-73(EmailValido).xml");

            List<String[]> entrada = parser.extractDataXML("casodeteste", atributosEmail());
            for (String[] strings : entrada) {

                if (strings[0].contains("gmail")) {
                    System.out.println(strings[0]);
                    driver.get("https://gmail.com");
                    driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(strings[0]);
                    driver.findElement(By.id("identifierNext")).click();
                    driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(strings[1]);
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
                    driver.findElement(By.id("passwordNext")).click();
                    //driver.findElement(By.xpath("//*[@id=\"gbqfq\"]")).sendKeys("in:spam gerenciador pampatec");
                    //driver.findElement(By.xpath("//*[@id=\"gbqfb\"]")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
                    driver.findElements(By.className("bog")).get(0).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("//*[@id=\":9h\"]/div[1]/div[2]/form/div/a[2]")));
                    driver.findElement(By.xpath("//*[@id=\":jw\"]/div[1]/div[2]/form/div/a[2]/strong")).click();
                } else if (strings[0].contains("hotmail") || strings[0].contains("outlook")) {
                    driver.get("https://www.live.com/");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idSIButton9\"]")));
                    driver.findElement(By.xpath("//*[@id=\"i0116\"]")).sendKeys(strings[0]);
                    driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idSIButton9\"]")));
                    driver.findElement(By.xpath("//*[@id=\"i0118\"]")).sendKeys(strings[1]);
                    driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("lvHighlightAllClass lvHighlightSubjectClass ms-fwt-sb ms-fcl-tp")));
                    driver.findElements(By.className("lvHighlightAllClass lvHighlightSubjectClass ms-fwt-sb ms-fcl-tp")).get(0).click();
                }

                //Connection.updateResults("Criar valido cadastrado", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
            }

        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Criar cadastro");

            //Connection.updateResults("Email valido cadastrado", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void emailInvalido() throws Exception {
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-73(EmailInvalido).xml");

            List<String[]> entrada = parser.extractDataXML("casodeteste", atributosEmail());
            for (String[] strings : entrada) {

                if (strings[0].contains("gmail")) {
                    System.out.println(strings[0]);
                    driver.get("https://gmail.com");
                    driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys(strings[0]);
                    driver.findElement(By.id("identifierNext")).click();
                    driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(strings[1]);
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
                    driver.findElement(By.id("passwordNext")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
                    driver.findElements(By.className("bog")).get(0).click();
                    driver.findElement(By.xpath("//*[@id=\":9h\"]/div[1]/div[2]/form/div/a[2]")).click();
                } else if (strings[0].contains("hotmail") || strings[0].contains("outlook")) {
                    driver.get("https://www.live.com/");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idSIButton9\"]")));
                    driver.findElement(By.xpath("//*[@id=\"i0116\"]")).sendKeys(strings[0]);
                    driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idSIButton9\"]")));
                    driver.findElement(By.xpath("//*[@id=\"i0118\"]")).sendKeys(strings[1]);
                    driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
                }

                //Connection.updateResults("Email inválido", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
            }

        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Criar cadastro");

            //Connection.updateResults("Email inválido", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    private List<String> atributosEmail() {
        List<String> atributosCadastro = new ArrayList();
        atributosCadastro.add("Email");
        atributosCadastro.add("Senha");
        return atributosCadastro;
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
