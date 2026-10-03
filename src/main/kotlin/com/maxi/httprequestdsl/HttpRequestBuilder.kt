package com.maxi.httprequestdsl

public class HttpRequestBuilder {

    public var method: HttpMethod = HttpMethod.GET
    public var url: String = ""
    public var body: String? = null

    private val headers = mutableMapOf<String, String>()

    public fun header(name: String, value: String) {
        headers[name] = value
    }

    public fun build(): HttpRequest {
        require(url.isNotBlank()) { // throws IllegalArgumentException if the condition is false
            "url must not be blank"
        }

        return HttpRequest(
            method = method,
            url = url,
            headers = headers.toMap(),
            body = body
        )
    }
}