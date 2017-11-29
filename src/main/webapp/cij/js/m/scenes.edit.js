(function($,E){
//	var bizCode=$("#business").find("option:selected").val();
//	$("#business").change(function(){
//			bizCode=$(this).find("option:selected").val();
//			
//	});
	
	$("#data_wdgz_con_1").dataTablePage({
		
		url: Cfg.contextPath + "/scenes",
		columns:[{
			data: "ssName",
			name: "场景名称",
			orderable: false,
			searchable: true
		},{
			data: "apiParam",
			name: "场景参数",
			orderable: false,
			searchable: true,
			display: function (td, value, vo, rowIndex, colIndex) {
				
				var strs=vo.apiParam.split("&");
				var html="";
				for(var i=0;i<strs.length;i++){
					html+=strs[i]+"<br />";
				}
				$(td).html(html);
			}
		},{
			data: "flowCode",
			name: "算法流程",
			orderable: false,
			searchable: true,
			display: function (td, value, vo, rowIndex, colIndex) {
				
				var flow=vo.flowCode;
				var json=JSON.parse(flow);
				var html="";
				for(var i=0;i<json.length;i++){
					html+=json[i].pathflowCode+"<br />";
				}
				$(td).html(html);
			}
		},{
			data: "status",
			name: "上次运行状态",
			orderable: false,
			searchable: true
		},{
			data: "id",
			name: "操作",
			orderable: false,
			searchable: false,
			display: function (td, value, vo, rowIndex, colIndex) {
				$(td).html('<a class="button tiny hollow" href="'+Cfg.contextPath+'/api.htm?apiName=rec&ssCode='+vo.ssCode+'">API调试</a>'
				+'<a class="button tiny hollow v_oper_delete">删除场景</a>'
				+'<a class="button tiny hollow" id="taskStart">启动场景</a>');
				$(td).find("#taskStart").click(function(){
					$.ajax({
						url:Cfg.contextPath+"/task/start.json?ssCode="+vo.id,
						success: function(){
							
						}
					});
				});
			}
		}
		]
	});
	var i = 0;
	var a = $('.step-title').find('li').size()-1;
	$('#next-step').on('click',function(){
		if( i<a ){
			$('.step-title').find('li').eq(i+1).addClass('selected').siblings().removeClass('selected');
			$('.step-con').find('.gallery').eq(i+1).removeClass('hide').siblings().addClass('hide');
			//$('#prev-step').removeClass('secondary');
			i++;
		}else{
			i--;
			//$('#next-step').text('完成');
		}
	});
	$('#prev-step').on('click',function(){
		//$('#next-step').text('下一步');
		if( i >= (l-1) ){
			$('.step-title').find('li').eq(i-1).addClass('selected').siblings().removeClass('selected');
			$('.step-con').find('.gallery').eq(i-1).removeClass('hide').siblings().addClass('hide');
			i--;
		}else{
			i=0;
			//$('#prev-step').addClass('secondary');
		}
	});
})(jQuery,RJ.E);