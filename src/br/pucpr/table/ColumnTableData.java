package br.pucpr.table;

import java.util.ArrayList;
import java.util.List;

public class ColumnTableData<T> implements TableData {

    private final List<T> data;
    private final List<ColumnData<? super T>> columns;

    public ColumnTableData(List<T> data, List<ColumnData<? super T>> columns) {
        this.data = data;
        this.columns = columns;
    }

    @SafeVarargs
    public ColumnTableData(List<T> data, ColumnData<? super T>... columns) {
        this(data, List.of(columns));
    }

    @Override
    public List<String> getHeaders() {
        var headers = new ArrayList<String>();
        for (var column : columns) {
            headers.add(column.getHeader());
        }
        return headers;
    }

    @Override
    public List<List<String>> getRows() {
        var rows = new ArrayList<List<String>>();
        if (data == null) return rows;

        for (var item : data) {
            if (item == null) continue;
            rows.add(toRow(item));
        }
        return rows;
    }

    private List<String> toRow(T item) {
        var row = new ArrayList<String>();
        for (var column : columns) {
            row.add(column.getValue(item));
        }
        return row;
    }
}
