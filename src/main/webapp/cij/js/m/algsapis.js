(function($,E){
	function changeDate($sel,res){
		var $sel = $($sel).find("option:selected");
		var val = $($sel).val();
		var num = $sel.attr("num");
		var vo = res[num];
		$("#method").val(vo.method);
		$("#uri").val(vo.uri);
		$("#params").val(vo.params);
	}
	var nmCid = $("#apiData").attr("value"),
		data={
			ssCode:"",
			apiName:""
		};
	if(nmCid.replace(",","").trim().length>0){
		var strs = nmCid.split(",");
		data.apiName = strs[0]
		data.ssCode = strs[1];
	}
	$.ajax({
		url: Cfg.contextPath + "/api/list.json",
		data: data,
		type: "GET",
		success: function(res){
			var $apilist = $("#apilist");
			if(res.length>0){
				var i,html = [];
				for(i=0;i<res.length;i++){
					var sel="";
					if(i==0){
						sel=" selected=selected";
					}
					html.push('<option num="'+i+'" value="'+res[i].id+'" '+sel+'>'+res[i].name+'</option>');
				}
				$apilist.html(html.join(""));
				$apilist.change(function(){
					changeDate($apilist,res);
				});
				changeDate($apilist,res);
			}else{
				
			}
		}
	});
	$("#apiTest").click(function(){
		var id = $("#apilist").val();
		var $result = $("#result");
		var params = $("#params").val();
		var data = {
			params:params
		}
		$.ajax({
			url: Cfg.contextPath + "/api/testApi/"+id+".json",
			data:data,
			beforeSend: function(){
				$.screenLoad.PageLoading({
					loadingTips: "正在调试接口，请稍后……",
					borderColor: "#FFF",
					TipsColor: "#333"
				});
			},
			success : function(res){
				$.screenLoad.PageRemove();
				$result.val(JSON.stringify(res));
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$.screenLoad.PageRemove();
				$result.val(textStatus+","+XMLHttpRequest.responseText);
			}
		});
	});
})(jQuery,RJ.E);