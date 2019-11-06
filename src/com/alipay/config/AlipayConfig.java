package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


	//沙箱APPID
	public static final  String app_id = "2016101200670147";
	//沙箱私钥
	public static final  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCprsN50KR9IDWNa7nescRZ62Key5B7TM1KInI5DzOR+8rJxtCHgecxWc7oKUxawD4ce88XIaO6IfaULZ9/bSy9V2yKNeqBlY40pmEPGcZ6Si/X/klCyKrfdYuddB6GvluN9fm77OXmCBlhf4yQp4ms6L5QryCFzeToyExJ2VMr5G3PT9iEVI3hpfQ6e27H9aY6fEf37DBi15o8CQUEbldvaenURRERBSdoDsZWs7Vn4lC4mUo925FIAY1dTx2WaO3JWSDEiMcEdfiGthSh0KTYTeFxe7MHzAuqSJvOx+IkzXIIyytz9B/G28PZAD8N5Jg9433cvxfM4Gw03pU1OF9dAgMBAAECggEAaotpBJFE56iU18L/x9x5fTi3CRyrETDtapJshHWOHSkscTxcbencgHly2QYtgoJZvcrkAxNFWje/O9XV485qnE1LwxENueZtj5O1OCUoIlt45TgvSjb0TDHjw69JB7R5io8cC6s5xBHr1fOgEKiSYzcVYVZFpp7dEGnMtUY32kOj+P5dydY/bkOTANKvuTLQYtsLOa5DJ+/HiibWdRa1EoucfX0cyVktpIN3Q54u9C05IbFjQfvjnYsxphiUHPDTjmpVTFe+6fdQ/NVRXI8Yy3Q0ow0QbEwqgafwDdD4G/PNPMXvDXveGJ8C9GgyoUkkUn3H9EEYoLSiBqahSl+ZZQKBgQDVxmP0bpXWCxJnTfpFufnKaikIVIvALxUUWzufdYi9vx/z4X46/4ET58oe02tOr1T7uJqpG4GF0RRxK9zjiJAHXou3tbW4ZCpmSbYj43GBRwBmLSHO04/Fqr6sMP/9Sd7ZThiy8XMMmz+twgJ7IxSwcJilJ4fkoGYRTezMTMCv1wKBgQDLMtSFIfgRWU3i269V2+IBvS426wbs61xAzPlDavg5mP5Go+QKw3lLlc+QceLaOuMPxGArLPhVjDJsV7QxX2FEbvvLvlZEyButFk88+F+Q5FDIx2keEcephDk7nqU5j4pWgm1eeoK+piAPn6P/BRW1GmaiBm/DCqcd5bcAxcYT6wKBgQDDZBKmLpF7UyFeFLLFGmgKr5+Tgps9bItK0NydMfNq4w8UfS1ARZq4E+r04+63u5/kd+W83Ww0YFbb3eiGe6KRacQwhMB0qfDrD0KQaOms7LWbrnKxNUO6uwaOIjdITkWGv0/p9rBefGPmsGZOIFFft3p9sbUOreC8vknKrfiMfwKBgCzD5/nkf+giinwd9cSuil3jtZi3snDjcRF9lrR5jnHx+KLLXGPaqJA0UuP5kwaMzpUcqCsa3yLJnhosQDBYWO9gm+xI5GyGwUIilhZSiYs8IWf6Nng/ZoM78/fkfXXu34UrerjDuKYTpyCKBPEbx6p8RHwRevG3GRYHE4gCCh89AoGAehr5AI2+9ttH/pCs+Hm5VmJ8Qv35FHVUNnCGsHnd5QfXPthCjrzQbOgkm4wZx098CV3+VqyApxHgLkUkK6BGIPQmkkuP+oBcqf6klKNAJNKD2N1hOqAGgH0qp1oMqVZXhvBqhVAu0VtzVxCf7Dlhw0iXiRg+vmrRYtQckxLEIq0=";
	//支付宝公钥
	public static final  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqa7DedCkfSA1jWu53rHEWetinsuQe0zNSiJyOQ8zkfvKycbQh4HnMVnO6ClMWsA+HHvPFyGjuiH2lC2ff20svVdsijXqgZWONKZhDxnGekov1/5JQsiq33WLnXQehr5bjfX5u+zl5ggZYX+MkKeJrOi+UK8ghc3k6MhMSdlTK+Rtz0/YhFSN4aX0Ontux/WmOnxH9+wwYteaPAkFBG5Xb2np1EUREQUnaA7GVrO1Z+JQuJlKPduRSAGNXU8dlmjtyVkgxIjHBHX4hrYUodCk2E3hcXuzB8wLqkibzsfiJM1yCMsrc/QfxtvD2QA/DeSYPeN93L8XzOBsNN6VNThfXQIDAQAB";
	//沙箱网关地址
	public static final  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

//	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/xiaomi/pay/notify_url.jsp";

//	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/xiaomi/pay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";

	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

