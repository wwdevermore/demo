package Util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author juncheng
 */
public class HttpUtils {
	
	private static Logger logger = Logger.getLogger(HttpUtils.class);

    private static final int GET = 1;
    private static final int DELETE = 2;
    private static final int PUT = 3;
    private static final int POST = 4;

    /**
     * @param url 请求的 url
     * @param params 请求参数
     * @param headers 请求头参数
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        return requestWithNoBody(HttpUtils.GET, url, params, headers);
    }

    /**
     * @param url 请求的 url
     * @param params 请求参数
     * @param headers 请求头参数
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    public static String delete(String url, Map<String, String> params, Map<String, String> headers) {
        return requestWithNoBody(HttpUtils.DELETE, url, params, headers);
    }

    /**
     * @param url 请求的 url，若需要添加 url 参数，则直接拼接在 url 后面
     * @param headers 请求头参数
     * @param body Body 参数，目前仅支持字符串类型
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    public static String put(String url, Map<String, String> headers, String body) {
        return requestWithBody(HttpUtils.PUT, url, headers, body);
    }

    /**
     * @param url 请求的 url，若需要添加 url 参数，则直接拼接在 url 后面
     * @param headers 请求头参数
     * @param body Body 参数，目前仅支持字符串类型
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    public static String post(String url, Map<String, String> headers, String body) {
        return requestWithBody(HttpUtils.POST, url, headers, body);
    }

    /**
     * @param method 请求方法，目前支持 GET(1) 或 DELETE(2)
     * @param requestUrl 请求的 url
     * @param params 请求参数
     * @param headers 请求头参数
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    private static String requestWithNoBody(
            int method, String requestUrl, Map<String, String> params, Map<String, String> headers) {
        List<String> paramList = null;
        String url = requestUrl;
        if (params != null) {
            if (url.contains("?")) {
                url += "&";
            } else {
                url += "?";
            }
            paramList = new ArrayList<>();
            for (Entry<String, String> entry : params.entrySet()) {
                paramList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        if (paramList != null) {
            url += String.join("&", paramList);
        }
        if (method == HttpUtils.GET) {
            return request(new HttpGet(), url, headers, null);
        } else if (method == HttpUtils.DELETE) {
            return request(new HttpDelete(), url, headers, null);
        } else {
            return null;
        }
    }

    /**
     * @param method 请求方法，目前支持 PUT(3) 或 POST(4)
     * @param url 请求的 url，若需要添加 url 参数，则直接拼接在 url 后面
     * @param headers 请求头参数
     * @param body Body 参数，目前仅支持字符串类型
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    private static String requestWithBody(int method, String url, Map<String, String> headers, String body) {
        if (method == HttpUtils.PUT) {
            return request(new HttpPut(), url, headers, body);
        } else if (method == HttpUtils.POST) {
            return request(new HttpPost(), url, headers, body);
        } else {
            return null;
        }
    }

    /**
     * @param requestBase 请求的方法，应该为 HttpRequestBase 的子类，目前只能是 HttpGet,HttpPost,HttpPut,HttpDelete 中的一种
     * @param url 请求的 url，如果为 GET 或者 DELETE 请求且有参数需要先拼接在 url 中
     * @param body PUT 或者 POST 请求的 Body 参数，目前仅支持字符串类型
     * @param headers 请求头参数
     * @return 请求返回的字符串，若请求异常则返回值为 null
     * */
    private static String request(HttpRequestBase requestBase, String url,
                                  Map<String, String> headers, String body) {
        CloseableHttpClient httpClient = HttpClients.createMinimal();
        HttpRequestBase method = requestBase;
        logger.debug("url:" + url);
        try {
            method.setURI(new URI(url));
        } catch (URISyntaxException e) {
        	logger.error(e);
            return null;
        }
        // 设置连接超时时间和请求超时时间均为30秒，忽略 Cookies
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30 * 1000)
                .setConnectTimeout(30 * 1000)
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build();
        method.setConfig(requestConfig);
        boolean useGBK = false;
        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                if (HTTP.CONTENT_TYPE.equalsIgnoreCase(entry.getKey())) {
                    String contentType = entry.getValue();
                    if (contentType != null && contentType.toLowerCase().contains("gb")) {
                        useGBK = true;
                    }
                }
                method.addHeader(entry.getKey(), entry.getValue());
            }
        } else {
            // 默认接收 UTF-8 编码的 JSON 数据
            method.addHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8");
            useGBK = false;
        }
        // 如果请求方式为 PUT 或者 POST，则添加 body
        if (method instanceof HttpPut || method instanceof HttpPost) {
            HttpEntityEnclosingRequestBase entityMethod = (HttpEntityEnclosingRequestBase) method;
            if (useGBK) {
                entityMethod.setEntity(new StringEntity(body, "GBK"));
            } else {
                entityMethod.setEntity(new StringEntity(body, "UTF-8"));
            }
            method = entityMethod;
        }
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(method);
            if (httpResponse != null) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity.getContentLength() > 0) {
                    String response = EntityUtils.toString(entity, "UTF-8");
                    logger.debug("Response: " + response);
                    return response;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (IOException e) {
        	logger.error("URL: " + url + "\t Body:" + body, e);
            return null;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
            	logger.error(e);
            }
        }
    }

}
