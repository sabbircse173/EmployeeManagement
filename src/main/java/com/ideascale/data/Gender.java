package com.ideascale.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE(1L, "male"),
    FEMALE(2L, "female"),
    OTHER(3L, "Other");

    private final Long id;
    private final String name;

    public static boolean contains(String trimmedTerm) {
        return Arrays.stream(Gender.values()).anyMatch(gender -> gender.getName().equalsIgnoreCase(trimmedTerm));
    }

    public static Gender getById(Long id) {
        return Arrays.stream(Gender.values())
                .filter(gender -> Objects.equals(gender.getId(), id))
                .findFirst()
                .orElseThrow();
    }

    public static Gender getByName(String name) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow();
    }
}
