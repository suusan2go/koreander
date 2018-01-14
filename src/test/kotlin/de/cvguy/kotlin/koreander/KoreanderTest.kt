package de.cvguy.kotlin.koreander

import de.cvguy.kotlin.koreander.exception.InvalidTypeException
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test
import java.io.InputStream

class KoreanderTest {
    val koreander = Koreander()
    val unit = Koreander.typeOf(Unit)

    @Test
    fun canCompileString() {
        val result = koreander.compile("", unit)
        assertThat(result, instanceOf(CompiledTemplate::class.java))
    }

    @Test
    fun canCompileURL() {
        val url = javaClass.getResource("/empty.kor")
        val result = koreander.compile(url, unit)
        assertThat(result, instanceOf(CompiledTemplate::class.java))
    }

    @Test
    fun conCompileInputStream() {
        val inputStream: InputStream = "".byteInputStream()
        val result = koreander.compile(inputStream, unit)
        assertThat(result, instanceOf(CompiledTemplate::class.java))
    }

    @Test
    fun canRenderCompiledTemplate() {
        val compiled = koreander.compile("", unit)
        val result = koreander.render(compiled, Unit)
        assertThat(result, instanceOf(String::class.java))
    }

    @Test
    fun failsIfWrongTypeIsGiven() {
        val compiled = koreander.compile("", unit)
        try {
            koreander.render(compiled, "This is a String, not Unit")
            Assert.fail()
        }
        catch(e: InvalidTypeException) {
        }
    }

    @Test
    fun canRenderCompiledTemplateUnsafe() {
        val compiled = koreander.compile("", unit)
        val result = koreander.render(compiled, Unit)
        assertThat(result, instanceOf(String::class.java))
    }

    @Test
    fun canRenderString() {
        val result = koreander.render("", unit)
        assertThat(result, instanceOf(String::class.java))
    }

    @Test
    fun canRenderURL() {
        val url = javaClass.getResource("/empty.kor")
        val result = koreander.render(url, unit)
        assertThat(result, instanceOf(String::class.java))
    }

    @Test
    fun conRenderInputStream() {
        val inputStream: InputStream = "".byteInputStream()
        val result = koreander.render(inputStream, unit)
        assertThat(result, instanceOf(String::class.java))
    }

    @Test
    fun fail() {
        Assert.fail("Travis fail test")
    }
}