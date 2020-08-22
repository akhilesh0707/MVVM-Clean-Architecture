package com.aqube.truecaller.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aqube.truecaller.domain.interector.blogs.BlogCharacterAt
import com.aqube.truecaller.domain.interector.blogs.BlogEveryCharacterAt
import com.aqube.truecaller.domain.interector.blogs.BlogWordCounter
import com.aqube.truecaller.presentation.BlogFactory.EMPTY
import com.aqube.truecaller.presentation.BlogFactory.EXCEPTION
import com.aqube.truecaller.presentation.BlogFactory.SUCCESS
import com.aqube.truecaller.presentation.util.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BlogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private val blogCharacterAt = mock<BlogCharacterAt>()
    private val blogEveryCharacterAt = mock<BlogEveryCharacterAt>()
    private val blogWordCounter = mock<BlogWordCounter>()
    private var SUT = BlogViewModel(blogCharacterAt, blogEveryCharacterAt, blogWordCounter)

    @Before
    fun setUp() {
        stubEveryCharacterAt(Observable.just(EMPTY))
        stubWordCount(Observable.just(EMPTY))
        stubCharacterAt(Observable.just(EMPTY))
    }

    @Test
    fun `should not null`() {
        assertNotNull(blogCharacterAt)
        assertNotNull(blogEveryCharacterAt)
        assertNotNull(blogWordCounter)
        assertNotNull(SUT)
    }

    @Test
    fun `fetchBlog call blogWordCounter execute and returned success empty value`() {
        // Act
        SUT.fetchBlog()
        // Assert
        val resultError = SUT.getBlog()
        assertThat(resultError.value).isInstanceOf(BlogUIModel::class.java)
        assertEquals((resultError.value as BlogUIModel.WordCount).wordCount, EMPTY)
    }

    @Test
    fun `fetchBlog call blogWordCounter execute and returned success value`() {
        // Arrange
        stubWordCount(Observable.just(SUCCESS))
        // Act
        SUT.fetchBlog()
        // Assert
        val resultError = SUT.getBlog()
        assertThat(resultError.value).isInstanceOf(BlogUIModel::class.java)
        assertEquals((resultError.value as BlogUIModel.WordCount).wordCount, SUCCESS)
    }

    @Test
    fun `fetchBlog call blogWordCounter execute and returned exception`() {
        // Arrange
        stubWordCount(Observable.error(Throwable(EXCEPTION)))
        // Act
        SUT.fetchBlog()
        // Assert
        val resultError = SUT.getBlog()
        assertThat(resultError.value).isInstanceOf(BlogUIModel::class.java)
        assertEquals((resultError.value as BlogUIModel.Error).error, EXCEPTION)
    }

    // region -------------------------------------------------------------------------------------
    private fun stubCharacterAt(stub: Observable<String>) {
        whenever(blogCharacterAt.execute(any())) doReturn stub
    }

    private fun stubEveryCharacterAt(stub: Observable<String>) {
        whenever(blogEveryCharacterAt.execute(any())) doReturn stub
    }

    private fun stubWordCount(stub: Observable<String>) {
        whenever(blogWordCounter.execute()) doReturn stub
    }
    // endregion ---------------------------------------------------------------------------------

}