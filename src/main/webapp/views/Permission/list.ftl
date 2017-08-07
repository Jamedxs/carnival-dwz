
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
				<th width="10%" orderField="Id">主键</th>
				<th width="10%" orderField="action_class">类名</th>
				<th width="10%" orderField="lower_href">表单地址</th>
				<th width="10%" orderField="status">状态(0-启用，1-禁用)</th>
				<th width="10%" orderField="behavior_args">行为模板</th>
				<th width="10%" orderField="behavior">行为名称</th>
				<th width="10%" orderField="carte_id">所属菜单</th>
				<th width="10%" orderField="create_time">添加时间</th>
				<th width="10%" orderField="button_name">按钮名称</th>
				<th width="10%" orderField="href">按钮路径</th>
				<th width="10%" orderField="action_method">方法名称</th>
			</tr>
		</thead>
		<tbody>
			<#list p.results as list>
				<tr target="id" rel="${list.Id}">
					<td >${(list.ID)!}</td>
					<td >${(list.ACTION_CLASS)!}</td>
					<td >${(list.LOWER_HREF)!}</td>
					<td >${(list.STATUS)!}</td>
					<td >${(list.BEHAVIOR_ARGS)!}</td>
					<td >${(list.BEHAVIOR)!}</td>
					<td >${(list.CARTE_ID)!}</td>
					<td >${(list.CREATE_TIME)!}</td>
					<td >${(list.BUTTON_NAME)!}</td>
					<td >${(list.HREF)!}</td>
					<td >${(list.ACTION_METHOD)!}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	<#include "/common/pages.ftl">
</div>

