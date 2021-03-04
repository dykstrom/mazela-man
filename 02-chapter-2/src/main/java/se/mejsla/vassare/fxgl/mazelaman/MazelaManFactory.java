package se.mejsla.vassare.fxgl.mazelaman;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

@SuppressWarnings("unused")
public class MazelaManFactory implements EntityFactory {

    @Spawns("Background")
    public Entity spawnBackground(SpawnData data) {
        return entityBuilder(data)
                .view(new Rectangle(data.<Integer>get("width"), data.<Integer>get("height"), Color.BLACK))
                .with(new IrremovableComponent())
                .zIndex(-100)
                .build();
    }

    @Spawns("Player")
    public Entity spawnPlayer(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .viewWithBBox("player.png")
                .build();
    }
}
