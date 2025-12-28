package com.github.pareronia.projecteuler;

import static java.util.stream.Collectors.toMap;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.util.ANSIColors;
import com.github.pareronia.projecteuler.util.ProblemUtils;
import com.github.pareronia.projecteuler.util.ProblemUtils.MyCallable;
import com.github.pareronia.projecteuler.util.StringUtils;
import com.github.pareronia.projecteuler.util.Timed;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class AllProblemsRunner<T extends ProblemBase<?, ?>> {

    private record Params(String input, String output) {}

    private static int NUM_PROBLEMS = 976;

    private Map<Integer, Params> getParams() {
        return Arrays.stream(StringUtils.splitLines(ProblemUtils.readString("problems.csv")))
                .map(s -> s.split(","))
                .collect(
                        toMap(
                                sp -> Integer.parseInt(sp[0]),
                                sp ->
                                        new Params(
                                                sp[1].replace("\"", ""), sp[2].replace("\"", ""))));
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
        final Map<Integer, Params> params = getParams();
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
                callable = () -> solve.invoke(obj, Integer.valueOf(params.get(num).input()));
            } else if (inputType.equals(Long.class)) {
                callable = () -> solve.invoke(obj, Long.valueOf(params.get(num).input()));
            } else if (inputType.equals(String.class)
                    && params.get(num).input().startsWith("classpath:")) {
                callable = () -> solve.invoke(obj, stringFromClasspath(params.get(num).input()));
            } else if (inputType.equals(NoInput.class)) {
                callable = () -> solve.invoke(obj, new NoInput());
            } else {
                throw new UnsupportedOperationException("Unsupported type");
            }
            final Timed<?> timed = Timed.timed(callable, System::nanoTime);
            listener.runCompleted(
                    num,
                    params.get(num).output(),
                    String.valueOf(timed.result()),
                    timed.duration());
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
        final PrintStream out = System.out;
        final AllListener listener = new AllListener(out);
        new AllProblemsRunner<>().run(listener);
        out.println("");
        out.println("Total: %.3f s".formatted(listener.total.toMillis() / 1000d));
    }

    private interface Listener {
        void runCompleted(int problemNumber, String expected, String actual, Duration duration);
    }

    private static final class AllListener implements Listener {
        private final PrintStream out;
        private Duration total = Duration.ZERO;

        private AllListener(final PrintStream out) {
            this.out = out;
        }

        @Override
        public void runCompleted(
                final int problemNumber,
                final String expected,
                final String actual,
                final Duration duration) {
            final String msg;
            if (actual.equals(expected)) {
                msg =
                        "%04d: %s, took %s"
                                .formatted(
                                        problemNumber,
                                        ANSIColors.bold(actual),
                                        ProblemUtils.printDuration(duration));
            } else {
                msg =
                        ANSIColors.red(
                                "%04d: FAIL: expected %s, got %s"
                                        .formatted(problemNumber, expected, actual));
            }
            this.out.println(msg);
            this.total = this.total.plus(duration);
        }
    }
}
