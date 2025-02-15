package com.fastscala.demo.docs.navigation

import com.fastscala.core.FSContext

import scala.xml.{Elem, NodeSeq}

trait BSMenuRenderer {
  def render(elem: BSMenu)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq

  def render(elem: BSNav)(implicit fsc: FSContext, renderer: BSMenuRenderer): Elem

  def render(elem: MenuSection)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq

  def render(elem: SimpleMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq

  def render(elem: RoutingMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq

  def render(elem: HeaderMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq

}
