$.fn.datetimepicker.defaults = {
	// 默认语言
	language : 'zh-CN',
	// 默认选择格式
	format : "yyyy-mm-dd hh:ii:ss",
	autoclose : true,
	todayBtn : true,
	// 选择板所在输入框位置
	pickerPosition : "bottom-left"
};

$(function() {

	var picker1 = $('#startTime').datetimepicker();
	var picker2 = $("#endTime").datetimepicker();

	// 动态设置最小值(选择前面一个日期后：后面一个日期不能小于前面一个)
	picker1.on('changeDate', function(e) {
		picker2.datetimepicker('setStartDate', e.date);
	});
	// 动态设置最大值(选择后面一个日期后：前面一个日期不能大于前面一个）
	picker2.on('changeDate', function(e) {
		picker1.datetimepicker('setEndDate', e.date);
	});

});

function modalShowQO(url) {
	$("#float-dialog").empty();
	$("#tempForm").ajaxSubmit({
		url : url,
		type : "post",
		success : function(result) {
			$("#float-dialog").append($(result));
		}
	})
}

function modalShow(url) {
	var options = {
		backdrop : false,
		show : true,
		remote : url
	}
	$("#myModal").modal(options);
}

jQuery(function() {
	$("#myModal").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
		$(this).find(".modal-content").children().remove();
	});
})

function remove(url) {
	$.confirm({
		title : '提示',
		content : '是否确认删除？',
		buttons : {
			ok : {
				text : '确定',
				btnClass : 'btn-success',
				keys : [ 'enter' ],
				action : function() {
					$.post(url);
				}
			},
			cancel : {
				text : '取消',
				btnClass : 'btn-danger'
			}
		}
	})
}

/**
 * Cookie utils
 * 
 * @param c_name
 * @param value
 * @param expiredays
 * @returns
 */
function setCookie(c_name, value, expiredays) {
	var exdate = new Date()
	exdate.setDate(exdate.getDate() + expiredays)
	document.cookie = c_name + "=" + escape(value)
			+ ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
}

function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = document.cookie.length;
			}
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

function clearCookie(c_name) {
	setCookie(c_name, "", -1);
}

function submitForm(id, url, method) {
	if (!url) {
		url = $("#" + id).attr("action");
	}

	if (!method) {
		method = $("#" + id).attr("method");
	}

	var validate = true;

	$("#" + id).find("input").each(function(i, e) {
		if ($(e).attr("required")) {
			if (!$(e).val()) {
				$(e).parents(".form-group").addClass("has-error");
				validate = false;
			}
		}
	})

	$(".has-error").find("input").focus(function() {
		$(this).parents(".has-error").removeClass("has-error");
	})

	if (validate) {
		$("#" + id).ajaxSubmit({
			url : url,
			type : method,
			success : function() {
				$("#myModal").modal('hide');
			}
		})
	}
}

function submitTaocan(id, url, method) {
	if (!url) {
		url = $("#" + id).attr("action");
	}

	if (!method) {
		method = $("#" + id).attr("method");
	}

	var validate = true;

	$("#" + id).find("input").each(function(i, e) {
		if ($(e).attr("required")) {
			if (!$(e).val()) {
				$(e).parents(".form-group").addClass("has-error");
				validate = false;
			}
		}
	})

	$(".has-error").find("input").focus(function() {
		$(this).parents(".has-error").removeClass("has-error");
	})

	var goodsDetailVal = "";
	var goodsDetailEle = $(".goods");
	goodsDetailEle.each(function(i, e) {
		var name = $(e).val();
		var count = $(e).parent().next().children(".count").val();
		if (name && count) {
			itemVal = name + ":" + count;
			goodsDetailVal = goodsDetailVal + "," + itemVal;
		}
	})

	$("#goodsDetail").val(goodsDetailVal);

	if (validate) {
		$("#" + id).ajaxSubmit({
			url : url,
			type : method,
			success : function() {
				$("#myModal").modal('hide');
			}
		})
	}
}

function closeFD() {
	$("#float-dialog").empty();
}

//确认发货
function confirmSend(id) {
	var url = '/goods_order/confirm';
	$.confirm({
		title : '提示',
		content : '是否确认发货？',
		buttons : {
			ok : {
				text : '确定',
				btnClass : 'btn-success',
				keys : [ 'enter' ],
				action : function() {
					$.post(url,{id:id},function(data){
						$.alert(data.content)
					});
				}
			},
			cancel : {
				text : '取消',
				btnClass : 'btn-danger'
			}
		}
	})
}

//确认退款   状态改为3，订单状态改为3
function confirmRefuncd(id){
	var url='/goods_order/confirmRefun';
	$.confirm({
		title:'提示',
		content:'是否确认退款？',
		buttons:{
			ok:{
				text :'确定',
				btnClass :'btn-success',
				keys :[ 'enter' ],
				action :function(){
					$.post(url,{id:id,paystatus:3,orderstatus:3},function(data){
						$.alert(data.content);
					});
				}
			},
			cancel:{
				text: '取消',
				btnClass : 'btn-danger'
			}
		}
	})
}

//服务订单确认退款   状态改为3，订单状态改为3
//服务状态    0---待使用  1---带评论  2---已完成
function confirmRefuncd(id){
	var url='/service_order/confirmSRefun';
	$.confirm({
		title:'提示',
		content:'是否确认退款？',
		buttons:{
			ok:{
				text :'确定',
				btnClass :'btn-success',
				keys :[ 'enter' ],
				action :function(){
					$.post(url,{id:id,paystatus:3,orderstatus:2},function(data){
						$.alert(data.content);
					});
				}
			},
			cancel:{
				text: '取消',
				btnClass : 'btn-danger'
			}
		}
	})
}
function confirmSubmit(form) {
	$.confirm({
		title : '提示',
		content : '是否确认？',
		buttons : {
			ok : {
				text : '确定',
				btnClass : 'btn-success',
				keys : [ 'enter' ],
				action : function() {
					$("#" + form).ajaxSubmit({
						
						async:true,
						success : function() {
							$("#myModal").modal('hide');
						}
					})
				}
			},
			cancel : {
				text : '取消',
				btnClass : 'btn-danger'
			}
		}
	})
}

//审核通过
function confirmEOK(pemid,rid,state){
	var url='/recruit/confirmOK';
	if(state!='1'){
		$.alert("请选择待审批记录");
		return;
	}else{
		$.confirm({
			title:'审核',
			content:'是否确认?',
			buttons:{
				ok:{
					text:'通过',
					btnClass:'btn-success',
					keys:['enter'],
					action:function(){
						$.post(url, {"status":2,"pemid":pemid,"rid":rid},function(){
								$.alert("审核成功:通过");
							})
					}
				},
				cancel:{
					text:'不通过',
					btnClass:'btn-danger',
	                action:function(){
	                	$.post({
	                		url:url,
	                		data:{"status":3,"pemid":pemid,"rid":rid},
	                		async:true,
	                		success:function(){
	                			$.alert("审核成功:不通过");
	                		}
	                	})
	                }
				}
			}
		});
	}
}

//审核通过
function confirmPOK(pfaid,pid,state){
	//
	var url='/factoryPact/confirmOK';
	if(state!='0'){
		$.alert("请选择待审批记录");
		return;
	}else{
		$.confirm({
			title:'审核',
			content:'是否确认?',
			buttons:{
				ok:{
					text:'通过',
					btnClass:'btn-success',
					keys:['enter'],
					action:function(){
						$.post(url, {"status":1,"pfaid":pfaid,"pid":pid},function(){
								$.alert("审核成功:通过");
							})
					}
				},
				cancel:{
					text:'不通过',
					btnClass:'btn-danger',
	                action:function(){
	                	$.post({
	                		url:url,
	                		data:{"status":2,"pfaid":pfaid,"pid":pid},
	                		async:true,
	                		success:function(){
	                			$.alert("审核成功:不通过");
	                		}
	                	})
	                }
				}
			}
		});
	}
}

/**
 * 日期格式化 用法new Date().format('yyyy-MM-dd HH:ss:mm')
 */
Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}   


