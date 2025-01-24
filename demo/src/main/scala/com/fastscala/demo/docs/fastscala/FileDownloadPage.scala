package com.fastscala.demo.docs.fastscala

import com.fastscala.core.{FSContext, FSSessionVarOpt, FSUploadedFile}
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.js.JS
import com.fastscala.components.bootstrap5.utils.{BSBtn, FileUpload}
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem

import java.util.Base64

// === code sample ===
object FileDownloadPageUploadedImage extends FSSessionVarOpt[FSUploadedFile]()
// === code sample ===

class FileDownloadPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "File Download"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      FileDownloadPageUploadedImage.clear()
      JS.rerenderable(rerenderer => implicit fsc => {
        div.border.p_2.rounded.apply {
          FileDownloadPageUploadedImage() match {
            case Some(uploadedFile) =>

              val fileDownloadUrl = fsc.fileDownload(uploadedFile.submittedFileName.replaceAll(".*\\.(\\w+)$", "uploaded.$1"), uploadedFile.contentType, () => uploadedFile.content)

              h3.apply("Uploaded image:") ++
                <img class="w-100" src={s"data:${uploadedFile.contentType};base64, " + Base64.getEncoder.encodeToString(uploadedFile.content)}></img>.mx_auto.my_4.d_block ++
                BSBtn().BtnPrimary.lbl("Download Uploaded File").href(fileDownloadUrl).btnLink.d_block
            case None =>
              h3.apply("Upload an image:") ++
                FileUpload(
                  uploadedFile => {
                    FileDownloadPageUploadedImage() = uploadedFile.head
                    rerenderer.rerender()
                  })
          }
        }
      }).render()
    }
    closeCodeSample()
  }
}
