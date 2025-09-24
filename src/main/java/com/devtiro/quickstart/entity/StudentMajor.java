package com.devtiro.quickstart.entity;


public enum StudentMajor {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ME("Mechanical Engineering"),
    CE("Civil Engineering"),
    BA("Business Administration"),
    PHY("Physics"),
    CHEM("Chemistry"),
    BIO("Biology"),
    MATH("Mathematics"),
    ART("Art"),
    MUS("Music"),
    HIS("History"),
    ENG("English");

    private final String displayName;

    StudentMajor(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
