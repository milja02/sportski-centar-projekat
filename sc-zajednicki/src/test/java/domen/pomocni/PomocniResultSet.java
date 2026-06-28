package domen.pomocni;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public final class PomocniResultSet {

    private PomocniResultSet() {
    }

    public static ResultSet jedanRed(Map<String, Object> red) {
        return viseRedova(List.of(red));
    }

    public static ResultSet viseRedova(List<Map<String, Object>> redovi) {
        return (ResultSet) Proxy.newProxyInstance(
                ResultSet.class.getClassLoader(),
                new Class<?>[] { ResultSet.class },
                new Handler(redovi));
    }

    private static final class Handler implements InvocationHandler {

        private final List<Map<String, Object>> redovi;
        private int index = -1;

        private Handler(List<Map<String, Object>> redovi) {
            this.redovi = redovi;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            String name = method.getName();
            if ("next".equals(name)) {
                index++;
                return index < redovi.size();
            }
            if (args != null && args.length == 1 && args[0] instanceof String kolona) {
                Object vrednost = redovi.get(index).get(kolona);
                if ("getInt".equals(name)) {
                    if (vrednost instanceof Number broj) {
                        return broj.intValue();
                    }
                    return Integer.parseInt(vrednost.toString());
                }
                if ("getString".equals(name)) {
                    return vrednost;
                }
                if ("getDate".equals(name)) {
                    return vrednost;
                }
            }
            if ("close".equals(name)) {
                return null;
            }
            Class<?> povratniTip = method.getReturnType();
            if (povratniTip == boolean.class) {
                return false;
            }
            if (povratniTip.isPrimitive()) {
                return 0;
            }
            return null;
        }
    }
}
