package com.fastscala.demo.docs.forms.model

import com.fastscala.demo.testdata.Country

import java.time.LocalDate

class User1(var firstName: String, var lastName: String, var email: String, var phoneNumber: String, var securityLevel: Int, var countryOfResidence: Country, var birthday: Option[LocalDate], var province: Province, var city: City, var enableLogin: Boolean)

case class Province(no: Int, name: String)

case class City(no: Int, name: String)

object CitiesData {
  lazy val data = Map(
    Province(1, "P1") -> List(City(11, "C11"), City(12, "C12"), City(13, "C13"), City(14, "C14"), City(15, "C15"), City(16, "C16")),
    Province(2, "P2") -> List(City(21, "C21"), City(22, "C22"), City(23, "C23"), City(24, "C24"), City(25, "C25"), City(26, "C26"), City(27, "C27")),
    Province(3, "P3") -> List(City(31, "C31"), City(32, "C32"), City(33, "C33"), City(34, "C34"), City(35, "C35"), City(36, "C36"), City(37, "C37")),
    Province(4, "P4") -> List(City(41, "C41"), City(42, "C42"), City(43, "C43"), City(34, "C44"), City(35, "C45"))
  )
}
