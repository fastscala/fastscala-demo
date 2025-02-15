package com.fastscala.demo.docs.html

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page

class HtmlTagsPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "HTML tags"

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {

    renderCodeSampleAndAutoClosePreviousOne("Creating div tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      div.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating span tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      span.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating pre tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      pre.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating s tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      s.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating u tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      u.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating small tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      small.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating strong tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      strong.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating em tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      em.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating b tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      b.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating p tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating h1 tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      h1.apply("h1 element")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating h2 tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      h2.apply("h2 element")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating h3 tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      h3.apply("h3 element")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating h4 tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      h4.apply("h4 element")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating h5 tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      h5.apply("h5 element")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating h6 tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      h6.apply("h6 element")
    }
    renderCodeSampleAndAutoClosePreviousOne("Creating hr tags") {
      import com.fastscala.components.bootstrap5.helpers.BSHelpers.*
      hr
    }
    closeCodeSample()
  }
}
