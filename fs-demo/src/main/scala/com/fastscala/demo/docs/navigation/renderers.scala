package com.fastscala.demo.docs.navigation

import com.fastscala.core.FSContext

import scala.xml.{Elem, NodeSeq}

trait BSMenuRenderer {
  
  implicit def renderer: this.type = this
  
  def render(elem: BSMenu)(implicit fsc: FSContext): NodeSeq

  def render(elem: BSNav)(implicit fsc: FSContext): Elem

  def render(elem: MenuSection)(implicit fsc: FSContext): NodeSeq

  def render(elem: SimpleMenuItem)(implicit fsc: FSContext): NodeSeq

  def render(elem: RoutingMenuItem)(implicit fsc: FSContext): NodeSeq

  def render(elem: HeaderMenuItem)(implicit fsc: FSContext): NodeSeq
}
