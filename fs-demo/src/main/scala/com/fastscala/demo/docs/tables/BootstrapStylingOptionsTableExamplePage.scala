package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{CountriesData, Country}

class BootstrapStylingOptionsTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Bootstrap Styling Table Example"

  override def codeSampleWrapperPadding: Boolean = false

  // === code sample ===
  class SampleTable extends Table6Base with Table6BootrapStyling with Table6StandardColumns with Table6SeqDataSource with Table6Paginated {
    override type R = Country

    val ColName = ColStr("Name", _.name.common)
    val ColCapital = ColStr("Capital", _.capital.mkString(", "))
    val ColRegion = ColStr("Region", _.region)
    val ColArea = ColStr("Area", _.area.toString)

    override def columns(): List[C] = List(
      ColName,
      ColCapital,
      ColRegion,
      ColArea
    )

    override def seqRowsSource: Seq[Country] = CountriesData.data.take(3)
  }
  // === code sample ===

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Striped rows") {
      new SampleTable() {
        override def tableStriped: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Striped columns") {
      new SampleTable() {
        override def tableStripedColumns: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Dark") {
      new SampleTable() {
        override def tableDark: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Dark & Stripped") {
      new SampleTable() {
        override def tableDark: Boolean = true

        override def tableStriped: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Dark & Stripped Columns") {
      new SampleTable() {
        override def tableDark: Boolean = true

        override def tableStripedColumns: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Hoverable rows") {
      new SampleTable() {
        override def tableHoverable: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Hoverable rows & Stripped") {
      new SampleTable() {
        override def tableHoverable: Boolean = true
        override def tableStriped: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Bordered tables") {
      new SampleTable() {
        override def tableBordered: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Borderless") {
      new SampleTable() {
        override def tableBorderless: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Small tables") {
      new SampleTable() {
        override def tableSmall: Boolean = true
      }.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Table group dividers") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      new SampleTable().onTableBody(_.table_group_divider).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Table header styling") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      new SampleTable().onTableHead(_.table_light).render()
    }
    closeCodeSample()
  }
}
