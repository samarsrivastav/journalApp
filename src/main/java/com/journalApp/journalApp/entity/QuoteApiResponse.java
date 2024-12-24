package com.journalApp.journalApp.entity;

import lombok.Data;

@Data
public class QuoteApiResponse {
    private String quote;
    private String author;
    private String category;
}
