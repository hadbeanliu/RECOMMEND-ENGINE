/*function  cheakErroe(e){
	setTimeout(function(){
		$(e).attr("src", $(e).attr("src"));
	},1000);				  
}*/
function stopPropagation(e) {
    if (e.stopPropagation)
        e.stopPropagation();
    else
        e.cancelBubble = true;
}
/**
 * 解决 ie，火狐浏览器不兼容new Date(s)
 * @param strDate
 * 返回 date对象
 */
function getDateForStringDate(strDate){
    //切割年月日与时分秒称为数组
    var s = strDate.split(" "); 
    var s1 = s[0].split("-"); 
    var s2 = s[1].split(":");
    if(s2.length==2){
        s2.push("00");
    }
    return new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
}
//input宽度计算
function width(){
	$('.input-label').each(function(){
		var target = $(this)[0];
		var leftwidth = target.offsetLeft;
		var targetwidth = target.offsetWidth;
		var parentwidth = target.parentNode.offsetWidth;
		var a = parentwidth - leftwidth;
		if(a > targetwidth){
			target.style.width = a -38 + "px"  ;
		}else{
			target.style.width = "100%";
		}	
	});
}
(function($, E) {

	var ue = E.ue = {};
	$(function() { 
		
		// 实例化编辑器
		// 建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例

		ue = UE.getEditor('editor', {
			// 这里可以选择自己需要的工具按钮名称,此处仅选择如下七个
			toolbars : [ [ 'fullscreen', 'undo', 'redo', 'bold', 'italic',
					'underline', 'strikethrough', 'justifyleft',
					'justifycenter', 'justifyright', 'blockquote', 'link',
					'insertimage' ] ],
			// 主题样式
			// theme: 'notitle',
			// themePath: baseMode.basePath + "javascript/ueditor143/themes/",
			//focus时自动清空初始化时的内容
			autoClearinitialContent : true,
			initialFrameHeight : 300,
//			scaleEnabled:true,
			autoHeightEnabled:false,
			//关闭字数统计
			wordCount : false,
			//关闭elementPath
			elementPathEnabled : false,
			//关闭自动保存
			enableAutoSave : false,
			//textarea : 'content',
			enableContextMenu : false,
			zIndex : 2			
		});
		ue.ready(function() {
			if(RJ.E.content!=null){
				ue.setContent(RJ.E.content);
				if(RJ.E.disabled){
					ue.setDisabled();
				}
			}
		});
		
		//百度编辑器初始化
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
			var url;
			var caseId=$("input[name='caseId']").val();
			if (action == 'uploadimage' || action == 'uploadscrawl') {
				url = "https://www.pmp99.com/fileCenter/fileUpload.sp?act=uploadSave&appId=00000001&caseId=00000014";
			} else if (action == 'catchimage') {
				url = "https://www.pmp99.com/fileCenter/fileUpload.sp?act=uploadOnLineFiles&appId=00000001&caseId=00000014";
			} else {
				url = this._bkGetActionUrl.call(this, action);
			}
			return url;
		};
	});
})(jQuery, RJ.E);