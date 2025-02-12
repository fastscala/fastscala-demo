package com.fastscala.demo.docs.jstree

import com.fastscala.components.jstree.{DefaultJSTreeContextMenuAction, JSTree, JSTreeContextMenuAction, JSTreeNode, JSTreeNodeWithContextMenu, JSTreeSimpleNode, JSTreeWithContextMenu}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page
import com.fastscala.demo.testdata.Continents
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, inScriptTag}

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
    renderCodeSampleAndAutoClosePreviousOne("Countries") {

      lazy val data = Source.fromResource("world-cities.csv").getLines().drop(1).map(_.split(",")).collect({
        case Array(name, country, subcountry, geonameid) => (country, subcountry, name)
      }).toVector
      lazy val country2Region2City: Map[String, Map[String, Vector[String]]] = data.groupBy(_._1).transform((k, v) => v.groupBy(_._2).transform((k, v) => v.map(_._3)))

      val jsTree = new JSTree[Unit, JSTreeSimpleNode[Unit]] {
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
        nodeId =>
          println(s"JsTree Selected Node is: $nodeId")
          JS.consoleLog(s"JsTree Selected Node is: $nodeId"),
      )
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      jsTree.render().withStyle("height: 300px; overflow: auto;")
        ++ jsTree.init(onSelect = onSelect).onDOMContentLoaded.inScriptTag
    }
    renderCodeSampleAndAutoClosePreviousOne("With menu") {

      lazy val data = Source.fromResource("world-cities.csv").getLines().drop(1).map(_.split(",")).collect({
        case Array(name, country, subcountry, geonameid) => (country, subcountry, name)
      }).toVector
      lazy val country2Region2City: Map[String, Map[String, Vector[String]]] = data.groupBy(_._1).transform((k, v) => v.groupBy(_._2).transform((k, v) => v.map(_._3)))

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      class Node(
                  var title: String,
                  val value: Unit,
                  val id: String,
                  val open: Boolean = false,
                  val disabled: Boolean = false,
                  val icon: Option[String] = None,
                  val children: ArrayBuffer[Node] = ArrayBuffer.empty
                )(implicit jsTree: JSTreeWithContextMenu[Unit, Node]
                ) extends JSTreeNodeWithContextMenu[Unit, Node] {
        override val allowDuplicated: Boolean = false

        override def actions: Seq[JSTreeContextMenuAction] = Seq(
          DefaultJSTreeContextMenuAction(
            label = "Open",
            icon = Some("bi bi-book text-success"),
            run = implicit fsc => id => JS.alert(s"Open: $id"),
          ),
          DefaultJSTreeContextMenuAction(
            label = "Close",
            icon = Some("bi bi-book text-info"),
            run = implicit fsc => id => JS.alert(s"Close: $id"),
          ),
          DefaultCreateAction(
            label = "Create",
            icon = Some("bi bi-book text-danger"),
            onCreate = id => Node(id, (), id, icon = Some("bi bi-book text-info")),
            onEdit = (node, text) =>
              node.title = text; JS.void,
          ),
          DefaultRenameAction(
            label = "Rename",
            icon = Some("bi bi-book text-danger"),
            onEdit = (node, text) =>
              node.title = text; JS.void,
          ),
          DefaultRemoveAction(
            label = "Remove",
            icon = Some("bi bi-book text-danger"),
            onRemove = (node, pid) =>
              JS.alert(s"remove id: ${node.id}"),
          ),
          DefaultJSTreeContextMenuAction(
            label = "SubMenu",
            icon = Some("bi bi-book text-warning"),
            separatorAfter = false,
            run = fsc => id => JS.void,
            subactions = Seq(
              DefaultJSTreeContextMenuAction(
                label = "SubOpen",
                icon = Some("bi bi-book text-success"),
                run = implicit fsc => id => JS.alert(s"Open From SubMenu: $id"),
              ),
              DefaultJSTreeContextMenuAction(
                label = "SubClose",
                icon = Some("bi bi-book text-info"),
                separatorAfter = false,
                run = implicit fsc => id => JS.alert(s"Close From SubMenu: $id"),
              ),
            ),
          ),
        )
      }

      val jsTree = new JSTreeWithContextMenu[Unit, Node] {
        implicit val jstree: JSTreeWithContextMenu[Unit, Node] = this

        override val rootNodes = Seq(new Node("root", (), "root"))
      }
      jsTree.render() ++ jsTree.init().onDOMContentLoaded.inScriptTag
    }
    closeCodeSample()
  }
}
