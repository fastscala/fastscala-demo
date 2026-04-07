package com.fastscala.demo.docs.forms

import com.fastscala.components.bootstrap5.form7.layout.F7BSFormInputGroup
import com.fastscala.components.bootstrap5.toast.BSToast2
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.fields.*
import com.fastscala.components.form7.fields.checkbox.{F7CheckboxField, F7CheckboxOptField}
import com.fastscala.components.form7.fields.color.F7ColorField
import com.fastscala.components.form7.fields.date.{F7LocalDateField, F7LocalDateOptField}
import com.fastscala.components.form7.fields.datetimelocal.F7LocalDateTimeOptField
import com.fastscala.components.form7.fields.html.{F7HtmlField, F7HtmlSurroundField}
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.multiselect.F7MultiSelectField
import com.fastscala.components.form7.fields.number.{F7DoubleField, F7DoubleOptField, F7IntField, F7IntOptField}
import com.fastscala.components.form7.fields.radio.{F7RadioField, F7RadioOptField}
import com.fastscala.components.form7.fields.select.{F7SelectField, F7SelectOptField}
import com.fastscala.components.form7.fields.submit.F7SubmitButtonField
import com.fastscala.components.form7.fields.text.*
import com.fastscala.components.form7.mixins.F7FieldWithEnabled
import com.fastscala.components.form7.mixins.mainelem.F7FieldWithDisabled
import com.fastscala.components.form7.{DefaultForm7, F7Field, Form7}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{Continents, Fruit}
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, printBeforeExec}

import scala.util.{Random, Try}

class UpdateClientSideWithoutRerenderingPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Form 7 - Update client side without full rerender"

  import DefaultFSDemoBSForm7Renderers.*
  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    renderCodeSampleAndAutoClosePreviousOne("Update field properties on another field change") {

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      val randomSeed: F7IntField = new F7IntField().step(1).label("Random seed").doSyncToServerOnChange

      val checkboxField = new F7CheckboxField().labelStrF(() => s"Checkbox example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val checkboxOptField = new F7CheckboxOptField().labelStrF(() => s"Tristate checkbox example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val colorField = new F7ColorField().labelStrF(() => s"Color example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val dateField = new F7LocalDateField().labelStrF(() => s"Date example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val dateOptField = new F7LocalDateOptField().labelStrF(() => s"Date optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val dateTimeOptField = new F7LocalDateTimeOptField().labelStrF(() => s"Date time optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val htmlField = new F7HtmlField(_ => <span>HTML HERE</span>).dependsOn(randomSeed)
      val textSurroundedField = new F7StringField().labelStrF(() => s"Surrounded text field example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val surroundField = new F7HtmlSurroundField(elem => <div>{elem}</div>.shadow.shadow_sm.p_3)(textSurroundedField).dependsOn(randomSeed)
      val multiSelectField = new F7MultiSelectField[Int]().options(1 to 10).labelStrF(() => s"Multi-select example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val doubleField = new F7DoubleField().labelStrF(() => s"Double example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val doubleOptField = new F7DoubleOptField().labelStrF(() => s"Double optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val intField = new F7IntField().labelStrF(() => s"Int example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val intOptField = new F7IntOptField().labelStrF(() => s"Int optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val radioField = new F7RadioField[String](Continents()).labelStrF(() => s"Radio example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val radioOptField = new F7RadioOptField[String]().optionsNonEmpty(Continents()).option2String(_.getOrElse("--")).labelStrF(() => s"Radio optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val selectField = new F7SelectField[String](Continents()).labelStrF(() => s"Select example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val selectOptField = new F7SelectOptField[String]().optionsNonEmpty(Continents()).option2String(_.getOrElse("--")).labelStrF(() => s"Select optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val stringField = new F7StringField().labelStrF(() => s"String example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed)
      val textareaField = new F7StringTextareaField().labelStrF(() => s"String textarea example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed).numRows(() => Some(randomSeed.currentValue + 1))
      val textareaOptField = new F7StringOptTextareaField().labelStrF(() => s"String textarea optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed).numRows(() => Some(randomSeed.currentValue + 1))

      List[F7FieldWithDisabled](
        checkboxField,
        checkboxOptField,
        colorField,
        dateField,
        dateOptField,
        dateTimeOptField,
        textSurroundedField,
        surroundField,
        multiSelectField,
        doubleField,
        doubleOptField,
        intField,
        intOptField,
        radioField,
        radioOptField,
        selectField,
        selectOptField,
        stringField,
        textareaField,
        textareaOptField,
      ).foreach(f => {
        f.disabled(() => randomSeed.currentValue == 1)
      })

      List[F7FieldWithEnabled](
        checkboxField,
        checkboxOptField,
        colorField,
        dateField,
        dateOptField,
        dateTimeOptField,
        htmlField,
        textSurroundedField,
        surroundField,
        multiSelectField,
        doubleField,
        doubleOptField,
        intField,
        intOptField,
        radioField,
        radioOptField,
        selectField,
        selectOptField,
        stringField,
        textareaField,
        textareaOptField,
      ).foreach(f => {
        f.enabled(() => randomSeed.currentValue != 2)
      })

      div.border.p_2.rounded.apply {
        new DefaultForm7() {

          override lazy val rootField: F7Field = F7VerticalField()(
            randomSeed,
            new F7ContainerField("row")(
              "col-md-6" -> F7VerticalField()(
                checkboxField,
                checkboxOptField,
                colorField,
                dateField,
                dateOptField,
                dateTimeOptField,
                htmlField,
                surroundField,
                multiSelectField,
                doubleField,
                doubleOptField,
                intField,
                intOptField,
              ),
              "col-md-6" -> F7VerticalField()(
                radioField,
                radioOptField,
                selectField,
                selectOptField,
                stringField,
                textareaField,
                textareaOptField,
              ),
            ),
            new F7SubmitButtonField(implicit fsc => BSBtn().BtnPrimary.lbl("Submit").btn.d_block.w_100),
          )

          override def postSubmitForm()(implicit fsc: FSContext): Js =
            BSToast2.VerySimple(label.apply("Submitted"))(div.my_1.apply("You submitted the form")).installAndShow()
        }.render()
      }
    }

    renderCodeSampleAndAutoClosePreviousOne("Update number of rows on another field change") {

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      val randomSeed: F7IntField = new F7IntField().step(1).label("Random seed").doSyncToServerOnChange
      val textareaField = new F7StringTextareaField().labelStrF(() => s"String textarea example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed).numRows(() => Some(randomSeed.currentValue + 1))
      val textareaOptField = new F7StringOptTextareaField().labelStrF(() => s"String textarea optional example (seed is ${randomSeed.currentValue})").dependsOn(randomSeed).numRows(() => Some(randomSeed.currentValue + 1))

      div.border.p_2.rounded.apply {
        new DefaultForm7() {

          override lazy val rootField: F7Field = F7VerticalField()(
            randomSeed,
            textareaField,
            textareaOptField,
            new F7SubmitButtonField(implicit fsc => BSBtn().BtnPrimary.lbl("Submit").btn.d_block.w_100),
          )

          override def postSubmitForm()(implicit fsc: FSContext): Js =
            BSToast2.VerySimple(label.apply("Submitted"))(div.my_1.apply("You submitted the form")).installAndShow()
        }.render()
      }
    }
    closeCodeSample()
  }
}
