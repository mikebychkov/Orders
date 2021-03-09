package orders.services;

import orders.models.Customer;
import orders.store.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo repo;

    public List<Customer> getList() {
        return repo.findAll();
    }

    public Customer getById(Integer id) throws Exception {
        Optional<Customer> op = ServiceHelper.checkId(id, repo, "Customer");
        return op.get();
    }

    public Customer addCustomer(Customer customer) {
        return repo.save(customer);
    }
}
