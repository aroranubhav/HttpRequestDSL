package com.maxi.httprequestdsl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

class HttpRequestBuilderTest {

    @Test
    fun `simple GET only needs a url`() {
        val builder = HttpRequestBuilder()
        builder.url = "https://randomapi.com"

        val request = builder.build()

        assertEquals(HttpMethod.GET, request.method)
        assertTrue(request.headers.isEmpty())
        assertNull(request.body)
    }

    @Test
    fun `headers can be added conditionally`() {
        val token: String? = "abc098"

        val builder = HttpRequestBuilder()
        builder.url = "https://randomapi.com"
        token?.let {
            builder.header("Authorization","Bearer $token")
        }

        val request = builder.build()
        assertEquals("Bearer abc098", request.headers["Authorization"])
    }

    @Test
    fun `blank url is rejected`() {
        val builder = HttpRequestBuilder()

        assertFailsWith<IllegalArgumentException> {
            builder.build()
        }
    }
}