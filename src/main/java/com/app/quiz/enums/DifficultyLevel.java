package com.app.quiz.enums;

import lombok.Getter;

@Getter
public enum DifficultyLevel {

    EASY("Easy", "সহজ"),
    MEDIUM("Medium", "মধ্যম"),
    HARD("Hard", "কঠিন");

    private final String nameEn;
    private final String nameBn;

    DifficultyLevel(String nameEn, String nameBn) {
        this.nameEn = nameEn;
        this.nameBn = nameBn;
    }
}
