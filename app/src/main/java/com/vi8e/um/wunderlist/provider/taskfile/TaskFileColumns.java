package com.vi8e.um.wunderlist.provider.taskfile;

import android.net.Uri;
import android.provider.BaseColumns;

import com.vi8e.um.wunderlist.provider.MasterProvider;

/**
 * Columns for the {@code task_file} table.
 */
public class TaskFileColumns implements BaseColumns {
    public static final String TABLE_NAME = "task_file";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TITLE = "title";

    public static final String TASKID = "taskId";

    public static final String TYPE = "type";

    public static final String URL = "url";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TITLE,
            TASKID,
            TYPE,
            URL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(TASKID) || c.contains("." + TASKID)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
        }
        return false;
    }

}
