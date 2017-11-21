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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author YURY
 */
public class MGP_27 {

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
    }

    @Test
    public void aprovarPlano() throws TestLinkAPIException, Exception {
        String nameMethod = "Aprovar um plano de negócio.";
        boolean error = false;
        int cont = 0;
        String errorMessage = "Ocorreu um erro para aprovar um plano e foram:\n";
        System.out.println(nameMethod);

        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-27(Observações).xml");
        List<String> observations = new ArrayList();
        observations.add("observations");
        List<String[]> data = parser.extractDataXML("casodeteste", observations);

        try {
            for (String[] strings : data) {
                Login.autenticar(driver, "gerentedefault1@gmail.com", "gerente123", url);
                cont++;
                
                //@TODO
                
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")).click();

                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[1]/td[1]/div/div[2]/span")).click();
            
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:campoObservacoes\"]")).sendKeys(strings);
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:botaoEnviar\"]")).click();
                
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")).click();
                
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                if(!driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios_data\"]/tr[1]")).getText().toLowerCase().contains("aceito para avaliação")){
                    error = true;
                    errorMessage += "\nCaso de teste: "+cont+" - Falhou";
                }
            }

            if (error) {
                throw new Exception(errorMessage);
            }

            Connection.updateResults(nameMethod, null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Connection.updateResults(nameMethod, e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Método para tirar um print, criar a pasta e salvar o mesmo la dentor com
     * evidência do erro.
     *
     * @param nameMethod
     * @throws Exception
     */
    private void saveScreenShot(String nameMethod) throws Exception {
        TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "evidenciaserro", nameMethod);
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
