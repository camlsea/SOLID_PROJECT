package com.solidskin.config;

@SuppressWarnings("serial")
public class ConfigurationException extends Exception {
	
	public ConfigurationException() {
        super();
    }
	
	/**
	 * @param s
	 */
	public ConfigurationException(String s) {
        super(s);
    }
	
}
