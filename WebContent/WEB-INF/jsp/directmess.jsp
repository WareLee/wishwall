<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>许愿</title>
	</head>

	<link rel="icon" href="aa.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="aa.ico">
	<link rel="Bookmark" href="aa.ico">
	<link rel="stylesheet" href="../css/directmess.css" />
	<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="../js/directmess.js"></script>

	<body>
		<header>
			<div>
				<form action="messPage_sendMess.action" method="post" id="sentForm">
					<label for="to" class="to">To:</label>
					<input type="text" name="message.toyou" id="to" autofocus="autofocus"/>
					<br />
					<label for="from" class="from">From:</label>
					<input type="text" name="message.fromname" id="from" value="${curNickname }"/>
					<br />

					<textarea name="message.content" rows="5" cols="22"></textarea><br />

					<input type="radio" name="message.mtype" id="a" value="pray" class="radio"/>
					<label for="a" id="sayhi_label" class="radio">(我要表白)</label><br>
					<input type="radio" name="message.mtype" id="b" value="sayhi" class="radio"/>
					<label for="b" id="pray_label">(我要祝福)</label><br>
					<input type="radio" name="message.mtype" id="c" value="complain" class="radio"/>
					<label for="c" id="complain_label">(我要吐槽)</label><br>
					<input type="radio" name="message.mtype" id="d" value="pray" class="radio" checked="checked"/>
					<label for="d" id="default_label">(默认许愿)</label><br>

					<div id="sendMess">
						<input type="button" value="发布消息" onclick="return confirm();"/>
					</div>
				</form>
			</div>
		</header>

	<script type="text/javascript" language="javascript">
		$(document).ready(function() {
		     $("#to").blur(function(){
		    	 var to=$(this).val();
		    	 if($.trim(to) == ''){
		     		$(this).css("box-shadow","0px 0px 5px #F71414");
		     	}else{
		     		$(this).css("box-shadow","none");
		     	}
			});
			
		     $("#from").blur(function(){
		    	 var to=$(this).val();
		    	 if($.trim(to) == ''){
		     		$(this).css("box-shadow","0px 0px 5px #F71414");
		     	}else{
		     		$(this).css("box-shadow","none");
		     	}
			});
			
		     $("textarea[name='message.content']").blur(function(){
		    	 var to=$(this).val();
		    	 if($.trim(to) == ''){
		     		$(this).css("box-shadow","0px 0px 5px #F71414");
		     	}else{
		     		$(this).css("box-shadow","none");
		     	}
			});
		     
		});
		
		function confirm(){
			var to=$("#to").val();
			var from=$("#from").val();
			var content=$("textarea[name='message.content']").val();
			
			if($.trim(content) == ''){
				$("textarea[name='message.content']").css("box-shadow","0px 0px 5px #F71414");
				$("textarea[name='message.content']").focus();
				return false;
			}
			if($.trim(from) == ''){
				$("#from").css("box-shadow","0px 0px 5px #F71414");
				$("#from").focus();
				return false;
			}
			if($.trim(to) == ''){
				$("#to").css("box-shadow","0px 0px 5px #F71414");
				$("#to").focus();
				return false;
			}
			
			if($.trim(content) != '' && $.trim(from) != '' && $.trim(to) != ''){
				$("#sentForm").submit();
			}
		}
		
	</script>
	</body>

</html>