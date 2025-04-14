package com.guld.sciq.search.dto;

import java.time.LocalDateTime;

public record SearchFilter(
    String keyword,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String category,
    SearchSortType sortType
) {} 