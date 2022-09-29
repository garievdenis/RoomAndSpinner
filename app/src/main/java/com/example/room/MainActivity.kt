package com.example.room

//noinspection SuspiciousImport
import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.room.data.DATABASE_NAME
import com.example.room.data.MoneyDatabase
import com.example.room.data.model.CostType
import com.example.room.databinding.ActivityMainBinding
import java.util.concurrent.Executors

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var costTypes: MutableList<CostType> = mutableListOf()
    private var titleString: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val db:MoneyDatabase = Room.databaseBuilder(this, MoneyDatabase::class.java, DATABASE_NAME).build()
        val moneyDao = db.moneyDAO()
        val executor = Executors.newSingleThreadExecutor()
        val types = moneyDao.getAllTypes()

        var spinnerAdapter: ArrayAdapter<String>

        types.observe(this) { it ->
            costTypes.addAll(it)
            titleString.clear()
            it.forEach {
                titleString.add(it.title)
            }

            spinnerAdapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, titleString)
            // spinnerAdapter.setDropDownViewResource(R.layout.dropdown_spinner_item)
            binding.spinner.adapter = spinnerAdapter

        }
        binding.button.setOnClickListener {
            if (!binding.editTextTextPersonName.text.isEmpty()) {
                executor.execute {
                    moneyDao.addType(CostType(0, binding.editTextTextPersonName.text.toString()))
                }
                val types = moneyDao.getAllTypes()
                types.observe(this) { it ->
                    titleString.clear()
                    it.forEach {
                        titleString.add(it.title)
                    }
                }
                binding.editTextTextPersonName.text.clear()
            } else {
                Toast.makeText(this,"Данные введены некорректно", Toast.LENGTH_SHORT).show()
            }
        }
    }
}