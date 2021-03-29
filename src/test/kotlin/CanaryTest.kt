import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CanaryTest {
  @Test
  fun `junit is setup`() {
    assertTrue(true)
  }

  @Test
  fun `approval tests is setup`() {
    Approvals.verify("Hello, world!")
  }
}
