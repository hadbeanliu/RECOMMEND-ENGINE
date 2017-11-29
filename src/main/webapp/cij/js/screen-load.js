jQuery.screenLoad = {
    PageLoading: function (options) {
        //配置项
        var defaults = {
        	parentBody: 'body',
            opacity: 1,
            //loading页面透明度
            backgroundColor: "rgba(0,0,0,0.5)",
            backgroundColorTip:'white',
            //loading页面背景色
            borderColor: "#bbb",
            //提示边框颜色
            borderWidth: 1,
            //提示边框宽度
            borderStyle: "solid",
            //提示边框样式
            loadingTips: "Loading, please wait...",
            //提示文本
            TipsColor: "#666",
            //提示颜色
            zindex: 999
            //loading页面层次
        }
        var options = $.extend({},defaults, options);
        //获取页面宽高
        var _PageHeight = document.documentElement.clientHeight,
        _PageWidth = document.documentElement.clientWidth;
        //在页面未加载完毕之前显示的loading Html自定义内容
        var _LoadingHtml = '<div id="loadingPage" style="position:fixed;left:0;top:0;_position: absolute;width:100%;height:' + _PageHeight + 'px;background:' + options.backgroundColor + ';opacity:' + options.opacity + ';filter:alpha(opacity=' + options.opacity * 100 + ');z-index:' + options.zindex + ';">'+
        				   '	<div id="loadingTips" style="position: absolute; cursor1: wait; width: auto;border-color:' + options.borderColor + ';border-style:' + options.borderStyle + ';border-width:' + options.borderWidth + 'px; height:50px; line-height:50px; padding-left:52px; padding-right: 10px;border-radius:5px; background: ' + options.backgroundColorTip + '; color:' + options.TipsColor + ';font-size:16px; background-size:10%;">' +
        				   '		<i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>'+options.loadingTips + 
        				   '	</div>'+
        				   '</div>';
        //呈现loading效果
        $(options.parentBody).append(_LoadingHtml);
        //获取loading提示框宽高
        var _LoadingTipsH = document.getElementById("loadingTips").clientHeight,
        _LoadingTipsW = document.getElementById("loadingTips").clientWidth;
        //计算距离，让loading提示框保持在屏幕上下左右居中
        var _LoadingTop = _PageHeight > _LoadingTipsH ? (_PageHeight - _LoadingTipsH) / 2 : 0,
        _LoadingLeft = _PageWidth > _LoadingTipsW ? (_PageWidth - _LoadingTipsW) / 2 : 0;
        $("#loadingTips").css({
            "left": _LoadingLeft + "px",
            "top": _LoadingTop + "px"
        });
		$(options.parentBody).css('overflow','hidden');
    },
    PageRemove: function(parentBody){
    	parentBody = parentBody || "body";
    	$("#loadingPage").empty().remove();
		$(parentBody).css('overflow','scroll');
    }
}