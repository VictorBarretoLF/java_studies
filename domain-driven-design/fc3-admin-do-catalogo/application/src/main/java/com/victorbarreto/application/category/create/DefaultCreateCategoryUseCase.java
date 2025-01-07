package com.victorbarreto.application.category.create;

import com.victorbarreto.domain.category.Category;
import com.victorbarreto.domain.category.CategoryGateway;
import com.victorbarreto.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway aCategoryGateway) {
        this.categoryGateway = Objects.requireNonNull(aCategoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(new ThrowsValidationHandler());

        this.categoryGateway.create(aCategory);

        return CreateCategoryOutput.from(aCategory);
    }

}
