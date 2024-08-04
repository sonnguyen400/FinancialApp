package com.sonnguyen.individual.nhs.security.Filter;

import com.sonnguyen.individual.nhs.model.Login;
import com.sonnguyen.individual.nhs.security.UserDetailImp;
import com.sonnguyen.individual.nhs.security.core.SecurityContextHolder;
import com.sonnguyen.individual.nhs.service.iservice.IAccountService;
import com.sonnguyen.individual.nhs.utils.SessionUtils;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.spi.*;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;

import static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE;

@Model
public class AuthenticationFilter  implements Filter {
    private final Logger logger=Logger.getLogger(this.getClass().getName());
    @Inject
    IAccountService accountService;
    @Inject
    BeanManager beanManager;
    @Inject
    SecurityContextHolder securityContextHolder;
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
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            UserDetailImp account = SessionUtils.getPrincipal(request);
            if(account == null){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                request.setAttribute(ERROR_MESSAGE,"unauthorized");
                request.getRequestDispatcher("/page/base/error.jsp").forward(servletRequest,servletResponse);
            }else{
                securityContextHolder.setPrincipal(account);
                filterChain.doFilter(request,response);
            }
        }
    }
}
