<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>背景集汇</title>
	<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
</head>
<link rel="icon" href="../img/aa.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../img/aa.ico">
<link rel="Bookmark" href="../img/aa.ico">
<style type="text/css">

       *{margin:0;padding:0}

       body{background-color: rgb(255,254,241);}
.bg{
	width:100%;
	height:100%;
}
.bgcon{
	   width:100%;
	   margin-top:50px;
	   float:left;
	   }
.bgcon button{
	   width:29%;
	   height:auto;
	   display:block;
	   background:none;
	   border:none;
	   float:left;
	   margin: 2%;
}	  
.bgcon img{
	width:100%;
	height:auto;	       
}

</style>

<body>
	<div class="bg">
		<div class="bgcon">
			<form action="userPage_bgChoose.action" method="post" id="bgForm">
				<s:iterator value="bgs" var="bg">
					<button type="button">
						<img alt='<s:property value="bgname" />' bg='<s:property value="bgaddress" />' src='../img/<s:property value="bgaddress" />' bid='<s:property value="bid" />'>
					</button>
				</s:iterator>
				<input type="hidden" name="bground" value="" id="choosed">
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	
	 $("button").click(
	 	function(){
	 		var bground=$(this).children('img').eq(0).attr('bg');
	 		$("#choosed").val(bground);
	 		$("#bgForm").submit();
	 		}
	 	);

</script>
</html>