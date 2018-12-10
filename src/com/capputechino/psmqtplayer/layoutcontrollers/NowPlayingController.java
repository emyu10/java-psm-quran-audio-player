package com.capputechino.psmqtplayer.layoutcontrollers;

import com.capputechino.psmqtplayer.models.Chapter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class NowPlayingController extends LayoutController {
    @FXML
    private MediaView mediaViewPlayer;

    @FXML
    private AnchorPane anchorPaneRoot;

    private MainController parentController;

    private Chapter chapter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLayoutData() {
        try {
//            Media media = new Media(chapter.getAudioUrl());
            Media media = new Media("file://" + getClass().getResource("test.mp3").toExternalForm());
            MediaPlayer player = new MediaPlayer(media);
            player.setAutoPlay(true);
            mediaViewPlayer.setMediaPlayer(player);
            parentController.getAnchorPaneNowPlaying().getChildren().add(anchorPaneRoot);
            System.out.println(player.getStatus());
            player.setOnReady(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ready");
                }
            });
            player.setOnError(new Runnable() {
                @Override
                public void run() {
                    System.out.println("error");
                }
            });
            media.setOnError(() -> {
                System.out.println("media error happened");
                System.out.println(media.getError().toString());
            });
        } catch (Exception e) {
            System.out.println("some exception");
            e.printStackTrace();
        }
    }

    public void setParentController(MainController controller) {
        this.parentController = controller;
    }

    public void setChapter(Chapter chap) {
        chapter = chap;
    }
}
