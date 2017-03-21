package bit.work.shop.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.MessageDao;
import bit.work.shop.domain.Message;
import bit.work.shop.domain.User;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月10日下午3:14:39
 */
public class MessageAction extends BaseAction{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int targetMid; // 要删除消息的mid
		private Message message; // 要发送的消息
		
		// 刪除消息
		public String deleteMess(){
			// curuid ---mid
			ActionContext ctx = ActionContext.getContext();
			User curUser = (User) ctx.getSession().get("curuser");
			
			MessageDao dao=new MessageDao();
			Integer rows = dao.deleteMessage(targetMid, curUser.getUid());
			
			try {
				witerList2json(rows, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return NONE;
//			return "personalJsp";
		}

		// 发送消息
		public String sendMess(){
			ActionContext ctx = ActionContext.getContext();
			User curUser = (User) ctx.getSession().get("curuser");
			if(message.getFromname().trim().equals(curUser.getNickname().trim())){
				message.setAnonymity(0);
			}else{
				message.setAnonymity(1);
			}
			message.setUid(curUser.getUid());
			message.setDetailtime(new Timestamp(new Date().getTime()));
			
			MessageDao dao=new MessageDao();
			dao.insertMess(message);
			
			// 发送完消息是否会刷新
			return SUCCESS;
		}
		
		// 产生一条随机消息
		public String getRandomMess(){
			
			ActionContext ctx = ActionContext.getContext();
			User curUser = (User) ctx.getSession().get("curuser");
			
			MessageDao dao=new MessageDao();
			Message mess = dao.getRandomMess(curUser.getUid());
			try {
				witerObject2json(mess, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		    return NONE;
		}

		//----------------------
		
		public int getTargetMid() {
			return targetMid;
		}

		public void setTargetMid(int targetMid) {
			this.targetMid = targetMid;
		}

		public Message getMessage() {
			return message;
		}

		public void setMessage(Message message) {
			this.message = message;
		}
		
		
		
}
