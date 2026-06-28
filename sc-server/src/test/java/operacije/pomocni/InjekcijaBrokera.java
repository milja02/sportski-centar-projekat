package operacije.pomocni;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import operacije.ApstraktnaGenerickaOperacija;
import repository.Repository;

public final class InjekcijaBrokera {

    private InjekcijaBrokera() {
    }

    public static <T extends ApstraktnaGenerickaOperacija> T saBrokerom(T operacija, Repository broker) {
        try {
            Field polje = ApstraktnaGenerickaOperacija.class.getDeclaredField("broker");
            polje.setAccessible(true);
            polje.set(operacija, broker);
            return operacija;
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException("Neuspela injekcija test brokera.", ex);
        }
    }

    public static void pokreniPreduslove(ApstraktnaGenerickaOperacija operacija, Object param) throws Exception {
        Method metoda = ApstraktnaGenerickaOperacija.class.getDeclaredMethod("preduslovi", Object.class);
        metoda.setAccessible(true);
        try {
            metoda.invoke(operacija, param);
        } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof Exception izuzetak) {
                throw izuzetak;
            }
            throw ex;
        }
    }
}
