/** ע����JS�ļ����ڴ�ų��ú�����������صĺ�������Java�ļ���ע��*/


/** No.1 ģ�����¼�############################################################################################*/
//ģ�����¼�
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
  localMethod.JI_showToast("length��"+cn.length);
  var btn = document.getElementsByClassName(className)[0];
  if(null!=btn){
    setTimeout(function(){
        btn.click();
    },time*1000);
    }
}

function tabByCN(className,time) {
  var cn = document.getElementsByClassName(className);
  localMethod.JI_showToast("length��"+cn.length);
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
  localMethod.JI_showToast("length��"+cn.length);
      localMethod.getBooleanByLength(cn.length);
}


function getDataByHotSells() {
    localMethod.resetComplete();
  var tab = document.getElementsByTagName("li");
    localMethod.JI_showToast("length��"+tab[1].innerHTML);

    tab[1].click();
}

function getHotSellsListData() {
    localMethod.resetComplete();
  var title = document.getElementsByClassName("d-title");
  var price = document.getElementsByClassName("d-price");
  var num = document.getElementsByClassName("d-num");
//    localMethod.JI_LOG("length��"+tab.length);


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
//    localMethod.JI_LOG("length��"+tab.length);


    for (var i = 0; i <title.length ; i++) {
        localMethod.JI_LOG(title[i].innerHTML);
        localMethod.JI_LOG(price[i].innerHTML);
        localMethod.JI_LOG(num[i].innerHTML);
    }

}

//ģ�ⴥ���¼�
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

//���ݸ��ؼ������ӿؼ��ٴ���
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


/** No.2 �����ı���Ϣ���������############################################################################################*/
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


/** No.3 ��ȡ�ؼ����ı���Ϣ###########################################################################################*/
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



