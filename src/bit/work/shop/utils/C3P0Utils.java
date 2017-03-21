package bit.work.shop.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日上午10:09:30
 */
public class C3P0Utils {
	private static Connection conn;
    private final static ComboPooledDataSource ds = new ComboPooledDataSource() ;

    public static Connection getConnection() {
    	try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        return conn;
    }
    
    public synchronized static void cleanup() { 
    	// make sure it's a c3p0 PooledDataSource 
    	if(ds != null){
    		if ( ds instanceof ComboPooledDataSource) { 
    			if(ds != null) ds.close(); 
    		} else {
    			System.err.println("Not a c3p0 PooledDataSource!"); 
    		}
    	}
    }
}
