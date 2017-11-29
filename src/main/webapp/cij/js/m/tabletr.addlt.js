(function($, E){
	E.rowNum = 0;
	$.tabletr = {
		addTr: function($t,data){
			var code = "",value="",desc="";
			if(typeof data!= "undefined" && data.length!=0){
				code = data.code;
				value = data.val;
				desc = data.desc;
			}
			var html = '<tr>'+
					   '	<td>'+
					   '		<input type="text" name="paramCode_'+E.rowNum+'" value="'+code+'"/>'+
					   '	</td>'+
					   '	<td>'+
					   '		<input type="text" name="paramValue_'+E.rowNum+'" value="'+value+'"/>'+
					   '	</td>'+
					   '	<td>'+
					   '		<input type="text" name="paramDesc_'+E.rowNum+'" value="'+desc+'"/>'+
					   '	</td>'+
					   '	<td>'+
					   '		<a href="javascript:;" class="button tiny hollow delCode">删除</a>'+
					   '	</td>'+
					   '</tr>';
			$(html).appendTo($t);
			E.rowNum++;
			$("[name=paramRow]").val(E.rowNum);
		},
		delTr: function($t){
			var $tr = $($t).parents('tr') || $($t)
			$tr.remove();
		},
		loadData: function($t,tdata){
			for(var index in tdata){
				this.addTr($t, tdata[index]);
			}
			E.rowNum = tdata.length;
			$("[name=paramRow]").val(E.rowNum);
		}
	};
	$(function(){
		$('.addCode').on('click',function(){
			$.tabletr.addTr($('#codeTable tbody'));
		})
		$(document).on('click','.delCode',function(){
			$.tabletr.delTr($(this));
		});
	});
})(jQuery, RJ.E);