package com.maxi.httprequestdsl

/**
public fun request(block: HttpRequestBuilder.() -> Unit): HttpRequest {
    val builder = HttpRequestBuilder() // 1. create the request-builder
    builder.block()                    // 2. run caller's code as with builder as `this`
    return builder.build()             // 3. call build
}
    apply takes a T.() -> Unit, runs it on the object, and returns the object
 */
public fun request(block: HttpRequestBuilder.() -> Unit): HttpRequest =
    HttpRequestBuilder()
        .apply(block)
        .build()



