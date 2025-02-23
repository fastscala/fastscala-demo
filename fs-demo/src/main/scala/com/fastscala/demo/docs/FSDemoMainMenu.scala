package com.fastscala.demo.docs

import com.fastscala.demo.docs.about.{AboutPage, AuthorPage, GettingStartedPage}
import com.fastscala.demo.docs.bootstrap.{BootstrapButtonsPage, BootstrapImagesPage, BootstrapModalPage, BootstrapOffcanvasPage, BootstrapTypographyPage}
import com.fastscala.demo.docs.fastscala.*
import com.fastscala.demo.docs.forms.*
import com.fastscala.demo.docs.html.{HtmlTagsPage, HtmlUtilsPage, ScalaTagsPage}
import com.fastscala.demo.docs.js.JsUtilsPage
import com.fastscala.demo.docs.jstree.JSTreePage
import com.fastscala.demo.docs.loggedout.LoginPage
import com.fastscala.demo.docs.navigation.DefaultBSMenuRenderer.*
import com.fastscala.demo.docs.navigation.{BSMenu, MenuSection, RoutingMenuItem, SimpleMenuItem}
import com.fastscala.demo.docs.other.AceEditorPage
import com.fastscala.demo.docs.tables.ModifyingTableExamplePage

import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

object FSDemoMainMenu extends BSMenu(
  MenuSection("About FastScala", <i class="bi bi-rocket-takeoff"></i>.withColorDanger)(
    new RoutingMenuItem()("About", () => new AboutPage())
    , new RoutingMenuItem("getting_started")("Getting Started", () => new GettingStartedPage())
    , new RoutingMenuItem("author")("Author", () => new AuthorPage())
  )
  , MenuSection("FastScala", <i class="bi bi-book"></i>.withColorInfo)(
    new RoutingMenuItem("demo", "fastscala", "callbacks")("Callbacks", () => new CallbacksPage())
    , new RoutingMenuItem("demo", "fastscala", "rerenderable")("Rerenderable", () => new RerenderablePage())
    , new RoutingMenuItem("demo", "fastscala", "file_upload")("File Upload", () => new FileUploadPage())
    , new RoutingMenuItem("demo", "fastscala", "anon_page")("Anonymous Page", () => new AnonymousPage())
    , new RoutingMenuItem("demo", "fastscala", "file_download")("File Download", () => new FileDownloadPage())
    , new RoutingMenuItem("demo", "fastscala", "server_side_push")("Server Side Push", () => new ServerSidePushPage())
    , new RoutingMenuItem("demo", "fastscala", "internal_metrics")("Internal Metrics", () => new InternalMetricsPage())
    , new RoutingMenuItem("demo", "fastscala", "discard_fs_context")("FSContext discard", () => new FSContextDiscardPage())
    , new SimpleMenuItem("Grafana", "https://grafana.fastscala.com/public-dashboards/e01e760c4321418e9b4903e7e6bfcfb0?orgId=1&refresh=5s")
  ),
  MenuSection("HTML utils", <i class="bi bi-filetype-html"></i>.withColorDark)(
    new RoutingMenuItem("demo", "html", "tags")("tags", () => new HtmlTagsPage())
    , new RoutingMenuItem("demo", "html", "utils")("utils", () => new HtmlUtilsPage())
    , new RoutingMenuItem("demo", "html", "scala-tags")("Integrating ScalaTags", () => new ScalaTagsPage())
  ),
  MenuSection("Js utils", <i class="bi bi-filetype-js"></i>.withColorDark)(
    new RoutingMenuItem("demo", "js", "overview")("Overview", () => new JsUtilsPage()),
  ),
  MenuSection("Bootstrap utils", <i class="bi bi-bootstrap"></i>.withColorSuccess)(
    new RoutingMenuItem("demo", "bootstrap", "buttons")("Buttons", () => new BootstrapButtonsPage())
    , new RoutingMenuItem("demo", "bootstrap", "typography")("Typography", () => new BootstrapTypographyPage())
    , new RoutingMenuItem("demo", "bootstrap", "images")("Images", () => new BootstrapImagesPage())
    , new RoutingMenuItem("demo", "bootstrap", "modal")("Modal", () => new BootstrapModalPage())
    , new RoutingMenuItem("demo", "bootstrap", "offcanvas")("Offcanvas", () => new BootstrapOffcanvasPage())
  ),
  MenuSection("Form7 Library", <i class="bi bi-ui-checks"></i>.withColorPrimary)(
    SimpleMenuItem("Creating a form", "/demo/simple_form")
    , new RoutingMenuItem("demo", "forms", "text_input")("Text Input Fields", () => new TextInputFieldsPage())
    , new RoutingMenuItem("demo", "forms", "select_input")("Select Input Fields", () => new SelectInputFieldsPage())
    , new RoutingMenuItem("demo", "forms", "checkbox_input")("Checkbox Input Fields", () => new CheckboxInputFieldsPage())
    , new RoutingMenuItem("demo", "forms", "radio_input")("Radio Input Fields", () => new RadioInputFieldsPage())
    , new RoutingMenuItem("demo", "forms", "validation")("Validation", () => new FormValidationPage())
    , new RoutingMenuItem("demo", "forms", "validation-strategies")("Validation Strategies", () => new ValidationStrategiesPage())
    , new RoutingMenuItem("demo", "forms", "validation-by-field-type")("Validation by Field Type", () => new ValidationByFieldTypePage())
    , new RoutingMenuItem("demo", "forms", "input-groups")("Input Groups", () => new FormInputGroupsPage())
    , new RoutingMenuItem("demo", "forms", "server-side-update")("Server-Side Update", () => new UpdatesFromServerSidePage())
    , new RoutingMenuItem("demo", "forms", "field-state")("Field state", () => new FieldStatesPage())
  ),
  MenuSection("Table5 Library", <i class="bi bi-table"></i>.withColorPrimary)(
    SimpleMenuItem("Simple", "/demo/simple_tables")
    , SimpleMenuItem("Sortable", "/demo/sortable_tables")
    , SimpleMenuItem("Paginated", "/demo/paginated_tables")
    , SimpleMenuItem("Selectable Rows", "/demo/selectable_rows_tables")
    , SimpleMenuItem("Selectable Columns", "/demo/tables_sel_cols")
    , new RoutingMenuItem("demo", "tables", "modifying")("Modifying tables", () => new ModifyingTableExamplePage())
  ),
  MenuSection("Log-in demo", <i class="bi bi-box-arrow-in-left"></i>.withColorDark)(
    new RoutingMenuItem("demo", "login")("Login example", () => new LoginPage()),
  ),
  MenuSection("Other integrations", <i class="bi bi-code-square"></i>.withColorPrimary)(
    SimpleMenuItem("ChartJS", "/demo/chartjs/simple"),
    new RoutingMenuItem("demo", "jstree", "simple")("JSTree", () => new JSTreePage()),
    new RoutingMenuItem("demo", "ace-editor", "simple")("AceEditor", () => new AceEditorPage()),
  ),
)
