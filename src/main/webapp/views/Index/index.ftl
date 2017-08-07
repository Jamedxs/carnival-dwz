<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=11" />
<!--<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11" />-->
<title>全国青少年智力嘉年华后台管理系统</title>
<link href="${base}/public/dwz/zTree/css/zTreeStyle/zTreeStyle.css"  rel="stylesheet" type="text/css"/>
<link href="${base}/public/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base}/public/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base}/public/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${base}/public/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base}/public/dwz/My97DatePicker/skin/default/datepicker.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${base}/public/dwz/poshytip/tip-yellow/tip-yellow.css" rel="stylesheet" type="text/css" />
<link href="${base}/public/dwz/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css" />
<link href="${base}/public/dwz/kindeditor/themes/default/default.css"  rel="stylesheet" type="text/css">
<link href="${base}/public/dwz/themes/css/my.css"  rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/public/dwz/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<!--[if IE]>
<link href="${base}/public/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${base}/public/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<!--<script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>-->
<script type="text/javascript" src="${base}/public/dwz/js/LunarUTF-8.js"></script>	
<script src="${base}/public/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/jquery.autocomplete.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${base}/public/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${base}/public/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${base}/public/dwz/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${base}/public/dwz/kindeditor/kindeditor.js" type="text/javascript"  charset="utf-8"></script>
<script src="${base}/public/dwz/kindeditor/lang/zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${base}/public/dwz/zTree/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<script src="${base}/public/dwz/zTree/js/jquery.ztree.exhide-3.5.js" type="text/javascript"></script>
<script src="${base}/public/dwz/poshytip/jquery.poshytip.js" type="text/javascript"></script>
<script src="${base}/public/dwz/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
<script src="${base}/public/dwz/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/my.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/area.js" type="text/javascript"></script>
<script src="${base}/public/dwz/js/ajaxfileupload.js" type="text/javascript"></script>

<script type="text/javascript">
window.user_power_bt="";
$(function(){
   
	DWZ.init("${base}/public/dwz/dwz.frag.xml", {
		loginUrl:"${base}/AdminInfo/exitlogin",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:true,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			chick();
			$("#themeList").theme({themeBase:"${base}/public/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
function chick(){
	var count=${count};
	if (count == 0) {
			$.pdialog.open("${base}/AdminInfo/changePwd", "pass", "修改密码", {
				width : 400,
				height : 200,
				resizable : false,
				maxable : false,
				mask : true
			});
		}

	}
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com" style="display:none">标志</a>
				<ul class="nav">  
					<li><a  href="/carnival/?spm=${encryption("AdminInfo/updatePass")}"
					 target="dialog" rel="AdminInfo_updatePass" mask="true" mixable="false" width="410" height="205">修改密码</a></li>
					<li><a  href="${base}/AdminInfo/exitlogin">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
			<!-- navMenu -->
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
					<div class="accordion" fillSpace="sideBar">
						<!------------------------循环菜单----------------------------->
						<#if cartes ?? >
							<#list cartes as carte>
								<#if carte.LEVELS==1>
									<div class="accordionHeader">
										<h2><span>Folder</span>${carte.CARTE_NAME}</h2>
									</div>
									<#if (cartes[carte_index+1]) ??>
										<#if (cartes[carte_index+1]).LEVELS ?? >
											<#if (cartes[carte_index+1].LEVELS > carte.LEVELS)>
												<div class="accordionContent">
													<ul class="tree treeFolder">
											<#elseif (cartes[carte_index + 1].LEVELS == carte.LEVELS)>
												<div class="accordionContent"></div>
											</#if>
										</#if>
									</#if>
								</#if>
								<#if carte.LEVELS == 2>
									<li>
										<#if cartes[carte_index + 1] ?? >
											<#if (cartes[carte_index + 1].LEVELS > carte.LEVELS)>
												<a>${carte.CARTE_NAME}</a>
											<#else><!-- zb -->
												<a href="/carnival/?spm=${encryption("/${carte.HREF}?url=${carte.HREF}&carte_id=${carte.ID}")}" target="${carte.TARGET}" rel="${carte.REL}">${carte.CARTE_NAME}</a>
											</#if>
										<#else>
											<a href="/carnival/?spm=${encryption("/${carte.HREF}?url=${carte.HREF}&carte_id=${carte.ID}")}" target="${carte.TARGET}" rel="${carte.REL}">${carte.CARTE_NAME}</a>
										</#if>
									<#if cartes[carte_index + 1] ??>
										<#if (cartes[carte_index + 1].LEVELS > carte.LEVELS)>
											<ul>
											<#assign flag="true">
										<#elseif (cartes[carte_index + 1].LEVELS < carte.LEVELS)>
											</li></ul></div>
										<#else>
											</li>
										</#if>
									</#if>
								</#if>
								
							</#list>
						</#if>
					</div>
			</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<p> <strong>上次登录</strong>：${(lastTiem)!}</p>
								<p> <strong>登录 IP </strong>：${(loginIp)!} &nbsp;&nbsp;<strong>总次数</strong>：${(loginnum)!}</p>
							</div>
							<div class="right">
							</div>
							<p><span>欢迎您：${(admininfom.admin_name)!}(${(admininfom.admin_realname)!}) </span></p>
							<p>系统正在不断开发中</p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
						<p align="center" style="font-size: 50px;color: red;width: 100%">最新公告</p>
						<fieldset style="width: 80%;height: 80%;margin-left: auto;margin-right: auto;margin-top: 5%">
						<tr>
							<div style="font-size: 25px;margin-top: 10px" align="center">${(slist.THEME)!}</div>
							<div style="font-size: 15px;margin-top: 15px">${(slist.note)!}</div>
						</tr>
						</fieldset>
						<div style="float: right;">发布时间：${(slist.CREATE_TIME)!}</div></br>
						<div style="float: right;">发布人：${(slist.ADMIN_REALNAME)!}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2015 <a href="javascript:void(0)">银联天津分公司</a> Tel：</div>
</body>
</html>