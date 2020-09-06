package com.sena.appculinariavirtual.interfaces

import com.sena.appculinariavirtual.dominio.entities.City
import com.sena.appculinariavirtual.dominio.entities.Department

interface IUbicationRepository {

    fun getDepartments(): MutableList<Department>

    fun getCities(): MutableList<City>

    fun getCities(department: Department): MutableList<City>

}