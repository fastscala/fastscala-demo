package com.fastscala.demo.docs.navigation

import com.fastscala.core.FSContext
import com.fastscala.scala_xml.ScalaXmlElemUtils.RichElem
import com.fastscala.utils.IdGen

import scala.xml.{Elem, NodeSeq}

implicit val BSMenuRenderer2: BSMenuRenderer = new BSMenuRenderer {

  def render(elem: BSMenu)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = {
    <nav class="bd-links w-100">
      <ul class="bd-links-nav list-unstyled mb-0 pb-3 pb-md-2 pe-lg-2">
        {elem.items.map(_.render())}
      </ul>
    </nav>
  }

  def render(elem: MenuSection)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = {
    val isOpen = elem.items.exists(_.matches(fsc.page.req.getHttpURI.getPath))
    val id = IdGen.id
    <li class="bd-links-group py-2">
        <strong class="bd-links-heading d-flex w-100 align-items-center fw-semibold">
            {elem.icon.map(_.withClass("bi me-2 mb-1")).getOrElse(NodeSeq.Empty)}
            {elem.name}
        </strong>
        <ul class="list-unstyled fw-normal pb-2 small">
            {elem.items.map(_.render())}
        </ul>
    </li>
  }

  def render(elem: SimpleMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = {
    val isOpen = elem.matches(fsc.page.req.getHttpURI.getPath)
    <li>
      {
      <a href={elem.href} class="bd-links-link d-inline-block rounded">{elem.name}</a>
        .withClassIf(isOpen, "active")
        .withAttrIf(isOpen, "aria-current" -> "page")
      }
    </li>
  }

  def render(elem: RoutingMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = {
    val isOpen = elem.matches(fsc.page.req.getHttpURI.getPath)
    <li>
      {
      <a href={elem.href} class="bd-links-link d-inline-block rounded">{elem.name}</a>
        .withClassIf(isOpen, "active")
        .withAttrIf(isOpen, "aria-current" -> "page")
      }
    </li>
  }

  override def render(elem: BSNav)(implicit fsc: FSContext, renderer: BSMenuRenderer): Elem = ???

  override def render(elem: HeaderMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = ???
}
