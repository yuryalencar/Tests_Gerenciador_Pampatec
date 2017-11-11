/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.testesgerenciador.br;

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
import testlink.api.java.client.TestLinkAPIResults;


/**
 *
 * @author Lucascorrea
 */
public class MPG_6 {
    
    
    public static WebDriver driver;
    public String url = "http://192.168.184.102:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static final String TESTLINK_KEY = "6a96bbf9e3bdc747abd3df8fbe2c18e3";
    
    
    @Before
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Login.autenticar(driver, "eslucascorrea@gmail.com", "lucascorrea122", url);
    
    }
    
    @Test
    public void verificarExemplosItemPlanoNegocio() throws Exception{
       
        try{
              driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]/span[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")));
        driver.findElement(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")).click();
        
        //Segmento de Clientes
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt78\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("ajuda com preenchimento de Segmento de Cliente")){
            throw new Exception("Exemplo de Segmento de Clientes não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Proposta de Valor
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt82\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Proposta de Valor")){
            throw new Exception("Exemplo de Proposta de valor não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Atividade Chave
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt86\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Atividades Chave")){
            throw new Exception("Exemplo de Atividades Chave não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Click para nova aba "Análise de Mercado"!
        driver.findElement(By.xpath("//*[@id=\"tabAnaliseMercado\"]")).click();
        
        //Relação com Cliente
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt92\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Relação com cliente")){
            throw new Exception("Exemplo de Relação com Cliente não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Parceria Chave
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt96\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Parcerias Chave")){
            throw new Exception("Exemplo de Parcerias Chave não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Canais
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt100\"]")).click();
        if(driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Canais")){
            throw new Exception("Exemplo de Canais não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Recursos Principais
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt104\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Recursos Principais")){
            throw new Exception("Exemplo de Recursos Principais não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Click para nova aba "Produto ou Serviço"
        driver.findElement(By.xpath("//*[@id=\"tabProdutoServico\"]")).click();
        
        //Tecnologia e processos (não poluentes)
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt122\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Tecnologia e Processos")){
            throw new Exception("Exemplo de Tecnologia e Processos não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Click para nova aba "Plano Financeiro"
        driver.findElement(By.xpath("//*[@id=\"tabPlanoFinanceiro\"]")).click();
        
        //Fonte de Receita
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt146\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Fontes de Receitas")){
            throw new Exception("Exemplo de Fontes de Receitas não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
       
        Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
            TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        
        
        }catch(Exception e){
            
             Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
                TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
             Assert.fail(e.getMessage());
        
        }
       
    }
    
   
     @Test
    public void verificarExemplosItemPlanoNegocioInvalidos() throws Exception{
       
        try{
              driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"menuSuperior\"]/nav/div/div[2]/ul/li[2]/ul/li[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"formEquipe:botaoSalvar1\"]/span[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")));
        driver.findElement(By.xpath("//*[@id=\"modalInfoSalvarEquipe\"]/div/div/div[3]/input")).click();
        
        //Segmento de Clientes
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt78\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("ajuda com  de Segmento de Cliente")){
            throw new Exception("Exemplo de Segmento de Clientes não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Proposta de Valor
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt82\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de  de Valor")){
            throw new Exception("Exemplo de Proposta de valor não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Atividade Chave
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt86\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de  Chave")){
            throw new Exception("Exemplo de Atividades Chave não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Click para nova aba "Análise de Mercado"!
        driver.findElement(By.xpath("//*[@id=\"tabAnaliseMercado\"]")).click();
        
        //Relação com Cliente
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt92\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de  com cliente")){
            throw new Exception("Exemplo de Relação com Cliente não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Parceria Chave
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt96\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de  Chave")){
            throw new Exception("Exemplo de Parcerias Chave não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Canais
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt100\"]")).click();
        if(driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de ")){
            throw new Exception("Exemplo de Canais não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Recursos Principais
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt104\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Recursos ")){
            throw new Exception("Exemplo de Recursos Principais não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Click para nova aba "Produto ou Serviço"
        driver.findElement(By.xpath("//*[@id=\"tabProdutoServico\"]")).click();
        
        //Tecnologia e processos (não poluentes)
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt122\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Tecnologia e ")){
            throw new Exception("Exemplo de Tecnologia e Processos não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
        
        //Click para nova aba "Plano Financeiro"
        driver.findElement(By.xpath("//*[@id=\"tabPlanoFinanceiro\"]")).click();
        
        //Fonte de Receita
        driver.findElement(By.xpath("//*[@id=\"formulario_cadastro_projeto:j_idt146\"]")).click();
        if(!driver.findElement(By.xpath("//*[@id=\"primefacesmessagedlg\"]")).getText().
                equals("Ajuda com preenchimento de Fontes de ")){
            throw new Exception("Exemplo de Fontes de Receitas não encontrado!");
        }
        driver.findElement(By.xpath("/html/body/div[11]/div[1]/a")).click();
       
        Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
            TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        
        
        }catch(Exception e){
            
             Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
                TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
             Assert.fail(e.getMessage());
        
        }
       
    }
    
    
    @After
    public void closeBrowser() {
        driver.quit();
    }
    
    
}
