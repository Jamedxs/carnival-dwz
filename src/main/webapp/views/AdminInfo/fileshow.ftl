<#include "/common/pageForm.ftl">
<script>
function down(name){
	
	$.ajax({ 
	     url:"/carnival/?spm=${encryption('AdminInfo/existsfile')}&txtName="+name,
	     type:"post", 
	     dataType:"JSON",
	     success:function(data){
	    	 if(data==1){
	    		 location.href="/carnival/?spm=${encryption('AdminInfo/downloadfile')}&txtName="+name;
	    	 }else{
	    		 alertMsg.info("文件名可能出现乱码!");
	    	 }
	     },
	     error:function(){
	    	alertMsg.error("系统异常！");
	     }
   });
}

function check(){
	//获取lists
	var check = document.getElementsByName("lists");
	$.ajax({ 
	url:"/carnival/?spm=${encryption('AdminInfo/delfile')}&lists="+check,
	     type:"post", 
	     dataType:"JSON",
	     success:function(data){
	    	 if(data==1){
	    		 location.href="/carnival/?spm=${encryption('AdminInfo/downloadfile')}&txtName="+name;
	    	 }else{
	    		 alertMsg.info("文件名可能出现乱码!");
	    	 }
	     },
	     error:function(){
	    	alertMsg.error("系统异常！");
	     }
	 });
}
</script>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<!--  <li><a class="delete permission" onclick="check()"><span>删除文件</span></a></li>  -->
		</ul>
	</div>
	<table class="table" width="98%" layoutH="76">
		<thead>
			<tr>
				<!-- <th width="2%"><input type="checkbox" class="checkboxCtrl" group="lists"/></th> -->
				<th width="40%" align="center">文件名称</th>
				<th width="45%" align="center" >下载链接</th>
			</tr>
		</thead>
		<tbody>
			 <#list list as list>
			     <tr style="height:45px; text-align:center; valign-align:middle;">
			     	<!--  <td ><input name="lists" value="${(list)!}" type="checkbox" ></td> -->
			        <td style="padding-top:8px;">${(list)!}</td>
			        <td style="padding-top:8px;" >
			       	 	<input type="button" onclick="down('${(list)!}');" value="点击下载" style="cursor:hand;color:blue;"/>
 					</td>
			     </tr>
			</#list>
		</tbody>
	</table>
	<div class="panelBar">
	<div class="pages">
		<span style="color:red;">共【${size}】个文件 </span>
	</div>
</div>
</div>