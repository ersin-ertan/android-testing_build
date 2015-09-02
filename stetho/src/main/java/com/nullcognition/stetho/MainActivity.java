package com.nullcognition.stetho;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends Activity{

	OkHttpClient httpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		httpClient = new OkHttpClient();
	}

	private void httpRequest(){
		// Create an instance of OkHttpClient

// Add Stetho interceptor
		httpClient.networkInterceptors().add(new StethoInterceptor());

		Request request = new Request.Builder()
				.url("https://publicobject.com/helloworld.txt")
				.build();

		Response response = null;
		try{
			response = httpClient.newCall(request).execute();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		if(!response.isSuccessful()){
			try{
				throw new IOException("Unexpected code " + response);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

		Headers responseHeaders = response.headers();
		for(int i = 0; i < responseHeaders.size(); i++){
			System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
		}

		try{
			Toast.makeText(MainActivity.this, "response: " + response.body().string(), Toast.LENGTH_SHORT).show();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_settings){

			new Runnable(){
				@Override public void run(){
					httpRequest();
				}
			}.run();


			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
