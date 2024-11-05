package com.example.oyun

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.oyun.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageliste = ArrayList<ImageView>()
    var runnable = kotlinx.coroutines.Runnable {  }
    var handler = Handler()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imageliste.add(binding.imageView1)
        imageliste.add(binding.imageView2)
        imageliste.add(binding.imageView3)
        imageliste.add(binding.imageView4)
        imageliste.add(binding.imageView5)
        imageliste.add(binding.imageView6)
        imageliste.add(binding.imageView7)
        imageliste.add(binding.imageView8)
        imageliste.add(binding.imageView9)
        imageliste.add(binding.imageView10)
        imageliste.add(binding.imageView11)
        imageliste.add(binding.imageView12)

        gizle()
        object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                binding.time.text = "Time :${p0/1000}"

            }

            override fun onFinish() {
                binding.time.text = "Süre Bitti!"
                handler.removeCallbacks(runnable)
                for(image in imageliste){
                    image.visibility = View.INVISIBLE
                }
                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Oyun Bitti!")
                alert.setMessage("Yeniden Başlamak İster Misiniz?")
                alert.setPositiveButton("Evet", DialogInterface.OnClickListener{dialogInterface, i ->
                    var main1 = intent
                    finish()
                    startActivity(main1)
                })
                alert.setNegativeButton("Hayır", DialogInterface.OnClickListener{dialogInterface, i ->
                    Toast.makeText(this@MainActivity, "Oyun Bitti!", Toast.LENGTH_SHORT).show()
                })


                alert.show()
            }
        }.start()



    }
    fun scoreartir(view: View){
        score +=1
        binding.score.text = "Score: $score"

    }
    fun scoreazalt(view: View){
        score -=1
        binding.score.text = "Score: $score"
    }
    fun gizle(){
        runnable = object:Runnable{
            override fun run() {
                for(image in imageliste){
                    image.visibility = View.INVISIBLE
                }
                var random = java.util.Random()
                var randomindex = random.nextInt(12)
                imageliste[randomindex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 800)

            }
        }
        handler.post(runnable)

    }

}