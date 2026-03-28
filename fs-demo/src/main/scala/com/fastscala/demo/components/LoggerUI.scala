package com.fastscala.demo.components

import com.fastscala.components.bootstrap5.modals.{BSModal5Base, BSModal5Size}
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.JS
import com.fastscala.utils.IdGen

import scala.xml.NodeSeq

trait LoggerUI {

  def title: String

  def log(mesg: String): Unit

  def info(mesg: String): Unit

  def success(mesg: String): Unit

  def danger(mesg: String): Unit

  def warn(mesg: String): Unit

  def debug(mesg: String): Unit

  def continue_?(): Boolean

  def finished(): Unit

  var total: Option[Double]

  def incrementProgress(): Unit

  def incrementProgress(by: Double): Unit
}

class LoggerUISysoutOnly(val title: String) extends LoggerUI {

  override def log(mesg: String): Unit = println(mesg)

  override def info(mesg: String): Unit = println(mesg)

  override def success(mesg: String): Unit = println(mesg)

  override def danger(mesg: String): Unit = println(mesg)

  override def warn(mesg: String): Unit = println(mesg)

  override def debug(mesg: String): Unit = println(mesg)

  def continue_?(): Boolean = true

  def finished(): Unit = JS.void

  def incrementProgress(): Unit = ()

  def incrementProgress(by: Double): Unit = ()

  var total: Option[Double] = None
}

class LoggerUIImpl(val title: String, var total: Option[Double] = None)(implicit fsc: FSContext) extends LoggerUI {

  private var current = 0d

  private val progressBarRerenderer = JS.rerenderableP[Double](_ => implicit fsc => percent => {
    <div class="progress mb-2" role="progressbar" aria-valuenow={percent.toInt.toString} aria-valuemin="0" aria-valuemax="100">
      <div class={"progress-bar" + (if (!hasFinished) " progress-bar-striped progress-bar-animated" else "")} style={s"width: ${percent.toInt}%"}></div>
    </div>
  })

  override def incrementProgress(): Unit = incrementProgress(1)

  def incrementProgress(by: Double): Unit = total.foreach { total =>
    current += by
    if (current == total) {
      hasFinished = true
      fsc.sendToPage(modal.rerenderModalFooterContent() & progressBarRerenderer.rerender(100))
    } else {
      fsc.sendToPage(progressBarRerenderer.rerender((current / total) * 100d))
    }
  }

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  val loggerOutputId = IdGen.id
  private var continue: Boolean = true
  private var hasFinished: Boolean = false

  def log(mesg: String): Unit = fsc.sendToPage(JS.prepend2(loggerOutputId, <p style="white-space: pre-wrap; margin: 0;">{mesg}</p>))

  def info(mesg: String): Unit = fsc.sendToPage(JS.prepend2(loggerOutputId, <p style="white-space: pre-wrap; margin: 0;" class="text-primary">{mesg}</p>))

  def success(mesg: String): Unit = fsc.sendToPage(JS.prepend2(loggerOutputId, <p style="white-space: pre-wrap; margin: 0;" class="text-success">{mesg}</p>))

  def danger(mesg: String): Unit = fsc.sendToPage(JS.prepend2(loggerOutputId, <p style="white-space: pre-wrap; margin: 0;" class="text-danger">{mesg}</p>))

  def warn(mesg: String): Unit = fsc.sendToPage(JS.prepend2(loggerOutputId, <p style="white-space: pre-wrap; margin: 0;" class="text-warning">{mesg}</p>))

  def debug(mesg: String): Unit = fsc.sendToPage(JS.prepend2(loggerOutputId, <p style="white-space: pre-wrap; margin: 0;" class="text-black-50">{mesg}</p>))

  override def continue_?(): Boolean = continue

  private val modal = new BSModal5Base {
    override def modalHeaderTitle: String = title

    override def modalSize: BSModal5Size.Value = BSModal5Size.Large

    override def modalBodyContents()(implicit fsc: FSContext): NodeSeq = {
      total.map(_ => {
        progressBarRerenderer.render(0d)
      }).getOrElse(NodeSeq.Empty) ++
        div.withId(loggerOutputId).withStyle("min-height: 400px; background: #f5f5f5; max-height: 800px; overflow: auto;").apply {
          ""
        }
    }

    override def modalFooterContents()(implicit fsc: FSContext): Option[NodeSeq] = Some {
      if (hasFinished) {
        BSBtn().BtnSecondary.lbl("Close").onclick(hideAndRemoveAndDeleteContext()).btn
      } else {
        val btn: BSBtn = BSBtn().BtnDark.lbl("Stop").withRandomId
        btn.callback(implicit fsc => {
          continue = false
          btn.disable()
        }).btn
      }
    }
  }

  def openProgressModal(): Js = fsc.page.initWebSocket() & modal.installAndShow()

  override def finished(): Unit = {
    hasFinished = true
    fsc.sendToPage(modal.rerenderModalFooterContent() & progressBarRerenderer.rerender(100))
  }
}

object LoggerUI {

  implicit val Default: com.fastscala.demo.components.LoggerUISysoutOnly = new LoggerUISysoutOnly("Default")

  def runInSeparateThreadAndOpenProgressModal(title: String, totalProgress: Option[Double] = None)(code: LoggerUI => Unit)(implicit fsc: FSContext): Js = {
    val loggerUI = new LoggerUIImpl(title)
    loggerUI.total = totalProgress
    new Thread() {
      override def run(): Unit = code(loggerUI)
    }.start()
    loggerUI.openProgressModal()
  }
}