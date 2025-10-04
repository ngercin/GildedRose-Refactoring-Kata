package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void quality_item_should_decreased_twice_when_sell_by_date_has_passed() {
        Item[] items = new Item[] { new Item("simple clothe", -2, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 8, app.items[0].quality);
        assertEquals( -3, app.items[0].sellIn);
    }

    @Test
    void quality_item_should_decreased_twice_when_sell_by_date_has_passed_start_limit() {
        Item[] items = new Item[] { new Item("simple clothe", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 8, app.items[0].quality);
        assertEquals( -1, app.items[0].sellIn);
    }

    @Test
    void quality_item_should_decreased_by_one_when_sell_by_date_has_not_passed() {
        Item[] items = new Item[] { new Item("simple clothe", 6, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 9, app.items[0].quality);
        assertEquals( 5, app.items[0].sellIn);
    }

    @Test
    void quality_item_should_not_been_negative_when_sell_by_date_has_passed() {
        Item[] items = new Item[] { new Item("simple clothe", -2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-3, app.items[0].sellIn);
    }

    @Test
    void quality_item_should_not_been_negative_when_sell_by_date_has_not_passed() {
        Item[] items = new Item[] { new Item("simple clothe", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void quality_age_brie_should_increase_by_one_when_sell_by_date_has_not_passed() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void quality_age_brie_should_increase_twice_when_sell_by_date_has_passed() {
        Item[] items = new Item[] { new Item("Aged Brie", -3, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
        assertEquals(-4, app.items[0].sellIn);
    }

    @Test
    void quality_of_item_is_not_more_than_50_when_sell_by_date_has_passed() {
        Item[] items = new Item[] { new Item("Aged Brie", -3, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(-4, app.items[0].sellIn);
    }

    @Test
    void quality_of_item_is_not_more_than_50_when_sell_by_date_has_not_passed() {
        Item[] items = new Item[] { new Item("Aged Brie", 3, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void Sulfuras_is_legendary() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 3, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void Backstage_passes_quality_increase_by_2_when_sellin_by_less_than_10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void Backstage_passes_quality_increase_by_3_when_sellin_by_less_than_5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void Backstage_passes_quality_passe_to_zero_when_sellin_has_passed() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-6, app.items[0].sellIn);
    }

}
