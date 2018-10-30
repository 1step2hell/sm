package com.step2hell.newsmth.service;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;
import com.tencent.bugly.crashreport.CrashReport;

public class LeakUploadService extends DisplayLeakService {

    @Override
    protected void afterDefaultHandling(@NonNull HeapDump heapDump, @NonNull AnalysisResult result, @NonNull String leakInfo) {
        Log.e("Bob","CrashReport afterDefaultHandling===========");
        if (!result.leakFound || result.excludedLeak) {
            return;
        }
        if (result.leakFound) {
            CrashReport.postCatchedException(new Throwable("CrashReport LeakCanary:"+leakInfo));
            Log.e("Bob","CrashReport AnalysisResult:"+result.toString());
            Log.e("Bob","CrashReport leakInfo:"+leakInfo);
//            uploadLeakToServer(result, leakInfo);
        }
    }

    private void uploadLeakToServer(AnalysisResult result, String leakInfo) {
        // TODO Upload result to server
    }

    class LeakThrowable extends Throwable {
        // TODO
    }

}
