package com.fastscala.demo.docs.tables

import com.fastscala.components.bootstrap5.form7.layout.F7BSFormInputGroup
import com.fastscala.components.bootstrap5.table6.*
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.select.F7SelectOptField
import com.fastscala.components.form7.fields.text.{F7IntOptField, F7StringField}
import com.fastscala.components.form7.fields.{F7CheckboxOptField, F7HtmlField, F7SubmitButtonField}
import com.fastscala.components.form7.{DefaultForm7, F7Field, QueryStringSavedForm}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.components.Widget
import com.fastscala.demo.testdata.{CountriesData, Country}
import com.fastscala.js.Js
import com.fastscala.scala_xml.js.JS

import scala.util.chaining.scalaUtilChainingOps
import scala.xml.{Elem, NodeSeq}

class FiltersSavedInQueryStringTableExamplePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Filters in Query String Example"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
    import com.fastscala.demo.docs.forms.DefaultFSDemoBSForm7Renderers.*

    renderCodeSampleAndAutoClosePreviousOne("Source") {

      val nameFilterField = new F7StringField().label("Name").id("name")
      val cCA2FilterField = new F7StringField().label("CCA2").id("cca2")
      val cCN3FilterField = new F7StringField().label("CCN3").id("ccn3")
      val cCA3FilterField = new F7StringField().label("CCA3").id("cca3")
      val cIOCFilterField = new F7StringField().label("CIOC").id("cioc")
      val statusFilterField = new F7SelectOptField[String]().optionsNonEmpty(CountriesData.data.map(_.status).distinct.sorted).option2String(_.getOrElse("All")).label("Status").id("status")
      val uNMemberFilterField = new F7CheckboxOptField().label("UN Member")
      val capitalFilterField = new F7StringField().label("Capital").id("capital")
      val altSpellingsFilterField = new F7StringField().label("AltSpellings").id("altspellings")
      val regionFilterField = new F7SelectOptField[String]().optionsNonEmpty(CountriesData.data.map(_.region).distinct.sorted).option2String(_.getOrElse("All")).label("Region").id("region")
      val subregionFilterField = new F7SelectOptField[String]().optionsNonEmpty(CountriesData.data.map(_.subregion).distinct.sorted).option2String(_.getOrElse("All")).label("Subregion").id("subregion")
      val latLngFilterField = new F7StringField().label("LatLng").id("latlng")
      val landlockedFilterField = new F7CheckboxOptField().label("Landlocked").id("landlocked")
      val bordersFilterField = new F7StringField().label("Borders").id("borders")
      val areaFilterMinField = new F7IntOptField().label("Area").id("area")
      val areaFilterMaxField = new F7IntOptField()
      val areaFilterField = new F7BSFormInputGroup()(F7HtmlField.label("from:"), areaFilterMinField, F7HtmlField.label("to:"), areaFilterMaxField)
      val callingCodesFilterField = new F7StringField().label("CallingCodes").id("callingcodes")

      import com.fastscala.demo.docs.forms.DefaultFSDemoBSForm7Renderers.*

      lazy val filtersForm = new DefaultForm7() with QueryStringSavedForm {

        override lazy val rootField: F7Field = {
          new F7VerticalField()(
            new F7ContainerField("row")("col" -> nameFilterField),
            new F7ContainerField("row")("col" -> cCA2FilterField, "col" -> cCN3FilterField, "col" -> cCA3FilterField, "col" -> cIOCFilterField),
            new F7ContainerField("row")("col-4" -> statusFilterField, "col-8" -> areaFilterField),
            new F7ContainerField("row")("col" -> regionFilterField, "col" -> subregionFilterField),
            new F7ContainerField("row")("col" -> capitalFilterField, "col" -> altSpellingsFilterField),
            new F7ContainerField("row")("col" -> uNMemberFilterField, "col" -> landlockedFilterField),
            new F7ContainerField("row")("col" -> latLngFilterField, "col" -> bordersFilterField, "col" -> callingCodesFilterField),
            new F7SubmitButtonField[BSBtn](implicit fsc => BSBtn().sm.addClass(w_100.d_block.getClassAttr), _.lbl("Filter").BtnOutlinePrimary, _.lbl("Filter").BtnPrimary, _.lbl("Error").BtnDanger)
          )
        }

        override def postSubmitForm()(implicit fsc: FSContext): Js = super.postSubmitForm() & mainTable.rerender()
      }

      lazy val mainTable: Table6Base & Table6ColumnSelection = new Table6Base with Table6BootrapStyling with Table6Paginated with Table6SeqSortableDataSource with Table6StandardColumns with Table6Sortable with Table6ColumnSelection {
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
          case ColName         => _.sortBy(_.name.common)
          case ColCCA2         => _.sortBy(_.cca2)
          case ColCCN3         => _.sortBy(_.ccn3)
          case ColCCA3         => _.sortBy(_.cca3)
          case ColCIOC         => _.sortBy(_.cioc)
          case ColStatus       => _.sortBy(_.status)
          case ColUNMember     => _.sortBy(_.unMember.map(_.toString).getOrElse("--"))
          case ColCapital      => _.sortBy(_.capital.mkString(", "))
          case ColAltSpellings => _.sortBy(_.altSpellings.mkString(", "))
          case ColRegion       => _.sortBy(_.region)
          case ColSubregion    => _.sortBy(_.subregion)
          case ColLatLng       => _.sortBy(_.latlng.map(_.mkString(";")).getOrElse("--"))
          case ColLandlocked   => _.sortBy(_.landlocked.map(_.toString).getOrElse("--"))
          case ColBorders      => _.sortBy(_.borders.mkString(", "))
          case ColArea         => _.sortBy(_.area.toString)
          case ColCallingCodes => _.sortBy(_.callingCodes.mkString(", "))
          case ColFlag         => _.sortBy(_.flag)
        }

        override def allColumns: List[C] = List(ColName, ColCCA2, ColCCN3, ColCCA3, ColCIOC, ColStatus, ColUNMember, ColCapital, ColAltSpellings, ColRegion, ColSubregion, ColLatLng, ColLandlocked, ColBorders, ColArea, ColCallingCodes, ColFlag)

        override val initiallyVisibleColumns: Set[Table6StandardColumn[Country]] = Set(ColName, ColUNMember, ColCapital, ColArea)

        override def seqRowsSource: Seq[Country] = CountriesData.data.filter(country => {
          Some(nameFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.name.common.toLowerCase.contains(str.toLowerCase)) &&
          Some(cCA2FilterField.getInternalValue()).filter(_.trim != "").forall(str => country.cca2.toLowerCase.contains(str.toLowerCase)) &&
          Some(cCN3FilterField.getInternalValue()).filter(_.trim != "").forall(str => country.ccn3.toLowerCase.contains(str.toLowerCase)) &&
          Some(cCA3FilterField.getInternalValue()).filter(_.trim != "").forall(str => country.cca3.toLowerCase.contains(str.toLowerCase)) &&
          Some(cIOCFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.cioc.toLowerCase.contains(str.toLowerCase)) &&
          statusFilterField.getInternalValue().forall(status => country.status == status) &&
          uNMemberFilterField.getInternalValue().forall(boolOpt => country.unMember.forall(_ == boolOpt)) &&
          Some(capitalFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.capital.exists(_.toLowerCase.contains(str.toLowerCase))) &&
          Some(altSpellingsFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.altSpellings.exists(_.toLowerCase.contains(str.toLowerCase))) &&
          regionFilterField.getInternalValue().forall(str => country.region.toLowerCase.contains(str.toLowerCase)) &&
          subregionFilterField.getInternalValue().forall(str => country.subregion.toLowerCase.contains(str.toLowerCase)) &&
          areaFilterMinField.getInternalValue().forall(min => country.area >= min) &&
          areaFilterMaxField.getInternalValue().forall(max => country.area >= max) &&
          Some(latLngFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.latlng.exists(_.toString.contains(str.toLowerCase))) &&
          landlockedFilterField.getInternalValue().forall(boolOpt => country.landlocked.forall(_ == boolOpt)) &&
          Some(bordersFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.borders.exists(_.toLowerCase.contains(str.toLowerCase))) &&
          Some(callingCodesFilterField.getInternalValue()).filter(_.trim != "").forall(str => country.callingCodes.exists(_.toLowerCase.contains(str.toLowerCase)))
        })
      }

      filtersForm.render() ++
        new Widget {
          override def widgetTitle: String = "Filtered Table"

          override def transformWidgetCardBody(elem: Elem): Elem = super.transformWidgetCardBody(elem).p_0

          override def widgetTopRight()(implicit fsc: FSContext): NodeSeq = mainTable.columnSelectionDropdown()

          override def widgetContents()(implicit fsc: FSContext): NodeSeq = mainTable.render()
        }.renderWidget()
    }
    closeCodeSample()
  }
}
