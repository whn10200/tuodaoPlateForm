package com.tuodao.bp.depository.sign;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RequestUtil {

	private final static Logger log = LoggerFactory.getLogger("RequestUtil");
	
	/**
	 * 功能：前台交易构造HTTP POST自动提交表单<br>
	 * @param action 表单提交地址<br>
	 * @param hiddens 以MAP形式存储的表单键值<br>
	 * @param encoding 上送请求报文域encoding字段的值<br>
	 * @return 构造好的HTTP POST交易表单<br>
	 */
	public static String createAutoFormHtml(String reqUrl, Map<String, String> hiddens,String encoding,String method) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
		sf.append("<form id = \"pay_form\" action=\"" + reqUrl
				+ "\" method=\""+method+"\"  style=\"display:none;\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				if(value!=null&&!value.equals(""))
				{
					sf.append(key + ":");
					sf.append("<input  name=\"" + key + "\" id=\"" + key + "\" value=\"" + value + "\"/>");
					sf.append("</br>");
				}
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}
	
	public static String createUrl(String reqUrl, Map<String, String> hiddens) {
		List<String> keys = new ArrayList<String>(hiddens.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append(reqUrl);

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) hiddens.get(name);
            if(value!=null&&!value.equals(""))
            {
            	sbHtml.append("&" + name + "=" + java.net.URLEncoder.encode(value.trim()));
            }
        }
        
        log.debug("测试用，请求URL，与html格式效果一致，但是会显示参数，仅测试用。");
        log.debug(sbHtml.toString());
        
        return sbHtml.toString();
	}
	
}
