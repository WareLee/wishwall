package bit.work.shop.domain;

import java.util.List;

/**
 * mysql中limit[偏移量,行数量]
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20162016年11月19日下午2:44:58
 */
public class MessagePage {
	private int yema; // 当前已经展示消息中索引值最小的记录的索引
	private List<Message> messList;
	private int yechang; // 页面消息长度
	private int zongshu; // 总记录数
	private int havemore; // 剩余消息数,以此判断是否有更多消息
	
	// 为考虑背景设置的字段
	private String bg="animal.png";
	
	
	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public int getHavemore() {
		return havemore;
	}

	public void setHavemore(int havemore) {
		this.havemore = havemore;
	}

	public int getZongshu() {
		return zongshu;
	}

	public void setZongshu(int zongshu) {
		this.zongshu = zongshu;
	}

	public List<Message> getMessList() {
		return messList;
	}

	public void setMessList(List<Message> messList) {
		this.messList = messList;
	}

	public int getYema() {
		return yema;
	}

	public void setYema(int yema) {
		this.yema = yema;
	}

	public int getYechang() {
		return yechang;
	}

	public void setYechang(int yechang) {
		this.yechang = yechang;
	}


}
