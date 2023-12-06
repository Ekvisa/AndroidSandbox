
package com.example.androidsandbox

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlin.random.Random

//Задание 2: кастомный экстеншн для imageView, загружающий картинку по ссылке.
//При добавлении питомца (при выполнении onAddPetClick) в нижнем imageView появится мемчик (строки 71 и 75)

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null
    private var editTextPetType: EditText? = null
    private var editTextPetName: EditText? = null
    private var editTextPetAge: EditText? = null
    private var editTextOwnerName: EditText? = null

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editTextPetType = findViewById(R.id.editTextPetType)
        editTextPetName = findViewById(R.id.editTextPetName)
        editTextPetAge = findViewById(R.id.editTextPetAge)
        editTextOwnerName = findViewById(R.id.editTextOwnerName)

        imageView = findViewById(R.id.from_url)

        textView?.text = "Ветеринарный журнал"

        val cuteBarsikTheCat: Cat = Cat("Barsik", 10)
        val sillyDiegoTheDog: Dog = Dog("Diego", 6)
        val crazyMaryTheParrot: Bird = Bird("Mary", 3)
        val shyBorisTheHedgehog: Animal = Animal("Boris", 4)

        cuteBarsikTheCat.wash()
        crazyMaryTheParrot.knock()

        Log.d("New string was created:", "string".addLine("Add me!"))
    }

    fun onAddPetClick(view: View) {
        val petType = editTextPetType?.text.toString()
        val petName = editTextPetName?.text.toString()
        val petAge = editTextPetAge?.text.toString()
        val ownerName = editTextOwnerName?.text.toString().takeIf { it.isNotEmpty() } ?: "no owner"

        if (petName.isNotEmpty() && petAge.isNotEmpty()) {

            Log.d("Pet was added", "Pet type: $petType, Pet name: $petName, Pet age: $petAge, Owner name: $ownerName")
            Log.d("Pet says", animalSays(petType, petName, ownerName))

            editTextPetType?.text?.clear()
            editTextPetName?.text?.clear()
            editTextPetAge?.text?.clear()
            editTextOwnerName?.text?.clear()
        }

        val mems = arrayOf("https://avatars.dzeninfra.ru/get-zen_doc/1110951/pub_6124885201871c067c2441b1_61248999fb223510ec1dca5d/scale_1200", "https://i.pinimg.com/originals/54/a0/0c/54a00c1f25c869460ff57c5d8c0f6be3.jpg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRZegv0cbFxtbZshbU2tcegHCm05hYefiJR7tfw6SnHQs4CEF9IaGNSjSDe6ehuoL9bfo&usqp=CAU", "https://i.pinimg.com/originals/aa/1b/ce/aa1bce17ee2e7659c01e213b3a324108.jpg", "https://cs12.pikabu.ru/post_img/2020/10/05/1/og_og_1601850346229035653.jpg", "https://cs12.pikabu.ru/post_img/big/2022/11/24/4/1669266035136690160.jpg")
        val randomMem: String = mems.get(Random.nextInt(mems.size))
        imageView?.loadImg(randomMem) //вызываем созданный метод у imageView

    }

    private fun ImageView.loadImg(l : String) { //созданный экстеншен для ImageView
        Picasso.get().load(l).into(this)
    }

    private fun String.addLine(l : String): String { //экстеншен для String, чтобы не забыть что это
        return this + l
    }

    // Пусть животные что-то говорят в зависимости от вида и наличия хозяина, чтобы было веселее
    private fun animalSays(t: String, p: String, o: String) : String {
        var result = "$p the ${t.lowercase()} says:"
        if (o != "no owner") {
            result = if (t.lowercase() == "cat") {
                "$result \"$o is my slave.\""
            } else if (t.lowercase() == "dog") {
                "$result \"I'm $o's slave.\""
            } else {
                "$result \"$o, I \uD83D\uDC97 you!\""
            }
        }
        else {
            result = if (t.lowercase() == "cat") {
                "$result \"You need me!\""
            } else if (t.lowercase() == "dog") {
                "$result \"I need you!\""
            } else {
                "$result \"Am I not cute?\""
            }

        }
        return result
    }

    open class Animal(name: String, age: Int) {
        var name: String
        val age: Int
        init {
            this.name = name
            this.age = age
        }
        fun makeASound() {
            Log.d("Pet do smth", "$name makes a sound")
        }
    }

    class Cat(name: String, age: Int) : Animal(name, age) {
        fun wash() {
            Log.d("Pet do smth", "$name the cat is grooming")
        }
    }

    class Dog(name: String, age: Int) : Animal(name, age) {
        fun followCommands() {
            Log.d("Pet do smth", "$name the dog is enjoying theirs training")
        }
    }

    class Bird(name: String, age: Int) : Animal(name, age) {
        fun knock() {
            Log.d("Pet do smth", "$name the bird pecks with theirs beak")
        }
    }

}