package com.mctoluene.notificationlogicservice.service.impl;

import brave.baggage.BaggageField;

import org.springframework.stereotype.Service;

import com.mctoluene.notificationlogicservice.service.TracingService;

@Service
public class TracingServiceImpl implements TracingService {
    private final BaggageField trackingCodeTraceField;

    public TracingServiceImpl(BaggageField trackingCodeTraceField) {
        this.trackingCodeTraceField = trackingCodeTraceField;
    }

    @Override
    public void propagateSleuthFields(String tracingId) {
        trackingCodeTraceField.updateValue(tracingId);
    }
}
