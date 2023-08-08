package knight.arkham.helpers;

import knight.arkham.Breakout;

public class Constants {
    public static final float PIXELS_PER_METER = 32.0f;
    public static final int FULL_SCREEN_HEIGHT = Breakout.INSTANCE.screenHeight;
    public static final int FULL_SCREEN_WIDTH = Breakout.INSTANCE.screenWidth;
    public static final short BALL_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short WALL_BIT = 8;
    public static final short CEILING_BIT = 16;
}
