package com.fastscala.demo.docs.jstree

import com.fastscala.components.jstree.{DefaultJSTreeContextMenuAction, JSTree, JSTreeContextMenuAction, JSTreeNode, JSTreeNodeWithContextMenu, JSTreeSimpleNode, JSTreeWithContextMenu}
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples2Page
import com.fastscala.demo.testdata.Continents
import com.fastscala.js.Js
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.scala_xml.js.{JS, inScriptTag}

import scala.io.Source
import scala.xml.NodeSeq

class JSTreePage extends MultipleCodeExamples2Page() {

  override def pageTitle: String = "JsTree Example"

  override def append2Head(): NodeSeq = super.append2Head() ++
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />

  override def append2Body(): NodeSeq = super.append2Body() ++
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

  override def renderContentsWithSnippets()(implicit fsc: FSContext): Unit = {
    renderSnippet("Countries") {

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
      jsTree.render() ++ jsTree.init().onDOMContentLoaded.inScriptTag
    }
    renderSnippet("With menu") {

      lazy val data = Source.fromResource("world-cities.csv").getLines().drop(1).map(_.split(",")).collect({
        case Array(name, country, subcountry, geonameid) => (country, subcountry, name)
      }).toVector
      lazy val country2Region2City: Map[String, Map[String, Vector[String]]] = data.groupBy(_._1).transform((k, v) => v.groupBy(_._2).transform((k, v) => v.map(_._3)))

      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

      class Node(
                  val titleNs: NodeSeq,
                  val value: Unit,
                  val id: String,
                  val open: Boolean = false,
                  val disabled: Boolean = false,
                  val icon: Option[String] = None,
                ) extends JSTreeNodeWithContextMenu[Unit, Node] {
        override def childrenF: () => Seq[Node] = () => Nil

        override def actions: Seq[JSTreeContextMenuAction] = Seq(
          new DefaultJSTreeContextMenuAction(
            label = "Open",
            run = implicit fsc => JS.alert("Hello world"),
          )
        )
      }

      val jsTree = new JSTreeWithContextMenu[Unit, Node] {
        override val rootNodes = Seq(new Node(
          span("root"),
          (),
          "root"
        ))
      }
      jsTree.render() ++ jsTree.init().onDOMContentLoaded.inScriptTag
    }
    closeSnippet()
  }
}
