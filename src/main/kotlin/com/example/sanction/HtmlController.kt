package com.example.sanction

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

  @GetMapping("/")
  fun form(): String {
    return "form"
  }

}