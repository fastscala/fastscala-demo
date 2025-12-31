package com.fastscala.demo.docs.forms

import com.fastscala.components.bootstrap5.form7.BSForm7Renderers
import com.fastscala.components.bootstrap5.modals.BSModal5
import com.fastscala.components.bootstrap5.toast.BSToast2
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.*
import com.fastscala.components.form7.fields.*
import com.fastscala.components.form7.fields.checkbox.{F7CheckboxField, F7CheckboxOptField}
import com.fastscala.components.form7.fields.date.F7LocalDateOptField
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.multiselect.F7MultiSelectField
import com.fastscala.components.form7.fields.number.F7IntOptField
import com.fastscala.components.form7.fields.radio.F7RadioField
import com.fastscala.components.form7.fields.select.F7SelectField
import com.fastscala.components.form7.fields.submit.F7SubmitButtonField
import com.fastscala.components.form7.fields.text.{F7StringField, F7StringTextareaField}
import com.fastscala.components.form7.renderers.F7FormRenderer
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.forms.model.{CitiesData, City, Province, User1}
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, printBeforeExec}

class SaveFormStateInQueryStringExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Save on enter/on change Form Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      val BSFormRenderer = new BSForm7Renderers {
        override def defaultRequiredFieldLabel: String = "Required Field"
      }
      import BSFormRenderer.*
      div.border.p_2.rounded.apply {
        new Form7 with QueryStringSavedForm {

          override def submitOnSuggestion: Boolean = true

          override lazy val rootField: F7Field = F7VerticalField()(
            F7ContainerField("row")("col" -> new F7StringField().label("First Name").id("first_name"), "col" -> new F7StringField().label("Last Name").id("last_name")),
            new F7StringField().label("Email").inputType("email").id("email"),
            new F7StringTextareaField().label("Address").id("address"),
            new F7SelectField[Country](CountriesData.data.toList).label("Country of Residence").option2String(_.name.common).id("country"),
            new F7LocalDateOptField().label("Birthday").id("birthday"),
            new F7CheckboxField().label("Login enabled").id("login_enabled"),
            new F7CheckboxOptField().label("Is veteran?").id("veteran"),
            new F7MultiSelectField[String]().options(List("Sales", "Support", "Development", "Accounting")).label("Security clearance to access:").id("security_clearance"),
            new F7RadioField[String](() => List("Email", "Phone", "SMS")).label("Preferred contact method:").id("contact_method"),
            new F7SubmitButtonField[BSBtn](implicit fsc => BSBtn().sm.addClass(w_100.d_block.getClassAttr), _.lbl("Saved").BtnOutlineSecondary, _.lbl("Save").BtnSuccess, _.lbl("Error").BtnDanger)
          )

          override def formRenderer: F7FormRenderer = BSFormRenderer.formRenderer
        }.render()
      }
    }
    closeCodeSample()
  }
}
