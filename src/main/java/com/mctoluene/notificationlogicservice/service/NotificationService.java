package com.mctoluene.notificationlogicservice.service;

public interface NotificationService {
    void initiate(String payload);

    void saveResponse(String payload);

    void processCallback(String payload);
}
