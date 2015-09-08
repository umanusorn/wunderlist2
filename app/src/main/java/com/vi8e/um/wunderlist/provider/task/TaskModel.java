package com.vi8e.um.wunderlist.provider.task;

import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

import java.util.Date;

/**
 * Data model for the {@code task} table.
 */
public interface TaskModel extends BaseModel {

    /**
     * Get the {@code task_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTaskTitle();

    /**
     * Get the {@code folder_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getFolderId();

    /**
     * Get the {@code isstar} value.
     * Can be {@code null}.
     */
    @Nullable
    String getIsstar();

    /**
     * Get the {@code iscomplete} value.
     * Can be {@code null}.
     */
    @Nullable
    String getIscomplete();

    /**
     * Get the {@code img_path} value.
     * Can be {@code null}.
     */
    @Nullable
    String getImgPath();

    /**
     * Get the {@code create_date} value.
     * Can be {@code null}.
     */
    @Nullable
    Date getCreateDate();

    /**
     * Get the {@code dueto_date} value.
     * Can be {@code null}.
     */
    @Nullable
    Date getDuetoDate();

    /**
     * Get the {@code reminder_date} value.
     * Can be {@code null}.
     */
    @Nullable
    Date getReminderDate();

    /**
     * Get the {@code note} value.
     * Can be {@code null}.
     */
    @Nullable
    String getNote();

    /**
     * Get the {@code listid} value.
     * Can be {@code null}.
     */
    @Nullable
    String getListid();
}
