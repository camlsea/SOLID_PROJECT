package com.solidskin.base.system;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.solidskin.config.Configuration;


@SuppressWarnings("serial")
public class WebAppInfoInit extends HttpServlet {
	public void init() throws ServletException {
		super.init();
		try {
			URL base = WebAppInfoInit.class.getResource("");
			File baseDir = new File(base.getPath());
			File file = baseDir.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
			String configPath = file + File.separator + getServletConfig().getInitParameter("config");
			Configuration.init(configPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
