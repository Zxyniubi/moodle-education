package com.moodleeducation.commoncore.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static final ExecutorService callbackExecutor = Executors.newFixedThreadPool(50);
}
