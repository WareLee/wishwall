<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>许愿墙</title>
</head>
<link rel="icon" href="aa.ico" type="image/x-icon" />
<link rel="shortcut icon" href="aa.ico">
<link rel="Bookmark" href="aa.ico">
<link rel="stylesheet" href="../css/public.css" />
<link rel="stylesheet" href="../css/index.css" />
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="../js/public.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<style type="text/css">
body {

	background-image: url(../img/default_bg.png);
	background-repeat:repeat-x repeat-y;
	background-size:100% 40%;
	background-attachment: fixed;
}

</style>
<body>
	<header>
		<div id="head_container">
			<div id="firstimg">
				<a href="userPage_getTargetHome.action?targetUid=${sessionScope.curuser.uid}"><img src="${sessionScope.curuser.headimgurl }" alt="${sessionScope.curuser.nickname }" ></a>
			</div>
			<div id="signature">
				<p>${sessionScope.curuser.signature }</p>
			</div>
		</div>
	</header>

	<div>
		<section id="mosthot">
			<h2 id="hottitle" class="title">本周热门</h2>
			<s:iterator value="hotMess" var="mess" status="st">
				<s:if test="#st.index>1">
					<section class="messSection" style="display:none">
				</s:if>
				<s:else>
					<section class="messSection">
				</s:else>
				<div class="headimg">
					<s:if test="#mess.anonymity>0">
						<a><img src='../img/ni_img.png' /></a>
					</s:if>
					<s:else>
						<a href='userPage_getTargetHome.action?targetUid=<s:property value="uid" />'><img
							src='<s:property value="headimgurl"/>' /></a>
					</s:else>
				</div>
				<s:if test="#mess.mtype=='pray'">
					<blockquote class="messBlock prayimg pointimg">
				</s:if>
				<s:elseif test="#mess.mtype=='complain'">
					<blockquote class="messBlock complainimg pointimg">
				</s:elseif>
				<s:else>
					<blockquote class="messBlock sayhiimg pointimg">
				</s:else>

				<s:if test="#mess.anonymity>0">
					<b class="from">From:<s:property value="fromname" /></b>
				</s:if>
				<s:else>
					<b class="from">From:<s:property value="nickname" /></b>
				</s:else>

				<p class="message">
					<s:property value="content" />
				</p>

				<b class="to">TO:<s:property value="toyou" /></b>
				<time datetime='<s:property value="timestamp" />' >
					<s:property value="simpleTime" />
				</time>
				</blockquote>
				<div class="zan">
					<s:if test="#mess.sstate>0">
						<span><s:property value="zantimes" />赞</span>
						<img src="../img/heart_red.png" mid=<s:property value="mid" />>
					</s:if>
					<s:else>
						<span><s:property value="zantimes" />赞</span>
						<img src="../img/heart_white.png" mid=<s:property value="mid" />>
					</s:else>
				</div>
		</section>
		</s:iterator>

		<div id="moreHot" class="more">
			<span>︾</span>
		</div>

		</section>

		<section id="weekNew">
			<h2 id="newtitle" class="title">本周最新</h2>
			<s:iterator value="messagePage.messList" var="mess">
				<section class="messSection">
					<div class="headimg">
						<s:if test="#mess.anonymity>0">
							<a><img src="../img/ni_img.png" /></a>
						</s:if>
						<s:else>
							<a href='userPage_getTargetHome.action?targetUid=<s:property value="uid" />'><img
								src="<s:property value="headimgurl"/>" /></a>
						</s:else>
					</div>
					<s:if test="#mess.mtype=='pray'">
					<blockquote class="messBlock prayimg pointimg">
					</s:if>
					<s:elseif test="#mess.mtype=='complain'">
						<blockquote class="messBlock complainimg pointimg">
					</s:elseif>
					<s:else>
						<blockquote class="messBlock sayhiimg pointimg">
					</s:else>
					
						<s:if test="#mess.anonymity>0">
							<b class="from">From:<s:property value="fromname" /></b>
						</s:if>
						<s:else>
							<b class="from">From:<s:property value="nickname" /></b>
						</s:else>

						<p class="message">
							<s:property value="content" />
						</p>

						<b class="to">TO:<s:property value="toyou" /></b>
						<time datetime='<s:property value="timestamp" />'>
							<s:property value="simpleTime" />
						</time>
					</blockquote>
					<div class="zan">
						<s:if test="#mess.sstate>0">
							<span><s:property value="zantimes" />赞</span>
							<img src="../img/heart_red.png" mid=<s:property value="mid" />>
						</s:if>
						<s:else>
							<span><s:property value="zantimes" />赞</span>
							<img src="../img/heart_white.png" mid=<s:property value="mid" />>
						</s:else>
					</div>
				</section>
			</s:iterator>
		</section>
		<s:if test="messagePage.havemore>0">
			<div id="moreNewst" class="more">
				<span messagepage.zongshu=${messagePage.zongshu } messagepage.yema=${messagePage.yema } messagepage.yechang=${messagePage.yechang }>︾</span>
			</div>
		</s:if>
		<s:else>
			<div class="more">没有更多了</div>
		</s:else>

		<div class="more" style="display: none" id="theend">没有更多了</div>
	</div>

	<!-- 模板代码  -->
	<div style="display: none" id="template">
		<section class="messSection">
			<div class="headimg">
				<a href="TARGETUID"><img src="HEADIMGURL"></a>
			</div>
			<blockquote class="messBlock PRAYIMG pointimg">
				<b class="from">From:FROM</b>
				<p class="message">CONTENT</p>
				<b class="to">TO:DESTINATION</b>
				<time datetime="">SIMPLETIME</time>
			</blockquote>
			<div class="zan">
				<span>ZAN赞</span>
				<img src="HEART" mid="MID">		
			</div>
		</section>
	</div>

	<footer> </footer>

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
</body>

</html>