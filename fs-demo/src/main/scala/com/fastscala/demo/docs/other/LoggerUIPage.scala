package com.fastscala.demo.docs.other

import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.chartjs.*
import com.fastscala.components.chartjs.ChartJsNullable2Option.*
import com.fastscala.core.FSContext
import com.fastscala.demo.components.LoggerUI
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.JS

import scala.xml.NodeSeq

class LoggerUIPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "LoggerUI Util"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
    renderCodeSampleAndAutoClosePreviousOne("Using LoggerUI", <p>Small utility to easily show the status/progress of some server activity to the user.</p>) {
      BSBtn().BtnPrimary.lbl("Start task").callback(implicit fsc => {
        LoggerUI.runInSeparateThreadAndOpenProgressModal("Long running task") { logger =>
          logger.info("Starting a long running task...")
          Thread.sleep(500)
          logger.warn("Trying something which may fail...")
          Thread.sleep(500)
          logger.danger("Ups, it inded failed")
          Thread.sleep(500)
          logger.debug("Retrying...")
          Thread.sleep(500)
          logger.info("Great, now it succeeded!")
          Thread.sleep(500)
          logger.debug("Cleaning up a couple of things...")
          Thread.sleep(500)
          logger.success("Finished successfully!")
          logger.finished()
        }
      }).btn.w_100
    }
    renderCodeSampleAndAutoClosePreviousOne("With progres") {
      BSBtn().BtnPrimary.lbl("Start task").callback(implicit fsc => {
        LoggerUI.runInSeparateThreadAndOpenProgressModal("Long running task with 7 steps", Some(7)) { logger =>
          logger.info("Starting a long running task...")
          logger.incrementProgress()
          Thread.sleep(500)
          logger.warn("Trying something which may fail...")
          logger.incrementProgress()
          Thread.sleep(500)
          logger.danger("Ups, it inded failed")
          logger.incrementProgress()
          Thread.sleep(500)
          logger.debug("Retrying...")
          logger.incrementProgress()
          Thread.sleep(500)
          logger.info("Great, now it succeeded!")
          logger.incrementProgress()
          Thread.sleep(500)
          logger.debug("Cleaning up a couple of things...")
          logger.incrementProgress()
          Thread.sleep(500)
          logger.success("Finished successfully!")
          logger.incrementProgress()
        }
      }).btn.w_100
    }
    closeCodeSample()
  }
}
