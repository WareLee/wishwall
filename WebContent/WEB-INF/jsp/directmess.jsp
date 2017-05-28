<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>许愿</title>
	</head>
	<link rel="icon" href="../img/aa.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="../img/aa.ico">
	<link rel="Bookmark" href="../img/aa.ico">

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
					<input name="message.toyou" id="to" autofocus="autofocus" style="box-shadow: 0px 0px 5px rgb(247, 20, 20); width:48%;height:22px;" type="text">
					<br />
					<label for="from" class="from">From:</label>
					<input name="message.fromname" id="from" readonly="true" value="${curNickname }" type="text" style="width:42%;height:22px;">
					<br>
		            <!-- 匿名按钮 -->
		            <div class="choose bg1" style="top: 107.5px;" ><p><input id="anon" type="checkbox" name="message.anonymity" value="1" style="opacity:0">匿名</p></div>
                    <!-- 用户第一次进入个人中心的提示 -->
                    <s:if test="usenotify>0" >
	                   	<div class="prompt"><p>匿名后可自定义from哟~</p></div>
                    </s:if> 
                   	<div style="position:relative;">
                            <textarea id="ipt" class="textarea" name="message.content"  onblur="if(value=='')document.getElementById('note').style.display='block'" onfocus="document.getElementById('note').style.display='none'"></textarea>
                            <div id="note" class="note">
                                 <font color="#777">快来许个愿望吧~</font>
                            </div>
                    </div>
					<br>
					
					<div class="tem">
					<input type="radio" name="message.mtype" id="a" value="sayhi" />
					<label for="a" id="sayhi_label" class="radio sayhino">我要表白</label><br>
					
					<input type="radio" name="message.mtype" id="b" value="pray" />
					<label for="b" id="pray_label" class="radio prayno">我要祝福</label><br>
					
					<input type="radio" name="message.mtype" id="c" value="complain" />
					<label for="c" id="complain_label" class="radio complainno">我要吐槽</label><br>
					
					<input type="radio" name="message.mtype" id="d" value="default" checked="checked"/>
					<label for="d" id="default_label" class="radio default">默认许愿</label><br>
					</div>

					<div id="sendMess">
						<input value="许          愿  " onclick="return confirm();" type="button">
					</div>
				</form>
			</div>
		</header>

	<script type="text/javascript" language="javascript">
		$(document).ready(function() {
			if($('#ipt').val()!=''){
				$('#note').css('display','none');
			}
			if($('#from').val()!='${curNickname }'){
				$('div.choose').removeClass('bg1').addClass('bg2');
			}
			
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