package knight.arkham.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.Breakout;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.GameDataHelper;
import knight.arkham.screens.GameScreen;

import static knight.arkham.helpers.Constants.FULL_SCREEN_HEIGHT;
import static knight.arkham.helpers.Constants.FULL_SCREEN_WIDTH;

public class PauseMenu {
    private final Breakout game;
    public final Stage stage;
    private final Skin skin;

    public PauseMenu() {

        game = Breakout.INSTANCE;

        Viewport viewport = new ExtendViewport(FULL_SCREEN_WIDTH, FULL_SCREEN_HEIGHT);

        stage = new Stage(viewport);

        Table table = new Table();

        table.setFillParent(true);

        Label pauseLabel = new Label("Pause Menu", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label scoreLabel = new Label("High Score: " + GameDataHelper.loadHighScore(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(pauseLabel).expandX().padBottom(15);
        table.row();

        table.add(scoreLabel).expandX().padBottom(15);
        table.row();

        stage.addActor(table);

        skin = AssetsHelper.loadUiSkin();

        addButton(table, "Resume").addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.isGamePaused = false;

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addButton(table, "Retry").addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen());

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addButton(table, "Quit").addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();

                return super.touchDown(event, x, y, pointer, button);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(Table table, String buttonName) {

        TextButton textButton = new TextButton(buttonName, skin);

        table.add(textButton).width(150).height(60).padBottom(15);
        table.row();

        return textButton;
    }

    public void dispose(){
        stage.dispose();
        skin.dispose();
    }
}
