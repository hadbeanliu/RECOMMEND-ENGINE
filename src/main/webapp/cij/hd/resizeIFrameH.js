var currentScriptUrl = (function() {
	if (document.currentScript) {
		return document.currentScript.src;
	} else {
		var scripts = document.getElementsByTagName('script'),
	    script = scripts[scripts.length - 1];
		if (script.getAttribute.length !== undefined) {
			return script.src;
		}
		return script.getAttribute('src', -1);
	}
})();

var resizeIframeUrl = (function(csu){
	if (csu) {
		var ix = csu.lastIndexOf('/');
		if (ix > 0) {
			return csu.substring(0, ix) + "/resizeIFrameH.html";
		}
	}
})(currentScriptUrl);

function setHeight(type,dm){
	var src = null;
    hashH = document.documentElement.scrollHeight; //获取自身高度
    if (dm) {
    	src = dm + "/resizeIFrameH.html#" + hashH; //将高度作为参数传递
    } else if (resizeIframeUrl) {
    	src = resizeIframeUrl + "#" + hashH; //将高度作为参数传递
    }
    if(type){
    	src=src + "#" + type;
    }else{
    	src=src + "#block";
    }
    if (src) {
    	var frm = document.getElementById("resizeIFrameH");
    	if (frm) {
    		frm.parentElement.removeChild(frm);
    	}
    	frm = document.createElement("iframe");
    	frm.setAttribute("id","resizeIFrameH");
    	frm.setAttribute("width","1");
    	frm.setAttribute("height","0");
    	frm.setAttribute("style","display:none;");
    	document.body.appendChild(frm);
    	frm.src=src;
    }
}