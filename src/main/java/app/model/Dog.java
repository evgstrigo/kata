package app.model;

import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "prototype")
public class Dog extends Animal {

    @Override
    public String toString() { return "Im a Dog"; }
}
