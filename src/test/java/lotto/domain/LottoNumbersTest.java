package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sun.tools.javac.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    void 정수_리스트로부터_객체를_만든다() {
        LottoNumbers numbers = LottoNumbers.from(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(numbers)
                .isEqualTo(LottoNumbers.from(Arrays.asList(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                        LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6))));
    }

    @Test
    void 개수_체크() {
        assertThatThrownBy(
                () -> {
                    LottoNumbers numbers = LottoNumbers.from(() -> Arrays.asList(1, 2, 3, 4, 5));
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복_에크() {
        assertThatThrownBy(
                () -> {
                    LottoNumbers numbers = LottoNumbers.from(() -> Arrays.asList(1, 2, 3, 4, 5, 5));
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 맞는_로또_번호_개수_구하기() {
        LottoNumbers lotto = LottoNumbers.from(() ->Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumbers winingLotto = LottoNumbers.from(() ->Arrays.asList(4, 5, 6, 7, 8, 9));
        assertThat(lotto.hitCounts(winingLotto)).isEqualTo(3);
    }
}
