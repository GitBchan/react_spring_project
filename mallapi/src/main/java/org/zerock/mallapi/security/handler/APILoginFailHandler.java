package org.zerock.mallapi.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginFailHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
                log.info("Login fail....." + exception);

                Gson gson = new Gson();

                String jsonStr = gson.toJson(Map.of("error", "ERROR_LOGIN"));

                response.setContentType("application/json");
                PrintWriter printWriter = response.getWriter();
                printWriter.println(jsonStr);
                printWriter.close();
    }

    
}
