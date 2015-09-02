package com.vi8e.um.wunderlist.provider.taskcomment;

import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

/**
 * Data model for the {@code task_comment} table.
 */
public interface TaskCommentModel extends BaseModel {

    /**
     * Get the {@code comment_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getCommentTitle();

    /**
     * Get the {@code task_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTaskId();

    /**
     * Get the {@code user_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getUserId();

    /**
     * Get the {@code datetime} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDatetime();
}
