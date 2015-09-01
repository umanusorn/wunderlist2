package com.vi8e.um.wunderlist.provider.list;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code list} table.
 */
public class ListCursor extends AbstractCursor implements ListModel {
    public ListCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ListColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code list_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getListTitle() {
        String res = getStringOrNull(ListColumns.LIST_TITLE);
        return res;
    }

    /**
     * The commercial name of this company.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getNumLateTask() {
        String res = getStringOrNull(ListColumns.NUM_LATE_TASK);
        if (res == null)
            throw new NullPointerException("The value of 'num_late_task' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code num_current_task} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getNumCurrentTask() {
        String res = getStringOrNull(ListColumns.NUM_CURRENT_TASK);
        return res;
    }

    /**
     * Get the {@code folder_id} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getFolderId() {
        String res = getStringOrNull(ListColumns.FOLDER_ID);
        return res;
    }

    /**
     * Get the {@code ispinned} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getIspinned() {
        String res = getStringOrNull(ListColumns.ISPINNED);
        return res;
    }

    /**
     * Get the {@code isdisturb} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getIsdisturb() {
        String res = getStringOrNull(ListColumns.ISDISTURB);
        return res;
    }

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getImgPath() {
        String res = getStringOrNull(ListColumns.IMG_PATH);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getType() {
        String res = getStringOrNull(ListColumns.TYPE);
        return res;
    }
}
