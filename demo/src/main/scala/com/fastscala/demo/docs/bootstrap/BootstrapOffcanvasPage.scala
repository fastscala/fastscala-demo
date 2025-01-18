package com.fastscala.demo.docs.bootstrap

import com.fastscala.components.bootstrap5.modals.BSModal5Base
import com.fastscala.components.bootstrap5.offcanvas.{BSOffcanvas, BSOffcanvasBase}
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples2Page

import scala.xml.NodeSeq

class BootstrapOffcanvasPage extends MultipleCodeExamples2Page() {

  override def pageTitle: String = "Offcanvas Example"

  override def renderContentsWithSnippets()(implicit fsc: FSContext): Unit = {
    renderSnippet("Basic") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Open Offcanvas").ajax(implicit fsc =>
        BSOffcanvas.start("Sample offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").ajax(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").ajax(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").ajax(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").ajax(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").ajax(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    renderSnippet("Slide right") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Slide right offcanvas").ajax(implicit fsc =>
        BSOffcanvas.end("Slide right offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").ajax(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").ajax(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").ajax(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").ajax(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").ajax(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    renderSnippet("Slide top") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Slide top offcanvas").ajax(implicit fsc =>
        BSOffcanvas.top("Slide top offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").ajax(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").ajax(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").ajax(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").ajax(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").ajax(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    renderSnippet("Slide bottom") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      BSBtn().BtnPrimary.lbl("Slide bottom offcanvas").ajax(implicit fsc =>
        BSOffcanvas.bottom("Slide bottom offcanvas")(
          offcanvas => implicit fsc => p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed posuere nec nisl non blandit. Ut vel libero dictum, feugiat risus quis, placerat lorem. Nam eleifend egestas pulvinar. Vestibulum non viverra sapien, at hendrerit ex. Vestibulum tempor eu risus ut vestibulum. Nullam semper vitae ex quis vestibulum. Fusce posuere, purus non consequat scelerisque, nulla risus bibendum diam, finibus mollis ipsum ligula eu enim.") ++
            BSBtn().BtnSecondary.sm.lbl("Hide and remove").ajax(_ => offcanvas.hideAndRemoveAndDeleteContext()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("dispose").ajax(_ => offcanvas.dispose()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("hide").ajax(_ => offcanvas.hide()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("show").ajax(_ => offcanvas.show()).btn.me_2 ++
            BSBtn().BtnSecondary.sm.lbl("toggle").ajax(_ => offcanvas.toggle()).btn
        )
      ).btn.w_100
    }
    closeSnippet()
  }
}
