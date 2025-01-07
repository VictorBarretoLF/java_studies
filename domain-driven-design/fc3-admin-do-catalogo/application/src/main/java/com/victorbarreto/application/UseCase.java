package com.victorbarreto.application;

import com.victorbarreto.domain.category.Category;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

}