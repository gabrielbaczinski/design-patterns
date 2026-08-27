package br.pucpr.table;

public interface ColumnData<T> {

    String getHeader();

    String getValue(T item);
}
