package com.pirozhenko.calofits

class TrainClass (var dateTrain: String){
    var exercisesList2: ArrayList<ExerciseTrainClass> = ArrayList()
    var exercisesList: HashMap<ExerciseTrainClass,PropertyTrain> = HashMap()

}

class PropertyTrain(){
//    var dateExercise: String? = null
    //    val idExercise: Int = ExerciseTrainClass.nextNewId
    var repetitionsDate: Int? = null
    var approachesDate: Int? = null
    var distanceDate: Int? = null
    var timeDate: Int? = null
    var weightDate: Int? = null
}