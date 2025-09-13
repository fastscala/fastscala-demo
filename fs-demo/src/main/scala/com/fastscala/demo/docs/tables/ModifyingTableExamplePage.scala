package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.{CountriesData, Country}

class ModifyingTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Modifying Table HTML Example"

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    var buildTable: () => Table6Base = null

    renderCodeSampleAndAutoClosePreviousOne("Building the table") {
      buildTable = () =>
        new Table6Base with Table6BaseBootrapSupport with Table6StandardColumns with Table6SeqDataSource with Table6Paginated {
          override type R = Country

          val ColName = ColStr("Name", _.name.common)
          val ColCapital = ColStr("Capital", _.capital.mkString(", "))
          val ColRegion = ColStr("Region", _.region)
          val ColArea = ColStr("Area", _.area.toString, rows => s"Total Area: ${rows.map(_.area).sum}")

          override def columns(): List[C] = List(
            ColName,
            ColCapital,
            ColRegion,
            ColArea
          )

          override def tableFootEnabled: Boolean = true

          override def seqRowsSource: Seq[Country] = CountriesData.data.take(3)
        }
      buildTable().render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onTableWrapper method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableWrapper(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onTable method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTable(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onTableHead method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableHead(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onTableHeadTr method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableHeadTr(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableHeadTrThClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableHeadTrThClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onTableBodyClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableBodyClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableBodyTRClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyTRClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableBodyTRTDClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyTRTDClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableFootClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableFootClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableFootTRClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableFootTRClasses(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableFootTrThClasses method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableFootTrThClasses(_.border.border_danger.border_5).render()
    }
    closeCodeSample()
  }
}
