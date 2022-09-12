package com.capg.CartService;

import com.capg.CartService.controller.CartController;
import com.capg.CartService.model.Cart;
import com.capg.CartService.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    Cart CART_1 = new Cart(1,0,null);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }
    @Test
    public void getAllCarts_Test() throws Exception {
        List<Cart> records = new ArrayList<>(Arrays.asList((CART_1)));

        Mockito.when(cartService.getAllCarts()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cart/getAllCarts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }
    @Test
    public void addCart_Test() throws Exception {
        Cart record = new Cart(2,0,null);

        //Product(9,"kidsSpecial","joggers","Clothing",null,null,null,999,"Its a very good",null);

        Mockito.when(cartService.addCart(record)).thenReturn(record);

        String content = objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/cart/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }


}


















/*
    @Test
    public void deleteProduct_success() throws  Exception {
        //Mockito.when(productService.deleteProductsById(PRO_3.getProductId())).thenReturn(Optional.ofNullable(PRO_3));
        willDoNothing().given(productService).deleteProductsById(PRO_3.getProductId());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/product/delete/" + PRO_3.getProductId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());

    @Test
    public void updateCart_Test() throws Exception{
        Cart cart = new Cart(1,0,null);

        //Mockito.when(productService.updateProducts(PRO_1.getProductId())).thenReturn(Optional.ofNullable(PRO_1));
        Mockito.when(cartService.updateCart(cart.getCartId(),cart )).thenReturn(cart);

        String updateContent = objectWriter.writeValueAsString(cart);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/cart/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
        // .andExpect(jsonPath("$.productType", is("updated Type")));

    }
*/