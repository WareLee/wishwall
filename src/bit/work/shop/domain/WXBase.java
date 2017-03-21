package bit.work.shop.domain;
/**
 * 微信用户认证所需要的琳琳碎碎的东西
 * 
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午3:51:03
 */
public class WXBase {
	
	private String code;
	private String state;
	private User curUser;
	
	public User getCurUser() {
		return curUser;
	}
	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
