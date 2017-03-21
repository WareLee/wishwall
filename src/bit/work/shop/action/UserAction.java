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
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月10日下午2:23:19
 */
public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int targetUid; // 要查詢的目標用戶uid
	private User targetUser; // 要查询的目标用户
	private List<Message> targetMess; // 目标用户的所有消息
	private int size=0; // 用户发送的消息数
	private boolean self=false; // 是用户本身吗
	
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

	
	// ---------
	
	
	public int getTargetUid() {
		return targetUid;
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
