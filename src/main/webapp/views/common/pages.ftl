<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select name="numPerPage" onChange="navTabPageBreak({numPerPage:this.value})" selectvl="${(p.pageSize)!"20"}">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="50">50</option>
			<option value="100">100</option>
			<option value="200">200</option>
		</select>
		<span>共${p.totalRecord}条     &nbsp;&nbsp;第${p.pageNo}/${p.totalPage}页</span>
	</div>
	<div class="pagination" targetType="navTab" totalCount="${p.totalRecord}" numPerPage="${p.pageSize}" pageNumShown="10" currentPage="${p.pageNo}"></div>
</div>