package bit.work.shop.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Hacker News 排序算法 3个决定因素:t---发帖过去的小时数,采用进一法,不足一小时算作一小时, p---投的票数
 * G---重力因子:决定随时间变化,热度下降的速度快慢 公式:(p)/Math.pow(t+2,G)
 * 这里建议G的取值为1.5
 * 精度建议为:小数点后5~6位
 * 
 * G=1.5时
 * 1 week----1vote---0.00045
 * 48hours---1vote---0.002828
 * 24hours---1vote---0.007543
 * 0 hours---1vote---0.287175
 * 
 * re刚发送 = re(一天前39票) = re(两天102票)
 * 
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20162016年11月14日下午4:55:13
 */
public class HackerNewsSort {


	// 根据时间戳返回距离当前时刻的小时数,采用进一法
	public static double hoursPast(Timestamp start,Timestamp end) {
		Long ms = end.getTime() - start.getTime();
		Long tt = ms / 1000L;
		return tt.doubleValue()/60.0;
	}
	
	// 据当前一周的timestamp
	public static Timestamp getLastWeek() {
		java.sql.Timestamp start = new Timestamp(new Date().getTime());
		return new Timestamp(start.getTime()-604800000L);
	}
	/**
	 * 计算热度
	 * @param point 投票数
	 * @param start 发表时间
	 * @param end	当前时间
	 * @param gravity 重力参数
	 * @param accuracy 最终计算值的精度
	 * @return
	 */
	public static double hackerScore(int point,Timestamp start,Timestamp end,double gravity,DecimalFormat accuracy){		
		double hours=Math.abs(hoursPast(start,end));
		double a=0;
		if(point>0){
			a=point;
		}
		double b=Math.pow(hours+2, gravity);
		return Double.parseDouble((accuracy.format(a/b)).trim());
	}

}
  
