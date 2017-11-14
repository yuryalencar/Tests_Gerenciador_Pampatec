/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;

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
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucascorrea
 */
public class MPG_6 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "8305a296244bd063e868bf5a357946d0";

    @Before
    public void OpenBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
    }

    @Test
    public void verificarExemplosItemPlanoNegocio() throws Exception {

        try {
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]/span[2]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")).click();

            //Segmento de Clientes
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt78\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt78\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                throw new Exception("Exemplo de Segmento de Clientes não encontrado!");
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Proposta de Valor
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt82\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt82\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                throw new Exception("Exemplo de Proposta de valor não encontrado!");
            }

            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Atividade Chave
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt86\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt86\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                throw new Exception("Exemplo de Atividades Chave não encontrado!");
            }

            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Click para nova aba "Análise de Mercado"!
            driver.findElement(By.id("tabAnaliseMercado")).click();

            //Relação com Cliente
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt92\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt92\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                throw new Exception("Exemplo de Relação com Cliente não encontrado!");
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Parceria Chave
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt96\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt96\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Dica")) {
                throw new Exception("Exemplo de Parcerias Chave não encontrado!");
            }

            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Canais
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt100\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt100\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                throw new Exception("Exemplo de Canais não encontrado!");
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Recursos Principais
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt104\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt104\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                throw new Exception("Exemplo de Recursos Principais não encontrado!");
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Click para nova aba "Produto ou Serviço"
            driver.findElement(By.id("tabProdutoServico")).click();

            //Tecnologia e processos (não poluentes)
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt122\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt122\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Dica")) {
                throw new Exception("Exemplo de Tecnologia e Processos não encontrado!");
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            //Click para nova aba "Plano Financeiro"
            driver.findElement(By.id("tabPlanoFinanceiro")).click();

            //Fonte de Receita
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt146\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt146\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")));
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Dica")) {
                throw new Exception("Exemplo de Fontes de Receitas não encontrado!");
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            Connection.updateResults("Verificar Descrição e Exemplos dos Itens de Plano de Negócio", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Verificar Descrição e Exemplos dos Itens de Plano de Negócio");
            Connection.updateResults("Verificar Descrição e Exemplos dos Itens de Plano de Negócio", null, TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());

        }

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
