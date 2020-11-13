package com.parser.model;

import lombok.Data;

@Data
public class Product {
    private String category;
    private String name;
    private String oldPrice;
    private String newPrice;
    private String discountType;
    private String installment;
    private String coins;
    private String buyersVsItems;
    private String deliveryInfo;
    private String availableItems;

    public Product(){
    }

    public static class ProductBuilder {
        private final Product product;

        public ProductBuilder() {
            product = new Product();
        }

        public ProductBuilder withCategory(String category) {
            product.category = category;
            return this;
        }

        public ProductBuilder withName(String name) {
            product.name = name;
            return this;
        }

        public ProductBuilder withOldPrice(String oldPrice) {
            product.oldPrice = oldPrice;
            return this;
        }

        public ProductBuilder withNewPrice(String newPrice) {
            product.newPrice = newPrice;
            return this;
        }

        public ProductBuilder withDiscountType(String discountType) {
            product.discountType = discountType;
            return this;
        }

        public ProductBuilder withInstallment(String installment) {
            product.installment = installment;
            return this;
        }

        public ProductBuilder withCoins(String coins) {
            product.coins = coins;
            return this;
        }

        public ProductBuilder withBuyersVsItems(String buyersVsItems) {
            product.buyersVsItems = buyersVsItems;
            return this;
        }

        public ProductBuilder withDeliveryInfo(String deliveryInfo) {
            product.deliveryInfo = deliveryInfo;
            return this;
        }

        public ProductBuilder withAvailableItems(String availableItems) {
            product.availableItems = availableItems;
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
