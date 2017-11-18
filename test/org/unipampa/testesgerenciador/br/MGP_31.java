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
import static org.unipampa.testesgerenciador.br.MPG_6.wait;
import org.unipampa.tests.evidenceerror.TestingSupport;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Juliana
 */
public class MGP_31 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "8305a296244bd063e868bf5a357946d0";
    String mensagemErro = "Campos que não foram salvos ao comentar:" + "\n";
    int cont;

    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Login.autenticar(driver, "gerentedefault1@ideiah.com", "gerente123", url);
    }

    @Ignore
    @Test
    public void adicionarComentario() throws Exception {
        boolean plano = false;
        try {
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select")).click();
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[3]")).click();
            driver.findElement(By.id("locovelho:tabelaDeNegocios:0:avaliarPlano")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("tabNegocio")));
            Thread.sleep(2000);
            driver.findElement(By.id("tabNegocio")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioSegmento")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:segmentoDeCliente2")).sendKeys("Comentario Segmento Cliente");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioSegmento")).click();
            verificaSalvo("Segmento Cliente");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioProposta")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:propostaDeValor2")).sendKeys("Comentario Proposta de Valor");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioProposta")).click();
            verificaSalvo("Proposta de Valor");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioAtividades")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:atividadesChave2")).sendKeys("Comentario Atividade Chave");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioAtividades")).click();
            verificaSalvo("Atividades Chave");
            Thread.sleep(2000);

            driver.findElement(By.id("tabAnaliseMercado")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioRelacao")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:relacaoClientes2")).sendKeys("Comentario Relação com Clientes");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioRelacao")).click();
            verificaSalvo("Relação com Clientes");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioParceria")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioParceria")).sendKeys("Comentario Parcerias Chave");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioParceria")).click();
            verificaSalvo("Parcerias Chave");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioCanais")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:canais2")).sendKeys("Comentario Canais");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioCanais")).click();
            verificaSalvo("Canais");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioRecursos")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:recursosPrincipais2")).sendKeys("Comentario Recursos Principais");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioRecursos")).click();
            verificaSalvo("Recursos Principais");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioConcorrentes")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:concorrentes2")).sendKeys("Comentario Concorrentes");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioConcorrentes")).click();
            verificaSalvo("Concorrentes");
            Thread.sleep(2000);

            driver.findElement(By.id("tabProdutoServico")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioEstagio")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:estagioEvolucao2")).sendKeys("Comentario Estagio");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioEstagio")).click();
            verificaSalvo("Estágio");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioTecnologia")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:tecnologiaProcessos2")).sendKeys("Comentario Tecnologia e Processo");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioTecnologia")).click();
            verificaSalvo("Tesnologia e Processos");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioPotencial")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:potencialInovacaoTecnologica2")).sendKeys("Comentario Potenciação de Inovação");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioPotencial")).click();
            verificaSalvo("Potencial de Inovação");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioAplicacao")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:aplicacoes2")).sendKeys("Comentario Aplicações");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioAplicacao")).click();
            verificaSalvo("Aplicações");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioDificuldades")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:dificuldadesEsperadas2")).sendKeys("Comentario Dificuldades Esperadas");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioDificuldades")).click();
            verificaSalvo("Dificultades Esperadas");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInteracaoUniversidade")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:interacaoEmpresaUniversidade2")).sendKeys("Comentario Interação Empresa e Universidade");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInteracaoUniversidade")).click();
            verificaSalvo("Interação Empresa e Universidade");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInteracaoComunidade")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:interacaoEmpresaComunidadeGoverno2")).sendKeys("Comentario Interação Empresa, Universidade e Governo");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInteracaoComunidade")).click();
            verificaSalvo("Interação Empresa, Comunidade e Governo");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInfraEstrutura")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:infraestrutura2")).sendKeys("Comentario Infra-Estrutura Necessária");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInfraEstrutura")).click();
            verificaSalvo("Infra-Estrutura Necessária");
            Thread.sleep(2000);

            driver.findElement(By.id("tabGestaoPessoas")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioDescricao")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:participacaoAcionaria2")).sendKeys("Comentario Participação Acionária");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioDescricao")).click();
            verificaSalvo("Participação Acionária");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioPotencialRenda")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:potencialEmprego2")).sendKeys("Comentario Pontencial Geração de Emprego e Renda");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioPotencialRenda")).click();
            verificaSalvo("Potencial Geração de Empregos e Renda");
            Thread.sleep(2000);

            driver.findElement(By.id("tabPlanoFinanceiro")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioFontes")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:fontesDeReceita2")).sendKeys("Comentario Fontes de Receita");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioFontes")).click();
            verificaSalvo("Fonte de Receita");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioEstruturaCustos")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:estruturaCustos2")).sendKeys("Comentario Estrutura de Custos");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioEstruturaCustos")).click();
            verificaSalvo("Estrutura de Custos");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInvestimentoInicial")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:investimentoInicial2")).sendKeys("Comentario Investimento Inicial");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioInvestimentoInicial")).click();
            verificaSalvo("Investimento Inicial");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioCustosFixos")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:comentarioCustoFixo2")).sendKeys("Comentario Custo Fixo");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioCustosFixos")).click();
            verificaSalvo("Custo Fixo");
            Thread.sleep(2000);

            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioCustosVariaveis")).click();
            driver.findElement(By.id("formulario_comentarpreavalizar:comentarioCustoVariavel2")).sendKeys("Comentario Custo Variavel");
            driver.findElement(By.id("formulario_comentarpreavalizar:adicionarComentarioCustosVariaveis")).click();
            verificaSalvo("Custo Variável");
            Thread.sleep(2000);

            if (cont > 0) {
                throw new Exception(mensagemErro);
            }

            //Connection.updateResults("Adicionar comentarios", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            //Connection.updateResults("Adicionar comentarios", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void verificarAjuda() throws Exception {
        String mensagemErroAjuda = "Descrição de Ajuda não encontrado em:" + "\n";
        int erro = 0;
        try {
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select")).click();
            driver.findElement(By.xpath("//*[@id=\"locovelho:tabelaDeNegocios:j_idt41\"]/div/select/option[3]")).click();
            driver.findElement(By.id("locovelho:tabelaDeNegocios:0:avaliarPlano")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("tabNegocio")));
            driver.findElement(By.id("tabNegocio")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt68")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt68")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                mensagemErroAjuda += "Segmento de Cliente" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt89")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt89")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                mensagemErroAjuda += "Proposta de Valor" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt110")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt110")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                mensagemErroAjuda += "Atividades Chave" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("tabAnaliseMercado")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt136")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt136")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                mensagemErroAjuda += "Relação com Clientes" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt157")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt157")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Dica")) {
                mensagemErroAjuda += "Parcerias Chave" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt178")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt178")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                mensagemErroAjuda += "Canais" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt199")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt199")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Exemplo")) {
                mensagemErroAjuda += "Recursos Principais" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("tabProdutoServico")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt263")));

            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt263")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Dica")) {
                mensagemErroAjuda += "Teclogia e Processos" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            driver.findElement(By.id("tabPlanoFinanceiro")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_comentarpreavalizar:j_idt446")));
            driver.findElement(By.id("formulario_comentarpreavalizar:j_idt446")).click();
            Thread.sleep(2000);
            if (!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[2]")).getText().contains("Dica")) {
                mensagemErroAjuda += "Fontes de Receitas" + "\n";
                erro++;
            }
            driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]/div[1]/a/span")).click();
            Thread.sleep(2000);

            if (erro > 0) {
                throw new Exception(mensagemErroAjuda);
            }

            Connection.updateResults("Descrições de Ajuda", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

        } catch (Exception e) {
            Connection.updateResults("Descrições de Ajuda", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void comentarioOutroGerente() throws Exception {
        try {
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a")).click();
            driver.findElement(By.id("locovelho:tabelaDeNegocios:0:avaliarPlano")).click();
            Connection.updateResults("Comentario outro gerente", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

        } catch (Exception e) {
            screenshot("Comentario outro gerente");
            Connection.updateResults("Comentario outro gerente", "Não foi possivel acessar o plano de teste", TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    public void verificaSalvo(String nome) throws Exception {
        if (!driver.findElement(By.id("notificacaoSalvo")).isDisplayed()) {
            mensagemErro += nome + "\n";
            cont++;
            screenshot("Adicionar comentario");
        }
    }

    public void screenshot(String nomeCasoTeste) throws Exception {
        TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "evidenciaserro", nomeCasoTeste);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
