package com.fastscala.demo.docs.other

import com.fastscala.components.aceeditor.*
import com.fastscala.components.chartjs.*
import com.fastscala.components.chartjs.ChartJsNullable2Option.nullable2Option
import com.fastscala.components.form7.fields.layout.{F7ContainerField, F7VerticalField}
import com.fastscala.components.form7.fields.select.F7SelectOptField
import com.fastscala.components.form7.fields.text.F7IntOptField
import com.fastscala.components.form7.fields.{F7CheckboxField, F7CheckboxOptField, F7EnumField}
import com.fastscala.components.form7.{ChangedField, DefaultForm7, F7Field}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.docs.components.Widget
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, inScriptTag, printBeforeExec}

import scala.xml.{Elem, NodeSeq}

class AceEditorPage extends MultipleCodeExamples3Page() {

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def pageTitle: String = "AceEditor integration"

  override def append2Body(): NodeSeq =
    super.append2Body() ++
      AceEditor.jsImports(Language.scala, Theme.textmate)

  override def append2Head(): NodeSeq = super.append2Head() ++ AceEditor.cssImports

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOneStrDesc("AceEditor", "Note: this integration is still at an early state.") {

      val aceEditor = new AceEditor() {

        override def defaultMode: Language.Value = Language.scala

        override def defaultTheme: Theme.Value = Theme.solarized_dark

        override def initalValue: String =
          """object Hello {
            |    def main(args: Array[String]) = {
            |        println("Hello, world")
            |    }
            |}""".stripMargin
      }

      val editorOptions = new Widget {
        override def widgetTitle: String = "Options"

        override def widgetContents()(implicit fsc: FSContext): NodeSeq = {
          import com.fastscala.demo.docs.forms.DefaultFSDemoBSForm7Renderers.*
          new DefaultForm7() {

            override val rootField: F7Field = new F7VerticalField()(
              new F7ContainerField("row")(
                "col-4" -> new F7CheckboxOptField().label("IndentedSoftWrap").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setIndentedSoftWrap(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("UseWorker").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setUseWorker(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("UseSoftTabs").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setUseSoftTabs(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("NavigateWithinSoftTabs").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setNavigateWithinSoftTabs(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("Overwrite").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setOverwrite(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("AnimatedScroll").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setAnimatedScroll(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ShowInvisibles").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setShowInvisibles(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ShowPrintMargin").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setShowPrintMargin(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ShowGutter").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setShowGutter(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("FadeFoldWidgets").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setFadeFoldWidgets(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ShowFoldWidgets").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setShowFoldWidgets(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ShowLineNumbers").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setShowLineNumbers(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("DisplayIndentGuides").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setDisplayIndentGuides(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("HighlightIndentGuides").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setHighlightIndentGuides(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("HighlightGutterLine").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setHighlightGutterLine(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("HScrollBarAlwaysVisible").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setHScrollBarAlwaysVisible(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("VScrollBarAlwaysVisible").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setVScrollBarAlwaysVisible(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("FixedWidthGutter").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setFixedWidthGutter(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("CustomScrollbar").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setCustomScrollbar(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("HasCssTransforms").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setHasCssTransforms(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("UseSvgGutterIcons").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setUseSvgGutterIcons(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ShowFoldedAnnotations").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setShowFoldedAnnotations(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("UseResizeObserver").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setUseResizeObserver(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("DragEnabled").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setDragEnabled(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("TooltipFollowsMouse").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setTooltipFollowsMouse(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("HighlightActiveLine").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setHighlightActiveLine(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("HighlightSelectedWord").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setHighlightSelectedWord(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("ReadOnly").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setReadOnly(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("CopyWithEmptySelection").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setCopyWithEmptySelection(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("BehavioursEnabled").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setBehavioursEnabled(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("WrapBehavioursEnabled").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setWrapBehavioursEnabled(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableAutoIndent").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableAutoIndent(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableBasicAutocompletion").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableBasicAutocompletion(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableLiveAutocompletion").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableLiveAutocompletion(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableSnippets").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableSnippets(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("AutoScrollEditorIntoView").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setAutoScrollEditorIntoView(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("RelativeLineNumbers").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setRelativeLineNumbers(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableMultiselect").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableMultiselect(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableKeyboardAccessibility").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableKeyboardAccessibility(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableCodeLens").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableCodeLens(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
                "col-4" -> new F7CheckboxOptField().label("EnableMobileMenu").addOnThisFieldChanged(_.currentValue.map(bool => aceEditor.setEnableMobileMenu(bool)).getOrElse(JS.void)).disableSwitchingToUndefined,
              ),
              new F7ContainerField("row")(
                "col-4" -> new F7IntOptField().label("FirstLineNumber").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setFirstLineNumber(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("TabSize").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setTabSize(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("PrintMarginColumn").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setPrintMarginColumn(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("MaxLines").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setMaxLines(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("MinLines").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setMinLines(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("ScrollPastEnd").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setScrollPastEnd(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("MaxPixelHeight").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setMaxPixelHeight(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("ScrollSpeed").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setScrollSpeed(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("DragDelay").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setDragDelay(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("FocusTimeout").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setFocusTimeout(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("LiveAutocompletionDelay").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setLiveAutocompletionDelay(i)).getOrElse(JS.void)),
                "col-4" -> new F7IntOptField().label("LiveAutocompletionThreshold").addOnThisFieldChanged(_.currentValue.map(i => aceEditor.setLiveAutocompletionThreshold(i)).getOrElse(JS.void)),
              ),
              new F7ContainerField("row")(
                //                "col-6" -> F7EnumField.Nullable(Language).label("Language").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setMode(v)).getOrElse(JS.void)),
                //                "col-6" -> F7EnumField.Nullable(Theme).label("Theme").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setTheme(v)).getOrElse(JS.void)),
                "col-4" -> F7EnumField.Nullable(WrapMethod).label("WrapMethod").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setWrapMethod(v)).getOrElse(JS.void)),
                "col-4" -> F7EnumField.Nullable(FoldStyle).label("FoldStyle").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setFoldStyle(v)).getOrElse(JS.void)),
                "col-4" -> F7EnumField.Nullable(NewLineMode).label("NewLineMode").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setNewLineMode(v)).getOrElse(JS.void)),
                "col-4" -> F7EnumField.Nullable(SelectionStyle).label("SelectionStyle").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setSelectionStyle(v)).getOrElse(JS.void)),
                "col-4" -> F7EnumField.Nullable(CursorStyle).label("CursorStyle").addOnThisFieldChanged(_.currentValue.map(v => aceEditor.setCursorStyle(v)).getOrElse(JS.void)),
              )
            )
          }.render()
        }
      }

      aceEditor.render().withStyle("height: 300px;").w_100.border.border_secondary_subtle.bg_white.p_3.mb_3 ++
        editorOptions.renderWidget() ++
        aceEditor.initialize().onDOMContentLoaded.inScriptTag
    }
    closeCodeSample()
  }
}
