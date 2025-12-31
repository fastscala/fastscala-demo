package com.fastscala.demo.docs.forms

import com.fastscala.components.bootstrap5.form7.BSForm7Renderers
import com.fastscala.components.bootstrap5.modals.BSModal5
import com.fastscala.components.bootstrap5.toast.BSToast2
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.*
import com.fastscala.components.form7.fields.*
import com.fastscala.components.form7.fields.checkbox.F7CheckboxField
import com.fastscala.components.form7.fields.date.F7LocalDateOptField
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.number.F7IntOptField
import com.fastscala.components.form7.fields.select.F7SelectField
import com.fastscala.components.form7.fields.submit.F7SubmitButtonField
import com.fastscala.components.form7.fields.text.F7StringField
import com.fastscala.components.form7.renderers.F7FormRenderer
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.forms.model.{CitiesData, City, Province, User1}
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, printBeforeExec}

class SaveOnEnterAndOnChangeFormExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Save on enter/on change Form Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    // === code sample ===
    import BSFormRenderer.*
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
    val BSFormRenderer: BSForm7Renderers = new BSForm7Renderers {
      override def defaultRequiredFieldLabel: String = "Required Field"
    }
    val editing = new User1(
      firstName = "John",
      lastName = "Doe",
      email = "john.doe@email.com",
      phoneNumber = "+1 123456789",
      securityLevel = 3,
      countryOfResidence = CountriesData.data.find(_.name.common == "United States").get,
      birthday = None,
      province = Province(1, "P1"),
      city = City(1, "C1"),
      enableLogin = false
    )
    class ExampleForm extends Form7 {
      override def onSubmitIgnoredFormAlreadySaved()(implicit fsc: FSContext): Js =
        BSToast2.Simple(<span>Form already saved!</span>)(<p>
          Form is already saved, submit was ignored.
        </p>).positionTopRight.installAndShow()

      override def postSubmitForm()(implicit fsc: FSContext): Js = {
        BSToast2.Simple(<span>Submitted!</span>)(<div>
          <p>{editing.firstName}{editing.lastName}, with email {editing.email}, and birthday on {editing.birthday.map(_.toString).getOrElse("--")}.</p>
          <p>User from country {editing.countryOfResidence.name.common}.</p>
          <p>Login for user is: {editing.enableLogin}.</p>
        </div>).positionTopRight.installAndShow()
      }

      override lazy val rootField: F7Field = F7VerticalField()(
        F7ContainerField("row")("col" -> new F7StringField().label("First Name").rw(editing.firstName, editing.firstName = _), "col" -> new F7StringField().label("Last Name").rw(editing.lastName, editing.lastName = _)),
        new F7StringField().label("Email").rw(editing.email, editing.email = _).inputType("email"),
        new F7SelectField[Country](CountriesData.data.toList).label("Country of Residence").rw(editing.countryOfResidence, editing.countryOfResidence = _).option2String(_.name.common),
        new F7LocalDateOptField().rw(editing.birthday, editing.birthday = _).label("BirthDay"),
        new F7CheckboxField().label("Login enabled").rw(editing.enableLogin, editing.enableLogin = _),
        new F7SubmitButtonField[BSBtn](implicit fsc => BSBtn().sm.addClass(w_100.d_block.getClassAttr), _.lbl("Saved").BtnOutlineSecondary, _.lbl("Save").BtnSuccess, _.lbl("Error").BtnDanger)
      )

      override def formRenderer: F7FormRenderer = BSFormRenderer.formRenderer
    }
    // === code sample ===

    renderCodeSampleAndAutoClosePreviousOne("Form submitting on enter") {
      div.border.p_2.rounded.apply {
        new ExampleForm() {
          override def submitOnSuggestion: Boolean = true
        }.render()
      }
    }
    renderCodeSampleAndAutoClosePreviousOne("Form submitting on change") {
      div.border.p_2.rounded.apply {
        new ExampleForm() {
          override def submitOnChangedField: Boolean = true
        }.render()
      }
    }
    closeCodeSample()
  }
}
