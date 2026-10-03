package com.maxi.httprequestdsl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HttpRequestDslTest {

    @Test
    fun `builds a request with DSL`() {
        val request = request {
            method = HttpMethod.POST
            url = "https://randomapi.com"
            header("Accept", "application/json")
            body = """{"name": "Maxi"}"""
        }

        assertEquals(HttpMethod.POST, request.method)
        assertEquals("application/json", request.headers["Accept"])
    }

    @Test
    fun `conditional logic is just kotlin`() {
        val token: String? = "abc098"

        val request = request {
            url = "https://randomapi.com"
            if (token != null) {
                header("Authorization", "Bearer $token")
            }
        }

        assertEquals("Bearer abc098", request.headers["Authorization"])
    }

    @Test
    fun `validation still runs`() {
        assertFailsWith<IllegalArgumentException> {
            request {
                method = HttpMethod.POST
            }
        }
    }

    @Test
    fun `blocks are values and can be reused`() {
        val jsonDefaults: HttpRequestBuilder.() -> Unit = {
            header("Accept", "application/json")
            header("Content-Type", "application/json")
        }

        val request = request {
            jsonDefaults()
            url = "https://randomapi.com"
        }

        assertEquals("application/json", request.headers["Content-Type"])
        assertEquals("https://randomapi.com", request.url)
    }
}