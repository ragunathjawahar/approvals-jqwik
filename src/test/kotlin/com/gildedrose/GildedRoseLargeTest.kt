package com.gildedrose

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.From
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.approvaltests.Approvals

class GildedRoseLargeTest {
  companion object {
    private const val DATA_SET_SIZE = 1000
  }

  @Property(
    seed = "6348610496668862340",
    tries = 1
  )
  fun `gilded rose golden master`(
    @ForAll @From("names") names: List<String>,
    @ForAll @From("sellIns") sellIns: List<Int>,
    @ForAll @From("qualities") qualities: List<Int>,
  ) {
    // given
    val items = names.zip(sellIns.zip(qualities)) { name, (sellIn, quality) ->
      Item(name, sellIn, quality)
    }
    val gildedRose = GildedRose(items.toTypedArray())

    // when
    for (i in 0..25) {
      gildedRose.updateQuality()
    }

    // then
    val gildedRoseSnapshot = gildedRose
      .items
      .joinToString("\n") { it.toString() }
    Approvals.verify(gildedRoseSnapshot)
  }

  @Provide
  fun names(): Arbitrary<List<String>> {
    return Arbitraries.of(
      "Aged Brie",
      "Backstage passes to a TAFKAL80ETC concert",
      "Sulfuras, Hand of Ragnaros",
      "Mana Flower",
    )
      .list()
      .ofSize(DATA_SET_SIZE)
  }

  @Provide
  fun sellIns(): Arbitrary<List<Int>> =
    Arbitraries.integers().between(-10, 60).list().ofSize(DATA_SET_SIZE)

  @Provide
  fun qualities(): Arbitrary<List<Int>> =
    Arbitraries.integers().between(-10, 60).list().ofSize(DATA_SET_SIZE)
}
