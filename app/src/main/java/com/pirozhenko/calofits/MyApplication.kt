package com.pirozhenko.calofits

import android.app.Application


class MyApplication : Application() {
    //     var trainBaseList: ArrayList<String> = ArrayList<String>()
//    var trainBaseList: ArrayList<String> = arrayListOf("Отжимания", "Приседания", "Бег")
//    var trainCreateList: ArrayList<String> = ArrayList<String>()


    //     var train2: ExerciseTrain = ExerciseTrain("Отжимания", "Description")
    var trainBaseListNew: ArrayList<ExerciseTrainClass> = ArrayList()
//     var trainBaseListNew: ArrayList<ExerciseTrainClass> = arrayListOf(ExerciseTrainClass("Отжимания", "Description"),
//     ExerciseTrainClass("Приседания", "Description"),
//     ExerciseTrainClass("Бег", "Description"))

//    var trainCreateListNew: ArrayList<ExerciseTrainClass> = ArrayList()
    var trainExercisesListNew: ArrayList<ExerciseTrainClass> = ArrayList()
    var trainClassListNew: ArrayList<TrainClass> = ArrayList()

    var foodBaseList: ArrayList<String> = arrayListOf("Банан", "Яица", "Молоко")
    var foodCreateList: ArrayList<String> = ArrayList<String>()

    //     var food2: ExerciseTrain = ExerciseTrain("Отжимания", "Description")
    var foodBaseListNew: ArrayList<FoodClass> = ArrayList()
//    var foodBaseListNew: ArrayList<FoodClass> = arrayListOf(
//        FoodClass("Банан", "Description"),
//        FoodClass("Яица", "Description"),
//        FoodClass("Молоко", "Description")
//    )

    var foodCreateListNew: ArrayList<FoodClass> = ArrayList()


}