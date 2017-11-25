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
import static org.unipampa.testesgerenciador.br.Login.timeToSleep;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucas
 */
public class MGP_55 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "cf444f32c54e4371a53aac0aacc56108";
    private String caminhoBase = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "test" + System.getProperty("file.separator")
            + "org" + System.getProperty("file.separator")
            + "unipampa" + System.getProperty("file.separator")
            + "testesgerenciador" + System.getProperty("file.separator")
            + "datatests" + System.getProperty("file.separator");

    private boolean erro;
    private String mensagemErro = "Erro nos elementos: ";

    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //<editor-fold defaultstate="collapsed" desc="Metodos Auxiliares">
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

    private void elementClick(String xpath) throws Exception {
        Thread.sleep(timeToSleep);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    private void elementInput(String xpath, String data) throws Exception {
        Thread.sleep(timeToSleep);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(data);
    }

    private void elementVerificarTexto(String xpath, String data) throws InterruptedException {
        Thread.sleep(timeToSleep);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        if (!driver.findElement(By.xpath(xpath)).getText().equalsIgnoreCase(data)) {
            erro = true;
            mensagemErro += driver.findElement(By.xpath(xpath)).getAttribute("name") + " ";
        }
    }

    private void elementVerificaHistorico(String xpath) throws InterruptedException {
        Thread.sleep(timeToSleep);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        if (!driver.findElement(By.xpath(xpath)).isDisplayed()) {
            erro = true;
            mensagemErro += driver.findElement(By.xpath(xpath)).getAttribute("name") + " ";
        }
    }

    private void criarPlano() throws Exception {

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        parser = new ParserXML(caminhoBase + "MGP-55(DadosCriarPlano).xml");

        List<String[]> inputData;
        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        String[] inputs = inputData.get(0);

        //<editor-fold defaultstate="collapsed" desc="Criando o plano de negocio">
        elementClick("/html/body/div[1]/div[2]/a");

        elementClick("//*[@id=\"lista_planos\"]/div/div[1]/div[3]/a");

        elementClick("//*[@id=\"formEquipe:botaoSalvar1\"]");

        elementClick("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Preenchendo aba Negócio">
        elementInput("//*[@id=\"formulario_cadastro_projeto:empresaProjeto\"]", inputs[0]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:segmentoDeClientes\"]", inputs[1]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:propostaDeValor\"]", inputs[2]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:atividadesChave\"]", inputs[3]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Preenchendo aba Análise de Mercado">
        elementClick("//*[@id=\"tabAnaliseMercado\"]");

        elementInput("//*[@id=\"formulario_cadastro_projeto:relacoComClientes\"]", inputs[4]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:parceriasChaves\"]", inputs[5]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:canais\"]", inputs[6]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:recursosPrincipais\"]", inputs[7]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:concorrentes\"]", inputs[8]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Produto ou Serviço">
        elementClick("//*[@id=\"tabProdutoServico\"]");

        elementInput("//*[@id=\"formulario_cadastro_projeto:tecnologiaProcessos\"]", inputs[9]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:potencialInovacaoTecnologica\"]", inputs[10]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:aplicacoes\"]", inputs[11]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:dificuldadesEsperadas\"]", inputs[12]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:interacaoEmpresaUniversidade\"]", inputs[13]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno\"]", inputs[14]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:infraestrutura\"]", inputs[15]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Gestão de Pessoas">
        elementClick("//*[@id=\"tabGestaoPessoas\"]");

        elementInput("//*[@id=\"formulario_cadastro_projeto:participacaoAcionaria\"]", inputs[16]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:potencialEmprego\"]", inputs[17]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Plano Financeiro">
        elementClick("//*[@id=\"tabPlanoFinanceiro\"]");

        elementInput("//*[@id=\"formulario_cadastro_projeto:fontesDeReceita\"]", inputs[18]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:estruturaCustos\"]", inputs[19]);
        elementInput("//*[@id=\"formulario_cadastro_projeto:investimentoInicial\"]", inputs[20]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Submetendo o Plano">
        elementClick("//*[@id=\"botao_submeter\"]");
        elementClick("//*[@id=\"form_enviar_projeto:j_idt221\"]");
        elementClick("//*[@id=\"modalInfoSubmeter\"]/div/div/div[3]/input");
        //</editor-fold>

    }

    private void comentarPlano() throws Exception {

        Login.autenticar(driver, "petersonrodrigues@ideiah.com", "123", url);

        parser = new ParserXML(caminhoBase + "MGP-55(DadosComentarPlano).xml");

        List<String[]> inputData;
        inputData = parser.extractDataXML("casodeteste", nomesAtributos());

        String[] inputs = inputData.get(0);

        //<editor-fold defaultstate="collapsed" desc="Chegando até o projeto">
        elementClick("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a");
        elementClick("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a");
        elementInput("//*[@id=\"locovelho:tabelaDeNegocios:globalFilter\"]", inputs[0]);
        elementClick("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]");
        //</editor-fold>        

        //<editor-fold defaultstate="collapsed" desc="Comentando aba Negócio">
        elementClick("//*[@id=\"tabNegocio\"]");

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioSegmento\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:segmentoDeCliente2\"]", inputs[1]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioProposta\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:propostaDeValor2\"]", inputs[2]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioAtividades\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:atividadesChave2\"]", inputs[3]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Comentando aba Análise de Mercado">
        elementClick("//*[@id=\"tabAnaliseMercado\"]");

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioRelacao\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:relacaoClientes2\"]", inputs[4]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioParceria\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:parceriasChave2\"]", inputs[5]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioCanais\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:canais2\"]", inputs[6]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioRecursos\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:recursosPrincipais2\"]", inputs[7]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioConcorrentes\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:concorrentes2\"]", inputs[8]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Comentando Aba Produto ou Serviço">
        elementClick("//*[@id=\"tabProdutoServico\"]");

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioTecnologia\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:tecnologiaProcessos2\"]", inputs[9]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioPotencial\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:potencialInovacaoTecnologica2\"]", inputs[10]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioAplicacao\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:aplicacoes2\"]", inputs[11]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioDificuldades\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:dificuldadesEsperadas2\"]", inputs[12]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInteracaoUniversidade\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaUniversidade2\"]", inputs[13]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaGoverno2\"]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:interacaoEmpresaComunidadeGoverno2\"]", inputs[14]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInfraEstrutura\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:infraestrutura2\"]", inputs[15]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Comentando Aba Gestão de Pessoas">
        elementClick("//*[@id=\"tabGestaoPessoas\"]");

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioDescricao\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:participacaoAcionaria2\"]", inputs[16]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioPotencialRenda\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:potencialEmprego2\"]", inputs[17]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Plano Financeiro">
        elementClick("//*[@id=\"tabPlanoFinanceiro\"]");

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioFontes\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:fontesDeReceita2\"]", inputs[18]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioEstruturaCustos\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:estruturaCustos2\"]", inputs[19]);

        elementClick("//*[@id=\"formulario_comentarpreavalizar:adicionarComentarioInvestimentoInicial\"]/span[2]");
        elementInput("//*[@id=\"formulario_comentarpreavalizar:investimentoInicial2\"]", inputs[20]);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Submetendo a avaliação">
        elementClick("//*[@id=\"tabPlanoFinanceiro\"]");
        elementClick("//*[@id=\"formulario_comentarpreavalizar:avaliacao\"]/tbody/tr[2]/td[1]/div/div[2]/span");
        elementClick("//*[@id=\"formulario_comentarpreavalizar:botaoEnviar\"]/span");
        elementClick("//*[@id=\"formulario_comentarpreavalizar:j_idt47\"]");
        //</editor-fold>

    }
    //</editor-fold>

    @Test
    public void verificarSalvarComEspaco() throws Exception {

        String nomeCasoTeste = "Verificar Salvar com Campos em Branco";
        System.out.println(nomeCasoTeste);

        criarPlano();
        comentarPlano();
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        //<editor-fold defaultstate="collapsed" desc="Chegar até o plano">
        elementClick("/html/body/div[1]/div[2]/a");
        elementInput("//*[@id=\"lista_planos:singleDT:globalFilter\"]", "Projeto 2");
        elementClick("//*[@id=\"lista_planos:singleDT:0:visualizar\"]");
        elementClick("//*[@id=\"menuSuperior:botao_revisar\"]");
        //</editor-fold>

        try {

            //<editor-fold defaultstate="collapsed" desc="Preenchendo aba Negócio">
            elementClick("//*[@id=\"tabNegocio\"]");
            elementInput("//*[@id=\"formulario_resubmeterplano:segmentoDeClientes1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:propostaDeValor1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:atividadesChave1\"]", "");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Preenchendo aba Análise de Mercado">
            elementClick("//*[@id=\"tabAnaliseMercado\"]");

            elementInput("//*[@id=\"formulario_resubmeterplano:relacaoClientes1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:parceriasChaves1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:canais1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:recursosPrincipais1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:concorrentes1\"]", "");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Produto ou Serviço">
            elementClick("//*[@id=\"tabProdutoServico\"]");

            elementInput("//*[@id=\"formulario_resubmeterplano:tecnologiaProcessos1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:potencialInovacaoTecnologica1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:aplicacoes1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:dificuldadesEsperadas1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:interacaoEmpresaUniversidade1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:interacaoEmpresaComunidadeGoverno1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:infraestrutura1\"]", "");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Gestão de Pessoas">
            elementClick("//*[@id=\"tabGestaoPessoas\"]");

            elementInput("//*[@id=\"formulario_resubmeterplano:participacaoAcionaria1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:potencialEmprego1\"]", "");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Preenchendo Aba Plano Financeiro">
            elementClick("//*[@id=\"tabPlanoFinanceiro\"]");

            elementInput("//*[@id=\"formulario_resubmeterplano:fontesDeReceita1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:estruturaCustos1\"]", "");
            elementInput("//*[@id=\"formulario_resubmeterplano:investimentoInicial1\"]", "");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Submetendo o Plano">
            elementClick("//*[@id=\"menuSuperior:botao_Ressubmeter\"]");
            elementClick("//*[@id=\"modalInfoDeEnvio\"]/div/div/div/div[3]/input");
            //</editor-fold>

            if (driver.getCurrentUrl().equalsIgnoreCase("http://192.168.130.102:8080/GerenciadorPampatec/view/empreendedor/paginaBuscaPlanoDeNegocio.jsf")) {
                throw new Exception("Erro foi aceito submeter com espaços em branco");
            }

        } catch (Exception e) {
            Connection.updateResults(nomeCasoTeste, e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }

        Connection.updateResults(nomeCasoTeste, null,
                TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

    }

    @Test
    public void verificarSalvarParaEdicao() throws Exception {

        String nomeCasoTeste = "Verificar salvamento da revisão para submissão futura ";
        System.out.println(nomeCasoTeste);

        criarPlano();
        comentarPlano();
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        erro = false;

        //<editor-fold defaultstate="collapsed" desc="Chegar até o plano">
        elementClick("/html/body/div[1]/div[2]/a");
        elementInput("//*[@id=\"lista_planos:singleDT:globalFilter\"]", "Projeto 2");
        elementClick("//*[@id=\"lista_planos:singleDT:0:visualizar\"]");
        elementClick("//*[@id=\"menuSuperior:botao_revisar\"]");
        //</editor-fold>

        try {

            //<editor-fold defaultstate="collapsed" desc="Preenchendo aba Negócio">
            elementInput("//*[@id=\"formulario_cadastro_projeto:empresaProjeto\"]", "alterado");
            elementInput("//*[@id=\"formulario_cadastro_projeto:segmentoDeClientes\"]", "alterado");
            elementInput("//*[@id=\"formulario_cadastro_projeto:propostaDeValor\"]", "alterado");
            elementInput("//*[@id=\"formulario_cadastro_projeto:atividadesChave\"]", "alterado");
            //</editor-fold>

            closeBrowser();
            openBrowser();
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

            //<editor-fold defaultstate="collapsed" desc="Chegar até o plano">
            elementClick("/html/body/div[1]/div[2]/a");
            elementInput("//*[@id=\"lista_planos:singleDT:globalFilter\"]", "Projeto 2");
            elementClick("//*[@id=\"lista_planos:singleDT:0:visualizar\"]");
            elementClick("//*[@id=\"menuSuperior:botao_revisar\"]");
            //</editor-fold>

            elementVerificarTexto("//*[@id=\"formulario_cadastro_projeto:empresaProjeto\"]", "alterado");
            elementVerificarTexto("//*[@id=\"formulario_cadastro_projeto:segmentoDeClientes\"]", "alterado");
            elementVerificarTexto("//*[@id=\"formulario_cadastro_projeto:propostaDeValor\"]", "alterado");
            elementVerificarTexto("//*[@id=\"formulario_cadastro_projeto:atividadesChave\"]", "alterado");

            if (erro) {
                throw new Exception(mensagemErro);
            }

        } catch (Exception e) {
            Connection.updateResults(nomeCasoTeste, e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }

        Connection.updateResults(nomeCasoTeste, null,
                TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

    }

    @Test
    public void verificarhHistorico() throws Exception {

        String nomeCasoTeste = "Verificar Historico de interação entre gerente e empreendedor";
        System.out.println(nomeCasoTeste);

        criarPlano();
        comentarPlano();
        Login.autenticar(driver, "petersonrodrigues@ideiah.com", "123", url);

        //<editor-fold defaultstate="collapsed" desc="Chegando até o projeto">
        elementClick("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/a");
        elementClick("//*[@id=\"j_idt14\"]/nav/div/div[2]/ul/li[2]/ul/li/a");
        elementInput("//*[@id=\"locovelho:tabelaDeNegocios:globalFilter\"]", "Projeto 2");
        elementClick("//*[@id=\"locovelho:tabelaDeNegocios:0:avaliarPlano\"]");
        //</editor-fold>        

        try {

            //<editor-fold defaultstate="collapsed" desc="Verificando aba Negócio">
            elementClick("//*[@id=\"tabNegocio\"]");

            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:segmentoDeClientes1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:propostaDeValor1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:atividadesChave1\"]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando aba Análise de Mercado">
            elementClick("//*[@id=\"tabAnaliseMercado\"]");

            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:relacaoClientes1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:parceriasChaves1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:canais1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:recursosPrincipais1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:concorrentes1\"]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando Aba Produto ou Serviço">
            elementClick("//*[@id=\"tabProdutoServico\"]");

            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:tecnologiaProcessos1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:potencialInovacaoTecnologica1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:aplicacoes1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:dificuldadesEsperadas1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:interacaoEmpresaUniversidade1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:interacaoEmpresaComunidadeGoverno1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:infraestrutura1\"]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando Aba Gestão de Pessoas">
            elementClick("//*[@id=\"tabGestaoPessoas\"]");

            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:participacaoAcionaria1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:potencialEmprego1\"]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando Aba Plano Financeiro">
            elementClick("//*[@id=\"tabPlanoFinanceiro\"]");

            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:fontesDeReceita1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:estruturaCustos1\"]");
            elementVerificaHistorico("//*[@id=\"formulario_resubmeterplano:investimentoInicial1\"]");
            //</editor-fold>

            if (erro) {
                throw new Exception(mensagemErro);
            }

        } catch (Exception e) {
            Connection.updateResults(nomeCasoTeste, e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }

        Connection.updateResults(nomeCasoTeste, null,
                TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);

    }

    @Test
    public void verificarSinalizaoHistorico() throws Exception {

        String nomeCasoTeste = "Verificar sinalização de histórico de comentários";
        System.out.println(nomeCasoTeste);

        criarPlano();
        comentarPlano();
        Login.autenticar(driver, "petersonrodrigues@ideiah.com", "123", url);

        try {

            //<editor-fold defaultstate="collapsed" desc="Verificando aba Negócio">
            elementClick("//*[@id=\"tabNegocio\"]");

            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[0]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[1]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[2]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando aba Análise de Mercado">
            elementClick("//*[@id=\"tabAnaliseMercado\"]");

            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[3]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[4]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[5]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[6]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[7]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando Aba Produto ou Serviço">
            elementClick("//*[@id=\"tabProdutoServico\"]");

            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[8]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[9]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[10]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[11]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[12]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[13]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[14]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando Aba Gestão de Pessoas">
            elementClick("//*[@id=\"tabGestaoPessoas\"]");

            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[15]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[16]");
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Verificando Aba Plano Financeiro">
            elementClick("//*[@id=\"tabPlanoFinanceiro\"]");

            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[17]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[18]");
            elementVerificaHistorico(" //*[@id=\"formularioParte02\"]/div[2]/div[19]");            
            //</editor-fold>

            if (erro) {
                throw new Exception(mensagemErro);
            }

        } catch (Exception e) {
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
