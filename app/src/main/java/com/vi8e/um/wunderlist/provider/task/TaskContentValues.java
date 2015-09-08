package com.vi8e.um.wunderlist.provider.task;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code task} table.
 */
public class TaskContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TaskColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TaskSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable TaskSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TaskContentValues putTaskTitle(@Nullable String value) {
        mContentValues.put(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskContentValues putTaskTitleNull() {
        mContentValues.putNull(TaskColumns.TASK_TITLE);
        return this;
    }

    public TaskContentValues putFolderId(@Nullable String value) {
        mContentValues.put(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskContentValues putFolderIdNull() {
        mContentValues.putNull(TaskColumns.FOLDER_ID);
        return this;
    }

    public TaskContentValues putIsstar(@Nullable String value) {
        mContentValues.put(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskContentValues putIsstarNull() {
        mContentValues.putNull(TaskColumns.ISSTAR);
        return this;
    }

    public TaskContentValues putIscomplete(@Nullable String value) {
        mContentValues.put(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskContentValues putIscompleteNull() {
        mContentValues.putNull(TaskColumns.ISCOMPLETE);
        return this;
    }

    public TaskContentValues putImgPath(@Nullable String value) {
        mContentValues.put(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskContentValues putImgPathNull() {
        mContentValues.putNull(TaskColumns.IMG_PATH);
        return this;
    }

    public TaskContentValues putCreateDate(@Nullable String value) {
        mContentValues.put(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskContentValues putCreateDateNull() {
        mContentValues.putNull(TaskColumns.CREATE_DATE);
        return this;
    }

    public TaskContentValues putDuetoDate(@Nullable String value) {
        mContentValues.put(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskContentValues putDuetoDateNull() {
        mContentValues.putNull(TaskColumns.DUETO_DATE);
        return this;
    }

    public TaskContentValues putReminderDate(@Nullable String value) {
        mContentValues.put(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskContentValues putReminderDateNull() {
        mContentValues.putNull(TaskColumns.REMINDER_DATE);
        return this;
    }

    public TaskContentValues putNote(@Nullable String value) {
        mContentValues.put(TaskColumns.NOTE, value);
        return this;
    }

    public TaskContentValues putNoteNull() {
        mContentValues.putNull(TaskColumns.NOTE);
        return this;
    }

    public TaskContentValues putListid(@Nullable String value) {
        mContentValues.put(TaskColumns.LISTID, value);
        return this;
    }

    public TaskContentValues putListidNull() {
        mContentValues.putNull(TaskColumns.LISTID);
        return this;
    }
}
