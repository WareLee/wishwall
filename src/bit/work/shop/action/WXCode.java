package bit.work.shop.action;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.UserDao;
import bit.work.shop.domain.AccessToken;
import bit.work.shop.domain.User;
import bit.work.shop.domain.Userinfo;
import bit.work.shop.utils.HttpKits;
import bit.work.shop.utils.PropertiesUtils;

/**
 * 
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午3:41:42
 */
public class WXCode extends BaseAction{
	private String code="cjodeakdfja41sda5fasf1a";
	private String state;
	private User curUser;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String temp(){
		User user=new User();
		user.setUid(2);
		user.setNickname("兮兮");
		user.setOpenid("ksjakflsafjienxnxlafooo");
		user.setSignature("我的签名..");
		user.setHeadimgurl("../img/rBACE1TA2qfQ2FScAAAQoyZ33Vo309_200x200_3.jpg");
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put("curuser",user);
		
		return "home";
	}
	
	// index.jsp重定向到该认证第一步,在通过Redirect_Uri重定向到该方法
	// 获取用户信息,然后重定向到主页
	public String getUserinfo() {
		// 微信认证第二步:根据code获取access_token
		String path=PropertiesUtils.getKeyValue("FORACCESSTOKENURL");
		path=path.replace("APPID", PropertiesUtils.getKeyValue("APPID"));
		path=path.replace("SECRET", PropertiesUtils.getKeyValue("APPSECRET"));
		path=path.replace("CODE", code);
		AccessToken token =null;
		try {
			token = (AccessToken)(HttpKits.toPost(path,AccessToken.class, null));
		} catch (Exception e) {
			return "error";
		}
		
		// 微信认证第三步:根据access_token获取Userinfo,一定要用https请求(post),否则微信会返回证书不合法异常
		if(token.getErrmsg() != null || !"".equals(token.getErrmsg().trim())){
			return "error"; // 请求access_token错误
		}
		String path2=PropertiesUtils.getKeyValue("FORUSERINFOURL");
		path2=path2.replace("ACCESS_TOKEN", token.getAccess_token());
		path2=path2.replace("OPENID", token.getOpenid());
		Userinfo userinfo=null;
		try {
			userinfo = (Userinfo)(HttpKits.toPost(path2,Userinfo.class, null));
		} catch (Exception e) {
			return "error";
		}
		
		// 根据userinfo查看用户是否存在,若不存在则插入
		UserDao userDao=new UserDao();
		curUser=userDao.checkUser(userinfo.getOpenid());
		if(curUser==null){
			int uid=userDao.insertUser(userinfo).intValue();
			curUser=userDao.getUser(uid);
		}
		if(userinfo.getHeadimgurl()!=null && !userinfo.getHeadimgurl().trim().equals("")){
			curUser.setHeadimgurl(userinfo.getHeadimgurl());
		}

		// 将当前用户对象放到session中
		ActionContext ctx = ActionContext.getContext();
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
