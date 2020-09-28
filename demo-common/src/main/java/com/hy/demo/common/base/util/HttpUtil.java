package com.hy.demo.common.base.util;

import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * https://www.jb51.net/article/148199.htm
 */
@Slf4j
@Builder
public class HttpUtil {

    public static final Charset UTF8 = StandardCharsets.UTF_8;

    public static final String multipartFormDataBoundary = "Java11HttpClientFormBoundary";

    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String CONTENT_TYPE_VALUE_JSON = "application/json";
    public static final String CONTENT_TYPE_VALUE_FROM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_VALUE_UPLOAD = "multipart/form-data; boundary=" + multipartFormDataBoundary;

    public static final HttpClient.Version DEFAULT_VERSION = HttpClient.Version.HTTP_2;
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofMillis(3000);
    public static final Duration DEFAULT_TIMEOUT = Duration.ofMillis(3000);

    private final HttpClient httpClient;
    private final Duration timeout;

    private void setHeader(HttpRequest.Builder httpRequestBuilder, Map<String, String> header) {
        if (MapUtils.isNotEmpty(header)) {
            header.forEach(httpRequestBuilder::setHeader);
        }
    }
    private String getBody(Map<String, String> params, boolean encode) {
        if (MapUtils.isNotEmpty(params)) {
            return params.entrySet().stream()
                    .filter(e -> StringUtils.isNotBlank(e.getKey()))
                    .map(e -> {
                        var key = e.getKey();
                        var value = e.getValue();
                        if (encode && StringUtils.isNotBlank(value)) {
                            value = URLEncoder.encode(value, UTF8);
                        }
                        return key + "=" + value;
                    })
                    .collect(Collectors.joining("&"));
        }
        return "";
    }

    /**
     * post from
     * @param url URL
     * @param header header
     * @param params 参数
     * @return HttpResponse
     */
    @SneakyThrows
    public HttpResponse postThrow(String url, Map<String, String> header, Map<String, String> params) {
        var httpRequestBuilder = HttpRequest.newBuilder();
        httpRequestBuilder.setHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE_FROM);
        setHeader(httpRequestBuilder, header);
        if (MapUtils.isNotEmpty(params)) {
            httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(getBody(params, false)));
        }
        httpRequestBuilder.uri(URI.create(url));
        return httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse postThrow(String url, Map<String, String> params) {
        return postThrow(url, null, params);
    }

    public HttpResponse post(String url, Map<String, String> header, Map<String, String> params) {
        try {
            return postThrow(url, header, params);
        } catch (Exception e) {
            log.error(" error post ", e);
        }
        return null;
    }

    public HttpResponse post(String url, Map<String, String> params) {
        return post(url, null, params);
    }

    public HttpResponse post(String url) {
        return post(url, null, null);
    }

    /**
     * post body
     * @param url URL
     * @param header header
     * @param body json
     * @return HttpResponse
     */
    @SneakyThrows
    public HttpResponse postBodyThrow(String url, Map<String, String> header, String body) {
        var httpRequestBuilder = HttpRequest.newBuilder();
        httpRequestBuilder.setHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE_JSON);
        setHeader(httpRequestBuilder, header);
        if (StringUtils.isNotBlank(body)) {
            httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
        }
        httpRequestBuilder.uri(URI.create(url));
        return httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse postBodyThrow(String url, String body) {
        return postBodyThrow(url, null, body);
    }

    public HttpResponse postBody(String url, Map<String, String> header, String body) {
        try {
            return postBodyThrow(url, header, body);
        } catch (Exception e) {
            log.error(" error postBody ", e);
        }
        return null;
    }

    public HttpResponse postBody(String url, String body) {
        return postBody(url, null, body);
    }

    public HttpResponse postBody(String url) {
        return postBody(url, null, null);
    }

    /**
     * get
     * @param url URL
     * @param header header
     * @param params 参数
     * @return HttpResponse
     */
    @SneakyThrows
    public HttpResponse getThrow(String url, Map<String, String> header, Map<String, String> params) {
        var httpRequestBuilder = HttpRequest.newBuilder();
        setHeader(httpRequestBuilder, header);
        var paramSB = new StringBuilder();
        if (MapUtils.isNotEmpty(params)) {
            if (url.indexOf("?") <= 0) {
                paramSB.append("?");
            }
            paramSB.append(getBody(params, true    ));
        }
        httpRequestBuilder.uri(URI.create(url + paramSB.toString()));
        return httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse getThrow(String url, Map<String, String> params) {
        return getThrow(url, null, params);
    }

    public HttpResponse getThrow(String url) {
        return getThrow(url, null, null);
    }

    public HttpResponse get(String url, Map<String, String> header, Map<String, String> params) {
        try {
            return getThrow(url, header, params);
        } catch (Exception e) {
            log.error(" error get ", e);
        }
        return null;
    }

    public HttpResponse get(String url, Map<String, String> params) {
        return get(url, null, params);
    }

    public HttpResponse get(String url) {
        return get(url, null, null);
    }

    /**
     * 上传
     * @param url url
     * @param header header
     * @param path 上传文件 /tmp/body.txt
     */
    @SneakyThrows
    public HttpResponse uploadThrow(String url, Map<String, String> header, String path) {
        var httpRequestBuilder = HttpRequest.newBuilder();
        setHeader(httpRequestBuilder, header);
        httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofFile(Path.of(URI.create(path))));
        httpRequestBuilder.uri(URI.create(url));
        return httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse uploadThrow(String url, String path) {
        return uploadThrow(url, null, path);
    }

    public HttpResponse upload(String url, Map<String, String> header, String path) {
        try {
            return uploadThrow(url, header, path);
        } catch (Exception e) {
            log.error(" error downloadNotThrow ", e);
        }
        return null;
    }

    public HttpResponse upload(String url, String path) {
        return upload(url, null, path);
    }

    /**
     * 下载
     * @param url url
     * @param header header
     * @param path 保存路径 /tmp/body.txt
     */
    @SneakyThrows
    public HttpResponse downloadThrow(String url, Map<String, String> header, String path) {
        var httpRequestBuilder = HttpRequest.newBuilder();
        setHeader(httpRequestBuilder, header);
        httpRequestBuilder.uri(URI.create(url));
        return httpClient.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofFile(Paths.get(path)));
    }

    public HttpResponse downloadThrow(String url, String path) {
        return downloadThrow(url, null, path);
    }

    public HttpResponse download(String url, Map<String, String> header, String path) {
        try {
            return downloadThrow(url, header, path);
        } catch (Exception e) {
            log.error(" error downloadNotThrow ", e);
        }
        return null;
    }

    public HttpResponse download(String url, String path) {
        return download(url, null, path);
    }


    /*
     * ================================================== 异步 ==================================================
     */

    /**
     * 下载 异步
     * @param url url
     * @param header header
     * @param path 保存路径 /tmp/body.txt
     */
    @SneakyThrows
    public CompletableFuture<Path> asyncDownload(String url, Map<String, String> header, String path) {
        var httpRequestBuilder = HttpRequest.newBuilder();
        setHeader(httpRequestBuilder, header);
        httpRequestBuilder.uri(URI.create(url));
        return httpClient.sendAsync(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofFile(Paths.get(path)))
                .thenApply(HttpResponse::body);
        //System.out.println(result.get());
    }

    @SneakyThrows
    public CompletableFuture<Path> asyncDownload(String url, String path) {
        return asyncDownload(url, null, path);
    }



    public static ClientBuilder clientBuilder() {
        return new ClientBuilder();
    }

    public static class ClientBuilder {

        private HttpClient.Builder httpClientBuilder;

        private Duration timeout;

        public ClientBuilder httpClientBuilder(HttpClient.Builder builder) {
            this.httpClientBuilder = builder;
            return this;
        }

        public ClientBuilder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpUtil build() {
            if (httpClientBuilder == null) {
                throw new IllegalArgumentException( "No httpClientBuilder supplied." );
            }
            if (timeout == null) {
                throw new IllegalArgumentException( "No timeout supplied." );
            }
            return HttpUtil.builder().httpClient(httpClientBuilder.build()).timeout(timeout).build();
        }

        public HttpUtil buildSimple() {
            if (httpClientBuilder == null) {
                httpClientBuilder = HttpClient.newBuilder()
                        .version(DEFAULT_VERSION)
                        .connectTimeout(DEFAULT_CONNECT_TIMEOUT);
                timeout = DEFAULT_TIMEOUT;
            }
            return build();
        }
    }

    public static void main(String[] args) {

        var param = "{\"appid\":\"5992\",\n" +
                "\"advplaceid\":\"8633\",\n" +
                "\"muidtype\":\"1\",\n" +
                "\"mode\":\"3\",\n" +
                "\"mac\":\"5C:C3:07:80:F2:53\",\n" +
                "\"os\":\"android\",\n" +
                "\"dpi\":\"1080*2160\",\n" +
                "\"devicetype\":\"phone\",\n" +
                "\"c_ori\":0,\n" +
                "\"osversion\":\"9\",\n" +
                "\"imeiidfa\":\"868014037053131\",\n" +
                "\"model\":\"BND-AL10\",\n" +
                "\"c_device\":\"HUAWEI\",\n" +
                "\"network\":1,\n" +
                "\"carrieroperator\":0,\n" +
                "\"sdkversion\":\"3.2.0\",\n" +
                "\"c_w\":1080,\n" +
                "\"c_h\":2160,\n" +
                "\"c_pkgname\":\"com.yx.ssp\",\n" +
                "\"sizeid\":101,\n" +
                "\"ua\":\"Mozilla\\/5.0 (Linux; Android 9; BND-AL10 Build\\/HONORBND-AL10; wv) AppleWebKit\\/537.36 (KHTML, like Gecko) Version\\/4.0 Chrome\\/79.0.3945.116 Mobile Safari\\/537.36\",\n" +
                "\"density\":3,\n" +
                "\"appversion\":\"1.0\",\n" +
                "\"anid\":\"253521a88a56eade\",\n" +
                "\"vapi\":\"2.7.5\",\n" +
                "\"sdkType\":0,\n" +
                "\"isInit\":1,\n" +
                "\"c_adtype\":3,\n" +
                "\"oaid\":\"3ff2f3df-b1ff-3712-5dad-df7ffbff7760\",\n" +
                "\"pr_id_visited\":\"\",\n" +
                "\"timestamp\":1593669630111}";

        var httpUtil = HttpUtil.clientBuilder().buildSimple();

        var result = httpUtil.postBody("http://testapi.youxiaoad.com/Requestad", null, param);

        System.out.println(result.body());
    }
}