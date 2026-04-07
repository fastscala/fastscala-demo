package com.fastscala.demo.docs.fastscala

import com.fastscala.components.bootstrap5.utils.{FileUploadDedicatedXmlHttpRequest, FileUploadHiddenTargetForm}
import com.fastscala.core.{FSContext, FSSessionVarOpt, FSUploadedFile}
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.ScalaXmlNodeSeqUtils.MkNSFromElems
import com.fastscala.scala_xml.js.JS
import org.apache.commons.io.IOUtils

import java.util.Base64

class FileUploadDedicatedXmlHttpRequestPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "File Upload Example (dedicated XMLHttpRequest)"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source", description = <p>Creates an XMLHttpRequest. Includes updating a progress bar.</p>) {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      val allUploadedFiles = collection.mutable.ListBuffer[FSUploadedFile]()
      JS.rerenderable(rerenderer => implicit fsc => {
        div.apply {
          if (allUploadedFiles.isEmpty) {
            h3.apply("Upload images:") ++
              FileUploadDedicatedXmlHttpRequest(
                uploadedFiles => {
                  allUploadedFiles ++= uploadedFiles
                  rerenderer.rerender()
                },
                multiple = true
              )
          } else {
            h3.apply("Uploaded images:") ++
              allUploadedFiles.toList.flatMap(uploadedFile => {
                val bytes = IOUtils.toByteArray(uploadedFile.inputStream())
                List(
                  <img class="w-50 mb-1" src={s"data:${uploadedFile.contentType};base64, " + Base64.getEncoder.encodeToString(bytes)}></img>.mx_auto.d_block,
                  <pre class="mb-4 text-center">{uploadedFile.name}</pre>
                )
              }).mkNS
          }
        }
      }).render()
    }
    closeCodeSample()
  }
}
