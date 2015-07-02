package br.com.alexpfx.api.postal.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandre on 28/02/15.
 * Busca pelo código de rastreamento utilizando a API pública da Agência Idéias: http://www.agenciaideias.com.br
 *
 */
public class AgenciaIdeiaRepository implements SroRepository {
    public static final String CALL_URL = "http://developers.agenciaideias.com.br/correios/rastreamento/json/";

    private int timeout = 60 * 1000;


    @Override
    public List<SroRetornoInfo> consultarSro(String sro) throws InfraException {
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
        String respostaFromUrl = null;
        try {
            respostaFromUrl = callURL(CALL_URL + sro, timeout);
        } catch (SocketTimeoutException e) {
            throw new InfraException("Erro de timeout ao tentar buscar resultado por codigo: " + sro.toString(), e);
        }
        SroRetornoInfo[] retorno = null;
        try {
            retorno = gson.fromJson(respostaFromUrl, SroRetornoInfo[].class);
        } catch (JsonSyntaxException e) {
            throw new InfraException("Erro ao pesquisar objeto: " + sro.toString(), e);
        }
        return Arrays.asList(retorno);
    }

    private String callURL(String sUrl, int timeout) throws SocketTimeoutException {
        StringBuilder sb = new StringBuilder();
        URLConnection conn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(sUrl);
            conn = url.openConnection();

            if (conn == null) {
                return "";
            }

            conn.setReadTimeout(timeout);
            if (conn.getInputStream() == null) {
                return "";
            }
            in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
            BufferedReader br = new BufferedReader(in);
            if (br != null) {
                int cp;
                while ((cp = br.read()) != -1) {
                    sb.append((char) cp);
                }
                br.close();
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new InfraException("Erro desconhecido", e);
        } catch (SocketTimeoutException e) {
            throw e;
        } catch (IOException e) {
            throw new InfraException("Erro desconhecido", e);
        }
        return sb.toString();
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
