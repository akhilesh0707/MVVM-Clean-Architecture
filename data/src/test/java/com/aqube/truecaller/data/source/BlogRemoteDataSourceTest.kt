package com.aqube.truecaller.data.source

import com.aqube.truecaller.data.BlogFactory.EXCEPTION
import com.aqube.truecaller.data.BlogFactory.SUCCESS
import com.aqube.truecaller.data.repository.BlogRemote
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class BlogRemoteDataSourceTest {

    private val remoteSource = mock<BlogRemote>()
    private val SUT = BlogRemoteDataSource(remoteSource)

    @Test
    fun `should not be null`() {
        assertNotNull(remoteSource)
        assertNotNull(SUT)
    }

    @Test
    fun `call getBlog and get observable with return success complete`() {
        // Arrange
        stubGetBlog(Observable.just(SUCCESS))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertComplete()
        verify(remoteSource).getBlog()
    }

    @Test
    fun `call getBlog and get observable with return zero emits`() {
        // Arrange
        stubGetBlog(Observable.empty())
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertValueCount(0)
        verify(remoteSource).getBlog()
    }

    @Test
    fun `call getBlog and get observable with return error`() {
        // Arrange
        stubGetBlog(Observable.error(Throwable(EXCEPTION)))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertErrorMessage(EXCEPTION)
        verify(remoteSource).getBlog()
    }

    // region -------------------------------------------------------------------------------------
    private fun stubGetBlog(stub: Observable<String>) {
        whenever(remoteSource.getBlog()) doReturn stub
    }
    // endregion ----------------------------------------------------------------------------------
}