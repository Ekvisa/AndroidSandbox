package com.example.androidsandbox

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editTextPetType: EditText
    private lateinit var editTextPetName: EditText
    private lateinit var editTextPetAge: EditText
    private lateinit var editTextOwnerName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editTextPetType = findViewById(R.id.editTextPetType)
        editTextPetName = findViewById(R.id.editTextPetName)
        editTextPetAge = findViewById(R.id.editTextPetAge)
        editTextOwnerName = findViewById(R.id.editTextOwnerName)

        textView.text = "Ветеринарный журнал"

        val cuteBarsikTheCat: Cat = Cat("Barsik", 10)
        val sillyDiegoTheDog: Dog = Dog("Diego", 6)
        val crazyMaryTheParrot: Bird = Bird("Mary", 3)
        val shyBorisTheHedgehog: Animal = Animal("Boris", 4)

        Log.d("Pet do smth", cuteBarsikTheCat.wash())
        Log.d("Pet do smth", crazyMaryTheParrot.knock())
    }

    fun onAddPetClick(view: View) {
        val petType = editTextPetType.text.toString()
        val petName = editTextPetName.text.toString()
        val petAge = editTextPetAge.text.toString()
        val ownerName = editTextOwnerName.text.toString().takeIf { it.isNotEmpty() } ?: "no owner"

        if (petName.isNotEmpty() && petAge.isNotEmpty()) {
            // Выводим информацию о питомце в лог
            Log.d("Pet was added", "Pet type: $petType, Pet name: $petName, Pet age: $petAge, Owner name: $ownerName")
            Log.d("Pet says", animalSays(petType, petName, ownerName))

            // Очищаем поля ввода
            editTextPetType.text.clear()
            editTextPetName.text.clear()
            editTextPetAge.text.clear()
            editTextOwnerName.text.clear()
        }

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
        fun makeASound(): String {
            return "$name makes a sound"
        }
    }

    class Cat(name: String, age: Int) : Animal(name, age) {
        fun wash(): String {
            return "$name the cat is grooming"
        }
    }

    class Dog(name: String, age: Int) : Animal(name, age) {
        fun followCommands(): String {
            return "$name the dog is enjoying theirs training"
        }
    }

    class Bird(name: String, age: Int) : Animal(name, age) {
        fun knock(): String {
            return "$name the bird pecks with theirs beak"
        }
    }

}