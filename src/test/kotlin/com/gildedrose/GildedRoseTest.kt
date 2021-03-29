package com.gildedrose

import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.From
import net.jqwik.api.Property
import net.jqwik.api.Provide
import org.approvaltests.Approvals

class GildedRoseTest {
  companion object {
    const val DATA_SET_SIZE = 50
  }

  @Property(
    tries = 1,
    seed = "-8556729318537930054"
  )
  fun `Aged Brie`(
    @ForAll @From("sellIns") sellIns: List<Int>,
    @ForAll @From("qualities") qualities: List<Int>,
  ) {
    // given
    val name = "Aged Brie"
    val items = sellIns.zip(qualities)
      .map { (sellIn, quality) -> Item(name, sellIn, quality) }
    val gildedRose = GildedRose(items.toTypedArray())

    // when
    gildedRose.updateQuality()

    // then
    val gildedRoseSnapshot = gildedRose
      .items
      .joinToString("\n") { it.toString() }
    Approvals.verify(gildedRoseSnapshot)
  }

  @Property(
    tries = 1,
    seed = "1002985723690070170"
  )
  fun `Backstage passes to a TAFKAL80ETC concert`(
    @ForAll @From("sellIns") sellIns: List<Int>,
    @ForAll @From("qualities") qualities: List<Int>,
  ) {
    // given
    val name = "Backstage passes to a TAFKAL80ETC concert"
    val items = sellIns.zip(qualities)
      .map { (sellIn, quality) -> Item(name, sellIn, quality) }
    val gildedRose = GildedRose(items.toTypedArray())

    // when
    gildedRose.updateQuality()

    // then
    val gildedRoseSnapshot = gildedRose
      .items
      .joinToString("\n") { it.toString() }
    Approvals.verify(gildedRoseSnapshot)
  }

  @Property(
    tries = 1,
    seed = "3398327011427988732"
  )
  fun `Sulfuras, Hand of Ragnaros`(
    @ForAll @From("sellIns") sellIns: List<Int>,
    @ForAll @From("qualities") qualities: List<Int>,
  ) {
    // given
    val name = "Sulfuras, Hand of Ragnaros"
    val items = sellIns.zip(qualities)
      .map { (sellIn, quality) -> Item(name, sellIn, quality) }
    val gildedRose = GildedRose(items.toTypedArray())

    // when
    gildedRose.updateQuality()

    // then
    val gildedRoseSnapshot = gildedRose
      .items
      .joinToString("\n") { it.toString() }
    Approvals.verify(gildedRoseSnapshot)
  }

  @Property(
    tries = 1,
    seed = "6350692656906137395"
  )
  fun `all other items`(
    @ForAll @From("sellIns") sellIns: List<Int>,
    @ForAll @From("qualities") qualities: List<Int>,
  ) {
    // given
    val name = "all other items"
    val items = sellIns.zip(qualities)
      .map { (sellIn, quality) -> Item(name, sellIn, quality) }
    val gildedRose = GildedRose(items.toTypedArray())

    // when
    gildedRose.updateQuality()

    // then
    val gildedRoseSnapshot = gildedRose
      .items
      .joinToString("\n") { it.toString() }
    Approvals.verify(gildedRoseSnapshot)
  }

  @Provide
  fun sellIns(): Arbitrary<List<Int>> =
    Arbitraries
      .integers()
      .between(0, 50)
      .list()
      .uniqueElements()
      .ofSize(DATA_SET_SIZE)

  @Provide
  fun qualities(): Arbitrary<List<Int>> =
    Arbitraries
      .integers()
      .between(-10, 60)
      .list()
      .uniqueElements()
      .ofSize(DATA_SET_SIZE)
}
