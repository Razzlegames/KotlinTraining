package kotlintraining.lesson6

interface AccountInterface {

    fun name() : String
    fun dob() : String
    fun open() : Boolean
    fun close()
}

class AccountEntityDO(var name: String, var dob: String,
                      var open: Boolean = true) : AccountInterface {

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