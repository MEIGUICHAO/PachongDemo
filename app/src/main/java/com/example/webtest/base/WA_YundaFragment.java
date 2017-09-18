package com.example.webtest.base;

import java.io.IOException;
import java.math.BigDecimal;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.webtest.Utils.CacheUtils;
import com.example.webtest.Utils.UIUtils;
import com.example.webtest.io.WA_Parameters;

import static android.content.ContentValues.TAG;

/**
 * @author z.h
 * @desc 存放基本业务逻辑&Js调用本地方法的接口函数
 */
public class WA_YundaFragment extends WA_BaseFragment {

    protected WebView listWeb;
    protected WebView detailWeb;

    protected enum SearchType {
        All, Shop, Mall
    }

    private Handler handler = new Handler();

    /**
     * Function：选择商品所在的商铺类型(天猫或淘宝)
     */
    protected String selectSearchType(boolean isTMall) {
        String str = "var sortType= doGetTextByCN(\"s-input-tab-txt\");if(sortType!=\"店铺\"){doClickByCN(\"s-input-tab-txt\",1);doClickByCN(\"shop\",2);doClickByCN(\"s-input-tab-txt\",2);}";

//		String str = "var sortType= doGetTextByCN(\"s-input-tab-txt\");" + "if(!" + isTMall + "){" + "if(sortType!=\"天猫\"){" + "doClickByCN(\"s-input-tab-txt\",1);" + "doClickByCN(\"shop\",2);" + "doClickByCN(\"s-input-tab-txt\",2);" + "}}else{" + "if(sortType!=\"宝贝\"){" + "doClickByCN(\"s-input-tab-txt\",1);" + "doClickByCN(\"shop\",2);" + "doClickByCN(\"s-input-tab-txt\",2);" + "}}";
        return str;
    }

    /**
     * Function：点击进入搜索(BelongTo Step1)
     */
    protected void doEnterSearchPage() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                enterSearchPage(listWeb);
            }
        });
    }

    /**
     * Function：选择商铺类型(BelongTo Step2)
     */
    protected void doSelectStoreType(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                selectStoreType(listWeb, parameter.getIsTMall());
            }
        });
    }

    /**
     * Function：进行商品搜索(BelongTo Step2)
     */
    protected void doSearch(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                searchFor(listWeb, parameter.getKeywordStr());
            }
        });
    }

    /**
     * Function：点击进入商铺(BelongTo Step2)
     */
    protected void doClick(final WA_Parameters parameter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                shopCLick(listWeb, parameter.getKeywordStr());
            }
        });
    }

    /**
     * Function：首次进行商品浏览(BelongTo Step3)
     */
    protected void doScan(final WA_Parameters parameter) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 4000);

    }

    /**
     * Function：根据销量排序(BelongTo Step4)
     */
    protected void doOrderBySellAmount() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                orderBySellAmount(listWeb);
            }
        });
    }

    protected void selectAllShop() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                selectAllShop(listWeb);
            }
        });
    }

    protected void getHotSellsList() {

        handler.post(new Runnable() {
            @Override
            public void run() {
//				getHotSellsList(listWeb);
            }
        });
    }


    /**
     * Function：若当前页中不存在该商铺则翻页，同时另一个页面进行随机商品浏览，浏览时长随机(BelongTo Step5)
     */
    protected void doFlipAndScan(final WA_Parameters parameter, final int randomTime) {
        // 跳转到下一页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNextPage(listWeb);
            }
        }, 2000);

        // 在当前页查找，若没查到则翻到下一页递归查找
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 5000 + randomTime * 1000);
    }

    /**
     * Function：不翻页，在当前页进行随机商品浏览，浏览时长随机(BelongTo Step5)
     */
    protected void doScanForLongTime(final WA_Parameters parameter, final int randomTime) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanGoods(listWeb, parameter.getTitleStr());
            }
        }, 5000 + randomTime * 1000);

    }

    /**
     * Function：关闭提示框(BelongTo Step5)
     */
    protected void doAlertHide() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alertHide(detailWeb);
            }
        }, 2000);
    }

    /**
     * Function：根据给定的URL进入执行商铺(BelongTo Step5)
     */
    protected void doEnterShop(final String url) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                enterShop(detailWeb, url);
            }
        });

    }

    /**
     * Function：选择商品SKU
     */
    protected void doSelectSku() {

    }

    /**
     * 点击进入搜索页面(主页面)
     */
    private void enterSearchPage(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByRI(\"search-placeholder\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 选择店铺类型
     */
    private void selectStoreType(WebView webView, boolean isTMall) {
        // 拼接业务逻辑
        String logicStr = selectSearchType(isTMall);
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 输入搜索内容，然后查找
     */
    private void searchFor(WebView webView, String keywordStr) {
        // 拼接业务逻辑
        String logicStr = "doInputByCN(\"txt\",\"" + keywordStr + "\",2);"
                + "doClickByCN(\"searchbtn\",4);";
//				+ "doClickByCN(\"list-item\",4);";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 输入搜索内容，然后查找
     */
    private void shopCLick(WebView webView, String keywordStr) {
        // 拼接业务逻辑
        String logicStr = "doClickByCN(\"list-item\",2);";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 点击筛选按钮
     */
    private void filterGoods(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByRI(\"J_Sift\");";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 确定筛选条件
     */
    private void confirmFilter(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByRI(\"J_SiftCommit\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 点击分类菜单
     */
    private void orderBySellAmount(final WebView webView) {

        getHotSellsListData(webView,true);

        // 拼接业务逻辑
//        String logicStr0 = "getLengthByCn(\"item J_tabItem\");";
//        String completeJs0 = doAutoTest(logicStr0);
//        loadUrl(webView, completeJs0);
//
//        doSleep(2);
//        UIUtils.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (mLocalMethod.isBooleanIndex()) {
//                    String logicStr = "doClickByCNandPosition(\"item J_tabItem\",1);";
//                    String completeJs = doAutoTest(logicStr);
//                    loadUrl(webView, completeJs);
//                    UIUtils.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
////							getHotSellsList(webView,true);
//
//                            String url = webView.getUrl();
//                            Log.e(TAG, "url: " + url);
//                            String replace = url.replace("sort=default", "sort=d");
//                            webView.loadUrl(replace);
//                            doSleep(3);
//                            getHotSellsListData(webView,true);
//                        }
//                    }, 6000);
//
//                } else {
//                    String logicStr = "doClickByCN(\"js-nav-csch fun csch\",1);";
//                    String completeJs = doAutoTest(logicStr);
//                    loadUrl(webView, completeJs);
//                    doSleep(6);
//                    selectAllShop(webView);
//                    doSleep(6);
//                    getHotSellsList(webView, false);
//                    doSleep(6);
//                    getHotSellsListData(webView, false);
//                }
//            }
//        }, 2000);


    }

    private void selectAllShop(WebView webView) {

        // 拼接业务逻辑

        String logicStr = "doClickByCN(\"js-sc-m-title big-title omit\",2);";
//		String logicStr = "getLengthByCn(\"item J_tabItem\");";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    public void getHotSellsList(WebView webView, boolean isTmall) {
        if (isTmall) {
            String logicStr = "doTapByCN(\"o_item J_itemSort J_sortType_d\",0);";
            String completeJs = doAutoTest(logicStr);
            loadUrl(webView, completeJs);

        } else {

            String logicStr = "getDataByHotSells();";

            String completeJs = doAutoTest(logicStr);
            loadUrl(webView, completeJs);
        }

    }

    public void getHotSellsListData(WebView webView, boolean isTmall) {
        String logicStr;
        if (isTmall) {
            logicStr = "getTmallHotSellsListData();";
        } else {
            logicStr = "getHotSellsListData();";
        }

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }


    public void initAccountInfo(WebView webView) {
//        jq_name  idNumber jq_mobile

        String logicStr = "doInputByRI(\"jq_name\",\"" + "陈飞" + "\",2);"
                +"doInputByRI(\"idNumber\",\"" + "430481199003264556" + "\",2);"
                +"doInputByRI(\"jq_mobile\",\"" + "13148439851" + "\",2);";

//        String logicStr = "doClickByCN(\"jdriveInfo.idNumber\",2);";
        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);

    }

    /**
     * 浏览商铺
     */
    private void scanGoods(WebView webView, String titleStr) {
        // 拼接业务逻辑
        String logicStr = "var currentPage=doGetTextByCNByInner(\"currentPage\");" + "var totalSize=getSize(\"list-item\");"
                // +"localMethod.JI_showToast(totalSize);"
                // + "localMethod.JI_showToast(currentPage);"
                + "doTapForScanGoodsByTitle(\"list-item\",\"d-title\",\"" + titleStr + "\",currentPage,totalSize);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 关闭提示框
     */
    private void alertHide(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doClickByCN(\"btn-hide\",2);";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 进入目标商铺
     */
    private void enterShop(WebView webView, String url) {
        webView.loadUrl("https:" + url);
    }

    private void skuSelect(WebView webView) {
        // 拼接业务逻辑
        String logicStr = "doTapByCN02(); ";

        String completeJs = doAutoTest(logicStr);
        loadUrl(webView, completeJs);
    }

    /**
     * 翻页
     */
    private void getNextPage(WebView mWebView) {
        String logicStr = "doTapByCN(\"J_PageNext\"); ";

        String completeJs = doAutoTest(logicStr);
        loadUrl(mWebView, completeJs);
    }

    /* 暴露给JavaScript脚本调用的方法* */
    public class LocalMethod {
        Context mContext;
        private WA_Parameters parameter;
        private WebView webView;
        private boolean isComplete = false;

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete(boolean complete) {
            isComplete = complete;
        }

        public boolean isBooleanIndex() {
            return booleanIndex;
        }

        private boolean booleanIndex;

        public LocalMethod(Context c, WA_Parameters parameter, WebView webView) {

            this.mContext = c;
            this.parameter = parameter;
            this.webView = webView;

        }

        public WA_Parameters getParameter() {
            return parameter;
        }

        public void setParameter(WA_Parameters parameter) {
            this.parameter = parameter;
        }

        @JavascriptInterface
        public void JI_showToast(String content) {
            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void JI_scrollView() {
            listWeb.scrollBy(0, 1800);
        }

        @JavascriptInterface
        public void JI_doGetNextPage(int randomTime) {
            doFlipAndScan(parameter, randomTime);
        }

        @JavascriptInterface
        public void JI_doScanCurrentPage(int randomTime) {

            doScanForLongTime(parameter, randomTime);
        }

        @JavascriptInterface
        public void JI_doCloseAlert() {
            doAlertHide();
        }

        @JavascriptInterface
        public void JI_doEnterShop(String url) {
            doEnterShop(url);
        }

        @JavascriptInterface
        public void JI_createLog(String infoStr) throws IOException {
            createLog(infoStr);
        }


        @JavascriptInterface
        public void JI_LOG(String content) {
            Log.e(TAG, "JI_LOG: " + content);
            setComplete(true);
        }

        @JavascriptInterface
        public void resetComplete() {
            setComplete(false);
        }

        @JavascriptInterface
        public void getBooleanByLength(String content) {
            if (Integer.parseInt(content) > 0) {
                booleanIndex = true;
            } else {
                booleanIndex = false;
            }
        }

    }

}
