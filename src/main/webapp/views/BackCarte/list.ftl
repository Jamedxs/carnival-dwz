<script>
	//清除缓存
	function clearCache(){   
		$.post("/carnival/?spm=${encryption("/Cache/clearCache/")}",function (json){
			DWZ.ajaxDone(json);
			navTab.reloadFlag("carteManage");
		});
	}
	//默认打开方式点击事件
	function openTabDeafault(val){
		var liObj = $("#jbsxBox",navTab.getCurrentPanel()).find(".tabsHeaderContent > ul > li");
		$(liObj[val]).click();
		$(liObj[val]).find("a").click();
	}
	//重新载入该页面
	function reloadFlag(){
		navTab.reloadFlag("BackCarte_list");
	}
	$(function (){
		//当局部刷新菜单点击事件触发时,显示+添加菜单和-删除菜单按钮
		$(".clickEvent a[rel='jbsxBox']",navTab.getCurrentPanel()).bind("click",function(){
			$("#carteIsShow").show();
		});
	});
	//删除菜单时获取删除菜单名称和id
	function getMenuName(){
		var id = $("input[name='id'][type='hidden']",navTab.getCurrentPanel()).val();
		var menuName = $("h2[class='contentTitle']",navTab.getCurrentPanel()).text();
		menuName = menuName.replace(/\s+/g,"").replace("||>>","");
		alertMsg.confirm("确认删除【"+menuName+"】菜单么？",{
			okCall:function(){
				$.post("${base}/BackCarte/delete/?id="+id,function (json){
					DWZ.ajaxDone(json);
					if(json.navTabId){
						navTab.reloadFlag(json.navTabId);
					}
				});
			}
		});
	}
</script>
<div class="pageContent" style="padding:5px">
	<div class="formBar">
		<ul style="float:left;height:30px;">
			<li style="margin-left:20px;">
				默认打开：
					<select id = "defaultOpen" onchange="openTabDeafault(this.value)">
						<option value="0">菜单基本设置</option>
						<option value="1">按钮\行为管理</option>
					</select>
			</li>
			<li style="margin-left:20px;">
				<div class="buttonActive">
					<div class="buttonContent">
						<button type="button" onclick="clearCache()">清除缓存</button>
					</div>
				</div>
			</li>
		</ul>
		<ul style="float:right;display:none;" id = "carteIsShow">
			<li><a class="button" onclick = "reloadFlag()"><span>+ 菜单添加</span></a></li>
			<li style="margin-left:5px;"><a class="button" href = "javascript:getMenuName();"><span>- 菜单删除</span></a></li>
		</ul>
	</div>
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsContent">
			<div>
				<div layoutH="80" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:30px; background:#fff">
				 	<#include "menu.ftl">
				</div>
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;height:500px;">
					<#include "prepare.ftl">
				</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
