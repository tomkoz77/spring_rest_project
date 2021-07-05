package com.example.simplelibrary.core.checkout;

import com.example.simplelibrary.core.checkout.web.CheckoutBaseReq;
import com.example.simplelibrary.core.checkout.web.CheckoutView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/checkouts")
public class CheckoutController {

    private final CheckoutService service;

    public CheckoutController(final CheckoutService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CheckoutView getCheckout(@PathVariable Long id) {
        return service.getCheckout(id);
    }

    @GetMapping
    @ResponseBody
    public Page<CheckoutView> getAllCheckouts(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllCheckout(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CheckoutView create(@RequestBody @Valid CheckoutBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CheckoutView updateBook(@PathVariable(name = "id") Long id,
                                   @RequestBody @Valid CheckoutBaseReq req){
        Checkout checkout = service.findCheckoutOrThrow(id);
        return service.update(checkout, req);
    }
}