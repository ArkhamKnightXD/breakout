package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import knight.arkham.objects.Player;

public class GameDataHelper {

    public static void saveGameData(){

        Preferences preferences = Gdx.app.getPreferences("pong-data");

        preferences.putInteger("playerScore", Player.score);

        preferences.flush();
    }

    public static void loadGameData(){

        Preferences preferences = Gdx.app.getPreferences("breakout-data");

        Player.score = preferences.getInteger("playerScore");
    }
}
