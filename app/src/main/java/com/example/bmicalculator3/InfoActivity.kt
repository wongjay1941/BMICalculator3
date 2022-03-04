package com.example.bmicalculator3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bmicalculator3.databinding.ActivityInfoBinding

//To eliminate the use of findViewByID method, no need to use it
//lateinit = late initialize, var = variable
private lateinit var binding: ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Associate view(s) to code
        binding = ActivityInfoBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_info)
        setContentView(binding.root)

        val bmi = intent.getFloatExtra(MainActivity.EXTRA_BMI, 0f)

        if(bmi > 0){
            binding.textViewCurrentBMI.text = String.format("%s : %.2f", getString(R.string.current_bmi), bmi)
        }

        //Using binding will save the steps to declare variables, using methods, etc
        binding.buttonClose.setOnClickListener{
            finish()// Close the activity
        }

        binding.buttonFindOutMore.setOnClickListener{
            //Implicit Intent - to view a web page
            val intent = Intent(Intent.ACTION_VIEW)
            //First method
            intent.setData(Uri.parse("https://www.euro.who.int/en/health-topics/disease-prevention/nutrition/a-healthy-lifestyle/body-mass-index-bmi"))
            //Second method
            intent.data = Uri.parse("https://www.euro.who.int/en/health-topics/disease-prevention/nutrition/a-healthy-lifestyle/body-mass-index-bmi")

            //Check the availability of app to handle this intent
            //Package Manager - a class that keep track of all packages installed
            if(intent.resolveActivity(packageManager) != null){ //will return array
                //At least one App/Activity could handle this intent
                startActivity(intent)
            }
            else{
                //None - no App/Activity could handle this intent
                Toast.makeText(this, getString(R.string.no_app), Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageViewContact.setOnClickListener{
            //Implicit Intent
            val intent = Intent(Intent.ACTION_VIEW)
            //val intent = Intent(Intent.ACTION_DIAL) Directly call the phone no
            intent.data = Uri.parse("tel:03-12341234")
            startActivity(intent)
        }

        binding.imageViewLocation.setOnClickListener{
            //Implicit Intent
            val intent = Intent(Intent.ACTION_VIEW)
            //geo:0,0?q=weight+management
            intent.data = Uri.parse("geo:3.2051384339679236, 101.72061828192997")
            startActivity(intent)
        }

        binding.imageViewEmail.setOnClickListener{
            //Implicit Intent
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:info@weightmanagement.gov.my")
            startActivity(intent)
        }
    }
}