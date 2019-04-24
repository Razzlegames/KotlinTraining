package kotlintraining.lesson6

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AccountEntityDOTest {

    @Test
    fun test() {
        val accountEntityDO = AccountEntityDO(name = "Bob", dob = "10/2/60")
        val accountEntityDTO = AccountEntityDTO(accountEntityDO)
        assertEquals(accountEntityDTO.name(), accountEntityDO.name)
    }

    @Test
    fun testClosing() {
        val accountEntityDTO = AccountEntityDTO(AccountEntityDO(name = "guy", dob ="1/1/2001"))

        assertTrue(accountEntityDTO.open())
        accountEntityDTO.close()
        assertFalse(accountEntityDTO.open())
    }

    /**
     *  someLazyValue is not initialized until requested.
     *
     *  Great use case:
     *   - Where populating this value is an expensive operation
     *   - When this is a computed value that is rarely ever needed
     */
    @Test
    fun lazyInitialization(){
        val accountEntityDTO = AccountEntityDTO(AccountEntityDO(name = "guy", dob ="1/1/2001"))

        assertFalse(accountEntityDTO.expensiveFunctionRan)

        assertEquals(5, accountEntityDTO.someLazyValue)
        assertTrue(accountEntityDTO.expensiveFunctionRan)

    }
}