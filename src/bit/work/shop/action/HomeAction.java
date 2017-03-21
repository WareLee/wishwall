package bit.work.shop.action;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.MessageDao;
import bit.work.shop.domain.Message;
import bit.work.shop.domain.MessagePage;
import bit.work.shop.domain.User;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午7:05:23
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
	
	public void setMessagePage(MessagePage messagePage) {
		this.messagePage = messagePage;
	}

	// 首次加载首页信息
	@SuppressWarnings("unchecked")
	public String getHome(){
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		
		MessageDao dao=new MessageDao();
		TreeMap<Integer, Double> hotMessMid = null;
		if(ctx.getApplication().get("hotMessMids") == null){
			hotMessMid = dao.getHotMessMids();
			ctx.getApplication().put("hotMessMids",hotMessMid);
		}else{
			hotMessMid=(TreeMap<Integer, Double>) ctx.getApplication().get("hotMessMids");
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
		TreeMap<Integer,Double> hotMessMids = (TreeMap<Integer, Double>) ctx.getApplication().get("hotMessMids");
		
		MessageDao dao=new MessageDao();
		
		messagePage = dao.getMess(messagePage.getZongshu(), 
				messagePage.getYema(), 
				messagePage.getYechang(), 
				curUser.getUid(), 
				hotMessMids);
		
		try {
			witerObject2json(messagePage, null);
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		
		return NONE;
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
		
		return "prayJsp";
	}
	// 想要摇一摇
	public String toShake(){
		
		return "shakeJsp";
	}
	
	// ---------------
	public List<Message> getHotMess() {
		return hotMess;
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
