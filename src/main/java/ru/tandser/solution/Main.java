package ru.tandser.solution;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.XMLContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.ResourceUtils;
import ru.tandser.solution.domain.Role;
import ru.tandser.solution.domain.User;

import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:spring/datajpa-config.xml");
//        ctx.refresh();

//        UserRepository userService = ctx.getBean("userService", UserRepository.class);
//        MealRepository mealService = ctx.getBean("mealService", MealRepository.class);

        User user = new User();
        user.setName("Chris Schaefer");
        user.setEmail("c.schaefer@gmail.com");
        user.setPassword("aUeb6W2");
        user.setRole(Role.USER);
        user.setNormOfCalories(2000);

        StringWriter writer = new StringWriter();

        Mapping mapping = new Mapping();
        mapping.loadMapping(ResourceUtils.getURL("classpath:castor/mapping.xml"));

        XMLContext xmlContext = new XMLContext();
        xmlContext.addMapping(mapping);
        xmlContext.setProperty("org.exolab.castor.indent", "true");

        Marshaller marshaller = xmlContext.createMarshaller();
        marshaller.setWriter(writer);
        marshaller.marshal(user);

        System.out.println(writer);
    }
}