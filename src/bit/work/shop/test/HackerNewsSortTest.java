package bit.work.shop.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import org.junit.Test;

import bit.work.shop.utils.HackerNewsSort;
import bit.work.shop.utils.ValueComparator;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午8:25:14
 */
public class HackerNewsSortTest {
	@Test
	public void treemapcontaintTest(){
		TreeMap<Integer,Double> mids=new TreeMap<>();
		mids.put(12, 23.0);
		mids.put(2, 23.0);
		mids.put(4, 23.0);
		mids.put(15, 23.0);
		mids.put(8, 23.0);
		mids.put(9, 23.0);
		
		System.out.println(mids.containsKey(12));
		System.out.println(mids.containsKey(4));
		System.out.println(mids.containsKey(5));
		System.out.println(mids.containsKey(17));
		System.out.println(mids.containsKey(90));
	}
	
	@Test
	public void getLastWeek(){
		int s=(int)8.2;
		System.out.println(s);
		System.out.println((int)(Math.random()*24));
		//		System.out.println(HackerNewsSort.getLastWeek());
	}
	
	@Test
	public void hackerNewsScoreTest() throws ParseException{
		TreeMap<Integer,Double> mids=new TreeMap<>();
		mids.put(12, 23.0);
		mids.put(2, 23.0);
		mids.put(4, 23.0);
		mids.put(15, 23.0);
		mids.put(8, 23.0);
		mids.put(9, 23.0);
		
		String sql="SELECT m.*,z.sstate FROM ("
				+ "SELECT me.*,us.nickname,us.headimgurl FROM ("
				+ "select * from t_message where mid in(";
		
		// 组装sql
		for(Integer mid:mids.keySet()){
			sql = sql+mid+",";
		}
		sql=sql.substring(0,sql.length()-1);
		sql += ") )as me,t_user us WHERE me.uid=us.uid )AS m "
				+ " LEFT JOIN (SELECT * FROM t_zan WHERE uid=1) AS z ON m.mid=z.mid";
		
		System.out.println(sql);
		
		
		
		
		DecimalFormat accuracy=new DecimalFormat("0.00000");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");  
	    Date date = dateFormat.parse("2017-3-7 13:24:16"); 
		
		Timestamp start=new Timestamp(date.getTime());
		Timestamp end=new Timestamp(new Date().getTime());
		System.out.println(start.toString());
		System.out.println(end.toString());
		System.out.println(HackerNewsSort.hackerScore(3, start, end, 1.5, accuracy));
	}
	
	
	@Test
	public void mapTest(){
		HashMap<Integer,Double> map = new HashMap<Integer,Double>();  
        ValueComparator bvc =  new ValueComparator(map);  
        TreeMap<Integer,Double> sorted_map = new TreeMap<Integer,Double>(bvc);  
  
        map.put(3,99.5);  
        map.put(1,67.4);  
        map.put(9,67.4);  
        map.put(2,67.3);  
        System.out.println("unsortedDecimalFormat map: "+map);  
        
        sorted_map.putAll(map);  
  
        System.out.println("results: "+sorted_map); 
		
		
		
//		Map<Integer, Double> tree = new TreeMap<Integer, Double>(
//                new Comparator<Integer>() {
//                    public int compare(Integer obj1, Integer obj2) {
//                        // 降序排序
//                        return obj2-obj1;
//                    }
//                });
//		
//		tree.put(3, 22.1);
//		tree.put(1, 2.1);
//		tree.put(7, 4.1);
//		tree.put(2, 54.1);
//		tree.put(4, 27.1);
//		tree.put(2, 20.1);
//		
//		for (Integer key:tree.keySet()) {
//			System.out.println(key+"---"+tree.get(key));
//		}
//		ValueComparator bvc =  new ValueComparator(map);
//		TreeMap<Integer,Double> tree=new TreeMap<>();
//		
//		tree.put(3, 22.1);
//		tree.put(1, 2.1);
//		tree.put(7, 4.1);
//		tree.put(2, 54.1);
//		tree.put(4, 27.1);
//		tree.put(2, 20.1);
//		
//		for (Integer key:tree.keySet()) {
//			System.out.println(key+"---"+tree.get(key));
//		}
		
		
	}
	
}
//class ValueComparator implements Comparator<Integer> {  
//	  
//    Map<Integer, Double> base;  
//    public ValueComparator(Map<Integer, Double> base) {  
//        this.base = base;  
//    }  
//  
//    public int compare(Integer a, Integer b) {  
//        if (base.get(a) >= base.get(b)) {  
//            return -1;  
//        } else {  
//            return 1;  
//        }   
//    }  
//}  
