(function($, E) {
	
	$(document).foundation();
	
	//添加算法參數到彈出框，並且保存彈出框內容到相應的位置

	$(document).on('click','#param-set',function(){
		
		var id=$(this).parent().parent().attr("data-index");
		var pan= $("#args_"+id).children();
		console.log(pan);
		var html="";
		for(var j =0;j<pan.length;j++){
			var arg=$(pan[j]);
			var code=arg.find("input[name='code']").val();
			var val=arg.find("input[name='val']").val();
			var desc=arg.find("input[name='desc']").val();

			html +=  '<tr>'+
          	 	   	'<td>'+
          	 		 '<label style="text-align: center">'+code+'</label>'+
                     '</td>'+
                     '<td>'+
                     '<input data-index="'+id+'" style="text-align: center" type="text" value="'+val+'"/>'+
                     '</td>'+
                     '<td>'+
                     '<label style="text-align: center">'+desc+'</label>'+
                     '</td>'+
                     '</tr>';
		}
		$("#param-box").html(html);
	
	});
    
	//修改參數值
	$("#param-set-submit").click(function(){
		var vals = $("#param-box").find("input");
		for(var i=0;i< vals.length;i++){
			var param=$(vals[i]);
			var index=param.attr("data-index")+"";
			var value=param.val();
			$("#args_"+index).find("div[data-index='"+i+"']").find("input[name='val']").attr("value",value);
		}
		
	});
	
	afterLoad();

	function afterLoad() {
		var id = $("#flowcode").val();
		if (id == null || id == ""){
			
			$("#alg-flow-pan").append("");
		}
		$.ajax({

					url : Cfg.contextPath + "/algorithmflow/get/" + id + ".json",
					type : "GET",
					
					success : function(data) {
						
							var html = "";
							
							for ( var i = 0 ;i< data.length;i++) {
								
								html+=add(i,data[i]);
							}
							
							$("#alg-flow-pan").html(html);
						
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.screenLoad.PageRemove();
						$panel.find(".v_show_info").html(
								'<i class="fa fa-fw fa-exclamation-circle"></i><span class="v_show_info">获取失败：'
										+ textStatus + ','
										+ XMLHttpRequest.responseText
										+ '.</span>');
						setTimeout(function() {
							$panel.removeClass("show");
						}, 3000);
					}

				});

	}
	

	var typeList = $('.type-list');
	typeList.click(function() {
		$(this).siblings('.alg-list').slideToggle(250);
		$(this).find('i').toggleClass('fa-angle-down');
	});



	
	//排序整理后的页面
	function order() {
		
		var order = $('#alg-flow-pan').children();
		
		
		for ( var j = 0; j < order.length; j++) {
			
			var tr=$(order[j]);
			var oldIndex=tr.attr("data-index");
			
			tr.attr("data-index",j);
			tr.find("input[name='index']").attr("value",j);
			tr.find("span").first().html(j);
			tr.find("div[id=args_"+oldIndex+"]").attr("id","args_"+j);
		}
	}
	
	
	// 删除一行
	$(document).on('click', '.delCode', function() {

		if ($("table tbody").children().length == 1)
			return;

		$(this).parents('tr').remove();
		order();
	});

	// 添加一行算法
	var ordernum = 0;
	function add(ordernum,flow) {
		var args=JSON.parse(flow.label.args);
	    var argPan='<div id="args_'+ordernum+'">';
	    for(var j in args){
	    	
	    	var arg=args[j];
	    	argPan+='<div data-index="'+j+'"><input type="text" name="code" value="'+arg.code+'"/><input type="text" name="val" value="'+arg.val+'"/><input type="text" name="desc" value="'+arg.desc+'"/></div>';
	    }
	    argPan+='</div>';
		var html='<tr role="row" class="odd" data-index="'+ordernum+'">'
			+ '<td class="order">'
			+ '<input type="hidden" name="index" value="'+ordernum+'"/>'
			+ '<span>'+ordernum+'</span>'
			+ '</td>'
			+ '<td>'
			+ '<input type="hidden" name="algCode" value="'+flow.label.algCode+'"/><a name="algCode_'+ordernum+'" class="p-color">'+flow.label.algCode+'</a>'
			+ '</td>'
			+ '<td>'
			+ '<span>'+flow.label.input+'</span>'
			+ '</td>'
			+ '<td>'
			+ '<span>'+flow.label.output+'</span>'
			+ '</td>'
			+ '<td>'
			+ '<input style="text-align: center" name="dir" type="text" value="'+flow.edges+'"/>'
			+ '</td>'
			+ '<td  style="display:none">'
			+ argPan
			+ '</td>'
			+ '<td>'
			+ '<a href="javascript:;" class="button tiny hollow" id="param-set" data-reveal-id="codeModal" >参数设置</a>'
			+ '<a href="javascript:;" class="button tiny hollow delCode">删除</a>'
			/*+ '<a href="javascript:;" class="button tiny hollow addCode-up"  data-reveal-id="addModal">往上添加一行</a>'*/
			+ '</td>' + '</tr>';
		return html;
	}
	
	
	// 加载算法列表
	var addModal = $('#addModal');

	$('#addModal').one("click",function(){
		
		$.ajax({
			
			url:Cfg.contextPath+"/algorithm/list.json",
			
			success:function(data){
				
				var html='<li>'+
		    	'<div class="type-list"><i class="fa fa-angle-right fa-fw" aria-hidden="true"></i><a href="javascript:;">类别1</a></div>'+
				'<ul class="alg-list">';
				
				for(var i in data){
					
					html+=	'<li>'+
                    		'<input type="radio" name="type" value="'+data[i].algCode+'" checked="true" id="alg'+i+'"/><label for="alg'+i+'">'+data[i].algCode+'</label>'+
                    		'</li>';
                    		
				}
				
				html+=		'</ul>'+
                		'</li>';
				
				$("#alg-line").html(html);
			}
			
			
		});
		
	});
	
	
	addModal.click();

	$(document).on('click', '.addCode-up', function() {
		getThis = $(this);
		
		addModal.foundation('reveal', 'open');
		addModal.addClass('modalUp');
	});
	
	
	// 往下添加一行
	$(document).on('click', '.addCode-down', function() {
		getThis = $(this);
		addModal.foundation('reveal', 'open');
		addModal.addClass('modalDown');
		
	});
	
	
	//確定添加算法
	$('#save-alg').click(function() {
		var algId=$("input[name='type']:checked").val();
		$.ajax({
			url:Cfg.contextPath+"/algorithm/v_get/"+algId+".json",
			
			success:function(data){
				var colsNum=$("#alg-flow-pan").children().length;
				
				var flow=new Object();
				flow.label=data;
				flow.edges="";
				
				$("#alg-flow-pan").append(add(colsNum,flow));
				order(); 	
			}
			
		});
		
		addModal.foundation('reveal', 'close');
	});
	
	$(".submit").click(function(){
		
		var flowCode=$("#flowcode").val();
		var desc=$("#desc").val();
		
		if(flowCode=="" || desc=="")
			return alert("字段不能为空");
		
		var vertexs=new Array();
		var form=$("#alg-flow-pan").children("tr");
		for(var i=0;i< form.length;i++){
			var tr=$(form[i]);
			var index=tr.find("input[name='index']").val();
			var algCode=tr.find("input[name='algCode']").val();
			var dir=tr.find("input[name='dir']").val();
            var vertex=new Object();
			
			var argdiv=$(form[i]).find("#args_"+index).children();
			
			var alg=new Object();
			var args=new Array();			
			for(var j=0 ; j<argdiv.length;j++){
				var param=RJ.E.util.getFormData($(argdiv[j]));
				var arg=new Object();
				arg.code=param.code;
				arg.desc=param.desc;
				arg.val=param.val;
				args[j]=arg;
			}
			
			alg.args=JSON.stringify(args);
			alg.algCode=algCode;
			if(dir!=""){
			
				vertex.edges=dir.split(",");
			}
			vertex.index=index;
			vertex.label=alg;
			
			vertexs[i]=vertex;
			
		}
		$.ajax({
	
			url : Cfg.contextPath+"/algorithmflow/save",
			type : 'POST',
			data:{"flow":JSON.stringify(vertexs),"flowCode":flowCode,"desc":desc},
			success:function(data){
				alert("success");
			}
			
		});

	});
	
	

})(jQuery, RJ.E);