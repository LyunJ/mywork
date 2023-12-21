package com.example.mywork.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
@Getter
public enum TeamType {
    DQA13(1);

    private final Integer type;


    @JsonCreator
    public static TeamType from(Integer value) {
        for (TeamType l_type : TeamType.values()) {
            if (l_type.getValue().equals(value)) {
                return l_type;
            }
        }
        return null;
    }

    @JsonValue
    public Integer getValue() {
        return this.type;
    }
}
