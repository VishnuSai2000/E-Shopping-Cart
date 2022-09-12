package com.scart.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.scart.productservice.controller.ProductController;
import com.scart.productservice.model.Product;
import com.scart.productservice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    Product PRO_1 = new Product(1,"household","ApplePhone","Electronics",null,null,null,90000,"Its a very gud mobile",null);
    Product PRO_2 = new Product(2,"office","Table ","Furniture",null,null,null,9000,"Hard wood and good ",null);
    Product PRO_3 = new Product(3,"skincare","sunscreen","cosmetics",null,null,null,900,"Useful in sun",null);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    @Test
    public void getAllProducts_success() throws Exception {
        List<Product> records = new ArrayList<>(Arrays.asList(PRO_1, PRO_2, PRO_3));

        Mockito.when(productService.getAllProducts()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/view")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
        // .andExpect(jsonPath("$[2].ProductType",is("skincare")));
    }
    @Test
    public void addProducts() throws Exception {
            Product record =   Product.builder().productId(9)
                    .productType("joggers")
                    .productName("KidsSpecial")
                    .category("clothing")
                    .price(900)
                    .description("this is of good quality")
                    .build();
                    //Product(9,"kidsSpecial","joggers","Clothing",null,null,null,999,"Its a very good",null);

            Mockito.when(productService.addProducts(record)).thenReturn(record);

            String content = objectWriter.writeValueAsString(record);

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/product/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(content);
            mockMvc.perform(mockRequest)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$",notNullValue()))
                    .andExpect(jsonPath("$.productType", is("joggers")));
    }
    @Test
    public void updateProduct_success() throws Exception{
        Product updateRecord = new Product(1,"updated Type","update Name","updates categore",null,null,null,989,"Updated description",null);

        //Mockito.when(productService.updateProducts(PRO_1.getProductId())).thenReturn(Optional.ofNullable(PRO_1));
        Mockito.when(productService.updateProducts(updateRecord, updateRecord.getProductId())).thenReturn(updateRecord);

        String updateContent = objectWriter.writeValueAsString(updateRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/product/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.productType", is("updated Type")));

    }

    @Test
    public void deleteProduct_success() throws  Exception {
        //Mockito.when(productService.deleteProductsById(PRO_3.getProductId())).thenReturn(Optional.ofNullable(PRO_3));
         willDoNothing().given(productService).deleteProductsById(PRO_3.getProductId());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/product/delete/" + PRO_3.getProductId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}



