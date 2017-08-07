<#include "/common/pageForm.ftl">
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="ajaxPost" action="${url}"  method="post">
		<div class="searchBar">
		<input type="hidden" name="carte_id" value="${(p.params.carte_id)!''}">
			<table class="searchContent">
				<tr>
					<td> 
						<label style="width:70px;">所属平台： </label>
						<select name="belong_platform" selectvl="${(p.params.belong_platform)!''}">
							<option value="1">总后台</option>
							<option value="0">商户端</option>
							<option value="2">银行端</option>
							<option value="">所有</option>
						<select>
					</td>
					<td>
							真实姓名:<input type="text" name="name" value="${(p.params.name)!''}" size=15/>
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
			<li><a class="add permission"  href="/carnival/?spm=${encryption("${action}/add")}" target="dialog" rel="AdminInfo_add" width="450" height="350"><span>添加</span></a></li>
			<li><a class="edit permission" href="/carnival/?spm=${encryption("${action}/add")}&id={id}&belong={belong}" target="dialog" rel="AdminInfo_add" width="450" height="350" warn="请选择用户信息修改" ptip="请选择用户信息修改" rowclick="true"><span>修改</span></a></li>
			<li><a class="delete permission" href="/carnival/?spm=${encryption("${action}/delete")}&id={id}" target="ajaxTodo" title="确定要删除【{name}】吗?" rel="AdminInfo_delete"><span>删除</span></a></li>
			<li><a class="add permission" href="/carnival/?spm=${encryption("/Permission/userlist")}&id={id}&belong={belongf}" target="navTab" rel="Permission_userlist" warn="请选择【总后台】用户进行权限设置" ptip="请选择【总后台】用户进行权限设置" ><span>权限设置</span></a></li>
			<li><a class="edit permission" href="/carnival/?spm=${encryption("${action}/initializesPass")}&id={id}" target="ajaxTodo" title="确定要初始化该用户密码吗？" rel="AdminInfo_initializesPass"><span>初始化密码</span></a></li>
			<li><a class="edit permission"  href="/carnival/?spm=${encryption("${action}/thawingaccount")}&id={id}&sta={sta}"  target="ajaxTodo" rel="AdminInfo_thawingaccount" title="您确认【解锁】-【{name}】用户 信息吗？" warn="请选择非【锁定】用户进行解锁" ptip="请选择非【锁定】用户进行解锁"><span>解锁</span></a></li>
		</ul>
	</div>
	<table class="table" width="98%" layoutH="109">
		<thead>
			<tr>
				<th width="10%">用户编号</th>
				<th width="8%">用户名</th>
				<th width="8%">真实姓名</th>
				<th width="10%">手机号</th>
				<th width="12%" orderField="add_time">注册日期</th>
				<th width="7%">所属商户编号</th>
				<th width="10%">所属角色</th>
				<th width="10%">所属平台</th>
				<th width="5%" orderField="status">状态</th>
				<th width="12%" orderField="login_time">登录时间</th>
				<th width="10%">登录ip</th>
			</tr>
		</thead>
		<tbody>
			<#assign belong_role=getCache("belong_role")>
			<#assign belong_platform=getCache("belong_platform")>
			<#assign admin_status=getCache("admin_status")>
			<#list p.results as list>
				<tr target="id" rel="${list.ID}">    
					<td target="name" rel="${list.ADMIN_REALNAME}">${(list.USERNO)!}</td>
					<td target="belong"<#if list.BELONG_PLATFORM!=2>rel="${encryption("${list.BELONG_PLATFORM}")}"</#if> rel="">${(list.ADMIN_NAME)!}</td>
					<td target="belongf"<#if list.BELONG_PLATFORM==1>rel="${encryption("${list.BELONG_PLATFORM}")}"</#if> rel="">${(list.ADMIN_REALNAME)!}</td>
					<td target="sta"<#if list.STATUS==2>rel="${encryption("${list.STATUS}")}"</#if> rel="">${(list.PHONE)!}</td>
					<td <#if list.STATUS==2>target="warn_status" rel="<#if list.STATUS==0>开通<#else>关闭</#if>"</#if>>${(list.ADD_TIME)!}</td>
					<td >${(list.ENTERPRISE_NO)!}</td>
					<td ><#if belong_role ??>${belong_role[(list.BELONG_ROLE)+""]}<#else>${(list.BELONG_ROLE)!}</#if></td>
					<td ><#if belong_platform ??>${belong_platform[(list.BELONG_PLATFORM)+""]}<#else>${(list.BELONG_PLATFORM)!}</#if></td>
					<td ><#if admin_status ??>${admin_status[(list.STATUS)+""]}<#else>${(list.STATUS)!}</#if></td>        
					<td >${(list.LOGIN_TIME)!}</td>
					<td >${(list.LOGIN_IP)!}</td>
				</tr>
			</#list>         
		</tbody>
	</table>
	<#include "/common/pages.ftl">
</div>

