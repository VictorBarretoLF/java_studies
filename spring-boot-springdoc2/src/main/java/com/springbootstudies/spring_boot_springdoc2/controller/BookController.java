package com.springbootstudies.spring_boot_springdoc2.controller;

import com.springbootstudies.spring_boot_springdoc2.errors.ErrorResponseWithPath;
import com.springbootstudies.spring_boot_springdoc2.model.Book;
import com.springbootstudies.spring_boot_springdoc2.dto.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(description = "Inteface para livros", name = "BookController")
public interface BookController {

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class),
                                    examples = @ExampleObject(
                                            name = "exampleBook",
                                            value = "{\"id\": 1, \"title\": \"Effective Java Book\", \"author\": \"Joshua Bloch\"}"
                                    )
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseWithPath.class),
                                    examples = @ExampleObject(
                                            name = "errorResponse",
                                            value = "{\"path\": \"id\", \"message\": \"Book not found\"}"
                                    )
                            )
                    }
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<Book> findById(
            @Parameter(description = "Id of book to be searched") @PathVariable Long id
    );

    @Operation(summary = "Save a book")
    @ApiResponses( value = {
            @ApiResponse( responseCode = "201", description = "Book created successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Book.class),
                                    examples = @ExampleObject(
                                        name = "exampleBook",
                                        value = "{\"book\": " +
                                                    "{\"title\": \"Effective Java Book\", " +
                                                    "\"author\":  \"Joshua Bloch asdjads kjasdjk dasjk asldk\"}" +
                                                "}"
                                    )
                            )
                    }
            ),
            @ApiResponse( responseCode = "400",  description = "Invalid book",
                    content = { @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorResponseWithPath.class)),
                                    examples = @ExampleObject(
                                            name = "errorResponse",
                                            value = "{\"erros\": [" +
                                                    "{\"path\": \"author\", \"message\": \"size must be between 0 and 30\"}," +
                                                    "{\"path\": \"title\", \"message\": \"size must be between 0 and 20\"}" +
                                                    "]}"
                                    )
                            )
                    }
            ),
            @ApiResponse( responseCode = "500", description = "Internal server error")
        }
    )
    ResponseEntity<Book> save(
            @Parameter(description = "Book to be saved") BookRequest BookRequest
    );

}
