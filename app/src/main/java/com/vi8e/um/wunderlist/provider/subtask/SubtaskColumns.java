package com.vi8e.um.wunderlist.provider.subtask;

import android.net.Uri;
import android.provider.BaseColumns;

import com.vi8e.um.wunderlist.provider.MasterProvider;

/**
 * Columns for the {@code subtask} table.
 */
public class SubtaskColumns implements BaseColumns {
    public static final String TABLE_NAME = "subtask";
    public static final Uri CONTENT_URI = Uri.parse(MasterProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String SUBTASK_TITLE = "subtask_title";

    public static final String TASKID = "taskId";

    public static final String ISCOMPLETE = "isComplete";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SUBTASK_TITLE,
            TASKID,
            ISCOMPLETE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SUBTASK_TITLE) || c.contains("." + SUBTASK_TITLE)) return true;
            if (c.equals(TASKID) || c.contains("." + TASKID)) return true;
            if (c.equals(ISCOMPLETE) || c.contains("." + ISCOMPLETE)) return true;
        }
        return false;
    }

}
