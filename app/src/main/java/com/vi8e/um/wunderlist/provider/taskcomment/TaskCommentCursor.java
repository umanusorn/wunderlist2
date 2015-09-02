package com.vi8e.um.wunderlist.provider.taskcomment;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code task_comment} table.
 */
public class TaskCommentCursor extends AbstractCursor implements TaskCommentModel {
    public TaskCommentCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TaskCommentColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code comment_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCommentTitle() {
        String res = getStringOrNull(TaskCommentColumns.COMMENT_TITLE);
        return res;
    }

    /**
     * Get the {@code task_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTaskId() {
        String res = getStringOrNull(TaskCommentColumns.TASK_ID);
        return res;
    }

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getUserId() {
        String res = getStringOrNull(TaskCommentColumns.USER_ID);
        return res;
    }

    /**
     * Get the {@code datetime} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDatetime() {
        String res = getStringOrNull(TaskCommentColumns.DATETIME);
        return res;
    }
}
