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

    @Ignore
    @Test
    public void emailValidoCadastrado() throws Exception {
        try {
            driver.get("https://gmail.com");
            driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("novotestetestezin@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("teste123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
            driver.findElement(By.id("passwordNext")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"gbqfb\"]")));
            driver.findElement(By.xpath("//*[@id=\"gbqfq\"]")).sendKeys("in:spam gerenciador pampatec");
            driver.findElement(By.xpath("//*[@id=\"gbqfb\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
            driver.findElements(By.className("bog")).get(0).click();

            if (!driver.findElement(By.className("hP")).getText().equals("Gerenciador Pampatec - Confirmação de E-mail")) {
                throw new Exception("Email não recebido para confirmar cadastro!");
            }
            Connection.updateResults("Email valido cadastrado", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Email valido cadastrado");

            Connection.updateResults("Email valido cadastrado", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void emailValidoEditado() throws Exception {
        try {
            driver.get("https://gmail.com");
            driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("testetestezin2@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("teste123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
            driver.findElement(By.id("passwordNext")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"gbqfb\"]")));
            driver.findElement(By.xpath("//*[@id=\"gbqfq\"]")).sendKeys("in:spam gerenciador pampatec");
            driver.findElement(By.xpath("//*[@id=\"gbqfb\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
            driver.findElements(By.className("bog")).get(0).click();

            if (!driver.findElement(By.className("hP")).getText().equals("Gerenciador Pampatec - Confirmação de E-mail")) {
                throw new Exception("Email não recebido para confirmar edição!");
            }

            Connection.updateResults("Email valido editado", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Email valido editado");

            Connection.updateResults("Email valido editado", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void reenviarEmail() throws Exception {
        try {
            Login.autenticar(driver, "novotestetestezin@gmail.com", "teste123456", url);
            driver.findElement(By.id("formReenviaEmail:linkEmail")).click();
            driver.get("https://gmail.com");
            driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("novotestetestezin@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("teste123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
            driver.findElement(By.id("passwordNext")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"gbqfb\"]")));
            driver.findElement(By.xpath("//*[@id=\"gbqfq\"]")).sendKeys("in:spam gerenciador pampatec");
            driver.findElement(By.xpath("//*[@id=\"gbqfb\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
            driver.findElements(By.className("bog")).get(0).click(); // Ultimo e-mail recebido

            if (!driver.findElement(By.className("hP")).getText().equals("Gerenciador Pampatec - Confirmação de E-mail")) {
                throw new Exception("Reenvio de email não enviado!");
            }

            Connection.updateResults("Reenviar email", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Reenviar email");

            Connection.updateResults("Reenviar email", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }

    }

    @Ignore
    @Test
    public void emailNãoMeu() throws Exception {
        try {
            Login.autenticar(driver, "novotestetestezin@gmail.com", "teste123456", url);
            driver.findElement(By.id("formConfirmaEmail:email")).sendKeys("testetestezin2@gmail.com");
            driver.findElement(By.id("formConfirmaEmail:botaoAtualizarEmail")).click();
            driver.get("https://gmail.com");
            driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("testetestezin2@gmail.com");
            driver.findElement(By.id("identifierNext")).click();
            driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("teste123456");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
            driver.findElement(By.id("passwordNext")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"gbqfb\"]")));
            driver.findElement(By.xpath("//*[@id=\"gbqfq\"]")).sendKeys("in:spam gerenciador pampatec");
            driver.findElement(By.xpath("//*[@id=\"gbqfb\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
            driver.findElements(By.className("bog")).get(0).click(); // Ultimo e-mail recebido não spam

            if (!driver.findElement(By.className("hP")).getText().equals("Gerenciador Pampatec - Confirmação de E-mail")) {
                throw new Exception("Email não meu: email não enviado!");
            }
            Connection.updateResults("Email não meu", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Reenviar email");

            Connection.updateResults("Email não meu", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
