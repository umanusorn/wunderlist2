package com.vi8e.um.wunderlist.provider.taskcomment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code task_comment} table.
 */
public class TaskCommentSelection extends AbstractSelection<TaskCommentSelection> {
    @Override
    protected Uri baseUri() {
        return TaskCommentColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TaskCommentCursor} object, which is positioned before the first entry, or null.
     */
    public TaskCommentCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TaskCommentCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TaskCommentCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TaskCommentCursor} object, which is positioned before the first entry, or null.
     */
    public TaskCommentCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TaskCommentCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TaskCommentCursor query(Context context) {
        return query(context, null);
    }


    public TaskCommentSelection id(long... value) {
        addEquals("task_comment." + TaskCommentColumns._ID, toObjectArray(value));
        return this;
    }

    public TaskCommentSelection idNot(long... value) {
        addNotEquals("task_comment." + TaskCommentColumns._ID, toObjectArray(value));
        return this;
    }

    public TaskCommentSelection orderById(boolean desc) {
        orderBy("task_comment." + TaskCommentColumns._ID, desc);
        return this;
    }

    public TaskCommentSelection orderById() {
        return orderById(false);
    }

    public TaskCommentSelection commentTitle(String... value) {
        addEquals(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentSelection commentTitleNot(String... value) {
        addNotEquals(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentSelection commentTitleLike(String... value) {
        addLike(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentSelection commentTitleContains(String... value) {
        addContains(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentSelection commentTitleStartsWith(String... value) {
        addStartsWith(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentSelection commentTitleEndsWith(String... value) {
        addEndsWith(TaskCommentColumns.COMMENT_TITLE, value);
        return this;
    }

    public TaskCommentSelection orderByCommentTitle(boolean desc) {
        orderBy(TaskCommentColumns.COMMENT_TITLE, desc);
        return this;
    }

    public TaskCommentSelection orderByCommentTitle() {
        orderBy(TaskCommentColumns.COMMENT_TITLE, false);
        return this;
    }

    public TaskCommentSelection taskId(String... value) {
        addEquals(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentSelection taskIdNot(String... value) {
        addNotEquals(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentSelection taskIdLike(String... value) {
        addLike(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentSelection taskIdContains(String... value) {
        addContains(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentSelection taskIdStartsWith(String... value) {
        addStartsWith(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentSelection taskIdEndsWith(String... value) {
        addEndsWith(TaskCommentColumns.TASK_ID, value);
        return this;
    }

    public TaskCommentSelection orderByTaskId(boolean desc) {
        orderBy(TaskCommentColumns.TASK_ID, desc);
        return this;
    }

    public TaskCommentSelection orderByTaskId() {
        orderBy(TaskCommentColumns.TASK_ID, false);
        return this;
    }

    public TaskCommentSelection userId(String... value) {
        addEquals(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentSelection userIdNot(String... value) {
        addNotEquals(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentSelection userIdLike(String... value) {
        addLike(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentSelection userIdContains(String... value) {
        addContains(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentSelection userIdStartsWith(String... value) {
        addStartsWith(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentSelection userIdEndsWith(String... value) {
        addEndsWith(TaskCommentColumns.USER_ID, value);
        return this;
    }

    public TaskCommentSelection orderByUserId(boolean desc) {
        orderBy(TaskCommentColumns.USER_ID, desc);
        return this;
    }

    public TaskCommentSelection orderByUserId() {
        orderBy(TaskCommentColumns.USER_ID, false);
        return this;
    }

    public TaskCommentSelection datetime(String... value) {
        addEquals(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentSelection datetimeNot(String... value) {
        addNotEquals(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentSelection datetimeLike(String... value) {
        addLike(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentSelection datetimeContains(String... value) {
        addContains(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentSelection datetimeStartsWith(String... value) {
        addStartsWith(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentSelection datetimeEndsWith(String... value) {
        addEndsWith(TaskCommentColumns.DATETIME, value);
        return this;
    }

    public TaskCommentSelection orderByDatetime(boolean desc) {
        orderBy(TaskCommentColumns.DATETIME, desc);
        return this;
    }

    public TaskCommentSelection orderByDatetime() {
        orderBy(TaskCommentColumns.DATETIME, false);
        return this;
    }
}
