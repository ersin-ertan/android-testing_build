/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.stetho.sample;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.facebook.stetho.Stetho;

public class SampleDebugApplication extends SampleApplication {
  private static final String TAG = "SampleDebugApplication";

  @Override
  public void onCreate() {
    super.onCreate();

    long startTime = SystemClock.elapsedRealtime();
    initializeStetho(this);
    long elapsed = SystemClock.elapsedRealtime() - startTime;
    Log.i(TAG, "Stetho initialized in " + elapsed + " ms");
  }

  private void initializeStetho(final Context context) {
    // See also: Stetho.initializeWithDefaults(Context)
    Stetho.initialize(Stetho.newInitializerBuilder(context)
//        .enableDumpapp(new DumperPluginsProvider() { // do not enable dump app
//          @Override
//          public Iterable<DumperPlugin> get() {
//            return new Stetho.DefaultDumperPluginsBuilder(context)
//                .provide(new HelloWorldDumperPlugin())
//                .provide(new APODDumperPlugin(context.getContentResolver()))
//                .finish();
//          }
//        })
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        .build());
  }
}
