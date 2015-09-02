package com.vi8e.um.wunderlist.provider.user;

import android.net.Uri;
import android.provider.BaseColumns;

import com.vi8e.um.wunderlist.provider.MasterProvider;

/**
 * Columns for the {@code user} table.
 */
public class UserColumns implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String USER_TITLE = "user_title";

    public static final String IMG_PATH = "img_path";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            USER_TITLE,
            IMG_PATH
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(USER_TITLE) || c.contains("." + USER_TITLE)) return true;
            if (c.equals(IMG_PATH) || c.contains("." + IMG_PATH)) return true;
        }
        return false;
    }

}
