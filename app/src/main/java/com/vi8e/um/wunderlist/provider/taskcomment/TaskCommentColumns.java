package com.vi8e.um.wunderlist.provider.taskcomment;

import android.net.Uri;
import android.provider.BaseColumns;

import com.vi8e.um.wunderlist.provider.MasterProvider;

/**
 * Columns for the {@code task_comment} table.
 */
public class TaskCommentColumns implements BaseColumns {
    public static final String TABLE_NAME = "task_comment";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String COMMENT_TITLE = "comment_title";

    public static final String TASK_ID = "task_id";

    public static final String USER_ID = "user_id";

    public static final String DATETIME = "dateTime";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            COMMENT_TITLE,
            TASK_ID,
            USER_ID,
            DATETIME
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(COMMENT_TITLE) || c.contains("." + COMMENT_TITLE)) return true;
            if (c.equals(TASK_ID) || c.contains("." + TASK_ID)) return true;
            if (c.equals(USER_ID) || c.contains("." + USER_ID)) return true;
            if (c.equals(DATETIME) || c.contains("." + DATETIME)) return true;
        }
        return false;
    }

}
