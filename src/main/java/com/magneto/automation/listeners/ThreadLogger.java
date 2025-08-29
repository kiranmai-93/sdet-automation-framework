package com.magneto.automation.listeners;

import org.testng.*;
import java.time.*;
import java.util.concurrent.ConcurrentHashMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ThreadLogger implements ITestListener {
    private final ConcurrentHashMap<ITestResult, Instant> startTimes = new ConcurrentHashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        startTimes.put(result, Instant.now());
        Reporter.log("START ➜ " + result.getMethod().getMethodName() +
                " | Thread=" + Thread.currentThread().getId(), true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("PASS ➜ " + result.getMethod().getMethodName() +
                " | Thread=" + Thread.currentThread().getId(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("FAIL ➜ " + result.getMethod().getMethodName() +
                " | Thread=" + Thread.currentThread().getId(), true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}
