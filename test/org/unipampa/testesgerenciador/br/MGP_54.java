/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import static org.unipampa.testesgerenciador.br.Login.timeToSleep;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucas
 */
public class MGP_54 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "cf444f32c54e4371a53aac0aacc56108";
    boolean erro;
    String mensagemErro;

    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //<editor-fold defaultstate="collapsed" desc="Funções Auxiliares">
    private void verificaBordaVermelha(WebElement element) {

        String corElemento = element.getCssValue("border-color");

        if (!corElemento.equalsIgnoreCase("rgb(255, 0, 0)")) {
            erro = true;
            mensagemErro += element.getAttribute("name") + ";\n";
        }

    }

    //</editor-fold>
    @Test
    public void visualizarComentarios() throws Exception {

        String nomeCasoTeste = "Visualizar comentários";
        System.out.println(nomeCasoTeste);
        int numeroComentarios = 23;

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        erro = false;
        mensagemErro = "Existe erro nos campos:\n";

        try {
            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/a")));
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lista_planos:singleDT:2:visualizar\"]/span[2]")));
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:2:visualizar\"]/span[2]")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior:botao_revisar\"]")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior:botao_revisar\"]")).click();

            WebElement divPrincipal = driver.findElement(By.xpath("//*[@id=\"sessao_revisar_plano_pre_melhoria\"]"));

            List<WebElement> listaCamposTexto = divPrincipal.findElements(By.className("campoInvalido"));

            List<WebElement> listaBotoesComentario = divPrincipal.findElements(By.className("botaoBaseComentario"));

            for (WebElement campoTexto : listaCamposTexto) {
                verificaBordaVermelha(campoTexto);
            }

            if (listaBotoesComentario.size() != numeroComentarios) {
                erro = true;
                mensagemErro += "Erro não existe botões para visualização de comentários para todos os campos";
            }

            if (erro) {
                throw new Exception(mensagemErro);
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
    public void verificarNaoExistenciaBotoesComentarios() throws Exception {

        String nomeCasoTeste = "Verificar a não existência de comentários";
        System.out.println(nomeCasoTeste);
        int numeroComentarios = 1;

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        erro = false;
        mensagemErro = "Existe erro nos campos:\n";

        try {
            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/a")));
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/a")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")));
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:1:visualizar\"]/span[2]")).click();

            Thread.sleep(timeToSleep);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior:botao_revisar\"]")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior:botao_revisar\"]")).click();

            WebElement divPrincipal = driver.findElement(By.xpath("//*[@id=\"sessao_revisar_plano_pre_melhoria\"]"));

            List<WebElement> listaCamposTexto = divPrincipal.findElements(By.className("campoInvalido"));

            List<WebElement> listaBotoesComentario = divPrincipal.findElements(By.className("botaoBaseComentario"));

            if (listaCamposTexto.size() != numeroComentarios) {
                erro = true;
                mensagemErro += "Erro existe mais campos de texto com borda vermelha do que deveria\n";
            }

            if (listaBotoesComentario.size() != numeroComentarios) {
                erro = true;
                mensagemErro += "Erro não existe botões para visualização de comentários para todos os campos\n";
            }

            if (erro) {
                throw new Exception(mensagemErro);
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
