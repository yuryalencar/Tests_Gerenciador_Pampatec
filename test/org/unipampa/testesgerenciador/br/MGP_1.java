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
public class MGP_1 {

    public static WebDriver driver;
    public static String url = "http://192.168.56.101:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static ParserXML parserAux;
    public static final String TESTLINK_KEY = "e462370c35a05bab566ee54b202b6a23";

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

    private String voltarVerificar(String[] inputs, String nameMethod) throws Exception {
        String error = "";

        Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")));
        driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")));
        driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
        driver.findElement(By.id("lista_planos:singleDT:0:visualizar")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:empresaProjeto")));
        if (!driver.findElement(By.id("formulario_cadastro_projeto:empresaProjeto")).getText().equals(inputs[0])) {
            error += " - Nome não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:segmentoDeClientes")).getText().equals(inputs[1])) {
            error += " - Segmento de Clientes não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:propostaDeValor")).getText().equals(inputs[2])) {
            error += " - Proposta de valor não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:atividadesChave")).getText().equals(inputs[3])) {
            error += " - Atividades chave não editado";
            screenshotError(nameMethod);
        }

        driver.findElement(By.id("tabAnaliseMercado")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:relacoComClientes")));
        if (!driver.findElement(By.id("formulario_cadastro_projeto:relacoComClientes")).getText().equals(inputs[4])) {
            error += " - Relacao com Clientes não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:parceriasChaves")).getText().equals(inputs[5])) {
            error += " - Parcerias chave não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:canais")).getText().equals(inputs[6])) {
            error += " - Canais não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:recursosPrincipais")).getText().equals(inputs[7])) {
            error += " - Recursos Principais não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:concorrentes")).getText().equals(inputs[8])) {
            error += " - Concorrentes não editado";
            screenshotError(nameMethod);
        }

        driver.findElement(By.id("tabProdutoServico")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]")));
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]")).click();
        if (!driver.findElement(By.id("formulario_cadastro_projeto:tecnologiaProcessos")).getText().equals(inputs[9])) {
            error += " - Tecnologia e Processos não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:potencialInovacaoTecnologica")).getText().equals(inputs[10])) {
            error += " - Potencial Inovacao Tecnologica não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:aplicacoes")).getText().equals(inputs[11])) {
            error += " - Aplicacoes não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:dificuldadesEsperadas")).getText().equals(inputs[12])) {
            error += " - Dificuldades esperadas não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaUniversidade")).getText().equals(inputs[13])) {
            error += " - interação empresa universidade não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno")).getText().equals(inputs[14])) {
            error += " - Interação Empresa comunidade e governo não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:infraestrutura")).getText().equals(inputs[15])) {
            error += " - infraestrutura não editado";
            screenshotError(nameMethod);
        }

        driver.findElement(By.id("tabGestaoPessoas")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:participacaoAcionaria")));
        if (!driver.findElement(By.id("formulario_cadastro_projeto:participacaoAcionaria")).getText().equals(inputs[16])) {
            error += " - participacao acionaria não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:potencialEmprego")).getText().equals(inputs[17])) {
            error += " - Potencial Emprego não editado";
            screenshotError(nameMethod);
        }

        driver.findElement(By.id("tabPlanoFinanceiro")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:fontesDeReceita")));
        if (!driver.findElement(By.id("formulario_cadastro_projeto:fontesDeReceita")).getText().equals(inputs[18])) {
            error += " - Fontes de Receita não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:estruturaCustos")).getText().equals(inputs[19])) {
            error += " - Estrutura Custos não editado";
            screenshotError(nameMethod);
        }
        if (!driver.findElement(By.id("formulario_cadastro_projeto:investimentoInicial")).getText().equals(inputs[20])) {
            error += " - Investimento Inicial não editado";
            screenshotError(nameMethod);
        }

        return error;
    }

    private void preencherCampos(String nameMethod) throws Exception {
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
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
            driver.findElement(By.id("lista_planos:singleDT:0:visualizar")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:empresaProjeto")));
            driver.findElement(By.id("formulario_cadastro_projeto:empresaProjeto")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:empresaProjeto")).sendKeys(inputs[0]);
            driver.findElement(By.id("formulario_cadastro_projeto:segmentoDeClientes")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:segmentoDeClientes")).sendKeys(inputs[1]);
            driver.findElement(By.id("formulario_cadastro_projeto:propostaDeValor")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:propostaDeValor")).sendKeys(inputs[2]);
            driver.findElement(By.id("formulario_cadastro_projeto:atividadesChave")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:atividadesChave")).sendKeys(inputs[3]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar2")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabAnaliseMercado")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:relacoComClientes")));
            driver.findElement(By.id("formulario_cadastro_projeto:relacoComClientes")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:relacoComClientes")).sendKeys(inputs[4]);
            driver.findElement(By.id("formulario_cadastro_projeto:parceriasChaves")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:parceriasChaves")).sendKeys(inputs[5]);
            driver.findElement(By.id("formulario_cadastro_projeto:canais")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:canais")).sendKeys(inputs[6]);
            driver.findElement(By.id("formulario_cadastro_projeto:recursosPrincipais")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:recursosPrincipais")).sendKeys(inputs[7]);
            driver.findElement(By.id("formulario_cadastro_projeto:concorrentes")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:concorrentes")).sendKeys(inputs[8]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar3")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabProdutoServico")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]")));
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:estagioDeEvolucao\"]/option[" + cont + "]")).click();
            driver.findElement(By.id("formulario_cadastro_projeto:tecnologiaProcessos")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:tecnologiaProcessos")).sendKeys(inputs[9]);
            driver.findElement(By.id("formulario_cadastro_projeto:potencialInovacaoTecnologica")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:potencialInovacaoTecnologica")).sendKeys(inputs[10]);
            driver.findElement(By.id("formulario_cadastro_projeto:aplicacoes")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:aplicacoes")).sendKeys(inputs[11]);
            driver.findElement(By.id("formulario_cadastro_projeto:dificuldadesEsperadas")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:dificuldadesEsperadas")).sendKeys(inputs[12]);
            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaUniversidade")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaUniversidade")).sendKeys(inputs[13]);
            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:interacaoEmpresaComunidadeGoverno")).sendKeys(inputs[14]);
            driver.findElement(By.id("formulario_cadastro_projeto:infraestrutura")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:infraestrutura")).sendKeys(inputs[15]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar4")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabGestaoPessoas")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:participacaoAcionaria")));
            driver.findElement(By.id("formulario_cadastro_projeto:participacaoAcionaria")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:participacaoAcionaria")).sendKeys(inputs[16]);
            driver.findElement(By.id("formulario_cadastro_projeto:potencialEmprego")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:potencialEmprego")).sendKeys(inputs[17]);
            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar5")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            driver.findElement(By.id("tabPlanoFinanceiro")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formulario_cadastro_projeto:fontesDeReceita")));
            driver.findElement(By.id("formulario_cadastro_projeto:fontesDeReceita")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:fontesDeReceita")).sendKeys(inputs[18]);
            driver.findElement(By.id("formulario_cadastro_projeto:estruturaCustos")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:estruturaCustos")).sendKeys(inputs[19]);
            driver.findElement(By.id("formulario_cadastro_projeto:investimentoInicial")).clear();
            driver.findElement(By.id("formulario_cadastro_projeto:investimentoInicial")).sendKeys(inputs[20]);

            driver.findElement(By.id("formulario_cadastro_projeto:botaoSalvar6")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoSalvar\"]/div/div/div[3]/input")).click();

            String retorno;
            retorno = voltarVerificar(inputs, nameMethod);

            if (!retorno.equals("")) {
                error = true;
                errorMessage += "\nCaso de teste: " + casoTeste + " : " + retorno;
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

    @Test
    public void testEditarPlanoNegocioCom9000CaracteresDeEntrada() throws Exception {
        String nameMethod = "Editar Plano de Negócio com Dados Com 9000 caracteres de Entrada.";
        System.out.println(nameMethod);
        try {
            this.parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-1(EditarPlanoDadosCom9000Caracteres).xml");
            preencherCampos(nameMethod);
            report(false, nameMethod, null);
        } catch (Exception e) {
            report(true, nameMethod, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    private void report(boolean error, String nameMethod, String message) throws TestLinkAPIException {
        if (error) {
            Connection.updateResults(nameMethod, message, TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
        } else {
            Connection.updateResults(nameMethod, message, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        }
    }

    private void screenshotError(String nameMethod) throws Exception {
        TestingSupport.saveScreenshotError(driver, System.getProperty("user.dir") + System.getProperty("file.separator")
                + "test" + System.getProperty("file.separator")
                + "org" + System.getProperty("file.separator")
                + "unipampa" + System.getProperty("file.separator")
                + "testesgerenciador" + System.getProperty("file.separator")
                + "evidenciaserro", nameMethod);
    }

    //<editor-fold defaultstate="collapsed" desc="Finalizados">
    @Ignore
    @Test
    public void testEditarPlanoNegocioDadosConsistentes() throws Exception {
        String nameMethod = "Editar Plano de Negócio com Dados Consistentes.";
        System.out.println(nameMethod);
        try {
            this.parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-1(EditarPlanoDadosValidos).xml");
            preencherCampos(nameMethod);
            report(false, nameMethod, null);
        } catch (Exception e) {
            screenshotError(nameMethod);
            report(true, nameMethod, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void testRevisarPlanoNegocio() throws Exception {
        String nameMethod = "Revisar Plano de negócio.";
        System.out.println(nameMethod);
        try {
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
            if (!driver.getTitle().equals("Página Principal - Empreendedor")) {
                throw new Exception("Verifique o pré-requisito.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
            driver.findElement(By.id("lista_planos:singleDT:0:visualizar")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("botao_revisar")));
            driver.findElement(By.id("botao_revisar")).click();

            if (!driver.findElement(By.className("conteudoDaRevisao")).isDisplayed()) {
                throw new Exception("Botão revisar não está apresentando os dados do plano de negócio.");
            }

            report(false, nameMethod, null);
        } catch (Exception e) {
            screenshotError(nameMethod);
            report(true, nameMethod, e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void testExcluirPlanoNegocio() throws Exception {
        String nameMethod = "Excluir Plano de Negócio.";
        System.out.println(nameMethod);
        boolean error = false;
        try {
            Login.autenticar(driver, "testetestezin@gmail.com", "teste123456", url);
            if (!driver.getTitle().equals("Página Principal - Empreendedor")) {
                throw new Exception("Verifique o pré-requisito.");
            }

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")));
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();

            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:j_idt56\"]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoDeExclusao\"]/div/div/div[3]/div/input")));
            driver.findElement(By.xpath("//*[@id=\"modalInfoDeExclusao\"]/div/div/div[3]/div/input")).click();
            try {
                driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:j_idt56\"]")).click();
                throw new Exception("Erro esperado");
            } catch (Exception e) {
                error = false;
            }
            if (error) {
                throw new Exception("Não foi possível apagar o elemento.");
            }
            report(false, nameMethod, null);
        } catch (Exception e) {
            screenshotError(nameMethod);
            report(true, nameMethod, e.getMessage());
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
