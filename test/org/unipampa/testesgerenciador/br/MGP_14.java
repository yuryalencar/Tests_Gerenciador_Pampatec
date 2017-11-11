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
import org.openqa.selenium.support.ui.WebDriverWait;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

/**
 *
 * @author Lucascorrea
 */
public class MGP_14 {
    
    public static WebDriver driver;
    public String url = "http://192.168.184.102:8080/GerenciadorPampatec/";
    public static WebDriverWait wait;
    public static final String TESTLINK_KEY = "6a96bbf9e3bdc747abd3df8fbe2c18e3";
    
    
    @Before
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10000);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Login.autenticar(driver, "eslucascorrea@gmail.com", "lucascorrea122", url);
        
    
    }
    
     @Test
    public void revisarEmpreendedoresValidos() throws TestLinkAPIException{
        
        boolean erro = false;
        StringBuilder mensagem = new StringBuilder();
        try{
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
            //botão visualizar
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:visualizar\"]")).click();
            //botão revisar
            driver.findElement(By.xpath("//*[@id=\"botao_revisar\"]")).click();
            
            if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[1]/div[2]/div/div[2]")).getText().
                    contains("Correa da silva Lucas")){
                mensagem.append("Nome de empreendedor adicionado não existe.\n");
                erro = true;
               
            }
            
            if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[1]/div[2]/div/div[2]")).getText().
                    contains("Lucas da Silva Corrêa")){
                mensagem.append("Nome de empreendedor padrão não existe.\n");
                erro = true;
               
            }
             
            Connection.updateResults("Revisão Plano de negócio", null,
            TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        
        }catch(Exception e){
        
            Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
             TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
          
        }   
    }
    
      @Test
    public void revisarEmpreendedoresInvalidos() throws TestLinkAPIException{
        
        boolean erro = false;
        StringBuilder mensagem = new StringBuilder();
        try{
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
            //botão visualizar
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:visualizar\"]")).click();
            //botão revisar
            driver.findElement(By.xpath("//*[@id=\"botao_revisar\"]")).click();
            
            if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[1]/div[2]/div/div[2]")).getText().
                    contains("Yury Alencar")){
                mensagem.append("Nome de empreendedor adicionado não existe.\n");
                erro = true;
               
            }
            
            if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[1]/div[2]/div/div[2]")).getText().
                    contains("Lucas da Silva Corrêa")){
                mensagem.append("Nome de empreendedor padrão não existe.\n");
                erro = true;
               
            }
             
            Connection.updateResults("Revisão Plano de negócio", null,
            TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        
        }catch(Exception e){
        
            Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
            TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
             System.out.println(e.getMessage());
        }   
    }
    
    @Test
    public void revisarPlanoNegocioValido() throws TestLinkAPIException{
        
        boolean erro = false;
        StringBuilder mensagem = new StringBuilder();
        try{
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
            //botão visualizar
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:visualizar\"]")).click();
            //botão revisar
            driver.findElement(By.xpath("//*[@id=\"botao_revisar\"]")).click();
            
            
            if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[1]/h2[1]/b")).getText().
                    contains("Segmento de clientes:")){
                mensagem.append("Segmento de clientes inexistente.\n");
                erro = true;
            }
          
             if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[1]/h2[2]/b")).getText().
                    contains("Proposta de valor:")){
                mensagem.append("Proposta de valor inexistente.\n");
                erro = true;
            }
             
             if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[1]/h2[3]/b")).getText().
                    contains("Atividades Chave:")){
                mensagem.append("Atividades Chave inexistente.\n");
                erro = true;
            }
             
             if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[1]/b")).getText().
                    contains("Relações com clientes:")){
                mensagem.append("Relações com clientes inexistente.\n");
                erro = true;
            }
             
               if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[2]/b")).getText().
                    contains("Parcerias chaves:")){
                mensagem.append("Parcerias chaves inexistente.\n");
                erro = true;
            }
               
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[3]/b")).getText().
                    contains("Canais:")){
                mensagem.append("Canais inexistente.\n");
                erro = true;
            }
                
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[4]/b")).getText().
                    contains("Recursos principais:")){
                mensagem.append("Recursos principais inexistente.\n");
                erro = true;
            }
            
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[5]/b")).getText().
                    contains("Concorrentes:")){
                mensagem.append("Concorrentes inexistente.\n");
                erro = true;
            }
            
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[1]/b")).getText().
                    contains("Estágio de evolução:")){
                mensagem.append("Estágio de evolução inexistente.\n");
                erro = true;
            }
                
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[2]/b")).getText().
                    contains("Tecnologia e processos (não poluentes):")){
                mensagem.append("Tecnologia e processos (não poluentes) inexistente.\n");
                erro = true;
            }
                 
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[3]/b")).getText().
                    contains("Potencial de inovação tecnológica:")){
                mensagem.append("Potencial de inovação tecnológica: inexistente.\n");
                erro = true;
            }
                 
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[4]/b")).getText().
                    contains("Aplicações:")){
                mensagem.append("Aplicações inexistente.\n");
                erro = true;
            }
               
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[5]/b")).getText().
                    contains("Dificuldades esperadas:")){
                mensagem.append("Dificuldades esperadas inexistente.\n");
                erro = true;
            }
                
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[6]/b")).getText().
                    contains("Interação entre empresa e Universidade:")){
                mensagem.append("Interação entre empresa e Universidade inexistente.\n");
                erro = true;
            }
                 
                 if (!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[7]/b")).getText().
                    contains("Interação entre empresa, comunidade e governo:")) {
                mensagem.append("Interação entre empresa, comunidade e governo inexistente.\n");
                erro = true;
            }
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[8]/b")).getText().
                    contains("Infra-estrutura necessária para o desenvolvimento e produção:")){
                mensagem.append("Infra-estrutura necessária para o desenvolvimento e produção inexistente.\n");
                erro = true;
            }
                 
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[8]/b")).getText().
                    contains("Infra-estrutura necessária para o desenvolvimento e produção:")){
                mensagem.append("Infra-estrutura necessária para o desenvolvimento e produção inexistente.\n");
                erro = true;
            }
                
            Connection.updateResults("Revisão Plano de negócio", null,
            TestLinkAPIResults.TEST_PASSED, TESTLINK_KEY);
        
        }catch(Exception e){
        
            Connection.updateResults("Verificar Exemplos dos Itens de Plano de Negócio Completo", null,
             TestLinkAPIResults.TEST_FAILED, TESTLINK_KEY);
            Assert.fail(e.getMessage());
             
        }
        
        
    
    }
    
    
      @Test
    public void revisarPlanoNegocioInvalido() throws TestLinkAPIException{
        
        boolean erro = false;
        StringBuilder mensagem = new StringBuilder();
        try{
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/a")).click();
            driver.findElement(By.xpath("/html/body/form/nav/div/div[2]/ul/li[2]/ul/li[2]/input")).click();
            //botão visualizar
            driver.findElement(By.xpath("//*[@id=\"lista_planos:singleDT:0:visualizar\"]")).click();
            //botão revisar
            driver.findElement(By.xpath("//*[@id=\"botao_revisar\"]")).click();
            
            
            if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[1]/h2[1]/b")).getText().
                    contains("Proposta de valor:")){
                mensagem.append("Segmento de clientes inexistente.\n");
                erro = true;
            }
          
             if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[1]/h2[2]/b")).getText().
                    contains("Segmento de clientes:")){
                mensagem.append("Proposta de valor inexistente.\n");
                erro = true;
            }
             
             if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[1]/h2[3]/b")).getText().
                    contains("Atividades Chave:")){
                mensagem.append("Atividades Chave inexistente.\n");
                erro = true;
            }
             
             if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[1]/b")).getText().
                    contains("Relações com clientes:")){
                mensagem.append("Relações com clientes inexistente.\n");
                erro = true;
            }
             
               if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[2]/b")).getText().
                    contains("Parcerias chaves:")){
                mensagem.append("Parcerias chaves inexistente.\n");
                erro = true;
            }
               
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[3]/b")).getText().
                    contains("Cais:")){
                mensagem.append("Canais inexistente.\n");
                erro = true;
            }
                //ERROR
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[4]/b")).getText().
                    contains("principais:")){
                mensagem.append("Recursos principais inexistente.\n");
                erro = true;
            }
            
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[2]/h2[5]/b")).getText().
                    contains("Concorrentes:")){
                mensagem.append("Concorrentes inexistente.\n");
                erro = true;
            }
            
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[1]/b")).getText().
                    contains("Estág de evolução:")){
                mensagem.append("Estágio de evolução inexistente.\n");
                erro = true;
            }
                
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[2]/b")).getText().
                    contains("Tecnolog e processos (não poluentes):")){
                mensagem.append("Tecnologia e processos (não poluentes) inexistente.\n");
                erro = true;
            }
                 
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[3]/b")).getText().
                    contains("Potencial de inovação tecnológica:")){
                mensagem.append("Potencial de inovação tecnológica: inexistente.\n");
                erro = true;
            }
                 
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[4]/b")).getText().
                    contains("Aplicações:")){
                mensagem.append("Aplicações inexistente.\n");
                erro = true;
            }
               
                if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[5]/b")).getText().
                    contains("Dificuldad esperadas:")){
                mensagem.append("Dificuldades esperadas inexistente.\n");
                erro = true;
            }
                
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[6]/b")).getText().
                    contains("Interação tre empresa e Universidade:")){
                mensagem.append("Interação entre empresa e Universidade inexistente.\n");
                erro = true;
            }
                 
                 if (!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[7]/b")).getText().
                    contains("Interação en empresa, comunidade e governo:")) {
                mensagem.append("Interação entre empresa, comunidade e governo inexistente.\n");
                erro = true;
            }
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[8]/b")).getText().
                    contains("Infra-estrutura necessária para o desenvolvimento e produção:")){
                mensagem.append("Infra-estrutura necessária para o desenvolvimento e produção inexistente.\n");
                erro = true;
            }
                 
                 if(!driver.findElement(By.xpath("/html/body/div[8]/form/div/div[1]/div[2]/div[3]/h2[8]/b")).getText().
                    contains("Infra-estrura necessária para o desenvolvimento e produção:")){
                mensagem.append("Infra-estrutura necessária para o desenvolvimento e produção inexistente.\n");
                erro = true;
            }
                
            Connection.updateResults("Revisão Plano de negócio", null,
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
