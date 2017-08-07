
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
				<th width="10%" orderField="Id">编号</th>
				<th width="10%" orderField="enterprise_no">所属企业编号</th>
				<th width="10%" orderField="user_name">登录名</th>
				<th width="10%" orderField="login_time">登录时间</th>
				<th width="10%" orderField="login_state">登录状态0成功1失败</th>
				<th width="10%" orderField="real_name">姓名</th>
				<th width="10%" orderField="login_ip">登录ip</th>
				<th width="10%" orderField="note">内容0成功1密码错误2验证码错误</th>
				<th width="10%" orderField="login_date">登录日期</th>
			</tr>
		</thead>
		<tbody>
			<#list p.results as list>
				<tr target="id" rel="${list.Id}">
					<td >${(list.ID)!}</td>
					<td >${(list.ENTERPRISE_NO)!}</td>
					<td >${(list.USER_NAME)!}</td>
					<td >${(list.LOGIN_TIME)!}</td>
					<td >${(list.LOGIN_STATE)!}</td>
					<td >${(list.REAL_NAME)!}</td>
					<td >${(list.LOGIN_IP)!}</td>
					<td >${(list.NOTE)!}</td>
					<td >${(list.LOGIN_DATE)!}</td>
				</tr>
			</#list>
		</tbody>
	</table>
	<#include "/common/pages.ftl">
</div>

