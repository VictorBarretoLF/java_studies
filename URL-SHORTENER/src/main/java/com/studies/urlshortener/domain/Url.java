package com.studies.urlshortener.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "URL_TABLE")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long urlId;

    @Column(nullable = false, length = 7)
    private String shortUrl;

    @Column(nullable = false)
    private String longUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime creationDate;

}
