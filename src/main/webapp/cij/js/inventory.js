var inventoryScriptUrl = (function() {
	if (document.currentScript) {
		return document.currentScript.src;
	} else {
		var scripts = document.getElementsByTagName('script'),
	    script = scripts[scripts.length - 1];
		if (script.getAttribute.length !== undefined) {
			return script.src;
		}
		return script.getAttribute('src', -1);
	}
})();

var baseUrl = "";
var standardId="";

function getsum(classname, goodsId) {
	$.ajax({
		type : "get",
		url : baseUrl+"/inventory/sum.json",
		data : {
			"goodsId" : goodsId
		},
		dataType : "json",
		success : function(res) {
			$("." + classname).html(res.sum);
		},error:function(e) {
		$("." + classname).html('12');

		}
	});

}
function getInventoryNumsum(classname, skuId, goodsId) {
	$.ajax({
		type : "get",
		url : baseUrl+"/inventory/inventoryNum.json",
		data : {
			"skuId" : skuId,
			"goodsId" : goodsId
		},
		dataType : "json",
		success : function(res) {
			$("." + classname).html(res.inventory.inventory);
			$(".v_price").html(res.inventory.goodsPrice);
			goodsPrice=res.inventory.goodsPrice;
            if(res.inventory.inventory=='0'){
            	alert('该型号缺货')
            }
		},error:function(e) {
			$("." + classname).html('7');
			$(".v_price").html('1234');
		}
	});

}

function getStandardHtml(res) {
	var standard = res;
	var ps = []
	if (standard && standard.length > 0) {
		for ( var i = 0; i < standard.length; i++) {
			var itemlist = standard[i].itemList;
			if (itemlist && itemlist.length > 0) {
				var isImg = false;
				var html = '';
				for ( var j = 0; j < itemlist.length; j++) {
					if (itemlist[j].uploadImageThumb) {
						isImg = true;
						html = html + "<li class='v_vary' id='" + itemlist[j].id
								+ "'><span><a><img src='"
								+ itemlist[j].uploadImageThumb + "' >"
								+ itemlist[j].title + "</span></li>"
					} else {
						html = html + "<li  class='v_vary' id='"  + itemlist[j].id + "'><span>"
								+ itemlist[j].title + "</a></span></li>"
					}
				}
				if (isImg) {
					ps.push("<div class='goods-color clearfix'><div class='color-tit'>"
							+ standard[i].title
							+ "</div><div class='img-text param-"+i+"'><ul class='clearfix'>"
							+ html + "</ul></div></div>");
				} else {
					ps.push("<div class='goods-size clearfix'><div class='size-tit'>"
							+ standard[i].title
							+ "</div><div class='size-con param-"+i+"'><ul class='clearfix'>"
							+ html + "</ul></div></div>");
				}
			}

		}
	}
	return ps.join('');
}

function submitstandard() {
	var skuIds = [];
	var $ps = $(".goods-prop .v_standard").children();
	$ps.each(function(i){
		var $li = $(this).find("li.selected");
		if ($li.length === 1) {
			skuIds.push($li.attr("id"));
		}
	});
	
	if ($ps.length === skuIds.length) {
		standardId = skuIds.join('_');
		return true;
	} else {
		standardId = "";
		return false;
	}
	
//	standardId="";
//	var list=$(".goods-prop .v_standard")[1];
//	var ul=$(list).find("ul");
//	var boolean=false;
//	for ( var i= 0; i < ul.length; i++) {
//		var li=$(ul[i]).find("li");
//		for(var j= 0; j < li.length; j++){
//			isselecet=$(li[j]).attr("class");
//			//console.log(isselecet);
//			if(isselecet && isselecet.indexOf("selected")>-1){
//				if(i!=(ul.length-1)){
//					standardId=standardId+$(li[j]).attr("id")+"_";
//				}else{
//					standardId=standardId+$(li[j]).attr("id");
//				}
//				break;
//			}
//			if(j==li.length-1){
//				return false;
//			}
//
//		}
//
//	}
//	return true;
}
function vary(){
	//console.log(111111);
//	console.log(submitstandard())
	if(submitstandard()){
//		var id = rj.getQueryString("id");
		var id = goodsId;
		skuId=id+"_"+standardId;
		getInventoryNumsum("v_inventory",skuId,id);
	}
}

function selectProp(){
	var $gpJs = $('.gp-js');

	$gpJs.find('li:not(.no)').each(function(){
		$(this).click(function(){
			var index = $(this).index();
			$(this).siblings().removeClass('selected');
			$(this).addClass('selected');

			var $propEle = $(this).parents('.prop'),
				parentEle = /param-\d/.exec($(this).parent().parent()[0].className)[0];

			if($propEle.hasClass('spe-con')){
				var $target = $('.goods-prop').find('.' + parentEle).children().children();
				$target.removeClass('selected');
				$target.eq(index).addClass('selected');
			}else if($propEle.hasClass('goods-prop')){
				var $target = $('.spe-con').find('.' + parentEle).children().children();
				$target.removeClass('selected');
				$target.eq(index).addClass('selected');
			}
			vary();
		});
	});
}

var goodsId = '';
function init(res,id) {
	goodsId = id;
	
	$(".v_standard").html(getStandardHtml(res));
	selectProp();
	getsum("v_inventory",id);
	$('.addCart').on('click',function(){
		addCart();
	});
}

function buy(){
	
	if(rj.islogin()){
		
	}else{
		rj.openNativeWindow('login2.html', false);
	}
}

function getBuyInfo(){
	var  obj= new Object();

	if(submitstandard()){
		obj["skuId"]=goodsId+"_"+standardId; // rj.getQueryString("id")+"_"+standardId;
		obj["num"]=$(".group-inp").val();
        obj["goodsId"]=goodsId;// rj.getQueryString("id");
        return obj
	}else{
        alert('未选择类型')
		return null;
	}

}



