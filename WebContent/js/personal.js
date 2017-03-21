$(document).ready(function() {
//	backAndRefresh();
	getMoreSelfInf();
	deleteMess();
});


function getMoreSelfInf(){
	$("#moreSelfInf>span").click(
		function(){
			// ....
		}
	);
}

function deleteMess(){
	$(".messBlock>img").click(
			function(){
				var mid=$(this).attr("mid");
				var messNum=parseInt($("#messNum").text());
				$("#messNum").text((messNum-1)+"个愿望");
				$(this).parent().parent('.messSection').css('display','none');
				$.ajax({
					url: 'enter/messPage_deleteMess.action?targetMid='+mid,     //要访问的service路径
					type: "get",        //访问实现的方式:get 或 post
					async:true,      //是否异步
					cache:false,      //是否缓存
					success:function(result){    //请求成功后执行
//						 window.location.reload();//刷新当前页面.
					}
				});
			}
	);
}

//function backAndRefresh(){
//    var counter = 0;
//    if (window.history && window.history.pushState) {
//             $(window).on('popstate', function () {
//            	 window.location.href = document.referrer;
//             });
//      }
//
//      window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
//      window.history.forward(1);
//}
