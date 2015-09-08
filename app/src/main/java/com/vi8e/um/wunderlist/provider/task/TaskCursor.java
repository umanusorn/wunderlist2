package com.vi8e.um.wunderlist.provider.task;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code task} table.
 */
public class TaskCursor extends AbstractCursor implements TaskModel {
    public TaskCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TaskColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code task_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTaskTitle() {
        String res = getStringOrNull(TaskColumns.TASK_TITLE);
        return res;
    }

    /**
     * Get the {@code folder_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getFolderId() {
        String res = getStringOrNull(TaskColumns.FOLDER_ID);
        return res;
    }

    /**
     * Get the {@code isstar} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getIsstar() {
        String res = getStringOrNull(TaskColumns.ISSTAR);
        return res;
    }

    /**
     * Get the {@code iscomplete} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getIscomplete() {
        String res = getStringOrNull(TaskColumns.ISCOMPLETE);
        return res;
    }

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImgPath() {
        String res = getStringOrNull(TaskColumns.IMG_PATH);
        return res;
    }

    /**
     * Get the {@code create_date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getCreateDate() {
        String res = getStringOrNull(TaskColumns.CREATE_DATE);
        return res;
    }

    /**
     * Get the {@code dueto_date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getDuetoDate() {
        String res = getStringOrNull(TaskColumns.DUETO_DATE);
        return res;
    }

    /**
     * Get the {@code reminder_date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getReminderDate() {
        String res = getStringOrNull(TaskColumns.REMINDER_DATE);
        return res;
    }

    /**
     * Get the {@code note} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getNote() {
        String res = getStringOrNull(TaskColumns.NOTE);
        return res;
    }

    /**
     * Get the {@code listid} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getListid() {
        String res = getStringOrNull(TaskColumns.LISTID);
        return res;
    }
}
