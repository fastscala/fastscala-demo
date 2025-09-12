package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{CountriesData, Country}

class OtherFeaturesTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Very Simple Table Example"

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Striped rows") {
      new Table6Base with Table6BaseBootrapSupport with Table6StandardColumns with Table6SeqDataSourceNoContext {
        override type R = Country

        override def tableStriped: Boolean = true

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] =
          List(ColName, ColCapital, ColRegion, ColArea)

        override def seqRowsSource: Seq[Country] = CountriesData.data.take(10)
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Striped columns") {
      new Table6Base with Table6BaseBootrapSupport with Table6StandardColumns with Table6SeqDataSourceNoContext {
        override type R = Country

        override def tableStripedColumns: Boolean = true

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] =
          List(ColName, ColCapital, ColRegion, ColArea)

        override def seqRowsSource: Seq[Country] = CountriesData.data.take(10)
      }.render()
    }
    closeCodeSample()
  }
}
