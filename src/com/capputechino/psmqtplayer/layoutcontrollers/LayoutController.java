package com.capputechino.psmqtplayer.layoutcontrollers;

import com.capputechino.psmqtplayer.MainApp;
import javafx.fxml.Initializable;

public abstract class LayoutController implements Initializable {
    protected MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public MainApp getMainApp() {
        return mainApp;
    }
}
