
package com.example.dutyapp

import android.app.*
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnDate: Button
    private lateinit var btnMode: Button
    private lateinit var btnBaseDate: Button
    private lateinit var txtResult: TextView

    private var selectedDate: Calendar = Calendar.getInstance()
    private var baseDate: Calendar = Calendar.getInstance()
    private var cycle = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDate = findViewById(R.id.btnDate)
        btnMode = findViewById(R.id.btnMode)
        btnBaseDate = findViewById(R.id.btnBaseDate)
        txtResult = findViewById(R.id.txtResult)

        btnDate.setOnClickListener { pick(selectedDate) }
        btnBaseDate.setOnClickListener { pick(baseDate) }
        btnMode.setOnClickListener {
            val opts = arrayOf("上一休一","上一休二","上一休三")
            AlertDialog.Builder(this).setItems(opts){_,i->
                cycle = i+2; btnMode.text = opts[i]; calc()
            }.show()
        }

        calc()
    }

    private fun pick(cal: Calendar){
        DatePickerDialog(this,{_,y,m,d->
            cal.set(y,m,d); calc()
        },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun calc(){
        val diff = ((selectedDate.timeInMillis - baseDate.timeInMillis)/(1000*60*60*24)).toInt()
        txtResult.text = if(diff % cycle == 0) "上班" else "休息"
    }
}
