package bit.work.shop.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.MessageDao;
import bit.work.shop.dao.UserDao;
import bit.work.shop.domain.Bg;
import bit.work.shop.domain.Message;
import bit.work.shop.domain.MessagePage;
import bit.work.shop.domain.User;

/**
 * @author ware E-mail:
 */
public class HomeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Message> hotMess;
	private MessagePage messagePage;
	
	private int targetMid; // 要删除消息的mid
	private String curNickname; // 当前用户的昵称,在发送消息时使用
	
	// 提供管理员使用
	private String openid;
	
	private List<Bg> bgs ;
	
	private int usenotify=0;
	
	public void setMessagePage(MessagePage messagePage) {
		this.messagePage = messagePage;
	}

	// 首次加载首页信息
	@SuppressWarnings("unchecked")
	public String getHome(){
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		
		long minute = 60 * 1000;// 1分钟   
		long hour = 60 * minute;// 1小时   
		long day = 24 * hour;// 1天   
		Long lasttime=(Long)ctx.getApplication().get("lasttime");
		Long now = new Date().getTime();
		
		MessageDao dao=new MessageDao();
		List<Integer> hotMessMid = (List<Integer>) ctx.getApplication().get("hotMessMids");
		
		// 如果没有统计过热门消息或者统计的已经过期(超过一天),就重新统计一次
		if(hotMessMid == null || hotMessMid.size()==0 ||(lasttime == null) || (lasttime != null && now-lasttime >= day )){
			hotMessMid = dao.getHotMessMids();
			ctx.getApplication().put("hotMessMids",hotMessMid);
			ctx.getApplication().put("lasttime",now);
		}
		hotMess=dao.hotMessage(hotMessMid,curUser.getUid());
		messagePage=dao.getNewestMess(curUser.getUid(),hotMessMid);
		
		return "homeJsp";
	}

	// 获取更多Message信息---json
	public String getMoreMess(){
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		@SuppressWarnings("unchecked")
		List<Integer> hotMessMids = (List<Integer>) ctx.getApplication().get("hotMessMids");
		
		MessageDao dao=new MessageDao();
		
		messagePage = dao.getMess(messagePage.getZongshu(), 
				messagePage.getYema(), 
				messagePage.getYechang(), 
				curUser.getUid(), 
				hotMessMids);

		// 设置背景字段
		messagePage.setBg(curUser.getBground());
		
		try {
			witerObject2json(messagePage, null);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		
		return NONE;
	}
	
	// 管理员登录入口
	public String administor(){
		UserDao dao=new UserDao();
		User administor = dao.checkUser(openid);
		if(administor==null){
			return "error";
		}
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put("curuser",administor);
		
		long minute = 60 * 1000;// 1分钟   
		long hour = 60 * minute;// 1小时   
		long day = 24 * hour;// 1天   
		Long lasttime=(Long)ctx.getApplication().get("lasttime");
		Long now = new Date().getTime();
		
		MessageDao messDao=new MessageDao();
		List<Integer> hotMessMid = (List<Integer>) ctx.getApplication().get("hotMessMids");
		
		// 如果没有统计过热门消息或者统计的已经过期(超过一天),就重新统计一次
		if(hotMessMid == null || hotMessMid.size()==0 ||(lasttime == null) || (lasttime != null && now-lasttime >= day )){
			hotMessMid = messDao.getHotMessMids();
			ctx.getApplication().put("hotMessMids",hotMessMid);
			ctx.getApplication().put("lasttime",now);
		}
		hotMess=messDao.hotMessage(hotMessMid,administor.getUid());
		messagePage=messDao.getNewestMess(administor.getUid(),hotMessMid);
		
		return "administor";
	}
	
	// 點讚
	public String doZan(){
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		
		MessageDao dao=new MessageDao();
		Integer rows = dao.doAZan(targetMid, curUser.getUid());
		
		try {
			witerList2json(rows, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	// 想要许愿
	public String toPray(){
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		curNickname=curUser.getNickname();
		usenotify = curUser.getUsenotify();
		
		return "prayJsp";
	}
	// 想要摇一摇
	public String toShake(){
		
		return "shakeJsp";
	}
	
	// 展示所有可选的背景图
	public String showBgs(){
		UserDao dao = new UserDao();
		bgs = dao.showAllbgs();
		
		// 去掉系统提示消息
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		if(curUser.getSysnotify()!=null && curUser.getSysnotify()>0){
			UserDao dd=new UserDao();
			dd.dealNotify(-1, 0, curUser.getUid());
			curUser.setSysnotify(curUser.getSysnotify()-1);
		}
		
		return "bgs";
	}
	
	// ---------------
	
	public List<Message> getHotMess() {
		return hotMess;
	}

	public int getUsenotify() {
		return usenotify;
	}

	public void setUsenotify(int usenotify) {
		this.usenotify = usenotify;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public List<Bg> getBgs() {
		return bgs;
	}

	public MessagePage getMessagePage() {
		return messagePage;
	}
	public int getTargetMid() {
		return targetMid;
	}

	public void setTargetMid(int targetMid) {
		this.targetMid = targetMid;
	}

	public String getCurNickname() {
		return curNickname;
	}

}
