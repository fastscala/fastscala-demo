package com.fastscala.demo.docs.js

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.js.JS
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem

import java.util.UUID

class JsUtilsPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Js utils"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

    renderCodeSampleAndAutoClosePreviousOne("Create Js") {
      button.btn.btn_success.apply("click me").addOnClick(JS("alert('clicked')"))
    }
    renderCodeSampleAndAutoClosePreviousOne("Escape string") {
      val string = """string with 'single' and "double" quotes"""
      button.btn.btn_success.apply("click me").addOnClick(JS(s"""alert("${JS.escapeStr(string)}")""")) ++
        button.ms_2.btn.btn_primary.apply("click me").addOnClick(JS(s"""alert('${JS.escapeStr(string)}')"""))
    }
    renderCodeSampleAndAutoClosePreviousOne("Element by id") {
      d_flex.justify_content_between.apply {
        input.withType("text").withId("id1").withValue("Lorem ipsum dolor sit amet").form_control ++
          button.ms_2.btn.btn_primary.apply("Get input elem").addOnClick(JS(s"""alert('Elem: '+${JS.elementById("id1")})""")).text_nowrap
      }
    }
    renderCodeSampleAndAutoClosePreviousOne("Element value by id") {
      d_flex.justify_content_between.apply {
        input.withType("text").withId("id2").withValue("All work and no play makes Jack a dull boy").form_control ++
          button.ms_2.btn.btn_primary.apply("Get input elem value").addOnClick(JS(s"""alert('Elem: '+${JS.elementValueById("id2")})""")).text_nowrap
      }
    }
    renderCodeSampleAndAutoClosePreviousOne("Copy to clipboard") {
      button.btn.btn_success.apply("Copy UUID").addOnClick(JS.copy2Clipboard(UUID.randomUUID().toString))
    }
    closeCodeSample()
  }
}
