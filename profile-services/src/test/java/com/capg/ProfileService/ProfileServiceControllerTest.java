package com.capg.ProfileService;

import com.capg.ProfileService.Controller.ProfileController;
import com.capg.ProfileService.Model.Profile;
import com.capg.ProfileService.Service.ProfileService;
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

public class ProfileServiceControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    Profile PROF_1 = new Profile(1, "Balaji", "balu@gmail.com", "Vishnu","9909909999",null, "male", null);

    Profile PROF_2 = new Profile(2, "venkatesh", "venki@gmail.com", "sai","9009009999", null, "Male",null);


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void getAllProfiles_Test() throws Exception {
        List<Profile> records = new ArrayList<>(Arrays.asList(PROF_1, PROF_2));

        Mockito.when(profileService.getAllProfiles()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/profile/show")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
              //.andExpect(jsonPath("$[2].emailId",is("venki@gmail.com")));
    }

    @Test
    public void addNewCustomerProfile_Test() throws Exception {
        Profile record = new Profile(2, "venkatesh", "venki@gmail.com", "chaitanya","9009009999", null, "Male",null);
        //Product(9,"kidsSpecial","joggers","Clothing",null,null,null,999,"Its a very good",null);

        Mockito.when(profileService.addNewCustomerProfile(record)).thenReturn(record);

        String content = objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/profile/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.fullName", is("venkatesh")));
    }

    @Test
    public void updateProfile_Test() throws Exception {

        Profile updateRecord = new Profile(3, "padmavati", "padmavati@gmail.com", "99888", "9999999999", null, "female",null);


        //Mockito.when(productService.updateProducts(PRO_1.getProductId())).thenReturn(Optional.ofNullable(PRO_1));
        Mockito.when(profileService.updateProfile(updateRecord, updateRecord.getProfileId())).thenReturn(updateRecord);

        String updateContent = objectWriter.writeValueAsString(updateRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/profile/update/3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.emailId", is("padmavati@gmail.com")));

    }

    @Test
    public void deleteProfileById_Test() throws Exception {
        //Mockito.when(productService.deleteProductsById(PRO_3.getProductId())).thenReturn(Optional.ofNullable(PRO_3));
        willDoNothing().given(profileService).deleteProfileById(PROF_2.getProfileId());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/profile/delete/" + PROF_2.getProfileId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
