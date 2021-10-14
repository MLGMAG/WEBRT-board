package com.webmuffins.rtsx.board.constants;

public class HTTPConstants {

    private HTTPConstants() {
    }

    public static final String CORS_ORIGIN_HEADER_PATTERN = "Access-Control-Allow-Origin";
    public static final String CORS_METHODS_HEADER_PATTERN = "Access-Control-Allow-Methods";
    public static final String CORS_HEADERS_HEADER_PATTERN = "Access-Control-Allow-Headers";
    public static final String CORS_REQUEST_MAX_AGE_PATTERN = "Access-Control-Max-Age";

    public static final String ALLOWED_HTTP_METHODS = "POST, PUT, GET, PATCH, OPTIONS, DELETE";
    public static final String ALLOWED_HEADERS = "Authorization, Content-Type, Authorization-Access";
    public static final String ALLOWED_MAX_AGE = "3600";
}
