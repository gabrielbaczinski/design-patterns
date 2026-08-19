package br.pucpr.usuario;

import br.pucpr.table.TableData;

import java.util.ArrayList;
import java.util.List;

public class UsuarioTableData implements TableData {

    private static final List<String> HEADERS =
        List.of("ID DO USUARIO", "NOME COMPLETO DO USUARIO", "EMAIL DE CONTATO DO USUARIO", "CPF (DOCUMENTO)");

    private final List<Usuario> usuarios;
    private final boolean maskCpf;

    public UsuarioTableData(List<Usuario> usuarios, boolean maskCpf) {
        this.usuarios = usuarios;
        this.maskCpf = maskCpf;
    }

    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    @Override
    public List<List<String>> getRows() {
        var rows = new ArrayList<List<String>>();
        if (usuarios == null) return rows;

        for (var usuario : usuarios) {
            if (usuario == null) continue;
            rows.add(List.of(
                formatId(usuario.id()),
                formatNome(usuario.nome()),
                formatEmail(usuario.email()),
                formatCpf(usuario.cpf(), maskCpf)
            ));
        }
        return rows;
    }

    private String formatId(Long id) {
        return id != null ? id.toString() : "0";
    }

    private String formatNome(String nome) {
        return (nome == null || nome.isEmpty()) ? "NÃO INFORMADO" : nome;
    }

    private String formatEmail(String email) {
        return (email == null || !email.contains("@")) ? "INVALIDO" : email;
    }

    private String formatCpf(String cpf, boolean mask) {
        if (cpf == null || cpf.length() != 11) return "CPF INVALIDO";
        if (mask) return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
}
