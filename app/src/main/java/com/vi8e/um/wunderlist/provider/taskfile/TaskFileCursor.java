package com.vi8e.um.wunderlist.provider.taskfile;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code task_file} table.
 */
public class TaskFileCursor extends AbstractCursor implements TaskFileModel {
    public TaskFileCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TaskFileColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(TaskFileColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code taskid} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTaskid() {
        String res = getStringOrNull(TaskFileColumns.TASKID);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getType() {
        String res = getStringOrNull(TaskFileColumns.TYPE);
        return res;
    }

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getUrl() {
        String res = getStringOrNull(TaskFileColumns.URL);
        return res;
    }
}
