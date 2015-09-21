package com.vi8e.um.wunderlist.provider.subtask;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code subtask} table.
 */
public class SubtaskSelection extends AbstractSelection<SubtaskSelection> {
    @Override
    protected Uri baseUri() {
        return SubtaskColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code SubtaskCursor} object, which is positioned before the first entry, or null.
     */
    public SubtaskCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new SubtaskCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public SubtaskCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code SubtaskCursor} object, which is positioned before the first entry, or null.
     */
    public SubtaskCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new SubtaskCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public SubtaskCursor query(Context context) {
        return query(context, null);
    }


    public SubtaskSelection id(long... value) {
        addEquals("subtask." + SubtaskColumns._ID, toObjectArray(value));
        return this;
    }

    public SubtaskSelection idNot(long... value) {
        addNotEquals("subtask." + SubtaskColumns._ID, toObjectArray(value));
        return this;
    }

    public SubtaskSelection orderById(boolean desc) {
        orderBy("subtask." + SubtaskColumns._ID, desc);
        return this;
    }

    public SubtaskSelection orderById() {
        return orderById(false);
    }

    public SubtaskSelection subtaskTitle(String... value) {
        addEquals(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskSelection subtaskTitleNot(String... value) {
        addNotEquals(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskSelection subtaskTitleLike(String... value) {
        addLike(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskSelection subtaskTitleContains(String... value) {
        addContains(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskSelection subtaskTitleStartsWith(String... value) {
        addStartsWith(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskSelection subtaskTitleEndsWith(String... value) {
        addEndsWith(SubtaskColumns.SUBTASK_TITLE, value);
        return this;
    }

    public SubtaskSelection orderBySubtaskTitle(boolean desc) {
        orderBy(SubtaskColumns.SUBTASK_TITLE, desc);
        return this;
    }

    public SubtaskSelection orderBySubtaskTitle() {
        orderBy(SubtaskColumns.SUBTASK_TITLE, false);
        return this;
    }

    public SubtaskSelection taskid(String... value) {
        addEquals(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskSelection taskidNot(String... value) {
        addNotEquals(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskSelection taskidLike(String... value) {
        addLike(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskSelection taskidContains(String... value) {
        addContains(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskSelection taskidStartsWith(String... value) {
        addStartsWith(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskSelection taskidEndsWith(String... value) {
        addEndsWith(SubtaskColumns.TASKID, value);
        return this;
    }

    public SubtaskSelection orderByTaskid(boolean desc) {
        orderBy(SubtaskColumns.TASKID, desc);
        return this;
    }

    public SubtaskSelection orderByTaskid() {
        orderBy(SubtaskColumns.TASKID, false);
        return this;
    }

    public SubtaskSelection complete(String... value) {
        addEquals(SubtaskColumns.COMPLETE, value);
        return this;
    }

    public SubtaskSelection completeNot(String... value) {
        addNotEquals(SubtaskColumns.COMPLETE, value);
        return this;
    }

    public SubtaskSelection completeLike(String... value) {
        addLike(SubtaskColumns.COMPLETE, value);
        return this;
    }

    public SubtaskSelection completeContains(String... value) {
        addContains(SubtaskColumns.COMPLETE, value);
        return this;
    }

    public SubtaskSelection completeStartsWith(String... value) {
        addStartsWith(SubtaskColumns.COMPLETE, value);
        return this;
    }

    public SubtaskSelection completeEndsWith(String... value) {
        addEndsWith(SubtaskColumns.COMPLETE, value);
        return this;
    }

    public SubtaskSelection orderByComplete(boolean desc) {
        orderBy(SubtaskColumns.COMPLETE, desc);
        return this;
    }

    public SubtaskSelection orderByComplete() {
        orderBy(SubtaskColumns.COMPLETE, false);
        return this;
    }
}
