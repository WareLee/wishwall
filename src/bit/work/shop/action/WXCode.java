package bit.work.shop.action;


import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.UserDao;
import bit.work.shop.domain.SNSUserInfo;
import bit.work.shop.domain.User;
import bit.work.shop.domain.WeixinOauth2Token;
import bit.work.shop.utils.AdvancedUtil;
import bit.work.shop.utils.PropertiesUtils;
import bit.work.shop.utils.TextTransfer;

/**
 * 
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午3:41:42
 */
public class WXCode extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private String code="cjodeakdfja41sda5fasf1a";
	private String state;
	private User curUser;
	

	public String temp(){
		
		User user=new User();
		
		UserDao userDao=new UserDao();
		user=userDao.checkUser("oXxykvyvy");
		
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put("curuser",user);
		
		
		return "home";
	}
	
	// index.jsp重定向到该认证第一步,在通过Redirect_Uri重定向到该方法
	// 获取用户信息,然后重定向到主页
	public String getUserinfo() {
		//System.out.println("code--->"+code);
		
		// 获取网页授权access_token
		WeixinOauth2Token weixinOauth2Token;
		try {
			weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(PropertiesUtils.getKeyValue("APPID"), PropertiesUtils.getKeyValue("APPSECRET"), code);
		} catch (Exception e) {
			// 网页授权失败
			return "error";
		}
		
		// 网页授权接口访问凭证,由于accesstoken请求的限制,要注意重复使用
		String accessToken = weixinOauth2Token.getAccessToken();
		
		//System.out.println("accessToken--->"+accessToken);
		
		// 用户标识
		String openId = weixinOauth2Token.getOpenId();
		//System.out.println("openId---->"+openId);
//		// 获取用户信息
		SNSUserInfo snsUserInfo = null;
		
		// 根据userinfo查看用户是否存在,若不存在则插入
		UserDao userDao=new UserDao();
		curUser=userDao.checkUser(openId);
		
		//System.out.println("curUser-->"+curUser);
		ActionContext ctx = ActionContext.getContext();
		
		// 用户存在--->查数据库
		// 用户不存在--->获取个人info
		if(curUser==null ){ // 当前用户不存在
			//System.out.println("当前用户不存在");
			snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
			// 如果不支持表情,要过滤
			if(PropertiesUtils.getKeyValue("emoji").equals("0")){
				snsUserInfo.setNickname(TextTransfer.filterEmoji(snsUserInfo.getNickname()));
			}
			int uid=userDao.insertUser(snsUserInfo).intValue();
			curUser=userDao.getUser(uid);
		}else{
			Long now=new Date().getTime();
			Long lastupdate=curUser.getLastupdate().getTime();
			long minute = 60 * 1000;// 1分钟   
			long hour = 60 * minute;// 1小时   
			long twodays = 48 * hour;// 2天   
			
			if(now-lastupdate > twodays ){
				snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
				userDao.updateUser(snsUserInfo);
				curUser.setHeadimgurl(snsUserInfo.getHeadImgUrl());
			}
		}
		
		// 将当前用户对象放到session中
		ctx.getSession().put("curuser", curUser);
				
		return "home";
	}
	
	
	//-----------
	
	
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

	public User getCurUser() {
		return curUser;
	}

	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}
	
	
	
}
