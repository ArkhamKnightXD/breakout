package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.ContactType;

public class Wall extends GameObject {

    public Wall(Rectangle rectangle, World world) {
        super(
            new Box2DBody(rectangle, 0, world, ContactType.WALL),
            "images/wall.png", 0
        );
    }
}
