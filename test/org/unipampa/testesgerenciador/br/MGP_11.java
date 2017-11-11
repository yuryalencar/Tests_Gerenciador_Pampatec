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
import static org.unipampa.testesgerenciador.br.Login.timeToSleep;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucas
 */
public class MGP_11 {

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
        attributesName.add("Email");
        return attributesName;
    }

    //<editor-fold defaultstate="collapsed" desc="Funções Auxiliares">
    private void cadastrarEmails() throws Exception {

        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-11(ObservadoresValidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

        for (String[] input : inputData) {
            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"formEquipe:autocomplete_input\"]")).sendKeys(input[0]);

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"formEquipe:j_idt203\"]")).click();

        }

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]/span[2]")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")).click();

    }

    private void deletarEmails() throws Exception {

        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-11(ObservadoresValidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

        for (int i = inputData.size(); i >= 0; i--) {
            Thread.sleep(500);
            if (driver.findElement(By.xpath("//*[@id=\"formEquipe:tabelaEmpreendedores:" + i + ":botaoExcluirEmpreendedor\"]/span[2]")).isEnabled()) {
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"formEquipe:tabelaEmpreendedores:" + i + ":botaoExcluirEmpreendedor\"]/span[2]")).click();
            }
        }

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]/span[2]")).click();

        Thread.sleep(timeToSleep);
        driver.findElement(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")).click();

    }
    //</editor-fold>

    @Test
    public void cadastrarObservadoresValidos() throws Exception {

        String nomeCasoTeste = "Cadastrar Observadores Válidos";

        System.out.println(nomeCasoTeste);
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-11(ObservadoresValidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        try {
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")).click();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

            for (String[] input : inputData) {
                Thread.sleep(500);

                driver.findElement(By.xpath("//*[@id=\"formEquipe:autocomplete_input\"]")).sendKeys(input[0]);
                Thread.sleep(500);

                driver.findElement(By.xpath("//*[@id=\"formEquipe:j_idt203\"]")).click();
            }

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]/span[2]")).click();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")).click();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

            List<String> comparisonData = new ArrayList();

            for (String[] string : inputData) {
                comparisonData.add(string[0]);
            }

            comparisonData.add("testetestezin@gmail.com");

            boolean error = false;

            for (int i = 1; i <= comparisonData.size(); i++) {
                Thread.sleep(timeToSleep);
                if (!comparisonData.contains(driver.findElement(By.xpath("//*[@id=\"formEquipe:tabelaEmpreendedores_data\"]/tr[" + i + "]/td[1]")).getText())) {
                    error = true;
                }
            }

            if (error) {
                throw new Exception("Erro ao conferir emails cadastrados");
            } else {

            }

            deletarEmails();

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
    public void cadastrarObservadoresInvalidos() throws Exception {

        String nomeCasoTeste = "Cadastrar Observadores Inválidos";

        System.out.println(nomeCasoTeste);
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-11(ObservadoresInvalidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        try {
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")).click();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

            for (String[] input : inputData) {
                Thread.sleep(500);

                driver.findElement(By.xpath("//*[@id=\"formEquipe:autocomplete_input\"]")).sendKeys(input[0]);
                Thread.sleep(500);

                driver.findElement(By.xpath("//*[@id=\"formEquipe:j_idt203\"]")).click();

                Thread.sleep(500);
                if (!driver.findElement(By.xpath("//*[@id=\"formEquipe:mensagemErroEquipe\"]")).isDisplayed()) {
                    throw new Exception("Erro ao cadastrar email inválido");
                }

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
    public void verificarStatus() throws Exception {

        String nomeCasoTeste = "Verificar Status";

        System.out.println(nomeCasoTeste);
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-11(ObservadoresValidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        try {

            cadastrarEmails();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

            boolean error = false;

            for (int i = 1; i <= inputData.size(); i++) {
                if (!(driver.findElement(By.xpath("//*[@id=\"formEquipe:tabelaEmpreendedores_data\"]/tr[" + i + "]/td[1]"))).isDisplayed()) {
                    error = true;
                }
            }

            if (error) {
                throw new Exception("Erro ao conferir status dos emails cadastrados");
            } else {

            }

            deletarEmails();

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
    public void cadastrarEmailsJaCadastrados() throws Exception {

        String nomeCasoTeste = "Cadastrar Emails Já Cadastrados";

        System.out.println(nomeCasoTeste);
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-11(ObservadoresValidos).xml");
        List<String[]> inputData;

        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        try {

            cadastrarEmails();

            Thread.sleep(timeToSleep);
            driver.findElement(By.xpath("//*[@id=\"botao_elaboracao_equipe\"]")).click();

            for (String[] input : inputData) {
                Thread.sleep(timeToSleep);
                driver.findElement(By.xpath("//*[@id=\"formEquipe:autocomplete_input\"]")).sendKeys(input[0]);

                Thread.sleep(timeToSleep);
                driver.findElement(By.xpath("//*[@id=\"formEquipe:j_idt203\"]")).click();

                if (!(driver.findElement(By.xpath(" //*[@id=\"formEquipe:mensagemErroEquipe\"]"))).isDisplayed()) {
                    throw new Exception("Erro ao tentar cadastrar email já cadastrado");
                }
            }

            deletarEmails();

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
