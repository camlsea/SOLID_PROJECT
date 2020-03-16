package com.solidskin.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;


public final class Configuration extends AbstractConfiguration {
	
	public static Configuration conf;

	private TimeZone timeZone = null;

	private Locale locale = null;

	/** �ʱ�ȭ ���� */
	private static boolean init = false;

	/** �������� ���? */
	private static String configFilePath;

	/**
	 * @param configPath
	 *            �������� ���?
	 */
	public synchronized static void init(String configPath) {
		if (init) {
			throw new RuntimeException("�̹� ���� �ʱ�ȭ�� �Ǿ� �ֽ��ϴ�.");
		}
		configFilePath = configPath;

		conf = new Configuration();
		
		init = true;
	}

	/**
	 * ������
	 */
	protected Configuration() throws RuntimeException {
		try {
			initialize();
		} catch (ConfigurationException ce) {
			System.err.println("CAN'T INITIALIZE GLOBAL CONFIGURATION.");
			ce.printStackTrace(System.err);
			throw new RuntimeException(ce);
		}
	}

	/**
	 * ������Ƽ ��������
	 */
	private synchronized void initialize() throws ConfigurationException {
		// �ʱ� ��ü�� �����? ���� ���� ���? ��ü ����
		if (props == null) {
			String path = configFilePath;
			if (path == null) {
				throw new NullPointerException(
						"setConfigFilePath(String)�� �̿��Ͽ� ���� ��θ�? �����ϼ��� ");
			}
			File f = new File(path);
			if (!f.exists() || !f.isFile() || !f.canRead()) {
				String mesg = this.getClass().getName()
						+ " - Can't open jdf configuration file: " + path;
				throw new ConfigurationException(mesg);
			}

			Properties p = null;
			try {
				p = new Properties();
				InputStream i = new BufferedInputStream(new FileInputStream(f));
				p.load(i);
				i.close();
				AbstractConfiguration.lastModified = f.lastModified();
			} catch (Exception e) {
				AbstractConfiguration.lastModified = 0;
				String mesg = this.getClass().getName()
						+ " - Can't load configuration file. (Exception: "
						+ e.getClass().getName() + ", Message: "
						+ e.getMessage();
				throw new ConfigurationException(mesg);
			}

			props = p;
		} // end if
	}

	/**
	 * ȯ�漳�� ������ ������ �����ϰ� �ð��� ������ �������� �޷°�ü�� �����Ѵ�.
	 * 
	 * @return ȯ�漳�� ���������� �־����� �����ϰ� �ð��� ������ �������� �ϴ� �޷� ��ü
	 * @see #getDefaultLocale()
	 * @see #getDefaultTimeZone()
	 */
	public Calendar createDefaultCalendar() {
		return Calendar.getInstance(getDefaultTimeZone(), getDefaultLocale());
	}

	/**
	 * ȯ�漳�� ������ ������ �ð��� �ڵ带 �������� �ð��� ��ü�� ��´�?. ���Ǵ� �ð��� �ڵ��? JRE 1.4 ȯ�濡�� �����Ǵ� ǥ��
	 * �ڹ� �ڵ尪�̴�.
	 * 
	 * @return ȯ�漳�� ������ �������� �ϴ� �ð���(TimeZone) ��ü
	 */
	public TimeZone getDefaultTimeZone() {
		if (timeZone == null) {
			String tzName = super.getString("TIMEZONE", "Asia/Seoul");
			timeZone = TimeZone.getTimeZone(tzName);
		}
		return timeZone;
	}

	/**
	 * ȯ�漳�� ������ ������ ���� �����ڵ带 �������� ������ ��ü�� ��´�?.
	 * 
	 * <p>
	 * ȯ�漳�� �׸��� ����ڵ�� "LANGUAGE" �Ӽ��� �����Ǵ� ISO-639 ������ �ؼ��ϴ� ���� 2�ڸ� �ڵ尪�̸�, �����ڵ��?
	 * "COUNTRY" �Ӽ��� �����Ǵ� ISO-3166 ������ �ؼ��ϴ� ���� 2�ڸ� �ڵ尪�̴�.
	 * </p>
	 * <p>
	 * �⺻ ������ ���? �ڵ��? "ko", ���� �ڵ��? "KR"�̴�.
	 * </p>
	 * 
	 * @return ȯ�漳�� ������ �������� �ϴ� ������ ��ü
	 */
	public Locale getDefaultLocale() {
		if (locale == null) {
			String lcLang = super.getString("LANGUAGE", "ko");
			String lcCountry = super.getString("COUNTRY", "KR");
			locale = new Locale(lcLang, lcCountry);
		}
		return locale;
	}
}
