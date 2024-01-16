package org.example

import io.reactivex.rxjava3.core.Observable
import kotlin.collections.ArrayList

//ConcatMap vs FlatMap vs SwitchMap
//Handling new data (eg. list of posts in the feed).
//Lets assume somewhere in the application there is an Observable that periodically emits list of objects. It can be list of the posts in the timeline that is refreshed each time user makes interaction with it. In this case the best operator to use would be switchMap because we don’t care about the previous result if we have a new set of items. It is safe to unsubscribe from it and focus on the newest data. This can save us some time if processing of previous (old) response is skipped.
//Getting specific data for each item from a list (eg. get avatar for each user in contacts).
//In this case I would recommend using concatMap. When using flatMap here, there is a possibility of messing up the order of the avatars, and wrong avatar can be displayed. It depends of your implementation though, it may be still ok to use flatMap if you haver other mechanism to preserve the order. SwitchMap is not a good idea here, because we will not get all avatars as described above.
//Doing something for each item in sorted list:
//FlatMap or switchMap should not be used in this case. Only concatMapwill make sure that our list stays the same. Because of synchronous calls in the concatMap, increase of the processing time must be taken into account.
//Sending some information for each item in list (eg. sending ‘like’ message for each post in the list).
//To make sure that every request will be called, we definitely should not use switchMap here. Both flatMap and concatMap will do the job, but flatMap would be better as we don’t care about the order and we can call all requests altogether and receive the results faster.
//Searching through items by query.
//Lest assume that user inputs letters: ‘x’ then ‘y’. The whole query is now ‘xy’ so there is no need to be subscribed for results with the letter ‘x’. In this case we can safely use switchMap.

fun main() {
//    println("Hello World!")

    val greetings = arrayListOf("a","b","c")
    val greetings2 = arrayListOf("abc","def","hij")

    val myObservable: Observable<ArrayList<String>> = Observable.fromArray(greetings,greetings2)

//    map(myObservable)
//    flatmap(myObservable)
//    concatMap(myObservable)
     switchMap(myObservable)
//    buffer(Observable.range(0,20))
//      distinct(Observable.fromArray(1,2,3,4,1,5,2,3,4,6))
//    distinctUntilChanged(Observable.fromArray(1, 2, 3, 1, 1, 5, 2, 4, 4, 6))
}

fun map(myObservable: Observable<ArrayList<String>>) {
    myObservable.map {
        //only for transforming
    }.subscribe({ item ->
        println(item)
    }, {
    })
}

//speed is important
fun flatmap(myObservable: Observable<ArrayList<String>>) {
    //flatmap operator
    myObservable.flatMap {
        //can transform and flatten
        println("Inside flatmap $it")
        Observable.fromIterable(it)
    }.subscribe({ item ->
        println(item)
    }, {})
}

//order is important
fun concatMap(myObservable: Observable<ArrayList<String>>) {
    //flatmap operator
    myObservable.concatMap {
        //can transform and flatten
        println("Inside flatmap $it")
        Observable.fromIterable(it)
    }.subscribe({ item ->
        println(item)
    }, {})
}

fun switchMap(myObservable: Observable<ArrayList<String>>) {
    //flatmap operator
    myObservable.switchMap {
        //can transform and flatten
        println("Inside flatmap $it")
        Observable.fromIterable(it)
    }.subscribe({ item ->
        println(item)
    }, {})
}


fun buffer(myObservable: Observable<Int>){
    myObservable.buffer(4).subscribe {
        println("Inside buffer ${it.toList()}")
    }
}

//for unique emissions
fun distinct(myObservable: Observable<Int>){
    myObservable.distinct().subscribe {
        println("Inside buffer ${it}")
    }
}

//for emissions that are not same as last predecessor emission
fun distinctUntilChanged(myObservable: Observable<Int>){
    myObservable.distinctUntilChanged().subscribe {
        println("Inside buffer ${it}")
    }
}