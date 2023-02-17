package main.java.com.company.practics.practic_8.Command;

public class JavaClass extends Class{
    public final String documentation;

    public JavaClass(String code, String documentation) {
        super(code);
        this.documentation = documentation;
    }

    @Override
    public void beWritten(Developer developer) {
        developer.create(this);
    }

    @Override
    public String toString() {
        return "JavaClass{" +
                "documentation='" + documentation + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
