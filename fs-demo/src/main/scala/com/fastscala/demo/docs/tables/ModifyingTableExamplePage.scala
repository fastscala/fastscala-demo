package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{CountriesData, Country}

class ModifyingTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Modifying Table HTML Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    var buildTable: () => Table6Base = null

    renderCodeSampleAndAutoClosePreviousOne("Building the table") {
      buildTable = () =>
        new Table6Base
          with Table6BaseBootrapSupport
          with Table6StandardColumns
          with Table6SeqDataSourceNoContext {
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
      buildTable().render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify table") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTable(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify table (alternative)") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTable(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Head Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableHead(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Head TR Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableHeadTr(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Head TRTH Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable()
        .onAllTableHeadTrThClasses(_.border.border_danger.border_5)
        .render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Body Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable()
        .onAllTableBodyClasses(_.border.border_danger.border_5)
        .render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Body TR Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable()
        .onAllTableBodyTRClasses(_.border.border_danger.border_5)
        .render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Body TRTD Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable()
        .onAllTableBodyTRTDClasses(_.border.border_danger.border_5)
        .render()
    }
    closeCodeSample()
  }
}
