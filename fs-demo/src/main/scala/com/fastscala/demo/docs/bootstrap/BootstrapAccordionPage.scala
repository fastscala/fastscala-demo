package com.fastscala.demo.docs.bootstrap

import com.fastscala.components.bootstrap5.accordion.BSAccordion
import com.fastscala.components.bootstrap5.modals.{BSModal5, BSModal5Base}
import com.fastscala.components.bootstrap5.offcanvas.{BSOffcanvas, BSOffcanvasBase}
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.fields.F7SubmitButtonField
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.select.F7SelectField
import com.fastscala.components.form7.fields.text.{F7IntOptField, F7LocalDateOptField, F7StringField}
import com.fastscala.components.form7.{F7Field, F7FormRenderer, Form7}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.forms.*
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.js.Js

import scala.xml.NodeSeq

class BootstrapAccordionPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Offcanvas Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    var editing = new User1(
      firstName = "",
      lastName = "",
      email = "",
      phoneNumber = "",
      securityLevel = 0,
      countryOfResidence = CountriesData.data(0),
      birthDay = Some("2022-08-04"),
      province = CitiesData.data.head._1,
      city = CitiesData.data.head._2.head
    )

    renderCodeSampleAndAutoClosePreviousOne("Basic") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      new BSAccordion(
        <span>User data</span> -> (implicit fsc =>
          idx => {
            import DefaultFSDemoBSForm7Renderers.*

            new Form7 {
              override def postSubmitForm()(implicit fsc: FSContext): Js = {
                BSModal5.verySimple("Created User", "Done")(modal =>
                  implicit fsc => {
                    <span><b>First Name:</b> {editing.firstName}</span><br/> ++
                      <span><b>Last Name:</b> {editing.lastName}</span><br/> ++
                      <span><b>Email:</b> {editing.email}</span><br/>
                  }
                )
              }

              lazy val _provField: F7SelectField[Province] =
                new F7SelectField[Province](CitiesData.data.keys.toList.sortBy(_.no)).label("Province").rw(editing.province, editing.province = _).option2String(_.name)

              override lazy val rootField: F7Field = F7VerticalField()(
                F7ContainerField("row")(
                  "col" -> new F7StringField().label("First Name").rw(editing.firstName, editing.firstName = _),
                  "col" -> new F7StringField().label("Last Name").rw(editing.lastName, editing.lastName = _)
                ),
                new F7StringField().label("Email").rw(editing.email, editing.email = _).inputType("email"),
                new F7SubmitButtonField(implicit fsc => BSBtn().BtnPrimary.lbl("Create User").btn.d_block)
              )

              override def formRenderer: F7FormRenderer = DefaultFSDemoBSForm7Renderers.formRenderer
            }.render()
          }
        ),
        <span>Actions</span> -> (implicit fsc =>
          idx => {
            BSBtn().BtnPrimary.lbl("Delete User").btn
          }
        )
      ).render()
    }
    closeCodeSample()
  }
}
