package bit.work.shop.utils;

import java.sql.Timestamp;
import java.util.Date;
import net.sf.json.JSONObject;

/**
 * 各种对象到格式文本的转换
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午3:45:42
 */
public class TextTransfer {
	
	private final static long minute = 60 * 1000;// 1分钟   
	private final static long hour = 60 * minute;// 1小时   
	private final static long day = 24 * hour;// 1天   
	private final static long month = 31 * day;// 月   
	private final static long year = 12 * month;// 年  

	
	public static Object jsonStr2Pojo(String jsonStr,Class target){
		JSONObject jsonObject=JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(jsonObject, target);
	}
	
	// 简化输出时间
		public static String getTimeFormatText(Timestamp date) {   
		    if (date == null) {   
		      return null;   
		    }   
		    long diff = new Timestamp(new Date().getTime()).getTime() - date.getTime();   
		    Long r = 0L;   
		    if (diff > year) {   
		      r = (diff / year);   
		      return r + "年前";   
		    }   
		    if (diff > month) {   
		      r = (diff / month);   
		      return r + "个月前";   
		    }   
		    if (diff > day) {   
		      r = (diff / day);
		      if(r.intValue()==1){
		    	  return "昨天";
		      }else if(r.intValue()==2){
		    	  return "前天";
		      }
		      return r + "天前";   
		    }   
		    if (diff > hour) {   
		      r = (diff / hour);   
		      return r + "小时前";   
		    }   
		    if (diff > minute) {   
		      r = (diff / minute);   
		      return r + "分钟前";   
		    }   
		    return "刚刚";   
		}  
}
