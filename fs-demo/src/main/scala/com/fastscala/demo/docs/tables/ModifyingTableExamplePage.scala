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
        new Table6Base with Table6BootrapStyling with Table6StandardColumns with Table6SeqDataSource with Table6Paginated {
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
    renderCodeSampleAndAutoClosePreviousOne("onAllTableHeadTrTh method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableHeadTrTh(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onTableBody method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onTableBody(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableBodyTR method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyTR(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableBodyTRTD method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableBodyTRTD(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableFoot method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableFoot(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableFootTR method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableFootTR(_.border.border_danger.border_5).render()
    }
    renderCodeSampleAndAutoClosePreviousOne("onAllTableFootTrTh method") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      buildTable().onAllTableFootTrTh(_.border.border_danger.border_5).render()
    }
    closeCodeSample()
  }
}
