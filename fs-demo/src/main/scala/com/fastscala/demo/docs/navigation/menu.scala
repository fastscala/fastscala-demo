package com.fastscala.demo.docs.navigation

import com.fastscala.core.{FSContext, FSSession}
import com.fastscala.routing.req.Get
import com.fastscala.scala_xml.utils.{FSPageImplWithFSContext}
import com.fastscala.utils.IdGen
import org.eclipse.jetty.server.Request

import scala.xml.{Elem, NodeSeq}

case class BSMenu(items: MenuItem*) extends MenuItem {
  def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = renderer.render(this)

  def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext] =
    items.map(_.serve()).find(_.isDefined).flatten

  override def matches(uri: String): Boolean = items.exists(_.matches(uri))
}

case class BSNav(items: MenuItem*) extends MenuItem {

  val navBarId = IdGen.id("navBar")

  def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = renderer.render(this)

  def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext] =
    items.map(_.serve()).find(_.isDefined).flatten

  override def matches(uri: String): Boolean = items.exists(_.matches(uri))
}

trait MenuItem {
  def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq

  def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext]

  def matches(uri: String): Boolean
}

object MenuSection {
  def apply(name: String, icon: Elem)(items: MenuItem*) = new MenuSection(name, Some(icon))(items: _*)

  def apply(name: String)(items: MenuItem*) = new MenuSection(name, None)(items: _*)
}

case class MenuSection(name: String, icon: Option[Elem])(val items: MenuItem*) extends MenuItem {

  def matches(uri: String): Boolean = items.exists(_.matches(uri))

  override def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = renderer.render(this)

  override def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext] =
    items.map(_.serve()).find(_.isDefined).flatten
}

case class SimpleMenuItem(name: String, href: String) extends MenuItem {

  def matches(uri: String): Boolean = href == uri

  def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext] = None

  def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = renderer.render(this)
}

class RoutingMenuItem(matching: String*)(val name: String, page: () => FSPageImplWithFSContext) extends MenuItem {

  def matches(uri: String): Boolean = href == uri

  def href: String = matching.mkString("/", "/", "")

  def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = renderer.render(this)

  def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext] = Some(req).collect {
    case Get(path@_*) if path == matching => page()
  }

  override def toString: String = s"RoutingMenuItem(${matching.mkString("/", "/", "")})($name)"
}

class HeaderMenuItem(val title: String) extends MenuItem {
  override def render()(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = renderer.render(this)

  override def serve()(implicit req: Request, session: FSSession): Option[FSPageImplWithFSContext] = None

  override def matches(uri: String): Boolean = false
}

