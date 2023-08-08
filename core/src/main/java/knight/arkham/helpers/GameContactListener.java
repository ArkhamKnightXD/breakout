package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.Ball;
import knight.arkham.objects.Brick;
import knight.arkham.objects.Player;
import knight.arkham.objects.structures.Ceiling;
import knight.arkham.objects.structures.Wall;

import static knight.arkham.helpers.Constants.*;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int collisionDefinition = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionDefinition) {

            case BALL_BIT | PLAYER_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT){
                    ((Ball) fixtureA.getUserData()).reverseVelocityY();
                    ((Player) fixtureB.getUserData()).hitTheBall();
                }

                else{
                    ((Ball) fixtureB.getUserData()).reverseVelocityY();
                    ((Player) fixtureA.getUserData()).hitTheBall();
                }
                break;

            case BALL_BIT | BRICK_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT){
                    ((Ball) fixtureA.getUserData()).reverseVelocityY();
                    ((Brick) fixtureB.getUserData()).hitByTheBall();

                }
                else{
                    ((Ball) fixtureB.getUserData()).reverseVelocityY();
                    ((Brick) fixtureA.getUserData()).hitByTheBall();
                }
                break;


            case BALL_BIT | WALL_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT){
                    ((Ball) fixtureA.getUserData()).reverseVelocityX();
                    ((Wall) fixtureB.getUserData()).hitByTheBall();
                }

                else{
                    ((Ball) fixtureB.getUserData()).reverseVelocityX();
                    ((Wall) fixtureA.getUserData()).hitByTheBall();
                }
                break;

            case BALL_BIT | CEILING_BIT:

                if (fixtureA.getFilterData().categoryBits == BALL_BIT){
                    ((Ball) fixtureA.getUserData()).reverseVelocityY();
                    ((Ceiling) fixtureB.getUserData()).hitByTheBall();
                }

                else{
                    ((Ball) fixtureB.getUserData()).reverseVelocityY();
                    ((Ceiling) fixtureA.getUserData()).hitByTheBall();
                }
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
