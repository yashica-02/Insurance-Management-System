package edu.iit.sat.itmd4515.ysharma7.converter;

import edu.iit.sat.itmd4515.ysharma7.domain.Customer;
import edu.iit.sat.itmd4515.ysharma7.service.CustomerService;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 * Converts Customer entities to and from String representations in JSF.  
 * @author yashica
 */
@FacesConverter(value = "customerConverter", forClass = Customer.class, managed = true)
public class CustomerConverter implements Converter<Customer> {

    @EJB
    private CustomerService customerSvc;

    @Override
    public Customer getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        if (customerSvc == null) {
            customerSvc = CDI.current().select(CustomerService.class).get();
        }
        return customerSvc.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Customer customer) {
        if (customer == null || customer.getCustomerId() == 0L) {
            return "";
        }
        return String.valueOf(customer.getCustomerId());
    }
}
