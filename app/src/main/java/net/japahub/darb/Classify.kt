package net.japahub.darb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private lateinit var classifyBtn: Button
private lateinit var age: TextInputEditText
private lateinit var insulin: TextInputEditText
private lateinit var bp: TextInputEditText
private lateinit var skin: TextInputEditText
private lateinit var weight: TextInputEditText
private lateinit var pedi: TextInputEditText
private lateinit var preg: TextInputEditText

private lateinit var ageL: TextInputLayout
private lateinit var insulinL: TextInputLayout
private lateinit var bpL: TextInputLayout
private lateinit var skinL: TextInputLayout
private lateinit var weightL: TextInputLayout
private lateinit var pediL: TextInputLayout
private lateinit var pregL: TextInputLayout
class Classify : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classify)

        classifyBtn = findViewById(R.id.classify)
        age         = findViewById(R.id.ageInput)
        insulin     = findViewById(R.id.insulinInput)
        bp          = findViewById(R.id.bpInput)
        skin        = findViewById(R.id.skinInput)
        weight      = findViewById(R.id.weightInput)
        pedi        = findViewById(R.id.pediInput)
        preg        = findViewById(R.id.pregInput)
        ageL        = findViewById(R.id.age)
        insulinL    = findViewById(R.id.insulin)
        bpL         = findViewById(R.id.bp)
        skinL       = findViewById(R.id.skin)
        weightL     = findViewById(R.id.weight)
        pediL       = findViewById(R.id.pedi)
        pregL       = findViewById(R.id.preg)

        classifyBtn.setOnClickListener {
            if (TextUtils.isEmpty(age.text.toString())){
                ageL.error = "Enter Age"
            } else if (TextUtils.isEmpty((insulin.text.toString()))){
                ageL.isErrorEnabled = false
                insulinL.error = "Enter Insulin value"
            } else if(TextUtils.isEmpty(bp.text.toString())){
                insulinL.isErrorEnabled = false
                bpL.error = "Enter BP value"
            } else if(TextUtils.isEmpty(skin.text.toString())){
                bpL.isErrorEnabled = false
                skinL.error = "Enter BMI value"
            }else if(TextUtils.isEmpty(weight.text.toString())){
                skinL.isErrorEnabled = false
                weightL.error = "Enter Weight value"
            } else if (TextUtils.isEmpty(pedi.text.toString())){
                weightL.isErrorEnabled = false
                pediL.error = "Enter Pedi value"
            } else if (TextUtils.isEmpty(preg.text.toString())){
                pediL.isErrorEnabled = false
                pregL.error = "Enter number of pregnancy"
            } else{
//                ##CALL TENSORFLOW API HERE
//                ## AND DISPLAY OUTPUT OF THE RESULT
            }
        }
    }
}