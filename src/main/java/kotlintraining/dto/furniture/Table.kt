package kotlintraining.dto.furniture

/**
 *  Example of default parameters we don't need to specify in construction
 *
 *   Note, since "val" the object is still immutable so:
 *     Table(..) Will have color == White and cannot be changed
 *     Table(color = "RED") Will have color == RED and cannot be changed
 */
data class Table(val color: String = "White", val length: Float, val width: Float,
    val height: Float)
