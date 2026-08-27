package br.pucpr.usuario;

import br.pucpr.table.ColumnData;

public class IdColumn implements ColumnData<Usuario> {

    @Override
    public String getHeader() {
        return "ID DO USUARIO";
    }

    @Override
    public String getValue(Usuario usuario) {
        var id = usuario.id();
        return id != null ? id.toString() : "0";
    }
}
