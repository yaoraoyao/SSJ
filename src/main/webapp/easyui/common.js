$.extend({
	postJSON : function(url, params, fn){
		$.post(url, params, function(data){
			fn(data);
		},"json");
	}
});
$.fn.extend({
	toJson: function(){
		var paramArray = $(this).serializeArray();  
        /*请求参数转json对象*/
        var jsonObj = {}; 
        //each是jQuery的foreach循环
        $(paramArray).each(function(){  
        	jsonObj[this.name] = this.value;  
        });  
        // json对象
        return jsonObj;
	}
});