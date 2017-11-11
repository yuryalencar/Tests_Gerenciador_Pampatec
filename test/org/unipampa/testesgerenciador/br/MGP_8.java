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
    public static ParserXML parserAux;
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

    //<editor-fold defaultstate="collapsed" desc="Métodos auxiliares">
    private void zerarCustos() {
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosFixos:0:j_idt166\"]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosFixos:0:j_idt162\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosFixos:0:j_idt162\"]")).sendKeys("0");

        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosFixos:1:j_idt166\"]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosFixos:1:j_idt162\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosFixos:1:j_idt162\"]")).sendKeys("0");
        System.out.println("aquifoi");

        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:botao_adicionar_nova_linha\"]")).click();
    }

    private void adicionarLinhasVariaveis(List<Integer> lines) {
        int cont = 0;
        for (Integer line : lines) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:botao_adicionar_nova_linhaVariavel\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:botao_adicionar_nova_linhaVariavel\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosVariaveis:" + cont + ":j_idt187\"]/span[1]")).click();
            driver.findElement(By.id("formulario_cadastro_projeto:novaTabelaCustosVariaveis:" + cont + ":j_idt183")).sendKeys(String.valueOf(line));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:novaTabelaCustosVariaveis:" + cont + ":j_idt187\"]/span[2]")).click();
            cont++;
        }
    }

    private void preencherCampos(boolean dataValid, List<Integer> lines, String nameMethod,
        boolean addEmpreendedoresError, boolean custoZero) throws Exception {
        int cont = 1, casoTeste = 1;
        boolean error = false;
        String errorMessage = "O Teste apontou erros nos respectivos casos de teste presentes no arquivo:";
        List<String[]> inputData = parser.extractDataXML("casodeteste", nomesAtributosSubmeterPlano());

        for (String[] inputs : inputData) {
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
            if (!driver.getTitle().equals("Página Principal - Empreendedor")) {
                throw new Exception("Verifique o pré-requisito.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")).click();

            if (addEmpreendedoresError) {
                List<String> value = new ArrayList();
                value.add("email");
                List<String[]> inputDataEmpreendedorError = parserAux.extractDataXML("casoteste", value);
                wait.until(ExpectedConditions.elementToBeClickable(By.id("formEquipe:autocomplete_input")));
                for (String[] input : inputDataEmpreendedorError) {
                    Thread.sleep(2000);
                    driver.findElement(By.id("formEquipe:autocomplete_input")).sendKeys(input[0]);
                    driver.findElement(By.id("formEquipe:j_idt203")).click();
                }
            }
            if (!addEmpreendedoresError) {
                wait.until(ExpectedConditions.elementToBeClickable(By.id("formEquipe:botaoSalvar1")));
            }

            driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.name("formulario_cadastro_projeto:j_idt65")));
            driver.findElement(By.name("formulario_cadastro_projeto:j_idt65")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:empresaProjeto")));
            driver.findElement(By.id("formulario_cadastro_projeto:empresaProjeto")).sendKeys(inputs[0]);
            driver.findElement(By.id("formulario_cadastro_projeto:segmentoDeClientes")).sendKeys(inputs[1]);
            driver.findElement(By.id("formulario_cadastro_projeto:propostaDeValor")).sendKeys(inputs[2]);
            driver.findElement(By.id("formulario_cadastro_projeto:atividadesChave")).sendKeys(inputs[3]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar2")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabAnaliseMercado")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:relacoComClientes")));
            driver.findElement(By.id("formulario_cadastro_projeto:relacoComClientes")).sendKeys(inputs[4]);
            driver.findElement(By.id("formulario_cadastro_projeto:parceriasChaves")).sendKeys(inputs[5]);
            driver.findElement(By.id("formulario_cadastro_projeto:canais")).sendKeys(inputs[6]);
            driver.findElement(By.id("formulario_cadastro_projeto:recursosPrincipais")).sendKeys(inputs[7]);
            driver.findElement(By.id("formulario_cadastro_projeto:concorrentes")).sendKeys(inputs[8]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar3")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabProdutoServico")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]/option[" + cont + "]")).click();
            driver.findElement(By.id("formulario_cadastro_projeto:tecnologiaProcessos")).sendKeys(inputs[9]);
            driver.findElement(By.id("formulario_cadastro_projeto:potencialInovacaoTecnologica")).sendKeys(inputs[10]);
            driver.findElement(By.id("formulario_cadastro_projeto:aplicacoes")).sendKeys(inputs[11]);
            driver.findElement(By.id("formulario_cadastro_projeto:dificuldadesEsperadas")).sendKeys(inputs[12]);
            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaUniversidade")).sendKeys(inputs[13]);
            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno")).sendKeys(inputs[14]);
            driver.findElement(By.id("formulario_cadastro_projeto:infraestrutura")).sendKeys(inputs[15]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar4")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabGestaoPessoas")).click();
            driver.findElement(By.id("formulario_cadastro_projeto:participacaoAcionaria")).sendKeys(inputs[16]);
            driver.findElement(By.id("formulario_cadastro_projeto:potencialEmprego")).sendKeys(inputs[17]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar5")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabPlanoFinanceiro")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:fontesDeReceita")));
            driver.findElement(By.id("formulario_cadastro_projeto:fontesDeReceita")).sendKeys(inputs[18]);
            driver.findElement(By.id("formulario_cadastro_projeto:estruturaCustos")).sendKeys(inputs[19]);
            driver.findElement(By.id("formulario_cadastro_projeto:investimentoInicial")).sendKeys(inputs[20]);
            if (!lines.isEmpty()) {
                adicionarLinhasVariaveis(lines);
            }
            if (custoZero) {
                zerarCustos();
            }
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar6")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("botao_submeter")));
            driver.findElement(By.id("botao_submeter")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form_enviar_projeto:j_idt221\"]")));
            driver.findElement(By.xpath("//*[@id=\"form_enviar_projeto:j_idt221\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSubmeter\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSubmeter\"]/div/div/div[3]/input")).click();
            if (dataValid) {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"modalInfoSubmeter\"]/div/div/div[3]/input")));
                if (driver.findElement(By.id("formulario_cadastro_projeto:mensagemSalvamento")).getText().equals("")
                        && !addEmpreendedoresError && !custoZero) {
                    error = true;
                    errorMessage += "\nCaso de teste: " + casoTeste;
                    if (addEmpreendedoresError) {
                        errorMessage += " - Foi possível submeter com os empreendedores com cadastro incompleto.";
                    }
                    screenShotError(nameMethod);
                } else if (driver.findElement(By.id("formulario_cadastro_projeto:mensagemErroSubmissao")).getText().equals("")
                        && addEmpreendedoresError) {
                    error = true;
                    errorMessage += "\nCaso de teste: " + casoTeste;
                    if (addEmpreendedoresError) {
                        errorMessage += " - Foi possível submeter com os empreendedores com cadastro incompleto.";
                    }
                    screenShotError(nameMethod);
                } else if (driver.findElement(By.id("formulario_cadastro_projeto:mensagemErroSubmissao")).getText().equals("")
                        && !addEmpreendedoresError && custoZero) {
                    error = true;
                    errorMessage += "\nCaso de teste: " + casoTeste;
                    if (addEmpreendedoresError) {
                        errorMessage += " - Foi possível submeter com os custos iguais a zero.";
                    }
                    screenShotError(nameMethod);
                }
            } else {
                if (driver.findElement(By.id("formulario_cadastro_projeto:mensagemErroSubmissao")).getText().equals("")) {
                    error = true;
                    if (cont == 7) {
                        errorMessage += "\nCaso de teste: " + casoTeste + " Obs.: Com o Outros não especificado e sem Custos Variáveis";
                    } else {
                        errorMessage += "\nCaso de teste: " + casoTeste + " Obs.: Sem Custos Variáveis";
                    }
                    screenShotError(nameMethod);
                }
            }
            if (cont == 7) {
                cont = 0;
            }
            cont++;
            casoTeste++;
        }

        if (error) {
            throw new Exception(errorMessage);
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

    private void screenShotError(String nameMethod) throws Exception {
        TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "evidenciaserro", nameMethod);
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Finalizados">
    @Ignore
    @Test
    public void submeterPlanoDeNegocioCustoZero() throws TestLinkAPIException {
        System.out.println("Submeter plano de negócio com Custos iguais a zero.");
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-8(SubmeterPlanoDadosValidos).xml");

            preencherCampos(true, new ArrayList(), "Submeter plano de negócio com Custos iguais a zero", false, true);

            Connection.updateResults("Submeter plano de negócio com Custos iguais a zero.",
                    null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            Connection.updateResults("Submeter plano de negócio com Custos iguais a zero.",
                    e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void submeterPlanoDeNegocioCadastroIncompletoEmpreendedores() throws TestLinkAPIException {
        System.out.println("Submeter plano de negócio com empreendedores possuindo cadastro incompleto.");
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-8(SubmeterPlanoDadosValidos).xml");

            parserAux = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-8(EmailsInvalidosENaoCadastrados).xml");

            preencherCampos(true, new ArrayList(),
                    "Submeter plano de negócio com empreendedores possuindo cadastro incompleto", true, false);

            Connection.updateResults("Submeter plano de negócio com empreendedores possuindo cadastro incompleto.",
                    null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            Connection.updateResults("Submeter plano de negócio com empreendedores possuindo cadastro incompleto.",
                    e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Exemplo de método de teste utilizando reports integrados com o TestLink.
     *
     * @throws Exception
     */
    @Ignore
    @Test
    public void submeterPlanoDeNegocioDadosInvalidos() throws Exception {
        System.out.println("Submeter Plano de Negócio com Dados inválidos.");
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-8(SubmeterPlanoDadosInvalidos).xml");

        try {
            preencherCampos(false, new ArrayList<Integer>(), "Submeter plano de negócio com Dados inválidos", false, false);
            Connection.updateResults("Submeter plano de negócio com Dados inválidos.", null,
                    TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {

            Connection.updateResults("Submeter plano de negócio com Dados inválidos.", e.getMessage() + "\nObs.: Lista com os dados utilizados fornecidas"
                    + " no MantisBT e em anexo no TestLink.",
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Exemplo de método de teste utilizando reports integrados com o TestLink.
     *
     * @throws Exception
     */
    @Ignore
    @Test
    public void submeterPlanoDeNegocioCompletoDadosValidos() throws Exception {
        System.out.println("Submeter Plano de Negócio Completo com Dados Válidos.");
        parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "datatests" + System.getProperty("file.separator")
                + "MGP-8(SubmeterPlanoDadosValidos).xml");
        try {
            ArrayList<Integer> lines = new ArrayList();
            lines.add(20);
            lines.add(0);
            lines.add(100000);
            preencherCampos(true, lines, "Submeter plano de negócio com Dados Válidos", false, false);
            Connection.updateResults("Submeter plano de negócio com Dados Válidos.", null,
                    TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {

            Connection.updateResults("Submeter plano de negócio com Dados Válidos.", e.getMessage() + ". Sendo o mesmo"
                    + " preenchendo todos os campos utilizando valores válidos.",
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Exemplo de método de teste utilizando reports integrados com o TestLink.
     *
     * @throws Exception
     */
    @Ignore
    @Test
    public void submeterPlanoDeNegocioEmBranco() throws Exception {
        System.out.println("Submeter Plano de  Negócio em Branco.");

        try {

            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
            if (!driver.getTitle().equals("Página Principal - Empreendedor")) {
                throw new Exception("Verifique o pré-requisito.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formEquipe:botaoSalvar1")));
            driver.findElement(By.id("formEquipe:botaoSalvar1")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.name("formulario_cadastro_projeto:j_idt65")));
            driver.findElement(By.name("formulario_cadastro_projeto:j_idt65")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("botao_submeter")));
            driver.findElement(By.id("botao_submeter")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form_enviar_projeto:j_idt221\"]")));
            driver.findElement(By.xpath("//*[@id=\"form_enviar_projeto:j_idt221\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSubmeter\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSubmeter\"]/div/div/div[3]/input")).click();

            if (!driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt69:0:notificacaoErroSubmissao\"]")).isDisplayed()) {
                throw new Exception("Foi possível submeter um projeto em branco.");
            }

            Connection.updateResults("Submeter plano de negócio em branco.", null,
                    TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {

            TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "evidenciaserro", "Submeter plano de negócio em branco ");

            Connection.updateResults("Submeter plano de negócio em branco.", e.getMessage(),
                    TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);

            Assert.fail(e.getMessage());
        }
    }
    //</editor-fold>

    /**
     * Método que contém a finalidade de fechar o navegador depois de cada
     * execução.
     */
    @After
    public void closeBrowser() {
        driver.quit();
    }

}
