package com.sena.appculinariavirtual.data

import com.sena.appculinariavirtual.dominio.entities.City
import com.sena.appculinariavirtual.dominio.entities.Department
import com.sena.appculinariavirtual.interfaces.IUbicationRepository


class LocalUbicationRepository: IUbicationRepository {

    override fun getDepartments(): MutableList<Department> = mutableListOf(
        Department("Risaralda"),
        Department("Antioquia"),
        Department("Caldas"),
        Department("Cundinamarda")
    )

    override fun getCities(): MutableList<City> = mutableListOf(
        City("Pereira", Department("Risaralda")),
        City("Dosquebradas", Department("Risaralda")),
        City("Viterbo", Department("Caldas")),
        City("Medellín", Department("Antioquia"))
    )

    override fun getCities(department: Department): MutableList<City> {
        val cities = getCities().filter { city -> city.department.name == department.name }
        return cities.toMutableList()
    }

}