package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.interector.blogs.BlogFactory.CHAR_COUNT
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.EXCEPTION
import com.aqube.truecaller.domain.interector.blogs.BlogFactory.SUCCESS
import com.aqube.truecaller.domain.interector.util.RxImmediateSchedulerRule
import com.aqube.truecaller.domain.repository.BlogRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.Assert
import org.junit.ClassRule
import org.junit.Test

class BlogWordCounterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private val repository = mock<BlogRepository>()
    private val SUT = BlogWordCounter(repository)

    @Test
    fun `should not null`() {
        Assert.assertNotNull(repository)
        Assert.assertNotNull(SUT)
    }

    @Test
    fun `call buildUseCaseObservable with char index and returned value`() {
        // Arrange
        stubBlog(Observable.just(SUCCESS))
        // Act
        val testObserver = SUT.buildUseCaseObservable().test()
        // Assert
        Assert.assertEquals(testObserver.completions(), 1)
        Assert.assertEquals(testObserver.values()[0], CHAR_COUNT)
        verify(repository).getBlog()
    }

    @Test
    fun `call buildUseCaseObservable with char index and returned empty value`() {
        // Arrange
        stubBlog(Observable.just(BlogFactory.EMPTY))
        // Act
        val testObserver = SUT.buildUseCaseObservable().test()
        // Assert
        Assert.assertEquals(testObserver.completions(), 1)
        verify(repository).getBlog()
    }

    @Test
    fun `call buildUseCaseObservable with char index and returned exception`() {
        // Arrange
        stubBlog(Observable.error(Throwable(EXCEPTION)))
        // Act
        val testObserver = SUT.buildUseCaseObservable().test()
        // Assert
        testObserver.assertErrorMessage(EXCEPTION)
        verify(repository).getBlog()
    }



    // region -------------------------------------------------------------------------------------
    private fun stubBlog(observable: Observable<String>) {
        whenever(repository.getBlog()) doReturn observable
    }
    // endregion ----------------------------------------------------------------------------------

}