package bit.work.shop.action;

import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.MessageDao;
import bit.work.shop.dao.UserDao;
import bit.work.shop.domain.Message;
import bit.work.shop.domain.User;

/**
 * @author ware E-mail:
 */
public class UserAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private int targetUid; // 要查詢的目標用戶uid
	private User targetUser; // 要查询的目标用户
	private List<Message> targetMess; // 目标用户的所有消息
	private int size=0; // 用户发送的消息数
	private boolean self=false; // 是用户本身吗
	
	private String bground="bg_default.jpg";
	
	// 根据目标用户的uid查询对应用户的消息
	public String getTargetHome() throws SQLException{
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		
		UserDao dao=new UserDao();
		targetUser=dao.getUser(targetUid);
		
		MessageDao mdao=new MessageDao();
		targetMess=mdao.getMessByUid(curUser.getUid(), targetUid);
		
		if(targetMess!=null){
			size=targetMess.size();
		}
		self=(targetUid==curUser.getUid());
		
		return "personalJsp";
	}
	
	// 设置用户背景
	public String bgChoose(){
		ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
		int uid = curUser.getUid();
		
		UserDao dao=new UserDao();
		int effect = dao.setBg(bground, uid);
		// 修改成功
		if(effect>0){
			curUser.setBground(bground);
		}
		
		return "home";
	}

	// ---------
	
	
	public int getTargetUid() {
		return targetUid;
	}

	public String getBground() {
		return bground;
	}

	public void setBground(String bground) {
		this.bground = bground;
	}

	public boolean isSelf() {
		return self;
	}


	public int getSize() {
		return size;
	}


	public void setTargetUid(int targetUid) {
		this.targetUid = targetUid;
	}

	public User getTargetUser() {
		return targetUser;
	}

	public List<Message> getTargetMess() {
		return targetMess;
	}
	
	
	
	

}
