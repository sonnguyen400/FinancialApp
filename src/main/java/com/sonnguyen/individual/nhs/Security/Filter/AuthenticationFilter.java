package com.sonnguyen.individual.nhs.Security.Filter;

import com.sonnguyen.individual.nhs.Model.Account;
import com.sonnguyen.individual.nhs.Model.Login;
import com.sonnguyen.individual.nhs.Service.IService.IAccountService;
import com.sonnguyen.individual.nhs.Utils.SessionUtils;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE;

@Model
public class AuthenticationFilter  implements Filter {
    private final Logger logger=Logger.getLogger(this.getClass().getName());
    @Inject
    IAccountService accountService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Auth filter initialized");
    }
    @Override
    public void destroy() {
        logger.info("Auth filter destroyed");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(ExclusiveFilter.isAllow(request)){
            filterChain.doFilter(servletRequest,response);
        }else{
            Login account = SessionUtils.getPrincipal(request);
            if(account == null){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                request.setAttribute(ERROR_MESSAGE,"unauthorized");
                request.getRequestDispatcher("/page/base/error.jsp").forward(request,response);
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
