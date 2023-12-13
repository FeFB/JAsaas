package br.com.intersistemas.jasaas.adapter;

import java.io.IOException;

import br.com.intersistemas.jasaas.exception.ConnectionException;

/**
 *
 * @author bosco
 */
public interface AdapterConnection {

    /**
     * GET Request
     *
     * @param url A url utilizada para fazer a requisição
     * @return string
     * @throws ConnectionException Representação de algum erro durante a conexão com a api ASAAS
     */
    String get(String url);

    /**
     * DELETE Request
     *
     * @param url A url utilizada para fazer a requisição
     * @return String contendo JSON com o objeto deletado ou um objeto de erro
     * @throws ConnectionException Representação de algum erro durante a conexão com a api ASAAS
     * @throws IOException Representação de algum erro de leitura ou escrita
     */
    String delete(String url) throws IOException;

    /**
     * PUT Request
     *
     * @param url URL do serviço para comunicação
     * @param contentJSON Conteúdo da comunicação
     * @throws ConnectionException Representação de algum erro durante a conexão com a api ASAAS
     * @return string
     */
    String put(String url, String contentJSON);

    /**
     * POST Request
     *
     * @param url URL do serviço para comunicação
     * @param contentJSON Conteúdo da comunicação
     * @return String contendo JSON com o objeto gerado ou um objeto de erro
     * @throws ConnectionException Representação de algum erro durante a conexão com a api ASAAS
     */
    String post(String url, String contentJSON);

}
