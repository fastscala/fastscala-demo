package com.fastscala.demo.docs.bootstrap

import com.fastscala.components.bootstrap5.modals.BSModal5Base
import com.fastscala.components.bootstrap5.offcanvas.{BSOffcanvas, BSOffcanvasBase}
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page

import scala.xml.NodeSeq

class BootstrapOffcanvasPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Offcanvas Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Basic") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Open Offcanvas").callback(implicit fsc =>
        BSOffcanvas.start("Sample offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").callback(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").callback(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").callback(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").callback(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").callback(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    renderCodeSampleAndAutoClosePreviousOne("Slide right") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Slide right offcanvas").callback(implicit fsc =>
        BSOffcanvas.end("Slide right offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").callback(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").callback(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").callback(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").callback(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").callback(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    renderCodeSampleAndAutoClosePreviousOne("Slide top") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Slide top offcanvas").callback(implicit fsc =>
        BSOffcanvas.top("Slide top offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").callback(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").callback(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").callback(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").callback(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").callback(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    renderCodeSampleAndAutoClosePreviousOne("Slide bottom") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Slide bottom offcanvas").callback(implicit fsc =>
        BSOffcanvas.bottom("Slide bottom offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").callback(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").callback(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").callback(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").callback(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").callback(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    closeCodeSample()
  }
}
