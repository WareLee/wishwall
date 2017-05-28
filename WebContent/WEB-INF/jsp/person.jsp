<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>${targetUser.nickname }</title>
	</head>
	<link rel="icon" href="../img/aa.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="../img/aa.ico">
	<link rel="Bookmark" href="../img/aa.ico">
	<link rel="stylesheet" href="../css/public.css" />
	<link rel="stylesheet" href="../css/personal.css" />
	<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>
	<script type="text/javascript" src="../js/layer.js"></script>
	<script type="text/javascript" src="../js/personal.js"></script>
	<body>
		<header>
			<div id="head_container">
				<div id="firstimg">
					<a><img src="${targetUser.headimgurl }" alt="${targetUser.nickname }" /></a>
				</div>
				<div id="someInf">
					<span id="nickname">ID:${targetUser.nickname }</span>
					<span id="messNum">${size }个愿望</span>
				</div>
			</div>
			<div class="line"></div>
		</header>
		
		<div>
			<section>
			<s:if test="targetMess!=null && targetMess.size>0" >
				<s:iterator value="targetMess" var="mess" status="st">
					<section class="messSection">
						<div class="headimg">
							<s:if test="#mess.anonymity>0">
								<a><img src="../img/ni_img.png" /></a>
							</s:if>
							<s:else>
								<a><img src="<s:property value="#mess.headimgurl"/>" ></a>
							</s:else>
						</div>
							<s:if test="#mess.mtype=='pray'">
								<blockquote class="messBlock prayimage pointimg">
							</s:if>
							<s:elseif test="#mess.mtype=='complain'">
								<blockquote class="messBlock complainimage pointimg">
							</s:elseif>
							<s:elseif test="#mess.mtype=='sayhi'">
								<blockquote class="messBlock sayhiimage pointimg">
							</s:elseif>
							<s:else>
								<blockquote class="messBlock defaultimage pointimg">
							</s:else>
							<s:if test="#mess.state>0">
								<button type="button" class="del" style="border:none;background:none" >
									<img src="../img/delete.png" style="z-index:2;" mid=<s:property value="#mess.mid"/>>
								</button>
							</s:if>	
							<p class="to">TO:<s:property value="#mess.toyou" /></p>
							<p class="message">
								<s:property value="#mess.content"/>
							</p>
							<s:if test="#mess.anonymity>0">
	                        <p class="from">From:<s:property value="#mess.fromname" /></p>
	                        </s:if>
	                        <s:else>
	                        <p class="from">From:<s:property value="#mess.nickname" /></p>
	                        </s:else>
							
							<time datetime="<s:property value="#mess.timestamp"/>"><s:property value="#mess.simpleTime"/></time>
						</blockquote>
						<div class="zan">
							<span><s:property value="zantimes" />赞</span>
							<s:if test="#mess.sstate>0">
								<button type="button"  style="border:none;background:none">
									<img src="../img/heart_red.png" mid=<s:property value="mid" /> />
								</button>
							</s:if>
							<s:else>
								<button type="button"  style="border:none;background:none">
									<img src="../img/heart_white.png" mid=<s:property value="mid" /> />
								</button>
							</s:else>
						</div>
					</section>
				</s:iterator>
			</s:if>
				
			</section>
		</div>
	</body>

</html>