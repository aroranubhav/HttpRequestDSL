package com.maxi.httprequestdsl

public enum class HttpMethod {
    GET, POST, PUT, DELETE
}

public data class HttpRequest(
    val method: HttpMethod,
    val url: String,
    val headers: Map<String, String>,
    val body: String?
)