package com.github.pareronia.projecteuler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.util.ANSIColors;
import com.github.pareronia.projecteuler.util.ProblemUtils;
import com.github.pareronia.projecteuler.util.ProblemUtils.MyCallable;
import com.github.pareronia.projecteuler.util.Timed;

public class AllProblemsRunner<T extends ProblemBase<?, ?>> {

    private record Params(String input, String output) {}

    private static int NUM_PROBLEMS = 975;
    private static final Map<Integer, Params> PARAMS = new HashMap<>();

    static {
        PARAMS.put(1, new Params("1000", "233168"));
        PARAMS.put(2, new Params("4000000", "4613732"));
        PARAMS.put(3, new Params("600851475143", "6857"));
        PARAMS.put(4, new Params("3", "906609"));
        PARAMS.put(5, new Params("20", "232792560"));
        PARAMS.put(6, new Params("100", "25164150"));
        PARAMS.put(7, new Params("10001", "104743"));
        PARAMS.put(8, new Params("13", "23514624000"));
        PARAMS.put(9, new Params("1000", "31875000"));
        PARAMS.put(10, new Params("2000000", "142913828922"));
        PARAMS.put(11, new Params("4", "70600674"));
        PARAMS.put(13, new Params("10", "5537376230"));
        PARAMS.put(14, new Params("1000000", "837799"));
        PARAMS.put(15, new Params("20", "137846528820"));
        PARAMS.put(16, new Params("1000", "1366"));
        PARAMS.put(17, new Params("1000", "21124"));
        PARAMS.put(18, new Params("classpath:0018_triangle.txt", "1074"));
        PARAMS.put(19, new Params("=none=", "171"));
        PARAMS.put(20, new Params("100", "648"));
        PARAMS.put(22, new Params("classpath:0022_names.txt", "871198282"));
        PARAMS.put(24, new Params("10", "2783915460"));
        PARAMS.put(25, new Params("1000", "4782"));
        PARAMS.put(28, new Params("1001", "669171001"));
        PARAMS.put(30, new Params("5", "443839"));
        PARAMS.put(67, new Params("classpath:0067_triangle.txt", "7273"));
        PARAMS.put(92, new Params("10000000", "8581146"));
    }

    @SuppressWarnings("unchecked")
    public void run(final Listener listener)
            throws NoSuchMethodException,
                    SecurityException,
                    InstantiationException,
                    IllegalAccessException,
                    IllegalArgumentException,
                    InvocationTargetException {
        final List<Class<T>> allProblems = getAllProblems();
        System.out.println("%d Problems found".formatted(allProblems.size()));
        for (final Class<T> problem : allProblems) {
            if (!ProblemBase.class.isAssignableFrom(problem)) {
                continue;
            }
            final Method create = problem.getDeclaredMethod("create");
            final T obj = (T) create.invoke(null);
            final String name = obj.getClass().getSimpleName();
            final int num = Integer.parseInt(name.substring("Problem".length(), name.length()));
            final Class<?> inputType = getInputType(problem);
            final Method solve = problem.getDeclaredMethod("solve", inputType);
            MyCallable<?> callable;
            if (inputType.equals(Integer.class)) {
                callable = () -> solve.invoke(obj, Integer.valueOf(PARAMS.get(num).input()));
            } else if (inputType.equals(Long.class)) {
                callable = () -> solve.invoke(obj, Long.valueOf(PARAMS.get(num).input()));
            } else if (inputType.equals(String.class)
                    && PARAMS.get(num).input().startsWith("classpath:")) {
                callable = () -> solve.invoke(obj, stringFromClasspath(PARAMS.get(num).input()));
            } else if (inputType.equals(NoInput.class)) {
                callable = () -> solve.invoke(obj, new NoInput());
            } else {
                throw new UnsupportedOperationException("Unsupported type");
            }
            final Timed<?> timed = Timed.timed(callable, System::nanoTime);
            listener.runCompleted(num, timed);
        }
    }

    private String stringFromClasspath(final String classpath) {
        final String filename = classpath.substring("classpath:".length(), classpath.length());
        return ProblemUtils.readString(filename);
    }

    private Class<?> getInputType(final Class<T> klass) {
        final ParameterizedType parameterizedType =
                (ParameterizedType) klass.getGenericSuperclass();
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    private List<Class<T>> getAllProblems() {
        return IntStream.rangeClosed(1, NUM_PROBLEMS)
                .mapToObj(this::findProblem)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private Optional<Class<T>> findProblem(final int n) {
        final String name = "%s.Problem%04d".formatted(this.getClass().getPackageName(), n);
        try {
            final Class<T> klass = (Class<T>) Class.forName(name);
            return Optional.of(klass);
        } catch (final ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public static void main(final String[] args) throws Exception {
        final AllListener listener = new AllListener();
        new AllProblemsRunner<>().run(listener);
        System.out.println("");
        System.out.println("Total: %s".formatted(ProblemUtils.printDuration(listener.total)));
    }

    private interface Listener {
        void runCompleted(int problemNumber, Timed<?> result);
    }

    private static final class AllListener implements Listener {
        private Duration total = Duration.ZERO;

        @Override
        public void runCompleted(final int problemNumber, final Timed<?> result) {
            System.out.println(
                    "%04d: %s, took %s"
                            .formatted(
                                    problemNumber,
                                    ANSIColors.bold(result.result().toString()),
                                    ProblemUtils.printDuration(result.duration())));
            this.total = this.total.plus(result.duration());
        }
    }
}
