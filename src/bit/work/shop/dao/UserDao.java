package bit.work.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bit.work.shop.domain.Bg;
import bit.work.shop.domain.SNSUserInfo;
import bit.work.shop.domain.User;
import bit.work.shop.utils.C3P0Utils;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午6:03:37
 */
public class UserDao {
	
	// 查询用户是否在数据库, 若不在返回null
	public User checkUser(String openid){
		User user=null;
		Connection conn = C3P0Utils.getConnection();
		String sql="select * from t_user where openid = ?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, openid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setBground(rs.getString("bground"));
				user.setHeadimgurl(rs.getString("headimgurl"));
				user.setNickname(rs.getString("nickname"));
				user.setOpenid(rs.getString("openid"));
				user.setSignature(rs.getString("signature"));
				user.setUid(rs.getInt("uid"));
				user.setSysnotify(rs.getInt("sysnotify"));
				user.setUsenotify(rs.getInt("usenotify"));
				user.setLastupdate(rs.getTimestamp("lastupdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	// 插入用户,并返回用户的uid
	public Long insertUser(SNSUserInfo userinfo){
		Long uid=null;
		
		Connection conn = C3P0Utils.getConnection();
		String sql="insert into t_user(openid,nickname,headimgurl,signature,bground,lastupdate,sysnotify,usenotify) values(?,?,?,?,?,?,1,1)";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,userinfo.getOpenId());
			pstmt.setString(2,userinfo.getNickname());
			pstmt.setString(3,userinfo.getHeadImgUrl());
			pstmt.setString(4,"...");
			pstmt.setString(5,"animal.png");
			pstmt.setTimestamp(6,new Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				uid = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return uid;
	}
	
	public int updateUser(SNSUserInfo userinfo){
		int executeUpdate=0;
		
		Connection conn = C3P0Utils.getConnection();
		String sql="UPDATE t_user SET headimgurl = ?,lastupdate=? WHERE openid = ?";
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,userinfo.getHeadImgUrl());
			pstmt.setTimestamp(2,new Timestamp(new Date().getTime()));
			pstmt.setString(3,userinfo.getOpenId());
			executeUpdate = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return executeUpdate;
	}
	
		// 根据用户的uid查询用户的信息
		public User getUser(int targetUid){
			User user=null;
			Connection conn = C3P0Utils.getConnection();
			String sql="select * from t_user where uid=?";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, targetUid);
				rs=pstmt.executeQuery();
				if(rs.next()){
					user=new User();
					user.setBground(rs.getString("bground"));
					user.setHeadimgurl(rs.getString("headimgurl"));
					user.setNickname(rs.getString("nickname"));
					user.setOpenid(rs.getString("openid"));
					user.setSignature(rs.getString("signature"));
					user.setUid(rs.getInt("uid"));
					user.setSysnotify(rs.getInt("sysnotify"));
					user.setUsenotify(rs.getInt("usenotify"));
					user.setLastupdate(rs.getTimestamp("lastupdate"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return user;
		}
		
		// 为用户设置新背景
		public int setBg(String bground,int uid){
			Connection conn = C3P0Utils.getConnection();
			String sql="update t_user set bground=? where uid=?";
			PreparedStatement pstmt=null;
			int rs=-1;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bground);
				pstmt.setInt(2, uid);
				
				rs=pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return rs;
		}
		
		// 插入上传的背景图片的信息到数据库
		public int provideBg(String bgname,String path,int uid){
			Connection conn = C3P0Utils.getConnection();
			String sql="INSERT into t_bgs(bgname,bgaddress,provider) VALUES (?,?,?)";
			PreparedStatement pstmt=null;
			int rs=-1;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, bgname);
				pstmt.setString(2, path);
				pstmt.setInt(3, uid);
				
				rs=pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return rs;
		}
		
		// 查询所有bg图片信息
		public List<Bg> showAllbgs(){
			List<Bg> bgs=new ArrayList<Bg>();
			
			Connection conn = C3P0Utils.getConnection();
			String sql="SELECT * from t_bgs";
			Statement stat=null;
			ResultSet rs=null;
			try{
				stat=conn.createStatement();
				rs=stat.executeQuery(sql);
				while(rs.next()){
					Bg bg=new Bg();
					bg.setBid(rs.getInt("bid"));
					bg.setBgaddress(rs.getString("bgaddress"));
					bg.setBgname(rs.getString("bgname"));
					bg.setProvider(rs.getInt("provider"));
					
					bgs.add(bg);
				}
			} catch (Exception e) {
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
			
			return bgs;
		}
		
		/**
		 * 设置或重置通知,
		 * @param sysnotify 为负数,减一; 为零,不变;为正,加一
		 * @param usenotify 为负数,减一; 为零,不变;为正,加一
		 * @param uid
		 */
		public int dealNotify(int sysnotify,int usenotify,int uid){
			Connection conn = C3P0Utils.getConnection();
			String sql="";
			if(sysnotify<0){
				sql="update t_user set sysnotify=sysnotify-1 where uid=? and sysnotify>0 ";
			}else if(sysnotify>0){
				sql="update t_user set sysnotify=sysnotify+1 where uid=? and sysnotify>0 ";
			}
			if(usenotify<0){
				sql="update t_user set usenotify=usenotify-1 where uid=? and usenotify>0 ";	
			}else if(usenotify>0){
				sql="update t_user set usenotify=usenotify+1 where uid=? and usenotify>0 ";	
			}
			
			PreparedStatement pstmt=null;
			int rs=-1;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, uid);
				
				rs=pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return rs;
		}
		

}
