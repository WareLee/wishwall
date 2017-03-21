package bit.work.shop.domain;

import java.sql.Timestamp;

/**
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20162016年11月19日上午10:41:35
 */
public class Message {
	private Integer mid;
	private Integer uid;
	private String content;
	private Integer anonymity;
	private Integer zantimes;
	private String mtype;
	private String headimgurl;
	private String nickname;
	private String toyou;
	private String fromname;
	private Integer state;
	private Timestamp detailtime;
	
	// 用于将timestamp简化输出,并不在t_message表中 
	private String simpleTime;

	// 用于查询展示消息使用到,并不在t_message表中 
	private int sstate;
	
	public Timestamp getDetailtime() {
		return detailtime;
	}

	public void setDetailtime(Timestamp detailtime) {
		this.detailtime = detailtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setAnonymity(Integer anonymity) {
		this.anonymity = anonymity;
	}

	public void setZantimes(Integer zantimes) {
		this.zantimes = zantimes;
	}


	public String getSimpleTime() {
		return simpleTime;
	}

	public void setSimpleTime(String simpleTime) {
		this.simpleTime = simpleTime;
	}

	public String getToyou() {
		return toyou;
	}

	public void setToyou(String toyou) {
		this.toyou = toyou;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public int getAnonymity() {
		return anonymity;
	}

	public void setAnonymity(int anonymity) {
		this.anonymity = anonymity;
	}

	public int getZantimes() {
		return zantimes;
	}

	public void setZantimes(int zantimes) {
		this.zantimes = zantimes;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public int getSstate() {
		return sstate;
	}

	public void setSstate(int sstate) {
		this.sstate = sstate;
	}

	

}
