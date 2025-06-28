package com.sparkedember

import com.sparkedember.utils.EventLogger
import com.sparkedember.utils.EventLoggerWithDI
import com.sparkedember.utils.Logger
import com.sparkedember.utils.MockTestObject
import com.sparkedember.utils.MockTestObject.userService
import com.sparkedember.utils.SingletonLogger
import com.sparkedember.utils.User
import com.sparkedember.utils.UserService
import com.sparkedember.utils.UserServiceImpl
import com.sparkedember.utils.UserServiceWithExtensionFunction
import com.sparkedember.utils.getUser
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.Assert
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.infra.Blackhole
import java.util.UUID
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
open class MockkBenchmark {

    @Param("true", "false")
    var isRelaxedParam: String = ""

    private val isRelaxed: Boolean
        get() = isRelaxedParam.toBoolean()

    private val testUserIds = (0 .. 50).map { UUID.randomUUID().toString() }

    @TearDown(Level.Invocation)
    fun tearDown() {
        unmockkAll()
    }

    @Benchmark
    fun setupOnly() {
        MockKAnnotations.init(MockTestObject, relaxed = isRelaxed)
        MockTestObject.initMocks()
    }

    @Benchmark
    fun setupWithStaticInterface(blackhole: Blackhole) {
        val userService = mockk<UserServiceWithExtensionFunction>(relaxed = isRelaxed)
        mockkStatic(UserServiceWithExtensionFunction::class.qualifiedName + "Kt")
        every { userService.getUser(any()) } returns User(email = "test@test.com")
        val user = userService.getUser("test")
        Assert.assertEquals("test@test.com", user.email)
        blackhole.consume(user)
    }

    @Benchmark
    fun setupWithInterface(blackhole: Blackhole) {
        val userService = mockk<UserService>(relaxed = isRelaxed)
        every { userService.getUser(any()) } returns User(email = "test@test.com")
        val user = userService.getUser("test")
        Assert.assertEquals("test@test.com", user.email)
        blackhole.consume(user)
    }

    @Benchmark
    fun setupWithClassUsingInterface(blackhole: Blackhole) {
        val userService: UserServiceImpl = mockk(relaxed = isRelaxed)
        every { userService.getUser(any()) } returns User(email = "test@test.com")
        val user = userService.getUser("test")
        Assert.assertEquals("test@test.com", user.email)
        blackhole.consume(user)
    }

    @Benchmark
    fun setupWithAnswers(blackhole: Blackhole) {
        val userService = mockk<UserService>(relaxed = isRelaxed)
        every { userService.getUser(any()) } answers {
            User(email = "test@test.com")
        }
        val user = userService.getUser("test")
        Assert.assertEquals("test@test.com", user.email)
        blackhole.consume(user)
    }

    @Benchmark
    fun setupWithCalls(blackhole: Blackhole) {
        setupMocks(isRelaxed = isRelaxed)
        MockTestObject.callMocks(blackhole)
    }

    @Benchmark
    fun setupWithCallsAndVerify(blackhole: Blackhole) {
        setupMocks(isRelaxed = isRelaxed)
        MockTestObject.callMocks(blackhole)
        MockTestObject.verifyMocks()
    }

    @Benchmark
    fun setupWithUnStubbedCallsAndVerify(blackhole: Blackhole) {
        MockKAnnotations.init(MockTestObject, relaxed = isRelaxed)
        MockTestObject.callMocks(blackhole)
        MockTestObject.verifyMocks()
    }

    @Benchmark
    fun setupWithStaticAndCallsAndVerify(blackhole: Blackhole) {
        setupMocks(isRelaxed = isRelaxed)
        mockkStatic(UserService::class)
        MockTestObject.callMocks(blackhole)
        MockTestObject.verifyMocks()
    }

    @Benchmark
    fun setupWithFirstArgCall(blackhole: Blackhole) {
        setupMocks(isRelaxed = isRelaxed)
        every { userService.getUser(any()) } answers {
            User(id = UUID.fromString(firstArg()))
        }
        testUserIds.forEach { id ->
            val user: User = userService.getUser(id)
            blackhole.consume(user)
        }
    }

    @Benchmark
    fun setupWithLoopCalls(blackhole: Blackhole) {
        setupMocks(isRelaxed = isRelaxed)
        testUserIds.forEach { id ->
            every { userService.getUser(id) } returns User(id = UUID.fromString(id))
            val user: User = userService.getUser(id)
            blackhole.consume(user)
        }
    }

    @Benchmark
    fun mockObjectAndCall(blackhole: Blackhole) {
        mockkObject(SingletonLogger)
        every { SingletonLogger.log(any()) } returns "Hello, Mockk!"
        val consumer = EventLogger() // Calls SingletonLogger.log internally
        val event = consumer.logEvent("Test Event")
        Assert.assertEquals("Hello, Mockk!", event)
        blackhole.consume(listOf(consumer, event))
    }

    @Benchmark
    fun mockWithDIAndCall(blackhole: Blackhole) {
        val mockLogger = mockk<Logger>(relaxed = isRelaxed) {
            every { log(any()) } returns "Hello, Mockk!"
        }
        val consumer = EventLoggerWithDI(logger = mockLogger)
        val event = consumer.logEvent("Test Event")
        Assert.assertEquals("Hello, Mockk!", event)
        blackhole.consume(listOf(consumer, event))
    }

    private fun setupMocks(isRelaxed: Boolean) {
        MockKAnnotations.init(MockTestObject, relaxed = isRelaxed)
        MockTestObject.initMocks()
    }
}