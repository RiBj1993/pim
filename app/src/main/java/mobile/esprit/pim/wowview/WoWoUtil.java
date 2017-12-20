package mobile.esprit.pim.wowview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by chicago on 18/12/2017.
 */

class WoWoUtil    {

    private static int screenWidth = -1;
    private static int screenHeight = -1;

    /**
     * get the screen width in pixels
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (screenWidth == -1) {
            Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }
        return screenWidth;
    }

    /**
     * get the screen height in pixels
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (screenHeight == -1) {
            Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }
        return screenHeight;
    }

    /**
     * dp to px
     * @param dp
     * @param mContext
     * @return
     */
    public static int dp2px(int dp, Context mContext) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    private static WoWoUtil ourInstance = new WoWoUtil();

    public static WoWoUtil getInstance() {
        return ourInstance;
    }

    private WoWoUtil() {
    }
}