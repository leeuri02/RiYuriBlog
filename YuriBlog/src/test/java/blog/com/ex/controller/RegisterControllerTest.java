package blog.com.ex.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import blog.com.ex.service.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
public class RegisterControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AdminService adminService;
	@BeforeEach
	public void prepareDate() {
		when(adminService.createAdmin("test123","a1234567","test@test.com")).thenReturn(true);
	}
	
	//register画面表示テスト＿成功
	@Test
	public void testGetRegisterPage_Success() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/register");
		mockMvc.perform(request)
		.andExpect(view().name("register.html"));
	}
	
}
