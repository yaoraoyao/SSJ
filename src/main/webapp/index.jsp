<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<!--导入easyui的核心样式文件-->
		<link type="text/css" rel="stylesheet" href="easyui/themes/default/easyui.css" />
		<!--导入easyui的图标样式文件-->
		<link type="text/css" rel="stylesheet" href="easyui/themes/icon.css" />
		<!--导入jQuery的核心js文件-->
		<script type="text/javascript" src="easyui/jquery-1.11.3.min.js"></script>
		<!--导入easyui的核心js文件-->
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
		<!--导入easyui的汉化包js文件-->
		<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
			$(function(){
				
				//给左侧的树形菜单节点绑定点击事件
				$("#treeMenu").tree({
					onClick:function(node){
						//如果node.url不为null就一定是最低级菜单，被点击之后要在center部分添加一个选项卡panel
						if(node.url && node.url != ""){
							//添加之前一个判断一下该选项卡是否已经存在，不存在就添加，存在就选中并且刷新它
							var isExists = $("#tabs").tabs("exists", node.text);
							if(isExists){//存在就选中它并且刷新它
								$("#tabs").tabs("select", node.text);		//选中标题为node.text的那一个选项卡
								var tab = $('#tabs').tabs('getSelected');  	//获取当前选中的面板
								//更新当前选中的面板的内容
								$('#tabs').tabs('update', {
									tab: tab,
									options: {}
								});
							}else{//不存在就直接添加
								$("#tabs").tabs("add",{
									title:node.text,
									selected:true,
									closable:true,
									content:'<iframe src="'+node.url+'" width="100%" height="600px" frameborder="no" scrolling="auto"></iframe>'
								});
							}
						}
					}
				});
				
			});
		</script>
	</head>
	<!--
		easyui-layout  表示布局，可以在整个页面body，也可以用在body内部某个局部布局
			region表示区域的意思 取值有：east、west、south、north、center
			split表示分割，拖动改变宽度或者高度
			class="easyui-layout"的容器之内的每个div就代表了一块区域，每一个div都需要使用region属性来指定是哪个区域
				内部的每一个div都相当于panel
	-->
	<body class="easyui-layout">   
	    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>   
	    <div data-options="region:'west',title:'West',split:false" style="width:200px;">
	    	<ul id="treeMenu" class="easyui-tree" data-options="url:'tree.json',method:'get'"></ul>
	    </div> 
	    <!--
	    	fit表示是否填充父级容器尺寸大小
	    	class="easyui-tabs"表示当前div是一个选项卡的容器，这个div之内的每一个小div都是一个选项卡【本质就是一个panel】
	    -->
	    <div data-options="region:'center'"  style="padding:5px;background:#eee;">
	    	<div id="tabs" class="easyui-tabs" data-options="fit:true">
	    		<div title="欢迎">
	    			<div class="easyui-calendar" data-options="fit:true"></div>
	    		</div>
	    	</div>
	    </div>   
	</body> 
</html>