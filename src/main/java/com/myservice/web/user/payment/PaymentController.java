package com.myservice.web.user.payment;

import com.myservice.domain.cartline.CartLine;
import com.myservice.domain.cartline.CartLineService;
import com.myservice.domain.member.Member;
import com.myservice.domain.payment.Payment;
import com.myservice.domain.payment.PaymentService;
import com.myservice.web.session.SessionConst;
import com.myservice.web.user.cart.CartForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.myservice.web.user.cart.CartForm.createCartForms;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final CartLineService cartLineService;

    @GetMapping
    public String checkPayment(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        List<CartLine> allCartLine = cartLineService.findAllCartLine(user);

        List<CartForm> items = createCartForms(allCartLine);
        int totalPrice = user.getCart().getTotalPrice();

        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return "user/items/payment";
    }

    @GetMapping("/complete")
    public String complete(HttpSession session, Model model) {
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //clear CartLines of User
        int price = user.getCart().getTotalPrice();
        cartLineService.paymentCartLine(user);
        user.getCart().getCartLines().clear();

        //save Payment of User
        Payment payment = Payment.createPayment(user.getCart(), price);
        paymentService.save(payment);

        return "redirect:/user/cart";
    }
}
