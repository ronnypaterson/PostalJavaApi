package br.com.alexpfx.api.postal;

import java.util.Date;

/**
 * Created by alex on 26/02/2015.
 */
public class SroRetornoInfo {
    private Date data;
    private String local;
    private String acao;
    private String detalhes;


    public SroRetornoInfo(Date data, String local, String acao, String detalhes) {
        this.data = data;
        this.local = local;
        this.acao = acao;
        this.detalhes = detalhes;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public Date getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public String getAcao() {
        return acao;
    }
}
