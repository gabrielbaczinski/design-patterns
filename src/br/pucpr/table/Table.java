package br.pucpr.table;

import java.util.List;

public class Table {

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
                case "DARK" -> DARK;
                case "LIGHT" -> LIGHT;
                default -> DEFAULT;
            };
        }
    }

    private static final String ALIGN_INDENT = "                    ";

    private final TableData data;

    public Table(TableData data) {
        this.data = data;
    }

    private int[] columnWidths(List<String> headers) {
        var widths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            widths[i] = headers.get(i).length();
        }
        return widths;
    }

    private String fit(String value, int width) {
        var text = value == null ? "" : value;
        if (text.length() > width) {
            return width > 3 ? text.substring(0, width - 3) + "..." : text.substring(0, width);
        }
        return String.format("%-" + width + "s", text);
    }

    private String renderLine(List<String> values, int[] widths) {
        var sb = new StringBuilder("|");
        for (int i = 0; i < widths.length; i++) {
            var value = i < values.size() ? values.get(i) : "";
            sb.append(" ").append(fit(value, widths[i])).append(" |");
        }
        return sb.append("\n").toString();
    }

    private String renderBorder(int[] widths, Theme theme) {
        var totalWidth = widths.length + 1;
        for (var width : widths) {
            totalWidth += width + 2;
        }
        return theme.getBorderChar().repeat(totalWidth) + "\n";
    }

    private String renderTable(Theme theme) {
        var headers = data.getHeaders();
        var widths = columnWidths(headers);
        var border = renderBorder(widths, theme);

        var sb = new StringBuilder();
        sb.append(border);
        sb.append(renderLine(headers, widths));
        sb.append(border);
        for (var row : data.getRows()) {
            if (row == null) continue;
            sb.append(renderLine(row, widths));
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

    public void print(boolean alignRight, String themeStr) {
        var rows = data.getRows();
        if (rows == null || rows.isEmpty()) {
            System.out.println("ERRO: Nenhum dado para exibir.");
            return;
        }
        output(renderTable(Theme.of(themeStr)), alignRight);
    }
}
