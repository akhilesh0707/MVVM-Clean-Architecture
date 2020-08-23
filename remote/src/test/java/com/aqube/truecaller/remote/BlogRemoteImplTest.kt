package com.aqube.truecaller.remote

import com.aqube.truecaller.remote.BlogFactory.EMPTY
import com.aqube.truecaller.remote.BlogFactory.EXCEPTION
import com.aqube.truecaller.remote.BlogFactory.SUCCESS
import com.aqube.truecaller.remote.service.BlogService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BlogRemoteImplTest {

    private val service = mock<BlogService>()
    private val SUT = BlogRemoteImpl(service)

    @Test
    fun `should not be null`() {
        assertNotNull(SUT)
        assertNotNull(service)
    }

    @Test
    fun `getBlog called getBlogResponse and returned success response`() {
        // Arrange
        stubBlogService(Observable.just(SUCCESS))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertComplete()
        assertEquals(testObserver.values()[0], SUCCESS)
        verify(service).getBlogResponse()
    }

    @Test
    fun `getBlog called getBlogResponse and returned success empty`() {
        // Arrange
        stubBlogService(Observable.just(EMPTY))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertComplete()
        assertEquals(testObserver.values()[0], EMPTY)
        verify(service).getBlogResponse()
    }

    @Test
    fun `getBlog called getBlogResponse and returned exception`() {
        // Arrange
        stubBlogService(Observable.error(Throwable(EXCEPTION)))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertErrorMessage(EXCEPTION)
        verify(service).getBlogResponse()
    }

    // region -------------------------------------------------------------------------------------
    private fun stubBlogService(stub: Observable<String>) {
        whenever(service.getBlogResponse()) doReturn stub
    }
    // endregion ----------------------------------------------------------------------------------

}