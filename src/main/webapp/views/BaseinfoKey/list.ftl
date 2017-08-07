
<#include "/common/pageForm.ftl">
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${url}" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
						<td>
							ID:<input type="text" name="Id" value="${(p.params.xingming)!''}" size=15/>
						</td>
						<td>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
						</td>
				<tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${baseClass}/add" target="dialog" rel="BaseinfoClass_add" width="450"><span>添加</span></a></li>
			<li><a class="edit" href="${baseClass}/add?id={id}" target="dialog"  width="450" rel="BaseinfoClass_add" title="修改-{class_cn}"><span>修改</span></a></li>
			<li><a class="delete" href="${baseClass}/delete?id={id}" target="ajaxTodo" title="确定要删除【{class_cn}】吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="98%" layoutH="109">
		<thead>
			<tr>
				<th width="10%" orderField="Id"></th>
				<th width="10%" orderField="addtime">添加时间</th>
				<th width="10%" orderField="parent_key_name">父类KEY的名称</th>
				<th width="10%" orderField="key_name">KEY名称</th>
				<th width="10%" orderField="remark">备注</th>
				<th width="10%" orderField="status">0-启用，1-禁用</th>
				<th width="10%" orderField="key_value">值(内容)</th>
				<th width="10%" orderField="parent_key_en">父类KEY标识</th>
				<th width="10%" orderField="addname">添加人</th>
				<th width="10%" orderField="key_en">KEY的英文标识</th>
				<th width="10%" orderField="vl_num">值个数(0-无，1-一个，2-多个)</th>
			</tr>
		</thead>
		<tbody>
			<#list p.results as list>
				<tr target="id" rel="${list.Id}">
					<td >${(list.ID)!}</td>
					<td >${(list.ADDTIME)!}</td>
					<td >${(list.PARENT_KEY_NAME)!}</td>
					<td >${(list.KEY_NAME)!}</td>
					<td >${(list.REMARK)!}</td>
					<td >${(list.STATUS)!}</td>
					<td >${(list.KEY_VALUE)!}</td>
					<td >${(list.PARENT_KEY_EN)!}</td>
					<td >${(list.ADDNAME)!}</td>
					<td >${(list.KEY_EN)!}</td>
					<td >${(list.VL_NUM)!}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	<#include "/common/pages.ftl">
</div>

