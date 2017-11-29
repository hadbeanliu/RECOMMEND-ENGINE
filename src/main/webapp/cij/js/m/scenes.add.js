(function($,E){
	//获取业务信息
    $.ajax({
    	url:Cfg.contextPath+"/business/list.json" ,
    	
    	success:function(data){
    		var str="";
    		for(var i=0;i<data.length;i++){
    			str+=' <div class="row"><div class="large-3 columns"><input type="radio" id="off-line-biz-sel" value="'+data[i].bizCode+'" name="bizCode" id="bizCode"/><label for="bizCode">'+data[i].bizCode+'</label></div><div class="large-9 columns"><label id="bizName">'+data[i].bizName+'</label></div></div>';
    		}

    		$("#biz-pan").html(str);
    		
    	}
    	
    });	

    //添加上一步和下一步控制
	var i = 0;
	var l = $('.step-title').find('li').size()-1;
	$('#next-step').on('click',function(){
		if(i==2){
			var data = RJ.E.util.getFormData($("#scenes-form"));
			$.ajax({
				  url: Cfg.contextPath + "/scenes/save.json",
				  data: data,
				  type: "POST",
				  success:function(data){

					  if(data=="success")
						  alert("save success");
					  else alert("faild");  
				  }
			
			});
		}
		
		if( i<l ){
			if(i==0&&typeof $("[name=bizCode]:checked").val() =="undefined"){
				var $panel=$('.v_info');
				$panel.addClass("show");
				$panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">请选择所属业务.</span>');
				setTimeout(function() {
		  			$panel.removeClass("show");
		  		}, 3000);
				return;
			}
			if(!RJ.E.util.verificationForm($('.step-con').find('.gallery').eq(i))){
				return;
			}
			$('.step-title').find('li').eq(i+1).addClass('selected').siblings().removeClass('selected');
			$('.step-con').find('.gallery').eq(i+1).removeClass('hide').siblings().addClass('hide');
			$('#prev-step').removeClass('secondary');
			i++;
			if(i==2){
				$(this).text("完成");
			}
		}
	});
	$('#prev-step').on('click',function(){
		//$('#next-step').text('下一步');
		if( i >= (l-1) ){
			$('.step-title').find('li').eq(i-1).addClass('selected').siblings().removeClass('selected');
			$('.step-con').find('.gallery').eq(i-1).removeClass('hide').siblings().addClass('hide');
			i--;
			if(i<2){
				$('#next-step').text("下一步");
			}
			if(i==0)
				$(this).addClass('secondary');
				
		}
		
	});
	
	//添加参数
	$("#addCustomParams").click(function(){
		var $form = $("#myModal");
		$(document).on('open.fndtn.reveal', '[data-reveal]', function () {
			$(this).find('#param-submit').click(function(){
				if(!RJ.E.util.verificationForm( $form)){
					return;
				}
				var i=$('#select-param').children('div').size();		
				var add_param_name=$('#add-param-name').val();
				var add_param_desc=$('#add-param-desc').val();
			
				$('#select-param').append('<div class="row">'
						 +'<div class="large-3 columns">'+
						 '<input name="paramName_'+i+'" id="paramName_'+i+'" type="checkbox" value="'+add_param_name+'"/>'+
						 '<label for="paramName_'+i+'">'+add_param_name+'</label>'+
						 '</div><div class="large-9 columns">'+
						 '<label><input type="hidden" name="paramDesc_'+i+'" value="'+add_param_desc+'"/>'+add_param_desc+'</label></div></div>');

				$form.foundation("reveal","close");
			});
		});
		$form.foundation("reveal","open");
	});

	//添加算法流程
  $("#addScense").click(function(){
	  $(document).on('open.fndtn.reveal', '[data-reveal]', function () {
		  $.ajax({
			  url: Cfg.contextPath + "/algorithmflow/list.json",
		  	  success:function(data){
		  		  var str="";
		  		  for(var i=0;i<data.length;i++)
		  			  str+='<option id="off-line-flow-sel" value="'+data[i].flowCode+'">'+data[i].flowCode+'</option>';
		  		  $("#offline-flow-code-pan").html(str);
		  	  }
		  });
	  });
	  $("#myModal2").foundation("reveal","open");
  });
  
//添加算法流程
  $("#biz-pan-submit").on("click",function(){
	  var $panel=$('.v_info');
	  if(!RJ.E.util.verificationForm( $("#myModal2"))){
		  return;
	  }
	  var bizCode=$("#biz-code-text").val();
	  var exist=$("#biz-list").find("label").text().indexOf(bizCode);
	  
	  if(exist > -1){
		  $panel.find(".v_show_info").html('<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">[ '+bizCode+' ]已存在.</span>');
				setTimeout(function() {
	  			$panel.removeClass("show");
	  		}, 3000);
		  return;
	  }
	  //var offLineCode=$("#off-line-flow-Code").val();
	  var offLineCode=$("#offline-flow-code-pan").val();
	  var onLineCode=$("#on-line-flow-code-pan").val();
	  var i=$("#biz-list").children("tr").size();
	  var str='<tr role="row" class="odd" id="'+bizCode+'">'
	  		   +'<td ><label>'+bizCode+'</label><input type="hidden" name="pathflowCode_'+i+'" value="'+bizCode+'"/></td>'
	  		   +'<td><a name="offflowCode_"'+i+' value="'+offLineCode+'" class="button tiny hollow"><input type="hidden" name="offflowCode_'+i+'" value="'+offLineCode+'"/>修改</a></td>'
	  		   +'<td><a name="onflowCode_"'+i+' value="'+onLineCode+'" class="button tiny hollow"><input type="hidden" name="onflowCode_'+i+'" value="'+onLineCode+'"/>修改</a></td>'
	  		   +'<td><a class="button tiny hollow margin-r10">删除</a><a class="button tiny hollow">调试</a></td>'
	  		   +'</tr>';
	  
	  
	  $("#biz-list").append(str);
	  $("#myModal2").foundation("reveal","close");
  });
  
})(jQuery,RJ.E);