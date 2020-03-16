package com.solidskin.config;

import java.util.Properties;

public interface Config {

	public String get(String key);

	public boolean getBoolean(String key);

	public boolean getBoolean(String key, boolean defaultValue);

	public int getInt(String key);

	public int getInt(String key, int defaultValue);

	public Properties getProperties();

	public String getString(String key);

	public String getString(String key, String defaultValue);

	long lastModified();
}
