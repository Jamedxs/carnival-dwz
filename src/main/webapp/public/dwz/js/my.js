var thistip=null; //定义当前tip
function initMy(_box){	
    var $p = $(_box || document);
    $("a[rel=example_group]",$p).fancybox({
        'transitionIn'		: 'none',
        'transitionOut'		: 'none',
        'titlePosition' 	: 'over',
        'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
            return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
        }
    });
    //自动填充  
    
    $(".power_bt").each(function(){
        if(window.user_power_bt){	
            if(window.user_power_bt!="*"){
                var pw=","+$(this).attr("power")+",";				
                if(user_power_bt.indexOf(pw)==-1){
                    $(this).remove();
                }
            }        
        }
    
    })
	//解决排序问题,DWZ排序不自动显示图标
	if($("input[name='orderField']",$p).length!=0){
		var f=$("input[name='orderField']:last",$p).val();	
		
		if(f!=""){
			var ds=$("input[name='orderDirection']:last",$p).val();
			$("th[orderField='"+f+"']",$p).attr("class",ds);
		}
		f=null;
	}
    /*
     *  大小写自动转换函数
     *  shift，指定为小写，默认为大写
     */
    $(".capslock").change(function(){
        var shift=$(this).attr("shift")?true:false;
        var str=$(this).val();
        if(shift===false){
            str=(str.toUpperCase())
        }else{
            str=(str.toLowerCase())
        }
        $(this).val(str)
    })
    

    /*
	 * 全拼生成首字母转换方法
	 * 2013/7/18
	 * zhangpeng
	 */
    $(".first_letter").change(function(){
        var vo=$(this).val();
        var len=vo.length;
        var myform=$(this).parents("form");
        var obj=myform.find("."+$(this).attr("directing"));
        if(len>0){
            var first_letter=vo[0].toUpperCase();
            obj.val(first_letter);
        }
    })
    $(".jhstep",$p).each(function(){
        var $this=$(this);	
        var lilen=$this.find("li").length;		
        var liwidth=parseInt(100/lilen*100)/100;
        var pheight=$this.parent().css("height");		
        $this.find("li").css("width",liwidth+"%");
        $this.find("li").last().css("width",liwidth-0.1+"%")
        $this.find("em").css("line-height",pheight);
    });
    
/*
 *  联动插件
 *  linkage_href,获取数据的连接地址
 *  directing,指向需赋值的下拉列表名称
 *  linkage_format,最终输出数据的格式化名称，可指定组合显示
 *  linkage_keep,获取数据后可指定保留html字段的个数
 */
$(".linkage").change(function(){
    var $th=$(this);
    var linkage_href=$th.attr("linkage_href");
    var directing=$th.attr("directing");
    var nb=$th.val();
    var obj=$th.parents("form").find("select[name='"+directing+"']");
    var linkage_format=$th.attr("linkage_format");
    var linkage_keep=obj.attr("linkage_keep")?obj.attr("linkage_keep"):0;   
    $.ajax({ 
        url:linkage_href+nb,
        global:false,
        dataType:"json",
        success:function(data){          
            var str="";                  
            if(linkage_keep==0){       
                obj.html("");
            }else{             
                obj.find("option:gt("+(linkage_keep-1)+")").remove();               
            }           
            if(data){
                var current_format=linkage_format;                           
                for(var s in data){
                    current_format=linkage_format;   
                    for(var f in data[s]){                       
                        current_format=current_format.replace(new RegExp(f,"g"),data[s][f]);                       
                    }
                    str+="<option value='"+current_format.split("||")[0]+"'>"+current_format.split("||")[1]+"</option>";
                }
            }
            obj.append(str);
            obj.val(obj.attr("selectvl"));            
            obj.change();            
        }
    })
})
    //金额精确到两位小数
    $(".money",$p).blur(function(){		 
        var vl=$(this).val();		
        // 验证是否为数字
        if (vl.match(/[^,.\d]/) != null) {	
            $(this).val("0.00");
            return false;
        }
        // 验证输入内容
        if ((vl).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {		
            $(this).val("0.00");
            return false;
        }
        $(this).val(toDecimal2(vl));
    
    })
    /**
     * auto_href 指向的地址
     * auto_width 下拉的高度
     * auto_group 值组，其它带出值的控件都以CLASS
     * auto_show  显示的格式
     * auto_must 是否必填
     * callback 选择后调用
     * lable   产生label的格式
     * label_close_callback  移除label后调用的
     */
    $(".autocomplete",$p).each(function(){
        $thiss=$(this);
        //var id=$this.attr("id");
        var auto_href=$thiss.attr("auto_href");
        var auto_width=$thiss.attr("auto_width")?$(this).attr("auto_width"):100;	
        var auto_group=$thiss.attr("auto_group");
        var auto_show=$thiss.attr("auto_show");
        var auto_must=$thiss.attr("auto_must")?eval($thiss.attr("auto_must")):true;    
        var callback=$thiss.attr("callback")?$thiss.attr("callback"):"";
        var label=$thiss.hasClass("label");
        var label_close_callback=$thiss.attr("label_close_callback")?$thiss.attr("label_close_callback"):"";   
        $thiss.autocomplete(auto_href, {
            minChars:0,
            width: auto_width,
            mustMatch: auto_must,
            matchContains:true,
            selectFirst: false,
            formatItem: function(row, i, max) {			
                if(auto_show){
                    return eval(auto_show);
                }else{
                    return row[0];
                }
            }
        }).result(function(event, row, formatted) { 
			$("."+auto_group).val("");
            if(row){				
                if(auto_group){
                    var obj=$("."+auto_group);
                    var datastr;
                    for(var i=0;i<obj.length;i++){
                        datastr=$(obj[i]).attr("auto_data");                   
                        $(obj[i]).val(eval(datastr));
                    }
                }
                
                if(label){      
                    if($(this).parent().find("input[name='"+$(this).attr("name")+"[]'][value='"+row[1]+"']").size()==0){
                        $(this).parent().append("<span class='auto_label'>"+row[0]+"<a href='#' class='close_label' callback='"+label_close_callback+"'>X</a><input type='hidden' name='"+$(this).attr("name")+"[]' value='"+row[1]+"'></span>");
                    }
                    $(this).val("");
                }
                if(callback!=""){                 
                    eval(""+callback+"");
                }    
            }
        });	
    });
    $(".close_label").die().live("click",function(){          
        if($(this).attr("callback")&&$(this).attr("callback")!=""){           
            eval($(this).attr("callback"));
        }else{
            $(this).parent().remove();   
        }
    });
    if ($.fn.datepicker){
        $('div.moredate', $p).each(function(){
            var $this = $(this);
            var opts = {};
            if ($this.attr("format")) opts.pattern = $this.attr("format");
            if ($this.attr("yearstart")) opts.yearstart = $this.attr("yearstart");
            if ($this.attr("yearend")) opts.yearend = $this.attr("yearend");
            $this.datepicker(opts);
        //                $("input .date").click();
        });
    }
    
    //自动选中(复选框，单选框)2013/7/17最后修改     selectvl选中指  selectvl_for作用区域
    $("input[type='checkbox'][selectvl]",$p).each(function(){
        var $this=$(this);
        var $h=$p;
        var vl=$this.attr("selectvl");
        var selectvl_for=$this.attr("selectvl_for")?$this.attr("selectvl_for"):"";			
        if(selectvl_for!=""){
            $h=$(this).parentsUntil(selectvl_for).last().parent();
        }
        if(vl!=""){
            vl=","+vl+",";
            var ref_name=$this.attr("name");
            $("input[type='checkbox'][name='"+ref_name+"']",$h).each(function(){
                if(vl.indexOf(","+$(this).val()+",")!==-1){
                    $(this).attr("checked","checked");
                }
            });
        }
        if($(this).attr("selectvl_event")){   //如果需要触发选中事件。
            $(this).change();
        }
		ref_name=null;
		vl=null;
    });
    /*
	 *  2013/7/19新增
	 *  张鹏
	 *  完善DWZ自带的全选/反选插件
	 *  选中时判断全选checkbox是否选中
	 */
    $(":checkbox.checkboxCtrl", $p).each(function(){
        var $this=$(this);
        var form=$this.parents("form");
        var group=$this.attr("group");
        var box_num=form.find("input[name='"+group+"']").size()
        form.find("input[name='"+group+"']").change(function(){
            var num=form.find("input[name='"+group+"']:checked").size();
            if(num==box_num){
                $this.attr("checked",true);
            }else{
                $this.attr("checked",false);
            }
        })
        form.find("input[name='"+group+"']").change();
    })
    
    //下拉列表框选中
    $("select[selectvl]",$p).each(function(){
        var selectvl=$(this).attr("selectvl");
        $(this).val(selectvl);
        if($(this).attr("selectvl_event")){   //如果需要触发选中事件。
            $(this).change();
        }
    });
    //多选框选中
//    $("input[type='checkbox'][selectvl]",$p).each(function(){
//        var checkbox=$(this).attr("selectvl");
//        $(this).val(checkbox);
//        if($(this).attr("selectvl_event")){   //如果需要触发选中事件。
//            $(this).change();
//        }
//    });
    /*
 *起航自创日期插件
 *class="qihang_date"
 */
    $(".qihang_date",$p).each(function(){
        var name=$(this).attr("name");
        initial(name,$(this)); 
    })
    $("input[type='radio'][selectvl]",$p).each(function(){              //单选框选中效果
        var r_name=$(this).attr("name");
        var selectvl=$(this).attr("selectvl");
        var obj=$(this);
        if(selectvl==""){
            obj.attr("checked",true);
        }else{
            obj=$("input[type='radio'][name='"+r_name+"'][value='"+selectvl+"']",$p);
            obj.attr("checked",true);
        }
        if($(this).attr("selectvl_event")){   //如果需要触发选中事件。
            obj.change();
        }
    });
    $("input[validit]",$p).each(function(){
        var css="."+$(this).attr("validit");
        if($(this).val()!=""){
            $(css).html("个月");
        }else{
            $(css).html("永不过期");
        }
    });
    $('.ajax_ptip',$p).each(function(){                     //ZTree产品类型弹出层多选
        var url=$(this).attr("href");			
        $(this).poshytip({	
            showOn: 'none',
            alignTo: 'target',
            alignX: 'right',
            alignY: 'center',
            offsetX: 5,
            content: function(updateCallback) {
                $.get(url,function(data){						
                    updateCallback(data);
                });						
                return 'Loading...';
            }
        
        });
    });
    /* $('.ajax_ptip',$p).click(function(){  //Ztree弹出
        thistip=this;
        $(this).poshytip('show');
        $('#dialogBackground').show();
        return false;	
    })*/
    
    /**
	 * 备注信息
	 */
	$('.ajax_ptip', $p).each(function() { // 
		var url = $(this).attr("href");
		var weizhi = $(this).attr("name");
		var nowtime = new Date();
		if (weizhi != null) {
			var ali_X = $(this).attr("alignX") || "top";
			var ali_Y = $(this).attr("alignY") || "top";
		} else {
			var ali_X = $(this).attr("alignX") || "center ";
			var ali_Y = $(this).attr("alignY") || "center";
		}
		var off_X = $(this).attr("offsetX") || 0;
		var off_Y = $(this).attr("offsetY") || 0;

		$(this).poshytip( {
			alignTo : "target",
			alignX : ali_X,
			alignY : ali_Y,
			offsetX : off_X,
			offsetY : off_Y,
			content : function(updateCallback) {
				$.ajax( {
					url : url,
					global : false,
					success : function(data) {
					updateCallback(data);
					}
				});
				return 'Loading...';
			}
		});
	});
	$('.ajax_ptip', $p).click(function() {
		return false;
	})
    
    $(".comma",$p).change(function(){       //逗号转换类
        $(this).val(replace_sign("，",",",$(this).val()))
    })
    
    //tip功能
    $("[ptip]",$p).each(function(){
        $this=$(this);
        var tip=$this.attr("ptip");
        var op = {
            content:tip,
            fade:true
        };
        
        if($this.attr("type")=="text"){
            op["showon"]="focus";
        }
        var upAttrs = ["className","showOn","alignTo","alignX","alignY","offsetX","offsetY"];
        $(upAttrs).each(function(i){
            var true_op="ptip_"+upAttrs[i];//tip_showOn....都加上tip以免和别的冲突
            var op_name=upAttrs[i];
            if($this.attr(true_op)){
                op[op_name]=$this.attr(true_op);
            }
        });
        $this.poshytip(op);
    });
    
    /*
 *  treeButton,指定是否生成树按钮，默认true
 *  tree_href,传入到ajax的参数:model,id,pId,name
 *  treeW,指定生成div宽度
 *  treeH,指定生成div高度
 *  tree_reload,指定是否为动态树，默认为false
 *  before_fn,可增加函数先与树结构之前执行
 *  after_fn,可增加函数点击树结构之后执行
 *  tree_style,指定样式,ztree形式,ctree_x形式,ctree_y形式,默认为ztree
 *  ztreeSetting,指定为何种样式,默认为ztree无多选框模式
 *  ztree_top,指定ztree顶端显示数据
 *  ztree_nocheck,指定复选框是否显示 条件pId
 *  ztree_remove,指定不显示的树节点id
 *  ztree_pId,可指定获取所有上级id
 *  ztree_open,可指定是否打开节点
 */
    $(".mtree",$p).each(function(){
        $(this).attr("readonly",true)                                                   //默认文本框不可写
        var treeButton=$(this).attr("treeButton")=="false"?false:true;                  //是否生成按钮判断
        var button_num=$(this).parent().find(".inputButton").size()                     //判断按钮是否以存在
        if(treeButton&&button_num==0)$(this).after('<a class="inputButton mtree"></a>') //生成按钮方法
        $(".mtree").die().live("click",function(){                                      //点击文本框或按钮时执行的操作
            var $th=$(this);
            if($th.hasClass("inputButton")){						//判断是点击文本框还是点击按钮
                $th=$th.parent().find(".mtree")						//指向文本框
            }
            var name=$th.attr("name");                                                  //文本框name
            var tree_reload=$th.attr("tree_reload")=="true"?true:false;                 //是否是动态弹出层判断
            var before_fn=$th.attr("before_fn")?$th.attr("before_fn"):"";               //指定生成弹出层之前执行函数
            var treeid=name+"_ztree";                                                   //生成弹出层id名称
            var pObj=$th.parent();                                                      //文本框的上级对象
            pObj.addClass(name.split("name")[0]+"pObj")                                 //为上级对象添加标示
            $(".mtree").parent().css("z-index","0")                                     //初始化所有弹出层上级的优先级
            $(".ztreeDiv").not("#"+treeid+"div").hide();                                //隐藏其他弹出层
            $(".tree_reload").not("#"+treeid+"div").remove();                           //删除其他弹出层
            pObj.css("z-index","1");                                                    //为当前弹出层赋予优先级
            if(before_fn!=""){                                                          //点击时可前置执行自定函数
                var result=true;
                result=eval(""+before_fn+"()")                                          //提供字符串当函数执行
                if(false==result)return false;                                          //如有需要，可返回false终止生成弹出层
            }
            var tree_style=$th.attr("tree_style")?$th.attr("tree_style"):"ztree";       //样式
            var tree_num=pObj.find("#"+treeid).size();                                  //生成的弹出层个数
            if(tree_num==0){
                treeStyle($th,treeid,tree_style,tree_reload)                            //设置弹出层样式及获取数据
            }else{
                if(tree_reload){
                    pObj.find("#"+treeid+"div").remove();                               //动态树删除
                }
                pObj.find("#"+treeid+"div").toggle();					//显示隐藏
            }
        }).parents("body").bind('click',function(event){                       //body点击事件隐藏或删除弹出层(向华2013/10/6 去掉unbind())
            var result=true;
            if($(event.target).parents(".ztreeDiv").size()>0)result=false;              //弹出层是否被点击
            if($(event.target).is(".mtree")){                                           //按钮是否被点击
                var treeid=$(event.target).attr("name")?$(event.target).attr("name")+"_ztreediv":$(event.target).parent().find(".mtree").attr("name")+"_ztreediv";
                var suc_treeid=$(event.target).parent().find(".ztreeDiv").attr("id");
                if(treeid==suc_treeid)result=false;         
            }
            if(result){                                                                 //符合条件进行操作，隐藏或删除
                $(".ztreeDiv").hide();
                $(".tree_reload").remove();
            }
        })          
    })
    /********************
 *CTree复选框选中事件
 *7.23 修改完成
 *******************/
    $("input[type='checkbox'][name='Ctree']").die().live("click",function(){
        var $th=$(this);
        var str="";
        var nb="";
        var d="";
        var u_id=$th.parents("ul").attr("id");                                          
        var boxs=$th.parents("ul").find("input[type='checkbox'][name='Ctree']:checked");//或许呗选中的对象
        var input=u_id.split("name")[0];
        for(var i=0;i<boxs.size();i++){						//生成字符串及指向值
            if(i>0){
                d=",";
            }
            str+=d+$(boxs[i]).parent().text();
            nb+=d+$(boxs[i]).val();
        }
        var pObj=$th.parents("."+input+"pObj");					//弹出层及按钮所针对的父级
        pObj.find("input[name='"+input+"']").val(nb);                               //写入指向值
        pObj.find("input[name='"+input+"name']").val(str);				//写入字符串
    })
    

}//对应最上边函数结束

function jsonToTree(json,top_data,ztree_nocheck,ztree_open){
    var jsonStr="["+top_data;
    for(var o in json){
        if(json[o].pId==ztree_nocheck&&ztree_nocheck!=""){
            jsonStr+= "{ id:\""+json[o].id+"\", pId:\""+json[o].pId+"\", name:\""+json[o].name+"\", open:\""+ztree_open+"\",nocheck:true},"; 
        }else{
            jsonStr+= "{ id:\""+json[o].id+"\", pId:\""+json[o].pId+"\", name:\""+json[o].name+"\", open:\""+ztree_open+"\"},";   
        }
    }
    jsonStr+="]";
    return eval(jsonStr);
}
/*
* 弹出层生成div
* th,目标对象
* treeid生成弹出层id
* w,宽度
* h,高度
* tree_reload,是否是动态树:true,false
*/
function setPopup(th,treeid,tree_reload){
    var offset=th.offset();
    var reload=tree_reload==true?" tree_reload":"";
    var tree_css="ztreeDiv"+reload;
    var treeW=th.attr("treeW")?th.attr("treeW"):th.width()+18                       //宽度
    var treeH=th.attr("treeH")?th.attr("treeH"):"250";                              //高度
    var element = $("<div/>")                              
    .attr("id",treeid+"div") 
    .html("<div class='ztreediv' style='max-height:"+treeH+"px; min-height:auto; min-width:"+treeW+"px; max-width:auto'><ul style='padding-right:30px;' id='"+treeid+"' class='ztree'></ul></div>")
    .addClass(tree_css)
    .appendTo(th.parent())
    .show();        
    element.offset({
        top:offset.top + th.height()+5,
        left:(offset.left-2)
    })  
}
/*
 * ajax获取弹出层数据,并以指定样式显示
 * th,弹出层所对应的文本框
 * treeid,弹出层id名称
 * tree_style,指定的样式类型
 */

function treeStyle($th,treeid,tree_style,tree_reload){
    var tree_href=$th.attr("tree_href");                                                //ajax获取数据所需的连接地址
    $.ajax({
        url:tree_href,
        global:false,
        dataType:"json",
        success:function(data){
//                        alert(data)
            if($th.parent().find("#"+treeid).size()==0){
                setPopup($th,treeid,tree_reload);                                       //生成弹出层
                var mtreeObj=$th.parent().find("#"+treeid);                             //获取当前元素生成的弹出层对象
                switch(tree_style){
                    default:                                                            //默认为ztree样式
                        var top_data=$th.attr("ztree_top")?"{ id:0, pId:0, name:\""+$th.attr("ztree_top")+"\", open:true},":"";
                        var ztree_nocheck=$th.attr("ztree_nocheck")?$th.attr("ztree_nocheck"):"";
                        //ztree样式
                        var ztreeSetting=$th.attr("ztreeSetting")?$th.attr("ztreeSetting"):"{data:{simpleData:{enable:true}},callback:{onClick:zTreeOnClick}}";
                        eval("var setting = "+ztreeSetting);
                        var ztree_open=$th.attr("ztree_open")?$th.attr("ztree_open"):"true";  //默认为展开所有节点状态
                        $.fn.zTree.init(mtreeObj, setting, jsonToTree(data,top_data,ztree_nocheck,ztree_open));     //格式化显示输出
                        var vo=$th.parent().find("input[name='"+$th.attr("name").split("name")[0]+"']").val();//获取指向值
                        var treeObj=$.fn.zTree.getZTreeObj(treeid);                     //获取ztree对象
                        var remove=$th.attr("ztree_remove")?$th.attr("ztree_remove"):"";
                        if(vo!=""){                                                     //ztree选中指定值
                            if($th.attr("ztreeSetting")){
                                ztreeSleectNodes(treeObj,vo)                            //多选框样式ztree勾选事件
                            }else{
                                vo=vo.split(",")[0];                                    //逗号隔开形式取其首字符
                                var dataid=treeObj.getNodesByParam("id",vo,null);
                                treeObj.selectNode(dataid[0]);                          //选中事件
                            }
                            if(remove!=""){                                             //ztree删除指定节点
                                //alert(treeObj);
                                remove=remove.split(",");
                                for(var i=0;i<remove.length;i++){
                                    var removeid=treeObj.getNodesByParam("id",remove[i],null);
                                    treeObj.removeNode(removeid[0]);
                                }
                               
                            }
                        } //end if vo
                        break;
                    case "ctree":
                        var W=mtreeObj.width();
                        var str="<li style='width:"+W+"px;float:left;'>";
                        for(var o in data){
                            if(data[o]['pId']!=null&&data[o]['pId']==="0"){             //无限极形式
                                var end=o>0?"</li><li style='width:"+W+"px;float:left;'>":"";
                                str+=end+"<label style='width:auto;text-align:left;'>"+data[o]['name']+":</label>"
                            }else{                                                      //同等级形式
                                str+="<label style='width:auto;'><input name='Ctree' style='margin-right:5px;' type='checkbox' value='"+data[o]['id']+"'>"+data[o]['name']+"</label>"   
                            }
                        }
                        str+="</li>";
                        mtreeObj.html(str);
                        var hvo=","+$th.parent().find("input[name='"+$th.attr("name").split("name")[0]+"']").val()+",";
                        mtreeObj.find("input[type='checkbox'][name='Ctree']").each(function(){ //选中事件
                            if(hvo.indexOf(","+$(this).val()+",")!==-1){
                                $(this).attr("checked","checked");
                            }
                        })
                        break;
                }																						
            }
        }//end success
    })
}
/*
 *ztree点击事件
 *增加触发事件需在隐藏input或唯一input上添加ztree_fn
 *ztree_pId,可以指定点击事件后获取所有无极限上级的ID
 */
function zTreeOnClick(e, treeId, treeNode){
    var name= treeId.split("_ztree")[0];                                                //初始文本框name
    var h_name=treeId.split("name_ztree")[0];                                           //真正指向文本框name
    var inputvo="";                                                                     
    var pObj=$(e.target).parents("."+h_name+"pObj");                                    //弹出层及文本框唯一父元素对象
    var ztree_pId=pObj.find("input[name='"+name+"']").attr("ztree_pId")=="true"?true:false;		//是否获取所有上级ID，默认为false
    if(treeId.indexOf("_a")!=-1){
        h_name=name.split("-")[0]+"[]";
        name=name.split("-")[0]+"name[]";
        if(pObj.find(".inputButton[ztree_id='"+name+"']").parent().find("input[name='"+name+"']").size()>0){
            pObj.find(".inputButton[ztree_id='"+name+"']").parent().find("input[name='"+name+"']").val(treeNode.name);
            pObj.find(".inputButton[ztree_id='"+name+"']").parent().find("input[name='"+name+"']").val(treeNode.id);
        }else{
            inputvo=pObj.find(".inputButton[ztree_id='"+name+"']").parent().find("input[name='"+h_name+"']").val();
            pObj.find(".inputButton[ztree_id='"+name+"']").parent().find("input[name='"+h_name+"']").val(treeNode.name);
        }
    }else{
        pObj.find("input[name='"+name+"']").val(treeNode.name);                         //写入中文名
        if(ztree_pId){
            pObj.find("input[name='"+h_name+"']").val(limitless(treeNode));             //写入带所偶上级的值
        }else{
            pObj.find("input[name='"+h_name+"']").val(treeNode.id);                     //写入当前值
        }
        inputvo=pObj.find("input[name='"+h_name+"']").val();
    }
    var treeObj=$(e.target).parents(".ztreeDiv");                                       //弹出层对象
    if(treeObj.hasClass("tree_reload"))treeObj.remove();                                //动态树删除
    treeObj.hide();									//隐藏弹出层
    var after_fn=pObj.find("input[name='"+name+"']").attr("after_fn")?pObj.find("input[name='"+name+"']").attr("after_fn"):"";
    if(after_fn!=""){
        eval(""+after_fn+"(treeNode,name,inputvo)");                                    //提供字符串当函数执行
    }
}
/*
 *ztree选中事件
 */
function onCheck(e, treeId, treeNode) {
    var name= treeId.split("_ztree")[0];                                                //初始文本框name
    var h_name=treeId.split("name_ztree")[0];                                           //真正指向文本框name                                                              
    var pObj=$(e.target).parents("."+h_name+"pObj");                                    //弹出层及文本框唯一父元素对象
    var treeObj = $.fn.zTree.getZTreeObj(treeId);                                       //ztree对象
    var nodes = treeObj.getCheckedNodes(true);                                          //被选中的所有集合
    var str="";
    var ids="";
    var d="";
    for(var i=0;i<nodes.length;i++){                                                    //生成字符串及指向值
        if(i>0){
            d=","
        }
        str+=d+nodes[i].name;
        ids+=d+nodes[i].id;
    }
    pObj.find("input[name='"+name+"']").val(str);                                       //写入中文名
    pObj.find("input[name='"+h_name+"']").val(ids);                                     //写入当前值
    var after_fn=pObj.find("input[name='"+name+"']").attr("after_fn")?pObj.find("input[name='"+name+"']").attr("after_fn"):"";
    if(after_fn!=""){
        eval(""+after_fn+"(ids)")                                                       //提供字符串当函数执行
    }
}
/*********************************************
  *Ztree 选中节点，不带动父节点的选中
  *@tree ztree的id或者ztree对象（object）
  *@ids三种格式:1、单个id,2、多个id的数组([1,2,3,4,5]),3、多个id逗号隔开
  ************************************************/
function ztreeSleectNodes(tree,ids){
    if($.isPlainObject(tree)){                
        var treeObj=tree;
    }else{
        var treeObj = $.fn.zTree.getZTreeObj(tree);
    }
    if(!$.isArray(ids)){                              
        if(ids.toString().indexOf(",")==-1){ 
            treeObj.checkNode( treeObj.getNodeByParam("id", ids, null), true, false);                       
        }else{                  
            ids=ids.split(",");
        }
    }
    if($.isArray(ids)){
        for(var i=0;i<ids.length;i++){
            treeObj.checkNode( treeObj.getNodeByParam("id", ids[i], null), true, false); 
        }               
    }

}


/*
 * 上传文件调用方法（唯一)
 */
function upload_one_img(file,data,response){
    var datas=data.split("|");
    var myDate = new Date();
    var input_name=$("#"+file.id).parentsUntil(".upImg").last().parent().find("input[type='hidden']").attr("name");
    $("#"+file.id).parentsUntil(".upImg").last().parent().find(".add_img").html("<img src='/"+datas[0]+"?/"+myDate.getSeconds()+"'/><input type='hidden' name='"+input_name+"' value='"+datas[1]+"'/>");
}
//选中文字
var textSelect = function(o, a, b){
    
    //o是当前对象，例如文本域对象
    //a是起始位置，b是终点位置
    var a = parseInt(a, 10), b = parseInt(b, 10);
    
    var l = o.val().length;
    
    if(l){
        
        //如果非数值，则表示从起始位置选择到结束位置
        if(!a){
            a = 0;
        }
        if(!b){
            b = l;
        }
        //如果值超过长度，则就是当前对象值的长度
        if(a > l){
            a = l;
        }
        if(b > l){
            b = l;
        }
        //如果为负值，则与长度值相加
        if(a < 0){
            a = l + a;
        }
        if(b < 0){
            b = l + b;
        }
        
        if(o.createTextRange){//IE浏览器
            
            var range = o.createTextRange();
            range.moveStart("character",-l);
            range.moveEnd("character",-l);
            range.moveStart("character", a);
            range.moveEnd("character",b);
            range.select();
        }else{			
            o.setSelectionRange(a, b);
            o.focus();
        }
    }
};

function date_info_callback(json){
    DWZ.ajaxDone(json);	
    if (json.statusCode == DWZ.statusCode.ok){
        $.pdialog.closeCurrent();	
        if(json.rel){
            if(json.target=="dialog"){
                $.pdialog.reload_p(json.rel);
            }else{
                navTab.reloadFlag(json.rel);
            }
        }
    }

}

//保留两位小数

function toDecimal2(x) {  		
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return 0.00;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
}  
function replace_sign(r,s,str){                               //替换符号函数
    var reg=new RegExp(r,"g"); //创建正则RegExp对象 
    return str.replace(reg,s);
}
function limitless(treeNode,vo){                    //递归获取上级ID,Ztree调用
    vo=vo?vo:treeNode.id;
    var pNode = treeNode.getParentNode();
    if(pNode!=null&&pNode.id!="0"){
        vo+=","+pNode.id;
        vo=limitless(pNode,vo);
    }
    return vo;
}
//移除刚上传图片，图片上传类
function remove_upload_file(th,upload_type_en){  
    var rel=$(th).attr("rel");	   
    $("#"+rel).parentsUntil("."+upload_type_en).last().parent().remove();
}
//删除已使用的图片
function delete_upload_file(th,upload_type_en){
    var url=$(th).attr("url");
    alertMsg.confirm("您确认删除此图片吗？", {
        okCall: function(){
            $.ajax({
                type:'POST',
                url:url,
                data:{},
                dataType:"json",
                cache: false,
                success: function(json){
                    DWZ.ajaxDone(json);
                    remove_upload_file(th,upload_type_en);
                },
                error: DWZ.ajaxError
            });
        
        }
    });



}
//修改已使用图片
function update_use_file(th){	
    var url=$(th).attr("rel");
    var title=$(th).attr("title");
    $.pdialog.open(url, "update_use_file", title,{
        width:400,
        height:350
    });

}
//阻止事件冒泡
function stopBubble(e) {
	var e = e ? e : window.event;
	if (window.event) { // IE
		e.cancelBubble = true; 
	} else { // FF
		//e.preventDefault(); 
		e.stopPropagation(); 
	} 
}
//备注字数限制
function maxlimit(num,idn){
	var limitval = $("#"+idn+"").val();
	var limit = (num-limitval.length);
	if(limitval.length>num){
		$("#"+idn+"").val(limitval.substring(0,num));
	}else{
		if(limit<=10){
			$(".limit").css("color","red");
		}else{
			$(".limit").css("color","gray");
		}
		$(".limit").text(limit);
	}
}
//d
	
	function convertCurrency(currencyDigits,idstr) {
		 
	    var MAXIMUM_NUMBER = 99999999999.99;  //最大值
	    // 定义转移字符
	    var CN_ZERO = "零";
	    var CN_ONE = "壹";
	    var CN_TWO = "贰";
	    var CN_THREE = "叁";
	    var CN_FOUR = "肆";
	    var CN_FIVE = "伍";
	    var CN_SIX = "陆";
	    var CN_SEVEN = "柒";
	    var CN_EIGHT = "捌";
	    var CN_NINE = "玖";
	    var CN_TEN = "拾";
	    var CN_HUNDRED = "佰";
	    var CN_THOUSAND = "仟";
	    var CN_TEN_THOUSAND = "万";
	    var CN_HUNDRED_MILLION = "亿";
	    var CN_DOLLAR = "元";
	    var CN_TEN_CENT = "角";
	    var CN_CENT = "分";
	    var CN_INTEGER = "整";
	 
	    // 初始化验证:
	    var integral, decimal, outputCharacters, parts;
	    var digits, radices, bigRadices, decimals;
	    var zeroCount;
	    var i, p, d;
	    var quotient, modulus;
	 
	    // 验证输入字符串是否合法
	    /*if (currencyDigits.toString() == "") {
	        alert("还没有输入数字");
	        $("#Digits").focus();
	        return;
	    }*/
	    /*if (!currencyDigits.match(/[^,.\d]/)) {
	        alert("请输入有效数字");
	        $("#Digits").focus();
	        return;
	    }*/
	 
	    //判断是否输入有效的数字格式
	    /*var reg = /^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/;
	    if (!reg.test(currencyDigits)) {
	        alert("请输入有效格式数字");
	        $("#Digits").focus();
	        return;
	 
	    }*/
	 
	    currencyDigits = currencyDigits.replace(/,/g, ""); 
	    currencyDigits = currencyDigits.replace(/^0+/, ""); 
	    //判断输入的数字是否大于定义的数值
	    if (Number(currencyDigits) > MAXIMUM_NUMBER) {
	        alert("您输入的数字太大了");
	        $("#"+idstr).focus();
	        return;
	    }
	     
	    parts = currencyDigits.split(".");
	    if (parts.length > 1) {
	        integral = parts[0];
	        decimal = parts[1];
	        decimal = decimal.substr(0, 2);
	    }
	    else {
	        integral = parts[0];
	        decimal = "";
	    }
	    // 实例化字符大写人民币汉字对应的数字
	    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
	    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
	    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
	    decimals = new Array(CN_TEN_CENT, CN_CENT);
	    
	    outputCharacters = "";
	    //大于零处理逻辑
	    if (Number(integral) > 0) {
	        zeroCount = 0;
	        for (i = 0; i < integral.length; i++) {
	            p = integral.length - i - 1;
	            d = integral.substr(i, 1);
	            quotient = p / 4;
	            modulus = p % 4;
	            if (d == "0") {
	                zeroCount++;
	            }
	            else {
	                if (zeroCount > 0) {
	                    outputCharacters += digits[0];
	                }
	                zeroCount = 0;
	                outputCharacters += digits[Number(d)] + radices[modulus];
	            }
	            if (modulus == 0 && zeroCount < 4) {
	                outputCharacters += bigRadices[quotient];
	            }
	        }
	        outputCharacters += CN_DOLLAR;
	    }
	    // 包含小数部分处理逻辑
	    if (decimal != "") {
	        for (i = 0; i < decimal.length; i++) {
	            d = decimal.substr(i, 1);
	            if (d != "0") {
	                outputCharacters += digits[Number(d)] + decimals[i];
	            }
	        }
	    }
	    //确认并返回最终的输出字符串
	    if (outputCharacters == "") {
	        outputCharacters = CN_ZERO + CN_DOLLAR;
	    }
	    if (decimal == "") {
	        outputCharacters += CN_INTEGER;
	    }
	    //获取人民币大写
	    var taname=$("#"+idstr).get(0).tagName;
	    if(taname=='INPUT'){
	    	$("#"+idstr).val(outputCharacters);
	    }else if(taname=='LABEL'){
	    	$("#"+idstr).html(outputCharacters);
	    }
	}
