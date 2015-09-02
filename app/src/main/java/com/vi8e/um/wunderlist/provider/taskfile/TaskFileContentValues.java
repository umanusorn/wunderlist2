package com.vi8e.um.wunderlist.provider.taskfile;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code task_file} table.
 */
public class TaskFileContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TaskFileColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TaskFileSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable TaskFileSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TaskFileContentValues putTitle(@Nullable String value) {
        mContentValues.put(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileContentValues putTitleNull() {
        mContentValues.putNull(TaskFileColumns.TITLE);
        return this;
    }

    public TaskFileContentValues putTaskid(@Nullable String value) {
        mContentValues.put(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileContentValues putTaskidNull() {
        mContentValues.putNull(TaskFileColumns.TASKID);
        return this;
    }

    public TaskFileContentValues putType(@Nullable String value) {
        mContentValues.put(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileContentValues putTypeNull() {
        mContentValues.putNull(TaskFileColumns.TYPE);
        return this;
    }

    public TaskFileContentValues putUrl(@Nullable String value) {
        mContentValues.put(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileContentValues putUrlNull() {
        mContentValues.putNull(TaskFileColumns.URL);
        return this;
    }
}
