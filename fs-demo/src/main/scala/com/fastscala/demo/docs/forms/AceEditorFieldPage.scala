package com.fastscala.demo.docs.forms

import com.fastscala.components.aceeditor.{AceEditor, Language, Theme}
import com.fastscala.components.bootstrap5.modals.BSModal5
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.fields.*
import com.fastscala.components.form7.fields.aceeditor.F7AceEditorField
import com.fastscala.components.form7.fields.color.F7ColorField
import com.fastscala.components.form7.fields.date.F7LocalDateOptField
import com.fastscala.components.form7.fields.datetimelocal.F7LocalDateTimeOptField
import com.fastscala.components.form7.fields.html.F7HtmlField
import com.fastscala.components.form7.fields.layout.F7VerticalField
import com.fastscala.components.form7.fields.number.{F7DoubleField, F7DoubleOptField, F7IntField, F7IntOptField}
import com.fastscala.components.form7.fields.submit.F7SubmitButtonField
import com.fastscala.components.form7.fields.text.*
import com.fastscala.components.form7.{DefaultForm7, F7Field}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.{MultipleCodeExamples2Page, MultipleCodeExamples3Page}
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.JS

import java.time.{LocalDate, LocalDateTime}
import scala.xml.NodeSeq

class AceEditorFieldPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Form 7 Ace Editor Fields"

  override def append2Body(): NodeSeq =
    super.append2Body() ++
      AceEditor.jsImports(Language.scala, Theme.textmate)

  override def append2Head(): NodeSeq = super.append2Head() ++ AceEditor.cssImports

  import DefaultFSDemoBSForm7Renderers.*
  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Ace Editor Field") {
      val stringField = new F7StringField().label("Name (string input)")
        .setInternalValue("John Doe")
      val intField = new F7IntField().label("Your age")
        .help("An empty input translates into zero")
        .setInternalValue(47)
      val aceEditorField = new F7AceEditorField().label("HTML").setInternalValue(
        """<!DOCTYPE html>
          |<html lang="en">
          |  <head>
          |    <meta charset="utf-8">
          |    <title>title</title>
          |  </head>
          |  <body>
          |  </body>
          |</html>
          |""".stripMargin
      )
      val currentAceFieldValueField = new F7HtmlField(_ => <h6>Current value of Ace Editor field:</h6><pre>{aceEditorField.currentValue}</pre>).dependsOn(aceEditorField)

      div.apply {
        new DefaultForm7() {
          override lazy val rootField: F7Field = F7VerticalField()(
            stringField,
            intField,
            aceEditorField,
            currentAceFieldValueField,
            new F7SubmitButtonField(implicit fsc => BSBtn().BtnPrimary.lbl("Submit").btn.d_block.w_100)
          )

          override def postSubmitForm()(implicit fsc: FSContext): Js = BSModal5.verySimple("Submitted!", "Ok") {
            modal =>
              implicit fsc =>
                <h6>Your data:</h6> ++
                <ul>
                  <li><b>String Field:</b> {stringField.getInternalValue()}</li>
                  <li><b>Int Field:</b> {intField.getInternalValue()}</li>
                  <li><b>Ace Editor Field:</b> <pre>{aceEditorField.currentValue}</pre></li>
                </ul>
          }

        }.render()
      }
    }
    closeCodeSample()
  }
}
