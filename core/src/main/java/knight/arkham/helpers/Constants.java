package knight.arkham.helpers;

import knight.arkham.Breakout;

public class Constants {
    public static final float PIXELS_PER_METER = 32.0f;
    public static final int FULL_SCREEN_HEIGHT = Breakout.INSTANCE.screenHeight;
    public static final float BOX2D_FULL_SCREEN_HEIGHT = Breakout.INSTANCE.screenHeight / PIXELS_PER_METER;
    public static final int FULL_SCREEN_WIDTH = Breakout.INSTANCE.screenWidth;
    public static final float BOX2D_FULL_SCREEN_WIDTH = Breakout.INSTANCE.screenWidth / PIXELS_PER_METER;
}
