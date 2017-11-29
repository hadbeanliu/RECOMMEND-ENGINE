(function($,E){
	var $bizEdit = $("#bizEdit"),$bizDel = $("#bizDel"),$bizAdd = $("#bizAdd");
	var $form = $("#myModal");
	function changeSelect($sel){
		var $sel = $($sel).find("option:selected");
		var val = $($sel).val();
		
		var vid = $sel.attr("vid");
		$bizEdit.attr("value",vid);
		$bizDel.attr("value",vid);
		$("[name=bizCode]").attr("value",val);
		$(".v_oper_do_search").click();
	}
	function clearForm ($form) { /* 把表单$form表单的值清除 */
		$form.find("#bizCode").val('');
		$form.find("#bizName").val('');
		$form.find("#mqUser").val('');
		$form.find("#mqPassword").val('');
		$form.find("#mqHost").val('');
		$form.find("#mqPort").val('');
		$form.find("#mqDestination").val('');
		$form.find(".configure-con").css("display","none");
	};
	function loadBusiness(sel){
		$.ajax({
			url: Cfg.contextPath + "/business/list",
			
			type: "GET",
			dataType: "json",
			success: function(data){
				var i,soHtml=[],size = data.length,hasSel=false;
				
				if(size > 0){
					$bizEdit.removeClass("secondary");
					$bizDel.removeClass("secondary");
				}else{
					$bizEdit.addClass("secondary");
					$bizDel.addClass("secondary");
				}
				for(i=0;i<size;i++){
					var select = "";
					if($.isEmptyObject(sel)&&!hasSel){
						select = ' selected="selected"';
						hasSel = true;
					}else{
						if(data[i].bizCode == sel){
							select = ' selected="selected"';
							hasSel = true;
						}
					}
					var html='<option vid="'+data[i].id+'" value="'+data[i].bizCode+'"'+select+'>'+data[i].bizName+'</option>';
					soHtml.push(html);
				}
				if(!hasSel&&soHtml.length>0){
					soHtml[0]=soHtml[0].replace("option", 'option selected="selected"');
				}
				$("#business").html(soHtml.join('')).change(function(){
					changeSelect(this);
				});
				changeSelect($("#business"));
			}
		});
	};

	loadBusiness();
	$bizEdit.click(function(){
		var _this = $(this);
		var val = _this.attr("value"),$panel=$('.v_info');
		$.ajax({
			url: Cfg.contextPath + "/business/v_get/"+val+".json",
			success: function(data){
				if($.isEmptyObject(data)){
			    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">获取失败,'+algCode+'该业务不存.</span>');
					setTimeout(function() {
		    			$panel.removeClass("show");
		    		}, 3000);
				}else{
					E.util.fillFormValue($form, data);
					$form.find("#modalTitle").text("编辑业务信息");
					$form.find("#bizCode").attr("readonly","readonly");
					$form.find(".configure-con").css("display","block");
					$form.find(".v_oper_submit").attr("act","edit");
					$form.find(".v_oper_submit").attr("vid",val);
					$form.foundation("reveal","open");
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
	});
	$bizAdd.click(function(){
		$form.find("#modalTitle").text("添加业务信息");
		clearForm($form);
		$("#bizCode").removeAttr("readonly");
		$form.find(".v_oper_submit").attr("act","new");
		$form.find(".v_oper_submit").removeAttr("vid");
		$form.foundation("reveal","open");
	});

	$form.find(".v_oper_submit").click(function(){
		if(!RJ.E.util.verificationForm($form)){
			return;
		}
		var data = RJ.E.util.getFormData($form);
		var act = this.getAttribute("act");
		var id = this.getAttribute("vid");
		data.id = id;
		var $panel=$('.v_info');
		$.ajax({
			url: Cfg.contextPath + "/business/"+act+"/save.json",
			type: "POST",
			dataType: "json",
			data: data,
			beforeSend: function(){
				$.screenLoad.PageLoading({
					loadingTips: "正在提交数据，请稍后……",
					borderColor: "#FFF",
					TipsColor: "#333",
					parentBody: $form
				});
			},
			success: function(data){
				$.screenLoad.PageRemove($form);
				var html = "";
				$panel.addClass("show");
				if(data.Code=="200"){
			    	$panel.find(".v_show_info").html('<i class="fa fa-fw fa-check"></i><span class="v_show_info">'+data.Msg+'.</span>');
					setTimeout(function() {
		    			$panel.removeClass("show");
		    		}, 3000);
					$form.foundation("reveal","close");
					loadBusiness(data.result.bizCode);
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
		})
	});
	$bizDel.click(function(){
		var bizName = $("#business option:selected").text();
		var id = this.getAttribute("value");
		var $model = $("#delete_warning");
		$(document).on('open.fndtn.reveal', '[data-reveal]', function () {
			$(this).find(".v_info_msg").text("确定要删除[ "+bizName+" ]该业务？");
			$(this).find(".v_oper_do_delete").click(function(){
				$.ajax({
					url: Cfg.contextPath + "/business/v_dels.json?voIds="+id,
					success: function(res){
						loadBusiness();
					}
				});
			});
		});
		$model.foundation('reveal', 'open');
	});
})(jQuery,RJ.E);