package net.japahub.darb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

private lateinit var viewPager2: ViewPager2
private val sliderHandler = Handler()
private lateinit var classify: ImageButton
private lateinit var appointment: ImageButton
private lateinit var reminder: ImageButton
private lateinit var diet: ImageButton
private lateinit var hospital: ImageButton
private lateinit var contactDoctor: ImageButton
private lateinit var settings: ImageButton
private lateinit var help: ImageButton
private lateinit var emergency: ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        classify = findViewById(R.id.classify)
        appointment = findViewById(R.id.appointment)
        reminder = findViewById(R.id.reminder)
        diet = findViewById(R.id.diet)
        hospital = findViewById(R.id.hospital)
        contactDoctor = findViewById(R.id.contact_doctor)
        settings = findViewById(R.id.settings)
        help = findViewById(R.id.help)
        emergency = findViewById(R.id.emergency)

        classify.setOnClickListener{
            val intent = Intent(this, Classify::class.java)
            startActivity(intent)
        }
        appointment.setOnClickListener{
            val intent = Intent(this, Hospital::class.java)
            startActivity(intent)
        }
        reminder.setOnClickListener{
            val intent = Intent(this, BetaView::class.java)
            startActivity(intent)
        }
        diet.setOnClickListener{
            val intent = Intent(this, BetaView::class.java)
            startActivity(intent)
        }
        hospital.setOnClickListener{
            val intent = Intent(this, Hospital::class.java)
            startActivity(intent)
        }
        contactDoctor.setOnClickListener{
            val intent = Intent(this, BetaView::class.java)
            startActivity(intent)
        }
        settings.setOnClickListener{
            val intent = Intent(this, BetaView::class.java)
            startActivity(intent)
        }
        help.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
        }
        emergency.setOnClickListener{
            val intent = Intent(this, Emergency::class.java)
            startActivity(intent)
        }


        viewPager2 = findViewById(R.id.viewPager_ImageSlider)

        val sliderItems: MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.drawable.image1))
        sliderItems.add(SliderItem(R.drawable.image2))
        sliderItems.add(SliderItem(R.drawable.image3))
        sliderItems.add(SliderItem(R.drawable.image4))

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer{ page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.25f
        }
        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}