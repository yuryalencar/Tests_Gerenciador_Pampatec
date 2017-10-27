package org.unipampa.testesgerenciador.br;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unipampa.manipuladorxml.parserxml.br.ParserXML;
import testlink.api.java.client.TestLinkAPIResults;

/**
 * Exemplo de Classe de testes usando a integração com a finalidade de se fazer
 * vários reports.
 *
 * @author Yury Alencar Lima
 */
public class MPG_8 {

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
        realizarLogin();
    }

    //<editor-fold defaultstate="collapsed" desc="Realizar Login - Pré-Condição && Métodos Auxiliares">
    /**
     * Método para realizar o Login que é uma pré-condição para os testes desta
     * classe.
     */
    private void realizarLogin() {
        try {
            WebElement eElement;
            driver.manage().deleteAllCookies();
            driver.get(url);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeLogin:emailInput")));
            eElement = driver.findElement(By.id("formularioDeLogin:emailInput"));
            eElement.click();
            eElement.sendKeys("testetestezin@gmail.com");

            eElement = driver.findElement(By.id("formularioDeLogin:senhaInput"));
            eElement.click();
            eElement.sendKeys("teste123456");

            driver.findElement(By.id("formularioDeLogin:botaoLogin")).click();

        } catch (Exception e) {
            e.getMessage();
        }
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

//</editor-fold>
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

            Connection.updateResults("Submeter plano de negócio Completo.", null,
                    TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            File diretorio = new File(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Submeter Plano de Testes Completo - Fail");

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            diretorio.mkdirs();

            FileUtils.copyFileToDirectory(screenshot, diretorio);

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
