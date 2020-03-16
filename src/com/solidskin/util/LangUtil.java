package com.solidskin.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * @class LangUtil.java
 * @author Kyo
 * @since 2014. 9. 11.
 * @description : ��ҿ� ���� ���Ǹ� ���ڿ� ��ȯ ���� ���� Ŭ����
 *
 * Copyright (C) by Kyo-hyun Jeong All right reserved.
 */
public class LangUtil {

	private static final String EMPTY_STRING = "";

	private static BitSet dontNeedEncoding;

	static {
		dontNeedEncoding = new BitSet(256);
		for (int i = 97; i <= 122; i++) {
			dontNeedEncoding.set(i);
		}
		for (int j = 65; j <= 90; j++) {
			dontNeedEncoding.set(j);
		}
		for (int k = 48; k <= 57; k++) {
			dontNeedEncoding.set(k);
		}
		dontNeedEncoding.set(32);
		dontNeedEncoding.set(45);
		dontNeedEncoding.set(95);
		dontNeedEncoding.set(46);
		dontNeedEncoding.set(42);
	}

	/**
	 * SQL ������ ��ġ ó�� �ڵ�
	 * 
	 * @param str
	 *            ���ԵǴ� ���ڿ�
	 * @return ������ ó�� �� ��ȯ ���ڿ�
	 */
	public static String replaceInjection(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			String comp = str.substring(i, i + 1);
			if ("<".compareTo(comp) == 0) {
				buffer.append("&lt;");
			} else if (">".compareTo(comp) == 0) {
				buffer.append("&gt;");
			} else if ("'".compareTo(comp) == 0) {
				buffer.append("&#39;");
			} else if (";".compareTo(comp) == 0) {
				buffer.append("&#59;");
			} else if ("&".compareTo(comp) == 0) {
				buffer.append("&amp;");
			} else if ("script".compareTo(comp) == 0) {
				buffer.append("noscript");
			} else if ("SCRIPT".compareTo(comp) == 0) {
				buffer.append("NOSCRIPT");
			} else {
				buffer.append(comp);
			}
		}
		return buffer.toString();
	}

	/**
	 * ���� ���ڿ��� split �� �� ArrayList �������� ������.
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param separator
	 *            ������
	 * @return �����ڷ� ����� ArrayList ������ ����
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList splitCustom(String str, String separator) {
		String arrayItem = null;
		ArrayList arrayList = new ArrayList();
		StringTokenizer token = new StringTokenizer(str, separator);
		while (token.hasMoreTokens()) {
			arrayItem = token.nextToken();
			arrayList.add(arrayItem);
		}
		return arrayList;
	}

	/**
	 * 1234561234567 ������ �ֹι�ȣ�� 123456-1234567 �������� ����(���� ��ȣ�� �ش� ��)
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ���� ���ڿ� ����
	 */
	public static String fmtJumin(String str) {
		if (str.length() == 13) {
			StringBuffer sb = new StringBuffer();
			sb.append(str.substring(0, 6));
			sb.append("-");
			sb.append(str.substring(6));
			return sb.toString();
		} else if (str.length() == 10) {
			StringBuffer sb = new StringBuffer();
			sb.append(str.substring(0, 3));
			sb.append("-");
			sb.append(str.substring(3, 5));
			sb.append("-");
			sb.append(str.substring(5));
			return sb.toString();
		} else {
			return str;
		}
	}

	/**
	 * 123456 ������ �����ȣ�� 123-456 �������� ����
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ���� ���ڿ� ����
	 */
	public static String fmtPost(String str) {
		if (str == null) {
			return EMPTY_STRING;
		} else if (str.trim().length() == 6) {
			return str.substring(0, 3) + "-" + str.substring(3, 6);
		} else {
			return str;
		}
	}

	/**
	 * ��Ʈ�� Null Ȯ��(Null �켱) Null �̸� True, Not Null �̸� False
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ���ڿ��� ��,����
	 * 
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if ((str.trim()).equals(EMPTY_STRING)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ������ �迭�� �޸�(,)�����ڸ� �ٿ� ���ڿ��� ��ȯ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String fmtArrayToStringComma(String[] str) {
		String rtn = "";
		if (str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				rtn = rtn + str[i];
				if (i + 1 != str.length) {
					rtn += ", ";
				}
			}
		}
		return rtn;
	}

	/**
	 * ������ ���ڸ� 999,999,999 �Ǵ� 999,999,999.99 �������� ��ȯ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String fmtComma(String str) {
		if (str == null) {
			return EMPTY_STRING;
		}

		Double num;

		try {
			num = new Double(str);
		} catch (NumberFormatException nfe) {
			return "invalid argument";
		} catch (NullPointerException npe) {
			return "err-commaFormat(null)";
		}

		DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,###.##");

		return df.format(num);
	}

	/**
	 * ��Ʈ�� ������ ǥ�� (...) : ����Ʈ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param limit
	 *            ǥ���� ���ڿ� ũ�� : ����Ʈ
	 * @return ��ȯ ���ڿ�
	 */
	public static String ellipsis(String str, int limit) {
		String rtn;
		rtn = getByteCut(str, limit - 3);
		if (str == null || str.equals(rtn)) {
			return rtn;
		} else {
			return rtn + "...";
		}
	}

	/**
	 * ����Ʈ ���� ��Ʈ�� ���� (�ѱ� ���ڵ��� ���� �ȵ� ���� ����)
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param limit
	 *            ���� ����Ʈ
	 * @return ��ȯ ���ڿ�
	 */
	public static String getByteCut(String str, int limit) {

		if (str == null) {
			return str;
		}

		int len = str.length();
		int cnt = 0, index = 0;

		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256) {
				cnt++;
			} else {
				cnt += 2;
			}
		}

		if (index < len && limit >= cnt) {
			str = str.substring(0, index);
		} else if (index < len && limit < cnt) {
			str = str.substring(0, index - 1);
		}
		return str;
	}

	/**
	 * ���� ���ڿ� �� from �� ���ڸ� to ���ڷ� ġȯ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param from
	 *            ���� ��� ����
	 * @param to
	 *            ���� �� ����
	 * @return ��ȯ ���ڿ�
	 */
	public static String replace(String str, String from, String to) {
		String strCheck = new String(str);
		StringBuffer strBuf = new StringBuffer();
		while (strCheck.length() != 0) {
			int begin = strCheck.indexOf(from);
			if (begin == -1) {
				strBuf.append(strCheck);
				break;
			} else {
				int end = begin + from.length();
				strBuf.append(strCheck.substring(0, begin));
				strBuf.append(to);
				strCheck = strCheck.substring(end);
			}
		}
		return new String(strBuf);
	}

	/**
	 * SQL Left Like Type ('%str')
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String getSqlLLike(String str) {
		str = replace(str, "'", "&#39;");
		str = "%" + str;
		str = "'" + str + "'";
		return str;
	}

	/**
	 * SQL Right Like Type ('str%')
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String getSqlRLike(String str) {
		str = replace(str, "'", "&#39;");
		str = str + "%";
		str = "'" + str + "'";
		return str;
	}

	/**
	 * SQL Like Type ('%str%')
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String getSqlLike(String str) {
		str = replace(str, "'", "&#39;");
		str = "%" + str + "%";
		str = "'" + str + "'";
		return str;
	}

	/**
	 * ���ڿ� �� ���� "'" ���� ('str')
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String getSqlChar(String str) {
		str = nvl(str, EMPTY_STRING);
		str = replace(str, "'", "&#39;");
		str = str.trim();
		return new String("'" + str + "'");
	}

	/**
	 * ���� ���ڿ��� null �̸� ���� ���ڿ��� ��ȯ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param val
	 *            ��ȯ �� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String nvl(String str, String val) {
		if (str == null || str.equals("null") || str.equals(EMPTY_STRING)) {
			return val;
		} else {
			return str;
		}
	}

	/**
	 * ���� ���ڿ��� null �̸� val ���ڿ���, null �ƴϸ� nval ���ڿ��� ��ȯ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param val
	 *            null �϶� ��ȯ �� ����
	 * @param nval
	 *            not null �϶� ��ȯ �� ����
	 * @return
	 */
	public static String nvl(String str, String val, String nval) {
		if (str == null || str.equalsIgnoreCase("null")
				|| str.equals(EMPTY_STRING)) {
			return val;
		} else {
			return nval;
		}
	}

	/**
	 * ���� ���ڿ��� ��Ī ���ڿ��� ������� rtn_yes��, �ƴϸ� rtn_no�� ��ȯ
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @param mat
	 *            �� ��� ���ڿ�
	 * @param rtn_yes
	 *            ���� ���� ��ȯ ���ڿ�
	 * @param rtn_no
	 *            ���� �񼺸� ��ȯ ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String nvl(String str, String mat, String rtn_yes,
			String rtn_no) {
		str = LangUtil.nvl(str, EMPTY_STRING);
		if (str.equals(mat)) {
			return rtn_yes;
		} else {
			return rtn_no;
		}
	}

	/**
	 * ���� ���ڰ� ��Ī ���ڿ� ������� rtn_yes��, �ƴϸ� rtn_no�� ��ȯ
	 * 
	 * @param num
	 *            ���� ����
	 * @param mat
	 *            �� ��� ����
	 * @param rtn_yes
	 *            ���� ���� ��ȯ ���ڿ�
	 * @param rtn_no
	 *            ���� �񼺸� ��ȯ ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String nvl(int num, int mat, String rtn_yes, String rtn_no) {
		if (num == mat) {
			return rtn_yes;
		} else {
			return rtn_no;
		}
	}

	/**
	 * ���ڿ� ��ȣȭ ó��
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȣȭ ó���� ���ڿ�
	 */
	public static String encode(String str) {
		byte i_byte = 10;
		StringBuffer stringbuffer = new StringBuffer(str.length());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				i_byte);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				byteArrayOutputStream);

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (dontNeedEncoding.get(c)) {
				stringbuffer.append(Integer.toHexString(c));
				continue;
			}
			try {
				outputStreamWriter.write(c);
				outputStreamWriter.flush();
			} catch (IOException ex) {
				byteArrayOutputStream.reset();
				continue;
			}
			byte o_byte[] = byteArrayOutputStream.toByteArray();
			for (int j = 0; j < o_byte.length; j++) {
				char c1 = Character.forDigit(o_byte[j] >> 4 & 0xf, 16);
				// �����̸� ' '�� ���� �빮�� �����.
				if (Character.isLetter(c1))
					c1 -= ' ';
				stringbuffer.append(c1);
				c1 = Character.forDigit(o_byte[j] & 0xf, 16);
				if (Character.isLetter(c1))
					c1 -= ' ';
				stringbuffer.append(c1);
			}

			byteArrayOutputStream.reset();
		}

		return stringbuffer.toString();
	}

	/**
	 * ���ڿ� ��ȣȭ Ǯ��
	 * 
	 * @param str
	 *            Encode �� ��ȣȭ �� ���ڿ�
	 * @return ��ȯ�� ���� ���ڿ�
	 */
	public static String decode(String str) {
		byte b[] = null;

		try {
			StringBuffer stringbuffer = new StringBuffer();

			for (int i = 0; i < str.length(); i += 2)
				stringbuffer.append((char) Integer.parseInt(
						str.substring(i, i + 2), 16));

			String s = stringbuffer.toString();
			b = s.getBytes("8859_1");
		} catch (Exception exception) {
		}
		return new String(b);
	}

	/**
	 * ���� �ɸ��� �� ���� ���ڿ��� �ѱ� ���ڵ�
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String toKor(String str) {
		try {
			if (str == null) {
				return EMPTY_STRING;
			}
			byte[] b = str.getBytes("8859_1");
			for (int i = 0; i < b.length; i++) {
				if (b[i] < -1) {
					return convertKor(str);
				}
			}
			return str;
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * toKor �޼ҵ��� �ѱ� ���ڵ� ���� �κ�
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	private static String convertKor(String str) {
		try {
			if (str == null) {
				return EMPTY_STRING;
			}
			return new String(str.getBytes("8859_1"), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * �ѱ� ĳ���� �� ���� ���ڿ��� ���� ���ڵ�
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String toEng(String str) {
		try {
			if (str == null) {
				return EMPTY_STRING;
			}
			return new String(str.getBytes("KSC5601"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * ���� �ɸ��� �� ���� ���ڿ��� UTF8 ���ڵ�
	 * 
	 * @param str
	 *            ���� ���ڿ�
	 * @return ��ȯ ���ڿ�
	 */
	public static String toUTF8(String str) {
		try {
			if (str == null) {
				return EMPTY_STRING;
			}
			return new String(str.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	/**
	 * �� ���۽ÿ� ������ ����
	 * 
	 * @param src
	 * @return
	 */
	public static String toForm(String src) {
		String strBuffer = src;
		if (src == null) {
			return EMPTY_STRING;
		}
		strBuffer = replace(strBuffer, "&", "&amp;");
		strBuffer = replace(strBuffer, "<", "&lt;");
		strBuffer = replace(strBuffer, ">", "&gt;");
		strBuffer = replace(strBuffer, "\"", "&quot;");
		strBuffer = replace(strBuffer, "\'", "&#039;");
		strBuffer = replace(strBuffer, "&amp;amp;#", "&amp;#");
		return strBuffer;
	}

	/**
	 * �ؽ�Ʈ �������� ����
	 * 
	 * @param src
	 * @return
	 */
	public static String toText(String src) {
		String strBuffer = nvl(src, "");
		strBuffer = replace(strBuffer, "&", "&amp;");
		strBuffer = replace(strBuffer, "<", "&lt;");
		strBuffer = replace(strBuffer, ">", "&gt;");
		strBuffer = replace(strBuffer, "\"", "&quot;");
		strBuffer = replace(strBuffer, "\'", "&#039;");
		strBuffer = replace(strBuffer, " ", "&nbsp;");
		strBuffer = replace(strBuffer, "\n", "<br/>");
		strBuffer = replace(strBuffer, "&amp;#", "&#");
		return strBuffer;
	}

	/**
	 * @param msg
	 *            ���� �޼���.
	 * @return ���� �޼��� �ڹ� ��ũ��Ʈ�� ����� ���ڿ�
	 */
	public static String getErrMessage(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("  alert(\"" + msg + "\");\n");
		sb.append("  history.go(-1);\n");
		sb.append("</script>\n");
		return sb.toString();
	}

	/**
	 * @param msg
	 *            ���� �޼���.
	 * @return ���� �޼��� �ڹ� ��ũ��Ʈ�� ����� ���ڿ�
	 */
	public static String getErrMessage(String msg, String url) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("  alert(\"" + msg + "\");\n");
		sb.append("  location.href=\"" + url + "\";\n");
		sb.append("</script>\n");
		return sb.toString();
	}

	public static String getMessageOnly(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("  alert(\"" + msg + "\");\n");
		sb.append("</script>\n");
		return sb.toString();
	}
	
	/**
	 * @param msg
	 *            �޼���.
	 * @return �޼��� �ڹ� ��ũ��Ʈ�� ����� ���ڿ�
	 */
	public static String getMessage(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("  alert(\"" + msg + "\");\n");
		sb.append("  history.go(-1);\n");
		sb.append("</script>\n");
		return sb.toString();
	}

	/**
	 * @param msg
	 *            �޼���.
	 * @return �޼��� �ڹ� ��ũ��Ʈ�� ����� ���ڿ�
	 */
	public static String getMessage(String msg, String url) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("  alert(\"" + msg + "\");\n");
		sb.append("  location.href=\"" + url + "\";\n");
		sb.append("</script>\n");
		return sb.toString();
	}
	
	/**
	 *	�α��� 
	 */
	public static String getMessage(String mId, String name, String authLevel, String brandMgrAuthYn, String boardMgrAuthYn, String siteMgrAuthYn) {
		StringBuffer sb = new StringBuffer();
		sb.append("<form name=\"aForm\" action=\"./board/noticeList.do\" method=\"POST\">\n");
		sb.append("<input type=\"hidden\" name=\"uid\" value=\""+mId+"\">\n");
		sb.append("<input type=\"hidden\" name=\"uname\" value=\""+name+"\">\n");
		sb.append("<input type=\"hidden\" name=\"authLevel\" value=\""+authLevel+"\">\n");
		sb.append("<input type=\"hidden\" name=\"brandMgrAuthYn\" value=\""+brandMgrAuthYn+"\">\n");
		sb.append("<input type=\"hidden\" name=\"boardMgrAuthYn\" value=\""+boardMgrAuthYn+"\">\n");
		sb.append("<input type=\"hidden\" name=\"siteMgrAuthYn\" value=\""+siteMgrAuthYn+"\">\n");
		sb.append("</form>\n");
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("document.aForm.submit();\n");
		sb.append("</script>\n");
		return sb.toString();
	}
	
	/**
	 * �׼� URL ���(�������� �����ӿ�ũ ��)
	 * @param str
	 * @return
	 */
	public static String getActionUrl(String str) {
		String rtn = "";
		
		String tmp[] = str.split("/");
		for(int i = 1; i < tmp.length; i++) {
			if(!tmp[i].equals("WEB-INF") && !tmp[i].equals("jsp")){
				rtn = rtn + "/" + tmp[i];
			}
		}
		
		return rtn.replace(".jsp", ".do");
	}
	
	/**
	 * ���� �ڵ� ����
	 * @param len
	 * @return
	 */
	public static String getMakeRandomCode(int len) {
		Random ran = new Random();
		StringBuffer buf = new StringBuffer();
		
		for(int i = 0; i < len; i++) {
			if(ran.nextBoolean()){
				buf.append((char)((int)(ran.nextInt(26))+97));
			} else {
				buf.append((ran.nextInt(10)));
			}
		}
		
		return buf.toString();
	}

	/**
	 * @Method Name  : EnCode
	 * @�ۼ���   : 2014. 11. 2. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� : 
	 * @param param
	 * @return
	 */
	public static String EnCode(String param)
	{
	    // sjisbmoc
	    StringBuffer    sb  = new StringBuffer();

	    if(param == null)
	    {
	        sb.append("");
	    }
	    else
	    {
	        if(param.length()>0)
	        {
	            for(int i=0; i<param.length(); i++)
	            {
	                String  len = ""+((int)param.charAt(i));
	                sb.append(len.length());
	                sb.append(((int)param.charAt(i)));
	            }
	        }
	    }
	    return sb.toString();
	}	
	
	/**
	 * @Method Name  : DeCode
	 * @�ۼ���   : 2014. 11. 2. 
	 * @�ۼ���   : dino
	 * @�����̷�  :
	 * @Method ���� :
	 * @param param
	 * @return
	 */
	public static String DeCode(String param)

	{
	    // sjisbmoc 
	    StringBuffer    sb  = new StringBuffer();
	    int             pos = 0;
	    boolean         flg = true;

	    if(param!=null)
	    {
	        if(param.length()>1)
	        {
	            while(flg)
	            {
	                String  sLen    = param.substring(pos,++pos);
	                int     nLen    = 0;

	                try
	                {
	                    nLen    = Integer.parseInt(sLen);
	                }
	                catch(Exception e)
	                {
	                    nLen   = 0;
	                }

	                String  code    = "";
	                if((pos+nLen)>param.length())
	                    code    = param.substring(pos);
	                else
	                    code    = param.substring(pos,(pos+nLen));

	                pos += nLen;
	                sb.append(((char) Integer.parseInt(code)));

	                if(pos >= param.length())
	                    flg = false;
	            }
	        }
	    }
	    else
	    {
	        param = "";

	    }

	    return sb.toString();
	}
	
	public static String removeXSS(String str, boolean use_html) {
		if (str == null) return "";
	    String str_low = "";
	    if(use_html){ // HTML tag�� ����ϰ� �� ��� �κ� ���
	    	/*
	    	// HTML tag�� ��� ����
	        str = str.replaceAll("<","&lt;");
	        str = str.replaceAll(">","&gt;");
	        // Ư�� ���� ����
	        str = str.replaceAll("/","&frasl;");
	        str = str.replaceAll("&", "&amp;");
	        str = str.replaceAll("%00", null);
	        str = str.replaceAll("\"", "&#34;");
	        str = str.replaceAll("\'", "&#39;");
	        str = str.replaceAll("%", "&#37;");
	        str = str.replaceAll("../", "");
	        str = str.replaceAll("..\\\\", "");
	        str = str.replaceAll("./", "");
	        str = str.replaceAll("%2F", "");
	        // ����� HTML tag�� ����
	        str = str.replaceAll("&lt;p&gt;", "<p>");
	        str = str.replaceAll("&lt;P&gt;", "<P>");
	        str = str.replaceAll("&lt;br&gt;", "<br>");
	        str = str.replaceAll("&lt;BR&gt;", "<BR>");
	        */
	        // ��ũ��Ʈ ���ڿ� ���͸� (������ - �ʿ��� ��� ���Ȱ��̵忡 ÷�ε� ���� �߰�)
	        str_low= str.toLowerCase();
	        if( str_low.contains("javascript") || str_low.contains("script")     || str_low.contains("iframe") ||
	                str_low.contains("document")   || str_low.contains("vbscript")   || str_low.contains("applet") ||
	                str_low.contains("embed")      || str_low.contains("object")     || str_low.contains("frame") ||
	                str_low.contains("grameset")   || str_low.contains("layer")      || str_low.contains("bgsound") ||
	                str_low.contains("alert")      || str_low.contains("onblur")     || str_low.contains("onchange") ||
	                str_low.contains("onclick")    || str_low.contains("ondblclick") || str_low.contains("enerror") ||
	                str_low.contains("onfocus")    || str_low.contains("onload")     || str_low.contains("onmouse") ||
	                str_low.contains("onscroll")   || str_low.contains("onsubmit")   || str_low.contains("onunload"))
	        {
	            str = str_low;
	            str = str.replaceAll("javascript", "x-javascript");
	            str = str.replaceAll("script", "x-script");
	            str = str.replaceAll("iframe", "x-iframe");
	            str = str.replaceAll("document", "x-document");
	            str = str.replaceAll("vbscript", "x-vbscript");
	            str = str.replaceAll("applet", "x-applet");
	            str = str.replaceAll("embed", "x-embed");
	            str = str.replaceAll("object", "x-object");
	            str = str.replaceAll("frame", "x-frame");
	            str = str.replaceAll("grameset", "x-grameset");
	            str = str.replaceAll("layer", "x-layer");
	            str = str.replaceAll("bgsound", "x-bgsound");
	            str = str.replaceAll("alert", "x-alert");
	            str = str.replaceAll("onblur", "x-onblur");
	            str = str.replaceAll("onchange", "x-onchange");
	            str = str.replaceAll("onclick", "x-onclick");
	            str = str.replaceAll("ondblclick","x-ondblclick");
	            str = str.replaceAll("enerror", "x-enerror");
	            str = str.replaceAll("onfocus", "x-onfocus");
	            str = str.replaceAll("onload", "x-onload");
	            str = str.replaceAll("onmouse", "x-onmouse");
	            str = str.replaceAll("onscroll", "x-onscroll");
	            str = str.replaceAll("onsubmit", "x-onsubmit");
	            str = str.replaceAll("onunload", "x-onunload");
	        }
	    }else{ // HTML tag�� ������� ���ϰ� �� ���
	        str = str.replaceAll("\"","&gt;");
	        str = str.replaceAll("&", "&amp;");
	        str = str.replaceAll("<", "&lt;");
	        str = str.replaceAll(">", "&gt;");
	        str = str.replaceAll("%00", null);
	        str = str.replaceAll("\"", "&#34;");
	        str = str.replaceAll("\'", "&#39;");
	        str = str.replaceAll("%", "&#37;");
	        str = str.replaceAll("../", "");
	        str = str.replaceAll("..\\\\", "");
	        str = str.replaceAll("./", "");
	        str = str.replaceAll("%2F", "");
	    }

	    return str;
	}
	
}
