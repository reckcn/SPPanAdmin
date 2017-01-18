<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- jqgrid-->
    <link href="${ctx!}/assets/css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        /* Additional style to fix warning dialog position */

        #alertmod_table_list {
            top: 100px !important;
        }
        .ui-jqgrid tr.jqgrow td {
		text-overflow : ellipsis;
		}
    </style>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>用户管理</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
                        	<@shiro.hasPermission name="system:user:add">
                        		<button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                        	</@shiro.hasPermission>
                        </p>
                        <hr>
                        <div class="jqGrid_wrapper">
                            <table id="table_list"></table>
                            <div id="pager_list"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- jqGrid -->
    <script src="${ctx!}/assets/js/plugins/jqgrid/i18n/grid.locale-cn.js?0820"></script>
    <script src="${ctx!}/assets/js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>
    
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {

            $.jgrid.defaults.styleUI = 'Bootstrap';
            $("#table_list").jqGrid({
            	url: "${ctx!}/admin/user/list",
                datatype: "json",
                height: 450,
                autowidth: true,
                shrinkToFit: true,
                rowNum: 20,
                rowList: [10, 20, 30],
                jsonReader: {
             		 root: "content",   // json中代表实际模型数据的入口  
           		     page: "number",   // json中代表当前页码的数据  
           		     total: "totalPages", // json中代表页码总数的数据  
           		     records: "totalElements", // json中代表数据行总数的数据  
           		  	 id: "id",
           		   	 repeatitems: false
                },
                colModel: [
                    {
                    	label: "ID",
                        name: 'id',
                        index: 'id',
                        width: 40,
                        align: "center"
                    },{
                    	label: "用户名",
                        name: 'userName',
                        index: 'userName'
                    },{
                    	label: "所属角色",
                        name: 'roles',
                        index: 'roles',
                        formatter: function(cellvalue, options, rowObject) {
                        	var r = "";
                        	$(cellvalue).each(function (index,role){
                        		r = r + "【" + role.name + "】";
                        	});
                        	return r;
                        }
                    },{
                    	label: "昵称",
                        name: 'nickName',
                        index: 'nickName'
                    },{
                    	label: "性别",
                        name: 'sex',
                        index: 'sex',
                        sortable: false,
                        width: 60,
                        formatter: function(cellvalue, options, rowObject) {
                            if (cellvalue == '0') 
                            	return '<span class="label label-warning">女</span>';
                            return '<span class="label label-primary">男</span>';
                        }
                    },{
                    	label: "出生日期",
                        name: 'birthday',
                        index: 'birthday',
                        width: 100
                    },{
                    	label: "电话",
                        name: 'telephone',
                        index: 'telephone',
                        width: 110
                    },{
                    	label: "邮箱",
                        name: 'email',
                        index: 'email',
                        width: 200
                        
                    },{
                    	label: "状态",
                        name: 'deleteStatus',
                        index: 'deleteStatus',
                        width: 80,
                        sortable: false,
                        formatter: function(cellvalue, options, rowObject) {
                            if (cellvalue == '0') 
                            	return '<span class="label label-info">未删除</span>';
                            return '<span class="label label-danger">已删除</span>';
                        }
                    },{
                    	label: "锁定",
                        name: 'locked',
                        index: 'locked',
                        width: 80,
                        sortable: false,
                        formatter: function(cellvalue, options, rowObject) {
                            if (cellvalue == '0') 
                            	return '<span class="label label-info">未锁定</span>';
                            return '<span class="label label-danger">已锁定</span>';
                        }
                    },{
                    	label: "创建日期",
                        name: 'createTime',
                        index: 'createTime'
                    },{
                    	label: "修改日期",
                        name: 'updateTime',
                        index: 'updateTime'
                    },{
                        label: '操作', 
                        name: '', 
                        index: 'operate', 
                        width: 250, 
                        align: 'center',
                        formatter: function (cellvalue, options, rowObject) {
                        	var operateHtml = '<@shiro.hasPermission name="system:user:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+rowObject.id+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;</@shiro.hasPermission>';
                        	operateHtml = operateHtml + '<@shiro.hasPermission name="system:user:deleteBatch"><button class="btn btn-danger btn-xs" type="button" onclick="del(\''+rowObject.id+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;</@shiro.hasPermission>';
                        	operateHtml = operateHtml + '<@shiro.hasPermission name="system:user:grant"><button class="btn btn-info btn-xs" type="button" onclick="grant(\''+rowObject.id+'\')"><i class="fa fa-arrows"></i>&nbsp;关联角色</button></@shiro.hasPermission>';
                            return operateHtml;
                        }
                    }
                ],
                pager: "#pager_list",
                viewrecords: true,
                caption: "用户列表",
                hidegrid: false
            });
            // Add responsive to jqGrid
            $(window).bind('resize', function () {
                var width = $('.jqGrid_wrapper').width();
                $('#table_list').setGridWidth(width);
            });
        });
        
        function edit(id){
        	layer.open({
        	      type: 2,
        	      title: '用户修改',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '600px'],
        	      content: '${ctx!}/admin/user/edit/' + id,
        	      end: function(index){
        	    	  $('#table_list').trigger("reloadGrid");
       	    	  }
        	    });
        }
        function add(){
        	layer.open({
        	      type: 2,
        	      title: '用户添加',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '600px'],
        	      content: '${ctx!}/admin/user/add',
        	      end: function(index){
        	    	  $('#table_list').trigger("reloadGrid");
       	    	  }
        	    });
        }
        function grant(id){
        	layer.open({
        	      type: 2,
        	      title: '关联角色',
        	      shadeClose: true,
        	      shade: false,
        	      area: ['893px', '600px'],
        	      content: '${ctx!}/admin/user/grant/'  + id,
        	      end: function(index){
        	    	  $('#table_list').trigger("reloadGrid");
       	    	  }
        	    });
        }
        function del(id){
        	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
        		$.ajax({
    	    		   type: "POST",
    	    		   dataType: "json",
    	    		   url: "${ctx!}/admin/user/delete/" + id,
    	    		   success: function(msg){
	 	   	    			layer.msg(msg.message, {time: 2000},function(){
	 	   	    				$('#table_list').trigger("reloadGrid");
	 	   	    				layer.close(index);
	 	   					});
    	    		   }
    	    	});
       		});
        }
    </script>

    
    

</body>

</html>
