package net.japahub.darb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Predict : AppCompatActivity() {
    private var viewModel: PredictViewModel = PredictViewModel()
    private lateinit var predictBtn: Button
    private lateinit var age: TextInputEditText
    private lateinit var insulin: TextInputEditText
    private lateinit var bp: TextInputEditText
    private lateinit var skin: TextInputEditText
    private lateinit var weight: TextInputEditText
    private lateinit var pedi: TextInputEditText
    private lateinit var plas: TextInputEditText
    private lateinit var preg: TextInputEditText
    private lateinit var ageL: TextInputLayout
    private lateinit var insulinL: TextInputLayout
    private lateinit var bpL: TextInputLayout
    private lateinit var skinL: TextInputLayout
    private lateinit var weightL: TextInputLayout
    private lateinit var pediL: TextInputLayout
    private lateinit var plasL: TextInputLayout
    private lateinit var pregL: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predict)

        initViewModel()
        seRes()

        //Predict Input Values
        age = findViewById(R.id.ageInput)
        insulin = findViewById(R.id.insulinInput)
        bp = findViewById(R.id.bpInput)
        skin = findViewById(R.id.skinInput)
        weight = findViewById(R.id.weightInput)
        pedi = findViewById(R.id.pediInput)
        plas = findViewById(R.id.plasInput)
        preg = findViewById(R.id.pregInput)

        //Predict Button Initialized
        predictBtn = findViewById(R.id.predict)

        //TextInput Layout
        ageL        = findViewById(R.id.age)
        insulinL    = findViewById(R.id.insulin)
        bpL         = findViewById(R.id.bp)
        skinL       = findViewById(R.id.skin)
        weightL     = findViewById(R.id.weight)
        pediL       = findViewById(R.id.pedi)
        plasL       = findViewById(R.id.plas)
        pregL       = findViewById(R.id.preg)

        //Predict Button in ACTION
        predictBtn.setOnClickListener {
            validateInputs()
        }
    }
    private fun createUInputs(){
        val uInputs = U(preg.text.toString(),
            plas.text.toString(),
            bp.text.toString(),
            skin.text.toString(),
            insulin.text.toString(),
            weight.text.toString(),
            pedi.text.toString(),
            age.text.toString())

        viewModel.makePredict(uInputs)
    }
    private fun initViewModel(){
        var viewModel = ViewModelProvider(this).get(PredictViewModel::class.java)
        Log.d("FUNC","Working 1")

    }
    private fun seRes() {
        viewModel.getPredictObservable().observe(this, Observer<UServerResponse?>{
            if (it == null){
                Toast.makeText(this@Predict,"Failed to make prediction",Toast.LENGTH_LONG).show()
            } else{
                finish()
                Toast.makeText(this@Predict,""+it.getPrediction().toString(),Toast.LENGTH_LONG).show()

            }
        })
    }
    private fun validateInputs(){
        when {
            TextUtils.isEmpty(age.text.toString()) -> {
                ageL.error = "Enter Age"
            }
            TextUtils.isEmpty((insulin.text.toString())) -> {
                ageL.isErrorEnabled = false
                insulinL.error = "Enter Insulin value"
            }
            TextUtils.isEmpty(bp.text.toString()) -> {
                insulinL.isErrorEnabled = false
                bpL.error = "Enter BP value"
            }
            TextUtils.isEmpty(skin.text.toString()) -> {
                bpL.isErrorEnabled = false
                skinL.error = "Enter BMI value"
            }
            TextUtils.isEmpty(weight.text.toString()) -> {
                skinL.isErrorEnabled = false
                weightL.error = "Enter Weight value"
            }
            TextUtils.isEmpty(pedi.text.toString()) -> {
                weightL.isErrorEnabled = false
                pediL.error = "Enter Pedi value"
            }
            TextUtils.isEmpty(plas.text.toString()) -> {
                pediL.isErrorEnabled = false
                plasL.error = "Enter Pedi value"
            }
            TextUtils.isEmpty(preg.text.toString()) -> {
                plasL.isErrorEnabled = false
                pregL.error = "Enter number of pregnancy"
            }
            else -> {
                //                ## CALL TENSORFLOW API HERE
                //                ##
                //                ## AND DISPLAY OUTPUT OF THE RESULT
                createUInputs()

            }
        }
    }
}