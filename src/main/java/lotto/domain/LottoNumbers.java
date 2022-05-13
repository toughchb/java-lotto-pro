package lotto.domain;

import static lotto.constants.LottoConstants.MAX_LOTTO_NUMBER_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.generator.NumberGenerateStrategy;

public class LottoNumbers {

    private final List<LottoNumber> numbers;

    private LottoNumbers(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public static LottoNumbers from(NumberGenerateStrategy strategy) {
        List<Integer> generatedNumbers = strategy.generate();
        return new LottoNumbers(convertToLottoNumber(generatedNumbers));
    }

    public static LottoNumbers from(List<LottoNumber> numbers) {
        return new LottoNumbers(numbers);
    }

    private void validateNumbers(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != MAX_LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        Set<LottoNumber> numbersSet = new HashSet<>(numbers);
        if (numbersSet.size() != MAX_LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private static List<LottoNumber> convertToLottoNumber(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    public int hitCounts(LottoNumbers otherNumbers) {
        return (int) otherNumbers.getNumbers().
                stream().
                filter(lottoNumber -> numbers.contains(lottoNumber)).
                count();
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}