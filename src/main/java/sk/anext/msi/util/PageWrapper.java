package sk.anext.msi.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;

public class PageWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 5;
    private Page<T> page;
    private List<PageItem> items;
    private int currentNumber;
    private String url;
    private String sortProperty;
    private String sortDirection;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageWrapper(Page<T> page, String url) {
        this.page = page;
        this.url = url;
        items = new ArrayList<PageItem>();

        currentNumber = page.getNumber() + 1; // start from 1 to match page.page

        int start, size;
        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
            start = 1;
            size = page.getTotalPages();
        } else {
            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else {
                start = currentNumber - MAX_PAGE_ITEM_DISPLAY / 2;
                size = MAX_PAGE_ITEM_DISPLAY;
            }
        }

        for (int i = 0; i < size; i++) {
            items.add(new PageItem(start + i, (start + i) == currentNumber));
        }

        if (page.getSort() != null) {
            Iterator<Order> s = page.getSort().iterator();
            if (s.hasNext()) {
                Order o = s.next();
                sortDirection = o.getDirection().toString();
                sortProperty = o.getProperty();
            }
        }
    }

    public List<PageItem> getItems() {
        return items;
    }

    public int getNumber() {
        return currentNumber;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    public boolean isHasPreviousPage() {
        return page.hasPrevious();
    }

    public boolean isHasNextPage() {
        return page.hasNext();
    }

    public String getDirection(String sortColumn) {
        if (sortColumn.equals(sortProperty)) {
            if ("ASC".equalsIgnoreCase(sortDirection)) {
                return "DESC";
            } else {
                return "ASC";
            }
        }
        return "DESC";
    }

    public String getSort() {
        if (sortProperty != null && sortDirection != null && !sortProperty.isEmpty() && !sortDirection.isEmpty())
            return sortProperty + "," + sortDirection;
        return "";
    }

    public String getSortStyle(String sortColumn) {
        if (sortColumn != null && sortColumn.equals(sortProperty)) {
            if (isDesc())
                return "sortDesc";
            else if (isAsc())
                return "sortAsc";
            else
                return "sortNone";
        }
        return "sortNone";
    }

    public boolean isDesc() {
        if ("DESC".equals(sortDirection))
            return true;
        return false;
    }

    public boolean isAsc() {
        if ("ASC".equals(sortDirection))
            return true;
        return false;
    }

    public class PageItem {
        private int number;
        private boolean current;

        public PageItem(int number, boolean current) {
            this.number = number;
            this.current = current;
        }

        public int getNumber() {
            return this.number;
        }

        public boolean isCurrent() {
            return this.current;
        }
    }
}
