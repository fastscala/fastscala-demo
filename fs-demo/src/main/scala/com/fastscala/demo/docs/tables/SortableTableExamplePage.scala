package com.fastscala.demo.docs.tables

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.components.bootstrap5.tables.*
import com.fastscala.components.form7.{DefaultForm7, F7Field}
import com.fastscala.components.form7.fields.text.F7StringField
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.scala_xml.js.JS

class SortableTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Sortable Table Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    import com.fastscala.demo.docs.forms.DefaultFSDemoBSForm7Renderers.*
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      var countryNameFilter = ""
      lazy val filterF: F7StringField = new F7StringField().label("Country name contains")
        // Option A: filter on return:
        //        .rw(countryNameFilter, v => countryNameFilter = v)
        //        .addOnThisFieldChanged(field => {
        //          table.rerenderTableAround()
        //        })
        // Option B:
        .additionalAttrs(() => Seq(
          "oninput" -> fsc.callback(JS("this.value"), value => {
            countryNameFilter = value
            table.rerenderTableAround()
          }).cmd
        ))
      lazy val table = new Table5Base
        with Table5BaseBootrapSupport
        with Table5StandardColumns
        with Table5SeqSortableDataSource
        with Table5Sortable {
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

        override def rowsSorter: PartialFunction[Table5StandardColumn[Country], Seq[Country] => Seq[Country]] = {
          case ColName => _.sortBy(_.name.common)
          case ColCapital => _.sortBy(_.capital.mkString(", "))
          case ColRegion => _.sortBy(_.region)
          case ColArea => _.sortBy(_.area)
        }

        override def seqRowsSource: Seq[Country] = CountriesData.data.filter(_.name.common.toLowerCase.contains(countryNameFilter.toLowerCase))
      }
      new DefaultForm7() with com.fastscala.components.form7.Form7WithSaveOnEnterHint {
        override def rootField: F7Field = filterF
      }.render().mb_2 ++
        table.render()
    }
    closeCodeSample()
  }
}
