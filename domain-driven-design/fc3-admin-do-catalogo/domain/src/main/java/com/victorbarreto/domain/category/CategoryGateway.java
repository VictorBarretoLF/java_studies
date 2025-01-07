package com.victorbarreto.domain.category;

import com.victorbarreto.domain.pagination.Pagination;
import com.victorbarreto.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category category);

    void deleteById(CategoryID id);

    Optional<Category> findById(CategoryID id);

    Category update(Category category);

    Pagination<Category> findAll(SearchQuery aQuery);
}
