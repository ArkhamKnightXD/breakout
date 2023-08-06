package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.Ball;
import knight.arkham.objects.Brick;

import static knight.arkham.helpers.Constants.*;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Gdx.app.log("fixture A:", String.valueOf(fixtureA.getFilterData().categoryBits));
        Gdx.app.log("fixture B:", String.valueOf(fixtureB.getFilterData().categoryBits));

        int collisionDefinition = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionDefinition) {

//            It calls the same function in both cases.
            case BALL_BIT | PLAYER_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT)
                    ((Ball) fixtureA.getUserData()).reverseVelocityY();

                else
                    ((Ball) fixtureB.getUserData()).reverseVelocityY();
                break;

            case BALL_BIT | BRICK_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT){
                    ((Ball) fixtureA.getUserData()).reverseVelocityY();
                    ((Brick) fixtureB.getUserData()).hitByBall();

                }
                else{
                    ((Ball) fixtureB.getUserData()).reverseVelocityY();
                    ((Brick) fixtureB.getUserData()).hitByBall();
                }
                break;


            case BALL_BIT | WALL_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT)
                    ((Ball) fixtureA.getUserData()).reverseVelocityX();

                else
                    ((Ball) fixtureB.getUserData()).reverseVelocityX();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
