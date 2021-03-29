import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide

class CanaryJqwikTest {
  @Property
  fun `jqwik is setup`(
    @ForAll("divisibleBy3") number: Int
  ): Boolean {
    return fizzBuzz()[number - 1].startsWith("Fizz")
  }

  @Provide
  fun divisibleBy3(): Arbitrary<Int> =
    Arbitraries
      .integers()
      .between(1, 100)
      .filter { it % 3 == 0 }

  private fun fizzBuzz(): List<String> =
    (1..100).map(this::word)

  private fun word(number: Int): String {
    val divisibleBy3 = number % 3 == 0
    val divisibleBy5 = number % 5 == 0
    return if (divisibleBy3 && divisibleBy5) {
      "FizzBuzz"
    } else if (divisibleBy3) {
      "Fizz"
    } else if (divisibleBy5) {
      "Buzz"
    } else {
      number.toString()
    }
  }
}
