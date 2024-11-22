package com.springbootstudies.spring_boot_springdoc2.dto.request;

import com.springbootstudies.spring_boot_springdoc2.model.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @Schema(description = "Book to be saved", required = true)
    private Book book;

}
