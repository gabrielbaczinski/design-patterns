package br.pucpr.planeta;

import br.pucpr.table.Table;

import java.util.List;

public class PlanetasPrinter {

    public static void main(String[] args) {
        var planetas = List.of(
            new Planeta(1, "Mercúrio", "Rochoso", 0.39, 0),
            new Planeta(2, "Vênus", "Rochoso", 0.72, 0),
            new Planeta(3, "Terra", "Rochoso", 1.00, 1),
            new Planeta(4, "Marte", "Rochoso", 1.52, 2),
            new Planeta(5, "Júpiter", "Gasoso", 5.20, 95),
            new Planeta(6, "Saturno", "Gasoso", 9.58, 146),
            new Planeta(7, "Urano", "Gelado", 19.20, 27),
            new Planeta(8, "Netuno", "Gelado", 30.05, 14),
            new Planeta(9, "", null, -1.0, -1)
        );

        var table = new Table(new PlanetaTableData(planetas));
        table.print(false, "DARK");
    }
}
