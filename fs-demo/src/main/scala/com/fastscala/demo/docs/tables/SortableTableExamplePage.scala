package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.components.form7.fields.text.F7StringField
import com.fastscala.components.form7.{DefaultForm7, F7Field}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.scala_xml.js.JS

class SortableTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Sortable Table Example"

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
    import com.fastscala.demo.docs.forms.DefaultFSDemoBSForm7Renderers.*
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      new Table6Base with Table6BootrapStyling with Table6StandardColumns with Table6SortableWithSeqDataSource with Table6Sortable {
        override type R = Country

        val ColName = ColStr("Name", _.name.common)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] = List(ColName, ColCapital, ColRegion, ColArea)

        override def rowsSorter: PartialFunction[C, Seq[Country] => Seq[Country]] = {
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
