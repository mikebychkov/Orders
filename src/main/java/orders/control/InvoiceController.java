package orders.control;

import orders.models.Invoice;
import orders.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @GetMapping("/list")
    public List<Invoice> getList() {
        return service.getList();
    }
}
