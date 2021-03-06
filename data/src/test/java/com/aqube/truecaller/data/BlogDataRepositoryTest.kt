package com.aqube.truecaller.data

import com.aqube.truecaller.data.BlogFactory.EXCEPTION
import com.aqube.truecaller.data.BlogFactory.SUCCESS
import com.aqube.truecaller.data.repository.BlogDataSource
import com.aqube.truecaller.data.source.BlogDataSourceFactory
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class BlogDataRepositoryTest {

    private val factory = mock<BlogDataSourceFactory>()
    private val dataSource = mock<BlogDataSource>()
    private val SUT = BlogDataRepository(factory)

    @Before
    fun setUp() {
        stubFactoryGetDataSource()
    }

    @Test
    fun `should not be null`() {
        assertNotNull(factory)
        assertNotNull(SUT)
    }

    @Test
    fun `call getBlog and get observable with return complete`() {
        // Arrange
        stubGetBlog(Observable.just(SUCCESS))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertComplete()
        verify(factory).getDataSource()
        verify(factory.getDataSource()).getBlog()
    }

    @Test
    fun `call getBlog and get observable with return value zero`() {
        // Arrange
        stubGetBlog(Observable.empty())
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertValueCount(0)
        verify(factory).getDataSource()
        verify(factory.getDataSource()).getBlog()
    }

    @Test
    fun `call getBlog and get observable with return error`() {
        // Arrange
        stubGetBlog(Observable.error(Throwable(EXCEPTION)))
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertErrorMessage(EXCEPTION)
        verify(factory).getDataSource()
        verify(factory.getDataSource()).getBlog()
    }

    // region -------------------------------------------------------------------------------------
    private fun stubFactoryGetDataSource() {
        whenever(factory.getDataSource()) doReturn dataSource
    }

    private fun stubGetBlog(stub: Observable<String>) {
        whenever(dataSource.getBlog()) doReturn stub
    }
    // endregion ----------------------------------------------------------------------------------
}