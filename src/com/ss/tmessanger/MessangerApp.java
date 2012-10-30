package com.ss.tmessanger;

import com.memetix.mst.language.Language;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class MessangerApp extends Application implements OnSharedPreferenceChangeListener
{
	static final String TAG = "TMessangerApp";
	private Language language;
	
	SharedPreferences prefs;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
		
		
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) 
	{
		// TODO Auto-generated method stub
		
	}
	
	
	public Language getLanguage()
	{
		return language;
	}
	public void setLanguage(Language language)
	{
		this.language = language;
	}

}
