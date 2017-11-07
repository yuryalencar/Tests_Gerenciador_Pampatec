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
 * @author Juliana
 */
public class MPG_3 {

    public static WebDriver driver;
    public static String url = "http://192.168.56.101:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "8305a296244bd063e868bf5a357946d0";

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
    public void visualizarStatusPlano() throws Exception {
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-3(VisualizarStatusPlano).xml");
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();
            List<String> lista = new ArrayList();
            lista.add("StatusPlano");
            List<String[]> entrada = parser.extractDataXML("casodeteste", lista);
            int cont = 1;
            for (String[] strings : entrada) {
                if (!driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT_data\"]/tr[" + cont + "]/td[4]")).getText().equals(strings[0])) {
                    switch (cont) {
                        case 1:
                            throw new Exception("Erro: Não existe elemento em pré-avaliação");
                        case 2:
                            throw new Exception("Erro: Não existe elemento em elaboração");
                    }
                    
                }
                cont++;
            }
            
            Connection.updateResults("Visualizar status do plano de negócio", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
            

        } catch (Exception e) {

            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Visualizar Status Plano");

            Connection.updateResults("Visualizar status do plano de negócio", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
