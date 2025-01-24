package com.fastscala.demo.docs.tables

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.components.bootstrap5.tables.*
import com.fastscala.demo.testdata.{CountriesData, Country}


class ModifyingTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Table example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    var buildTable: () => Table5Base = null

    renderCodeSampleAndAutoClosePreviousOne("Building the table") {
      buildTable = () => new Table5Base
        with Table5BaseBootrapSupport
        with Table5StandardColumns {
        override type R = Country

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

        override def rows(hints: Seq[RowsHint]): Seq[Country] = CountriesData.data.take(3)
      }
      buildTable().render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify table") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().border.border_danger.border_5.render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify table (alternative)") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTable(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Head Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableHead(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Head TR Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableHeadTRs(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Head TRTH Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableHeadTRTHClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Body Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Body TR Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyTRClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("Modify Table Body TRTD Classes") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyTRTDClasses(_.border.border_danger.border_5).render()
    }
    closeCodeSample()
  }
}
