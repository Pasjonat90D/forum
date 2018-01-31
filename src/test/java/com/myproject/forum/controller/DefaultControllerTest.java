package com.myproject.forum.controller;


import com.myproject.forum.models.Category;
import com.myproject.forum.service.CategoriesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class DefaultControllerTest {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .dispatchOptions(true)
                .build();
    }

    @Test
    @WithMockUser
    public void testHome_NameOfTheReturnViewer() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("/home"));
    }

    @Test
    @WithMockUser
    public void testLogin_NameOfTheReturnViewer() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    @WithMockUser
    public void testError403_NameOfTheReturnViewer() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("/error/403"));
    }

    @Test
    @WithMockUser
    public void testIndex_NameOfTheReturnViewer()throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/home"));
    }

    @Test
    @WithMockUser
    public void testIndex_CategoriesFromTheAttributeModel()throws Exception{
        Category testCategory = new Category("name","description");
        categoriesService.saveCategory(testCategory);
        int categoryListSize = categoriesService.getAllCategories().size();
        categoriesService = Mockito.spy(categoriesService);
        mockMvc.perform(get("/"))
                .andExpect(model().attribute("categories",hasSize(categoryListSize)))
                .andExpect(model().attribute("categories", hasItem(  allOf(
                        hasProperty("name", is(testCategory.getName())),
                        hasProperty("description", is(testCategory.getDescription()))
                ))));
        verifyNoMoreInteractions(categoriesService);
        categoriesService.deleteCategory(testCategory.getName());
    }
}
