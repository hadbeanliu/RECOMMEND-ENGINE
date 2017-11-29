(function($,E){
	var method = $("[name=method]").val(),
		algId = $("#algId").val();
	var $panel=$('.v_info'),$submit = $(".submit"),$close = $(".close");
	if(method==="EDIT"){
		if($.isEmptyObject(algCode)){
			$panel.find(".v_show_info").html('<i class="fa fa-fw fa-check"></i><span class="v_show_info">算法不存在，请检查.</span>');
			setTimeout(function() {
    			$panel.removeClass("show");
    		}, 3000);
			location.href = Cfg.contextPath + "/algorithm.htm?type="+$submit.attr("value");
		}else{
			$.ajax({
				url: Cfg.contextPath + "/algorithm/v_get/"+algId+".json",
				type: "GET",
				dataType: "json",
				success: function(data){
					if($.isEmptyObject(data)){
						$.screenLoad.PageRemove();
				    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">获取失败,'+algCode+'该算法不存.</span>');
						setTimeout(function() {
			    			$panel.removeClass("show");
			    		}, 3000);
					}else{
						E.util.fillFormValue($("#algData"), data);
						$("#algType").html('<span><input type="radio" name="type" checked="checked" id="radio'+data.type+'" value="'+data.type+'"><label for="radio'+data.type+'">'+data.typeDesc+'</label></span>');
						$("#statusDesc").text(data.stDesc[1]);
						$("#input").text(data.input);
						$("#output").text(data.output);
						$.tabletr.loadData($("#codeTable tbody"),JSON.parse(data.args));
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					$.screenLoad.PageRemove();
			    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">获取失败：'+textStatus+','+XMLHttpRequest.responseText+'.</span>');
					setTimeout(function() {
		    			$panel.removeClass("show");
		    		}, 3000);
				}
			});
		}
	}
	
	$submit.click(function(){
		var type = $(this).attr("value");
		var $form = $("#algData");
		if(!RJ.E.util.verificationForm($form)){
			return;
		}
		var data = E.util.getFormData($form);
		var $panel=$('.v_info');
		$.ajax({
			url: Cfg.contextPath + "/algorithm/save.json",
			type: "POST",
			dataType: "json",
			data: data,
			beforeSend: function(){
				$.screenLoad.PageLoading({
					loadingTips: "正在提交数据，请稍后……",
					borderColor: "#FFF",
					TipsColor: "#333"
				});
			},
			success: function(data){
				$.screenLoad.PageRemove();
				var html = "";
				$panel.addClass("show");
				if(data.Code=="200"){
			    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-check"></i><span class="v_show_info">'+data.Msg+'.</span>');
					setTimeout(function() {
		    			$panel.removeClass("show");
		    		}, 3000);
					location.href = Cfg.contextPath + "/algorithm.htm?type="+type;
				}else{
					$("#algCode").focus();
			    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">'+data.Msg+'.</span>');
					setTimeout(function() {
		    			$panel.removeClass("show");
		    		}, 3000);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$.screenLoad.PageRemove();
		    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">保存失败：'+textStatus+','+XMLHttpRequest.responseText+'.</span>');
				setTimeout(function() {
	    			$panel.removeClass("show");
	    		}, 3000);
			}
		});
	});
	
	$("#customDropdown1").change(function(){
		
		var category=$(this).val();
		
		$.ajax({
			url:Cfg.contextPath+"/algorithm/getIOTable/"+category+".json",
			type: "POST",
			success:function(data){
				$('input[name="input"]').attr("value",data[0]);
				$("#input").text(data[0]);
				$('input[name="output"]').attr("value",data[1]);
				$("#output").text(data[1]);
			}
		});
		
	});
	
	$close.click(function(){
		var type = $(this).attr("value");
		location.href = Cfg.contextPath + "/algorithm.htm?type="+type;
	})
})(jQuery,RJ.E);