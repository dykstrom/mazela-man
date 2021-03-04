package se.mejsla.vassare.fxgl.mazelaman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MazelaManApp extends GameApplication {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("MazelaMan");
        settings.setVersion("introduction");
    }

    @Override
    protected void initUI() {
        Label label = new Label("Hello, FXGL!");
        label.setFont(Font.font(20.0));
        FXGL.addUINode(label, 350.0, 290.0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
