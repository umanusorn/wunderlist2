package com.vi8e.um.wunderlist.provider.list;

import android.net.Uri;
import android.provider.BaseColumns;

import com.vi8e.um.wunderlist.provider.MasterProvider;

/**
 * Table of list
 */
public class ListColumns implements BaseColumns {
    public static final String TABLE_NAME = "list";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = new String(BaseColumns._ID);

    public static final String LIST_TITLE = "list_title";

    /**
     * The commercial name of this company.
     */
    public static final String NUM_LATE_TASK = "num_late_task";

    public static final String NUM_CURRENT_TASK = "num_current_task";

    public static final String FOLDER_ID = "folder_id";

    public static final String ISPINNED = "ispinned";

    public static final String ISDISTURB = "isdisturb";

    public static final String IMG_PATH = "img_path";

    public static final String TYPE = "type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            LIST_TITLE,
            NUM_LATE_TASK,
            NUM_CURRENT_TASK,
            FOLDER_ID,
            ISPINNED,
            ISDISTURB,
            IMG_PATH,
            TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c == LIST_TITLE || c.contains("." + LIST_TITLE)) return true;
            if (c == NUM_LATE_TASK || c.contains("." + NUM_LATE_TASK)) return true;
            if (c == NUM_CURRENT_TASK || c.contains("." + NUM_CURRENT_TASK)) return true;
            if (c == FOLDER_ID || c.contains("." + FOLDER_ID)) return true;
            if (c == ISPINNED || c.contains("." + ISPINNED)) return true;
            if (c == ISDISTURB || c.contains("." + ISDISTURB)) return true;
            if (c == IMG_PATH || c.contains("." + IMG_PATH)) return true;
            if (c == TYPE || c.contains("." + TYPE)) return true;
        }
        return false;
    }

}
