package com.capputechino.psmqtplayer.layoutcontrollers;

import com.capputechino.psmqtplayer.MainApp;
import com.capputechino.psmqtplayer.models.Chapter;
import com.capputechino.psmqtplayer.utils.DocumentService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends LayoutController {
    @FXML
    private AnchorPane anchorPaneRoot;

    @FXML
    private TableView<Chapter> tableViewChapters;

    @FXML
    private TableColumn<Chapter, String> tableColumnOrderNumber = new TableColumn<>("#");
    @FXML
    private TableColumn<Chapter, String> tableColumnNameEnglish = new TableColumn<>("English Name");
    @FXML
    private TableColumn<Chapter, String> tableColumnNameArabic = new TableColumn<>("Arabic Name");

    @FXML
    private AnchorPane anchorPaneNowPlaying;

    private ObservableList<Chapter> chapters = FXCollections.observableArrayList();

    private BooleanProperty playing = new SimpleBooleanProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchChapters();
        playing.set(false);
    }

    public void setLayout() {
        ((BorderPane) this.mainApp.getRoot()).setCenter(anchorPaneRoot);
    }

    private void fetchChapters() {
        DocumentService service = new DocumentService();
        service.setOnSucceeded((WorkerStateEvent event) -> {
            JSONObject ob = service.getValue();
            JSONArray jsonArray = ob.getJSONArray("chapters");
            for (Object chap: jsonArray) {
                Chapter chapter = new Chapter();
                String order = ((JSONObject) chap).getString("order");
                String nameEng = ((JSONObject) chap).getString("title-english");
                String nameAra = ((JSONObject) chap).getString("title-arabic");

                JSONObject urlObject = ((JSONObject) chap).getJSONObject("urls");

                String audioUrl = urlObject.getString("audio");

                chapter.setChapterId(order);
                chapter.setNameEnglish(nameEng);
                chapter.setNameArabic(nameAra);
                chapter.setAudioUrl(audioUrl);

                chapters.add(chapter);
            }
            setTableViewChapters();
        });

        service.setOnFailed((WorkerStateEvent event) -> {
            System.out.println("something bad happened");
            tableViewChapters.setPlaceholder(new Label("something went wrong ..."));
        });
        service.start();
    }

    private void setTableViewChapters() {
        tableViewChapters.setItems(chapters);
        tableColumnOrderNumber.setCellValueFactory(cellData -> cellData.getValue().chapterIdProperty());
        tableColumnNameEnglish.setCellValueFactory(cellData -> cellData.getValue().nameEnglishProperty());
        tableColumnNameArabic.setCellValueFactory(cellData -> cellData.getValue().nameArabicProperty());
        setOnClickTableRow();

    }

    private void setOnClickTableRow() {
        tableViewChapters.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Chapter> observable, Chapter oldValue, Chapter newValue) -> play(newValue) );
    }

    private void play(Chapter chapter) {
        if (chapter == null) {
            playing.set(false);
        } else {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("layouts/NowPlaying.fxml"));
            try {
                loader.load();
                NowPlayingController controller = loader.getController();
                controller.setMainApp(this.getMainApp());
                controller.setParentController(this);
                controller.setChapter(chapter);
                controller.setLayoutData();
            } catch (IOException e) {
                //todo: do something
            } catch (IllegalStateException e) {
                System.out.println(e.getCause() + e.getLocalizedMessage());
            }
            playing.set(true);
        }
    }

    public BooleanProperty playingProperty() {
        return playing;
    }

    public AnchorPane getAnchorPaneNowPlaying() {
        return anchorPaneNowPlaying;
    }
}
