package com.fastscala.demo.docs.bootstrap

import com.fastscala.components.bootstrap5.modals.BSModal5Base
import com.fastscala.components.bootstrap5.offcanvas.{BSOffcanvas, BSOffcanvasBase}
import com.fastscala.components.bootstrap5.toast.BSToast2
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.scala_xml.js.printBeforeExec

import scala.xml.NodeSeq

class BootstrapToastPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Bootstrap Toast Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Basic") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Simple").onclick({
        BSToast2.Simple(<span>Import has finished!</span>) {
          <p>The import of file <b>sales_data_1988.xlsx</b> has now finished successfully.</p>
        }.positionTopRight
          // Show the toast bellow the top bar:
          .onToastContainer(_.withStyle("margin-top: 65px;"))
          .installAndShow()
      }).btn.w_100
    }
    renderCodeSampleAndAutoClosePreviousOne("Positions") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      val loremIpsum = <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus pellentesque imperdiet sapien, mollis molestie ante pulvinar eget.</p>
      row.apply {
        col_4.apply {
          BSBtn().BtnPrimary.lbl("Top-Left").callback(implicit fsc => {
            BSToast2.Simple(<span>Position Top-Left</span>)(loremIpsum).positionTopLeft.onToastContainer(_.withStyle("margin-top: 65px;")).installAndShow()
          }).btn.w_100.mb_2
        } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Top-Center").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Top-Center</span>)(loremIpsum).positionTopCenter.onToastContainer(_.withStyle("margin-top: 65px;")).installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Top-Right").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Top-Right</span>)(loremIpsum).positionTopRight.onToastContainer(_.withStyle("margin-top: 65px;")).installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Middle-Left").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Middle-Left</span>)(loremIpsum).positionMiddleLeft.installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Middle-Center").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Middle-Center</span>)(loremIpsum).positionMiddleCenter.installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Middle-Right").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Middle-Right</span>)(loremIpsum).positionMiddleRight.installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Bottom-Left").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Bottom-Left</span>)(loremIpsum).positionBottomLeft.installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Bottom-Center").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Bottom-Center</span>)(loremIpsum).positionBottomCenter.installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Bottom-Right").callback(implicit fsc => {
              BSToast2.Simple(<span>Position Bottom-Right</span>)(loremIpsum).positionBottomRight.installAndShow()
            }).btn.w_100.mb_2
          }
      }
    }
    renderCodeSampleAndAutoClosePreviousOne("Colors") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      row.apply {
        col_4.apply {
          BSBtn().BtnPrimary.lbl("Danger").onclick({
            BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
              .positionTopRight
              .onToastContainer(_.withStyle("margin-top: 65px;"))
              .onToast(_.text_bg_danger.border_0.p_2)
              .onToastHeaderCloseBtn(_.btn_close_white)
              .enableCloseBtn
              .installAndShow()
          }).btn.w_100.mb_2
        } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Dark").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_dark.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Info").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_info.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Light").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_light.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Primary").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_primary.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Secondary").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_secondary.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Success").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_success.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          } ++
          col_4.apply {
            BSBtn().BtnPrimary.lbl("Warning").onclick({
              BSToast2.Simple(<span>Primary Style</span>)(<span>Lorem ipsum</span>)
                .positionTopRight
                .onToastContainer(_.withStyle("margin-top: 65px;"))
                .onToast(_.text_bg_warning.border_0.p_2)
                .onToastHeaderCloseBtn(_.btn_close_white)
                .enableCloseBtn
                .installAndShow()
            }).btn.w_100.mb_2
          }
      }
    }
    closeCodeSample()
  }
}
