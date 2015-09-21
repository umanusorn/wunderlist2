package com.vi8e.um.wunderlist.provider.subtask;

import android.support.annotation.Nullable;

import com.vi8e.um.wunderlist.provider.base.BaseModel;

/**
 * Data model for the {@code subtask} table.
 */
public interface SubtaskModel extends BaseModel {

    /**
     * Get the {@code subtask_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSubtaskTitle();

    /**
     * Get the {@code taskid} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTaskid();

    /**
     * Get the {@code complete} value.
     * Can be {@code null}.
     */
    @Nullable
    String getComplete();
}
