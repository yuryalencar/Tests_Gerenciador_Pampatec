package org.unipampa.testesgerenciador.br;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

/**
 * Classe de exemplo de integração entre o TestLink e o Selenium WebDriver
 * Atualizada em 18/10/2017
 * 
 * @author Yury Alencar Lima
 */
public class Connection {

    public static final String TESTLINK_URL = "http://lesse.com.br/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
    public static final String TEST_PROJECT_NAME = "Gerenciador Pampatec";
    public static final String TEST_PLAN_NAME = "Gerenciador Pampatec - Plano de Teste (Grupo 01)";
    public static final String BUILD_NAME = "Versão do Grupo 01 (1.2)";
    
    /**
     * Este método tem como finalidade enviar os resultados encontrados por meio
     * dos testes para o TestLink na nuvem.
     * @param testCaseName Nome do caso de testes previamente já inserido no TestLink.
     * @param exception Notas relacionadas ao erro encontrado caso o teste tenha falhado.
     * @param results Resultado referente ao final do teste podendo ser PASSOU, FALHOU dentre os
     * suportados pela ferramenta.
     * @param testLinkKey Chave do usuário criados dos testes, para que todos utilizem a mesma conexão.
     * @throws TestLinkAPIException 
     */
    public static void updateResults(String testCaseName, String exception, String results, String testLinkKey) throws TestLinkAPIException{

        TestLinkAPIClient testlink = new TestLinkAPIClient(testLinkKey, TESTLINK_URL);
        testlink.reportTestCaseResult(TEST_PROJECT_NAME, TEST_PLAN_NAME, testCaseName,
                BUILD_NAME, exception, results);
    }
    
}
