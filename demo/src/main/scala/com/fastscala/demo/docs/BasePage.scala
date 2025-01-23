package com.fastscala.demo.docs

import com.fastscala.core.FSContext
import com.fastscala.demo.db.CurrentUser
import com.fastscala.js.Js
import com.fastscala.scala_xml.js.inScriptTag
import com.fastscala.scala_xml.js.JS
import com.fastscala.scala_xml.utils.RenderableWithFSContext
import com.fastscala.components.bootstrap5.helpers.BSHelpers
import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.typesafe.config.ConfigFactory

import java.time.LocalDate
import scala.io.Source
import scala.util.Try
import scala.xml.NodeSeq

trait BasePage extends RenderableWithFSContext {

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
        <a href="https://training.fastscala.com/" class="btn btn-warning">Register for Free Training!</a>
    </div>
  //      <div class="text-end justify-content-end ms-2">
  //        <a href="https://github.com/fastscala/fastscala" class="btn btn-warning">GitHub</a>
  //    </div> // ++

  def renderSideMenu()(implicit fsc: FSContext): NodeSeq = {
    FSDemoMainMenu.render() ++ CurrentUser().map(user => {
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

  def openWSSessionAtStart: Boolean = false

  implicit val atTime: LocalDate = LocalDate.now()

  lazy val pageRenderer = JS.rerenderableContents(rerenderer => implicit fsc => {
    renderPageContents()
  }, debugLabel = Some("page"))

  def rerenderPageContents(): Js = pageRenderer.rerender()

  def renderPageContents()(implicit fsc: FSContext): NodeSeq

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
        {JS.inScriptTag(fsc.fsPageScript(openWSSessionAtStart))}
        {append2Head()}
        {Try(config.getString("com.fastscala.demo.pages.include_file_in_head")).map(Source.fromFile(_).getLines().mkString("\n")).map(scala.xml.Unparsed(_)).getOrElse(NodeSeq.Empty)}
      </head>
      <body>

        <div class="container-xxl bd-gutter mt-3 my-md-4 bd-layout">
          <aside class="bd-sidebar">
          <div class="offcanvas-lg offcanvas-start" tabindex="-1" id="bdSidebar" aria-labelledby="bdSidebarOffcanvasLabel">
        <div class="offcanvas-header border-bottom">
          <h5 class="offcanvas-title" id="bdSidebarOffcanvasLabel">Browse docs</h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close" data-bs-target="#bdSidebar"></button>
        </div>

        <div class="offcanvas-body">
          <nav class="bd-links w-100" id="bd-docs-nav" aria-label="Docs navigation"><ul class="bd-links-nav list-unstyled mb-0 pb-3 pb-md-2 pe-lg-2">
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-indigo);" aria-hidden="true"><use xlink:href="#book-half"></use></svg>
          Getting started
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/getting-started/introduction/" class="bd-links-link d-inline-block rounded">Introduction</a></li>
            <li><a href="/docs/5.3/getting-started/download/" class="bd-links-link d-inline-block rounded">Download</a></li>
            <li><a href="/docs/5.3/getting-started/contents/" class="bd-links-link d-inline-block rounded">Contents</a></li>
            <li><a href="/docs/5.3/getting-started/browsers-devices/" class="bd-links-link d-inline-block rounded">Browsers &amp; devices</a></li>
            <li><a href="/docs/5.3/getting-started/javascript/" class="bd-links-link d-inline-block rounded">JavaScript</a></li>
            <li><a href="/docs/5.3/getting-started/webpack/" class="bd-links-link d-inline-block rounded">Webpack</a></li>
            <li><a href="/docs/5.3/getting-started/parcel/" class="bd-links-link d-inline-block rounded">Parcel</a></li>
            <li><a href="/docs/5.3/getting-started/vite/" class="bd-links-link d-inline-block rounded">Vite</a></li>
            <li><a href="/docs/5.3/getting-started/accessibility/" class="bd-links-link d-inline-block rounded">Accessibility</a></li>
            <li><a href="/docs/5.3/getting-started/rfs/" class="bd-links-link d-inline-block rounded">RFS</a></li>
            <li><a href="/docs/5.3/getting-started/rtl/" class="bd-links-link d-inline-block rounded">RTL</a></li>
            <li><a href="/docs/5.3/getting-started/contribute/" class="bd-links-link d-inline-block rounded">Contribute</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-pink);" aria-hidden="true"><use xlink:href="#palette2"></use></svg>
          Customize
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/customize/overview/" class="bd-links-link d-inline-block rounded">Overview</a></li>
            <li><a href="/docs/5.3/customize/sass/" class="bd-links-link d-inline-block rounded">Sass</a></li>
            <li><a href="/docs/5.3/customize/options/" class="bd-links-link d-inline-block rounded">Options</a></li>
            <li><a href="/docs/5.3/customize/color/" class="bd-links-link d-inline-block rounded">Color</a></li>
            <li><a href="/docs/5.3/customize/color-modes/" class="bd-links-link d-inline-block rounded">Color modes</a></li>
            <li><a href="/docs/5.3/customize/components/" class="bd-links-link d-inline-block rounded">Components</a></li>
            <li><a href="/docs/5.3/customize/css-variables/" class="bd-links-link d-inline-block rounded">CSS variables</a></li>
            <li><a href="/docs/5.3/customize/optimize/" class="bd-links-link d-inline-block rounded">Optimize</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-teal);" aria-hidden="true"><use xlink:href="#grid-fill"></use></svg>
          Layout
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/layout/breakpoints/" class="bd-links-link d-inline-block rounded">Breakpoints</a></li>
            <li><a href="/docs/5.3/layout/containers/" class="bd-links-link d-inline-block rounded">Containers</a></li>
            <li><a href="/docs/5.3/layout/grid/" class="bd-links-link d-inline-block rounded">Grid</a></li>
            <li><a href="/docs/5.3/layout/columns/" class="bd-links-link d-inline-block rounded">Columns</a></li>
            <li><a href="/docs/5.3/layout/gutters/" class="bd-links-link d-inline-block rounded">Gutters</a></li>
            <li><a href="/docs/5.3/layout/utilities/" class="bd-links-link d-inline-block rounded">Utilities</a></li>
            <li><a href="/docs/5.3/layout/z-index/" class="bd-links-link d-inline-block rounded">Z-index</a></li>
            <li><a href="/docs/5.3/layout/css-grid/" class="bd-links-link d-inline-block rounded">CSS Grid</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-gray);" aria-hidden="true"><use xlink:href="#file-earmark-richtext"></use></svg>
          Content
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/content/reboot/" class="bd-links-link d-inline-block rounded">Reboot</a></li>
            <li><a href="/docs/5.3/content/typography/" class="bd-links-link d-inline-block rounded">Typography</a></li>
            <li><a href="/docs/5.3/content/images/" class="bd-links-link d-inline-block rounded">Images</a></li>
            <li><a href="/docs/5.3/content/tables/" class="bd-links-link d-inline-block rounded">Tables</a></li>
            <li><a href="/docs/5.3/content/figures/" class="bd-links-link d-inline-block rounded">Figures</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-blue);" aria-hidden="true"><use xlink:href="#ui-radios"></use></svg>
          Forms
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/forms/overview/" class="bd-links-link d-inline-block rounded">Overview</a></li>
            <li><a href="/docs/5.3/forms/form-control/" class="bd-links-link d-inline-block rounded">Form control</a></li>
            <li><a href="/docs/5.3/forms/select/" class="bd-links-link d-inline-block rounded">Select</a></li>
            <li><a href="/docs/5.3/forms/checks-radios/" class="bd-links-link d-inline-block rounded">Checks &amp; radios</a></li>
            <li><a href="/docs/5.3/forms/range/" class="bd-links-link d-inline-block rounded">Range</a></li>
            <li><a href="/docs/5.3/forms/input-group/" class="bd-links-link d-inline-block rounded">Input group</a></li>
            <li><a href="/docs/5.3/forms/floating-labels/" class="bd-links-link d-inline-block rounded">Floating labels</a></li>
            <li><a href="/docs/5.3/forms/layout/" class="bd-links-link d-inline-block rounded">Layout</a></li>
            <li><a href="/docs/5.3/forms/validation/" class="bd-links-link d-inline-block rounded">Validation</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-cyan);" aria-hidden="true"><use xlink:href="#menu-button-wide-fill"></use></svg>
          Components
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/components/accordion/" class="bd-links-link d-inline-block rounded">Accordion</a></li>
            <li><a href="/docs/5.3/components/alerts/" class="bd-links-link d-inline-block rounded">Alerts</a></li>
            <li><a href="/docs/5.3/components/badge/" class="bd-links-link d-inline-block rounded">Badge</a></li>
            <li><a href="/docs/5.3/components/breadcrumb/" class="bd-links-link d-inline-block rounded">Breadcrumb</a></li>
            <li><a href="/docs/5.3/components/buttons/" class="bd-links-link d-inline-block rounded">Buttons</a></li>
            <li><a href="/docs/5.3/components/button-group/" class="bd-links-link d-inline-block rounded">Button group</a></li>
            <li><a href="/docs/5.3/components/card/" class="bd-links-link d-inline-block rounded">Card</a></li>
            <li><a href="/docs/5.3/components/carousel/" class="bd-links-link d-inline-block rounded">Carousel</a></li>
            <li><a href="/docs/5.3/components/close-button/" class="bd-links-link d-inline-block rounded">Close button</a></li>
            <li><a href="/docs/5.3/components/collapse/" class="bd-links-link d-inline-block rounded">Collapse</a></li>
            <li><a href="/docs/5.3/components/dropdowns/" class="bd-links-link d-inline-block rounded">Dropdowns</a></li>
            <li><a href="/docs/5.3/components/list-group/" class="bd-links-link d-inline-block rounded">List group</a></li>
            <li><a href="/docs/5.3/components/modal/" class="bd-links-link d-inline-block rounded">Modal</a></li>
            <li><a href="/docs/5.3/components/navbar/" class="bd-links-link d-inline-block rounded">Navbar</a></li>
            <li><a href="/docs/5.3/components/navs-tabs/" class="bd-links-link d-inline-block rounded">Navs &amp; tabs</a></li>
            <li><a href="/docs/5.3/components/offcanvas/" class="bd-links-link d-inline-block rounded">Offcanvas</a></li>
            <li><a href="/docs/5.3/components/pagination/" class="bd-links-link d-inline-block rounded">Pagination</a></li>
            <li><a href="/docs/5.3/components/placeholders/" class="bd-links-link d-inline-block rounded">Placeholders</a></li>
            <li><a href="/docs/5.3/components/popovers/" class="bd-links-link d-inline-block rounded">Popovers</a></li>
            <li><a href="/docs/5.3/components/progress/" class="bd-links-link d-inline-block rounded">Progress</a></li>
            <li><a href="/docs/5.3/components/scrollspy/" class="bd-links-link d-inline-block rounded">Scrollspy</a></li>
            <li><a href="/docs/5.3/components/spinners/" class="bd-links-link d-inline-block rounded">Spinners</a></li>
            <li><a href="/docs/5.3/components/toasts/" class="bd-links-link d-inline-block rounded active" aria-current="page">Toasts</a></li>
            <li><a href="/docs/5.3/components/tooltips/" class="bd-links-link d-inline-block rounded">Tooltips</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-orange);" aria-hidden="true"><use xlink:href="#magic"></use></svg>
          Helpers
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/helpers/clearfix/" class="bd-links-link d-inline-block rounded">Clearfix</a></li>
            <li><a href="/docs/5.3/helpers/color-background/" class="bd-links-link d-inline-block rounded">Color &amp; background</a></li>
            <li><a href="/docs/5.3/helpers/colored-links/" class="bd-links-link d-inline-block rounded">Colored links</a></li>
            <li><a href="/docs/5.3/helpers/focus-ring/" class="bd-links-link d-inline-block rounded">Focus ring</a></li>
            <li><a href="/docs/5.3/helpers/icon-link/" class="bd-links-link d-inline-block rounded">Icon link</a></li>
            <li><a href="/docs/5.3/helpers/position/" class="bd-links-link d-inline-block rounded">Position</a></li>
            <li><a href="/docs/5.3/helpers/ratio/" class="bd-links-link d-inline-block rounded">Ratio</a></li>
            <li><a href="/docs/5.3/helpers/stacks/" class="bd-links-link d-inline-block rounded">Stacks</a></li>
            <li><a href="/docs/5.3/helpers/stretched-link/" class="bd-links-link d-inline-block rounded">Stretched link</a></li>
            <li><a href="/docs/5.3/helpers/text-truncation/" class="bd-links-link d-inline-block rounded">Text truncation</a></li>
            <li><a href="/docs/5.3/helpers/vertical-rule/" class="bd-links-link d-inline-block rounded">Vertical rule</a></li>
            <li><a href="/docs/5.3/helpers/visually-hidden/" class="bd-links-link d-inline-block rounded">Visually hidden</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-red);" aria-hidden="true"><use xlink:href="#braces-asterisk"></use></svg>
          Utilities
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/utilities/api/" class="bd-links-link d-inline-block rounded">API</a></li>
            <li><a href="/docs/5.3/utilities/background/" class="bd-links-link d-inline-block rounded">Background</a></li>
            <li><a href="/docs/5.3/utilities/borders/" class="bd-links-link d-inline-block rounded">Borders</a></li>
            <li><a href="/docs/5.3/utilities/colors/" class="bd-links-link d-inline-block rounded">Colors</a></li>
            <li><a href="/docs/5.3/utilities/display/" class="bd-links-link d-inline-block rounded">Display</a></li>
            <li><a href="/docs/5.3/utilities/flex/" class="bd-links-link d-inline-block rounded">Flex</a></li>
            <li><a href="/docs/5.3/utilities/float/" class="bd-links-link d-inline-block rounded">Float</a></li>
            <li><a href="/docs/5.3/utilities/interactions/" class="bd-links-link d-inline-block rounded">Interactions</a></li>
            <li><a href="/docs/5.3/utilities/link/" class="bd-links-link d-inline-block rounded">Link</a></li>
            <li><a href="/docs/5.3/utilities/object-fit/" class="bd-links-link d-inline-block rounded">Object fit</a></li>
            <li><a href="/docs/5.3/utilities/opacity/" class="bd-links-link d-inline-block rounded">Opacity</a></li>
            <li><a href="/docs/5.3/utilities/overflow/" class="bd-links-link d-inline-block rounded">Overflow</a></li>
            <li><a href="/docs/5.3/utilities/position/" class="bd-links-link d-inline-block rounded">Position</a></li>
            <li><a href="/docs/5.3/utilities/shadows/" class="bd-links-link d-inline-block rounded">Shadows</a></li>
            <li><a href="/docs/5.3/utilities/sizing/" class="bd-links-link d-inline-block rounded">Sizing</a></li>
            <li><a href="/docs/5.3/utilities/spacing/" class="bd-links-link d-inline-block rounded">Spacing</a></li>
            <li><a href="/docs/5.3/utilities/text/" class="bd-links-link d-inline-block rounded">Text</a></li>
            <li><a href="/docs/5.3/utilities/vertical-align/" class="bd-links-link d-inline-block rounded">Vertical align</a></li>
            <li><a href="/docs/5.3/utilities/visibility/" class="bd-links-link d-inline-block rounded">Visibility</a></li>
            <li><a href="/docs/5.3/utilities/z-index/" class="bd-links-link d-inline-block rounded">Z-index</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-blue);" aria-hidden="true"><use xlink:href="#tools"></use></svg>
          Extend
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/extend/approach/" class="bd-links-link d-inline-block rounded">Approach</a></li>
            <li><a href="/docs/5.3/extend/icons/" class="bd-links-link d-inline-block rounded">Icons</a></li>
        </ul>
      </li>
      <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            <svg class="bi me-2" style="color: var(--bs-indigo);" aria-hidden="true"><use xlink:href="#globe2"></use></svg>
          About
        </strong>

        <ul class="list-unstyled fw-normal pb-2 small">
            <li><a href="/docs/5.3/about/overview/" class="bd-links-link d-inline-block rounded">Overview</a></li>
            <li><a href="/docs/5.3/about/team/" class="bd-links-link d-inline-block rounded">Team</a></li>
            <li><a href="/docs/5.3/about/brand/" class="bd-links-link d-inline-block rounded">Brand</a></li>
            <li><a href="/docs/5.3/about/license/" class="bd-links-link d-inline-block rounded">License</a></li>
            <li><a href="/docs/5.3/about/translations/" class="bd-links-link d-inline-block rounded">Translations</a></li>
        </ul>
      </li>
      <li class="bd-links-span-all mt-1 mb-3 mx-4 border-top"></li>
      <li class="bd-links-span-all">
        <a href="/docs/5.3/migration/" class="bd-links-link d-inline-block rounded small ">
          Migration
        </a>
      </li>
  </ul>
</nav>

        </div>
      </div>
          </aside>
        </div>
        <main>
            <aside class="main-sidebar min-vh-100 h-100 offcanvas-lg offcanvas-start" id="main-sidebar">
                <div class="d-flex flex-nowrap min-vh-100 h-100">
                    <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 280px;">
                        <div class="position-relative">
                          <a href="/" class="p-3 d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                            <img style="width: 200px;" src="/static/images/logo-wide.png"></img>
                          </a>
                          <button type="button" class="btn-close btn-close-white d-lg-none text-white position-absolute end-0 top-0" data-bs-dismiss="offcanvas" aria-label="Close" data-bs-target="#main-sidebar"></button>
                        </div>
                        {renderSideMenu()}
                    </div>
                </div>
            </aside>

            <div class="main-content min-vh-100">
                <header class="p-3 text-bg-dark">
                    <div class="d-flex flex-row align-items-center justify-content-between">
                        <div>
                            <button class="d-lg-none navbar-toggler p-2" type="button" data-bs-toggle="offcanvas"
                                    data-bs-target="#main-sidebar" aria-controls="main-sidebar"
                                    aria-label="Toggle docs navigation">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" class="bi" fill="currentColor"
                                     viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>
                                </svg>

                                <span class="d-none fs-6 pe-1">Browse</span>
                            </button>
                        </div>

                        {div.d_flex.apply(navBarTopRight())}
                    </div>
                </header>
                <header class="py-1 px-2 text-bg-secondary">#Callbacks: <span id="fs_num_page_callbacks">--</span></header>

                <div class="p-3">
                  {renderPageContents()}
                </div>
            </div>
        </main>

        <script src="//code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
        <!--script src="/static/assets/dist/js/bootstrap.bundle.min.js"></script-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="//cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
        {append2Body()}
        {Try(config.getString("com.fastscala.demo.pages.include_file_in_body")).map(Source.fromFile(_).getLines().mkString("\n")).map(scala.xml.Unparsed(_)).getOrElse(NodeSeq.Empty)}
        {JS.setContents("fs_num_page_callbacks", scala.xml.Text(fsc.page.callbacks.size.toString)).inScriptTag}
      </body>
    </html>

  }
}
