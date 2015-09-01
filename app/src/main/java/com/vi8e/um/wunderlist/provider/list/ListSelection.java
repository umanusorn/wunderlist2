package com.vi8e.um.wunderlist.provider.list;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vi8e.um.wunderlist.provider.base.AbstractSelection;

/**
 * Selection for the {@code list} table.
 */
public class ListSelection extends AbstractSelection<ListSelection> {
    @Override
    protected Uri baseUri() {
        return ListColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ListCursor} object, which is positioned before the first entry, or null.
     */
    public ListCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ListCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ListCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ListCursor} object, which is positioned before the first entry, or null.
     */
    public ListCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ListCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ListCursor query(Context context) {
        return query(context, null);
    }


    public ListSelection id(long... value) {
        addEquals("list." + ListColumns._ID, toObjectArray(value));
        return this;
    }

    public ListSelection idNot(long... value) {
        addNotEquals("list." + ListColumns._ID, toObjectArray(value));
        return this;
    }

    public ListSelection orderById(boolean desc) {
        orderBy("list." + ListColumns._ID, desc);
        return this;
    }

    public ListSelection orderById() {
        return orderById(false);
    }

    public ListSelection listTitle(String... value) {
        addEquals(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleNot(String... value) {
        addNotEquals(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleLike(String... value) {
        addLike(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleContains(String... value) {
        addContains(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleStartsWith(String... value) {
        addStartsWith(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection listTitleEndsWith(String... value) {
        addEndsWith(ListColumns.LIST_TITLE, value);
        return this;
    }

    public ListSelection orderByListTitle(boolean desc) {
        orderBy(ListColumns.LIST_TITLE, desc);
        return this;
    }

    public ListSelection orderByListTitle() {
        orderBy(ListColumns.LIST_TITLE, false);
        return this;
    }

    public ListSelection numLateTask(String... value) {
        addEquals(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskNot(String... value) {
        addNotEquals(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskLike(String... value) {
        addLike(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskContains(String... value) {
        addContains(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskStartsWith(String... value) {
        addStartsWith(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection numLateTaskEndsWith(String... value) {
        addEndsWith(ListColumns.NUM_LATE_TASK, value);
        return this;
    }

    public ListSelection orderByNumLateTask(boolean desc) {
        orderBy(ListColumns.NUM_LATE_TASK, desc);
        return this;
    }

    public ListSelection orderByNumLateTask() {
        orderBy(ListColumns.NUM_LATE_TASK, false);
        return this;
    }

    public ListSelection numCurrentTask(String... value) {
        addEquals(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskNot(String... value) {
        addNotEquals(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskLike(String... value) {
        addLike(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskContains(String... value) {
        addContains(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskStartsWith(String... value) {
        addStartsWith(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection numCurrentTaskEndsWith(String... value) {
        addEndsWith(ListColumns.NUM_CURRENT_TASK, value);
        return this;
    }

    public ListSelection orderByNumCurrentTask(boolean desc) {
        orderBy(ListColumns.NUM_CURRENT_TASK, desc);
        return this;
    }

    public ListSelection orderByNumCurrentTask() {
        orderBy(ListColumns.NUM_CURRENT_TASK, false);
        return this;
    }

    public ListSelection folderId(String... value) {
        addEquals(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdNot(String... value) {
        addNotEquals(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdLike(String... value) {
        addLike(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdContains(String... value) {
        addContains(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdStartsWith(String... value) {
        addStartsWith(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection folderIdEndsWith(String... value) {
        addEndsWith(ListColumns.FOLDER_ID, value);
        return this;
    }

    public ListSelection orderByFolderId(boolean desc) {
        orderBy(ListColumns.FOLDER_ID, desc);
        return this;
    }

    public ListSelection orderByFolderId() {
        orderBy(ListColumns.FOLDER_ID, false);
        return this;
    }

    public ListSelection ispinned(String... value) {
        addEquals(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedNot(String... value) {
        addNotEquals(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedLike(String... value) {
        addLike(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedContains(String... value) {
        addContains(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedStartsWith(String... value) {
        addStartsWith(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection ispinnedEndsWith(String... value) {
        addEndsWith(ListColumns.ISPINNED, value);
        return this;
    }

    public ListSelection orderByIspinned(boolean desc) {
        orderBy(ListColumns.ISPINNED, desc);
        return this;
    }

    public ListSelection orderByIspinned() {
        orderBy(ListColumns.ISPINNED, false);
        return this;
    }

    public ListSelection isdisturb(String... value) {
        addEquals(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbNot(String... value) {
        addNotEquals(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbLike(String... value) {
        addLike(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbContains(String... value) {
        addContains(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbStartsWith(String... value) {
        addStartsWith(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection isdisturbEndsWith(String... value) {
        addEndsWith(ListColumns.ISDISTURB, value);
        return this;
    }

    public ListSelection orderByIsdisturb(boolean desc) {
        orderBy(ListColumns.ISDISTURB, desc);
        return this;
    }

    public ListSelection orderByIsdisturb() {
        orderBy(ListColumns.ISDISTURB, false);
        return this;
    }

    public ListSelection imgPath(String... value) {
        addEquals(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathNot(String... value) {
        addNotEquals(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathLike(String... value) {
        addLike(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathContains(String... value) {
        addContains(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathStartsWith(String... value) {
        addStartsWith(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection imgPathEndsWith(String... value) {
        addEndsWith(ListColumns.IMG_PATH, value);
        return this;
    }

    public ListSelection orderByImgPath(boolean desc) {
        orderBy(ListColumns.IMG_PATH, desc);
        return this;
    }

    public ListSelection orderByImgPath() {
        orderBy(ListColumns.IMG_PATH, false);
        return this;
    }

    public ListSelection type(String... value) {
        addEquals(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeNot(String... value) {
        addNotEquals(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeLike(String... value) {
        addLike(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeContains(String... value) {
        addContains(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeStartsWith(String... value) {
        addStartsWith(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection typeEndsWith(String... value) {
        addEndsWith(ListColumns.TYPE, value);
        return this;
    }

    public ListSelection orderByType(boolean desc) {
        orderBy(ListColumns.TYPE, desc);
        return this;
    }

    public ListSelection orderByType() {
        orderBy(ListColumns.TYPE, false);
        return this;
    }
}
