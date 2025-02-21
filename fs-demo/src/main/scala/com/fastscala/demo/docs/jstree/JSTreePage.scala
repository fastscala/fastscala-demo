package com.fastscala.demo.docs.jstree

import com.fastscala.components.jstree.editable.EditableJSTreeNode
import com.fastscala.components.jstree.{DefaultJSTreeContextMenuAction, JSTree, JSTreeContextMenuAction, JSTreeNode, JSTreeNodeWithContextMenu, JSTreeSimpleNode, JSTreeWithContextMenu}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.Continents
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, inScriptTag}

import java.util.Date
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.xml.NodeSeq

class JSTreePage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "JsTree Example"

  override def append2Head(): NodeSeq = super.append2Head() ++
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.17/themes/default/style.min.css" />

  override def append2Body(): NodeSeq = super.append2Body() ++
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.17/jstree.min.js"></script>

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Countries", <span>An <code>onSelect</code> handler can also be set.</span>) {

      lazy val data = Source.fromResource("world-cities.csv").getLines().drop(1).map(_.split(",")).collect({
        case Array(name, country, subcountry, geonameid) => (country, subcountry, name)
      }).toVector
      lazy val country2Region2City: Map[String, Map[String, Vector[String]]] = data.groupBy(_._1).transform((k, v) => v.groupBy(_._2).transform((k, v) => v.map(_._3)))

      val jsTree = new JSTree[JSTreeSimpleNode[Unit]] {
        override val rootNodes: Seq[JSTreeSimpleNode[Unit]] =
          List(new JSTreeSimpleNode[Unit]("Cities of the world", (), s"root")(
            country2Region2City.toVector.sortBy(_._1).map({
              case (country, region2City) =>
                new JSTreeSimpleNode[Unit](country, (), s"c_$country")(
                  region2City.toVector.sortBy(_._1).map({
                    case (region, cities) =>
                      new JSTreeSimpleNode[Unit](region, (), s"r_$region")(
                        cities.sorted.map(city =>
                          new JSTreeSimpleNode[Unit](city, (), s"c_$city", true)(Nil)
                        )
                      )
                  })
                )
            })
          ))
      }

      val onSelect = fsc.callback(
        Js("data.node.id"),
        nodeId => {
          println(s"JsTree Selected Node is: $nodeId")
          JS.consoleLog(s"JsTree Selected Node is: $nodeId")
        },
      )

      jsTree.render().withStyle("height: 600px; overflow: auto;")
        ++ jsTree.init(onSelect = onSelect).onDOMContentLoaded.inScriptTag
    }
    renderCodeSampleAndAutoClosePreviousOne("Countries (dedicated classes)", <p>Same functionality but with dedicated classes for RootNode, CountryNode, RegionNode and CityNode.</p>) {

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      lazy val data = Source.fromResource("world-cities.csv").getLines().drop(1).map(_.split(",")).collect({
        case Array(name, country, subcountry, geonameid) => (country, subcountry, name)
      }).toVector

      abstract class Node(title: String) extends JSTreeNode[Node] {
        override def titleNs: NodeSeq = span.apply(title)

        val open: Boolean = false
        val disabled: Boolean = false
        val icon: Option[String] = None
      }

      class RootNode(country2Region2City: Map[String, Map[String, Vector[String]]]) extends Node("Cities of the world") {
        val id: String = "root"

        override def childrenF: () => collection.Seq[Node] = () => country2Region2City.toVector.sortBy(_._1).map({
          case (country, region2City) => new CountryNode(country, region2City)
        })
      }

      class CountryNode(countryName: String, region2City: Map[String, Vector[String]]) extends Node(countryName) {
        val id: String = "country-" + countryName

        override def childrenF: () => collection.Seq[Node] = () => region2City.toVector.sortBy(_._1).map({
          case (region, cities) => new RegionNode(region, cities)
        })
      }

      class RegionNode(regionName: String, cities: Vector[String]) extends Node(regionName) {
        val id: String = "region-" + regionName

        override def childrenF: () => collection.Seq[Node] = () => cities.sorted.map({
          case city => new CityNode(city)
        })
      }

      class CityNode(cityName: String) extends Node(cityName) {
        val id: String = "city-" + cityName

        override def childrenF: () => collection.Seq[Node] = () => Nil
      }


      val jsTree = new JSTree[Node] {
        override def rootNodes: Seq[Node] = List(new RootNode(data.groupBy(_._1).transform((k, v) => v.groupBy(_._2).transform((k, v) => v.map(_._3)))))
      }

      val onSelect = fsc.callback(
        Js("data.node.id"),
        nodeId => {
          println(s"JsTree Selected Node is: $nodeId")
          JS.consoleLog(s"JsTree Selected Node is: $nodeId")
        },
      )

      jsTree.render().withStyle("height: 600px; overflow: auto;")
        ++ jsTree.init(onSelect = onSelect).onDOMContentLoaded.inScriptTag
    }
    renderCodeSampleAndAutoClosePreviousOne("With menu") {

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      class Node(
                  val titleNs: NodeSeq,
                  val value: Unit,
                  val id: String,
                  val open: Boolean = false,
                  val disabled: Boolean = false,
                  val icon: Option[String] = None,
                ) extends JSTreeNodeWithContextMenu[Node] {
        override def childrenF: () => Seq[Node] = () => Nil

        override def actions: Seq[JSTreeContextMenuAction] = Seq(
          new DefaultJSTreeContextMenuAction(
            label = "Open",
            icon = Some("bi bi-book text-success"),
            action = Some(implicit fsc => JS.alert(s"Open from node: $id")),
          ),
          new DefaultJSTreeContextMenuAction(
            label = "Close",
            icon = Some("bi bi-book text-info"),
            action = Some(implicit fsc => JS.alert(s"Close from node: $id")),
          ),
          new DefaultJSTreeContextMenuAction(
            label = "SubMenu",
            icon = Some("bi bi-book text-warning"),
            separatorAfter = false,
            action = None,
            subactions = Seq(
              new DefaultJSTreeContextMenuAction(
                label = "SubOpen",
                icon = Some("bi bi-book text-success"),
                action = Some(implicit fsc => JS.alert(s"Open in SubMenu from node: $id")),
              ),
              new DefaultJSTreeContextMenuAction(
                label = "SubClose",
                icon = Some("bi bi-book text-info"),
                separatorAfter = false,
                action = Some(implicit fsc => JS.alert(s"Close in SubMenu from node: $id")),
              ),
            ),
          ),
        )
      }

      implicit val jsTree: JSTreeWithContextMenu[Node] = new JSTreeWithContextMenu[Node] {
        override val rootNodes = Seq(new Node(
          span("root"),
          (),
          "root"
        ))
      }
      jsTree.render() ++ jsTree.init().onDOMContentLoaded.inScriptTag
    }
    renderCodeSampleAndAutoClosePreviousOne("Editable", <p><b>NOTE:</b> work in progress</p>) {

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      class Node(
                  val id: String,
                  var title: String,
                  val open: Boolean = false,
                  val disabled: Boolean = false,
                  val icon: Option[String] = None,
                )(implicit jsTree: JSTreeWithContextMenu[Node]) extends EditableJSTreeNode[Node]()(jsTree) {
        override val allowDuplicated: Boolean = false

        override val children: ArrayBuffer[Node] = ArrayBuffer[Node]()

        override def actions: Seq[JSTreeContextMenuAction] = Seq(
          new DefaultCreateAction(
            label = "New",
            icon = Some("bi bi-book text-danger"),
            // business logic and db operations can be here, e.g. insert a new record into database
            onCreate = newId => new Node(newId, newId),
            // business logic and db operations can be here, e.g. update a title field in database
            onEdit = (node, newTitle) => { node.title = newTitle; Js.Void}
          ),

          new DefaultRenameAction(
            label = "Rename",
            icon = Some("bi bi-book text-danger"),
            // business logic and db operations can be here, e.g. update a title field in database
            onEdit = (node, newTitle) => { node.title = newTitle; Js.Void}
          ),

          new DefaultRemoveAction(
            label = "Remove",
            icon = Some("bi bi-book text-danger"),
            // business logic and db operations can be here, e.g. delete a new record in database
            // But Note: the node has already been removed from its parent's children, Don't do it again!
            onRemove = (node, pid) => JS.alert(s"node(${node.id}) removed from $pid")
          ),
        )
      }

      val editableJSTree: JSTreeWithContextMenu[Node] = new JSTreeWithContextMenu[Node] {

        override val rootNodes = Seq(new Node("root", "Example")(this))
      }

      editableJSTree.render() ++ editableJSTree.init().onDOMContentLoaded.inScriptTag
    }
    closeCodeSample()
  }
}
