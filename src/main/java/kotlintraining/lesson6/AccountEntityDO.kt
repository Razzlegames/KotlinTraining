package kotlintraining.lesson6

interface AccountInterface {

    fun name() : String
    fun name(name: String)
    fun dob() : String
    fun open() : Boolean
    fun close()
}

/**
 *  TODO Add a observer Delegate to all name changes (See PropertyDelegates for hints)
 *
 *  - Just print on observe of change:
 *     - print("Name changed to $name") .
 *   - Still keep the same AccountInterface functionality (override fun name(String) etc)
 *      We still want to be able to set and get name from AccountEntityDTo
 */
class AccountEntityDO(var name: String, val dob: String,
                      var open: Boolean = true) : AccountInterface {

    override fun name(name: String) {
        this.name = name
    }

    override fun dob() = dob
    override fun open() = open
    override fun name() = name

    override fun close() {
        open = false
        print("closed AccountEntityDO")
    }
}

class AccountEntityDTO(accountEntityDO: AccountEntityDO) :
    AccountInterface by accountEntityDO {

    var expensiveFunctionRan = false

    // This will not execute expensiveFunction() until someLazyValue
    //   is accessed!
    val someLazyValue by lazy {
        expensiveFunction()
    }

    fun expensiveFunction() : Int {

        expensiveFunctionRan = true
        // Some expensive calculation
        return 5
    }

}