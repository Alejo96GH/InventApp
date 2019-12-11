package com.example.inventapp

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.inventapp.utils.Constantes.Companion.EMPTY
import com.example.inventapp.utils.Constantes.Companion.INTERLIN
import com.example.inventapp.utils.Constantes.Companion.SPACE
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        var sexo = "Masculino"

        rb_masculino.setOnClickListener{
            sexo = getString(R.string.masculino)
        }

        rb_femenino.setOnClickListener{
            sexo = getString(R.string.femenino)
        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM-DD-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha = sdf.format(cal.time).toString()
            }

        }

        bt_showPicker.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        bt_registrar.setOnClickListener {
            val nombre :String = et_nombre.text.toString()
            val correo :String = et_correo.text.toString()
            val telefono :String = et_telefono.text.toString()
            val password :String = et_password.text.toString()
            val reppassword :String = et_reppassword.text.toString()
            var pasatiempos = EMPTY

            if (cb_cine.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.cine)
            if (cb_gimansio.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.gimnasio)
            if (cb_leer.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.leer)
            if (cb_series.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.series)




           /* if (rb_masculino.isChecked) sexo = "Masculino"
            else sexo = "Femenino" */

            if (nombre.isEmpty() ||
                        correo.isEmpty() ||
                        et_telefono.text.toString().isEmpty() ||
                        password.isEmpty()

            ) {
                Toast.makeText( this, "Debe digitar todos los campos", Toast.LENGTH_SHORT).show()
            } else {

            tv_resultado.text = getString(R.string.nombre_lb) + nombre + INTERLIN +
                    getString(R.string.correo) + correo + INTERLIN +
                    getString(R.string.telefono) + telefono + INTERLIN +
                    getString(R.string.password) + password + INTERLIN+
                    getString(R.string.sexo) + sexo + INTERLIN +
                    getString(R.string.pasatiempos) + pasatiempos + INTERLIN +
                    getString(R.string.fecha_nacimiento) + SPACE + fecha
        }}
    }
}
