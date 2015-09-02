package com.vi8e.um.wunderlist.provider.task;

import android.net.Uri;
import android.provider.BaseColumns;

import com.vi8e.um.wunderlist.provider.MasterProvider;

/**
 * Columns for the {@code task} table.
 */
public class TaskColumns implements BaseColumns {
    public static final String TABLE_NAME = "task";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TASK_TITLE = "task_title";

    public static final String FOLDER_ID = "folder_id";

    public static final String ISSTAR = "isStar";

    public static final String ISCOMPLETE = "isComplete";

    public static final String IMG_PATH = "img_path";

    public static final String DUETODATE = "dueToDate";

    public static final String REMINDERDATE = "reminderDate";

    public static final String NOTE = "note";

    public static final String LISTID = "listId";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TASK_TITLE,
            FOLDER_ID,
            ISSTAR,
            ISCOMPLETE,
            IMG_PATH,
            DUETODATE,
            REMINDERDATE,
            NOTE,
            LISTID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TASK_TITLE) || c.contains("." + TASK_TITLE)) return true;
            if (c.equals(FOLDER_ID) || c.contains("." + FOLDER_ID)) return true;
            if (c.equals(ISSTAR) || c.contains("." + ISSTAR)) return true;
            if (c.equals(ISCOMPLETE) || c.contains("." + ISCOMPLETE)) return true;
            if (c.equals(IMG_PATH) || c.contains("." + IMG_PATH)) return true;
            if (c.equals(DUETODATE) || c.contains("." + DUETODATE)) return true;
            if (c.equals(REMINDERDATE) || c.contains("." + REMINDERDATE)) return true;
            if (c.equals(NOTE) || c.contains("." + NOTE)) return true;
            if (c.equals(LISTID) || c.contains("." + LISTID)) return true;
        }
        return false;
    }

}
