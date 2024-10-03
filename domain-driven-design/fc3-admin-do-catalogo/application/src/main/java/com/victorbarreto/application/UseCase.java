package com.victorbarreto.application;

import com.victorbarreto.domain.category.Category;

public class UseCase {

    public Category execute() {
        return Category.newCategory("name", "description", true);
    }

}