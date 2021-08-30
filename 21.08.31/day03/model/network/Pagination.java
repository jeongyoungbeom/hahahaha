package com.koreait.day03.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {
    private Integer totalPages;
    private  Long totalElements;
    private Integer currentPage;
    private Integer currentElements;
}
