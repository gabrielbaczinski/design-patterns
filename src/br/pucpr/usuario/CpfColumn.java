package br.pucpr.usuario;

import br.pucpr.table.ColumnData;

public class CpfColumn implements ColumnData<Usuario> {

    private final boolean maskCpf;

    public CpfColumn(boolean maskCpf) {
        this.maskCpf = maskCpf;
    }

    @Override
    public String getHeader() {
        return "CPF (DOCUMENTO)";
    }

    @Override
    public String getValue(Usuario usuario) {
        var cpf = usuario.cpf();
        if (cpf == null || cpf.length() != 11) return "CPF INVALIDO";
        if (maskCpf) return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
}
