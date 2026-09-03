package br.pucpr.table;

public class Paginator {

    private static final String INDENT = "                    ";

    private final PagedTableData pagedData;
    private final Table table;

    public Paginator(TableData data, int pageSize) {
        this.pagedData = new PagedTableData(data, pageSize);
        this.table = new Table(pagedData);
    }

    public void print(boolean alignRight, String theme) {
        var info = String.format("[ Página %d de %d ]", getCurrentPage() + 1, getTotalPages());
        System.out.println(alignRight ? INDENT + info : info);
        table.print(alignRight, theme);
    }

    public void nextPage() { pagedData.nextPage(); }
    public void prevPage() { pagedData.prevPage(); }
    public void goToPage(int page) { pagedData.goToPage(page); }
    public int getCurrentPage() { return pagedData.getCurrentPage(); }
    public int getTotalPages() { return pagedData.getTotalPages(); }
    public boolean hasNextPage() { return pagedData.hasNextPage(); }
    public boolean hasPrevPage() { return pagedData.hasPrevPage(); }
}
