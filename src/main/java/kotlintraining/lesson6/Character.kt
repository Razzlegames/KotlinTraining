package kotlintraining.lesson6

/**
 *  Examples of delegation from an interface
 */
interface Character {
    fun jump()
    fun moveLeft()
    fun moveRight()
    fun getName(): String
}

class CharacterBehavior : Character {

    override fun getName() = "CharacterBehavior"

    override fun jump() {
        print("Character Jump")
    }

    override fun moveLeft() {
        print("Character Move Left")
    }

    override fun moveRight() {
        print("Character Move Right")
    }
}

interface Flying {
    fun fly()
}

class FlyingBehavior : Flying {

    override fun fly() {
        print("Flying")
    }
}

interface Swimming {
    fun swim()
}

class SwimmingBehavior : Swimming {
    override fun swim() {
        print("Swim")
    }
}

// TODO Add a DigBehavior 

// Any implementation of Flying, Swimming etc will be delegated to the passed
//      Behavior implementation!
data class Monster( var tailSize:Int = 0, val flying: Flying, val character: Character,
                    val swimming: Swimming) :
    Flying by flying, Character by character,
    Swimming by swimming


