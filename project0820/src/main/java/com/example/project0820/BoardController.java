package com.example.project0820;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "board-list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board-detail";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("board", new Board());
        return "board-form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Board board) {
        boardService.saveBoard(board);
        return "redirect:/boards";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }
}