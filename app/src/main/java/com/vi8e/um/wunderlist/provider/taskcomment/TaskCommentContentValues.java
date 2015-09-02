package com.vi8e.um.wunderlist.provider.taskcomment;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code task_comment} table.
 */
public class TaskCommentContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TaskCommentColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TaskCommentSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable TaskCommentSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TaskCommentContentValues putCommentTitle(@Nullable String value) {
        mContentValues.put(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentContentValues putCommentTitleNull() {
        mContentValues.putNull(TaskCommentColumns.COMMENT_TITLE);
        return this;
    }

    public TaskCommentContentValues putTaskId(@Nullable String value) {
        mContentValues.put(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentContentValues putTaskIdNull() {
        mContentValues.putNull(TaskCommentColumns.TASK_ID);
        return this;
    }

    public TaskCommentContentValues putUserId(@Nullable String value) {
        mContentValues.put(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentContentValues putUserIdNull() {
        mContentValues.putNull(TaskCommentColumns.USER_ID);
        return this;
    }

    public TaskCommentContentValues putDatetime(@Nullable String value) {
        mContentValues.put(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentContentValues putDatetimeNull() {
        mContentValues.putNull(TaskCommentColumns.DATETIME);
        return this;
    }
}
