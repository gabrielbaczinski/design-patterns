package br.pucpr.usuario;

import br.pucpr.table.ColumnData;

public class EmailColumn implements ColumnData<Usuario> {

    @Override
    public String getHeader() {
        return "EMAIL DE CONTATO DO USUARIO";
    }

    @Override
    public String getValue(Usuario usuario) {
        var email = usuario.email();
        return (email == null || !email.contains("@")) ? "INVALIDO" : email;
    }
}
