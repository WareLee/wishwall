package bit.work.shop.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月12日下午12:29:19
 */
public class HttpKits {
	 /** 
     * 发送 get请求 , 如果Class target 为null, 返回字符串对象
     */  
	public static Object toGet(String url,Class target) throws Exception{
		Object result=null;
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 try {  
	            // 创建httpget.    
	            HttpGet httpget = new HttpGet(url);  
	            // 执行get请求.    
	            CloseableHttpResponse response = httpclient.execute(httpget);  
	            try {  
	                // 获取响应实体    
	                HttpEntity entity = response.getEntity();  
	                // 如果响应码为200, 那么请求成功
	                if(response.getStatusLine().getStatusCode() >= 400){
	                	// 小于0说明没有返回数据(返回页面不算在里面)
	                	if(entity != null && entity.getContentLength() >= 0 ){
	                		if(target==null) result=EntityUtils.toString(entity,"UTF-8");
	                		else result =TextTransfer.jsonStr2Pojo(EntityUtils.toString(entity), target);
	                	}
	                }
	                
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }
		 
		 return result;
	}
	/** 
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
     */  
    public static Object toPost(String url ,Class target , Map<String,String> params) throws Exception{
    	Object result=null;
    	
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
        // 创建参数队列    
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if(params !=null){
	        for(Entry<String, String> pair:params.entrySet()){
	        	formparams.add(new BasicNameValuePair(pair.getKey(),pair.getValue()));  
	        }
        }
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();
                
                if(response.getStatusLine().getStatusCode()==200){
                	// 小于0说明没有返回数据(返回页面不算在里面)
                	if(entity != null && entity.getContentLength() >= 0 ){
                		if(target==null) result=EntityUtils.toString(entity,"UTF-8");
                		else result =TextTransfer.jsonStr2Pojo(EntityUtils.toString(entity), target);
                	}
                }
                
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        
        return result;
    }
    
    /**
     * 模拟https请求
     * 一般使用toPost()即可
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     * @throws Exception 
     */
    public static Object toPostHttps(String url ,Class target , Map<String,String> params) throws Exception {
    	Object result=null;
    	
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = createSSLClientDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
        // 创建参数队列    
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if(params !=null){
	        for(Entry<String, String> pair:params.entrySet()){
	        	formparams.add(new BasicNameValuePair(pair.getKey(),pair.getValue()));  
	        }
        }
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();
                
                if(response.getStatusLine().getStatusCode()==200){
                	// 小于0说明没有返回数据(返回页面不算在里面)
                	if(entity != null && entity.getContentLength() >= 0 ){
                		if(target==null) result=EntityUtils.toString(entity,"UTF-8");
                		else result =TextTransfer.jsonStr2Pojo(EntityUtils.toString(entity), target);
                	}
                }
                
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        
        return result;
    }  	

    
    public static CloseableHttpClient createSSLClientDefault() throws Exception{
    	
    	SSLContext sslContext =new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){

			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				// 信任所有
				return true;
			}}).build();
    	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    	
    }
   
}
