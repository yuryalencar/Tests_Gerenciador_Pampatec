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
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucas
 */
public class MGP_7 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "cf444f32c54e4371a53aac0aacc56108";
    public String mensagemErro;
    public boolean erro;

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

        attributesName.add("nomeProjeto");
        attributesName.add("segmentoClientes");
        attributesName.add("propostaValor");
        attributesName.add("atividadesChave");
        attributesName.add("relacoesClientes");
        attributesName.add("parceriasChave");
        attributesName.add("canais");
        attributesName.add("recursos");
        attributesName.add("concorrentes");
        attributesName.add("tecnologia");
        attributesName.add("potencialInovacao");
        attributesName.add("aplicacoes");
        attributesName.add("dificuldades");
        attributesName.add("interacaoUniversidade");
        attributesName.add("interacaoGoverno");
        attributesName.add("infraestrutura");
        attributesName.add("descricaoParticipacao");
        attributesName.add("potencialGeracaoEmprego");
        attributesName.add("fontesReceita");
        attributesName.add("estruturaCustos");
        attributesName.add("investimentoInicial");

        return attributesName;
    }

    public void verificaMensagemSalvamento(String nomeCampo) {
        if (!driver.findElement(By.xpath("//*[@id='formulario_cadastro_projeto:notificacaoSalvo']")).isDisplayed()) {
            mensagemErro += " " + nomeCampo;
            erro = true;
        }
    }

    public boolean verificaCampoSalvo(String nomeCampo, String mensagemErro) {
        if (!driver.findElement(By.xpath("//*[@id='formulario_cadastro_projeto:notificacaoSalvo']")).isDisplayed()) {
            mensagemErro += " " + nomeCampo;
            return true;
        }
        return false;
    }

    @Test
    public void testarMensagemSalvamentoAutomatico() throws Exception {

        String nomeCasoTeste = "Mensagem Salvar Automaticamente";
        mensagemErro = "Erro no campo(s):";
        erro = false;

        System.out.println(nomeCasoTeste);
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-7(DadosValidos).xml");

        List<String[]> inputData;
        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        String[] inputs = inputData.get(0);

        try {
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

            //<editor-fold defaultstate="collapsed" desc="Chegar até o plano de negocio">
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formEquipe:botaoSalvar1")));
            driver.findElement(By.id("formEquipe:botaoSalvar1")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.name("formulario_cadastro_projeto:j_idt65")));
            driver.findElement(By.name("formulario_cadastro_projeto:j_idt65")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:empresaProjeto")));
            //</editor-fold>

            driver.findElement(By.id("formulario_cadastro_projeto:empresaProjeto")).sendKeys(inputs[0]);
            verificaMensagemSalvamento("Nome Projeto");

            driver.findElement(By.id("formulario_cadastro_projeto:segmentoDeClientes")).sendKeys(inputs[1]);
            verificaMensagemSalvamento("Segmento de Clientes");

            driver.findElement(By.id("formulario_cadastro_projeto:propostaDeValor")).sendKeys(inputs[2]);
            verificaMensagemSalvamento("Proposta de Valor");

            driver.findElement(By.id("formulario_cadastro_projeto:atividadesChave")).sendKeys(inputs[3]);
            verificaMensagemSalvamento("Atividades Chave");

            //Mudando a Tab
            driver.findElement(By.id("tabAnaliseMercado")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:relacoComClientes")));

            driver.findElement(By.id("formulario_cadastro_projeto:relacoComClientes")).sendKeys(inputs[4]);
            verificaMensagemSalvamento("Relaciomento com Clientes");

            driver.findElement(By.id("formulario_cadastro_projeto:parceriasChaves")).sendKeys(inputs[5]);
            verificaMensagemSalvamento("Parecias Chaves");

            driver.findElement(By.id("formulario_cadastro_projeto:canais")).sendKeys(inputs[6]);
            verificaMensagemSalvamento("Canais");

            driver.findElement(By.id("formulario_cadastro_projeto:recursosPrincipais")).sendKeys(inputs[7]);
            verificaMensagemSalvamento("Recursos Principais");

            driver.findElement(By.id("formulario_cadastro_projeto:concorrentes")).sendKeys(inputs[8]);
            verificaMensagemSalvamento("Concorrentes");

            //Mudando tab
            driver.findElement(By.id("tabProdutoServico")).click();

            driver.findElement(By.id("formulario_cadastro_projeto:tecnologiaProcessos")).sendKeys(inputs[9]);
            verificaMensagemSalvamento("Tecnologia e Processos");

            driver.findElement(By.id("formulario_cadastro_projeto:potencialInovacaoTecnologica")).sendKeys(inputs[10]);
            verificaMensagemSalvamento("Inovação Tecnologica");

            driver.findElement(By.id("formulario_cadastro_projeto:aplicacoes")).sendKeys(inputs[11]);
            verificaMensagemSalvamento("Aplicações");

            driver.findElement(By.id("formulario_cadastro_projeto:dificuldadesEsperadas")).sendKeys(inputs[12]);
            verificaMensagemSalvamento("Dificuldades Esperadas");

            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaUniversidade")).sendKeys(inputs[13]);
            verificaMensagemSalvamento("Interação Empresa e Universidade");

            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno")).sendKeys(inputs[14]);
            verificaMensagemSalvamento("Interação Empresa e Governo");

            driver.findElement(By.id("formulario_cadastro_projeto:infraestrutura")).sendKeys(inputs[15]);
            verificaMensagemSalvamento("Inraestrutura");

            //Mudando de Tab
            driver.findElement(By.id("tabGestaoPessoas")).click();

            driver.findElement(By.id("formulario_cadastro_projeto:participacaoAcionaria")).sendKeys(inputs[16]);
            verificaMensagemSalvamento("Descrição da participação acionária");

            driver.findElement(By.id("formulario_cadastro_projeto:potencialEmprego")).sendKeys(inputs[17]);
            verificaMensagemSalvamento("Potencial de geração de emprego e renda");

            //Mudando de Tab
            driver.findElement(By.id("tabPlanoFinanceiro")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:fontesDeReceita")));

            driver.findElement(By.id("formulario_cadastro_projeto:fontesDeReceita")).sendKeys(inputs[18]);
            verificaMensagemSalvamento("Fontes de Receita");

            driver.findElement(By.id("formulario_cadastro_projeto:estruturaCustos")).sendKeys(inputs[19]);
            verificaMensagemSalvamento("Estrutura de Custos");

            driver.findElement(By.id("formulario_cadastro_projeto:investimentoInicial")).sendKeys(inputs[20]);
            verificaMensagemSalvamento("Investimento Inicial");

            if (erro) {
                throw new Exception(mensagemErro);
            } else {
                Connection.updateResults(nomeCasoTeste, null,
                        TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
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
    } 
    

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
