package se.mejsla.vassare.fxgl.mazelaman;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MazelaManApp extends GameApplication {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;

    private static int startLevel = 1;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("MazelaMan");
        settings.setVersion("chapter 8");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("level", startLevel);
        vars.put("score", 0);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new MazelaManFactory());
        initLevel();
    }

    private void initLevel() {
        spawn("Background", new SpawnData(0, 0).put("width", WIDTH).put("height", HEIGHT));
        setLevelFromMap("level" + geti("level") + ".tmx");
        // Store the number of pills on this level
        FXGL.set("pills", getGameWorld().getEntitiesByType(EntityType.PILL).size());
    }

    @Override
    protected void initUI() {
        Label scoreLabel = new Label();
        scoreLabel.setTextFill(Color.LIGHTGRAY);
        scoreLabel.setFont(Font.font(20.0));
        scoreLabel.textProperty().bind(FXGL.getip("score").asString("Score: %d"));
        FXGL.addUINode(scoreLabel, 20, 10);

        Label levelLabel = new Label();
        levelLabel.setTextFill(Color.LIGHTGRAY);
        levelLabel.setFont(Font.font(20.0));
        levelLabel.textProperty().bind(FXGL.getip("level").asString("Level: %d"));
        FXGL.addUINode(levelLabel, 720, 10);
    }

    @Override
    protected void initPhysics() {
        PhysicsWorld physics = FXGL.getPhysicsWorld();
        physics.setGravity(0, 0);
        physics.addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.PILL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity pill) {
                pill.removeFromWorld();
                FXGL.inc("score", 10);
                FXGL.inc("pills", -1);
                if (geti("pills") == 0) {
                    FXGL.inc("level", 1);
                    Platform.runLater(() -> initLevel());
                }
            }
        });
        physics.addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.CHERRY) {
            @Override
            protected void onCollisionBegin(Entity player, Entity cherry) {
                cherry.removeFromWorld();
                FXGL.inc("score", 100);
            }
        });
        physics.addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.GHOST) {
            @Override
            protected void onCollisionBegin(Entity player, Entity ghost) {
                FXGL.getGameWorld()
                        .getEntitiesByType(EntityType.GHOST)
                        .forEach(entity -> entity.getComponent(GhostComponent.class).respawn());
                player.getComponent(PlayerComponent.class).respawn();
            }
        });
        physics.addCollisionHandler(new CollisionHandler(EntityType.GHOST, EntityType.WALL) {
            @Override
            protected void onCollisionBegin(Entity ghost, Entity wall) {
                ghost.getComponent(GhostComponent.class).turn();
            }
        });
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.A, "Move Left", () -> getPlayer().getComponent(PlayerComponent.class).left());
        FXGL.onKey(KeyCode.D, "Move Right", () -> getPlayer().getComponent(PlayerComponent.class).right());
        FXGL.onKey(KeyCode.W, "Move Up", () -> getPlayer().getComponent(PlayerComponent.class).up());
        FXGL.onKey(KeyCode.S, "Move Down", () -> getPlayer().getComponent(PlayerComponent.class).down());
    }

    private static Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            startLevel = Integer.parseInt(args[0]);
        }
        launch(args);
    }
}
