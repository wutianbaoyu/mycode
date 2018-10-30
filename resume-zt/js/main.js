// 获取id
function id(obj) {
    return document.getElementById(obj);
}
// 增加class名称
var mymusic=document.getElementById("bgMusic");

function pauseVid()
  {
  mymusic.pause();
  }
 function playVid()
  {
  mymusic.play();
  }
  
var tt=null;
var kkk;
var n=0;
var timer=0;
window.onload=function(){
	var li=document.getElementById("btn").getElementsByTagName("li");
	kkk=document.getElementById("imm").getElementsByTagName("a");
	for(var i=0;i<kkk.length;i++){
		if(i!=0){
			kkk[i].style.opacity=0;
		}
	}
	for(var j=0;j<li.length;j++){
			li[j].onmouseover=function(){
				var that=this;
				tt=setTimeout(function(){ var index=that.innerHTML-1;
					n=index;
					if(index <kkk.length){
						for(var o=0;o<li.length;o++){
							li[o].className="";
							kkk[o].style.opacity=0;
							kkk[o].style.zIndex=9998;
						}
						that.className="on";
						kkk[index].style.opacity=1;
						kkk[index].style.zIndex=9999;
						kkk[index].style.transition="opacity 0.8s";
					   leftf(-300,0,kkk[index]);
					}
				},100);

			};
		li[j].onmouseout=function(){
			clearTimeout(tt)
		}
		}

function SetWinHeight(obj)
{
    var test=obj;
    if (document.getElementById)
    {
        if (test && !window.opera)
        {
            if (test.contentDocument && test.contentDocument.body.offsetHeight) 
                test.height = test.contentDocument.body.offsetHeight; 
            else if(test.Document && test.Document.body.scrollHeight)
                test.height = test.Document.body.scrollHeight;
        }
    }
}

	var left=document.getElementById("left");
	var right=document.getElementById("right");
	var jiao=document.getElementById("jiao");
	var body=document.getElementById("cont");

	timer = setInterval("autoplay()",2000);
	body.onmouseover=function(){
		jiao.style.display="block";
		clearInterval(timer);
	};
	body.onmouseout=function(){
		jiao.style.display="none";
		timer = setInterval("autoplay()",2000);
	};
	left.onclick=function(){
		if(n>0){
			n--
		}else if(n==0){
			n=kkk.length-1;
		}
		var li=document.getElementById("btn").getElementsByTagName("li");
		li[n].onmouseover()
	};
	right.onclick=function(){
		n=n>=(kkk.length-1)?0:++n;
		var li=document.getElementById("btn").getElementsByTagName("li");
		li[n].onmouseover()
	}
}

;
function leftf(start,end,ele){ var tt=setInterval(function (){
	start+=10;
	ele.style.left=start+"px";
	if(start==end){
		clearInterval(tt)
	}
			},20)
}

function autoplay(){
	n=n>=(kkk.length-1)?0:++n;
	var li=document.getElementById("btn").getElementsByTagName("li");
	li[n].onmouseover()
};
function addClass(obj, sClass) { 
    var aClass = obj.className.split(' ');
    if (!obj.className) {
        obj.className = sClass;
        return;
    }
    for (var i = 0; i < aClass.length; i++) {
        if (aClass[i] === sClass) return;
    }
    obj.className += ' ' + sClass;
}

var i=0;
function change()
{
	obj=eval("div"+(i)+".style");
	obj.visibility="hidden";
	i=(i+1)%4;
	obj=eval("div"+(i)+".style");
	obj.visibility="visible";
	setTimeout("change()",1000);
	}


function removeClass(obj, sClass) { 
    var aClass = obj.className.split(' ');
    if (!obj.className) return;
    for (var i = 0; i < aClass.length; i++) {
        if (aClass[i] === sClass) {
            aClass.splice(i, 1);
            obj.className = aClass.join(' ');
            break;
        }
    }
}

function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
    if (isOpera) {
        return "Opera"
    }; //判断是否Opera浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return "FF";
    } //判断是否Firefox浏览器
    if (userAgent.indexOf("Chrome") > -1){
  return "Chrome";
 }
    if (userAgent.indexOf("Safari") > -1) {
        return "Safari";
    } //判断是否Safari浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        return "IE";
    }; //判断是否IE浏览器
}

function view() {
    return {
        w: document.documentElement.clientWidth || document.body.clientWidth,
        h: document.documentElement.clientHeight || document.body.clientHeight
    };
}
var viewHeight=function(){
	var section=document.getElementsByTagName("section");
	var iheight=view().h;
	section[0].style.height=iheight;
	for (var i = 0; i < section.length; i++) {
		section[i].style.height=iheight+"px";

	}
}

var tagSwitch=function() 
{
	var navP=document.getElementById("nav_bar");
	var next=document.getElementsByClassName("scroll");
    var con=document.getElementsByTagName("section");
    var tag=document.getElementsByTagName("aside")[0].children;
    con[0].style.display="block";
    tag[0].className="cur_a";
    for (var i = 0; i < tag.length; i++) {
    	tag[i].index=i;
    	tag[i].onclick=function(){
    		for (var n = 0; n < tag.length; n++) {
    			tag[n].className="";
    			con[n].style.display="none";
    		}
    		tag[this.index].className="cur_a";
    		con[this.index].style.display="block";
    	}
    }
    for (var i = 0; i < next.length; i++) {
    	next[i].index=i;
    	tag[i].index=i;
    	next[i].onclick=function(){
    		for (var n = 0; n < next.length; n++) {
    			tag[n].className="";
    			con[n].style.display="none";
    		}
    		con[this.index+1].style.display="block";
    		tag[this.index+1].className="cur_a";

    	}
    }
}

//judge


var checkUsername=function(){
	var username=document.getElementById("username").value;
	var usernamecheck=document.getElementById("check_username");
	usernamecheck.innerText="";
	var myreg=/^[\u4e00-\u9fa5 ]{2,20}$/;
	var myreg1=/^[a-zA-Z\/ ]{2,20}$/;
	if(username==""||username==null){
		usernamecheck.innerText="姓名不能为空";
	}
	else{
		if (myreg.test(username)||myreg1.test(username)) {
		usernamecheck.innerText="";		
		}
		else{
			usernamecheck.innerText="姓名格式错误";
		}
	}
}


var checkCon=function(){
	var usercon=document.getElementById("usercon").value;
	var userconcheck=document.getElementById("check_usercon");
	userconcheck.innerText="";
	if(usercon==""||usercon==null){
		userconcheck.innerText="内容不能为空";
	}
}

var usernameon=document.getElementById("username");
usernameon.onblur=checkUsername;

var myconon=document.getElementById("usercon");
myconon.onblur=checkCon;



var scrollFunc = function (e) {
    var con=document.getElementsByTagName("section");
        var tag=document.getElementsByTagName("aside")[0].children;  
    
    var direct = 0;
    e = e || window.event;
    if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件   
          
        if (e.wheelDelta > 0) { //当滑轮向上滚动时
            for (var i = 1; i < tag.length; i++) {
                if(tag[i].className=="cur_a"){
                    tag[i].className="";
                    con[i].style.display="none";
                    tag[i-1].className="cur_a";
                    con[i-1].style.display="block";
                    break;
                }
            }
        }
        if (e.wheelDelta < 0) { //当滑轮向下滚动时
            
            for (var i = 0; i < tag.length-1; i++) {
                var n=0;
                if(tag[i].className=="cur_a"){
                    tag[i].className="";
                    con[i].style.display="none";  
                    n=i+1;
                    tag[n].className="cur_a";
                    con[n].style.display="block";
                    break;
                }
            }

        }
    } else if (e.detail) {  //Firefox滑轮事件
        if (e.detail> 0) { //当滑轮向上滚动时
            for (var i = 1; i < tag.length; i++) {
                if(tag[i].className=="cur_a"){
                    tag[i].className="";
                    con[i].style.display="none";
                    tag[i-1].className="cur_a";
                    con[i-1].style.display="block";
                    break;
                }
            }
        }
        if (e.detail< 0) { //当滑轮向下滚动时
            for (var i = 0; i < tag.length-1; i++) {
                var n=0;
                if(tag[i].className=="cur_a"){
                    tag[i].className="";
                    con[i].style.display="none";  
                    n=i+1;
                    tag[n].className="cur_a";
                    con[n].style.display="block";
                    break;
                }
            }
        }
    }
    // ScrollText(direct);
}
// 给页面绑定滑轮滚动事件
if (document.addEventListener) {
    document.addEventListener('DOMMouseScroll', scrollFunc, false);
}
// //滚动滑轮触发scrollFunc方法

//以下是调用上面的函数
var browser = myBrowser();
if ("IE" == browser) {
    document.onmousewheel = scrollFunc;  
}else{
    window.onmousewheel= scrollFunc;  
}

viewHeight();
tagSwitch();
canhtml5();
cancss3();
canjs();
canjq();
canbs();
ps();
waited_develop();