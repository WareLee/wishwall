$(document).ready(function() {
	changeTypeIcon();
});

//点击更换图标事件
function changeTypeIcon(){
	$("input[name='message.mtype']").click(
		function (){
			var tnext=$(this).next();
			var tyepLabel=tnext.attr('id');
			if(tyepLabel=='sayhi_label'){
				tnext.css('background-image','url(../img/sayhi_type_yes.png)');
				$("#pray_label").css('background-image','url(../img/pray_type_no.png)');
				$("#complain_label").css('background-image','url(../img/complain_type_no.png)');
				$("#default_label").css('background-image','url(../img/default_type_no.png)');
			}else if(tyepLabel=='pray_label'){
				tnext.css('background-image','url(../img/pray_type_yes.png)');
				$("#sayhi_label").css('background-image','url(../img/sayhi_type_no.png)');
				$("#complain_label").css('background-image','url(../img/complain_type_no.png)');
				$("#default_label").css('background-image','url(../img/default_type_no.png)');
			}else if(tyepLabel=='complain_label'){
				tnext.css('background-image','url(../img/complain_type_yes.png)');
				$("#sayhi_label").css('background-image','../img/sayhi_type_no.png');
				$("#pray_label").css('background-image','url(../img/pray_type_no.png)');
				$("#default_label").css('background-image','url(../img/default_type_no.png)');
			}else if(tyepLabel=='default_label'){
				tnext.css('background-image','url(../img/default_type_yes.png)');
				$("#sayhi_label").css('background-image','../img/sayhi_type_no.png');
				$("#pray_label").css('background-image','url(../img/pray_type_no.png)');
				$("#complain_label").css('background-image','url(../img/complain_type_no.png)');
			}
			
		}
	);
}

