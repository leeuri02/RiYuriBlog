package blog.com.ex.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.com.ex.model.entity.AdminEntity;
import blog.com.ex.service.AdminService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AdminService adminService;
	@BeforeEach
	public void prepareDate() {
		AdminEntity adminEntity = new AdminEntity(
				"test123","a1234567","test@test.com");
		///正しいユーザー名(test123)&正しいパスワード(a1234567)
		when(adminService.checkLogin(eq("test123"),eq("a1234567"))).thenReturn(adminEntity);
		//間違ったユーザー名(tesx2)&正しいパスワード(a1234567)
		when(adminService.checkLogin(eq("testx2"),eq("a1234567"))).thenReturn(null);
		//ユーザー名が空白で正しいパスワード(a1234567)
		when(adminService.checkLogin(eq(""),eq("a1234567"))).thenReturn(null);
		//正しいユーザー名(test123)&間違ったパスワード(b1234567)
		when(adminService.checkLogin(eq("test123"),eq("b1234567"))).thenReturn(null);
		//正しいユーザー名(test123)&パスワードが空白
		when(adminService.checkLogin(eq("test123"),eq(""))).thenReturn(null);
		//ユーザー名とパスワードが空白
		when(adminService.checkLogin(eq(""),eq(""))).thenReturn(null);
		//間違ったユーザー名(tesx2)&間違ったパスワード(b1234567)
		when(adminService.checkLogin(eq("testx2"),eq("b1234567"))).thenReturn(null);
				
	}
	
	//login画面表示テスト＿成功
	@Test
	public void testGetLoginPage_Success() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/login");
		mockMvc.perform(request)
		.andExpect(view().name("login.html"));
	}
	//正しいユーザー名(test123)&正しいパスワード(a1234567)でログインボタンを押下
	//homeページに遷移
	@Test
	public void testLogin_displayHomePage_Success() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "test123")
				.param("password", "a1234567");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/home")).andReturn();
		HttpSession session = result.getRequest().getSession();
	    AdminEntity userList = (AdminEntity) session.getAttribute("admin");
	    assertNotNull(userList);
	    assertEquals("test123",userList.getUsername());
	    assertEquals("a1234567",userList.getPassword());
	    assertEquals("test@test.com",userList.getEmail());
	}
	//間違ったユーザー名(tesx2)&正しいパスワード(a1234567)でログインボタンを押下
	//画面遷移せずログイン画面に留まる
	@Test
	public void testLogin_WrongUsername_UnSuccess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "testx2")
				.param("password", "a1234567");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
	}
	//ユーザー名が空白で正しいパスワード(a1234567)でログインボタンを押下
	//画面遷移せずログイン画面に留まる
	@Test
	public void testLogin_NullUsername_UnSuccess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "")
				.param("password", "a1234567");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
	}
	//正しいユーザー名(test123)&間違ったパスワード(b1234567)でログインボタンを押下
	//画面遷移せずログイン画面に留まる
	@Test
	public void testLogin_WrongPassword_UnSuccess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "test123")
				.param("password", "b1234567");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
	}
	//正しいユーザー名(test123)&パスワードが空白でログインボタンを押下
	//画面遷移せずログイン画面に留まる
	@Test
	public void testLogin_NullPassword_UnSuccess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "test123")
				.param("password", "");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
	}
	//ユーザー名とパスワードが空白の状態ログインボタンを押下
	//画面遷移せずログイン画面に留まる
	@Test
	public void testLogin_NullUsernameAndPassword_UnSuccess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "")
				.param("password", "");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
	}
	//間違ったユーザー名(testx2)&間違ったパスワード(b1234567)でログインボタンを押下
	//画面遷移せずログイン画面に留まる
	@Test
	public void testLogin_WrongUsernameAndPassword_UnSuccess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login/process")
				.param("username", "testx2")
				.param("password", "b1234567");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
	}
	//logout画面表示テスト＿成功
	@Test
	public void testGetLogoutPage_Success() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/logout");
		MvcResult result = mockMvc.perform(request)
				.andExpect(redirectedUrl("/login")).andReturn();
		HttpSession session = result.getRequest().getSession();
		AdminEntity userList = (AdminEntity) session.getAttribute("admin");
		assertNull(userList);
		}



}
