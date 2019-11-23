package xyz.yaroslav.englishcards;

public class CardItem {
    private String titleCategory;
    private String bodyValue;

    public CardItem(String title, String value) {
        this.titleCategory = title;
        this.bodyValue = value;
    }

    public String getBodyValue() {
        return bodyValue;
    }

    public String getTitleCategory() {
        return titleCategory;
    }
}
