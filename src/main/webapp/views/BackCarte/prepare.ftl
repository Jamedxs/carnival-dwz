<style>
	.pageFormContent span.information{color:#7F7F7F;}
</style>
<script>
	function navTabAjaxDone(json){
		DWZ.ajaxDone(json);
		if (json.navTabId){
			navTab.reloadFlag(json.navTabId);
		}
	}
	$(function(){
		var parentroot = "${(map['PARENTROOT'])?default(0)?number}";
		var $box;
		var url;
		var flag = false;
		var id = "${(map['ID'])!}";
		$("#id").attr("href","/carnival/?spm=${encryption("/Permission/list/")}&carte_id="+id);
		$("#dataPermission").attr("href","/carnival/?spm=${encryption("/DataPermission/list/")}&carte_id="+id);
		if(parentroot!=0){
			var defaultOpen = $("#defaultOpen",navTab.getCurrentPanel()).val();
			var obj = $(".tabsCurrentIndex",navTab.getCurrentPanel());
			$(obj).attr("currentIndex",defaultOpen);
			if(defaultOpen=="1"){
				$box = $("#btnPageForm");
				url = "/carnival/?spm=${encryption("/Permission/list/")}";
				flag = true;
			}
			if(defaultOpen=="2"){
				$box = $("#dataPageForm");
				url = "/carnival/?spm=${encryption("/DataPermission/list/")}";
				flag = true;
			}
			if(flag==true){
				var data = {"carte_id":id};
				urlReload($box,url,data);
			}
		}
	});
	function urlReload($box,url,data){
		$box.ajaxUrl({
			type:"POST", url:url, data:data, callback:function(){
				$box.find("[layoutH]").layoutH();
			}
		});
	}
</script>
<div class="panelBar" style="height:38px">
	<h2 class="contentTitle">&nbsp;&nbsp;||&nbsp;&nbsp;>>&nbsp;&nbsp;${(map["CARTE_NAME"])?default("添加菜单")}</h2>
</div>
<div class="tabs tabsCurrentIndex" currentIndex = "0" style="margin-top:-9px">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>菜单基本设置</span></a></li>
					<#if (map["PARENTROOT"])?default(0)?number !=0>
						<li><a href="/carnival/?spm=${encryption("Permission/list/")}&carte_id=${(map['ID'])!}" class="j-ajax" id = "btnManage"><span>按钮\行为管理</span></a></li>
					</#if>			
				</ul>
			</div>
		</div>
		<div class="tabsContent" >
			<div class="pageContent">
									
				<form method="post" action="/carnival/?spm=${encryption("/BackCarte/save/")}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
				<input name="id" value="${(map['ID'])!}" type = "hidden" />
				<input name="parent" value="${(map['PARENTS'])!'0'}" type = "hidden"/>
				<input name="parentId" value="${(map['PARENT_ID'])!}" type = "hidden"/>
				<div class="pageFormContent" layoutH="175" style="border: 1px solid #999999;background-color:#FFFFFF">
					<p style="width:auto">
						<label style="width:auto">菜单名称：</label>
						<input name="carte_name" class="required" size="30" value="${(map['CARTE_NAME'])!}" maxLength = "10" />
						<span class="information" style="margin-left:10px">10个字以内</span>
					</p>
					<div class="divider"></div>
					<p style="width:auto">
						<label style="width:auto">菜单URL：</label>
						<input name="href" class="required" size="30" value="${(map['HREF'])!}" />
						<span class="information" style="margin-left:10px">例如：BackZbPosPriceShow/list/</span>
					</p>
					<div class="divider"></div>
					<p style="width:auto">
					<label style="width:auto">打开方式：</label>
						<select name="target" selectvl = "${(map['TARGET'])! }">			
							<option value="navTab">navTab</option>
							<option value="dialog">dialog</option>
						</select>
						<span class="information" style="margin-left:10px">弹出窗(dialog) 标签模式(navTab)</span>
					</p>
					<div class="divider"></div>
					<p>
						<label style="width:auto">上级菜单：</label>
						<input type="text" class="mtree required" name="organ_nbname" value="${(map['PARENTCARTE'])!'主菜单'}" ztree_remove="${(map['PARENT_ID'])!}" treeH="200" treeW="185" size="27" ztree_open="false" ztree_top="主菜单" tree_href="/carnival/?spm=${encryption("/BackCarte/publicMenu/")}"/>
						<span class="information" style="margin-left:10px">此菜单所属上级菜单</span>
						<input type="hidden" name="organ_nb" value="${(map['PARENTS'])!'0'}"><!--上级菜单ID随选择而变-->
					</p>
					<div class="divider"></div>
					<p style="height:auto;width:100%">
						<label style="width:auto">菜单介绍：</label>
						<textarea class="editor" name="carte_intro" style="width:800px;height:200px" tools="simple">${(map["CARTE_INTRO"])!}</textarea>
					</p>
					<p>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存菜单</button></div></div>
					</p>
				</div>
			  </form>
			</div>
			<div id = "btnPageForm" class="pageFormContent" layoutH="174"></div>
			<div id = "dataPageForm" class="pageFormContent" layoutH="174"></div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
</div>	

	