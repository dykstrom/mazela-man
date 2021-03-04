package se.mejsla.vassare.fxgl.mazelaman;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.application.Platform;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CherrySpawnComponent extends Component {

    private static final Random RANDOM = new Random();

    @Override
    public void onUpdate(double tpf) {
        // Spawn a cherry randomly if there is not a cherry already
        if (RANDOM.nextInt(1000) == 0 && noCherryAt(entity.getX(), entity.getY())) {
            Entity cherry = FXGL.spawn("Cherry", new SpawnData(entity.getX(), entity.getY()));
            despawnLater(cherry);
        }
    }

    private void despawnLater(Entity cherry) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(cherry::removeFromWorld);
                timer.cancel();
            }
        }, 10_000);
    }

    private boolean noCherryAt(double x, double y) {
        return FXGL.getGameWorld().getEntitiesByType(EntityType.CHERRY)
                .stream()
                .noneMatch(e -> e.getX() == x && e.getY() == y);
    }
}
