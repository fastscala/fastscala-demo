package com.fastscala.demo.docs.tables

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.demo.testdata.{CountriesData, Country}


class PaginatedTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Paginated Table Example"

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      new Table6Base
        with Table6BootrapStyling
        with Table6StandardColumns
        with Table6PaginatedWithSeqDataSource {

        override type R = Country

        override def defaultPageSize = 10

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] = List(
          ColName
          , ColCapital
          , ColRegion
          , ColArea
        )

        override def seqRowsSource: Seq[Country] = CountriesData.data
      }.render()
    }
    closeCodeSample()
  }
}
