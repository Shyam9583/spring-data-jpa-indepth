package online.technotap.springdatajpaindepth.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@ToString
public class PageableResult<T> {
    private long totalItems;
    private int currentPage;
    private int totalPages;
    private List<T> items;

    public PageableResult(Page<T> page) {
        totalItems = page.getTotalElements();
        currentPage = page.getNumber();
        totalPages = page.getTotalPages();
        items = page.getContent();
    }
}
