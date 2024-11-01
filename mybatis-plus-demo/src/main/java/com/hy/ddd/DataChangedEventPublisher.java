package com.hy.ddd;

/**
 * Description: 事件发布接口
 * Author: yhong
 * Date: 2024/10/31
 */
public interface DataChangedEventPublisher<T> {
    default void onCreated(final T data) {
        ApiLogEvent event = new ApiLogEvent("CREATE", data.toString(), "API_PATH");
        publish(event);
    }

    default void onUpdated(final T data, final T before) {
        ApiLogEvent event = new ApiLogEvent("UPDATE", data.toString(), "API_PATH");
        publish(event);
    }

    default void onDeleted(final T data) {
        ApiLogEvent event = new ApiLogEvent("DELETE", data.toString(), "API_PATH");
        publish(event);
    }

    void publish(ApiLogEvent apiLogEvent);
}
