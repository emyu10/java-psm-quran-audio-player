package com.capputechino.psmqtplayer.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Formatter;

public class Chapter {
    private StringProperty chapterId = new SimpleStringProperty();
    private StringProperty nameEnglish = new SimpleStringProperty();
    private StringProperty nameArabic = new SimpleStringProperty();
    private StringProperty audioUrl = new SimpleStringProperty();

    public void setChapterId(String id) {
        chapterId.set(String.format("%03d", Integer.parseInt(id)));
    }

    public StringProperty chapterIdProperty() {
        return chapterId;
    }

    public String getChapterId() {
        return chapterId.get();
    }


    public void setNameEnglish(String name) {
        nameEnglish.set(name);
    }

    public StringProperty nameEnglishProperty() {
        return nameEnglish;
    }

    public String getNameEnglish() {
        return nameEnglish.get();
    }

    public void setNameArabic(String name) {
        nameArabic.set(name);
    }

    public StringProperty nameArabicProperty() {
        return nameArabic;
    }

    public String getNameArabic() {
        return nameArabic.get();
    }

    public void setAudioUrl(String url) {
        this.audioUrl.set(url);
    }

    public String getAudioUrl() {
        return this.audioUrl.get();
    }

    public StringProperty audioUrlProperty() {
        return audioUrl;
    }
}
