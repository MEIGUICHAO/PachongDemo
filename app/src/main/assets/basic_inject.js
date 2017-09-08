/** 注：该JS文件用于存放常用函数，功用相关的函数放在Java文件中注入*/


/** No.1 模拟点击事件############################################################################################*/
//模拟点击事件
function doClickByRI(resId,time) {
    localMethod.resetComplete();
 var btn = document.getElementById(resId);
 if(null!=btn){
    setTimeout(function(){
        btn.click();
    },time*1000);
    }
}

function doClickByCN(className,time) {
    localMethod.resetComplete();
  var cn = document.getElementsByClassName(className);
  localMethod.JI_showToast("length："+cn.length);
  var btn = document.getElementsByClassName(className)[0];
  if(null!=btn){
    setTimeout(function(){
        btn.click();
    },time*1000);
    }
}

function tabByCN(className,time) {
  var cn = document.getElementsByClassName(className);
  localMethod.JI_showToast("length："+cn.length);
  var btn = document.getElementsByClassName(className)[0];
  if(null!=btn){
    setTimeout(function(){
        btn.trigger("tap");
    },time*1000);
    }
}

function doClickByCNandPosition(className,posititon) {
    localMethod.resetComplete();
  var cn = document.getElementsByClassName(className);
  var btn = document.getElementsByClassName(className)[posititon];
  if(null!=btn){
    setTimeout(function(){
        btn.click();
    },1*1000);
    }
}

function getLengthByCn(className) {
    localMethod.resetComplete();
  var cn = document.getElementsByClassName(className);
  localMethod.JI_showToast("length："+cn.length);
      localMethod.getBooleanByLength(cn.length);
}


function getDataByHotSells() {
    localMethod.resetComplete();
  var tab = document.getElementsByTagName("li");
    localMethod.JI_showToast("length："+tab[1].innerHTML);

    tab[1].click();
}

function getHotSellsListData() {
    localMethod.resetComplete();
  var title = document.getElementsByClassName("d-title");
  var price = document.getElementsByClassName("d-price");
  var num = document.getElementsByClassName("d-num");
//    localMethod.JI_LOG("length："+tab.length);


    for (var i = 0; i <title.length ; i++) {
        localMethod.JI_LOG(title[i].innerHTML);
        localMethod.JI_LOG(price[i].innerHTML);
        localMethod.JI_LOG(num[i].innerHTML);
    }

}

function getTmallHotSellsListData() {
    localMethod.resetComplete();
  var title = document.getElementsByClassName("tii_title");
  var price = document.getElementsByClassName("tii_price");
  var num = document.getElementsByClassName("tii_sold");
//    localMethod.JI_LOG("length："+tab.length);


    for (var i = 0; i <title.length ; i++) {
        localMethod.JI_LOG(title[i].innerHTML);
        localMethod.JI_LOG(price[i].innerHTML);
        localMethod.JI_LOG(num[i].innerHTML);
    }

}

//模拟触摸事件
function doTapByRI(resId,index) {
    localMethod.resetComplete();
   if(null==index){index=0;}
   $("#"+resId).eq(index).trigger("tap");
}

function doTapByCN(className,index) {
    localMethod.resetComplete();
  if(null==index){index=0;}
  $("."+className).eq(index).trigger("tap")
}

//根据父控件查找子控件再触摸
function doTapByParentCN(parentCN,className,index) {
    localMethod.resetComplete();
  if(null==index){index=0;}
  $("."+parentCN).children("."+className) .eq(index).trigger("tap");
}

function doTapForScanGoods(parentCN,index) {
    localMethod.resetComplete();
   if(null==index){index=0;}
   $("."+parentCN).eq(index).children(".p").children("a").trigger("tap");
}


/** No.2 输入文本信息至输入框中############################################################################################*/
function doInputByRI(resId,context,time) {
    localMethod.resetComplete();
   var text = document.getElementById(resId);
    setTimeout(function(){
        text.value = context;
    },time*1000);
}

function doInputByCN(className,context,time) {
    localMethod.resetComplete();
    var text = document.getElementsByClassName(className)[0];
    setTimeout(function(){
        text.value = context;
    },time*1000);
}


/** No.3 获取控件的文本信息###########################################################################################*/
function doGetTextByRI(resId) {
    localMethod.resetComplete();
    var text = document.getElementById(resId);
    return text.value;
}

function doGetTextByCN(className) {
    localMethod.resetComplete();
    var text = document.getElementsByClassName(className)[0];
    return text.value;
}

function doGetTextByCNByInner(className) {
    localMethod.resetComplete();
    var text = document.getElementsByClassName(className)[0];
    return text.innerHTML;
}



