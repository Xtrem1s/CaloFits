package com.pirozhenko.calofits

import java.io.Serializable

data class FoodClass(var name: String, private var description: String) : Serializable {
    private val idFood: Int = nextNewId
    private var picture: UiImageFood? = null
    var proteins: Float? = null
    var fats: Float? = null
    var carbohydrates: Float? = null
    var calories: Float? = null

    override fun toString(): String {
        var result = name + " ("
        if (calories != null)
            result += "ккал: " + calories.toString() + ", "
        else
            result += "ккал: 0, "

        if (proteins != null)
            result += "белки: " + proteins.toString() + "г, "
        else
            result += "белки: 0г, "
        if (fats != null)
            result += "жиры: " + fats.toString() + "г, "
        else
            result += "жиры: 0г, "
        if (carbohydrates != null)
            result += "углеводы: " + carbohydrates.toString() + "г, "
        else
            result += "углеводы: 0г"
        result += ")"
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is FoodClass) return false
        return this.idFood == (other as FoodClass).idFood
    }

    override fun hashCode(): Int {
        return idFood
    }

    init {
        nextNewId++
    }

    companion object {
        var nextNewId: Int = 0
    }
}

sealed class UiImageFood {
    data class LocalImage(var imageRes: Int) : UiImageFood()
    data class RemoteImage(var imageUrl: String) : UiImageFood()
}
