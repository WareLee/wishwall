<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>${targetUser.nickname }的空间</title>
	</head>
	<link rel="stylesheet" href="../css/public.css" />
	<link rel="stylesheet" href="../css/personal.css" />
	<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>
	<script type="text/javascript" src="../js/personal.js"></script>
	<body>
		<header>
			<div id="head_container">
				<div id="firstimg">
					<a><img src="${targetUser.headimgurl }" alt="${targetUser.nickname }" /></a>
				</div>
				<div id="someInf">
					<span id="nickname">ID:${targetUser.nickname }</span>
					<span id="messNum">${size }个愿望</span>
				</div>
			</div>
		</header>
		
		<div>
			<section>
			<s:iterator value="targetMess" var="mess" status="st">
				<section class="messSection">
					<div class="headimg">
					<s:if test="#mess.anonymity>0">
							<a><img src="../img/ni_img.png" /></a>
					</s:if>
					<s:else>
						<a><img src="<s:property value="headimgurl"/>" ></a>
					</s:else>
					</div>	
					<blockquote class="messBlock prayimg pointimg">
						<s:if test="self">
							<img src="../img/delete.png" mid=<s:property value="mid"/>>
						</s:if>
						
						<s:if test="#mess.anonymity>0">
						<b class="from">From:<s:property value="fromname" /></b>
						</s:if>
						<s:else>
						<b class="from">From:<s:property value="nickname" /></b>
						</s:else>
						<p class="message">
							<s:property value="content"/>
						</p>
						<b class="to">TO:<s:property value="toyou"/></b>
						<time datetime="<s:property value="timestamp"/>"><s:property value="simpleTime"/></time>
					</blockquote>
					<div class="zan">
					<s:if test="#mess.sstate>0">
						<span><s:property value="zantimes" />赞</span><img src="../img/heart_red.png" mid=<s:property value="mid" /> >
					</s:if>
					<s:else>
						<span><s:property value="zantimes" />赞</span><img src="../img/heart_white.png" mid=<s:property value="mid" /> >
					</s:else>
					</div>
				</section>
			</s:iterator>
					
				
				<div id="moreSelfInf" class="more"><span>︾</span></div>
			
			</section>
		</div>
	</body>

</html>