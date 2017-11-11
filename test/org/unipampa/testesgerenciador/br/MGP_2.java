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
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Juliana
 */
public class MGP_2 {

    public static WebDriver driver;
    public static String url = "http://192.168.130.102:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static ParserXML parser;
    public static final String TESTLINK_KEY = "8305a296244bd063e868bf5a357946d0";

    /**
     * Método que será executado antes de cada caso de teste com a finalidade de
     * abrir o navegador.
     *
     * @throws MalformedURLException
     */
    @Before
    public void openBrowser() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Ignore
    @Test
    public void criarCadastroValido() throws Exception {
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-2(CriarCadastroValidos).xml");

            List<String[]> entrada = parser.extractDataXML("casodeteste", atributosCadastrar());
            for (String[] strings : entrada) {
                driver.get(url);
                driver.findElement(By.xpath("//*[@id=\"formularioDeCadastro:botaoContinuaCadastro\"]/span[2]")).click();
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:nome\"]")).sendKeys(strings[0]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:cpf\"]")).sendKeys(strings[1]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:telefone\"]")).sendKeys(strings[2]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:j_idt19\"]")).sendKeys(strings[3]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:j_idt21\"]")).sendKeys(strings[4]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:rua\"]")).sendKeys(strings[5]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:numero\"]")).sendKeys(strings[6]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:bairro\"]")).sendKeys(strings[7]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:j_idt33\"]")).sendKeys(strings[8]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:email\"]")).sendKeys(strings[9]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:senha\"]")).sendKeys(strings[10]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:senhaConfig\"]")).sendKeys(strings[11]);
                driver.findElement(By.id("formularioCadastro:botaoEnviar")).click();

                if (!driver.getTitle().equals("Verificação de e-mail")) {
                    throw new Exception("Erro ao Cadastrar");
                }
                
                Connection.updateResults("Criar cadastro válido", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
            }

        } catch (Exception e) {
            screenshot("Criar cadastro válido");
            Connection.updateResults("Criar cadastro válido", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void criarCadastroInvalido() throws Exception {
        int casoTeste = 0;
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-2(CriarCadastroInvalidos).xml");

            List<String[]> entrada = parser.extractDataXML("casodeteste", atributosCadastrar());
            for (String[] strings : entrada) {
                casoTeste++;
                driver.get(url);
                driver.findElement(By.xpath("//*[@id=\"formularioDeCadastro:botaoContinuaCadastro\"]/span[2]")).click();
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:nome\"]")).sendKeys(strings[0]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:cpf\"]")).sendKeys(strings[1]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:telefone\"]")).sendKeys(strings[2]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:j_idt19\"]")).sendKeys(strings[3]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:j_idt21\"]")).sendKeys(strings[4]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:rua\"]")).sendKeys(strings[5]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:numero\"]")).sendKeys(strings[6]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:bairro\"]")).sendKeys(strings[7]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:j_idt33\"]")).sendKeys(strings[8]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:email\"]")).sendKeys(strings[9]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:senha\"]")).sendKeys(strings[10]);
                driver.findElement(By.xpath("//*[@id=\"formularioCadastro:senhaConfig\"]")).sendKeys(strings[11]);
                driver.findElement(By.id("formularioCadastro:botaoEnviar")).click();

                if (driver.getTitle().equals("Verificação de e-mail")) {
                    throw new Exception("Caso de Teste: "+casoTeste+" - Cadastro realizado com dados inválidos");
                }else if(!driver.getTitle().equals("Realizar Cadastro")){
                    throw new Exception("Caso de Teste: "+casoTeste+" - Erro inesperado ao cadastrar");
                }

                driver.get(url);

                Connection.updateResults("Criar cadastro inválido", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
            }

        } catch (Exception e) {
            screenshot("Criar cadastro inválido");
            Connection.updateResults("Criar cadastro inválido", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void editarCadastroValido() throws Exception {
        try {
            
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-2(EditarCadastroValidos).xml");

            List<String[]> entrada = parser.extractDataXML("casodeteste", atributosEditar());
            Login.autenticar(driver, "novotestetestezin@gmail.com", "teste123456", url);
            if (!driver.getTitle().equals("Página Principal - Empreendedor")) {
                throw new Exception("Verifique o pré-requisito.");
            }

            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/ul/li[1]/input")).click();
            int cont = 0;
            for (String[] strings : entrada) {

                if (!strings[0].equals("")) {
                    driver.findElement(By.id("formularioCadastro:nome")).clear();
                    driver.findElement(By.id("formularioCadastro:nome")).sendKeys(strings[0]);
                }
                if (!strings[1].equals("")) {
                    driver.findElement(By.id("formularioCadastro:telefone")).clear();
                    driver.findElement(By.id("formularioCadastro:telefone")).sendKeys(strings[1]);
                }
                if (!strings[2].equals("")) {
                    driver.findElement(By.id("formularioCadastro:j_idt52")).clear();
                    driver.findElement(By.id("formularioCadastro:j_idt52")).sendKeys(strings[2]);
                }
                if (!strings[3].equals("")) {
                    driver.findElement(By.id("formularioCadastro:j_idt54")).clear();
                    driver.findElement(By.id("formularioCadastro:j_idt54")).sendKeys(strings[3]);
                }
                if (!strings[4].equals("")) {
                    driver.findElement(By.id("formularioCadastro:rua")).clear();
                    driver.findElement(By.id("formularioCadastro:rua")).sendKeys(strings[4]);
                }
                if (!strings[5].equals("")) {
                    driver.findElement(By.id("formularioCadastro:numero")).clear();
                    driver.findElement(By.id("formularioCadastro:numero")).sendKeys(strings[5]);
                }
                if (!strings[6].equals("")) {
                    driver.findElement(By.id("formularioCadastro:bairro")).clear();
                    driver.findElement(By.id("formularioCadastro:bairro")).sendKeys(strings[6]);
                }
                if (!strings[7].equals("")) {
                    driver.findElement(By.id("formularioCadastro:j_idt62")).clear();
                    driver.findElement(By.id("formularioCadastro:j_idt62")).sendKeys(strings[7]);
                }

                if (!strings[8].equals("")) {
                    driver.findElement(By.id("formularioCadastro:email")).clear();
                    driver.findElement(By.id("formularioCadastro:email")).sendKeys(strings[8]);

                }
                driver.findElement(By.id("formularioCadastro:senhaAtual")).sendKeys(strings[9]);
                driver.findElement(By.id("formularioCadastro:senhaNova")).sendKeys(strings[10]);
                driver.findElement(By.id("formularioCadastro:senhaConfig")).sendKeys(strings[11]);
                driver.findElement(By.id("formularioCadastro:botaoFinalizarEdicao")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioCadastro:botaoConfirmar")));
                driver.findElement(By.id("formularioCadastro:botaoConfirmar")).click();

                if (!driver.getTitle().equals("Logout")) {
                    try {
                        driver.findElement(By.id("notificacaoSalvo")).getText().equals("Cadastro atualizado com sucesso!");
                    } catch (Exception e) {
                        throw new Exception("Cadastro Não Atualizado");
                    }
                    driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/a")).click();
                    driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/ul/li[1]/a")).click();
                }

            }
            Connection.updateResults("Editar cadastro válido", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            screenshot("Editar cadastro válido");
            Connection.updateResults("Editar cadastro válinomeCasoTestedo", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void editarCadastroInvalido() throws Exception {
        int cont = 0, casoTeste = 0;
        String nomeCasoTeste = "Editar cadastro inválido";
        String mensagem = "Sistema está aceitando: "  + "\n";
        try {
            parser = new ParserXML(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "test" + System.getProperty("file.separator")
                    + "org" + System.getProperty("file.separator")
                    + "unipampa" + System.getProperty("file.separator")
                    + "testesgerenciador" + System.getProperty("file.separator")
                    + "datatests" + System.getProperty("file.separator")
                    + "MGP-2(EditarCadastroInvalidos).xml");

            List<String[]> entrada = parser.extractDataXML("casodeteste", atributosEditar());
            for (String[] strings : entrada) {
                casoTeste++;
                Login.autenticar(driver, "julianamareco18@gmail.com", "teste123456", url);
                driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/a")).click();
                driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/ul/li[1]/input")).click();

                if (!strings[0].equals("")) {
                    driver.findElement(By.id("formularioCadastro:nome")).clear();
                    driver.findElement(By.id("formularioCadastro:nome")).sendKeys(strings[0]);
                }
                if (!strings[1].equals("")) {
                    driver.findElement(By.id("formularioCadastro:telefone")).clear();
                    driver.findElement(By.id("formularioCadastro:telefone")).sendKeys(strings[1]);
                }
                if (!strings[2].equals("")) {
                    driver.findElement(By.id("formularioCadastro:j_idt52")).clear();
                    driver.findElement(By.id("formularioCadastro:j_idt52")).sendKeys(strings[2]);
                }
                if (!strings[3].equals("")) {
                    driver.findElement(By.id("formularioCadastro:j_idt54")).clear();
                    driver.findElement(By.id("formularioCadastro:j_idt54")).sendKeys(strings[3]);
                }
                if (!strings[4].equals("")) {
                    driver.findElement(By.id("formularioCadastro:rua")).clear();
                    driver.findElement(By.id("formularioCadastro:rua")).sendKeys(strings[4]);
                }
                if (!strings[5].equals("")) {
                    driver.findElement(By.id("formularioCadastro:numero")).clear();
                    driver.findElement(By.id("formularioCadastro:numero")).sendKeys(strings[5]);
                }
                if (!strings[6].equals("")) {
                    driver.findElement(By.id("formularioCadastro:bairro")).clear();
                    driver.findElement(By.id("formularioCadastro:bairro")).sendKeys(strings[6]);
                }
                if (!strings[7].equals("")) {
                    driver.findElement(By.id("formularioCadastro:j_idt62")).clear();
                    driver.findElement(By.id("formularioCadastro:j_idt62")).sendKeys(strings[7]);
                }

                if (!strings[8].equals("")) {
                    driver.findElement(By.id("formularioCadastro:email")).clear();
                    driver.findElement(By.id("formularioCadastro:email")).sendKeys(strings[8]);
                }

                driver.findElement(By.id("formularioCadastro:senhaAtual")).sendKeys(strings[9]);
                driver.findElement(By.id("formularioCadastro:senhaNova")).sendKeys(strings[10]);
                driver.findElement(By.id("formularioCadastro:senhaConfig")).sendKeys(strings[11]);
                driver.findElement(By.id("formularioCadastro:botaoFinalizarEdicao")).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioCadastro:botaoConfirmar")));
                driver.findElement(By.id("formularioCadastro:botaoConfirmar")).click();

                if (driver.findElement(By.id("formularioCadastro:nome")).getAttribute("value").equals(strings[0])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de nome inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:j_idt52")).getAttribute("value").equals(strings[2])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de experiência inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:j_idt54")).getAttribute("value").equals(strings[3])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de formação inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:rua")).getAttribute("value").equals(strings[4])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de logradouro inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:numero")).getAttribute("value").equals(strings[5])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de número inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:bairro")).getAttribute("value").equals(strings[6])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de bairro inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:j_idt62")).getAttribute("value").equals(strings[7])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de complemento inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:email")).getAttribute("value").equals(strings[8]) || driver.getTitle().equals("Logout")) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de email inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }
                if (driver.findElement(By.id("formularioCadastro:telefone")).getAttribute("value").equals(strings[1])) {
                    mensagem += "Caso de teste: " + casoTeste + "- Inserção de telefone inválido" + "\n";
                    screenshot(nomeCasoTeste);
                    cont++;
                }

                driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/a")).click();
                driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[3]/ul/li[2]/a")).click();
                System.out.println(cont);
            }

            if (cont > 0) {
                throw new Exception(mensagem);
            }

            Connection.updateResults("Editar cadastro inválido", null, TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        } catch (Exception e) {
            Connection.updateResults("Editar cadastro inválido", e.getMessage(), TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
        }
    }

    private List<String> atributosCadastrar() {
        List<String> atributosCadastro = new ArrayList();
        atributosCadastro.add("Nome");
        atributosCadastro.add("CPF");
        atributosCadastro.add("Telefone");
        atributosCadastro.add("Experiencia");
        atributosCadastro.add("Formacao");
        atributosCadastro.add("Logradouro");
        atributosCadastro.add("Numero");
        atributosCadastro.add("Bairro");
        atributosCadastro.add("Complemento");
        atributosCadastro.add("Email");
        atributosCadastro.add("Senha");
        atributosCadastro.add("ConfSenha");
        return atributosCadastro;
    }

    private List<String> atributosEditar() {
        List<String> atributosEditar = new ArrayList();
        atributosEditar.add("Nome");
        atributosEditar.add("Telefone");
        atributosEditar.add("Experiencia");
        atributosEditar.add("Formacao");
        atributosEditar.add("Logradouro");
        atributosEditar.add("Numero");
        atributosEditar.add("Bairro");
        atributosEditar.add("Complemento");
        atributosEditar.add("Email");
        atributosEditar.add("SenhaAtual");
        atributosEditar.add("SenhaNova");
        atributosEditar.add("ConfSenhaNova");
        return atributosEditar;
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
