package com.wp.common.constants;

import java.io.File;

import com.wp.util.ToolUtil;

/**
 * @Author：ld	
 * @CreatDate:2016年10月25日 下午4:03:06
 * @Description：系统全局常量类
 */
public class SysConstants {
	
	public final static String SESSION_USER = "SESSION_USER";
	public final static String SESSION_BEAN = "SESSION_BEAN";
	public final static String SESSION_ID = "sid";
	public final static String USER_NUM = "userNumber";
	public final static String SSO_SID = "ssoSid";
    public final static String Cookie_SSO_Sid = "Os_SSo_Sid";
    public final static String Cookie_autoSecretKey = "ak";
    public final static String Cookie_RMKEY = "RMKEY";
    public final static String Cookie_userAttrType = "userAttrType";
    public final static String Cookie_UIN = "uin";
    public final static String Cookie_USER_DATA = "UserData";
    public final static String Cookie_m = "m";
    public final static String REQUEST_TIME = "request_time";
    public final static String REQUEST_BUSINESS_RESULT = "request_business_result";
    
    public final static String OPERATE_SUCCESS_CODE = "0";
	public final static String OPERATE_SUCCESS_DESC = "操作成功";
	public final static String OPERATE_FAIL_DESC = "操作失败";
	public final static String OPERATE_FAIL_CODE = "999";
	
	public final static String JSON_TYPE = "JSON";
	public final static String XML_TYPE = "XML";
	public final static String SCRIPT_TYPE = "SCRIPT";
	
	public final static String GET_MPOSTUSER_FAIL_DESC = "获取云邮局用户失败";
	public final static String GET_MPOSTUSER_FAIL_CODE = "9999";
	
	/**反垃圾接口返回码**/
	public final static String FILTERAPI_PASSED_CODE = "000";
	public final static String COMMENTISFILTERED_FLAG = "011";
	public final static String COMMENT_IPBLACKLIST_FLAG = "018";
	public final static String COMMENT_FREQUENCYLIMIT_FLAG = "019";
	public final static String FILTERAPI_NORESPONSE_FLAG = "020";
	public final static String FILTERAPI_NORESPONSE_CODE = "E020";
	public final static String FILTERAPI_NORESPONSE_DESC = "垃圾过滤接口调用失败";
	
	public final static String NULL_COLUMNID_CODE = "E004";
	public final static String NULL_COLUMNID_DESC = "栏目id为空或非法";
	
    /**
     * 接口地址
     */
	public static String CONFIG_HTTP_XML_URL = "cloud_http_xml_url";
    public static String CONFIG_HTTP_JSON_URL = "cloud_http_json_url";
    
    /**
     * inner栏目订阅接口
     */
    public static String INNER_SUBSCRIBE = "subscribe";
    /**
     * inner小说订阅接口
     */
    public static String INNER_SUBSCRIBE_NOVEL = "subscribeNovel";
    
    /**
     * inner期刊订阅接口
     */
    public static String INNER_SUBSCRIBE_JOURANL = "subscribeJournal";
    /**
     * inner栏目退订接口
     */
    public static String INNER_UNSUBSCRIBE = "unSubscribe";
	/**
	 * 输出日志开关，1：打开，打印日志，0：关闭，不打印日志
	 */
	public final static int LOG_CLOSE = 0;
	public final static int LOG_OPEN = 1;
    
	/**
	 * 云邮局WEB客户端ID
	 */
	public static final String INNER_CLIENTID = "2003";
	/**
     * MEMC:默认缓存时间长度，下面这个转为十进制就是-1
     */
    public final static int SHORT_STORED = 0xffffffff;
   
	/**
	/**
	 * 用户根路径
	 */
	public static final String ROOT = System.getProperty("user.dir");
	/**
	 * 全局配置路径
	 */
	public static final String GLOBAL_SERVER_CFG = File.separator + ToolUtil.getAppRoot() + "/config/global.properties";
	
	
	
    
    /**
     * 智能推荐接口serviceId，web端传参,查询3个
     */
    public static Integer RECOMMEND_SERVICEID_WEB = 10;

    public static Integer INDEX_PAGE_COUNT = 21;

    public static Integer INDEX_TAG_COUNT = 7;
    /**
     * bizId 常量
     */
    
    public final static int bizId_web = 2000;
    public final static int bizId_wap = 2001;
    public final static int bizId_soft =2006;
    
    /**
     * pageId常量
     */
    public final static Integer pageId_web = 27;
    public final static Integer pageId_wap = 26;
    public final static Integer pageId_soft = 28;
    /**
     * 日志上报站点常量
     */
    public final static String portalId = "13";
    /**
     * 调用inner接口
     */
    //调用inner查询订购信息接口
    public final static String GET_WABP_RECORD = "getWabpRecord";
    
}
