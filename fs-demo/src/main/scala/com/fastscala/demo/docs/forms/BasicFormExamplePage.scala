package com.fastscala.demo.docs.forms

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.JS
import com.fastscala.components.bootstrap5.form7.BSForm7Renderers
import com.fastscala.components.bootstrap5.modals.BSModal5
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.*
import com.fastscala.components.form7.fields.*
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.select.F7SelectField
import com.fastscala.components.form7.fields.text.{F7IntOptField, F7LocalDateOptField, F7StringField}
import com.fastscala.demo.docs.forms.model.{CitiesData, City, Province, User1}
import com.fastscala.demo.testdata.{CountriesData, Country}

import java.time.LocalDate

class BasicFormExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Simple Form Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      var editing = new User1(
        firstName = "",
        lastName = "",
        email = "",
        phoneNumber = "",
        securityLevel = 0,
        countryOfResidence = CountriesData.data(0),
        birthday = Some(LocalDate.of(2000, 5, 1)),
        province = CitiesData.data.head._1,
        city = CitiesData.data.head._2.head,
        enableLogin = false
      )
      val BSFormRenderer = new BSForm7Renderers {
        override def defaultRequiredFieldLabel: String = "Required Field"
      }
      import BSFormRenderer.*
      div.border.p_2.rounded.apply {
        new Form7 {
          override def postSubmitForm()(implicit fsc: FSContext): Js = {
            BSModal5.verySimple(
              "Created User",
              "Done"
            )(modal => implicit fsc => {
              <span><b>First Name:</b> {editing.firstName}</span><br/> ++
              <span><b>Last Name:</b> {editing.lastName}</span><br/> ++
              <span><b>Email:</b> {editing.email}</span><br/> ++
              <span><b>Phone:</b> {editing.phoneNumber}</span><br/> ++
              <span><b>Security Level:</b> {editing.securityLevel}</span><br/> ++
              <span><b>Country of Residence:</b> {editing.countryOfResidence.name.common}</span><br/> ++
              <span><b>Date of Birth:</b> {editing.birthday.getOrElse("N/A")}</span><br/> ++
              <span><b>Province:</b> {s"${editing.province.name}(${editing.province.no})"}</span><br/>++
              <span><b>City:</b> {s"${editing.city.name}(${editing.city.no})"}</span>
            })
          }

          lazy val _provField: F7SelectField[Province] = new F7SelectField[Province](CitiesData.data.keys.toList.sortBy(_.no)).label("Province").rw(editing.province, editing.province = _).option2String(_.name)

          override lazy val rootField: F7Field = F7VerticalField()(
            F7ContainerField("row")(
              "col" -> new F7StringField().label("First Name").rw(editing.firstName, editing.firstName = _)
              , "col" -> new F7StringField().label("Last Name").rw(editing.lastName, editing.lastName = _)
            )
            , new F7StringField().label("Email").rw(editing.email, editing.email = _).inputType("email")
            , new F7StringField().label("Phone Number").rw(editing.phoneNumber, editing.phoneNumber = _).inputType("tel")
            , new F7SelectField[Country](CountriesData.data.toList).label("Country of Residence").rw(editing.countryOfResidence, editing.countryOfResidence = _).option2String(_.name.common)
            , new F7IntOptField().label("Security Level").rw(Some(editing.securityLevel), oi => editing.securityLevel = oi.getOrElse(0))
            , new F7LocalDateOptField().rw(editing.birthday, editing.birthday = _).label("Birthday")
            , _provField
            , new F7SelectField[City](() => CitiesData.data(_provField.currentValue)).label("City").rw(editing.city, editing.city = _).option2String(_.name).deps(() => Set[F7Field](_provField))
            , new F7SubmitButtonField(implicit fsc => BSBtn().BtnPrimary.lbl("Create User").btn.d_block)
          )

          override def formRenderer: F7FormRenderer = BSFormRenderer.formRenderer
        }.render()
      }
    }
    closeCodeSample()
  }
}
