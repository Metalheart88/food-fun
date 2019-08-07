package service;

import domain.Customer;
import exception.EntityNotFoundException;
import service.AuthenticationService;
import repository.CustomerRepository;
import exception.ValidationException;

import java.util.List;
import java.util.regex.Pattern;

public class CustomerService {

    private CustomerRepository customerRepository;

    private AuthenticationService authenticationService;

    private Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public CustomerService() {
        customerRepository = new CustomerRepository();
        authenticationService = new AuthenticationService();
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public CustomerService(CustomerRepository customerRepository, AuthenticationService authenticationService) {
        this.customerRepository = customerRepository;
        this.authenticationService = authenticationService;
    }

    private void validate(Customer customer) {
        if (isEmailInvalid(customer.getEmail())) {
            throw new ValidationException("The email is invalid");
        }
        if (isDuplicatedEmail(customer)) {
            throw new ValidationException("There is another user with the same email!");
        }
    }

    public boolean isEmailInvalid(final String email) {
        return !pattern.matcher(email).matches();
    }

    private boolean isDuplicatedEmail(Customer customer) {
        String email = customer.getEmail();
        Customer storedCustomer = customerRepository.getByEmail(email);

        if (storedCustomer == null) {
            return false;
        }

        String storedEmail = storedCustomer.getEmail();
        return storedEmail.equalsIgnoreCase(email);
    }

    public void add(Customer customer) {
        validate(customer);
        customer.setPassword(authenticationService.generateSecurePassword(customer.getPassword()));
        customerRepository.add(customer);
    }

    public void update(Customer customer) {
        Customer customerDB = getById(customer.getId());
        isEmailInvalid(customer.getEmail());
        customer.setPassword(customerDB.getPassword());
        customerRepository.update(customer);
    }

    public void delete(String id) {
        customerRepository.delete(getById(id));
    }

    public Customer getByEmail(String email) {
        Customer emailDB = customerRepository.getByEmail(email);

        if (emailDB != null) {
            return customerRepository.getByEmail(email);
        }
        else {
            throw new EntityNotFoundException("The user doesn't exist.");
        }
    }

    public Customer getById(String id) {
        Customer idDB = customerRepository.getById(id);
        if (idDB != null) {
            return customerRepository.getById(id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " was not found!");
        }
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

}
