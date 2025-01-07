package com.victorbarreto.application.category.create;

import com.victorbarreto.domain.category.Category;
import com.victorbarreto.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }

}
