package com.sonnguyen.individual.nhs;

import java.io.*;

import com.sonnguyen.individual.nhs.Model.Term;
import com.sonnguyen.individual.nhs.Repository.IRepository.ITermRepository;
import com.sonnguyen.individual.nhs.Repository.IRepository.Repository;
import com.sonnguyen.individual.nhs.Repository.TermsRepository;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    Repository<Term,Integer> repository=new ITermRepository();
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        repository.findAll();
        Term term=new Term(2,"jd","jdh");
        repository.insert(term);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}