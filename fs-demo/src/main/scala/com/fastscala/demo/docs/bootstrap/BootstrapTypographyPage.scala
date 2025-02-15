package com.fastscala.demo.docs.bootstrap

import com.fastscala.core.FSContext
import com.fastscala.demo.docs.MultipleCodeExamples3Page

class BootstrapTypographyPage extends MultipleCodeExamples3Page() {

  override def pageTitle: String = "Bootstrap Typography"

  import com.fastscala.components.bootstrap5.helpers.BSHelpers.*

  override def renderAllCodeSamples()(implicit fsc: FSContext): Unit = {
    renderCodeSampleAndAutoClosePreviousOne("Headings") {
      h1.apply("h1 heading") ++
        h2.apply("h2 heading") ++
        h3.apply("h3 heading") ++
        h4.apply("h4 heading") ++
        h5.apply("h5 heading") ++
        h6.apply("h6 heading")
    }
    renderCodeSampleAndAutoClosePreviousOne("Display headings") {
      display_1.apply("Display 1") ++
        display_2.apply("Display 2") ++
        display_3.apply("Display 3") ++
        display_4.apply("Display 4") ++
        display_5.apply("Display 5") ++
        display_6.apply("Display 6")
    }
    renderCodeSampleAndAutoClosePreviousOne("Lead") {
      lead.apply("Lead paragraph example")
    }
    renderCodeSampleAndAutoClosePreviousOne("Inline text elements") {
      <p>{mark.apply("Lorem ipsum")} dolor sit amet, consectetur adipiscing elit. Nulla luctus magna at lorem faucibus, vel condimentum lacus imperdiet.</p> ++
        p.apply(del.apply("del tag example")) ++
        p.apply(s.apply("s tag example")) ++
        p.apply(ins.apply("ins tag example")) ++
        p.apply(u.apply("u tag example")) ++
        p.apply(small.apply("small tag example")) ++
        p.apply(strong.apply("strong tag example")) ++
        p.apply(em.apply("em tag example"))
    }
    renderCodeSampleAndAutoClosePreviousOne("Abbreviations") {
      p.apply(abbr.apply("WWW").withTitle("World Wide Web"))
    }
    renderCodeSampleAndAutoClosePreviousOne("Blockquotes") {
      blockquote.apply(p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a porttitor elit, eu viverra nibh.")) ++
        blockquote.apply(p.apply("Centered blockquote")).text_center ++
        blockquote.apply(p.apply("Aligned to the right blockquote")).text_end
    }
    renderCodeSampleAndAutoClosePreviousOne("Blockquotes") {
      blockquote.apply(p.apply("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a porttitor elit, eu viverra nibh.")) ++
        blockquote.apply(p.apply("Centered blockquote")).text_center ++
        blockquote.apply(p.apply("Aligned to the right blockquote")).text_end
    }
    renderCodeSampleAndAutoClosePreviousOne("Lists") {
      ul.list_unstyled.apply {
        li.apply("Item 1") ++
          li.apply("Item 2") ++
          li.apply("Item 3") ++
          li.apply("Item 4") ++
          li.apply("Item 5") ++
          li.apply(p.apply("Nested list:") ++
            ul.apply {
              li.apply("Item 1") ++
                li.apply("Item 2") ++
                li.apply("Item 3") ++
                li.apply("Item 4") ++
                li.apply("Item 5")
            }
          )
      }
    }
    closeCodeSample()
  }
}
