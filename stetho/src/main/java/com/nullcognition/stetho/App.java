package com.nullcognition.stetho;// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.


import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application{

	@Override public void onCreate(){
		super.onCreate();

		Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

		// Enable Chrome DevTools
		initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)
		);

		// Enable command line interface
		initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this) // was context
		);

		Stetho.Initializer initializer = initializerBuilder.build();
		Stetho.initialize(initializer);
	}
}
