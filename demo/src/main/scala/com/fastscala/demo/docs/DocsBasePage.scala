package com.fastscala.demo.docs

import com.fastscala.components.bootstrap5.helpers.BSHelpers
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.db.CurrentUser
import com.fastscala.demo.docs.navigation.DefaultBSMenuRenderer
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, inScriptTag}
import com.fastscala.scala_xml.utils.RenderableWithFSContext
import com.typesafe.config.ConfigFactory

import java.time.LocalDate
import scala.io.Source
import scala.util.Try
import scala.xml.NodeSeq

trait DocsBasePage extends RenderableWithFSContext {

  import BSHelpers.*

  val config = ConfigFactory.load()

  def navBarTopRight()(implicit fsc: FSContext): NodeSeq =
    <div class="text-end justify-content-end">
        {
        CurrentUser().map(user => {
          BSBtn().BtnOutlineWarning.lbl("Logout").ajax(implicit fsc => {
            user.logOut()
          }).btn.ms_2
        }).getOrElse(Empty)
        }
        <a href="https://calendly.com/fastscala/fastscala-free-training" class="btn btn-warning">Register for Free Training!</a>
    </div>
  //      <div class="text-end justify-content-end ms-2">
  //        <a href="https://github.com/fastscala/fastscala" class="btn btn-warning">GitHub</a>
  //    </div> // ++

  def renderSideMenu()(implicit fsc: FSContext): NodeSeq = {
    FSDemoMainMenu.render()(fsc, DefaultBSMenuRenderer.renderer) ++ CurrentUser().map(user => {
      hr ++
        p_3.d_flex.align_items_center.apply {
          a.apply(user.miniHeadshotOrPlaceholderRendered.withStyle("width: 55px;border-radius: 55px;")) ++
            a.text_decoration_none.fw_bold.text_warning.ms_2.apply(user.fullName)
        }
    }).getOrElse(Empty)
  }

  def append2Head(): NodeSeq = NodeSeq.Empty

  def append2Body(): NodeSeq = NodeSeq.Empty

  def pageTitle: String

  def githubUrl: String

  def pageLead(implicit fsc: FSContext): NodeSeq = NodeSeq.Empty

  def openWSSessionAtStart: Boolean = false

  implicit val atTime: LocalDate = LocalDate.now()

  lazy val pageRenderer = JS.rerenderableContents(rerenderer => implicit fsc => {
    renderPageContents()
  }, debugLabel = Some("page"))

  def rerenderPageContents(): Js = pageRenderer.rerender()

  def renderPageContents()(implicit fsc: FSContext): NodeSeq

  def renderTableOfContents()(implicit fsc: FSContext): NodeSeq

  def additionalStyle: String =
    """
      |:root,[data-bs-theme='light'] {
      |    --bd-purple: #0053b4;
      |    --bd-violet: #1c69c2;
      |    --bd-accent: #e1832c;
      |    --bd-violet-rgb: 28, 105, 194;
      |    --bd-accent-rgb: 225, 131, 44;
      |    --bd-pink-rgb: 214,51,132;
      |    --bd-teal-rgb: 32,201,151;
      |    --bd-violet-bg: var(--bd-violet);
      |    --bd-toc-color: var(--bd-violet);
      |    --bd-sidebar-link-bg: rgba(var(--bd-violet-rgb), .1);
      |    --bd-callout-link: 10,88,202;
      |    --bd-callout-code-color: #ab296a;
      |    --bd-pre-bg: var(--bs-tertiary-bg)
      |}
      |""".stripMargin

  def render()(implicit fsc: FSContext): NodeSeq = {
    import BSHelpers.*

    <html>
      <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="author" content={"David Antunes <david@fastscala.com>"}/>
        <title>{pageTitle}</title>
        <meta name="description" content="FastScala is a Web Framework for the Scala language that enables to quickly develop complex web flows."/>
        <!--link href="/static/assets/dist/css/bootstrap.min.css" rel="stylesheet"/-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
        <link href="//cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css" rel="stylesheet"/>
        <link href="/static/custom_base_page.css" rel="stylesheet"/>
        <link href="/static/bootstrap-docs/docs.css" rel="stylesheet"/>
        {JS.inScriptTag(fsc.fsPageScript(openWSSessionAtStart))}
        {append2Head()}
        {Try(config.getString("com.fastscala.demo.pages.include_file_in_head")).map(Source.fromFile(_).getLines().mkString("\n")).map(scala.xml.Unparsed(_)).getOrElse(NodeSeq.Empty)}
        <style>{additionalStyle}</style>
      </head>
      <body data-bs-spy="scroll" data-bs-target="#TableOfContents">
        <div class="skippy visually-hidden-focusable overflow-hidden">
          <div class="container-xl">
            <a class="d-inline-flex p-2 m-1" href="#content">Skip to main content</a>
            <a class="d-none d-md-inline-flex p-2 m-1" href="#bd-docs-nav">Skip to docs navigation</a>
          </div>
        </div>
        <header class="navbar navbar-expand-lg bd-navbar sticky-top">
          <nav class="container-xxl bd-gutter flex-wrap flex-lg-nowrap" aria-label="Main navigation">
            <div class="bd-navbar-toggle">
              <button class="navbar-toggler p-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#bdSidebar" aria-controls="bdSidebar" aria-label="Toggle docs navigation">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" class="bi" fill="currentColor" viewBox="0 0 16 16">
                  <path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                </svg>
                <span class="d-none fs-6 pe-1">Browse</span>
              </button>
            </div>
            <a class="navbar-brand p-0 me-0 me-lg-2" href="/" aria-label="FastScala">
               <img height="32" src="/static/images/fastscala_short_logo_white.png"></img>
            </a>
            <div class="d-flex">
              <div class="bd-search" id="docsearch" data-bd-docs-version="5.3"></div>
              <button class="navbar-toggler d-flex d-lg-none order-3 p-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#bdNavbar" aria-controls="bdNavbar" aria-label="Toggle navigation">
                <svg class="bi" aria-hidden="true">
                  <use xlink:href="#three-dots"></use>
                </svg>
              </button>
            </div>
            <div class="offcanvas-lg offcanvas-end flex-grow-1" tabindex="-1" id="bdNavbar" aria-labelledby="bdNavbarOffcanvasLabel" data-bs-scroll="true">
              <div class="offcanvas-header px-4 pb-0">
                <h5 class="offcanvas-title text-white" id="bdNavbarOffcanvasLabel">Bootstrap</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close" data-bs-target="#bdNavbar"></button>
              </div>
              <div class="offcanvas-body p-4 pt-0 p-lg-0">
                <hr class="d-lg-none text-white-50"/>
                <ul class="navbar-nav flex-row flex-wrap bd-navbar-nav">
                  <li class="nav-item col-6 col-lg-auto">
                    <a class="nav-link py-2 px-0 px-lg-2 active" aria-current="true" href="/docs/5.3/getting-started/introduction/">Docs</a>
                  </li>
                  <li class="nav-item col-6 col-lg-auto">
                    <a class="nav-link py-2 px-0 px-lg-2" href="https://calendly.com/fastscala/fastscala-free-training">Training</a>
                  </li>
                </ul>
                <hr class="d-lg-none text-white-50"/>
                <ul class="navbar-nav flex-row flex-wrap ms-md-auto">
                  <li class="nav-item col-6 col-lg-auto">
                    <a class="nav-link py-2 px-0 px-lg-2" href="https://github.com/fastscala/fastscala" target="_blank" rel="noopener">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" class="navbar-nav-svg" viewBox="0 0 512 499.36" role="img">
                        <title>GitHub</title>
                        <path fill="currentColor" fill-rule="evenodd" d="M256 0C114.64 0 0 114.61 0 256c0 113.09 73.34 209 175.08 242.9 12.8 2.35 17.47-5.56 17.47-12.34 0-6.08-.22-22.18-.35-43.54-71.2 15.49-86.2-34.34-86.2-34.34-11.64-29.57-28.42-37.45-28.42-37.45-23.27-15.84 1.73-15.55 1.73-15.55 25.69 1.81 39.21 26.38 39.21 26.38 22.84 39.12 59.92 27.82 74.5 21.27 2.33-16.54 8.94-27.82 16.25-34.22-56.84-6.43-116.6-28.43-116.6-126.49 0-27.95 10-50.8 26.35-68.69-2.63-6.48-11.42-32.5 2.51-67.75 0 0 21.49-6.88 70.4 26.24a242.65 242.65 0 0 1 128.18 0c48.87-33.13 70.33-26.24 70.33-26.24 14 35.25 5.18 61.27 2.55 67.75 16.41 17.9 26.31 40.75 26.31 68.69 0 98.35-59.85 120-116.88 126.32 9.19 7.9 17.38 23.53 17.38 47.41 0 34.22-.31 61.83-.31 70.23 0 6.85 4.61 14.81 17.6 12.31C438.72 464.97 512 369.08 512 256.02 512 114.62 397.37 0 256 0z"/>
                      </svg>
                      <small class="d-lg-none ms-2">GitHub</small>
                    </a>
                  </li>
                  <li class="nav-item col-6 col-lg-auto">
                    <a class="nav-link py-2 px-0 px-lg-2" href="https://discord.com/channels/1305562942829367398/1332477467084066978" target="_blank" rel="noopener">
                      <i class="bi bi-discord"></i>
                      <small class="d-lg-none ms-2">Discord</small>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </header>
        <div class="container-xxl bd-gutter mt-3 my-md-4 bd-layout">
          <aside class="bd-sidebar">
            <div class="offcanvas-lg offcanvas-start" tabindex="-1" id="bdSidebar" aria-labelledby="bdSidebarOffcanvasLabel">
              <div class="offcanvas-header border-bottom">
                <h5 class="offcanvas-title" id="bdSidebarOffcanvasLabel">Browse docs</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close" data-bs-target="#bdSidebar"></button>
              </div>
              <div class="offcanvas-body">
                {
                FSDemoMainMenu.render()(fsc, com.fastscala.demo.docs.navigation.BSMenuRenderer2)
                }
              </div>
            </div>
          </aside>
          <main class="bd-main order-1">
            <div class="bd-intro pt-2 ps-lg-2">
              <div class="d-md-flex flex-md-row-reverse align-items-center justify-content-between">
                <div class="mb-3 mb-md-0 d-flex text-nowrap"><a class="btn btn-sm btn-bd-light rounded-2" href={githubUrl} title="View and edit this file on GitHub" target="_blank" rel="noopener">
                  View on GitHub
                  </a>
                </div>
                <h1 class="bd-title mb-0" id="content">{pageTitle}</h1>
              </div>
              <p class="bd-lead">{pageLead}</p>
            </div>
            <div class="bd-toc mt-3 mb-5 my-lg-0 mb-lg-5 px-sm-1 text-body-secondary">
              <button class="btn btn-link p-md-0 mb-2 mb-md-0 text-decoration-none bd-toc-toggle d-md-none" type="button" data-bs-toggle="collapse" data-bs-target="#tocContents" aria-expanded="false" aria-controls="tocContents">
                On this page
                <svg class="bi d-md-none ms-2" aria-hidden="true">
                  <use xlink:href="#chevron-expand"></use>
                </svg>
              </button>
              <strong class="d-none d-md-block h6 my-2 ms-3">On this page</strong>
              <hr class="d-none d-md-block my-2 ms-3"/>
              <div class="collapse bd-toc-collapse" id="tocContents">
                {renderTableOfContents()}
              </div>
            </div>
            <div class="bd-content ps-lg-2">
              {renderPageContents()}
            </div>
          </main>
        </div>
        <footer class="bd-footer py-4 py-md-5 mt-5 bg-body-tertiary">
          <div class="container py-4 py-md-5 px-4 px-md-3 text-body-secondary">
            <div class="row">
              <div class="col-lg-3 mb-3">
                <a class="d-inline-flex align-items-center mb-2 text-body-emphasis text-decoration-none" href="/" aria-label="FastScala">
                  <img src="/static/images/fastscala_full_logo_color.svg" height="50"/>
                </a>
                <ul class="list-unstyled small">
                  <li class="mb-2">Documentation website based on the Bootstrap docs, licensed under <a href="https://creativecommons.org/licenses/by/3.0/" target="_blank" rel="license noopener">CC BY 3.0</a>.</li>
                </ul>
              </div>
              <div class="col-6 col-lg-2 offset-lg-1 mb-3">
                <h5>Links</h5>
                <ul class="list-unstyled">
                  <li class="mb-2"><a href="/">Docs</a></li>
                  <li class="mb-2"><a href="https://github.com/fastscala/fastscala">GitHub</a></li>
                  <li class="mb-2"><a href="https://github.com/fastscala/fastscala-demo">Demo GitHub</a></li>
                </ul>
              </div>
              <div class="col-6 col-lg-2 mb-3">
                <h5>Community</h5>
                <ul class="list-unstyled">
                  <li class="mb-2"><a href="https://github.com/fastscala/fastscala/issues" target="_blank" rel="noopener">Issues</a></li>
                  <li class="mb-2"><a href="https://discord.com/channels/1305562942829367398/1332477467084066978" target="_blank" rel="noopener">Discord</a></li>
                  <li class="mb-2"><a href="mailto:david[at]fastscala.com" target="_blank" rel="noopener">Send an Email</a></li>
                </ul>
              </div>
            </div>
          </div>
        </footer>
        <script src="//code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="//cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
        <script src="/static/bootstrap-docs/docs.min.js"></script>
        {append2Body()}
        {Try(config.getString("com.fastscala.demo.pages.include_file_in_body")).map(Source.fromFile(_).getLines().mkString("\n")).map(scala.xml.Unparsed(_)).getOrElse(NodeSeq.Empty)}
        {JS.setContents("fs_num_page_callbacks", scala.xml.Text(fsc.page.callbacks.size.toString)).inScriptTag}
      </body>
    </html>

  }
}
