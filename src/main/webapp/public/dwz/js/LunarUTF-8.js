var lunarInfo=new Array(
    0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
    0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
    0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
    0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
    0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
    0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
    0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
    0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
    0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
    0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
    0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
    0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
    0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
    0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
    0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0)
var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var Gan=new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
var Zhi=new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
var Animals=new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪");
var solarTerm = new Array("小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至")
var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758)
var nStr1 = new Array('日','一','二','三','四','五','六','七','八','九','十')
var nStr2 = new Array('初','十','廿','卅','　')
//var monthName = new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");
var monthName = new Array("01","02","03","04","05","06","07","08","09","10","11","12");

var sFtv = new Array()

var lFtv = new Array()

var wFtv = new Array(
    "0520 母亲节")

function lYearDays(y) {
    var i, sum = 348;
    for(i=0x8000; i>0x8; i>>=1)sum += (lunarInfo[y-1900]&i)?1:0;
    return(sum+leapDays(y))
}

function leapDays(y) {
    if(leapMonth(y))  return((lunarInfo[y-1900] & 0x10000)? 30: 29)
    else return(0)
}

function leapMonth(y) {
    return(lunarInfo[y-1900] & 0xf)
}

function monthDays(y,m) {
    return( (lunarInfo[y-1900] & (0x10000>>m))? 30: 29 )
}

function Lunar(objDate) {

    var i, leap=0, temp=0
    var baseDate = new Date(1900,0,31)
    var offset   = (objDate - baseDate)/86400000

    this.dayCyl = offset + 40
    this.monCyl = 14

    for(i=1900; i<2050 && offset>0; i++) {
        temp = lYearDays(i)
        offset -= temp
        this.monCyl += 12
    }

    if(offset<0) {
        offset += temp;
        i--;
        this.monCyl -= 12
    }

    this.year = i
    this.yearCyl = i-1864

    leap = leapMonth(i) 
    this.isLeap = false

    for(i=1; i<13 && offset>0; i++) {
      
        if(leap>0 && i==(leap+1) && this.isLeap==false)
        {
            --i;
            this.isLeap = true;
            temp = leapDays(this.year);
        }
        else
        {
            temp = monthDays(this.year, i);
        }

     
        if(this.isLeap==true && i==(leap+1)) this.isLeap = false

        offset -= temp
        if(this.isLeap == false) this.monCyl ++
    }

    if(offset==0 && leap>0 && i==leap+1)
        if(this.isLeap)
        {
            this.isLeap = false;
        }
        else
        {
            this.isLeap = true;
            --i;
            --this.monCyl;
        }

    if(offset<0){
        offset += temp;
        --i;
        --this.monCyl;
    }

    this.month = i
    this.day = offset + 1
}

function solarDays(y,m) {
    if(m==1)
        return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28)
    else
        return(solarMonth[m])
}
function cyclical(num) {
    return(Gan[num%10]+Zhi[num%12])
}

function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap,cYear,cMonth,cDay,dates) {

    this.isToday    = false;
    this.sYear      = sYear;
    this.sMonth     = sMonth;
    this.sDay       = sDay;
    this.week       = week;
    this.lYear      = lYear;
    this.lMonth     = lMonth;
    this.lDay       = lDay;
    this.isLeap     = isLeap;
    this.cYear      = cYear;
    this.cMonth     = cMonth;
    this.cDay       = cDay;

    this.color      = '';
    this.dates = dates;
    this.lunarFestival = ''; //农历节日
    this.solarFestival = ''; //国历节日
    this.solarTerms    = ''; //节气

}

function sTerm(y,n) {
    var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) )
    return(offDate.getUTCDate())
}

function calendar(y,m) {
    var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2
    var lDPOS = new Array(3)
    var n = 0
    var firstLM = 0

    sDObj = new Date(y,m,1)            //当月一日日期

    this.length    = solarDays(y,m)    //国历当月天数
    this.firstWeek = sDObj.getDay()    //国历当月1日星期几


    for(var i=0;i<this.length;i++) {

        if(lD>lX) {
            sDObj = new Date(y,m,i+1)    //当月一日日期
            lDObj = new Lunar(sDObj)     //农历
            lY    = lDObj.year           //农历年
            lM    = lDObj.month          //农历月
            lD    = lDObj.day            //农历日
            lL    = lDObj.isLeap         //农历是否闰月
            lX    = lL? leapDays(lY): monthDays(lY,lM) //农历当月最後一天

            if(n==0) firstLM = lM
            lDPOS[n++] = i-lD+1
        }

        //sYear,sMonth,sDay,week,
        //lYear,lMonth,lDay,isLeap,
        //cYear,cMonth,cDay
        this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7],
            lY, lM, lD++, lL,
            cyclical(lDObj.yearCyl) ,cyclical(lDObj.monCyl), cyclical(lDObj.dayCyl++) )


        if((i+this.firstWeek)%7==0)   this[i].color = 'red'  //周日颜色
        if((i+this.firstWeek)%14==13) this[i].color = 'red'  //周六二日颜色
    }

    tmp1=sTerm(y,m*2  )-1
    tmp2=sTerm(y,m*2+1)-1
    this[tmp1].solarTerms = ''
    this[tmp2].solarTerms = ''
    if(m==3) this[tmp1].color = 'red' //清明颜色

    for(i in sFtv)
        if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
            if(Number(RegExp.$1)==(m+1)) {
                this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' '
                if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = 'red'
            }

    for(i in wFtv)
        if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
            if(Number(RegExp.$1)==(m+1)) {
                tmp1=Number(RegExp.$2)
                tmp2=Number(RegExp.$3)
                this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' '
            }

    //农历节日
    /*
   for(i in lFtv)
      if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
         tmp1=Number(RegExp.$1)-firstLM
         if(tmp1==-11) tmp1=1
         if(tmp1 >=0 && tmp1<n) {
            tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1
            if( tmp2 >= 0 && tmp2<this.length) {
               this[tmp2].lunarFestival += RegExp.$4 + ' '
               if(RegExp.$3=='*') this[tmp2].color = 'red'
            }
         }
      }
   */
    //黑色星期五
    //if((this.firstWeek+12)%7==5)
    //   this[12].solarFestival += '黑色星期五 '

    //今日
    if(y==tY && m==tM) this[tD-1].isToday = true;

}

//====================== 中文日期
function cDay(d){
    var s;

    switch (d) {
        case 10:
            s = '初十';
            break;
        case 20:
            s = '二十';
            break;
            break;
        case 30:
            s = '三十';
            break;
            break;
        default :
            s = nStr2[Math.floor(d/10)];
            s += nStr1[d%10];
    }
    return(s);
}

///////////////////////////////////////////////////////////////////////////////

var cld;

function drawCld(SY,SM,th) {
    var i,sD,s,size;
    var isart;
    var dates;
    var b  
    var num=$(th).attr("date_num")?$(th).attr("date_num"):1;
    var Form=$(th).parents("form")
    var div=$(th).attr("name")?$(th).attr("name"):$(th).parents("table").parent().attr("id");
    var pave_today=$(th).attr("pave_today")?true:false;
    for(var s=0;s<num;s++){
        if(s>0)SM++;
        if(SM>=12)SM=0;
        var cld = new calendar(SY,SM);
        Form.find("#"+div+s).find("#YMBG").html(SY + "年" + monthName[SM] + "月");
        var tds
        var rel=Form.find("#"+div).find(".lunar").attr("rel");
        var vl=$("input[name='"+rel+"']").val();
        for(i=0;i<42;i++) {
            var sObj=Form.find("#"+div+s).find("#SD"+i);
            var lObj='';
            sObj.className = '';
            sD = i - cld.firstWeek;
            tds=sObj.parentsUntil("td");
            tds.unbind("click");
            if(sD>-1 && sD<cld.length) {
                dates=SY+"-"+(SM+1<10?"0"+(SM+1):(SM+1))+"-"+(sD+1<10?"0"+(sD+1):(sD+1));	
                tds.hover(function(){
                    if(!$(this).hasClass("td_select")){
                        $(this).addClass('td_over');
                    }
                },function(){
                    $(this).removeClass('td_over')
                });
                tds.bind("click",function(){
                    var form=tds.parents("form");
                    var newdt = new Date();  	
                    var today=newdt.getFullYear()+"-"+(newdt.getMonth()+1)+"-"+format_time(newdt.getDate());
                    var rel=$(this).parents("table").attr("rel");
                    var vl=form.find("input[name='"+rel+"']").val();
                    var dates=$(this).parent().attr("id").replace("date","");
                    if(dates<today&&pave_today==true)return false;
                    if($(this).hasClass("td_select")){
                        vl=vl.replace(dates+",","").replace(","+dates,"").replace(dates,"");
                        $(this).removeClass("td_select");
                    }else{
                        vl+=vl==""?dates:","+dates;
                        $(this).addClass("td_select");
                    }			
                    form.find("input[name='"+rel+"']").val(vl);
                });
                tds.parent().attr("id","date"+dates);
                if(vl.indexOf(dates)!=-1){
                    tds.addClass("td_select");
                }else{
                    tds.removeClass("td_select");
                }
                var str="<font style='float:right;margin-right:5px;'>"+(sD+1);
                if(cld[sD].isToday){
                    str+="今";
                }
                str+="</font>";
                sObj.html(str);
            }else { //非日期
                sObj.html("");
                lObj.innerHTML="";
            }
        }
    }
}



function changeCld(th) {
    drawCld(mYear,mMonth,th);
}

function pushBtm(K,th) {
    var dobj=$(th).parents("table").find("#YMBG").html();
    var input_name=$(th).parents("table").attr("rel");
    var inputObj=$(th).parents("form").find("input[name='"+input_name+"']");
    var arr=dobj.split("年");
    var arr1=arr[1].split("月");
    var num=$(inputObj).attr("date_num")?$(inputObj).attr("date_num"):1;
    mYear=arr[0];
    mMonth=parseInt(arr1[0])-1;
    switch (K){
        case 'YU' :
            mYear--;
            if(mYear<1900)mYear=1900;
            break;
        case 'YD' :
            mYear++;
            if(mYear>2050)mYear=2050;
            break;
        case 'MU' :
            mMonth=mMonth-num;
            if(mMonth<0){
                mMonth=12-num;
                mYear--;
            }
            break;
        case 'MD' :
            mMonth++;
            if(mMonth>11){
                mMonth=0;
                mYear++;
            }
            break;
        default :
    }
    var divid=$(th).parents("table").parent().attr("id");
    $("#"+divid+" .td_select").removeClass("td_select");
    changeCld(inputObj);
}

var Today = new Date();
var tY = Today.getFullYear();
var tM = Today.getMonth();
var tD = Today.getDate();
var mYear=tY,mMonth=tM;
//////////////////////////////////////////////////////////////////////////////

var width = "130";
var offsetx = 2;
var offsety = 16;

var x = 0;
var y = 0;
var snow = 0;
var sw = 0;
var cnt = 0;

var dStyle;


//显示详细日期资料
function mOvr(v) {
    var s,festival;
    var sObj=document.getElementById('SD'+ v);
    var d=sObj.innerHTML-1;

    //sYear,sMonth,sDay,week,
    //lYear,lMonth,lDay,isLeap,
    //cYear,cMonth,cDay

    if(sObj.innerHTML!='') {

        sObj.style.cursor = 's-resize';

        if(cld[d].solarTerms == '' && cld[d].solarFestival == '' && cld[d].lunarFestival == '')
            festival = '';
        else
            festival = '<TABLE WIDTH=100% BORDER=0 CELLPADDING=4 CELLSPACING=0 BGCOLOR="#333333"><TR><TD ALIGN="RIGHT">'+
            '<FONT style="color:#FFFFFF; font-size:12pt">'+cld[d].solarTerms + ' ' + cld[d].solarFestival + ' ' + cld[d].lunarFestival+'</FONT></TD>'+
            '</TR></TABLE>';
        s= '<TABLE WIDTH="540" BORDER=1 CELLPADDING="0" CELLSPACING=0 BGCOLOR="#000099"><TR><TD>' +
        '<TABLE WIDTH=100% BORDER=1 CELLPADDING=4 CELLSPACING=0><TR><TD ALIGN="RIGHT" nowrap><FONT style="color:#FFFFFF; font-size:12pt">'+
        cld[d].sYear+'年 '+cld[d].sMonth+'月 '+cld[d].sDay+'日<br>星期'+cld[d].week+'<br>'+
        '<font color="fuchsia">农历'+(cld[d].isLeap?'闰 ':' ')+cld[d].lMonth+'月 '+cld[d].lDay+'日</font><br>'+
        '<font color="yellow">'+cld[d].cYear+'年 '+cld[d].cMonth+'月 '+cld[d].cDay + '日</font>'+
        '</FONT></TD></TR></TABLE>'+ festival +'</TD></TR></TABLE>';
        if (snow == 0) {
            dStyle.left = x+offsetx-(width/2);
            dStyle.top = y+offsety;
            dStyle.visibility = "visible";
            snow = 1;
        }
    }
}

//清除详细日期资料
function mOut() {
    if ( cnt >= 1 ) {
        sw = 0
    }
    if ( sw == 0 )
    {
        snow = 0;
        dStyle.visibility="hidden";
    }
    else cnt++;
}

//取得位置
function mEvn() {
    x=event.x;
    y=event.y;
    if (document.body.scrollLeft)
    {
        x=event.x+document.body.scrollLeft;
        y=event.y+document.body.scrollTop;
    }
    if (snow){
        dStyle.left = x+offsetx-(width/2)
        dStyle.top = y+offsety
    }
}
function initial(_date,th) {	
    var num=th.attr("date_num")?th.attr("date_num"):1;
    var w=th.attr("dateW")?th.attr("dateW"):20;
    var h=th.attr("dateH")?th.attr("dateH"):15;
    var zuo="<a href='javascript:void(0);' class='datetable_zuo' onClick=\"pushBtm('MU',this)\"></a>";
    var you="<a href='javascript:void(0);' class='datetable_you' onClick=\"pushBtm('MD',this)\"></a>";
    for(var s=0;s<num;s++){
        var table="<table cellspacing='0' cellpadding='0' class='datetable lunar' rel='"+_date+"' id='"+(_date+s)+"'>";
        table+="<tr style='height:26px;'><td class='topss is_zuo' style='cursor:pointer;background:#F3F3F3;'></td>"
        table+="<td colspan='5' height='20' nowrap='nowrap' class='topss' style='font-size:9pt;background:#F3F3F3;' id='YMBG' align='center'></td>";
        table+="<td class='topss is_you' style='cursor:pointer;background:#F3F3F3;' ></td></tr>";
        table+="<tr style='height:26px;border-left:1px solid #E8E8E8'><td style='background:#D9F3B6;border:#D9F3B6;color:#FF6600;'>星期日</td>";
        table+="<td style='background:#D9F3B6;'>星期一</td>";
        table+="<td style='background:#D9F3B6;'>星期二</td>";
        table+="<td style='background:#D9F3B6;'>星期三</td>";
        table+="<td style='background:#D9F3B6;'>星期四</td>";
        table+="<td style='background:#D9F3B6;'>星期五</td>";
        table+="<td style='background:#D9F3B6;color:#FF6600;'>星期六</td></tr>";
        for(var i=0;i<6;i++) {
            table+="	  <tr align=\"left\">\n";
            for(var j=0;j<7;j++) {
                gNum = i*7+j
                table+="<td nowrap=\"nowrap\"  bgcolor='#FFFFFF' align='center' style='cursor:pointer;width:"+w+"px;height:"+h+"px'><div style='width:99%;height:98%;";
                if(j == 0||j == 6){
                    table+="color:#FF6600;";
                }else{
                    table+="color:#000000;";
                };
                table+="'><font id=\"SD" + gNum +"\" style=' float:left;width:100%;'  face=\"Arial Black\"";		
                table+=" TITLE=\"\"> d</font><br /><font id=\"LD" + gNum + "\" > </font></div></td>";
            }
            table+="</tr>\n";
        }
        table+='<tr><td style=" background: #D9F3B6;" colspan=\"7\"><p style="width:32%;"><label style="width:auto;text-align: right;font-size:12px;">按星期:</label><select name="xingqi" onchange="date_data_change(this)"><option value="7">请选择</option><option value="0">星期日</option><option value="1">星期一</option><option value="2">星期二</option><option value="3">星期三</option><option value="4">星期四</option><option value="5">星期五</option><option value="6">星期六</option></select></p>';
        table+='<p style="width:36%;"><label style="width:auto;text-align: right;font-size:12px;">按日期:</label><input type="text" name="date_day1" class="digits" style="width:30px;" maxlength="2"/><label style="width: auto">到</label><input type="text" name="date_day2" class="digits" maxlength="2" style="width:30px;"/></p>';
        table+='<p style="width:32%;"><label style="width:auto;text-align: right;font-size:12px;">间隔天数:</label><input type="text" class="digits" name="date_day3" maxlength="2" style=width:30px;"/><a href="javascript:void(0);" style=";float:right;" class="btnAdd" onclick="date_datas(this)"></a></p></td></tr>'
        table+="</table>";
        th.parents("form").find("#"+_date).append(table);
        th.parents("form").find("#"+_date+" td").width(w)
        if(s==0){
            th.parents("form").find("#"+_date+s+" .is_zuo").append(zuo);
        }
        if(s==num-1){
            th.parents("form").find("#"+_date+s+" .is_you").append(you);
        }
    }
    //    document.getElementById(_date).innerHTML=shtml;
    drawCld(tY,tM,th);
}
function date_datas(th){                //起航日期插件日期多选事件按起止日期
    var tab=$(th).parents(".datetable").attr("id");
    var vo1=$("#"+tab+" input[name='date_day1']").val()?$("#"+tab+" input[name='date_day1']").val():"";//开始日期
    var vo2=$("#"+tab+" input[name='date_day2']").val()?$("#"+tab+" input[name='date_day2']").val():vo1;//结束日期
    var vo3=$("#"+tab+" input[name='date_day3']").val()?$("#"+tab+" input[name='date_day3']").val():0;//间隔日期
    var form=$(th).parents("form");
    var input_name=$(th).parents(".datetable").attr("rel");
    var div=$(th).parents("table").parent();
    var pave_today=form.find("input[name='"+input_name+"']").attr("pave_today")?true:false;
    var newdt = new Date();
    var today=newdt.getFullYear()+"-"+(newdt.getMonth()+1)+"-"+format_time(newdt.getDate());
    var data=$(form).find("input[name='"+input_name+"']").val();
    var YMD=$(th).parents("table").find("#YMBG").text().replace("年","-").replace("月","-");
    var str="";
    if(vo1==""&&vo2==""){
        var sevo=$("#"+tab+" .td_select");
        for(var o=0;o<sevo.size();o++){
            str=$(sevo[o]).parents("td").attr("id");
            str=str.replace("date","");
            data=data.replace(str+",","").replace(","+str,"").replace(str,"").replace(",","");
            $(sevo[o]).removeClass("td_select")
            $(form).find("input[name='"+input_name+"']").val(data);
        }
        $("#"+tab+" select[name='xingqi']").val("7")
    }else{
        var sevo=$(th).parents("table").find(".td_select").parents("td");
        for(var i=0;i<sevo.length;i++){
            var show=$(sevo[i]).attr("id");
            show=show.replace("date","");
            if(show<today&&pave_today==true)continue;
            data=data.replace(show+",","").replace(","+show,"").replace(show,"").replace(",","");
            $(sevo[i]).find(".td_select").removeClass("td_select")
        }
        for(var i=parseInt(vo1);i<=parseInt(vo2);i++){
            if(i>vo1)i=i+parseInt(vo3);
            if(i<10){
                str=YMD+"0"+i;
            }else{
                str=YMD+i; 
            }
            if(str<today&&pave_today==true||$("#date"+str).length<=0)continue;
            var div=$(th).parents("table").find("#date"+str).find("div");
            if(!div.hasClass("td_select")){
                div.addClass('td_select');
            }
            data=data.replace(str+",","").replace(","+str,"").replace(str,"");
            if(data!=""){
                data+=","+str;
            }else{
                data+=str;
            }
        }
        $(form).find("input[name='"+input_name+"']").val(data);
        var td=$(th).parents("td");
        td.find("input[type='text']").val("")
    }
    
}
function date_data_change(th){                      //起航日期插件集成条件按星期选中事件
    var vo=$(th).val();
    var YMD=$(th).parents("table").find("#YMBG").text().replace("年","-").replace("月","-");
    var newdt = new Date();  	
    var today=newdt.getFullYear()+"-"+(newdt.getMonth()+1)+"-"+format_time(newdt.getDate());
    var form=$(th).parents("form");
    var input_name=$(th).parents(".datetable").attr("rel");
    var pave_today=form.find("input[name='"+input_name+"']").attr("pave_today")?true:false;
    var data=$(form).find("input[name='"+input_name+"']").val();
    if(vo==7){
        var td_se=$(th).parents("table").find(".td_select");
        for(var i=0;i<td_se.size();i++){
            str=$(td_se[i]).parents("td").attr("id").replace("date","");
            if(str<today&&pave_today==true)continue;
            data=data.replace(str+",","").replace(","+str,"").replace(str,"").replace(",","");
            $(form).find("input[name='"+input_name+"']").val(data);
            $(td_se[i]).removeClass("td_select");
        }
    }else{
        for(var i=vo;i<42;i++){
            SD="#SD"+i;
            i=parseInt(i)+parseInt(6)
            var date=$(th).parents("table").find(SD).parents("td").attr("id")==undefined?"":$(th).parents("table").find(SD).parents("td").attr("id");
            var str=date.replace("date","");
            if(str<today&&pave_today==true)continue;
            if(str!=""&&str.split("-")[0]==YMD.split("-")[0]&&str.split("-")[1]==YMD.split("-")[1]){
                data=data.replace(str+",","").replace(","+str,"").replace(str,"");
            }
            if(data!=""){
                data+=","+str;
            }else{
                data+=str;
            }
            $(form).find("input[name='"+input_name+"']").val(data);
            if(date!=""&&str.split("-")[0]==YMD.split("-")[0]&&str.split("-")[1]==YMD.split("-")[1]){
                var div=$(th).parents("table").find("#"+date).find("div");
                if(!div.hasClass("td_select")){
                    div.addClass('td_select');
                }
            }
        }
    }
}
function select_qihang_date(Obj,vo){                  //指定日期插件选择效果
    var pave_today=$(Obj).attr("pave_today")?true:false;
    var newdt = new Date();  	
    var today=newdt.getFullYear()+"-"+(newdt.getMonth()+1)+"-"+format_time(newdt.getDate());
    today=format_date(today);
    var show=$(Obj).find(".td_select")
    for(var i=0;i<show.length;i++){
        $(show[i]).removeClass("td_select")
    }
    vo=vo.split(",");
    var YM=$(Obj).find("#YMBG").text().replace("年","-").replace("月","-");
    var str="";
    for(var i=0;i<vo.length;i++){
        if(vo[i]!=""){
            var arr=(vo[i]).split("-");
            if(vo[i]<today&&pave_today==true)continue;
            var div=$(Obj).find("#date"+vo[i]).find("div");
            if(!div.hasClass("td_select")&&vo[i]==YM+arr[2]){
                div.addClass('td_select');
            }
            str+=","+vo[i];
        }
    }
    str=str.replace(",","");
    var name=$(Obj).attr("id");
    $(Obj).parents("form").find("input[name='"+name+"']").val(str)
}
//日期相加自动判断
function add_day(d,n){  
    var arr = d.split("-");   
    var newdt = new Date(Number(arr[0]),Number(arr[1])-1,Number(arr[2])+parseInt(n));  
    var date=newdt.getFullYear()+"-"+(newdt.getMonth()+1)+"-"+format_time(newdt.getDate());
    return date
}
//
function format_time(m){
    if(m<10)m= "0"+m;
    return m;
}