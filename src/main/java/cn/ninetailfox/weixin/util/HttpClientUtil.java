package cn.ninetailfox.weixin.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public final class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;

    private static final int CONNECTION_TIMEOUT = 5000;

    private static final int SOCKET_TIMEOUT = 5000;

    private HttpClientUtil() { };

    /**
     * 发起get请求
     * @param url url
     * @param paramsMap params
     * @return result
     */
    public static Map<String, Object> get(String url, Map<String, Object> paramsMap) {
        Map<String, Object> resultMap = new HashMap<>();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            URI uri = new URI(url);
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            uri = new URIBuilder()
                    .setScheme(uri.getScheme())
                    .setHost(uri.getHost())
                    .setPort(uri.getPort())
                    .setPath(uri.getPath())
                    .setParameters(params).build();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(CONNECTION_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .setRedirectsEnabled(true).build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(requestConfig);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity responseEntity = response.getEntity();
                resultMap.put("status", response.getStatusLine());
                resultMap.put("contentLength", responseEntity.getContentLength());
                resultMap.put("entity", EntityUtils.toString(responseEntity, "UTF-8"));
            } catch (ConnectTimeoutException e) {
                LOGGER.warn("CONNECT TIMEOUT EXCEPTION: " + url);
            } catch (SocketTimeoutException e) {
                LOGGER.warn("SOCKET TIMEOUT EXCEPTION: " + url);
            } catch (HttpHostConnectException e) {
                LOGGER.warn("HTTP_HOST_CONNECT_EXCEPTION: " + url);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 发起post请求
     * @param url url
     * @param paramsMap parmas
     * @return result
     */
    public static Map<String, Object> post(String url, Map<String, Object> paramsMap) {
        Map<String, Object> resultMap = new HashMap<>();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), (String) entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setConnectTimeout(CONNECTION_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT).build();
            httpPost.setConfig(requestConfig);
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                resultMap.put("status", response.getStatusLine());
                resultMap.put("contentLength", responseEntity.getContentLength());
                resultMap.put("entity", EntityUtils.toString(responseEntity, "UTF-8"));
            } catch (ConnectTimeoutException e) {
                LOGGER.warn("CONNECT TIMEOUT EXCEPTION: " + url);
            } catch (SocketTimeoutException e) {
                LOGGER.warn("SOCKET TIMEOUT EXCEPTION: " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
