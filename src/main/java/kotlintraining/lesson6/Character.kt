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

class CharacterImpl : Character {
    override fun getName() = "CharacterImpl"

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

class CharacterDto(character: Character) : Character by character

data class Monster( var tailSize:Int = 0)

class MonsterDto(val monster: Monster)  {
    var tailSize : Int
        get() = monster.tailSize
        set(value: Int) {
            monster.tailSize = value
        }
}