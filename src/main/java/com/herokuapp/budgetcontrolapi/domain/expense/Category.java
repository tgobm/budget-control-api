package com.herokuapp.budgetcontrolapi.domain.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    FOOD("FOOD"), //
    HEALTH("HEALTH"), //
    HOME("HOME"), //
    TRANSPORT("TRANSPORT"), //
    EDUCATION("EDUCATION"), //
    LEISURE("LEISURE"), //
    UNFORESEEN("UNFORESEEN"), //
    OTHERS("OTHERS");

    private final String value;
}