package com.aqube.truecaller.data.source

import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class BlogDataSourceFactoryTest {

    private val remoteDataSource = mock<BlogRemoteDataSource>()
    private val SUT = BlogDataSourceFactory(remoteDataSource)

    @Test
    fun `should not null`() {
        assertNotNull(remoteDataSource)
        assertNotNull(SUT)
    }

    @Test
    fun `call getDataSource and return BlogDataSource`() {
        // Act
        val dataSource = SUT.getDataSource()
        // Assert
        assertNotNull(dataSource)
        assertThat(dataSource).isInstanceOf(BlogRemoteDataSource::class.java)
    }

}