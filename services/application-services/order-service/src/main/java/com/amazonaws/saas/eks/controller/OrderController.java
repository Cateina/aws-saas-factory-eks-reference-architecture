/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.amazonaws.saas.eks.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.saas.eks.model.Order;
import com.amazonaws.saas.eks.repository.OrderRepository;
import com.amazonaws.saas.eks.service.OrderService;
import com.amazonaws.saas.eks.service.OrderServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrderController {
	private static final Logger logger = LogManager.getLogger(OrderController.class);
	
	@Autowired
    private OrderService orderService;

    
    @GetMapping(value="{tenantId}/order/api/orders", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Order> getOrders(@PathVariable("tenantId") String tenantId) {
    	logger.info("Return orders");

        return orderService.getOrders(tenantId);
    }

    @GetMapping(value = "{tenantId}/order/api/order/{orderId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Order getOrderById(@PathVariable("tenantId") String tenantId, @PathVariable("orderId") String orderId) {

		return orderService.getOrderById(orderId, tenantId);
	}
        
    @PostMapping(value="{tenantId}/order/api/order", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Order saveOrder(@PathVariable("tenantId") String tenantId, @RequestBody Order order) {
        return orderService.save(order, tenantId);
    }

    @RequestMapping("{tenantId}/order/health/order")
    public String health() {
        return "\"Order service is up!\"";
    }

}