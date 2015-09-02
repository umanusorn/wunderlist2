package com.vi8e.um.wunderlist.provider.taskfile;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code task_file} table.
 */
public class TaskFileSelection extends AbstractSelection<TaskFileSelection> {
    @Override
    protected Uri baseUri() {
        return TaskFileColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TaskFileCursor} object, which is positioned before the first entry, or null.
     */
    public TaskFileCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TaskFileCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TaskFileCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TaskFileCursor} object, which is positioned before the first entry, or null.
     */
    public TaskFileCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TaskFileCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TaskFileCursor query(Context context) {
        return query(context, null);
    }


    public TaskFileSelection id(long... value) {
        addEquals("task_file." + TaskFileColumns._ID, toObjectArray(value));
        return this;
    }

    public TaskFileSelection idNot(long... value) {
        addNotEquals("task_file." + TaskFileColumns._ID, toObjectArray(value));
        return this;
    }

    public TaskFileSelection orderById(boolean desc) {
        orderBy("task_file." + TaskFileColumns._ID, desc);
        return this;
    }

    public TaskFileSelection orderById() {
        return orderById(false);
    }

    public TaskFileSelection title(String... value) {
        addEquals(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileSelection titleNot(String... value) {
        addNotEquals(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileSelection titleLike(String... value) {
        addLike(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileSelection titleContains(String... value) {
        addContains(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileSelection titleStartsWith(String... value) {
        addStartsWith(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileSelection titleEndsWith(String... value) {
        addEndsWith(TaskFileColumns.TITLE, value);
        return this;
    }

    public TaskFileSelection orderByTitle(boolean desc) {
        orderBy(TaskFileColumns.TITLE, desc);
        return this;
    }

    public TaskFileSelection orderByTitle() {
        orderBy(TaskFileColumns.TITLE, false);
        return this;
    }

    public TaskFileSelection taskid(String... value) {
        addEquals(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileSelection taskidNot(String... value) {
        addNotEquals(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileSelection taskidLike(String... value) {
        addLike(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileSelection taskidContains(String... value) {
        addContains(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileSelection taskidStartsWith(String... value) {
        addStartsWith(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileSelection taskidEndsWith(String... value) {
        addEndsWith(TaskFileColumns.TASKID, value);
        return this;
    }

    public TaskFileSelection orderByTaskid(boolean desc) {
        orderBy(TaskFileColumns.TASKID, desc);
        return this;
    }

    public TaskFileSelection orderByTaskid() {
        orderBy(TaskFileColumns.TASKID, false);
        return this;
    }

    public TaskFileSelection type(String... value) {
        addEquals(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileSelection typeNot(String... value) {
        addNotEquals(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileSelection typeLike(String... value) {
        addLike(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileSelection typeContains(String... value) {
        addContains(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileSelection typeStartsWith(String... value) {
        addStartsWith(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileSelection typeEndsWith(String... value) {
        addEndsWith(TaskFileColumns.TYPE, value);
        return this;
    }

    public TaskFileSelection orderByType(boolean desc) {
        orderBy(TaskFileColumns.TYPE, desc);
        return this;
    }

    public TaskFileSelection orderByType() {
        orderBy(TaskFileColumns.TYPE, false);
        return this;
    }

    public TaskFileSelection url(String... value) {
        addEquals(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileSelection urlNot(String... value) {
        addNotEquals(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileSelection urlLike(String... value) {
        addLike(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileSelection urlContains(String... value) {
        addContains(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileSelection urlStartsWith(String... value) {
        addStartsWith(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileSelection urlEndsWith(String... value) {
        addEndsWith(TaskFileColumns.URL, value);
        return this;
    }

    public TaskFileSelection orderByUrl(boolean desc) {
        orderBy(TaskFileColumns.URL, desc);
        return this;
    }

    public TaskFileSelection orderByUrl() {
        orderBy(TaskFileColumns.URL, false);
        return this;
    }
}
