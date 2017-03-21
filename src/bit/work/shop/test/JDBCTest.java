package bit.work.shop.test;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import bit.work.shop.domain.User;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午6:09:30
 */
public class JDBCTest {
	
	// 查询为空时,ResultSet永不为空,只能根据ResultSet中是否有某记录确定对否为空
	@Test
	public void test() throws SQLException{
		String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/dreamwall2";
	    String username = "root";
	    String password = "1234";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    User user=null;
	    String sql="select * from t_user where openid = ?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, "2222222");
		ResultSet rs=stmt.executeQuery();
		System.out.println(rs);
		if(rs.next()){
			user=new User();
			user.setBground(rs.getString("bground"));
			user.setHeadimgurl(rs.getString("headimgurl"));
			user.setNickname(rs.getString("nickname"));
			user.setOpenid(rs.getString("openid"));
			user.setSignature(rs.getString("signature"));
			user.setUid(rs.getInt("uid"));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		System.out.println(user);
	    
	}
	
	// 插入记录返回主键
	@Test
	public void test2() throws SQLException{
		String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/dreamwall2";
	    String username = "root";
	    String password = "1234";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    String sql="insert into t_user(openid,nickname,headimgurl) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, "hihiih");
		pstmt.setString(2, "ozoz");
		pstmt.setString(3, "defaultha.png");
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
		Long id = rs.getLong(1);
		System.out.println("数据主键：" + id); 
		}
	}

}
