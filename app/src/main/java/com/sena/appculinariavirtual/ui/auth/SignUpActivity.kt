package com.sena.appculinariavirtual.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sena.appculinariavirtual.R
import com.sena.appculinariavirtual.adapters.ArrayAdapterSpinner
import com.sena.appculinariavirtual.data.LocalUbicationRepository
import com.sena.appculinariavirtual.dominio.entities.City
import com.sena.appculinariavirtual.dominio.entities.Department
import com.sena.appculinariavirtual.interfaces.IUbicationRepository
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val ubicationRepository: IUbicationRepository = LocalUbicationRepository()
    private var departments: MutableList<Department> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnLogIn.setOnClickListener { goToLogIn() }

        departments = ubicationRepository.getDepartments()
        setAdapterSpinnerDepartment()
    }

    private fun setAdapterSpinnerDepartment(){

        val namesDepartment = departments.map { depart -> depart.name }
        val adapter = ArrayAdapterSpinner(context = this, items = namesDepartment).getAdapter()

        spinnerDepartaments.adapter = adapter
        spinnerDepartaments.onItemSelectedListener = this
    }

    private fun goToLogIn() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, pos: Int, p3: Long) {
        val department = Department(adapterView!!.getItemAtPosition(pos).toString())
        setCitiesSpinner(ubicationRepository.getCities(department))
    }

    private fun setCitiesSpinner(cities: MutableList<City>){
        val namesCity = cities.map { city -> city.name }
        val adapter = ArrayAdapterSpinner(context = this, items = namesCity).getAdapter()

        spinnerCities.adapter = adapter
    }
}