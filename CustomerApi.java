package com.design.ovm.catalog.api;

import com.design.ovm.catalog.model.Customer;
import com.design.ovm.catalog.model.DeviceInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "Customer API", tags = {"Customer"}, description = "Customer API")
@RequestMapping(value = "/ovm/catalog")
public interface CustomerApi {

    @ApiOperation(value = "Gets all Customers",
            notes = "Returns all Customers from db",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the list of Categories", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/all",
            method= RequestMethod.GET)
    ResponseEntity<List<Customer>> getAllCustomers();

/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Creates new Customer",
            notes = "Creates a new Customer",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Details", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/add",
            method= RequestMethod.POST)
    ResponseEntity<Customer> createCustomer(@ApiParam(value = "",required = true) @RequestBody Customer customer);
/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Creates a set of Customers",
            notes = "Creates a set of Customers",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Details", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/add/batch",
            method= RequestMethod.POST)
    ResponseEntity<List<Customer>> createCustomerBatch(@ApiParam(value = "",required = true) @RequestBody List<Customer> customers);

/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Edit an existing Customer",
            notes = "Edit an existing Customer",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Details", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/{id}",
            method= RequestMethod.PUT)
    ResponseEntity<Customer> editCustomer(@ApiParam(value = "",required = true) @PathVariable("id") long customer_id,
                                          @ApiParam(value = "",required = true) @RequestBody Customer customer);

/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Delete an existing Customer",
            notes = "Delete an existing Customer",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Deleted Successfully", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/{id}",
            method= RequestMethod.DELETE)
    ResponseEntity<String> deleteCustomer(@ApiParam(value = "",required = true) @PathVariable("id") long customer_id  );

/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Gets an existing Customer",
            notes = "Gets an existing Customer",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Details", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/{id}",
            method= RequestMethod.GET)
    ResponseEntity<Customer> getCustomer(@ApiParam(value = "",required = true) @PathVariable("id") long customer_id);

/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Gets an existing Customer",
            notes = "Gets an existing Customer",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Details", response = Customer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/email/{email}",
            method= RequestMethod.GET)
    ResponseEntity<Customer> getCustomerbyEmail(@ApiParam(value = "",required = true) @PathVariable("email") String email);

/////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "Register device info",
            notes = "Register device info",
            response = Customer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "DeviceInfo Details", response = DeviceInfo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})

    @RequestMapping(value="/customer/token/",
            method= RequestMethod.POST)
    ResponseEntity<?> registerToken(@ApiParam(value = "",required = true) @RequestBody DeviceInfo deviceInfo);


}
