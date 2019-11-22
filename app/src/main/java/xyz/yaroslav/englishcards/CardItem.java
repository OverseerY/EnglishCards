package xyz.yaroslav.englishcards;

public class CardItem {
    private String titleCategory;
    private String bodyValue;
    //private boolean isCompleted;

    public CardItem() {}

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

    public void setBodyValue(String bodyValue) {
        this.bodyValue = bodyValue;
    }

    public void setTitleCategory(String titleCategory) {
        this.titleCategory = titleCategory;
    }
}
