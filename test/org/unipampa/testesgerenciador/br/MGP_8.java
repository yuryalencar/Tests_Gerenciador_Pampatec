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
 * Exemplo de Classe de testes usando a integração com a finalidade de se fazer
 * vários reports.
 *
 * @author Yury Alencar Lima
 */
public class MGP_8 {

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
        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
    }

    /**
     * Método que cria a lista de atributos a serem extraídas do XML com a
     * finalidade de servirem como dados de entrada para os casos de teste
     * especificados.
     *
     * @return List<String> contendo os dados a serem retirados.
     */
    private List<String> nomesAtributosSubmeterPlano() {
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
        attributesName.add("estagioEvolucao");
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

    /**
     * Exemplo de método de teste utilizando reports integrados com o TestLink.
     *
     * @throws Exception
     */
    @Test
    public void submeterPlanoDeTestesCompleto() throws Exception {
        System.out.println("Submeter Plano de Testes Completo.");
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-8(SubmeterPlanoDadosInvalidos).xml");
        List<String[]> inputData;

        try {
            if (driver.getTitle().equals("Página Principal - Empreendedor")) {
                throw new Exception("Verifique o pré-requisito.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Novo Plano")));
            driver.findElement(By.linkText("Novo Plano")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formEquipe:botaoSalvar1")));
            driver.findElement(By.id("formEquipe:botaoSalvar1")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.name("formulario_cadastro_projeto:j_idt65")));
            driver.findElement(By.name("formulario_cadastro_projeto:j_idt65")).click();

            inputData = parser.extractDataXML("casodeteste", nomesAtributosSubmeterPlano());

            for (String[] inputs : inputData) {
                //Apagar os dados ou não??
                driver.findElement(By.id("formulario_cadastro_projeto:empresaProjeto")).sendKeys(inputs[0]);
                driver.findElement(By.id("formulario_cadastro_projeto:segmentoDeClientes")).sendKeys(inputs[1]);
                driver.findElement(By.id("formulario_cadastro_projeto:propostaDeValor")).sendKeys(inputs[2]);
                driver.findElement(By.id("formulario_cadastro_projeto:atividadesChave")).sendKeys(inputs[3]);
                driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar2")).click();

                driver.findElement(By.id("tabAnaliseMercado")).click();
                driver.findElement(By.id("formulario_cadastro_projeto:relacoComClientes")).sendKeys(inputs[4]);
                driver.findElement(By.id("formulario_cadastro_projeto:parceriasChaves")).sendKeys(inputs[5]);
                driver.findElement(By.id("formulario_cadastro_projeto:canais")).sendKeys(inputs[6]);
                driver.findElement(By.id("formulario_cadastro_projeto:recursosPrincipais")).sendKeys(inputs[7]);
                driver.findElement(By.id("formulario_cadastro_projeto:concorrentes")).sendKeys(inputs[8]);
                driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar3")).click();

                driver.findElement(By.id("tabProdutoServico")).click();
                driver.findElement(By.id("formulario_cadastro_proj0eto:estagioDeEvolucao")).click();
                driver.findElement(By.linkText(inputs[9])).click();
                driver.findElement(By.id("formulario_cadastro_projeto:tecnologiaProcessos")).sendKeys(inputs[10]);
                driver.findElement(By.id("formulario_cadastro_projeto:potencialInovacaoTecnologica")).sendKeys(inputs[11]);
                driver.findElement(By.id("formulario_cadastro_projeto:aplicacoes")).sendKeys(inputs[12]);
                driver.findElement(By.id("formulario_cadastro_projeto:dificuldadesEsperadas")).sendKeys(inputs[13]);
                driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaUniversidade")).sendKeys(inputs[14]);
                driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno")).sendKeys(inputs[15]);
                driver.findElement(By.id("formulario_cadastro_projeto:infraestrutura")).sendKeys(inputs[16]);
                driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar4")).click();

                driver.findElement(By.id("tabGestaoPessoas")).click();
                driver.findElement(By.id("formulario_cadastro_projeto:participacaoAcionaria")).sendKeys(inputs[17]);
                driver.findElement(By.id("formulario_cadastro_projeto:potencialEmprego")).sendKeys(inputs[18]);
                driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar5")).click();

                driver.findElement(By.id("tabPlanoFinanceiro")).click();
                driver.findElement(By.id("formulario_cadastro_projeto:fontesDeReceita")).sendKeys(inputs[19]);
                driver.findElement(By.id("formulario_cadastro_projeto:estruturaCustos")).sendKeys(inputs[20]);
                driver.findElement(By.id("formulario_cadastro_projeto:investimentoInicial")).sendKeys(inputs[21]);
                driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar6")).click();

                driver.findElement(By.id("botao_submeter")).click();
                driver.findElement(By.id("form_enviar_projeto:j_idt221")).click();
                driver.findElement(By.className("btn btn-success")).click();
                if (driver.findElement(By.className("fa fa-exclamation-circle fa-2x")) != null) {
                    throw new Exception("ERRO AO SUBMETER PLANO DE NEGÓCIO COM TODOS OS DADOS VÁLIDOS.");
                }
            }

            Connection.updateResults("Submeter plano de negócio Completo.", null,
                    TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {

            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Submeter Plano de Testes Completo");

            Connection.updateResults("Submeter plano de negócio Completo.", e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
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
