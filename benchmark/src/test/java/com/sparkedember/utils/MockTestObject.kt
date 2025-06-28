package com.sparkedember.utils

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Job
import org.openjdk.jmh.infra.Blackhole
import java.io.File
import java.io.InputStream
import java.io.ObjectInputValidation
import java.sql.Connection
import java.sql.ResultSet
import java.util.UUID
import java.util.concurrent.Callable
import javax.naming.Context

object MockTestObject {
    @MockK
    lateinit var callable: Callable<String>
    @MockK
    lateinit var closable: AutoCloseable
    @MockK
    lateinit var runnable: Runnable
    @MockK
    lateinit var inputStream: InputStream
    @MockK
    lateinit var inputValidation: ObjectInputValidation
    @MockK
    lateinit var connection: Connection
    @MockK
    lateinit var resultSet: ResultSet
    @MockK
    lateinit var context: Context
    @MockK
    lateinit var job: Job
    @MockK
    lateinit var userService: UserService

    fun initMocks() {
        every { callable.call() } returns "Mocked Result"
        every { closable.close() } returns Unit
        every { runnable.run() } returns Unit
        every { inputStream.read() } returns 1
        every { inputValidation.validateObject() } returns Unit
        every { connection.autoCommit } returns false
        every { resultSet.next() } returns true
        every { context.bind(any<String>(), any<String>()) } returns Unit
        every { job.isActive } returns true
    }

    fun callMocks(blackhole: Blackhole) {
        val calledString = callable.call()
        closable.close()
        runnable.run()
        val byte = inputStream.read()
        inputValidation.validateObject()
        val isAutoCommitEnabled = connection.autoCommit
        val next = resultSet.next()
        context.bind("Hello", "World")
        val isJobActive = job.isActive

        val results = listOf(
            calledString,
            byte,
            next,
            isJobActive,
            isAutoCommitEnabled
        )
        blackhole.consume(results)
    }

    fun verifyMocks() {
        verify {
            callable.call()
            closable.close()
            runnable.run()
            inputStream.read()
            inputValidation.validateObject()
            resultSet.next()
            job.isActive
            context.bind("Hello", "World")
            connection.autoCommit
        }
    }
}