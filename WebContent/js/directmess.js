$(document).ready(function() {
	// 修正显示键盘时,choosetop值重新计算的问题
	var height=$('#from').offset().top;
	var left=$('#from').offset().left;
	var width=$('#from').width();
	 $("div.choose").css('top',height);
	 $("div.choose").css('left',left+width+5);
	changeTypeIcon();
	changeChooseIcon();
});
//是否匿名
function changeChooseIcon(){
	 var toggle=0;
	 var name=$('#from').val();
	 $("div.choose").addClass('bg1'); // 初始化时添加背景1
	 $("div.choose").click(function() {
		 if(toggle%2==0){
			 $(this).removeClass('bg1');
			 $(this).addClass('bg2');
			 document.getElementById("from").readOnly = false;
			 $("#from").val('');
			 $("#from").focus();
		 }else{
			 $(this).removeClass('bg2');
			 $(this).addClass('bg1');
			 $('#from').val(name);
			 document.getElementById("from").readOnly = true;
		 }
		 toggle=toggle+1;
	   
	 });
}

//点击更换图标事件
function changeTypeIcon(){
	var tt=4;
	$("input[name='message.mtype']").click(
		function (){
			var tnext=$(this).next();
			var tyepLabel=tnext.attr('id');
			if(tyepLabel=='sayhi_label'){
				$("#sayhi_label").attr('class','radio sayhi');
				$("#pray_label").attr('class','radio prayno');
				$("#complain_label").attr('class','radio complainno');
				$("#default_label").attr('class','radio defaultno');
			}else if(tyepLabel=='pray_label'){
				$("#sayhi_label").attr('class','radio sayhino');
				$("#pray_label").attr('class','radio pray');
				$("#complain_label").attr('class','radio complainno');
				$("#default_label").attr('class','radio defaultno');
			}else if(tyepLabel=='complain_label'){
				$("#sayhi_label").attr('class','radio sayhino');
				$("#pray_label").attr('class','radio prayno');
				$("#complain_label").attr('class','radio complain');
				$("#default_label").attr('class','radio defaultno');
			}else if(tyepLabel=='default_label'){
				$("#sayhi_label").attr('class','radio sayhino');
				$("#pray_label").attr('class','radio prayno');
				$("#complain_label").attr('class','radio complainno');
				$("#default_label").attr('class','radio default');
			}
			
		}
	);
}

