package com.myapp.myapp.web;

import java.util.List;

import com.myapp.myapp.dao.InvoiceRepository;
import com.myapp.myapp.entities.Invoice;
import com.myapp.myapp.exception.InvoiceNotFoundException;
import com.myapp.myapp.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/invoice")
@Controller
public class InvoiceController {
    
    @Autowired
    private IInvoiceService service;

    @Autowired
    private InvoiceRepository repo;

    @GetMapping(path = "/homepage")
    public String showHomePage(){
        return "homePage";
    }

    @GetMapping(path ="/homepage/register")
    public String showRegistration(){
        return "registerInvoicePage";
    }

    @PostMapping(path ="/save")
    public String saveInvoice(@ModelAttribute Invoice invoice, Model model, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "registerInvoicePage";
        service.saveInvoice(invoice);
        Long id = service.saveInvoice(invoice).getId();
        String message = "Record with id: " + id + "is save successfully";
        model.addAttribute("message", message);
        return "registerInvoicePage";
    }

    @GetMapping(path ="/getAllInvoices")
    public String getAllInvoices( 
        @RequestParam(value="message", required = false) String message,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size,
        @RequestParam(name = "keyword", defaultValue = "") String mc,   
        Model model
        ){
        Page<Invoice> invoices =  repo.findByNameContains(mc, PageRequest.of(page, size));
        model.addAttribute("list", invoices.getContent());
        model.addAttribute("message", message);
        model.addAttribute("keyword", mc);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[invoices.getTotalPages()]);
        return "allInvoicePage";
    }

    @GetMapping(path ="/editInvoice")
    public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long id) {
       String page = null; 
       try {
            Invoice invoice = service.getInvoiceById(id);
            model.addAttribute("invoice", invoice);
            page="editInvoicePage";
       } catch (InvoiceNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:getAllInvoices";
       }
       return page; 
    }

    @PostMapping(path ="/updateInvoice")
    public String updatePage(@ModelAttribute Invoice invoice, RedirectAttributes attributes){
        service.updateInvoice(invoice);
        Long id = invoice.getId();
        attributes.addAttribute("messge", "Invoice with " + id + "was successfully update");
        return "redirect:getAllInvoices";
    }

    @GetMapping(path = "/deleteInvoice")
    public String deleteInvoice(RedirectAttributes attributes,  @RequestParam Long id){
        try{
            service.deleteInvoiceById(id);
            attributes.addAttribute("message", "Invoice with " + id+ "deleted successfully");
        } catch (InvoiceNotFoundException e){
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }

        return "redirect:getAllInvoices";
    }

}
