package bit.work.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import bit.work.shop.domain.User;
import bit.work.shop.domain.Userinfo;
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
	public Long insertUser(Userinfo userinfo){
		Long uid=null;
		
		Connection conn = C3P0Utils.getConnection();
		String sql="insert into t_user(openid,nickname,headimgurl) values(?,?,?)";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,userinfo.getOpenid());
			pstmt.setString(2,userinfo.getNickname());
			pstmt.setString(3,userinfo.getHeadimgurl());
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

}
