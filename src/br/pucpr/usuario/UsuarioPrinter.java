package br.pucpr.usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioPrinter {

    public record Usuario(Long id, String nome, String email, String cpf) {}

    public enum Theme {
        DEFAULT("="),
        DARK("#"),
        LIGHT("-");

        private final String borderChar;

        Theme(String borderChar) {
            this.borderChar = borderChar;
        }

        public String getBorderChar() {
            return borderChar;
        }

        public static Theme of(String value) {
            return switch (value) {
                case "DARK"  -> DARK;
                case "LIGHT" -> LIGHT;
                default      -> DEFAULT;
            };
        }
    }

    private static final int TABLE_WIDTH = 74;
    private static final String ALIGN_INDENT = "                    ";

    private String formatNome(String nome) {
        if (nome == null || nome.isEmpty()) return "NÃO INFORMADO";
        return nome.length() > 20 ? nome.substring(0, 17) + "..." : nome;
    }

    private String formatEmail(String email) {
        return (email == null || !email.contains("@")) ? "INVALIDO" : email;
    }

    private String formatCpf(String cpf, boolean mask) {
        if (cpf == null || cpf.length() != 11) return "CPF INVALIDO";
        if (mask) return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }

    private String formatId(Long id) {
        return id != null ? id.toString() : "0";
    }

    private String renderRow(Usuario u, boolean maskCpf) {
        return String.format("| %-5s | %-20s | %-22s | %-14s |\n",
            formatId(u.id()),
            formatNome(u.nome()),
            formatEmail(u.email()),
            formatCpf(u.cpf(), maskCpf)
        );
    }

    private String renderTable(List<Usuario> lista, boolean maskCpf, Theme theme) {
        var border = theme.getBorderChar().repeat(TABLE_WIDTH) + "\n";
        var sb = new StringBuilder();
        sb.append(border);
        sb.append(String.format("| %-5s | %-20s | %-22s | %-14s |\n", "ID", "NOME", "EMAIL", "CPF"));
        sb.append(border);
        for (var u : lista) {
            if (u == null) continue;
            sb.append(renderRow(u, maskCpf));
            sb.append(border);
        }
        return sb.toString();
    }

    private void output(String content, boolean alignRight) {
        if (alignRight) {
            for (var line : content.split("\n")) {
                System.out.println(ALIGN_INDENT + line);
            }
        } else {
            System.out.print(content);
        }
    }

    public void print(List<Usuario> lista, boolean maskCpf, boolean alignRight, String themeStr) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("ERRO: Lista de usuários vazia ou nula.");
            return;
        }
        output(renderTable(lista, maskCpf, Theme.of(themeStr)), alignRight);
    }

    public static void main(String[] args) {
        var usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
        usuarios.add(new Usuario(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
        usuarios.add(new Usuario(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
        usuarios.add(new Usuario(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
        usuarios.add(new Usuario(105L, "Lucas Mendes", "lucas@email.com", "12345"));
        usuarios.add(new Usuario(106L, "", "beatriz@email.com", "55566677788"));

        var printer = new UsuarioPrinter();
        printer.print(usuarios, true, true, "LIGHT");
    }
}
