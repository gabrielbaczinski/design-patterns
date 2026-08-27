package br.pucpr.usuario;

import br.pucpr.table.ColumnData;

public class NomeColumn implements ColumnData<Usuario> {

    @Override
    public String getHeader() {
        return "NOME COMPLETO DO USUARIO";
    }

    @Override
    public String getValue(Usuario usuario) {
        var nome = usuario.nome();
        return (nome == null || nome.isEmpty()) ? "NÃO INFORMADO" : nome;
    }
}
