package com.pirozhenko.calofits

data class ExerciseTrainClass(var name: String, private var description: String) {
    val idExercise: Int = nextNewId
    var repetitions: Int? = null
    var approaches: Int? = null
    var distance: Int? = null
    var time: Int? = null
    var weight: Int? = null
//    var propertyTrainList: ArrayList<PropertyTrain> = ArrayList()

    private var picture: UiImageTrain? = null
//    printTest()

//    private fun updateId(): Int {
//        nextNewId += 1
//        return  nextNewId
//    }

//    fun addPropertyTrainList(dateTrainTmp: String, repetTmp: Int, approachTmp: Int, distanceTmp: Int, timeTmp: Int){
//        val newPropertyTrain = PropertyTrain(dateTrainTmp)

//        if (repetitions == 0)
//            newPropertyTrain.repetitionsDate = repetTmp
//        if (approaches == 0)
//            newPropertyTrain.approachesDate = approachTmp
//        if (distance== 0)
//            newPropertyTrain.distanceDate = distanceTmp
//        if (time == 0)
//            newPropertyTrain.timeDate = timeTmp

//        propertyTrainList.add(newPropertyTrain)

//        newPropertyTrain = 1

//    }

    override fun toString(): String {
//        if (checkToString) {
////            Log.v("toString", "name")
//
            return name
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
        if (other == null || !(other is ExerciseTrainClass))
            return false;
        else
            return this.idExercise == (other as ExerciseTrainClass).idExercise;
//        return super.equals(other)
    }

    override fun hashCode(): Int {
        return idExercise
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

sealed class UiImageTrain {
    data class LocalImage(var imageRes: Int) : UiImageTrain()
    data class RemoteImage(var imageUrl: String) : UiImageTrain()
}

//class PropertyTrain(){
//    var dateExercise: String? = null
//    val idExercise: Int = ExerciseTrainClass.nextNewId
//    var repetitionsDate: Int? = null
//    var approachesDate: Int? = null
//    var distanceDate: Int? = null
//    var timeDate: Int? = null
//}
//class MyClass {
//    var staticVariable = 10
//}