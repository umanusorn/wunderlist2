package com.vi8e.um.wunderlist.provider.subtask;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code subtask} table.
 */
public class SubtaskContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return SubtaskColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable SubtaskSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable SubtaskSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public SubtaskContentValues putSubtaskTitle(@Nullable String value) {
        mContentValues.put(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskContentValues putSubtaskTitleNull() {
        mContentValues.putNull(SubtaskColumns.SUBTASK_TITLE);
        return this;
    }

    public SubtaskContentValues putTaskid(@Nullable String value) {
        mContentValues.put(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskContentValues putTaskidNull() {
        mContentValues.putNull(SubtaskColumns.TASKID);
        return this;
    }

    public SubtaskContentValues putIscomplete(@Nullable String value) {
        mContentValues.put(SubtaskColumns.ISCOMPLETE, value);
        return this;
    }

    public SubtaskContentValues putIscompleteNull() {
        mContentValues.putNull(SubtaskColumns.ISCOMPLETE);
        return this;
    }
}
