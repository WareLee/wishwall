# wishwall
大二时候的社团项目,半匿名微社区分享平台(类似QQ空间)

配置文件:
	wx.properties---微信公众号相关设置
	
	APPID=wx139*****8e23e0 (微信公众号的appid)
	APPSECRET=9d10c796b04a*********49beb (微信公众号的secret)
	FORODEURL=(一般不变)
	FORACCESSTOKENURL=(一般不变)
	FORUSERINFOURL=(一般不变)
	REDIRECTURI=http://wishwall.duapp.com/DM_wish/enter/login_getUserinfo.action(接收微信code的请求)
	TOKEN=用于验证微信后台服务器而设置的token,要和微信后台设置一致
	
	c3p0-config---数据库连接设置
	
	jdbcUrl 个人数据库地址
	其他参数将该xml文件注释---根据实际机子(服务器)的情况设定
	
微信后台回调域名认证:
	回调域名/本应用的名称/enter/wx_authenServer.action
	
访问主页默认为index.jsp, 会自动跳转到code请求连接的,无需改变.
	
	
