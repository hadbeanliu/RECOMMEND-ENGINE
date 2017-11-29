
//头部搜索
function showSearch(){
	$('.userset-list').slideUp(200);
	
	var topsearch =  document.getElementById("topsearch");	  	 
	if(topsearch.style.display == "block"){
	topsearch.style.display = "none";
	}else{
	  topsearch.style.display = "block";
	}
}

//返回头部、底部
function goTop() { window.scroll(0, 0)}
function goBottom(){var bottomH=Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);window.scroll(0,bottomH);}


//返回头部、底部
$(function () {
	$(".gotop").click(/*定义返回顶部点击向上滚动的动画*/
	function () {
		$('html,body').animate({ scrollTop: 0 }, 700);
	});
	$(".gohome").click(/*定义返回顶部点击向上滚动的动画*/
	function(){
		/*HomepageFavorite.Homepage();*/
	});
	$(".gobottom").click(/*定义返回顶部点击向上滚动的动画*/
	function () {
		var bottomH=Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);window.scroll(0,bottomH);
	});
	$(document).scroll(
		function(){
			if($(document.body).scrollTop() + $(document).scrollTop() > 300 && $(document.body).scrollTop() + $(document).scrollTop() < 300){
				$(".go").css("display","block");
				$(".gobottom").css("display","block"),$(".gotop").css("display","none");
			}else if($(document.body).scrollTop() + $(document).scrollTop() > 300){
				$(".go").css("display","block");
				$(".gobottom").css("display","none"),$(".gotop").css("display","block");				
			}else{
				$(".go").css("display","none");
			}
	}
	);
})

/*图片上传*/
var uploader,
	$list = $('#fileList'),
	arr_images = [];
uploader = WebUploader.create({
	duplicate: true,
	// 选完文件后，是否自动上传。
	auto: true,

	// swf文件路径
	swf: '/blog/flash/Uploader.swf',

	// 文件接收服务端。
	server: '/upload/fileUpload',

	// 选择文件的按钮。可选。
	// 内部根据当前运行是创建，可能是input元素，也可能是flash.
	pick: '#filePicker',

	// 只允许选择图片文件。
	accept: {
		title: 'Images',
		extensions: 'gif,jpg,jpeg,bmp,png',
		mimeTypes: 'image/*'
	},
	fileNumLimit: 10,
	//允许重复上传同一张图片
	//duplicate: true
});

uploader.on('uploadSuccess', function(file, response) {

	if($('.uploader').size()>5){
		console.log($('.uploader').size());
		$('.v_info_fail').find('.v_show_info').text('最多上传5张图片');
		$('.v_info_fail').addClass("show");
		setTimeout(function() {
			$('.v_info_fail').removeClass("show");
		}, 3000);
		return;
	} else {
//	var $li = '<li class="th"><img class="activity-img shop-show-img" src=' + response.url + '></li>';
	var $li = '<div class="uploader uploaded"><span class="bg-preview"><img src="'+response.url+'"></span><span class="fa fa-times reset"></span></div>';
	arr_images.push(response.url);

	$list.find('.no-bullet').append($li);

	var sortable;
	var el = document.getElementById('sortable-list');
	sortable = new Sortable(el, {
		group: "name", // or { name: "...", pull: [true, false, clone], put: [true, false, array] }
		sort: true, // sorting inside list
		disabled: false, // Disables the sortable if set to true.
		store: null, // @see Store
		animation: 150, // ms, animation speed moving items when sorting, `0` — without animation
		ghostClass: "sortable-ghost", // Class name for the drop placeholder
		dataIdAttr: 'data-id',

		scroll: true, // or HTMLElement
		scrollSensitivity: 30, // px, how near the mouse must be to an edge to start scrolling.
		scrollSpeed: 10
	});

	$('.uploader').on('click', '.reset', function() {
		$(this).parent('.uploader').remove();
	})

	}
});
uploader.on('error', function(reason) {
	var fail = $('.v_info_fail').find('.v_show_info');
	switch (reason) {
		case 'Q_TYPE_DENIED':
			fail.text('类型错误');
			$('.v_info_fail').addClass("show");
			setTimeout(function() {
				$('.v_info_fail').removeClass("show");
			}, 3000);	
			break;
		case 'F_DUPLICATE':
			fail.text('你已经选择过该图片了');
			$('.v_info_fail').addClass("show");
			setTimeout(function() {
				$('.v_info_fail').removeClass("show");
			}, 3000);	
			break;
	}
});
