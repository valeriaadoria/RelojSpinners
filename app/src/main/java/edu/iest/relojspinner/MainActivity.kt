package edu.iest.relojspinner


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import edu.iest.relojspinner.databinding.ActivityMainBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener  {

    private var s1 : Spinner? = null
    private var s2 : Spinner? = null
    private lateinit var binding: ActivityMainBinding
    private var textoSeleccionado: Int=1
    private var textoSeleccionado2: Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnCambio.text="Evaluar"

        val adaptador = ArrayAdapter.createFromResource(this,R.array.numUno,android.R.layout.simple_spinner_item)
        val adaptador2 = ArrayAdapter.createFromResource(this,R.array.numDos,android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spOpciones.adapter=adaptador
        binding.spOpciones.onItemSelectedListener=this
        binding.spOpciones2.adapter=adaptador2
        binding.spOpciones2.onItemSelectedListener=this

        binding.bnCambio.setOnClickListener {
            if(textoSeleccionado > textoSeleccionado2){
                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("Resultado").setMessage("El numero mayor es $textoSeleccionado en el Spinner 1")
                    .setCancelable(false)
                    .setPositiveButton("OK",DialogInterface.OnClickListener{
                            dialogInterface, i ->
                        //aqui el codigo
                        binding.tvSaludo.text="Numero Mayor"
                    })
                    .setNegativeButton("Cancelar",DialogInterface.OnClickListener{
                            dialogInterface, i ->
                        Toast.makeText(this,"Una lastima :(", Toast.LENGTH_SHORT).show()
                    }).show()
                }else if(textoSeleccionado < textoSeleccionado2) {
                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("Resultado").setMessage("El numero mayor es $textoSeleccionado2 en el Spinner 2")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                        //aqui el codigo
                        binding.tvSaludo.text = "Numero Mayor"
                    })
                    .setNegativeButton(
                        "Cancelar",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(this, "Una lastima :(", Toast.LENGTH_SHORT).show()
                        }).show()
            }else {
                val alerta = android.app.AlertDialog.Builder(this)
                alerta.setTitle("Resultado").setMessage("Los numeros son iguales en ambos Spinners")
                    .setCancelable(false)
                    .setPositiveButton(
                        "OK",
                        android.content.DialogInterface.OnClickListener { dialogInterface, i ->
                            //aqui el codigo
                            binding.tvSaludo.text = "Numero Mayor"
                        })
                    .setNegativeButton(
                        "Cancelar",
                        android.content.DialogInterface.OnClickListener { dialogInterface, i ->
                            android.widget.Toast.makeText(
                                this,
                                "Una lastima :(",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                        }).show()
            }
        }
    }

   //La vista del adaptador
    override fun onItemSelected(p0: AdapterView<*>?, vistaRow: View?, position: Int, idVista: Long) {

        when(p0?.id){
           R.id.sp_opciones->{
              textoSeleccionado= p0.getItemAtPosition(position).toString().toInt()
           }

            R.id.sp_opciones2 -> {
                textoSeleccionado2= p0.getItemAtPosition(position).toString().toInt()
           }
       }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}