package ls.s3proxy

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class CredentialFailureTest : FreeSpec({
    val client = HttpClient {}

    afterSpec {
        client.close()
    }

    "PUT to upload should fail if uploader has wrong credentials" {
        val response: HttpResponse = client.put("http://localhost:8081/key1.txt") {
            setBody("Lorem ipsum dolor sit amed")
        }
        response.status shouldBe HttpStatusCode.Forbidden
    }
})
