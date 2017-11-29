(function($,E){
	var i,sel=1;
	var $lis = $("#wdgz").find("li");
	for(i=0;i<$lis.length;i++){
		if($($lis[i]).hasClass("on")){
			sel = i+1;
			break;
		}
	}
	new o_tab().init("wdgz", "li", "data_wdgz_con_", "on",
			"", sel, "");
	new o_tab().init("wdgz2", "li", "data_wdgz_con_1_", "on",
			"", 1, "");
	new o_tab().init("wdgz3", "li", "data_wdgz_con_2_", "on",
			"", 1, "");
	var $offdata = $("#data_wdgz_con_1_1");
	var $offflowdata = $("#data_wdgz_con_1_2");
	
	var $indata = $("#data_wdgz_con_2_1");
	var $inflowdata = $("#data_wdgz_con_2_2");
	
	loadDataTable($offdata,$offflowdata,"/algorithm","offline");
	loadDataTable($indata,$inflowdata,"/algorithm/online","online");
	function loadDataTable($data1,$data2,url,type){
		if($data1.length>0 && $data1.dataTablePage){
			
			$data1.dataTablePage({
				
				url: Cfg.contextPath + (url?url:"/algorithm"),
				columns: [{
					data: "algCode",
					name: "算法CODE",
					orderable: false,
					searchable: true
				},{
					data: "algDesc",
					name: "算法介绍",
					orderable: false,
					searchable: true
				},{
					data: "type",
					name: "算法类型",
					orderable: false,
					searchable: true
				},{
					data: "sourceDesc",
					name: "来源",
					orderable: false,
					searchable: true
				},
//				{
//					data: "stDesc",
//					name: "状态",
//					orderable: false,
//					searchable: true,
//					display: function (td, value, vo, rowIndex, colIndex) {
//						$(td).html('<label class="font-success">'+value[0]+'</label>');
//					}
//				},
				{
					data: "id",
					name: "操作",
					orderable: false,
					searchable: false,
					display: function (td, value, vo, rowIndex, colIndex) {
						$(td).html('<a class="button tiny hollow" href="'+Cfg.contextPath+'/algorithm/'+type+'/editAlg/'+value+'.htm">查看</a>');
					}
				}]
			});
		}
		if($data2.length>0 && $data2.dataTablePage){
			$data2.dataTablePage({
				url: Cfg.contextPath + "/algorithmflow",
				columns: [{
					data: "flowCode",
					name: "模板Code",
					orderable: false,
					searchable: true
				},{
					data: "flowDesc",
					name: "模板介绍",
					orderable: false,
					searchable: true
				},{
					data: "sourceDesc",
					name: "来源",
					orderable: false,
					searchable: true
				},{
					data: "id",
					name: "操作",
					orderable: false,
					searchable: false,
					display: function (td, value, vo, rowIndex, colIndex) {
						$(td).html('<a class="button tiny hollow" href="'+Cfg.contextPath+'/algorithmflow/'+type+'/edit/'+value+'.htm">查看</a>');
					}
				}]
			});
		}
	}
})(jQuery,RJ.E);