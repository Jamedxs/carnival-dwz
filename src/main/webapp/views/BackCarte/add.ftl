
<div class="pageContent" style="overflow:hidden;">
	<form method="post" action="${baseClass}/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="50" style="background-color:#ecf2f4;overflow:hidden;padding-bottom:0">
			<fieldset>
				<p>
					<label style="width:60px;">编号</label>
					<input type="text" name="id" value="${(backCarte.id)!}"/>
				</p>
				<p>
					<label style="width:60px;">父菜单标识</label>
					<input type="text" name="parents" value="${(backCarte.parents)!}"/>
				</p>
				<p>
					<label style="width:60px;">屏蔽分公司</label>
					<input type="text" name="shield_filiale" value="${(backCarte.shield_filiale)!}"/>
				</p>
				<p>
					<label style="width:60px;">菜单介绍</label>
					<input type="text" name="carte_intro" value="${(backCarte.carte_intro)!}"/>
				</p>
				<p>
					<label style="width:60px;">菜单等级</label>
					<input type="text" name="levels" value="${(backCarte.levels)!}"/>
				</p>
				<p>
					<label style="width:60px;">菜单名称</label>
					<input type="text" name="carte_name" value="${(backCarte.carte_name)!}"/>
				</p>
				<p>
					<label style="width:60px;">打开方式</label>
					<input type="text" name="target" value="${(backCarte.target)!}"/>
				</p>
				<p>
					<label style="width:60px;">REL属性</label>
					<input type="text" name="rel" value="${(backCarte.rel)!}"/>
				</p>
				<p>
					<label style="width:60px;">平台标识 (0-总后台、1-分公司、2-财务、3-代理商、4-商户)</label>
					<input type="text" name="belong_terrace" value="${(backCarte.belong_terrace)!}"/>
				</p>
				<p>
					<label style="width:60px;">URL地址</label>
					<input type="text" name="href" value="${(backCarte.href)!}"/>
				</p>
				<p>
					<label style="width:60px;">菜单标识</label>
					<input type="text" name="parent_id" value="${(backCarte.parent_id)!}"/>
				</p>
				<input type="hidden" name="id" value="${(baseinfoClass.id)!}">
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>

