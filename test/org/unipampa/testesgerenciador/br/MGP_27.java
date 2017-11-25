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
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author YURY
 */
public class MGP_27 {

    public static WebDriver driver;
    public static String url = "http://192.168.1.113:8080/GerenciadorPampatec/";
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

    private void preencherObservacoes(List<String[]> data) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[2]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[2]")).click();

        System.out.println("Tab1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[2]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[2]")).click();

        System.out.println("item 1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioSegmento\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioSegmento\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:segmentoDeCliente2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:segmentoDeCliente2\"]")).sendKeys(data.get(0)[0]);

        System.out.println("item 2");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioProposta\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:propostaDeValor2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:propostaDeValor2\"]")).sendKeys(data.get(0)[1]);

        System.out.println("item 3");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioAtividades\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:atividadesChave2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:atividadesChave2\"]")).sendKeys(data.get(0)[2]);

        System.out.println("Tab2");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[3]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[3]")).click();

        System.out.println("item 1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioRelacao\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioRelacao\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:relacaoClientes2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:relacaoClientes2\"]")).sendKeys(data.get(0)[3]);

        System.out.println("item 2");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioParceria\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:parceriasChave2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:parceriasChave2\"]")).sendKeys(data.get(0)[4]);

        System.out.println("item 3");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioCanais\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:canais2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:canais2\"]")).sendKeys(data.get(0)[5]);

        System.out.println("item 4");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioRecursos\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:recursosPrincipais2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:recursosPrincipais2\"]")).sendKeys(data.get(0)[6]);

        System.out.println("item 5");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioConcorrentes\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:concorrentes2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:concorrentes2\"]")).sendKeys(data.get(0)[7]);

        System.out.println("Tab3");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[4]")).click();

        System.out.println("item 1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioEstagio\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioEstagio\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:estagioEvolucao2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:estagioEvolucao2\"]")).sendKeys(data.get(0)[8]);

        System.out.println("item 2");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioTecnologia\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:tecnologiaProcessos2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:tecnologiaProcessos2\"]")).sendKeys(data.get(0)[9]);

        System.out.println("item 3");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioPotencial\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:potencialInovacaoTecnologica2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:potencialInovacaoTecnologica2\"]")).sendKeys(data.get(0)[10]);

        System.out.println("item 4");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioAplicacao\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:aplicacoes2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:aplicacoes2\"]")).sendKeys(data.get(0)[11]);

        System.out.println("item 5");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioDificuldades\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:dificuldadesEsperadas2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:dificuldadesEsperadas2\"]")).sendKeys(data.get(0)[12]);

        System.out.println("item 6");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInteracaoUniversidade\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaUniversidade2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaUniversidade2\"]")).sendKeys(data.get(0)[13]);

        System.out.println("item 7");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInteracaoComunidade\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaComunidadeGoverno2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaComunidadeGoverno2\"]")).sendKeys(data.get(0)[14]);

        System.out.println("item 8");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInfraEstrutura\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:infraestrutura2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:infraestrutura2\"]")).sendKeys(data.get(0)[15]);

        System.out.println("Tab4");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[5]")).click();

        System.out.println("item 1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioDescricao\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioDescricao\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:participacaoAcionaria2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:participacaoAcionaria2\"]")).sendKeys(data.get(0)[16]);

        System.out.println("item 2");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioPotencialRenda\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:potencialEmprego2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:potencialEmprego2\"]")).sendKeys(data.get(0)[17]);

        System.out.println("Tab5");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[6]")).click();

        System.out.println("item 1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioFontes\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioFontes\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:fontesDeReceita2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:fontesDeReceita2\"]")).sendKeys(data.get(0)[18]);

        System.out.println("item 2");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioEstruturaCustos\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:estruturaCustos2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:estruturaCustos2\"]")).sendKeys(data.get(0)[19]);

        System.out.println("item 3");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInvestimentoInicial\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:investimentoInicial2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:investimentoInicial2\"]")).sendKeys(data.get(0)[20]);

        System.out.println("item 4");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioCustosFixos\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:comentarioCustoFixo2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:comentarioCustoFixo2\"]")).sendKeys(data.get(0)[21]);

        System.out.println("item 5");
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioCustosVariaveis\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:comentarioCustoVariavel2\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:comentarioCustoVariavel2\"]")).sendKeys(data.get(0)[22]);

    }

    @Test
    public void submeterPlanoComMelhoriasInvalido() throws TestLinkAPIException, Exception {
        String nameMethod = "Submeter necessidade de melhoria em um plano de negócio com dados inválidos.";
        boolean error = false;
        int cont = 0;
        String errorMessage = "Ocorreu um erro para Submeter necessidade de melhoria em um plano de negócio com dados inválidos:\n";
        System.out.println(nameMethod);

        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-27(ObservaçõesNecessitaMelhoriasInvalido).xml");
        List<String> observations = new ArrayList();
        for (int i = 1; i < 24; i++) {
            observations.add("data" + i);
        }
        List<String[]> data = parser.extractDataXML("casodeteste", observations);

        try {
            for (String[] strings : data) {
                Login.autenticar(driver, "gerentedefault1@ideiah.com", "gerente123", url);
                cont++;
                // Entrar no local para avaliar
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")));
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();

                // Escolher a opção no comboBox
                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[3]")).click();
                Thread.sleep(1500);

                // Escolher o primeiro plano
                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]")).click();

                preencherObservacoes(data);

                // Ir para a tab de avaliação
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")).click();

                
                // Escolher a opção (Radio)
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[2]/td[1]/div/div[2]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[2]/td[1]/div/div[2]")).click();

                if (!driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:mensagemStatusAvaliacao\"]")).getText().trim().equals("Para solicitar melhorias é necessário adicionar ao menos um comentário sugerindo melhoria.")) {
                    throw new Exception("Erro é possível submeter plano com dados em branco.");
                }
            }

            if (error) {
                throw new Exception(errorMessage);
            }

            Connection.updateResults(nameMethod, null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {

            saveScreenShot(nameMethod);

            // Botão de terminar pré avaliação
            driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:botaoEnviar\"]")).click();

            // Confirmação final
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")).click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
            if (driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios_data\"]/tr[1]")).getText().toLowerCase().contains("necessita melhoria")) {
                error = true;
                errorMessage += "\nCaso de teste: " + cont + " - Falhou";
                saveScreenShot(nameMethod);
            }

            System.out.println(errorMessage);
            Connection.updateResults(nameMethod, errorMessage, TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void submeterPlanoComMelhoriasValido() throws TestLinkAPIException, Exception {
        String nameMethod = "Submeter necessidade de melhoria em um plano de negócio.";
        boolean error = false;
        int cont = 0;
        String errorMessage = "Ocorreu um erro para Submeter necessidade de melhoria em um plano de negócio:\n";
        System.out.println(nameMethod);

        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-27(ObservaçõesNecessitaMelhoriasValido).xml");
        List<String> observations = new ArrayList();
        for (int i = 1; i < 24; i++) {
            observations.add("data" + i);
        }
        List<String[]> data = parser.extractDataXML("casodeteste", observations);

        try {
            for (String[] strings : data) {
                Login.autenticar(driver, "gerentedefault1@ideiah.com", "gerente123", url);
                cont++;
                // Entrar no local para avaliar
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")));
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();

                // Escolher a opção no comboBox
                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[3]")).click();
                Thread.sleep(1500);

                // Escolher o primeiro plano
                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]")).click();

                preencherObservacoes(data);

                // Ir para a tab de avaliação
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")).click();

                // Escolher a opção (Radio)
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[2]/td[1]/div/div[2]")).click();

                // Botão de terminar pré avaliação
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:botaoEnviar\"]")).click();

                // Confirmação final
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")).click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                if (!driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios_data\"]/tr[1]")).getText().toLowerCase().contains("necessita melhoria")) {
                    error = true;
                    errorMessage += "\nCaso de teste: " + cont + " - Falhou";
                    saveScreenShot(nameMethod);
                }
            }

            if (error) {
                throw new Exception(errorMessage);
            }

            Connection.updateResults(nameMethod, null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            saveScreenShot(nameMethod);
            Connection.updateResults(nameMethod, e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void reprovarPlano() throws TestLinkAPIException, Exception {
        String nameMethod = "Reprovar um plano de negócio.";
        boolean error = false;
        int cont = 0;
        String errorMessage = "Ocorreu um erro para reprovar um plano de negócio:\n";
        System.out.println(nameMethod);

        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-27(ObservaçõesReprovado).xml");
        List<String> observations = new ArrayList();
        observations.add("observations");
        List<String[]> data = parser.extractDataXML("casodeteste", observations);

        try {
            for (String[] strings : data) {
                Login.autenticar(driver, "gerentedefault1@ideiah.com", "gerente123", url);
                cont++;
                // Entrar no local para avaliar
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")));
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();

                // Escolher a opção no comboBox
                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[3]")).click();
                Thread.sleep(1500);

                // Escolher o primeiro plano
                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]")).click();

                // Ir para a tab de avaliação
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")).click();

                // Informar os dados de entrada no campo de observações
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:campoObservacoes\"]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:campoObservacoes\"]")).sendKeys(strings);

                // Escolher a opção (Radio)
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[3]/td[1]/div/div[2]")).click();

                // Botão de terminar pré avaliação
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:botaoEnviar\"]")).click();

                // Confirmação final
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")).click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                if (!driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios_data\"]/tr[1]")).getText().toLowerCase().contains("reprovado")) {
                    error = true;
                    errorMessage += "\nCaso de teste: " + cont + " - Falhou";
                    saveScreenShot(nameMethod);
                }
            }

            if (error) {
                throw new Exception(errorMessage);
            }

            Connection.updateResults(nameMethod, null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            saveScreenShot(nameMethod);
            Connection.updateResults(nameMethod, e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
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
                Login.autenticar(driver, "gerentedefault1@ideiah.com", "gerente123", url);
                cont++;

                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")));
                driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();

                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[3]")).click();
                Thread.sleep(1500);

                driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]")).click();

                System.out.println("1");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar\"]/div[3]/div/div[1]/ul/li[7]")).click();

                System.out.println("2");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:campoObservacoes\"]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:campoObservacoes\"]")).sendKeys(strings);

                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[1]/td[1]/div/div[2]")).click();

                System.out.println("3");

                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:botaoEnviar\"]")).click();

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                driver.findElement(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")).click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]")));
                if (!driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios_data\"]/tr[1]")).getText().toLowerCase().contains("aceito para avaliação")) {
                    error = true;
                    errorMessage += "\nCaso de teste: " + cont + " - Falhou";
                    saveScreenShot(nameMethod);
                }
            }

            if (error) {
                throw new Exception(errorMessage);
            }

            Connection.updateResults(nameMethod, null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            saveScreenShot(nameMethod);
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
