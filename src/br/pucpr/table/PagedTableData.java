package br.pucpr.table;

import java.util.List;

public class PagedTableData implements TableData {

    private final TableData delegate;
    private final int pageSize;
    private int currentPage;

    public PagedTableData(TableData delegate, int pageSize) {
        if (pageSize <= 0) throw new IllegalArgumentException("pageSize must be positive");
        this.delegate = delegate;
        this.pageSize = pageSize;
        this.currentPage = 0;
    }

    @Override
    public List<String> getHeaders() {
        return delegate.getHeaders();
    }

    @Override
    public List<List<String>> getRows() {
        var all = delegate.getRows();
        int start = currentPage * pageSize;
        if (start >= all.size()) return List.of();
        return all.subList(start, Math.min(start + pageSize, all.size()));
    }

    public int getTotalPages() {
        int size = delegate.getRows().size();
        return size == 0 ? 1 : (int) Math.ceil((double) size / pageSize);
    }

    public int getCurrentPage() { return currentPage; }

    public boolean hasNextPage() { return currentPage < getTotalPages() - 1; }
    public boolean hasPrevPage() { return currentPage > 0; }

    public void nextPage() { if (hasNextPage()) currentPage++; }
    public void prevPage() { if (hasPrevPage()) currentPage--; }

    public void goToPage(int page) {
        int total = getTotalPages();
        if (page < 0 || page >= total) {
            throw new IllegalArgumentException("Página " + page + " fora do intervalo [0, " + (total - 1) + "]");
        }
        this.currentPage = page;
    }
}
