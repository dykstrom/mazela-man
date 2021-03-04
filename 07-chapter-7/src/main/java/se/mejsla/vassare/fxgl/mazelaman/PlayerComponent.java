package se.mejsla.vassare.fxgl.mazelaman;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class PlayerComponent extends Component {

    private static final int SPEED = 150;

    private final double x;
    private final double y;

    private final AnimatedTexture texture;

    private final AnimationChannel left;
    private final AnimationChannel right;
    private final AnimationChannel upDown;

    private PhysicsComponent physics;

    public PlayerComponent(double x, double y) {
        this.x = x;
        this.y = y;
        left = new AnimationChannel(FXGL.image("player-left.png"), Duration.seconds(0.5), 6);
        right = new AnimationChannel(FXGL.image("player-right.png"), Duration.seconds(0.5), 6);
        upDown = new AnimationChannel(FXGL.image("player-up-down.png"), Duration.seconds(0.5), 6);
        texture = new AnimatedTexture(upDown);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(upDown);
    }

    public void left() {
        physics.setVelocityX(-SPEED);
        if (texture.getAnimationChannel() != left) {
            texture.loopAnimationChannel(left);
        }
    }

    public void right() {
        physics.setVelocityX(SPEED);
        if (texture.getAnimationChannel() != right) {
            texture.loopAnimationChannel(right);
        }
    }

    public void up() {
        physics.setVelocityY(-SPEED);
        if (texture.getAnimationChannel() != upDown) {
            texture.loopAnimationChannel(upDown);
        }
    }

    public void down() {
        physics.setVelocityY(SPEED);
        if (texture.getAnimationChannel() != upDown) {
            texture.loopAnimationChannel(upDown);
        }
    }

    public void respawn() {
        entity.removeFromWorld();
        spawn("Player", new SpawnData(x, y));
    }
}
