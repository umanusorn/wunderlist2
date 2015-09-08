package com.vi8e.um.wunderlist.provider.task;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code task} table.
 */
public class TaskSelection extends AbstractSelection<TaskSelection> {
    @Override
    protected Uri baseUri() {
        return TaskColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TaskCursor} object, which is positioned before the first entry, or null.
     */
    public TaskCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TaskCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TaskCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TaskCursor} object, which is positioned before the first entry, or null.
     */
    public TaskCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TaskCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TaskCursor query(Context context) {
        return query(context, null);
    }


    public TaskSelection id(long... value) {
        addEquals ( "task." + TaskColumns._ID, toObjectArray ( value ) );
        return this;
    }

    public TaskSelection idNot(long... value) {
        addNotEquals ( "task." + TaskColumns._ID, toObjectArray ( value ) );
        return this;
    }

    public TaskSelection orderById(boolean desc) {
        orderBy("task." + TaskColumns._ID, desc);
        return this;
    }

    public TaskSelection orderById() {
        return orderById(false);
    }

    public TaskSelection taskTitle(String... value) {
        addEquals(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskSelection taskTitleNot(String... value) {
        addNotEquals(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskSelection taskTitleLike(String... value) {
        addLike(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskSelection taskTitleContains(String... value) {
        addContains(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskSelection taskTitleStartsWith(String... value) {
        addStartsWith(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskSelection taskTitleEndsWith(String... value) {
        addEndsWith(TaskColumns.TASK_TITLE, value);
        return this;
    }

    public TaskSelection orderByTaskTitle(boolean desc) {
        orderBy(TaskColumns.TASK_TITLE, desc);
        return this;
    }

    public TaskSelection orderByTaskTitle() {
        orderBy(TaskColumns.TASK_TITLE, false);
        return this;
    }

    public TaskSelection folderId(String... value) {
        addEquals(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskSelection folderIdNot(String... value) {
        addNotEquals(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskSelection folderIdLike(String... value) {
        addLike(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskSelection folderIdContains(String... value) {
        addContains(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskSelection folderIdStartsWith(String... value) {
        addStartsWith(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskSelection folderIdEndsWith(String... value) {
        addEndsWith(TaskColumns.FOLDER_ID, value);
        return this;
    }

    public TaskSelection orderByFolderId(boolean desc) {
        orderBy(TaskColumns.FOLDER_ID, desc);
        return this;
    }

    public TaskSelection orderByFolderId() {
        orderBy(TaskColumns.FOLDER_ID, false);
        return this;
    }

    public TaskSelection isstar(String... value) {
        addEquals(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskSelection isstarNot(String... value) {
        addNotEquals(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskSelection isstarLike(String... value) {
        addLike(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskSelection isstarContains(String... value) {
        addContains(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskSelection isstarStartsWith(String... value) {
        addStartsWith(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskSelection isstarEndsWith(String... value) {
        addEndsWith(TaskColumns.ISSTAR, value);
        return this;
    }

    public TaskSelection orderByIsstar(boolean desc) {
        orderBy(TaskColumns.ISSTAR, desc);
        return this;
    }

    public TaskSelection orderByIsstar() {
        orderBy(TaskColumns.ISSTAR, false);
        return this;
    }

    public TaskSelection iscomplete(String... value) {
        addEquals(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskSelection iscompleteNot(String... value) {
        addNotEquals(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskSelection iscompleteLike(String... value) {
        addLike(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskSelection iscompleteContains(String... value) {
        addContains(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskSelection iscompleteStartsWith(String... value) {
        addStartsWith(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskSelection iscompleteEndsWith(String... value) {
        addEndsWith(TaskColumns.ISCOMPLETE, value);
        return this;
    }

    public TaskSelection orderByIscomplete(boolean desc) {
        orderBy(TaskColumns.ISCOMPLETE, desc);
        return this;
    }

    public TaskSelection orderByIscomplete() {
        orderBy(TaskColumns.ISCOMPLETE, false);
        return this;
    }

    public TaskSelection imgPath(String... value) {
        addEquals(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskSelection imgPathNot(String... value) {
        addNotEquals(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskSelection imgPathLike(String... value) {
        addLike(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskSelection imgPathContains(String... value) {
        addContains(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskSelection imgPathStartsWith(String... value) {
        addStartsWith(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskSelection imgPathEndsWith(String... value) {
        addEndsWith(TaskColumns.IMG_PATH, value);
        return this;
    }

    public TaskSelection orderByImgPath(boolean desc) {
        orderBy(TaskColumns.IMG_PATH, desc);
        return this;
    }

    public TaskSelection orderByImgPath() {
        orderBy(TaskColumns.IMG_PATH, false);
        return this;
    }

    public TaskSelection createDate(String... value) {
        addEquals(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskSelection createDateNot(String... value) {
        addNotEquals(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskSelection createDateLike(String... value) {
        addLike(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskSelection createDateContains(String... value) {
        addContains(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskSelection createDateStartsWith(String... value) {
        addStartsWith(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskSelection createDateEndsWith(String... value) {
        addEndsWith(TaskColumns.CREATE_DATE, value);
        return this;
    }

    public TaskSelection orderByCreateDate(boolean desc) {
        orderBy(TaskColumns.CREATE_DATE, desc);
        return this;
    }

    public TaskSelection orderByCreateDate() {
        orderBy(TaskColumns.CREATE_DATE, false);
        return this;
    }

    public TaskSelection duetoDate(String... value) {
        addEquals(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskSelection duetoDateNot(String... value) {
        addNotEquals(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskSelection duetoDateLike(String... value) {
        addLike(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskSelection duetoDateContains(String... value) {
        addContains(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskSelection duetoDateStartsWith(String... value) {
        addStartsWith(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskSelection duetoDateEndsWith(String... value) {
        addEndsWith(TaskColumns.DUETO_DATE, value);
        return this;
    }

    public TaskSelection orderByDuetoDate(boolean desc) {
        orderBy(TaskColumns.DUETO_DATE, desc);
        return this;
    }

    public TaskSelection orderByDuetoDate() {
        orderBy(TaskColumns.DUETO_DATE, false);
        return this;
    }

    public TaskSelection reminderDate(String... value) {
        addEquals(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskSelection reminderDateNot(String... value) {
        addNotEquals(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskSelection reminderDateLike(String... value) {
        addLike(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskSelection reminderDateContains(String... value) {
        addContains(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskSelection reminderDateStartsWith(String... value) {
        addStartsWith(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskSelection reminderDateEndsWith(String... value) {
        addEndsWith(TaskColumns.REMINDER_DATE, value);
        return this;
    }

    public TaskSelection orderByReminderDate(boolean desc) {
        orderBy(TaskColumns.REMINDER_DATE, desc);
        return this;
    }

    public TaskSelection orderByReminderDate() {
        orderBy(TaskColumns.REMINDER_DATE, false);
        return this;
    }

    public TaskSelection note(String... value) {
        addEquals(TaskColumns.NOTE, value);
        return this;
    }

    public TaskSelection noteNot(String... value) {
        addNotEquals(TaskColumns.NOTE, value);
        return this;
    }

    public TaskSelection noteLike(String... value) {
        addLike(TaskColumns.NOTE, value);
        return this;
    }

    public TaskSelection noteContains(String... value) {
        addContains(TaskColumns.NOTE, value);
        return this;
    }

    public TaskSelection noteStartsWith(String... value) {
        addStartsWith(TaskColumns.NOTE, value);
        return this;
    }

    public TaskSelection noteEndsWith(String... value) {
        addEndsWith(TaskColumns.NOTE, value);
        return this;
    }

    public TaskSelection orderByNote(boolean desc) {
        orderBy(TaskColumns.NOTE, desc);
        return this;
    }

    public TaskSelection orderByNote() {
        orderBy(TaskColumns.NOTE, false);
        return this;
    }

    public TaskSelection listid(String... value) {
        addEquals(TaskColumns.LISTID, value);
        return this;
    }

    public TaskSelection listidNot(String... value) {
        addNotEquals(TaskColumns.LISTID, value);
        return this;
    }

    public TaskSelection listidLike(String... value) {
        addLike(TaskColumns.LISTID, value);
        return this;
    }

    public TaskSelection listidContains(String... value) {
        addContains(TaskColumns.LISTID, value);
        return this;
    }

    public TaskSelection listidStartsWith(String... value) {
        addStartsWith(TaskColumns.LISTID, value);
        return this;
    }

    public TaskSelection listidEndsWith(String... value) {
        addEndsWith(TaskColumns.LISTID, value);
        return this;
    }

    public TaskSelection orderByListid(boolean desc) {
        orderBy(TaskColumns.LISTID, desc);
        return this;
    }

    public TaskSelection orderByListid() {
        orderBy(TaskColumns.LISTID, false);
        return this;
    }
}
