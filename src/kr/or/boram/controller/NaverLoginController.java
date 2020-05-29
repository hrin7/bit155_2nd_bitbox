package kr.or.boram.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@WebServlet("/NaverLoginController.naver")
public class NaverLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaverLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("네이버로그인 get 방식");
		
		//RequestDispatcher dis = request.getRequestDispatcher("/common/Naver_Call.jsp");
		//dis.forward(request, response);
		
		String clientId = "rTQ7n_ItOVY1_1eVCyl1";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "jPMdMlab3f";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://127.0.0.1:8090/2nd_bitbox/NaverLoginController.naver", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    	 System.out.println(res.toString());
	    	 
	    	 JSONParser parsing = new JSONParser();
	    	 
	    	//Type mismatch: cannot convert from Object to JSONObject
	    	//JSONObject jsonObj = parsing.parse(res.toString()); 
	    	 
	    	 Object obj = parsing.parse(res.toString());
	    	 //System.out.println("JSON 타입을 파서한 객체반환"+obj);
	    	 JSONObject jsonObj =(JSONObject)obj;
	    	 //System.out.println("객체를 JSON 오브젝트로 변환??"+jsonObj);
	    	 
	    	 access_token = (String)jsonObj.get("access_token");
	    	
	    	 String req_type = (String)jsonObj.get("service_provider"); 
	    	 System.out.println("응답 타입"+req_type);
	    
	    	 
	    
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("네이버로그인 post 방식");
	}

}
