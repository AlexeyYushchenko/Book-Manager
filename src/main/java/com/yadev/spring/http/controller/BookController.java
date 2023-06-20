package com.yadev.spring.http.controller;

import com.yadev.spring.dto.AuthorCreateEditDto;
import com.yadev.spring.dto.AuthorReadDto;
import com.yadev.spring.dto.BookCreateEditDto;
import com.yadev.spring.service.AuthorService;
import com.yadev.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView){
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        return bookService.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    model.addAttribute("books", bookService.findAll());
                    model.addAttribute("authors", authorService.findAll());
                    return "book/book";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated BookCreateEditDto book,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         AuthorCreateEditDto authorDto) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("book", book);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/books";
        }
        bookService.create(book);
        return "redirect:/books";
    }

    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated BookCreateEditDto dto) {
        return bookService.update(id, dto)
                .map(it -> "redirect:/books")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!bookService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/books";
    }

}
