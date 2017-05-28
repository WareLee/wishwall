package bit.work.shop.action.wx;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.utils.PropertiesUtils;
import bit.work.shop.utils.WXUtils;

/**
 * 微信后台服务器地址认证
 * 
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月11日下午5:51:03
 */
public class Authentication extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	
	// 验证同微信公众号绑定的服务器地址的有效性
	public String authenServer(){
		
		String token=PropertiesUtils.getKeyValue("TOKEN");
		
		if(WXUtils.checkSignature(token, signature, timestamp, nonce)){
			try {
				ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().print(echostr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return NONE;
	}
	

	
	
	
	
	//--------------------------
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	

}
