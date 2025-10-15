package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.core.{FSContext, FSSessionVar}
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.components.Widget
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.js.Js

import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, NodeSeq, Unparsed}

// === code sample ===
object CountriesDataSorted extends FSSessionVar[ListBuffer[Country]](ListBuffer[Country](CountriesData.data*))
// === code sample ===

class SortableRowsTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Sortable Rows Table Example"

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  // === code sample ===
  override def append2Head(): NodeSeq = super.append2Head() ++
    Unparsed {
      """<style>
        |.sorting-handle {
        |  opacity: 0;
        |}
        |tr:hover > td > span > .sorting-handle {
        |  opacity: 1;
        |}
        |</style>
        |""".stripMargin
    }

  override def append2Body(): NodeSeq = super.append2Body() ++
    <script src="//code.jquery.com/ui/1.14.1/jquery-ui.min.js" integrity="sha256-AlTido85uXPlSyyaZNsjJXeCs07eSv3r43kyCVc8ChI=" crossorigin="anonymous"></script>
  // === code sample ===

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      val table = new Table6Base with Table6BootrapStyling with Table6StandardColumns with Table6SortableRows {
        override type R = Country

        override def sortableRowsHandle: Option[String] = Some(".sorting-handle")

        override def sortedRow(row: Country, newIdx: Int): Js = super.sortedRow(row, newIdx) & {
          println(s"sortedRow($row: Country, $newIdx: Int)")
          CountriesDataSorted().remove(CountriesDataSorted().indexOf(row))
          CountriesDataSorted().insert(newIdx, row)
          Js.Void
        }

        val ColName = ColNs("Name", implicit fsc => row => <span><i class="bi bi-three-dots-vertical sorting-handle p-0"></i> {row.name.common}</span>)
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColArea = ColStr("Area", _.area.toString)

        override def columns(): List[C] = List(ColName, ColCapital, ColRegion, ColArea)

        override def rows(hints: Seq[RowsHint]): Seq[Country] = CountriesDataSorted().toSeq
      }

      new Widget {
        override def widgetTitle: String = "Selectable rows"

        override def transformWidgetCardBody(elem: Elem): Elem = super.transformWidgetCardBody(elem).p_0

        override def widgetContents()(implicit fsc: FSContext): NodeSeq = table.render()
      }.renderWidget()
    }
    closeCodeSample()
  }
}
