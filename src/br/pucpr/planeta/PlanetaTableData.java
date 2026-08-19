package br.pucpr.planeta;

import br.pucpr.table.TableData;

import java.util.ArrayList;
import java.util.List;

public class PlanetaTableData implements TableData {

    private static final List<String> HEADERS =
        List.of("ID DO PLANETA", "NOME DO PLANETA", "TIPO DO PLANETA", "DISTANCIA DO SOL (UA)", "QTD. DE LUAS");

    private final List<Planeta> planetas;

    public PlanetaTableData(List<Planeta> planetas) {
        this.planetas = planetas;
    }

    @Override
    public List<String> getHeaders() {
        return HEADERS;
    }

    @Override
    public List<List<String>> getRows() {
        var rows = new ArrayList<List<String>>();
        if (planetas == null) return rows;

        for (var planeta : planetas) {
            if (planeta == null) continue;
            rows.add(List.of(
                formatId(planeta.id()),
                formatNome(planeta.nome()),
                formatTipo(planeta.tipo()),
                formatDistancia(planeta.distanciaSolUA()),
                formatLuas(planeta.luas())
            ));
        }
        return rows;
    }

    private String formatId(Integer id) {
        return id != null ? id.toString() : "0";
    }

    private String formatNome(String nome) {
        return (nome == null || nome.isEmpty()) ? "DESCONHECIDO" : nome;
    }

    private String formatTipo(String tipo) {
        return (tipo == null || tipo.isEmpty()) ? "NAO CLASSIFICADO" : tipo;
    }

    private String formatDistancia(Double distanciaSolUA) {
        return (distanciaSolUA == null || distanciaSolUA < 0)
            ? "N/D"
            : String.format("%.2f UA", distanciaSolUA);
    }

    private String formatLuas(Integer luas) {
        return (luas == null || luas < 0) ? "N/D" : luas.toString();
    }
}
