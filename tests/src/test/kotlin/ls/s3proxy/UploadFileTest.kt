package ls.s3proxy

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class UploadFileTest : FreeSpec({

    val client = HttpClient {}

    afterSpec {
        client.close()
    }

    "PUT should upload the body" {
        val response: HttpResponse = client.put("http://localhost:8080/key1.txt") {
            setBody("Lorem ipsum dolor sit amed")
        }
        response.status shouldBe HttpStatusCode.OK

        val getResponse: HttpResponse = client.get("http://localhost:8080/key1.txt")
        getResponse.status shouldBe HttpStatusCode.OK
        getResponse.body<String>() shouldBe "Lorem ipsum dolor sit amed"
    }

    "DELETE should delete the resource" {
        val response: HttpResponse = client.put("http://localhost:8080/key2.txt") {
            setBody("Lorem ipsum dolor sit amed")
        }
        response.status shouldBe HttpStatusCode.OK
        client.get("http://localhost:8080/key2.txt").status shouldBe HttpStatusCode.OK

        val deleteResponse: HttpResponse = client.delete("http://localhost:8080/key2.txt")
        deleteResponse.status shouldBe HttpStatusCode.NoContent

        client.get("http://localhost:8080/key2.txt").status shouldBe HttpStatusCode.NotFound
    }
})
