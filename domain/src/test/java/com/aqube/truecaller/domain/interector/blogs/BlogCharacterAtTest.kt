package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.interector.blogs.BlogFactory.CHAR
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.CHAR_INDEX
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.EMPTY
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.ERROR
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.EXCEPTION
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.SUCCESS
import com.aqube.truecaller.domain.interector.util.RxImmediateSchedulerRule
import com.aqube.truecaller.domain.repository.BlogRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.ClassRule
import org.junit.Test

class BlogCharacterAtTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private val repository = mock<BlogRepository>()
    private val SUT = BlogCharacterAt(repository)

    @Test
    fun `should not null`() {
        assertNotNull(repository)
        assertNotNull(SUT)
    }

    @Test
    fun `call buildUseCaseObservable with char index and returned value`() {
        // Arrange
        stubBlog(Observable.just(SUCCESS))
        // Act
        val testObserver = SUT.buildUseCaseObservable(
            BlogCharacterAt.Params.characterAt(CHAR_INDEX)
        ).test()
        // Assert
        assertEquals(testObserver.completions(), 1)
        assertEquals(testObserver.values()[0], CHAR)
        verify(repository).getBlog()
    }

    @Test
    fun `call buildUseCaseObservable with char index and returned empty value`() {
        // Arrange
        stubBlog(Observable.just(EMPTY))
        // Act
        val testObserver = SUT.buildUseCaseObservable(
            BlogCharacterAt.Params.characterAt(CHAR_INDEX)
        ).test()
        // Assert
        assertEquals(testObserver.completions(), 1)
        assertEquals(testObserver.values()[0], EMPTY)
        verify(repository).getBlog()
    }

    @Test
    fun `call buildUseCaseObservable with char index and returned exception`() {
        // Arrange
        stubBlog(Observable.error(Throwable(EXCEPTION)))
        // Act
        val testObserver = SUT.buildUseCaseObservable(
            BlogCharacterAt.Params.characterAt(CHAR_INDEX)
        ).test()
        // Assert
        testObserver.assertErrorMessage(EXCEPTION)
        verify(repository).getBlog()
    }

    @Test
    fun `call buildUseCaseObservable without char index and returned error`() {
        // Arrange
        stubBlog(Observable.just(SUCCESS))
        // Act
        val testObserver = SUT.buildUseCaseObservable().test()
        // Assert
        testObserver.assertError(IllegalArgumentException::class.java)
        assertEquals(testObserver.errors()[0].localizedMessage, ERROR)
        verify(repository, never()).getBlog()
    }

    // region -------------------------------------------------------------------------------------
    private fun stubBlog(observable: Observable<String>) {
        whenever(repository.getBlog()) doReturn observable
    }
    // endregion ----------------------------------------------------------------------------------
}