package com.vi8e.um.wunderlist.provider.subtask;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code subtask} table.
 */
public class SubtaskCursor extends AbstractCursor implements SubtaskModel {
    public SubtaskCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(SubtaskColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code subtask_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSubtaskTitle() {
        String res = getStringOrNull(SubtaskColumns.SUBTASK_TITLE);
        return res;
    }

    /**
     * Get the {@code taskid} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTaskid() {
        String res = getStringOrNull(SubtaskColumns.TASKID);
        return res;
    }

    /**
     * Get the {@code iscomplete} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getIscomplete() {
        String res = getStringOrNull(SubtaskColumns.ISCOMPLETE);
        return res;
    }
}
