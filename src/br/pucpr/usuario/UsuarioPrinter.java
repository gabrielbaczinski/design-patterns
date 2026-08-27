package br.pucpr.usuario;

import br.pucpr.table.ColumnData;
import br.pucpr.table.ColumnTableData;
import br.pucpr.table.Table;

import java.util.List;

public class UsuarioPrinter {

    public static void main(String[] args) {
        var usuarios = List.of(
            new Usuario(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"),
            new Usuario(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"),
            new Usuario(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"),
            new Usuario(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"),
            new Usuario(105L, "Lucas Mendes", "lucas@email.com", "12345"),
            new Usuario(106L, "", "beatriz@email.com", "55566677788")
        );

        var columns = List.<ColumnData<? super Usuario>>of(
            new IdColumn(),
            new NomeColumn(),
            new EmailColumn(),
            new CpfColumn(true)
        );

        var table = new Table(new ColumnTableData<>(usuarios, columns));
        table.print(true, "LIGHT");
    }
}
