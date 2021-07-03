package com.github.pareronia.projecteuler;

import static java.util.Comparator.naturalOrder;

import java.util.Arrays;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.lang3.ArrayUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "create")
public class Problem0008 extends ProblemBase {
    
    private static final String NUMBER =
            "73167176531330624919225119674426574742355349194934" +
            "96983520312774506326239578318016984801869478851843" +
            "85861560789112949495459501737958331952853208805511" +
            "12540698747158523863050715693290963295227443043557" +
            "66896648950445244523161731856403098711121722383113" +
            "62229893423380308135336276614282806444486645238749" +
            "30358907296290491560440772390713810515859307960866" +
            "70172427121883998797908792274921901699720888093776" +
            "65727333001053367881220235421809751254540594752243" +
            "52584907711670556013604839586446706324415722155397" +
            "53697817977846174064955149290862569321978468622482" +
            "83972241375657056057490261407972968652414535100474" +
            "82166370484403199890008895243450658541227588666881" +
            "16427171479924442928230863465674813919123162824586" +
            "17866458359124566529476545682848912883142607690042" +
            "24219022671055626321111109370544217506941658960408" +
            "07198403850962455444362981230987879927244284909188" +
            "84580156166097919133875499200524063689912560717606" +
            "05886116467109405077541002256983155200055935729725" +
            "71636269561882670428252483600823257530420752963450";

    private final transient Integer input;
    
    @Override
    public Long solve() {
        final CircularFifoQueue<Character> buffer
                = new CircularFifoQueue<>(this.input);
        Arrays.stream(ArrayUtils.toObject(NUMBER.toCharArray()))
                .limit(this.input - 1)
                .forEach(buffer::add);
        return (long) Arrays.stream(ArrayUtils.toObject(NUMBER.toCharArray()))
                .skip(this.input - 1)
                .map(ch -> {
                    buffer.add(ch);
                    return buffer.stream()
                            .map(ch1 -> (long) Character.digit(ch1, 10))
                            .reduce(1L, (a, b) -> a * b);
                })
//                .peek(p -> System.out.println(buffer + " " + p.toString()))
                .max(naturalOrder()).orElseThrow();
    }

    public static void main(final String[] args) {
        assert Problem0008.create(4).solve().intValue() == 5832;
        
        lap("13", () -> Problem0008.create(13).solve());
    }
}
