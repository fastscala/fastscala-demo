package com.fastscala.demo.docs.tables

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.components.bootstrap5.tables.*
import com.fastscala.demo.testdata.{CountriesData, Country}


class SimpleTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Table example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      new Table5Base
        with Table5BaseBootrapSupport
        with Table5StandardColumns {
        override type R = Country

        override def tableStriped: Boolean = true


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

        override def rows(hints: Seq[RowsHint]): Seq[Country] = CountriesData.data
      }.render()
    }
    closeCodeSample()
  }
}
