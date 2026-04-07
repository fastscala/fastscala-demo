package com.fastscala.demo.docs.forms

import com.fastscala.components.bootstrap5.form7.BSForm7Renderers
import com.fastscala.components.bootstrap5.modals.BSModal5
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.*
import com.fastscala.components.form7.fields.*
import com.fastscala.components.form7.fields.date.F7LocalDateOptField
import com.fastscala.components.form7.fields.file.F7FileUploadField
import com.fastscala.components.form7.fields.html.F7HtmlField
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
import com.fastscala.scala_xml.ScalaXmlNodeSeqUtils.MkNSFromElems
import com.fastscala.scala_xml.js.JS

import java.time.LocalDate
import java.util.Base64
import scala.xml.NodeSeq

class FileUploadInputExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "File Upload Input Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      import DefaultFSDemoBSForm7Renderers.*
      div.border.p_2.rounded.apply {
        new Form7 {

          lazy val fileInputField = new F7FileUploadField().label("File upload").multiple(true).previewRenderer(files => implicit fsc => {
            div.row.overflow_x_auto.apply {
              files.collect({
                case f if f.name.endsWith("jpg") || f.name.endsWith("png") =>
                  <div class="col mb-1">{<img src={s"data:${f.contentType};base64, " + Base64.getEncoder.encodeToString(f.bytes)}></img>.d_block.w_100}</div>
              }).mkNS
            }
          })
          lazy val fileInputDescription = new F7HtmlField(_ => <p>The file input field has currently {fileInputField.currentValue.size} files.</p>).dependsOn(fileInputField)

          override lazy val rootField: F7Field = F7VerticalField()(
            fileInputField,
            fileInputDescription,
            new F7SubmitButtonField(implicit fsc => BSBtn().BtnPrimary.lbl("Submit").btn.d_block),
          )

          override def formRenderer: F7FormRenderer = DefaultFSDemoBSForm7Renderers.formRenderer
        }.render()
      }
    }
    closeCodeSample()
  }
}
