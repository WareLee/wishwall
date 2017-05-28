<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>管理员</title>
</head>
<link rel="icon" href="../img/aa.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../img/aa.ico">
<link rel="Bookmark" href="../img/aa.ico">
<link rel="stylesheet" href="../css/public.css" />
<link rel="stylesheet" href="../css/index.css" />
<link rel="stylesheet" href="../css/personal.css" />

<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/pullToRefresh.css">
<script src="../js/ca-pub-4168987395515775.js"></script>
<script src="../js/iscroll.js"></script>
<script src="../js/pullToRefresh.js"></script>

<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="../js/public.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript" src="../js/layer.js"></script>
<script type="text/javascript" src="../js/personal.js"></script>
<style type="text/css">
	.head_bg{
		background-image: url(../img/${sessionScope.curuser.bground });
		background-repeat: no-repeat;
		background-size: 100% 100%;
	}
	
	.praylea{
		<%
			int i=(int)(Math.random()* 4);
			pageContext.setAttribute("random",new Integer(i));
		%>
		background-color: rgba(88,147,106,0.8);
		background-image: url(../img/${pageScope.random}pray.png);            
		background-position:top 15% left 25%;        
		background-size: contain;                                  
	}
	.praylea:after{
		border-color: transparent rgba(88,147,106,0.8) transparent transparent;
	}
	
	.sayhilea{
		<%
			int j=(int)(Math.random()* 4);
			pageContext.setAttribute("random",new Integer(j));
		%>
		background-color: rgba(255,96,66,0.8);
		background-image: url(../img/${pageScope.random}sayhi.png);  
		background-position:top 15% left 25%;        
		background-size: contain;                                 
	
	}
	.sayhilea:after{
		border-color: transparent rgba(255,96,66,0.8) transparent transparent;
	}
	
	.complainlea{
		<%
			int m=(int)(Math.random()* 4);
			pageContext.setAttribute("random",new Integer(m));
		%>
		background-color: rgba(121,101,87,0.8);
		background-image: url(../img/${pageScope.random}complain.png);  
		background-position:top 15% left 25%;        
		background-size: contain;                                 
	
	}
	.complainlea:after{
		border-color: transparent rgba(121,101,87,0.8) transparent transparent;
	}
	
	.defaultlea{
		<%
			int n=(int)(Math.random()* 4);
			pageContext.setAttribute("random",new Integer(n));
		%>
		background-color: rgba(255,215,60,0.8);
		background-image: url(../img/${pageScope.random}default.png);  
		background-position:top 15% left 25%;        
		background-size: contain;                                 
	
	}
	.defaultlea:after{
		border-color: transparent rgba(255,215,60,0.8) transparent transparent;
	}
</style>

<body>
<div id="wrapper" style="overflow: hidden;">
	<div class="scroller" >
		<div class="pullDown" id="" style="line-height: 40px;">
				<div class="loader" style="display: none;"><span></span><span></span><span></span><span></span></div>
				<div class="pullDownLabel">Pull down to refresh...</div>
		</div>
		<header>
			<div id="head_container" class="head_bg" onclick="window.location.href='homePage_showBgs.action';">
				<div id="firstimg">
					<a href="userPage_getTargetHome.action?targetUid=${sessionScope.curuser.uid}"><img src="${sessionScope.curuser.headimgurl }" alt="${sessionScope.curuser.nickname }" ></a>
				</div>
				<div id="changebg"><a href="homePage_showBgs.action">&nbsp;&nbsp;点击可修改背景哟&nbsp;&nbsp;</a></div>
			</div>
		</header>
	<div>
	<section id="mosthot">
			<s:if test="hotMess!=null && hotMess.size>0" >
				<p id="hottitle" class="title">本周热门</p>
				<div class="line"></div>
				<s:iterator value="hotMess" var="mess" status="st">
					<section class="messSection">
						<div class="headimg">
							<s:if test="#mess.anonymity>0">
								<a><img src='../img/ni_img.png' /></a>
							</s:if>
							<s:else>
								<a href='userPage_getTargetHome.action?targetUid=<s:property value="uid" />'>
								<img src='<s:property value="#mess.headimgurl"/>' /></a>
							</s:else>
						</div>
						<s:if test="#session.curuser.bground=='wish.png'">
							<s:if test="#mess.mtype=='pray'">
								<blockquote class="messBlock prayimg pointimg">
							</s:if>
							<s:elseif test="#mess.mtype=='complain'">
								<blockquote class="messBlock complainimg pointimg">
							</s:elseif>
							<s:elseif test="#mess.mtype=='sayhi'">
								<blockquote class="messBlock sayhiimg pointimg">
							</s:elseif>
							<s:else>
								<blockquote class="messBlock defaultimg pointimg">
							</s:else>
						</s:if>
						<s:elseif test="#session.curuser.bground=='animal.png'">
							<s:if test="#mess.mtype=='pray'">
								<blockquote class="messBlock pray_color pointimg">
							</s:if>
							<s:elseif test="#mess.mtype=='complain'">
								<blockquote class="messBlock complain_color pointimg">
							</s:elseif>
							<s:elseif test="#mess.mtype=='sayhi'">
								<blockquote class="messBlock sayhi_color pointimg">
							</s:elseif>
							<s:else>
								<blockquote class="messBlock default_color pointimg">
							</s:else>
						</s:elseif>
						<s:else>
							<s:if test="#mess.mtype=='pray'">
								<blockquote class="messBlock praylea pointimg">
							</s:if>
							<s:elseif test="#mess.mtype=='complain'">
								<blockquote class="messBlock complainlea pointimg">
							</s:elseif>
							<s:elseif test="#mess.mtype=='sayhi'">
								<blockquote class="messBlock sayhilea pointimg">
							</s:elseif>
							<s:else>
								<blockquote class="messBlock defaultlea pointimg">
							</s:else>
						</s:else>
							<button type="button" class="del" style="border:none;background:none">
								<img src="../img/delete.png" mid=<s:property value="#mess.mid"/>>
							</button>
							
								<p class="to">TO:<s:property value="#mess.toyou" /></p>
								<p class="message"><s:property value="#mess.content" /></p>
								<s:if test="#mess.anonymity>0">
		                        <p class="from">From:<s:property value="#mess.fromname" /></p>
		                        </s:if>
		                        <s:else>
		                        <p class="from">From:<s:property value="#mess.nickname" /></p>
		                        </s:else>
		                        
								<time datetime=""><s:property value="simpleTime" /></time>
							</blockquote>
						<s:if test="#session.curuser.bground=='animal.png'">
							<s:if test="#mess.mtype=='pray'">
								<div class="pray_bg"><img src="../img/bear_h.png"></div>
							</s:if>
							<s:elseif test="#mess.mtype=='complain'">
								<div class="complain_bg"><img src="../img/mao.png"></div>
							</s:elseif>
							<s:elseif test="#mess.mtype=='sayhi'">
								<div class="sayhi_bg"><img src="../img/bear_b.png"></div>
							</s:elseif>
							<s:else>
								<div class="default_bg"><img src="../img/bear_y.png"></div>
							</s:else>
						</s:if>
								<div class="zan">
									<span><s:property value="#mess.zantimes" />赞</span>
									<button type="button"  style="border:none;background:none">
									<s:if test="#mess.sstate>0">
										<img src="../img/heart_red.png" style="z-index:2;" mid=<s:property value="#mess.mid" />>
									</s:if>
									<s:else>
										<img src="../img/heart_white.png" style="z-index:2;" mid=<s:property value="#mess.mid" />>
									</s:else>
									</button>
								</div>
					</section>
				</s:iterator>
			</s:if>
		</section>
				
		<section id="weekNew">
				<s:if test="messagePage.messList !=null && messagePage.messList.size>0" >
				<p id="newtitle" class="title">本周最新</p>
				<div class="line"></div>
				<s:iterator value="messagePage.messList" var="mess">
					<section class="messSection">
						<div class="headimg">
							<s:if test="#mess.anonymity>0">
								<a><img src='../img/ni_img.png' /></a>
							</s:if>
							<s:else>
								<a href='userPage_getTargetHome.action?targetUid=<s:property value="uid" />'>
								<img src='<s:property value="#mess.headimgurl"/>' /></a>
							</s:else>
						</div>
						<s:if test="#session.curuser.bground=='wish.png'">
									<s:if test="#mess.mtype=='pray'">
										<blockquote class="messBlock prayimg pointimg">
									</s:if>
									<s:elseif test="#mess.mtype=='complain'">
										<blockquote class="messBlock complainimg pointimg">
									</s:elseif>
									<s:elseif test="#mess.mtype=='sayhi'">
										<blockquote class="messBlock sayhiimg pointimg">
									</s:elseif>
									<s:else>
										<blockquote class="messBlock defaultimg pointimg">
									</s:else>
						</s:if>
						<s:elseif test="#session.curuser.bground=='animal.png'">
									<s:if test="#mess.mtype=='pray'">
										<blockquote class="messBlock pray_color pointimg">
									</s:if>
									<s:elseif test="#mess.mtype=='complain'">
										<blockquote class="messBlock complain_color pointimg">
									</s:elseif>
									<s:elseif test="#mess.mtype=='sayhi'">
										<blockquote class="messBlock sayhi_color pointimg">
									</s:elseif>
									<s:else>
										<blockquote class="messBlock default_color pointimg">
									</s:else>
						</s:elseif>
						<s:else>
							<s:if test="#mess.mtype=='pray'">
								<blockquote class="messBlock praylea pointimg">
							</s:if>
							<s:elseif test="#mess.mtype=='complain'">
								<blockquote class="messBlock complainlea pointimg">
							</s:elseif>
							<s:elseif test="#mess.mtype=='sayhi'">
								<blockquote class="messBlock sayhilea pointimg">
							</s:elseif>
							<s:else>
								<blockquote class="messBlock defaultlea pointimg">
							</s:else>
						</s:else>
							<button type="button" class="del" style="border:none;background:none">
								<img src="../img/delete.png" mid=<s:property value="#mess.mid"/>>
							</button>
		                        <p class="to">TO:<s:property value="#mess.toyou" /></p>
								<p class="message"><s:property value="#mess.content" /></p>
							<s:if test="#mess.anonymity>0">
							    <p class="from">From:<s:property value="#mess.fromname" /></p>
							</s:if>
							<s:else>
							    <p class="from">From:<s:property value="#mess.nickname" /></p>
							</s:else>
								<time datetime=""><s:property value="simpleTime" /></time>
							</blockquote>
							
							<div class="zan">
								<span><s:property value="#mess.zantimes" />赞</span>
								<button type="button"  style="border:none;background:none">
								<s:if test="#mess.sstate>0">
									<img src="../img/heart_red.png" mid=<s:property value="#mess.mid" />>
								</s:if>
								<s:else>
									<img src="../img/heart_white.png" mid=<s:property value="#mess.mid" />>
								</s:else>
								</button>
							</div>
					</section>
				</s:iterator>
			</s:if>
		
		</section>

	</div>

<div class="pullUp" style="display: block; line-height: 40px;">
				<div class="loader" style="display: none;"><span></span><span></span><span></span><span></span></div>
				<s:if test="messagePage.havemore>0">
				<div class="pullUpLabel" >Pull up to load more...</div>
				</s:if>
				<s:else>
					<div class="pullUpLabel">No more</div>
				</s:else>
				<div class="pullUpLabel" id='theend' style="display:none;">No more</div>
		</div>
	</div>
</div>

<div style="display:none" id="hid" messagepage.zongshu=${messagePage.zongshu } messagepage.yema=${messagePage.yema } messagepage.yechang=${messagePage.yechang }></div>

	<!-- 模板代码 -->
	<div style="display: none" id="template">
		<section class="messSection">
			<div class="headimg">
				<a href="TARGETUID"><img src="HEADIMGURL"></a>
			</div>
			<blockquote class="messBlock PRAYIMG pointimg">
				<button type="button" class="del" style="border:none;background:none">
					<img src="../img/delete.png" mid="MID" >
				</button>
                <p class="to">TO:DESTINATION</p>
				<p class="message">CONTENT</p>
				<p class="from">From:FROM</p>
				<time datetime="">SIMPLETIME</time>
			</blockquote>
			<s:if test="#session.curuser.bground=='animal.png'">
				<div class="BG"><img src="../img/PIC"></div>
			</s:if>
				<div class="zan">
				<span>ZAN赞</span>
				<button type="button"  style="border:none;background:none">
					<img src="../img/HEART" mid="MID">
				</button>
			</div>
		</section>
	</div>

	<div id="btn" class="rotate">
		<img src="../img/btn.png" />
	</div>

	<div id="mask" class="fade">
		<div id="topray" class="topray">
			<a href="homePage_toPray.action"><span>&nbsp;我要许愿&nbsp;</span><img
				src="../img/dopray.png" alt="pray" /></a>
		</div>
		<div id="shake" class="shake">
			<a href="homePage_toShake.action"><span>&nbsp;摇一摇&nbsp;</span><img
				src="../img/shake.png" alt="shake" /></a>
		</div>
	</div>

<script type="text/javascript">
    
        refresher.init({
            id: "wrapper",//<------------------------------------------------------------------------------------┐
            pullDownAction: Refresh,
            pullUpAction: Load
        });
        var generatedCount = 0;
        function Refresh() {
            setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!  
              // window.location.href="../index.jsp";
              window.location.reload();            
               wrapper.refresh();
            }, 1000);

        }

        function Load() {
            setTimeout(function () {// <-- Simulate network congestion, remove setTimeout from production!                
                getMoreNewst();
                wrapper.refresh();/****remember to refresh after action completed！！！   ---id.refresh(); --- ****/
            }, 1000);
        }
        
        function getMoreNewst(){
		// 前端需要告诉后台当前页码,页面长
		var zongshu = $("#hid").attr("messagepage.zongshu");
		var yema=$("#hid").attr("messagepage.yema");
		var yechang=$("#hid").attr("messagepage.yechang");
		if((zongshu-yema*yechang)>0 ){
			$.ajax({
				url: 'homePage_getMoreMess.action?messagePage.zongshu='+zongshu+"&messagePage.yema="+yema+"&messagePage.yechang="+yechang,     //要访问的service路径
				type: "get",        //访问实现的方式:get 或 post
				async:false,      //是否异步
				cache:false,      //是否缓存
				success:function(result){    //请求成功后执行
					for (var i=0;i<result['messList'].length;i++){
						var template=$("#template").html();
						if(result['messList'][i]['anonymity']==0){
							template=template.replace("TARGETUID",'../enter/userPage_getTargetHome.action?targetUid='+result['messList'][i]['uid']);
							template=template.replace("HEADIMGURL",result['messList'][i]['headimgurl']);
						}else{
							template=template.replace('TARGETUID','javascript:void(0)');// 移除href属性
							template=template.replace("HEADIMGURL","../img/ni_img.png");
						}
						if(result['messList'][i]['mtype']=='complain'){
						<s:if test="#session.curuser.bground=='animal.png'">
							template=template.replace("PRAYIMG","complain_color");
						</s:if>
						<s:elseif test="#session.curuser.bground=='wish.png'">
							template=template.replace("PRAYIMG","complainimg");
						</s:elseif>
						<s:else>
							template=template.replace("PRAYIMG","complainlea");
						</s:else>
						
							template=template.replace("BG","complain_bg");
							template=template.replace("PIC","mao.png");
							template=template.replace("CLASS","complainico");
						}else if(result['messList'][i]['mtype']=='pray'){
						<s:if test="#session.curuser.bground=='animal.png'">
							template=template.replace("PRAYIMG","pray_color");
						</s:if>
						<s:elseif test="#session.curuser.bground=='wish.png'">
							template=template.replace("PRAYIMG","prayimg");
						</s:elseif>
						<s:else>
							template=template.replace("PRAYIMG","praylea");
						</s:else>
							template=template.replace("BG","pray_bg");
							template=template.replace("PIC","bear_h.png");
							template=template.replace("ICO","pray.png");
							template=template.replace("CLASS","prayico");
						}else if(result['messList'][i]['mtype']=='sayhi'){
						<s:if test="#session.curuser.bground=='animal.png'">
							template=template.replace("PRAYIMG","sayhi_color");
						</s:if>
						<s:elseif test="#session.curuser.bground=='wish.png'">
							template=template.replace("PRAYIMG","sayhiimg");
						</s:elseif>
						<s:else>
							template=template.replace("PRAYIMG","sayhilea");
						</s:else>
						
							template=template.replace("BG","sayhi_bg");
							template=template.replace("PIC","bear_b.png");
							template=template.replace("ICO","sayhi.png");
							template=template.replace("CLASS","sayhiico");
						}else{
						<s:if test="#session.curuser.bground=='animal.png'">
							template=template.replace("PRAYIMG","default_color");
						</s:if>
						<s:elseif test="#session.curuser.bground=='wish.png'">
							template=template.replace("PRAYIMG","defaultimg");
						</s:elseif>
						<s:else>
							template=template.replace("PRAYIMG","defaultlea");
						</s:else>
							template=template.replace("BG","default_bg");
							template=template.replace("PIC","bear_y.png");
							template=template.replace("ICO","default.png");
							template=template.replace("CLASS","defaultico");
						
						}
						template=template.replace("FROM",result['messList'][i]['fromname']);
						template=template.replace("CONTENT",result['messList'][i]['content']);
						template=template.replace("DESTINATION",result['messList'][i]['toyou']);
						template=template.replace("ZAN",result['messList'][i]['zantimes']);
						template=template.replace("SIMPLETIME",result['messList'][i]['simpleTime']);
						template=template.replace("MID",result['messList'][i]['mid']);
						template=template.replace("MID",result['messList'][i]['mid']);
						if(result['messList'][i]['sstate']==0){
							template=template.replace("HEART","heart_white.png");	
						}else{
							template=template.replace("HEART","heart_red.png");	
						}
						
						$("#weekNew").append(template);
					}
					$("#hid").attr("messagepage.zongshu",result['zongshu']);
					$("#hid").attr("messagepage.yema",result['yema']);
					$("#hid").attr("messagepage.yechang",result['yechang']);
					
					if(result['havemore']<=0){
						$(".pullUpLabel").css("display","none");
						$("#theend").css("display","block");
					}
				}
			});
			
		}else{
			$(".pullUpLabel").css("display","none");
			$("#theend").css("display","block");
		}
	
}
    </script>
</body>
</html>