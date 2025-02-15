package com.fastscala.demo.docs.bootstrap

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page

class BootstrapImagesPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Bootstrap Images"

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  val image = img.withAttr("src" -> "/static/images/pexels-souvenirpixels-414612.jpg")

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Responsive") {
      image.img_fluid
    }
    renderCodeSampleAndAutoClosePreviousOne("Thumbnails") {
      text_center.apply {
        image.withStyle("max-width: 250px; max-height: 250px;").img_thumbnail
      }
    }
    renderCodeSampleAndAutoClosePreviousOne("Rounded") {
      text_center.apply {
        image.withStyle("max-width: 250px; max-height: 250px;").rounded
      }
    }
    closeCodeSample()
  }
}
