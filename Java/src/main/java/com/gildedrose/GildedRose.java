package com.gildedrose;

class GildedRose {
    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;
    public static final int BACKSTAGE_SELL_IN_STEP_QUALITY_INCREASE_BY_TWO = 10;
    public static final int BACKSTAGE_SELL_IN_STEP_QUALITY_INCREASE_BY_THREE = 5;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            boolean itemIsAgedBrie = items[i].name.equals("Aged Brie");
            boolean itemIsBackstagePasses = items[i].name.equals("Backstage passes to a TAFKAL80ETC concert");
            boolean itemIsSulfuras = items[i].name.equals("Sulfuras, Hand of Ragnaros");
            if (!itemIsSulfuras) {
                decreaseItemSellIn(items[i]);
            }
            boolean isSellByDateHasPassed = items[i].sellIn < 0;
            if (!itemIsAgedBrie && !itemIsBackstagePasses && !itemIsSulfuras) {
                updateQualityForClassicItem(items[i], isSellByDateHasPassed);
            } else if (itemIsAgedBrie) {
                updateQualityForAgedBrie(items[i], isSellByDateHasPassed);
            } else if (itemIsBackstagePasses) {
                updateQualityForBackStagePasses(items[i], isSellByDateHasPassed);
            }
        }
    }

    private void updateQualityForBackStagePasses(Item item, boolean isSellByDateHasPassed) {
        increaseBackStagePassesQuality(item);
        if (isSellByDateHasPassed) {
            setItemQualityToZero(item);
        }
    }

    private void updateQualityForAgedBrie(Item item, boolean isSellByDateHasPassed) {
        increaseItemQuality(item);
        if (isSellByDateHasPassed) {
            increaseItemQuality(item);
        }
    }

    private void updateQualityForClassicItem(Item item, boolean isSellByDateHasPassed) {
        decreaseItemQuality(item);
        if (isSellByDateHasPassed) {
            decreaseItemQuality(item);
        }
    }

    private void increaseBackStagePassesQuality(Item item) {
        increaseItemQuality(item);
        if (item.sellIn <= BACKSTAGE_SELL_IN_STEP_QUALITY_INCREASE_BY_TWO) {
            increaseItemQuality(item);
        }
        if (item.sellIn <= BACKSTAGE_SELL_IN_STEP_QUALITY_INCREASE_BY_THREE) {
            increaseItemQuality(item);
        }
    }

    private void decreaseItemSellIn(Item item) {
        item.sellIn--;
    }

    private void setItemQualityToZero(Item item) {
        item.quality = 0;
    }

    private void increaseItemQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    private void decreaseItemQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }
}
