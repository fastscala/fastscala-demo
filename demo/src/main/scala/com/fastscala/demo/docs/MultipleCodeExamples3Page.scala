package com.fastscala.demo.docs

import com.fastscala.core.FSContext
import com.fastscala.demo.server.JettyServer
import com.fastscala.scala_xml.ScalaXmlNodeSeqUtils.MkNSFromNodeSeq
import org.apache.commons.lang3.StringUtils
import org.eclipse.jetty.util.IO

import java.nio.charset.StandardCharsets
import java.nio.file.Path
import scala.util.matching.Regex
import scala.xml.NodeSeq

abstract class MultipleCodeExamples3Page() extends DocsBasePage() {

  val CommentSeparator = "=== code sample ==="

  def file = getClass.getName.split("\\.").mkString("/", "/", ".scala")

  override def append2Head(): NodeSeq = super.append2Head() ++
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/styles/atom-one-light.min.css" integrity="sha512-o5v54Kh5PH0dgnf9ei0L+vMRsbm5fvIvnR/XkrZZjN4mqdaeH7PW66tumBoQVIaKNVrLCZiBEfHzRY4JJSMK/Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />

  override def append2Body(): NodeSeq = super.append2Body() ++ {
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/highlight.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/languages/scala.min.js"></script>
    <script>hljs.highlightAll();</script>
  }

  def githubUrl: String = s"https://github.com/fastscala/fastscala-demo/tree/main/demo/src/main/scala$file"

  def pageTitle: String

  def renderAllCodeSamples()(implicit fsc: FSContext): Unit

  private def searchCodeSamplesMarkedWithComments(file: String): NodeSeq = {
    val allCode = IO.toString(Path.of(getClass.getResource(file).toURI()), StandardCharsets.UTF_8)
    val allSections: Array[String] = allCode.split("\n.*" + Regex.quote(CommentSeparator) + ".*\n")
    val relevantSections: List[String] = allSections.zipWithIndex.toList.collect({
      case (code, idx) if (idx + 1) % 2 == 0 => code.replaceAll("(^|\n).*" + Regex.quote(CommentSeparator) + ".*\n", "")
    })
    val code = relevantSections.mkString("\n\n// [...]\n\n")
    if (code != "") {
      val leftPadding: Int = code.split("\n").iterator.map(_.takeWhile(_ == ' ').size).filter(_ > 0).minOption.getOrElse(0)
      val withoutPadding = code.split("\n").map(_.drop(leftPadding)).mkString("\n")

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      div.apply {
        <pre><code style="background: rgba(0,0,0,0);" class="language-scala">{withoutPadding}</code></pre>.mb_2.border.border_secondary_subtle
      }
    } else NodeSeq.Empty
  }

  override def renderTableOfContents()(implicit fsc: FSContext): NodeSeq = {
    <nav id="TableOfContents">
      <ul>
        {
        output.reverse.collect({
          case RenderedCodeSample(_, Some(title), Some(id)) => <li><a href={"#" + id}>{title}</a></li>
        })
        }
      </ul>
    </nav>
  }

  override def render()(implicit fsc: FSContext): NodeSeq = {
    renderAllCodeSamples()

    super.render()
  }

  override def renderPageContents()(implicit fsc: FSContext): NodeSeq = {
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

    val codeSamplesInComments = searchCodeSamplesMarkedWithComments(file)

    codeSamplesInComments ++
      output.reverse.flatMap(_.ns)
  }

  case class RenderedCodeSample(ns: NodeSeq, title: Option[String], id: Option[String])

  private var output: List[RenderedCodeSample] = Nil

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  private def renderedCodeSampleWrapper = div.withClass("bd-example m-0 border-0")

  private def renderCodeSample(
                                title: String,
                                description: NodeSeq,
                                renderedSourceCode: NodeSeq,
                                renderedSample: NodeSeq
                              ): RenderedCodeSample = {
    val id = StringUtils.splitByCharacterTypeCamelCase(title).mkString(" ").toLowerCase.replaceAll(" ", "-").filter(_.isLetterOrDigit)

    val rendered = {
      <h2 id={id}>{title} <a class="anchor-link" href={"#" + id} aria-label={"Link to this code sample: " + title}></a></h2> ++
        description ++
        <div class="bd-example-snippet bd-code-snippet">
            {
              renderedCodeSampleWrapper.apply {
                renderedSample
              }
            }
            <div class="d-flex align-items-center highlight-toolbar ps-3 pe-2 py-1 border-0 border-top border-bottom">
              <small class="font-monospace text-body-secondary text-uppercase">scala</small>
              <div class="d-flex ms-auto">
                  <button type="button" class="btn-clipboard mt-0 me-0" title="Copy to clipboard">
                      <i class="bi bi-clipboard"></i>
                  </button>
              </div>
            </div>
          <div class="highlight p-0">{renderedSourceCode}</div>
        </div>
    }
    RenderedCodeSample(rendered, Some(title), Some(id))
  }

  private case class OpenCodeSample(
                                     lastSectionStartedAt: Int,
                                     title: String,
                                     description: NodeSeq,
                                     contents: NodeSeq
                                   )

  private var currentlyOpenCodeSample: Option[OpenCodeSample] = None

  private val lines = IO.toString(Path.of(getClass.getResource(file).toURI()), StandardCharsets.UTF_8).split("\\n")
  private val stackTracePos = if (JettyServer.useVirtualThreads) 2 else 3

  def renderCodeSampleAndAutoClosePreviousOneStrDesc(
                                                      title: String,
                                                      description: String,
                                                      thisSectionStartsAt: Int = Thread.currentThread.getStackTrace.apply(stackTracePos).getLineNumber
                                                    )(contents: => NodeSeq): Unit =
    renderCodeSampleAndAutoClosePreviousOne(title, p.apply(description), thisSectionStartsAt)(contents)

  def renderCodeSampleAndAutoClosePreviousOne(
                                               title: String,
                                               description: NodeSeq = NodeSeq.Empty,
                                               thisSectionStartsAt: Int = Thread.currentThread.getStackTrace.apply(stackTracePos).getLineNumber
                                             )(contents: => NodeSeq): Unit = {
    closeOpenCodeSample(thisSectionStartsAt)
    currentlyOpenCodeSample = Some(OpenCodeSample(thisSectionStartsAt, title, description, contents))
  }

  def renderHtmlAndAutoClosePreviousCodeSample(
                                                thisSectionStartsAt: Int = Thread.currentThread.getStackTrace.apply(stackTracePos).getLineNumber
                                              )(contents: => NodeSeq): Unit = {
    closeOpenCodeSample(thisSectionStartsAt)
    currentlyOpenCodeSample = None
    output ::= RenderedCodeSample(contents, None, None)
  }

  def closeCodeSample(): Unit = renderCodeSampleAndAutoClosePreviousOne("", NodeSeq.Empty, Thread.currentThread.getStackTrace.apply(stackTracePos).getLineNumber)(NodeSeq.Empty)

  private def closeOpenCodeSample(thisSectionStartsAt: Int): Unit = {
    import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
    currentlyOpenCodeSample.foreach({
      case OpenCodeSample(lastSectionStartedAt, title, description, contents) =>
        val code = lines.drop(lastSectionStartedAt).take(thisSectionStartsAt - lastSectionStartedAt - 2)
        val leftPadding: Int = code.iterator.map(_.takeWhile(_ == ' ').size).filter(_ > 0).minOption.getOrElse(0)
        val withoutPadding = code.map(_.drop(leftPadding)).mkString("\n")
        val rendered = div.apply {
          <pre><code style="background: rgba(0,0,0,0);" class="language-scala">{withoutPadding}</code></pre>.m_0
        }
        output ::= renderCodeSample(title, description, rendered, contents)
    })
    currentlyOpenCodeSample = None
  }
}
