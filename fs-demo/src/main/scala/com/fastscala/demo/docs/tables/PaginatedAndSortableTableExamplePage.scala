package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.JS

import java.util.Date

class PaginatedAndSortableTableExamplePage() extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Paginated and Sortable Table Example"

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      new Table6Base with Table6BootrapStyling with Table6PaginatedWithSeqDataSource with Table6StandardColumns with Table6SortableWithSeqDataSource with Table6Sortable {
        override type R = Country

        override def defaultPageSize: Int = 10

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] = List(ColName, ColCapital, ColRegion, ColArea)

        override def rowsSorter: PartialFunction[C, Seq[R] => Seq[R]] = {
          case ColName    => _.sortBy(_.name.common)
          case ColCapital => _.sortBy(_.capital.mkString(", "))
          case ColRegion  => _.sortBy(_.region)
          case ColArea    => _.sortBy(_.area)
        }

        override def seqRowsSource: Seq[Country] = CountriesData.data
      }.render()
    }
    closeCodeSample()
  }
}
