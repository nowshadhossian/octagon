package com.kids.crm.service.metric;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInMetric {
    private final MeterRegistry meterRegistry;

    @Autowired
    public SignInMetric(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void record() {
        meterRegistry.counter("signin.counter", "sign-tag", "nothing").increment();
    }
}
