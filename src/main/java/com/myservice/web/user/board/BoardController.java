package com.myservice.web.user.board;

import com.myservice.domain.board.Board;
import com.myservice.domain.board.BoardService;
import com.myservice.domain.member.Member;
import com.myservice.web.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "user/board/boards";
    }

    @GetMapping("/add")
    public String boardForm(@ModelAttribute("form") BoardForm form) {
        return "user/board/boardForm";
    }

    @PostMapping("/add")
    public String addBoard(@Validated @ModelAttribute("form") BoardForm form, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "user/board/boardForm";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Board board = Board.createBoard(form.getTitle(), form.getContent(), user);

        boardService.save(board);

        redirectAttributes.addAttribute("boardId", board.getId());

        return "redirect:/user/board/{boardId}";
    }

    @GetMapping("/{boardId}")
    public String content(@PathVariable Long boardId, Model model) {
        Board board = boardService.findOne(boardId);
        model.addAttribute("board", board);
        return "user/board/board";
    }
}
