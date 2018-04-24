$.fn.datetimepicker.defaults = {
	//默认语言
	language : 'zh-CN',
	//默认选择格式
	format : "yyyy-mm-dd hh:ii:ss",
	autoclose : true,
	todayBtn : true,
	//选择板所在输入框位置
	pickerPosition : "bottom-left"
};

$(function() {

	var picker1 = $('#startTime').datetimepicker();
	var picker2 = $("#endTime").datetimepicker();

	//动态设置最小值(选择前面一个日期后：后面一个日期不能小于前面一个)
	picker1.on('changeDate', function(e) {
		picker2.datetimepicker('setStartDate', e.date);
	});
	//动态设置最大值(选择后面一个日期后：前面一个日期不能大于前面一个）
	picker2.on('changeDate', function(e) {
		picker1.datetimepicker('setEndDate', e.date);
	});

});

function modalShow(url){
	var options = {
			backdrop:false,
			show:true,
			remote:url
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
		title:'提示',
		content:'是否确认删除？',
		buttons:{
			ok:{
				text:'确定',
				btnClass:'btn-success',
				keys:['enter'],
				action:function(){
					$.post(url);
				}
			},
			cancel:{
				text:'取消',
				btnClass:'btn-danger'
			}
		}
	})
}

/**
 * Cookie utils
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



function submitForm(id,url,method) {
	if(!url) {
		url = $("#"+id).attr("action");
	}

	if(!method) {
		method = $("#"+id).attr("method");
	}
	
	var validate = true;
	
	$("#"+id).find("input").each(function(i,e){
		if($(e).attr("required")) {
			if(!$(e).val()) {
				$(e).parents(".form-group").addClass("has-error");
				validate = false;
			}
		}
	})
	
	$(".has-error").find("input").focus(function(){
		$(this).parents(".has-error").removeClass("has-error");
	})
	
	if(validate) {
		$("#"+id).ajaxSubmit({
			url:url,
			type:method,
			success:function(){
				$("#myModal").modal('hide');
			}
		})
	}
}
