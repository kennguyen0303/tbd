package com.example.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller class HtmlController {
    @GetMapping("/")
    fun blog(model: Model): String{
        model["title"] = "Blog"
        return "blog"
    }

    @PostMapping("/")
    fun test(): String{
        return "ok"
    }
}
