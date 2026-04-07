package com.fastscala.demo.docs.fastscala

import com.fastscala.components.bootstrap5.utils.{BSBtn, FileUploadHiddenTargetForm}
import com.fastscala.core.{FSContext, FSSessionVarOpt, FSUploadedFile}
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.JS

import java.io.OutputStreamWriter
import java.util.Base64

// === code sample ===
object FileDownloadPageUploadedImage extends FSSessionVarOpt[FSUploadedFile]()
// === code sample ===

class FileDownloadPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "File Download"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Download as byte array") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      FileDownloadPageUploadedImage.clear()
      JS.rerenderable(rerenderer => implicit fsc => {
        div.apply {
          FileDownloadPageUploadedImage() match {
            case Some(uploadedFile) =>

              val fileDownloadUrl = fsc.fileDownloadByteArray(uploadedFile.name.replaceAll(".*\\.(\\w+)$", "uploaded.$1"), uploadedFile.contentType, () => uploadedFile.bytes())

              h3.apply("Uploaded image:") ++
                <img class="w-100" src={s"data:${uploadedFile.contentType};base64, " + Base64.getEncoder.encodeToString(uploadedFile.bytes())}></img>.mx_auto.my_4.d_block ++
                BSBtn().BtnPrimary.lbl("Download Uploaded File").href(fileDownloadUrl).btnLink.d_block
            case None =>
              h3.apply("Upload an image:") ++
                FileUploadHiddenTargetForm(
                  uploadedFile => {
                    FileDownloadPageUploadedImage() = uploadedFile.head
                    rerenderer.rerender()
                  })
          }
        }
      }).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Stream to output stream") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      val fileDownloadUrl = fsc.fileDownloadStreaming("numbers.txt", "text/plain", os => {
        val osw = new OutputStreamWriter(os)
        (1 to 7).foreach(i => {
          osw.write(i + "\n")
          osw.flush()
          Thread.sleep(1000)
        })
        osw.close()
      })
      BSBtn().BtnPrimary.lbl("Download numbers.txt").href(fileDownloadUrl).btnLink.d_block
    }
    closeCodeSample()
  }
}
