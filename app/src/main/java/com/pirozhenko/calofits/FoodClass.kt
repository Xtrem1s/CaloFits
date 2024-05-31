package com.pirozhenko.calofits

data class FoodClass(var name: String, private var description: String) {
    private val idFood: Int = nextNewId
    private var picture: UiImageFood? = null
    var proteins: Float? = null
    var fats: Float? = null
    var carbohydrates: Float? = null
    var calories: Float? = null
//    printTest()

//    private fun updateId(): Int {
//        nextNewId += 1
//        return  nextNewId
//    }

    override fun toString(): String {
//        if (checkToString) {
////            Log.v("toString", "name")
//
        var result = name + " ("
        if (calories != null)
            result = result + "ккал: " + calories.toString() + ", "
        else
            result += "ккал: 0, "

        if (proteins != null)
            result = result + "белки: " + proteins.toString() + "г, "
        else
            result += "белки: 0г, "
        if (fats != null)
            result = result + "жиры: " + fats.toString() + "г, "
        else
            result += "жиры: 0г, "
        if (carbohydrates != null)
            result = result + "углеводы: " + carbohydrates.toString() + "г, "
        else
            result += "углеводы: 0г"
        result += ")"
        return result
    }

    //        else{
////            Log.v("toString", "id")
//
////            val result = idExercise.toString()
//            return idExercise.toString()
//        }
//    }
//    fun printTest(){
//        Log.v("Exercise test", "id: $idExercise")
//    }
    override fun equals(other: Any?): Boolean {
//        if (checkEquals)
//        {
//
//        }
        if (other == null || !(other is FoodClass))
            return false;
        else
            return this.idFood == (other as FoodClass).idFood;
//        return super.equals(other)
    }

    override fun hashCode(): Int {
        return idFood
    }

    init {
        nextNewId++
//        printTest()
    }

    companion object {
        var nextNewId: Int = 0
//        var checkToString: Boolean = true
    }
}

sealed class UiImageFood {
    data class LocalImage(var imageRes: Int) : UiImageFood()
    data class RemoteImage(var imageUrl: String) : UiImageFood()
}
