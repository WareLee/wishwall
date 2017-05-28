$(document).ready(function() {
	getMoreSelfInf();
	deleteMess();
});

function deleteit(mid,obj){
	layer.open({
    content: '确定要删除吗？'
    ,btn: ['确定', '不要']
    ,yes: function(index){
      layer.close(index);
      $.ajax({
					url: 'enter/messPage_deleteMess.action?targetMid='+mid,     //要访问的service路径
					type: "get",        //访问实现的方式:get 或 post
					async:true,      //是否异步
					cache:false,      //是否缓存
					success:function(result){    //请求成功后执行
						obj.css('display','none'); 
						//window.location.reload();
						//刷新当前页面.
					}
				});
    },
    no:function(index){
    	layer.close(index);
    }
  });
}

function getMoreSelfInf(){
	$("#moreSelfInf>span").click(
		function(){
			// ....
		}
	);
}

function deleteMess(event){
	$("button.del").live("click",function(){
		var mid=$(this).children('img').eq(0).attr("mid");
		var obj= $(this).closest('.messSection');
		deleteit(mid,obj);
	});
}

