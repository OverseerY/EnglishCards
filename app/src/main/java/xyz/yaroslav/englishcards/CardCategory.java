package xyz.yaroslav.englishcards;

import java.util.Comparator;

public class CardCategory {
    private String cardTitle;
    //private boolean isCompleted;

    public CardCategory() {}

    public CardCategory(String title) {
        this.cardTitle = title;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public static Comparator<CardCategory> CardComparator = (cat1, cat2) -> {
        String name1 = cat1.getCardTitle().toUpperCase();
        String name2 = cat2.getCardTitle().toUpperCase();
        return name1.compareTo(name2);
    };
}
