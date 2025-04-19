package br.com.fiap.Bloom.model;

import java.time.LocalDate;

public record PostFilter(
    String title,
    String author,
     LocalDate startDate,
     LocalDate endDate
) {
}
