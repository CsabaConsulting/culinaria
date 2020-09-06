package com.sena.appculinariavirtual.dominio.entities


data class City(val name: String, val department: Department) {

    override fun toString(): String = name

}