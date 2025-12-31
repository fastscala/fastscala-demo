package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.components.Widget
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.scala_xml.js.JS

import scala.xml.{Elem, NodeSeq}

class SelectableRowsWithActionsTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Selectable Rows With Actions Table Example"

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      val table = new Table6Base with Table6BootrapStyling with Table6StandardColumns with Table6SelectableRowsWithActions with Table6SelectableRows with Table6Paginated {
        override type R = Country

        override def defaultPageSize: Int = 10

        override def actionsBtnToIncludeInTopDropdown: BSBtn = BSBtn().BtnPrimary.lbl("Actions").sm.addClass(ms_2.getClassAttr)

        override def actionsForRows(rows: Seq[Country]): Seq[BSBtn] = List(
          Some(()).filter(_ => rows.size >= 2).map(_ =>
            BSBtn().lbl("Calculate total area").callback(implicit fsc => JS.alert(s"Total area of ${rows.map(_.name.common).mkString(", ")}: ${rows.map(_.area).sum}"))
          ),
          Some(
            BSBtn().lbl("Download as TSV").callback(implicit fsc =>
              JS.redirectTo(fsc.fileDownloadByteArray(
                "countries.tsv",
                "text/tab-separated-values",
                () => {
                  ("Name\tCapital\tRegion\tArea" ::
                    rows.toList.sortBy(_.name.common)
                      .map(c => s"${c.name.common}\t${c.capital.mkString(", ")}\t${c.region}\t${c.area}"))
                    .mkString("\n").getBytes("UTF-8")
                }
              ))
            )
          )
        ).flatten

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] = List(
          ColRegion,
          ColName,
          ColCapital,
          ColArea,
          ColActionsDefault,
          ColSelectRow
        )

        override def seqRowsSource: Seq[Country] = CountriesData.data
      }

      new Widget {
        override def widgetTitle: String = "Selectable rows"

        override def transformWidgetCardBody(elem: Elem): Elem = super.transformWidgetCardBody(elem).p_0

        override def widgetTopRight()(implicit fsc: FSContext): NodeSeq =
          table.selectAllVisibleRowsBtn.sm.btn.ms_2 ++
            table.clearRowSelectionBtn.sm.btn.ms_2 ++
            table.actionsDropdownBtnRenderer.render()

        override def widgetContents()(implicit fsc: FSContext): NodeSeq = table.render()
      }.renderWidget()
    }
    closeCodeSample()
  }
}
