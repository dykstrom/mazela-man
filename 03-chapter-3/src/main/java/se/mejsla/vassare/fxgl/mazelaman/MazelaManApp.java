package se.mejsla.vassare.fxgl.mazelaman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.input.KeyCode;

public class MazelaManApp extends GameApplication {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    private static final int SPEED = 150;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("MazelaMan");
        settings.setVersion("chapter 3");
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new MazelaManFactory());
        FXGL.spawn("Background", new SpawnData(0, 0).put("width", WIDTH).put("height", HEIGHT));
        FXGL.setLevelFromMap("level1.tmx");
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 0);
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.A, "Move Left", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityX(-SPEED));
        FXGL.onKey(KeyCode.D, "Move Right", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityX(SPEED));
        FXGL.onKey(KeyCode.W, "Move Up", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityY(-SPEED));
        FXGL.onKey(KeyCode.S, "Move Down", () -> getPlayer().getComponent(PhysicsComponent.class).setVelocityY(SPEED));
    }

    private static Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
