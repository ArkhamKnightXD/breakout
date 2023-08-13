package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import knight.arkham.Breakout;

public class AssetsHelper {

    public static Sound loadSound(String filename){
        return Gdx.audio.newSound(Gdx.files.internal("sounds/"+ filename));
    }

    public static Music loadMusic(String filename){
        return Gdx.audio.newMusic(Gdx.files.internal("music/"+ filename));
    }

    public static Skin loadUiSkin() {

        //This method create an assetManager every time that is call. This could inefficient,
        // I should try to find a better way to load the uiSkin.
        AssetManager assetManager = new AssetManager();

        assetManager.load(Breakout.INSTANCE.uiSkin);

        assetManager.finishLoading();

        return assetManager.get(Breakout.INSTANCE.uiSkin);
    }
}
