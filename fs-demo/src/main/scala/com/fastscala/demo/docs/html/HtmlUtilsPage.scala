package com.fastscala.demo.docs.html

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.js.JS
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem

import scala.xml.NodeSeq

class HtmlUtilsPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "HTML Basics"

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def pageLead(implicit fsc: FSContext): NodeSeq =
    <p>
      Remember you need the import: {span.apply("import com.fastscala.components.bootstrap5.helpers.BSHelpers.").text_bg_primary.d_inline}
    </p>

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Adding a class") {
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").withClass("text-bg-success")
    }
    renderCodeSampleAndAutoClosePreviousOne("Adding a style") {
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").withStyle("color: green;")
    }
    renderCodeSampleAndAutoClosePreviousOne("Adding an onclick handler") {
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").addOnClick(JS.alert("Clicked"))
    }
    renderCodeSampleAndAutoClosePreviousOne("Adding a class conditionally") {
      val value = math.random() - 0.5
      span.apply(f"$value%.2f").withClassIf(value < 0, "text-bg-danger")
    }
    renderCodeSampleAndAutoClosePreviousOne("Adding an attribute conditionally") {
      val value = math.random() - 0.5
      span.apply(f"$value%.2f").withAttrIf(value < 0, "style" -> "color: red;")
    }
    renderCodeSampleAndAutoClosePreviousOne("Setting type") {
      input.withType("color")
    }
    renderCodeSampleAndAutoClosePreviousOne("Setting title") {
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").withTitle("Title")
    }
    renderCodeSampleAndAutoClosePreviousOne("Setting attribute") {
      input.withType("text").withAttr("placeholder" -> "First name")
    }
    renderCodeSampleAndAutoClosePreviousOne("Setting id") {
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").withId("main_label")
    }
    renderCodeSampleAndAutoClosePreviousOne("Setting id if not set already") {
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.").withIdIfNotSet("main_label")
    }
    renderCodeSampleAndAutoClosePreviousOne("Setting href") {
      a.apply("open google").withHref("https://www.google.com")
    }
    renderCodeSampleAndAutoClosePreviousOne("Prepend/Append to contents") {
      p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        .withPrependedToContents(badge.bg_success.apply("prepended"))
        .withAppendedToContents(badge.bg_info.apply("appended"))
    }
    closeCodeSample()
  }
}
