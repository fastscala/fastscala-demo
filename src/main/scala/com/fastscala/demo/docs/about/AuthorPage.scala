package com.fastscala.demo.docs.about

import com.fastscala.components.bootstrap5.utils.BSBtn
import com.fastscala.core.FSContext
import com.fastscala.demo.docs.{BasePage, MultipleCodeExamples3Page}

import scala.xml.NodeSeq

class AuthorPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Author"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderHtmlAndAutoClosePreviousCodeSampleWithTitle(
      "Framework Author"
    ) {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      row.apply {
        col_md_4.text_center.apply {
        <img src="/static/images/david_antunes2.png" class="bd-placeholder-img img-thumbnail" width="200px" height="200px"></img>
      } ++
          col_md_8.apply {
          <p>
            FastScala is developed by me, David Antunes.
          </p>
          <p>
            I started programming with Scala on 2012 and have been working both on startups as well as big companies developing in Scala.
          </p>
          <p>
            I'm also the founder of <a href="https://www.scala-academy.com/">ScalaAcademy.com</a> which teaches Scala to developers all around the globe.
          </p>
          <p>
            If you'd like to get in contact you can find me on LinkedIn at <a href="https://www.linkedin.com/in/david-miguel-antunes/">https://www.linkedin.com/in/david-miguel-antunes/</a> or you can drop me an email to: {<pre>david at fastscala.com</pre>.d_inline}.
          </p>
        }
      }
    }
    renderHtmlAndAutoClosePreviousCodeSampleWithTitle(
      "Contributors"
    ) {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      row.apply {
        col_md_4.text_center.apply {
        <img src="/static/images/kevin.png" class="bd-placeholder-img img-thumbnail" width="200px" height="200px"></img>
      } ++
          col_md_8.apply {
            <p>
              Special thanks to Kevin Hsu! our first contributor. He's a Scala hobbyist from Shanghai.
            </p>
            <p>
              He develops a high-performance, high-throughput data extraction and analysis framework for banks to provide support for credit card anti-fraud business.
            </p>
            <p>
              You can find him on GitHub at: <a href="https://github.com/mizerthilez">https://github.com/mizerthilez</a>
            </p>
        }
      }
    }
  }
}
