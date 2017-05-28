package bit.work.shop.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import javax.xml.soap.Text;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.MessageDao;
import bit.work.shop.dao.UserDao;
import bit.work.shop.domain.Message;
import bit.work.shop.domain.User;
import bit.work.shop.utils.PropertiesUtils;
import bit.work.shop.utils.TextTransfer;
import freemarker.template.SimpleDate;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月10日下午3:14:39
 */
public class MessageAction extends BaseAction{

	private static final long serialVersionUID = 1L;
		private int targetMid; // 要删除消息的mid
		private Message message; // 要发送的消息
		
		// 刪除消息
		public String deleteMess(){
			// curuid ---mid
			ActionContext ctx = ActionContext.getContext();
			User curUser = (User) ctx.getSession().get("curuser");
			List<Integer> hotMessMid = (List<Integer>) ctx.getApplication().get("hotMessMids");
			
			MessageDao dao=new MessageDao();
			Integer rows = dao.deleteMessage(targetMid, curUser.getUid());
			if(rows>0 && hotMessMid != null){ // 如果删除成功, 更新hotMessMid
				hotMessMid.remove((Integer)targetMid);
			}
			
			try {
				witerList2json(rows, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return NONE;
		}

		// 发送消息
		@SuppressWarnings("unchecked")
		public String sendMess(){
			ActionContext ctx = ActionContext.getContext();
			User curUser = (User) ctx.getSession().get("curuser");
			
			/******识别机器人,一旦发现是机器人超过5次,二十分钟后才允许再次发消息; 且每次机器行为都不会被受理********/
				Long now=new Date().getTime();
				/** 判断此次行为是否是机器人
				 * 		|---Yes, 更新机器人行为次数,不受理请求
				 * 		|---NO , 判断 以前是否有过机器人行为 |--- 有, 如果过了恶意处罚期(认为机器人行为达到5次才会有延迟处罚),重置机器人行为,并 正常受理(更新发消息的时间); 否则, 不受理
				 * 										  |--- 无 , 正常受理(更新发消息的时间)
				 * 
				 * 
				 */
				
					/**根据记录,判断此次行为是否是机器人**/
					Queue<Long> sendmess = (Queue<Long>) ctx.getSession().get("sendmess");
					if(sendmess==null){  
						sendmess=new ArrayDeque<Long>();
						ctx.getSession().put("sendmess", sendmess);
					}
					sendmess.add(now);
					while(sendmess.size()>5){ sendmess.poll(); }
					boolean isRobot=TextTransfer.isRobot(sendmess, 1000L);
					/**根据记录,判断是否是机器人**/
					
					Integer robot=(Integer)ctx.getSession().get("robot");
//					System.out.println(robot+"------------");
					if(isRobot){
						if(robot==null){ 
							robot=0;
							ctx.getSession().put("robot", robot);
						}
						robot = robot + 1;
						ctx.getSession().put("robot", robot);
						
//						System.out.println("机器人...更新受处罚的次数为--> "+robot+"  时间--->"+now);
						return SUCCESS;
					}else{
						if(robot==null || robot == 0){ // 以前无机器人行为
							robot=0;
							ctx.getSession().put("robot", robot);
						}else{ // 以前有机器人行为
							Long lastsend=(Long)ctx.getSession().get("lastsend");
							long s=1000L;
							long min=s * 60 ;
							if(robot>5){
								if(now - lastsend > min*20){
									// 过了处罚期, 重置机器人行为,并受理
									robot=0;
									ctx.getSession().put("robot", robot);
								}else{
//									System.out.println("未过处罚期... 需等待...");
									return SUCCESS;
								}
							}
						}
					}
					
			/******识别机器人********/
					
			// 正常受理请求
			Long lastsend=(Long)ctx.getSession().get("lastsend");
			if(lastsend==null){
				lastsend=now;
				ctx.getSession().put("lastsend", lastsend);
			}
					
			// 不匿名的条件一定是: 有1, 且昵称为真
			if(message.getAnonymity()==0){
				if( ! message.getFromname().trim().equals(curUser.getNickname().trim())){
					message.setAnonymity(1);
				}
			}
			message.setUid(curUser.getUid());
			message.setDetailtime(new Timestamp(now));
			// 如果不支持表情,要过滤from, to, content
			if(PropertiesUtils.getKeyValue("emoji").equals("0")){
				message.setFromname(TextTransfer.filterEmoji(message.getFromname()));
				message.setToyou(TextTransfer.filterEmoji(message.getToyou()));
				message.setContent(TextTransfer.filterEmoji(message.getContent()));
			}
			MessageDao dao=new MessageDao();
			dao.insertMess(message);
			
			if(curUser.getUsenotify()!=null && curUser.getUsenotify()>0){
				UserDao dd=new UserDao();
				dd.dealNotify(0, -1, curUser.getUid());
				curUser.setUsenotify(curUser.getUsenotify()-1);
			}
			
			return SUCCESS;
		}
		
		// 产生一条随机消息
		public String getRandomMess(){
			
			ActionContext ctx = ActionContext.getContext();
			User curUser = (User) ctx.getSession().get("curuser");
			
			Message mess=null;
			
			/******防止过度摇一摇******/
				Message lastmess = (Message)ctx.getSession().get("lastmess");
				Long lastrandom = (Long)ctx.getSession().get("lastrandom");
				Long now=new Date().getTime();
				// 如果之前没有摇一摇, 摇 ; 否则, 判断是否过度频繁
				if(lastmess != null && lastrandom != null){
					if( now-lastrandom < 500L){ // 过度频繁,一共上次的数据
						mess=lastmess;
					}else{ // 正常频率
						MessageDao dao=new MessageDao();
						mess = dao.getRandomMess(curUser.getUid());
						ctx.getSession().put("lastmess", mess);
						ctx.getSession().put("lastrandom", now);
					}
				}else{
					MessageDao dao=new MessageDao();
					mess = dao.getRandomMess(curUser.getUid());
					ctx.getSession().put("lastmess", mess);
					ctx.getSession().put("lastrandom", now);
				}
			/******防止过度摇一摇******/
			
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
