<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/zTree_v3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
var bathPath = "<%=request.getContextPath()%>";
var zTree;
var demoIframe;

var setting = {
	async: {
		enable: true,
		url:bathPath + "/user/zt_queryAllMenu.action",
		autoParam:["id"],
	},
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			return true;
		}
	}
};

$(document).ready(function(){
	initMyZtree();

});

function initMyZtree(){
    $.ajax({
        type: "POST",
        dataType: "json",
        url:bathPath + "/user/zt_queryAllMenu.action", 
        success: function(data) {
            zNodes=data;
            $.fn.zTree.init($("#tree"), setting, zNodes);
        }
    });
	
}

</script>
</head>
<body>
	<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
</body>
</html>