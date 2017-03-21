package bit.work.shop.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import bit.work.shop.domain.Message;
import bit.work.shop.domain.MessagePage;
import bit.work.shop.utils.C3P0Utils;
import bit.work.shop.utils.HackerNewsSort;
import bit.work.shop.utils.TextTransfer;
import bit.work.shop.utils.ValueComparator;

/**
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午7:07:35
 */
public class MessageDao {
	// 		根据mid的map获取对应的mid消息---最热
	public List<Message> hotMessage(TreeMap<Integer,Double> mids,int curuid){
		List<Message> messages=new ArrayList<>();
		
		// 组装sql
		String sql="SELECT m.*,z.sstate FROM ("
				+ "SELECT me.*,us.nickname,us.headimgurl FROM ("
				+ "select * from t_message where mid in(";
		if(mids.keySet().isEmpty()){
			sql += "-1";
		}else{
			for(Integer mid:mids.keySet()){
				sql = sql+mid+",";
			}
			sql=sql.substring(0,sql.length()-1);
		}
		sql += ") )as me,t_user us WHERE me.uid=us.uid )AS m "
				+ " LEFT JOIN (SELECT * FROM t_zan WHERE uid=?) AS z ON m.mid=z.mid";
		
		
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, curuid);
			rs = stmt.executeQuery();
			while(rs.next()){
				Message mess=new Message();
				mess.setMid(rs.getInt("mid"));
				mess.setContent(rs.getString("content"));
				mess.setDetailtime(rs.getTimestamp("detailtime"));
				mess.setDetailtime(rs.getTimestamp("detailtime"));
				mess.setAnonymity(rs.getInt("anonymity"));
				mess.setZantimes(rs.getInt("zantimes"));
				mess.setMtype(rs.getString("mtype"));
				mess.setUid(rs.getInt("uid"));
				mess.setToyou(rs.getString("toyou"));
				mess.setFromname(rs.getString("fromname"));
				mess.setNickname(rs.getString("nickname"));
				mess.setHeadimgurl(rs.getString("headimgurl"));
				mess.setSstate(rs.getInt("sstate"));
				mess.setSimpleTime(TextTransfer.getTimeFormatText(rs.getTimestamp("detailtime")));
				
				messages.add(mess);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return messages;
	}
	
	//获取前一周的赞数超过0的消息并打分排序<mid,score>
	public TreeMap<Integer,Double> getHotMessMids(){
		
		// 获取据当前一周的时间
		Timestamp lastWeek = HackerNewsSort.getLastWeek();
		Connection conn = C3P0Utils.getConnection();
		String sql = "SELECT mid,detailtime,zantimes FROM t_message WHERE zantimes>0 and detailtime>? ";
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		HashMap<Integer,Double> map=null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, lastWeek.toString());
			rs = stmt.executeQuery();
			
			// 为计算热度做准备
			map = new HashMap<Integer,Double>();
			Timestamp end=new Timestamp(new Date().getTime());
			DecimalFormat accuracy=new DecimalFormat("0.00000");
			
			while(rs.next()){
				map.put(rs.getInt("mid"), 
						HackerNewsSort.hackerScore(rs.getInt("zantimes"), rs.getTimestamp("detailtime"), end, 1.5, accuracy));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 对map根据value值排序
		TreeMap<Integer,Double> sorted_map=null;
		if(map!=null){
			ValueComparator bvc =  new ValueComparator(map);  
	        sorted_map = new TreeMap<Integer,Double>(bvc);
	        sorted_map.putAll(map);
		}
		
		return sorted_map;
	}
    
	// 根据偏移获取最新的几条数据,不知道为什么直接使用TreeMap的containsKey会中途中断
	public MessagePage getMess(int zongshu,int yema , int yechang,int curuid, TreeMap<Integer, Double> hotMessMid){
		MessagePage messagePage = new MessagePage();
		int offset=zongshu-(yema+1)*yechang;
		int amount=yechang;
		
		if(offset<0){
			amount=offset+yechang;
			offset = 0;
		}
		
		if(amount<0){
			return messagePage; 
		}
		
		List<Message> thenewes=new ArrayList<Message>();
		TreeSet<Integer> indexs=new TreeSet<>();
		for(Integer ind:hotMessMid.keySet()){
			indexs.add(ind);
		}
		
		String sql = "SELECT m.*,z.sstate FROM "
				+ " (SELECT me.*,us.nickname,us.headimgurl FROM t_message me,t_user us WHERE me.uid=us.uid ORDER BY me.mid asc LIMIT ?,? ) AS m "
				+ " LEFT JOIN (SELECT * FROM t_zan WHERE uid=?) AS z "
				+ " ON m.mid=z.mid ORDER BY MID DESC";
		Connection conn = C3P0Utils.getConnection();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, offset);
			stmt.setInt(2, amount);
			stmt.setInt(3, curuid);
			rs = stmt.executeQuery();
			while(rs.next()){
				
//				if(!hotMessMid.containsKey(rs.getInt("mid"))){
				if(! indexs.contains(rs.getInt("mid"))){
					
					Message mess=new Message();
					mess.setMid(rs.getInt("mid"));
					mess.setContent(rs.getString("content"));
					mess.setDetailtime(rs.getTimestamp("detailtime"));
					mess.setAnonymity(rs.getInt("anonymity"));
					if(rs.getInt("anonymity")==0){
						mess.setUid(rs.getInt("uid"));
						mess.setHeadimgurl(rs.getString("headimgurl"));
						mess.setNickname(rs.getString("nickname"));
					}else{
						mess.setUid(0);
						mess.setHeadimgurl("../img/ni_img.png");
						mess.setNickname("匿名");
					}
					mess.setZantimes(rs.getInt("zantimes"));
					mess.setMtype(rs.getString("mtype"));
					mess.setToyou(rs.getString("toyou"));
					mess.setFromname(rs.getString("fromname"));
					mess.setSstate(rs.getInt("sstate"));
					mess.setSimpleTime(TextTransfer.getTimeFormatText(rs.getTimestamp("detailtime")));
					
					thenewes.add(mess);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		messagePage.setYechang(yechang);
		messagePage.setYema(yema+1);
		messagePage.setZongshu(zongshu);
		messagePage.setMessList(thenewes);
		messagePage.setHavemore(zongshu-(yema+1)*yechang);

		return messagePage;
	}
	
	// 一共有多少条消息
	public int getCount(){
		String sql = "SELECT COUNT(*) FROM t_message";
		Connection conn = C3P0Utils.getConnection();
		Statement stat=null;
		ResultSet rs=null;
		int zongshu=0;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			rs.next();
			zongshu = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(stat!=null) stat.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return zongshu;
	}
	
	public MessagePage getNewestMess(int curUid, TreeMap<Integer, Double> hotMessMid) {
		int zongshu=getCount();
		
		return getMess(zongshu, 0, 10, curUid, hotMessMid);
	}

	// 赞
	public int doAZan(int targetMid, int curUid) {
		Connection conn = C3P0Utils.getConnection();
		// 删除message,mid表被操作的mid
		String sql = "select * from t_zan where mid=? and uid=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		int affect = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, targetMid);
			ps.setInt(2, curUid);
			rs = ps.executeQuery();
			
			if (rs.first()) {
				// 如果已经赞过
				// 为message的zantimes-1,mid表示被取消赞的消息的mid
				sql = "UPDATE t_message SET zantimes=zantimes-1 WHERE mid=? AND zantimes>0";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, targetMid);
				affect = ps.executeUpdate();
				if (affect >= 1) {
					// 去掉zan表中的记录,mid表当前操作的消息,uid表当前的操作人
					sql = "DELETE FROM t_zan WHERE mid=? AND uid=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, targetMid);
					ps.setInt(2, curUid);
					affect = ps.executeUpdate();
				}
				
			} else {
				// 如果没赞过
				sql = "update t_message set zantimes=zantimes+1 where mid=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, targetMid);
				affect = ps.executeUpdate();
				
				if (affect >= 1) {
					// 去掉zan表中的记录,mid表当前操作的消息,uid表当前的操作人
					sql = "INSERT INTO t_zan(uid,mid,sstate) VALUES (?,?,1)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, curUid);
					ps.setInt(2, targetMid);
					affect = ps.executeUpdate();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return affect;
	}
	
	public int deleteMessage(int targetMid, int curUid) {
		Connection conn = C3P0Utils.getConnection();
		// 删除message,mid表被操作的mid
		String sql = "delete from t_message where mid=? and uid=?";
		PreparedStatement ps=null;
		int affect=0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, targetMid);
			ps.setInt(2, curUid);
			affect = ps.executeUpdate();
			
			// 删除相关的赞消息
			if (affect > 0) {
				sql = "delete from t_zan where mid=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, targetMid);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return affect;
	}
	
	public int insertMess(Message mess){
		Connection conn = C3P0Utils.getConnection();
		// 添加新message,为保证安全,其中的uid应从session中获取
		String sql="INSERT INTO t_message (content,detailtime,anonymity,zantimes,mtype,uid,toyou,fromname) VALUES(?,?,?,0,?,?,?,?)";
		PreparedStatement ps=null;
		int rows=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mess.getContent());
			ps.setTimestamp(2, mess.getDetailtime());
			ps.setInt(3, mess.getAnonymity());
			ps.setString(4, mess.getMtype());
			ps.setInt(5, mess.getUid());
			ps.setString(6, mess.getToyou());
			ps.setString(7, mess.getFromname());
			
			rows = ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rows;
	}
	
	// 根據用戶的uid查詢對應用戶的所有消息
	public List<Message> getMessByUid(int curUid, int targetUid){
		
		List<Message> messList = new ArrayList<Message>();

		String sql = "SELECT m.*,z.sstate FROM (SELECT me.*,us.nickname,us.headimgurl FROM t_message me,t_user us WHERE me.uid=? and me.anonymity<? and me.uid=us.uid ORDER BY MID DESC) AS m LEFT JOIN (SELECT MID,sstate FROM t_zan WHERE uid=? ) AS z ON m.mid=z.mid";
		Connection conn = C3P0Utils.getConnection();
		// 找到用户uid=1的用户的所有消息,降序排列, 第一个uid为被查看的用户uid, 第二个uid为当前的用户
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, targetUid);
			if(curUid==targetUid){
				ps.setInt(2, 2);
			}else{
				ps.setInt(2, 1);
			}
			ps.setInt(3, curUid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Message mess = new Message();
				mess.setAnonymity(rs.getInt("anonymity"));
				mess.setContent(rs.getString("content"));
				mess.setMid(rs.getInt("mid"));
				mess.setMtype(rs.getString("mtype"));
				mess.setDetailtime(rs.getTimestamp("detailtime"));
				mess.setSimpleTime(TextTransfer.getTimeFormatText(rs.getTimestamp("detailtime")));
				mess.setUid(rs.getInt("uid"));
				mess.setZantimes(rs.getInt("zantimes"));
				mess.setSstate(rs.getInt("sstate"));
				mess.setNickname(rs.getString("nickname"));
				mess.setToyou(rs.getString("toyou"));
				mess.setFromname(rs.getString("fromname"));
				mess.setHeadimgurl(rs.getString("headimgurl"));
				messList.add(mess);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return messList;
		
	}

	// 获取一条随机消息体
	public Message getRandomMess(int curUid) {
		Message mess=null;
		
		int zongshu=getCount(); // 当前消息总数
		int random = (int)(Math.random()*zongshu);
		
		String sql="SELECT m.*,z.sstate FROM (SELECT me.*,us.nickname,us.headimgurl FROM t_message me,t_user us WHERE me.uid=us.uid ORDER BY me.mid asc LIMIT ?,1 ) AS m LEFT JOIN (SELECT * FROM t_zan WHERE uid=?) AS z ON m.mid=z.mid ORDER BY MID DESC";
		
		Connection conn = C3P0Utils.getConnection();
		// 找到用户uid=1的用户的所有消息,降序排列, 第一个uid为被查看的用户uid, 第二个uid为当前的用户
		PreparedStatement ps=null;
		ResultSet rs=null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, random);
				ps.setInt(2, curUid);
				rs=ps.executeQuery();
				rs.next();
				
				mess=new Message();
				mess.setAnonymity(rs.getInt("anonymity"));
				mess.setContent(rs.getString("content"));
				mess.setMid(rs.getInt("mid"));
				mess.setMtype(rs.getString("mtype"));
				mess.setDetailtime(rs.getTimestamp("detailtime"));
				mess.setSimpleTime(TextTransfer.getTimeFormatText(rs.getTimestamp("detailtime")));
				mess.setUid(rs.getInt("uid"));
				mess.setZantimes(rs.getInt("zantimes"));
				mess.setSstate(rs.getInt("sstate"));
				mess.setNickname(rs.getString("nickname"));
				mess.setToyou(rs.getString("toyou"));
				mess.setFromname(rs.getString("fromname"));
				mess.setHeadimgurl(rs.getString("headimgurl"));
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return mess;
			
	}
	
}
