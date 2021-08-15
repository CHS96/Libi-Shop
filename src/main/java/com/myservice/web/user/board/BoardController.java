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

    private final String VIEW_PATH = "user/board/";

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return VIEW_PATH + "boards";
    }

    @GetMapping("/{boardId}")
    public String content(@PathVariable Long boardId, Model model) {
        Board board = boardService.findOne(boardId, true);
        model.addAttribute("board", board);
        return VIEW_PATH + "board";
    }

    @GetMapping("/userBoards")
    public String getUserBoards(HttpSession session, RedirectAttributes redirectAttributes) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        redirectAttributes.addAttribute("userId", user.getId());
        return "redirect:/user/board/{userId}/boards";
    }

    @GetMapping("/{userId}/boards")
    public String userBoards(@PathVariable Long userId, HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        List<Board> boards = boardService.findAllOfUser(user);
        model.addAttribute("boards", boards);

        return VIEW_PATH + "boardsOfUser";
    }

    @GetMapping("/add")
    public String boardForm(@ModelAttribute("form") BoardForm form) {
        return "user/board/boardForm";
    }

    @PostMapping("/add")
    public String addBoard(@Validated @ModelAttribute("form") BoardForm form, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return VIEW_PATH + "boardForm";
        }

        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Board board = Board.createBoard(form.getTitle(), form.getContent(), user);

        boardService.save(board);

        redirectAttributes.addAttribute("boardId", board.getId());
        return "redirect:/user/board/{boardId}";
    }

    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardService.findOne(boardId, false);
        model.addAttribute("board", board);
        return VIEW_PATH + "updateForm";
    }

    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable Long boardId, @Validated @ModelAttribute("board") BoardUpdateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEW_PATH + "updateForm";
        }
        boardService.update(boardId, form);
        return "redirect:/user/board/userBoards";
    }

    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId, HttpSession session, Model model) {
        Board board = boardService.findOne(boardId, false);
        boardService.delete(board);
        return "redirect:/user/board/userBoards";
    }
}
