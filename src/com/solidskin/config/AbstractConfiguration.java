package com.solidskin.config;

import java.util.Properties;

import com.solidskin.util.LangUtil;

public abstract class AbstractConfiguration implements Config {

	protected static Object lock = new Object();

	protected static Properties props = null;

	protected static long lastModified = 0;

	public String get(String key) {
		return getString(key);
	}

	public boolean getBoolean(String key) {
		boolean value = false;
		try {
			value = (new Boolean(getRefProperty(key))).booleanValue();
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal Boolean Key : " + key);
		}
		return value;
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		boolean val = defaultValue;
		try {
			val = (new Boolean(getRefProperty(key))).booleanValue();
		} catch (Exception ign) {
		}
		return val;
	}

	public int getInt(String key) {
		int value = -1;
		try {
			value = Integer.parseInt(getRefProperty(key));
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal Integer Key : " + key);
		}
		return value;
	}

	public int getInt(String key, int defaultValue) {
		int val = defaultValue;
		try {
			val = Integer.parseInt(getRefProperty(key));
		} catch (Exception ign) {
		}
		return val;
	}

	public Properties getProperties() {
		return props;
	}

	public String getString(String key) {
		String value = getRefProperty(key);
		if (value == null) {
			System.out.print("[ERROR] Illegal String Key : " + key);
			throw new IllegalArgumentException("Illegal String Key : " + key);
		}
		return LangUtil.toKor(value);
	}

	public String getString(String key, String defaultValue) {
		String val = getRefProperty(key);
		if (val == null || val.length() == 0)
			val = defaultValue;
		return (val == null) ? null : LangUtil.toKor(val);
	}

	public long lastModified() {
		return lastModified;
	}

	public String getRefProperty(String key) {
		return parseRefProperty(props.getProperty(key));
	}

	public String parseRefProperty(String s) {
		if (s == null || s.length() == 0)
			return s;

		StringBuffer sbuf = new StringBuffer();

		// rear and front index
		int rIdx = 0;
		int fIdx = s.indexOf("${");

		if (fIdx < 0)
			return s;

		while (fIdx >= 0) {
			sbuf.append(s.substring(rIdx, fIdx));

			rIdx = fIdx;
			fIdx = s.indexOf('}', rIdx + 2);

			if (fIdx < 0) {
				sbuf.append(s.substring(rIdx));
				break;
			}

			rIdx += 2;
			String ref = s.substring(rIdx, fIdx);

			if (ref.length() > 0) {
				String deeper = props.getProperty(ref);
				if (deeper != null && deeper.length() > 0)
					sbuf.append(parseRefProperty(deeper));
			}

			rIdx = fIdx + 1;
			if (rIdx >= s.length())
				break;

			fIdx = s.indexOf("${", rIdx);
		}

		if (rIdx < s.length())
			sbuf.append(s.substring(rIdx));

		return sbuf.toString();
	}
}
