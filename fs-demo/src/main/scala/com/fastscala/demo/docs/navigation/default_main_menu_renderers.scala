package com.fastscala.demo.docs.navigation

import com.fastscala.core.FSContext
import com.fastscala.utils.IdGen

import scala.xml.{Elem, NodeSeq}

object DefaultBSMenuRenderer {
  implicit val renderer: BSMenuRenderer = new DefaultBSMenuRenderer {}
}

trait DefaultBSMenuRenderer extends BSMenuRenderer {
  override def render(elem: BSNav)(implicit fsc: FSContext, renderer: BSMenuRenderer): Elem = ???

  def render(elem: BSMenu)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = {
    <div class="position-sticky p-3 sidebar-sticky">
      <ul class="list-unstyled ps-0">
        {elem.items.map(_.render())}
      </ul>
    </div>
  }

  def render(elem: MenuSection)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = {
    val isOpen = elem.items.exists(_.matches(fsc.page.req.getHttpURI.getPath))
    val id = IdGen.id
    <li class="mb-1">
      <button class={"text-white btn bi btn-toggle d-inline-flex align-items-center rounded border-0" + (if (isOpen) "" else " collapsed")} data-bs-toggle="collapse" data-bs-target={s"#$id"} aria-expanded={isOpen.toString}>
        {elem.name}
      </button>
      <div class={"collapse" + (if (isOpen) " show" else "")} id={id}>
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          {elem.items.map(_.render())}
        </ul>
      </div>
    </li>
  }

  def render(elem: SimpleMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = <li><a href={elem.href} class="text-white d-inline-flex text-decoration-none rounded">{elem.name}</a></li>

  def render(elem: RoutingMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = <li><a href={elem.href} class="text-white d-inline-flex text-decoration-none rounded">{elem.name}</a></li>

  def render(elem: HeaderMenuItem)(implicit fsc: FSContext, renderer: BSMenuRenderer): NodeSeq = <li class="mt-3"><span class="menu-heading fw-bold text-uppercase fs-7 ">{elem.title}</span></li>
}
