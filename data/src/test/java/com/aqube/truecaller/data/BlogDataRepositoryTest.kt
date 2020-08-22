package com.aqube.truecaller.data

import com.aqube.truecaller.data.repository.BlogDataSource
import com.aqube.truecaller.data.source.BlogDataSourceFactory
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
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
    private val store = mock<BlogDataSource>()
    private val SUT = BlogDataRepository(factory)
    
    //region --------------------------------------------------------------------------------------
    private val SUCCESS = "<html><body>Blog return</body></html>"
    //endregion -----------------------------------------------------------------------------------
    
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
    }

    @Test
    fun `call getBlog and get observable with return value zero`(){
        // Arrange
        stubGetBlog(Observable.empty())
        // Act
        val testObserver = SUT.getBlog().test()
        // Assert
        testObserver.assertValueCount(0)
    }

    // region
    private fun stubFactoryGetDataSource() {
        whenever(factory.getDataSource()) doReturn store
    }

    private fun stubGetBlog(stub: Observable<String>) {
        whenever(store.getBlog()) doReturn stub
    }
    // end region
}