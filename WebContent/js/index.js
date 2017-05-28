$(document).ready(function() {
	operation();
});



function operation(){
	var state=false;
	$("#btn>img").click(
		function(){
			if(!state){
				$("#btn").addClass("rotate-show");
				$("#mask").css("display","block");
				
				$("#mask").addClass("fade-show");
				$("#topray>a>span").addClass("span-anima");
				$("#shake>a>span").addClass("span-anima2");
				$("#topray>a>img").addClass("icon-anima");
				$("#shake>a>img").addClass("icon-anima");
			}else{
				$("#btn").removeClass("rotate-show");
				$("#mask").removeClass("fade-show");
				$("#topray>a>span").removeClass("span-anima");
				$("#shake>a>span").removeClass("span-anima2");
				$("#topray>a>img").removeClass("icon-anima");
				$("#shake>a>img").removeClass("icon-anima");
				
				$("#mask").css("display","none");
			}
			state=!state;
			
		}
	);
	
	$("#mask").click(
		function(){
			$("#btn").removeClass("rotate-show");
			$("#mask").removeClass("fade-show");
			$("#topray>a>span").removeClass("span-anima");
			$("#shake>a>span").removeClass("span-anima2");
			$("#topray>a>img").removeClass("icon-anima");
			$("#shake>a>img").removeClass("icon-anima");
			
			$("#mask").css("display","none");
			state=!state;
		}
	);
}

