
<#include "/common/pageForm.ftl">
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${url}" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
						<td>
						</td>
				<tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<table class="table" width="98%" layoutH="109">
		<thead>
			<tr>
				<th width="10%" >企业名称</th>
				<th width="10%" >企业编号</th>
				<th width="10%" >用户编号</th>
				<th width="10%" >用户姓名</th>
				<th width="10%" >真实姓名</th>
				<th width="10%" orderField="login_time">登录时间</th>
				<th width="10%" >登录IP</th>
				
			</tr>
		</thead>
		<tbody>
			<#list p.results as list>
					<td >${(list.ENTERPRISE_NAME)!}</td>
					<td >${(list.ENTERPRISE_NO)!}</td>
					<td >${(list.USERNO)!}</td>
					<td >${(list.ADMIN_NAME)!}</td>
					<td >${(list.ADMIN_REALNAME)!}</td>
					<td >${(list.LOGIN_TIME)!}</td>
					<td >${(list.LOGIN_IP)!}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	<#include "/common/pages.ftl">
</div>

