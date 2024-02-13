import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


    @Retention(RetentionPolicy.RUNTIME)
    public @interface TgCommand {
        String name();
}
