<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>员工列表</title>
		<meta charset="utf-8">
		<!--导入easyui的核心样式文件-->
		<link type="text/css" rel="stylesheet" href="/easyui/themes/default/easyui.css" />
		<!--导入easyui的图标样式文件-->
		<link type="text/css" rel="stylesheet" href="/easyui/themes/icon.css" />
		<!--导入jQuery的核心js文件-->
		<script type="text/javascript" src="/easyui/jquery-1.11.3.min.js"></script>
		<!--导入easyui的核心js文件-->
		<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
		<!--导入easyui的汉化包js文件-->
		<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="/easyui/common.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#dg").datagrid({
					url:"/employee/page.do",
					method:"post",
					striped:true,		//是否显示斑马线效果
					nowrap:true,		//如果列的字符串太长，默认是要换行显示，这个属性设置为true表示不换行
					pagination:true,	//是否显示分页工具栏
					rownumbers:true,	//是否在表格左侧显示行号[主键id字段的值往往是不连续的，所以搞了一个行号从1开始显示]
					//singleSelect:true,	//是否只能单选，默认可以多选
					queryParams:{},		//发送请求加载数据的时候的额外请求参数
					frozenColumns:[[{field:'abcdefg',checkbox:true}]],	//在表格左侧显示多选框，全选、全不选
					columns:[[    
				        {field:'id',title:'编号',width:100,hidden:true},    
				        {field:'headImage',title:'头像',width:50,formatter:function (value,row,index) {
				        	return value ? "<img src='"+value+"' style='width:38px;height:38px;margin-top:5px;' />" : "没有头像";
						}},
				        {field:'name',title:'姓名',width:100,align:"center"},
				        {field:'hireDate',title:'入职日期',width:100},
				        {field:'salary',title:'薪资',width:100},
				        {field:'department',title:'部门',width:100,formatter:function (value,row,index) {
							return value && value.name ? value.name : "";
						}}
				    ]],
				    toolbar: [{
						iconCls: 'icon-add',
						text:'新增',
						handler: function(){
							//弹出模态窗口
							$('#win').window('open');  // open a window
						}
					},{
						iconCls: 'icon-edit',
						text:'编辑',
						handler: function(){
							//获取到datagrid内已经被选中的行
							var rows = $("#dg").datagrid("getSelections");
							if(rows.length == 0){
								$.messager.alert('错误',"请选择你要编辑的员工！","error"); 
								return;
							}
							if(rows.length > 1){
								$.messager.alert('错误',"你只能选择一行进行修改！","error"); 
								return;
							}
							var row = rows[0];
							$.getJSON("/employee/findById.do",{id:row.id},function(data){
								//表单回填
								$("#ffff").form("load", data);
								//弹出模态窗口
								$('#win').window('open');  // open a window
							});
						}
					},{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){
							//获取到datagrid内已经被选中的行
							var rows = $("#dg").datagrid("getSelections");
							if(rows.length == 0){
								$.messager.alert('错误',"请选择你要编辑的员工！","error"); 
								return;
							}
							var ids = [];
							for(var i=0;i<rows.length;i++){
								ids.push(rows[i].id);
							}
							//发送请求
							$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
							    if (r){  
							    	// 12,2,5
							        $.getJSON("/employee/delete.do",{ids:ids.join(",")},function(data){
							        	if(data.result == 200){
											$.messager.alert('消息',data.msg,"info"); 
											//重新刷新表格的数据
											$("#dg").datagrid('load',{
												pageNo:1,
												pageSize:10
											});
											$("#dg").datagrid("getPager").pagination({
												pageNumber:1,
												pageSize:10
											});
										}
							        });   
							    }    
							});
						}
					},'-','-']
				});
				//实现翻页功能
				$("#dg").datagrid("getPager").pagination({
					onSelectPage:function(pageNumber, pageSize){
						$("#dg").datagrid('loading');
						//load方法传入一个json对象用来取代datagrid的queryParams属性，将其放入请求中作为请求参数传递到后端，每次翻页的请求地址不变
						$("#dg").datagrid('load',{
							pageNo:pageNumber,
							pageSize:pageSize
						});
						//修改一下分页工具栏中显示的当前页码
						$(this).pagination({
							pageNumber:pageNumber,
							pageSize:pageSize
						});
						$("#dg").datagrid('loaded');
					}
				});
				//给btn-submit按钮绑定点击事件
				$("#btn-submit").click(function(){
					var params = $("#ffff").toJson();
					$.postJSON("/employee/save.do",params,function(data){
						if(data.result == 200){
							$.messager.alert('消息',data.msg,"info"); 
							//关闭模态窗口
							$('#win').window('close');  // close a window  
							//重新刷新表格的数据
							$("#dg").datagrid('load',{
								pageNo:1,
								pageSize:10
							});
							$("#dg").datagrid("getPager").pagination({
								pageNumber:1,
								pageSize:10
							});
							//重置一下表单
							$("#ffff").form("reset");
						}
					});
				});
			});
		</script>
		<style type="text/css">
			#ffff>div{
				width: 100%;
				height: 40px;
				text-align: center;
			}
			#ffff>div:first-child {margin-top: 20px;}
			.in{width: 200px;height: 20px;border:1px solid #ccc;border-radius: 5px;}
			.in:focus{border:1px solid #66AFE9;box-shadow: 0px 0px 10px 1px #66AFE9;}
		</style>
	</head>
	<body>
		<table id="dg"></table>
		<div id="win" class="easyui-window" title="新增/编辑员工信息" style="width:300px;height:385px;"   
		        data-options="iconCls:'icon-save',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,resizable:false">   
		    <form id="ffff">  
		    	<input type="hidden" name="id" id="empid"> 
			    <div>   
			        <input class="in" type="text" name="name" placeholder="姓名" />   
			    </div> 
			    <div>   
			        <input class="in" type="text" name="age" placeholder="年龄" />   
			    </div> 
			    <div>   
			        <input class="in" type="text" name="phone" placeholder="电话" />   
			    </div>
			    <div>   
			        <input class="in" type="text" name="email" placeholder="邮箱" />   
			    </div>
			    <div>   
			        <input class="in easyui-filebox" name="headImage" data-options="prompt:'头像'" />   
			    </div>
			    <div>   
			        <select id="departmentId" class="easyui-combobox in" name="departmentId" style="width:200px;"
			        	data-options="url:'/department/findDepartments.do',valueField:'id',textField:'name',value:'-1',editable:false"></select>
			    </div>
			    <div style="text-align: left;box-sizing: border-box;padding-left: 38px;">   
			        <input type="radio" name="gender" value="1" />男   
			        <input type="radio" name="gender" value="2" />女   
			    </div> 
			    <div style="text-align: right;box-sizing: border-box;padding-right: 38px;">
			    	<a id="btn-cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">取消</a>
			    	<a id="btn-submit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">提交</a>
			    </div>  
			</form> 
		</div>
	</body>
</html>