package com.maxi.httprequestdsl

import kotlin.test.Test
import kotlin.test.assertEquals


class HttpRequestTest {

    @Test
    fun `builds a request with the constructor`() {
        val request = HttpRequest(
            method = HttpMethod.POST,
            url = "https://randomaoi.com",
            headers = mapOf("Accept" to "application/json"),
            body = """{"name": "Maxi"}"""
        )

        assertEquals(HttpMethod.POST, request.method)
        assertEquals("application/json", request.headers["Accept"])
    }

    @Test
    fun `invalid request object gets created`() {
        val request = HttpRequest(
            method = HttpMethod.GET,
            url = "",
            headers = emptyMap(),
            body = null
        )

        // This compiles and passes.
        assertEquals("", request.url)
    }
}