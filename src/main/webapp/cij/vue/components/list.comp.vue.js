//(function(){

Vue.use(VueAsyncData);

var AjaxList = Vue.extend({
	data: function(){
		return {
			ajaxType: "GET",
			ajaxData: {},
			privateState: {},
			sharedState: store.state,
			loadingAsyncDataText: '数据加载中~~~',
			items: [],
			itemsCount: 0
		}
	},
	computed: {
		emptyItemsHeight: function() {
			return this.dataEmptyHeight ? ('height:' + this.dataEmptyHeight + ';') : '';
		}
	},
	props: {
//		title: {
//			type: String,
//			required: true
//		},
		title: String,
		url: String,
		errText: {
			type: String,
			default: function(){
				return '无'+ this.title +'数据~~~';
			}
		},
		refreshText: {
			type: String,
			default: "刷新"
		},
		dataEmptyHeight: String
	},
	watch: {
		url: 'reloadAsyncData'
	},
	asyncData: function(resolve, reject){
		if (this.url) {
			var self = this;
			jQuery.ajax({
//				type: "GET",
				type: this.ajaxType,
				data: this.ajaxData,
				url: this.getDataUrl(),
				dataType: "json",
				success: function(data) {
//					setTimeout(function(){
//					resolve({items: data});
					resolve(self.convertData(data));
					self.dataChange();
//					}, 11000);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					console.log(errorThrown);
					resolve({});
				}
			});
		} else {
			resolve({});
		}
	},
	methods: {
		getDataUrl: function() {
			return this.url;
		},
		delItemUrl: function(item) {
			return "";
		},
		convertData: function(data) {
			return {items: data};
		},
		remove: function(item){
			var self = this;
			jQuery.ajax({
				type: "GET",
				url: this.delItemUrl(item), // this.url,
				dataType: "json",
				success: function(data) {
					if (self.itemsCount === self.items.length) {
						self.items.$remove(item);
						self.itemsCount -= 1;
						self.dataChange();
					} else {
						self.reloadAsyncData();
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					console.log(errorThrown);
				}
			});
		},
		refresh: function(){
			this.reloadAsyncData();
//			if (this.$loadingAsyncData) {
//				alert(this.loadingAsyncDataText);
//				return;
//			}
//			var ix = this.url.indexOf('?');
//			this.url = (ix > 1 ? this.url.substring(0, ix+1) : this.url + "?") + new Date().getTime();
		},
		dataChange: function(){}
	},
	template: '<slot v-for="item in items">无模版</slot>'
});
Vue.component('ajaxList', AjaxList);


var SkeletonList = AjaxList.extend({
	data: function(){
		return {
			ajaxType: "POST",
			ajaxData: {
				start: 0,
				length: 5
			}
		}
	},
	methods: {
		getDataUrl: function() {
			return this.url + "/v_page.json";
		},
		delItemUrl: function(item) {
			return this.url + "/v_del/"+item.id+".json"; // this.url,
		},
		convertData: function(data) {
			return {
				items: data.data,
				itemsCount: data.recordsFiltered
			};
		}
	}
});

//})();