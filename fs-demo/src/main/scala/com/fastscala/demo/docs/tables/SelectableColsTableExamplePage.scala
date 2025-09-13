package com.fastscala.demo.docs.tables

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.components.Widget
import com.fastscala.js.Js
import com.fastscala.scala_xml.js.JS
import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem

import java.util.Date
import scala.xml.{Elem, NodeSeq}


class SelectableColsTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Selectable Column Table Example"

  override def codeSampleWrapperPadding: Boolean = false

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Source") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      lazy val mainTable = new Table6Base
        with Table6BaseBootrapSupport
        with Table6Paginated
        with Table6SeqSortableDataSource
        with Table6StandardColumns
        with Table6Sortable
        with Table6SelectableCols {
        override type R = Country

        override def defaultPageSize: Int = 10

        onTableWrapper(_.mb_5)

        override def tableHeadStyle: Option[Table6BootrapStyles.Value] = Some(Table6BootrapStyles.Primary)

        override def tableResponsive: Option[Table6BootrapResponsiveSizes.Value] = Some(Table6BootrapResponsiveSizes.ALL)

        val ColName = ColStr("Name", _.name.common)
        val ColCCA2 = ColStr("CCA2", _.cca2)
        val ColCCN3 = ColStr("CCN3", _.ccn3)
        val ColCCA3 = ColStr("CCA3", _.cca3)
        val ColCIOC = ColStr("CIOC", _.cioc)
        val ColStatus = ColStr("Status", _.status)
        val ColUNMember = ColStr("UN Member", _.unMember.map(_.toString).getOrElse("--"))
        val ColCapital = ColStr("Capital", _.capital.mkString(", "))
        val ColAltSpellings = ColStr("Alt Spellings", _.altSpellings.mkString(", "))
        val ColRegion = ColStr("Region", _.region)
        val ColSubregion = ColStr("Subregion", _.subregion)
        val ColLatLng = ColStr("LatLng", _.latlng.map(_.mkString(";")).getOrElse("--"))
        val ColLandlocked = ColStr("Landlocked", _.landlocked.map(_.toString).getOrElse("--"))
        val ColBorders = ColStr("Borders", _.borders.mkString(", "))
        val ColArea = ColStr("Area", _.area.toString)
        val ColCallingCodes = ColStr("Calling Codes", _.callingCodes.mkString(", "))
        val ColFlag = ColStr("Flag", _.flag)


        override def rowsSorter: PartialFunction[C, Seq[Country] => Seq[Country]] = {
          case ColName => _.sortBy(_.name.common)
          case ColCCA2 => _.sortBy(_.cca2)
          case ColCCN3 => _.sortBy(_.ccn3)
          case ColCCA3 => _.sortBy(_.cca3)
          case ColCIOC => _.sortBy(_.cioc)
          case ColStatus => _.sortBy(_.status)
          case ColUNMember => _.sortBy(_.unMember.map(_.toString).getOrElse("--"))
          case ColCapital => _.sortBy(_.capital.mkString(", "))
          case ColAltSpellings => _.sortBy(_.altSpellings.mkString(", "))
          case ColRegion => _.sortBy(_.region)
          case ColSubregion => _.sortBy(_.subregion)
          case ColLatLng => _.sortBy(_.latlng.map(_.mkString(";")).getOrElse("--"))
          case ColLandlocked => _.sortBy(_.landlocked.map(_.toString).getOrElse("--"))
          case ColBorders => _.sortBy(_.borders.mkString(", "))
          case ColArea => _.sortBy(_.area.toString)
          case ColCallingCodes => _.sortBy(_.callingCodes.mkString(", "))
          case ColFlag => _.sortBy(_.flag)
        }

        override def allColumns: List[C] = List(
          ColName
          , ColCCA2
          , ColCCN3
          , ColCCA3
          , ColCIOC
          , ColStatus
          , ColUNMember
          , ColCapital
          , ColAltSpellings
          , ColRegion
          , ColSubregion
          , ColLatLng
          , ColLandlocked
          , ColBorders
          , ColArea
          , ColCallingCodes
          , ColFlag
        )

        override def columnStartsVisible(c: C): Boolean = Set(
          ColName
          , ColUNMember
          , ColCapital
          , ColArea
        ).contains(c)

        override def seqRowsSource: Seq[Country] = CountriesData.data
      }

      new Widget {
        override def widgetTitle: String = "Selectable cols"

        override def transformWidgetCardBody(elem: Elem): Elem = super.transformWidgetCardBody(elem).p_0

        override def widgetTopRight()(implicit fsc: FSContext): NodeSeq = mainTable.colSelectionDropdown()

        override def widgetContents()(implicit fsc: FSContext): NodeSeq = mainTable.render()
      }.renderWidget()
    }
    closeCodeSample()
  }
}
