package com.fastscala.demo.docs.tables

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.components.Widget
import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.demo.testdata.{CountriesData, Country}

import scala.xml.{Elem, NodeSeq}


class SelectableRowsTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Selectable Rows Table Example"

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def codeSampleWrapperPadding: Boolean = false
  
  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      val table = new Table6Base
        with Table6BootrapStyling
        with Table6SelectableRows
        with Table6StandardColumns
        with Table6PaginatedWithSeqDataSource {
        override type R = Country

        override def defaultPageSize: Int = 10

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] = List(
          ColName
          , ColCapital
          , ColRegion
          , ColArea
          , ColSelectRow
        )

        override def seqRowsSource: Seq[Country] = CountriesData.data
      }

      new Widget {
        override def widgetTitle: String = "Selectable rows"

        override def transformWidgetCardBody(elem: Elem): Elem = super.transformWidgetCardBody(elem).p_0

        override def widgetTopRight()(implicit fsc: FSContext): NodeSeq = table.clearRowSelectionBtn.btn ++ table.selectAllVisibleRowsBtn.btn.ms_2

        override def widgetContents()(implicit fsc: FSContext): NodeSeq = table.render()
      }.renderWidget()
    }
    closeCodeSample()
  }
}
