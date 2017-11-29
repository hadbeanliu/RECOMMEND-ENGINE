var store = {
	state: {
		message: 'Hello!',
		cfg: Cfg
	},
	dataUrl: {
		myCart: Cfg.contextPath + "/buyer/mycart",
//	  hotSaleRecommend: Cfg.contextPath + "/cij/vue/data.json",
//	  guessMeLikeRecommend: Cfg.contextPath + "/cij/vue/data.json",
  		hotSaleRecommend: Cfg.contextPath + "/recommend/hotSale.json", // "/cij/vue/data.json",
  		guessMeLikeRecommend: Cfg.contextPath + "/recommend/guessMeLike.json" // "/cij/vue/data.json"
	},
	actionA: function () {
		this.state.message = 'action A triggered'
	},
	actionB: function () {
		this.state.message = 'action B triggered'
	}
}


Vue.component('cartList', SkeletonList.extend({
	methods: {
		dataChange: function(){
			$("div.cart num").text(this.itemsCount);
		}
	},
	template: '\
		<div class="drop-bar-tit"><i class="fa fa-shopping-cart"></i>购物车<span class="p-color">{{itemsCount ? itemsCount : ""}}</span></div>\
		<div class="drop-bar-con mini-cart">\
	        <div class="mini-cart-hd">\
	            <strong>\
	                最近加入的宝贝：\
	            </strong>\
	        </div>\
			<ul class="mini-cart-bd">\
				<li v-for="item in items" track-by="id">\
					<div class="mini-cart-img">\
					    <a target="_blank" href="{{item.goods.goodsUrl}}" title="{{item.goods.goodsTitle}}">\
					        <img :src="item.goods.goodsFaceThumb" alt="{{item.goods.goodsTitle}}" />\
					    </a>\
					</div>\
					<div class="mini-cart-count">\
					    <span>¥{{item.goods.goodsPrice}}</span>\
					</div>\
					<div class="mini-cart-title">\
					    <a target="_blank" href="{{item.goods.goodsUrl}}" title="{{item.goods.goodsTitle}}">{{item.goods.goodsTitle}}</a>\
					</div>\
					<div class="mini-cart-del">\
					    <a v-on:click.stop="remove(item)" href="javascript:void(0)" title="删除"><i class="fa fa-trash fa-2x"></i></a>\
					</div>\
					<div class="mini-cart-info">\
					    <a title="{{item.skuInfo}}">{{item.skuInfo}}</a>\
					</div>\
				</li>\
			</ul>\
			<div class="mini-cart-ft">\
			    <p class="left">\
			        <strong>\
			            购物车里还有{{itemsCount}}件宝贝\
			        </strong>\
			    </p>\
			    <p class="right">\
			        <a target="_top" href="@{/buyer/mycart.htm}" class="button">查看我的购物车</a>\
			    </p>\
			</div>\
		</div>\
'
}));


Vue.component('recommendList', AjaxList.extend({
	methods: {
		convertData: function(data){
			return {items: data.goodsList};
		}
	},
	template:'<div class="mod-rec-l1 margin-t">\
	    <div class="modhead clearfix">\
	    <div class="modtitle"><h2><a href="#" target="_self" title="{{title}}">{{title}}</a></h2></div>\
	    <div class="modmore">\
	        <div id="data_sidebar" v-on:click="refresh">\
			<slot>\
	            <a class="p-color radius fa-border p-border" title="{{refreshText}}"><i class="fa fa-refresh"></i>{{refreshText}}</a>\
			</slot>\
	        </div>\
	    </div>\
	</div>\
	<div class="modcon">\
		<div v-if="$loadingAsyncData" :style="emptyItemsHeight"><p class="empty-h170 text-center p-color font-large"><i class="fa fa-info-circle"></i>{{loadingAsyncDataText}}</p></div>\
	    <div v-else>\
	        <ul v-if="items && items.length > 0" class="small-block-grid-2 medium-block-grid-5 large-block-grid-5">\
	            <li v-for="item in items">\
	            	<div class="img-container">\
	                    <div class="block-img">\
	                        <a target="_blank" href="{{item.goodsUrl}}" title="{{item.goodsTitle}}">\
								<img :src="item.goodsFaceThumb" title="{{item.goodsTitle}}" alt="{{item.goodsTitle}}" />\
							</a>\
	                    </div>\
	                    <div class="des goods pad5">\
	                        <span class="font-tiny block tit"><a target="_blank" href="{{item.goodsUrl}}" title="{{item.goodsTitle}}">{{item.goodsTitle}}</a></span>\
	                        <span class="block font-alert font-large">\
	                            ￥{{item.goodsPrice}}元\
	                            <!-- <span class="label">满200减</span> -->\
	                        </span>\
	                    </div>\
	                </div>\
	            </li>\
	        </ul>\
			<p v-else :style="emptyItemsHeight" class="empty-h170 text-center p-color font-large"><i class="fa fa-info-circle"></i>{{errText}}</p>\
	    </div>\
	</div>\
	</div>'
}));


$(function(){
	new Vue({
		el: 'body',
		data: {
			showModal: false,
			dataUrl: store.dataUrl
		}
	});
});